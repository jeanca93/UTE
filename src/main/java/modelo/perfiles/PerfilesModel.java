package modelo.perfiles;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.CheckEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Window;

import entidades.Perfilesusuario;
import entidadesDAO.PerfilesusuarioHomeExt;

public class PerfilesModel {
	private ListModelList<PerfilStatus> allPerfilStatus;
	private List<Perfilesusuario> listPerfilesTMP;
	private boolean displayEdit = true;
	
	@Wire
	private Grid GridPerfiles;
	
	public PerfilesModel(){
		super();
		
		allPerfilStatus = new ListModelList<PerfilStatus>();		
		listPerfilesTMP = new ArrayList<Perfilesusuario>();
	}
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
		
		refresh();		
	}
	
	@NotifyChange({"allPerfiles", "displayEdit"})
    public void setDisplayEdit(boolean displayEdit) {
        this.displayEdit = displayEdit;
    }
	
	@Command
    public void nuevoPerfil() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("modelPrincipal",allPerfilStatus );
		parameters.put("gridPrincipal",GridPerfiles );
		
		Window window = (Window)Executions.createComponents(
                "/WEB-INF/include/Usuarios/AdministracionPerfiles/vtnPerfiles.zul", null, parameters);
        window.doModal();
    }
	
	@Command
	public void eliminarPerfiles(){
		List<Row> components = GridPerfiles.getRows().getChildren();
	    final List<Perfilesusuario> perfilesDelete = new ArrayList<Perfilesusuario>();
	    
	    for(Row row:components){
	      //Row comp = (Row) obj;
	      Checkbox ck = (Checkbox) row.getChildren().get(0);
	      
	      if(ck.isChecked()){
	    	  PerfilStatus perfilStatus = (PerfilStatus)row.getValue();
	    	  perfilesDelete.add(perfilStatus.getPerfil());
	      }
	   }
	    
	   if(perfilesDelete.size() == 0){
		   Clients.alert("Debe seleccionar mínimo un registro para continuar", "Error", null);
	   }else{
		   Messagebox.show("Esta seguro que desea continuar?", "Mensaje de Confirmación", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
			
				public void onEvent(Event event) throws Exception {
					// TODO Auto-generated method stub
					if (event.getName().equals("onYes")) {								
						try{
							for(Perfilesusuario perfil:perfilesDelete){
								new PerfilesusuarioHomeExt().delete(perfil);
							}
							
							BindUtils.postNotifyChange(null, null,this, "*");
							
							refresh();
							
							Clients.showNotification("Eliminado correctamente");
						}catch(RuntimeException re){
							throw re;
						}
			        }
				}
		   });
	   }
	}
	
	public void refresh() {
		PerfilesDatos perfilDatos = new  PerfilesDatos();
		
		allPerfilStatus = new ListModelList<PerfilStatus>();
		allPerfilStatus = genListModel(perfilDatos.getAllPerfiles());
		GridPerfiles.setModel(allPerfilStatus);
	}
	
	@Command
	public void selectedCheckBox(@BindingParam("CheckEvent") CheckEvent evt){
		List<Row> rows = GridPerfiles.getRows().getChildren();
	    
	    for(Row row:rows){
	      Checkbox ck = (Checkbox) row.getChildren().get(0);
	      PerfilStatus perfilStatus = (PerfilStatus)row.getValue();
	      if(!perfilStatus.isEditingStatus())
	    	  ck.setChecked(evt.isChecked());
	   }
	}
	
	@Command
    public void changeEditableStatus(@BindingParam("PerfilStatus") PerfilStatus perfil) {
		infoUsuarioInicial(perfil.getPerfil());
		
		perfil.setEditingStatus(!perfil.isEditingStatus());
        refreshRowTemplate(perfil);
    }
     
    @Command
    public void confirm(@BindingParam("PerfilStatus") PerfilStatus perfil) {
    	changeEditableStatus(perfil);
        refreshRowTemplate(perfil);
        
        Perfilesusuario perfilTMP = null;
        boolean flagCambio = false;
        
        for(Perfilesusuario perf:listPerfilesTMP){
        	if(perf.getIdPerfilUsuario() == perfil.getPerfil().getIdPerfilUsuario()){
        		if(perf.getPerfil() != perfil.getPerfil().getPerfil() || perf.getComentarios() != perfil.getPerfil().getComentarios())
        			flagCambio = true;
        		
        		perfilTMP = perf;
        		
        		break;
        	}
        }
        
        if (flagCambio){
        	try{
        		Session session = Sessions.getCurrent();
        		perfil.getPerfil().setUsuarioModifica(Integer.parseInt(session.getAttribute("idUsuario").toString()));
        		perfil.getPerfil().setFechaModificacion(new Date());
        		new PerfilesusuarioHomeExt().attachDirty(perfil.getPerfil());
        		
        		refresh();
        		
        		Clients.showNotification("Modificado correctamente");
        	}catch(RuntimeException re){
        		throw re;
        	}
        }
        
        listPerfilesTMP.remove(perfilTMP);
    }
	
    private void infoUsuarioInicial(Perfilesusuario perfil)
    {
    	boolean flag = false;
    	
    	if(listPerfilesTMP.size() != 0){    		
    		for(Perfilesusuario perf:listPerfilesTMP){
    			if(perf.getIdPerfilUsuario() == perfil.getIdPerfilUsuario()){
    				flag = true;
    				
    				break;
    			}
    		}
    	}
    	
    	if(flag == false){
    		listPerfilesTMP.add(new Perfilesusuario());
    		listPerfilesTMP.get(listPerfilesTMP.size()-1).setIdPerfilUsuario(perfil.getIdPerfilUsuario());
    		listPerfilesTMP.get(listPerfilesTMP.size()-1).setPerfil(perfil.getPerfil());
    		listPerfilesTMP.get(listPerfilesTMP.size()-1).setComentarios(perfil.getComentarios());
		}
    }
    
    public void refreshRowTemplate(PerfilStatus perfil) {
        //replace the element in the collection by itself to trigger a model update
    	allPerfilStatus.set(allPerfilStatus.indexOf(perfil), perfil);
    }
	
    private ListModelList<PerfilStatus> genListModel(List<Perfilesusuario> lsPerfiles){
    	for(Perfilesusuario perfil: lsPerfiles){
			allPerfilStatus.add(new PerfilStatus(perfil, false, false));
		}
    	
    	return allPerfilStatus;
    }
    
	public ListModelList<PerfilStatus> getAllPerfiles() {
		return allPerfilStatus;
	}
	
	public boolean isDisplayEdit() {
        return displayEdit;
    }
}
