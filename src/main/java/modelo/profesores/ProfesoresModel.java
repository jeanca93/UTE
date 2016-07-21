package modelo.profesores;

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

import entidades.Profesores;
import entidadesDAO.ProfesoresHomeExt;

public class ProfesoresModel {
	private ListModelList<ProfesorStatus> allProfesoresStatus;
	private List<Profesores> listProfesorTMP;
	private boolean displayEdit = true;
	
	@Wire
	private Grid GridProfesores;
	
	public ProfesoresModel(){
		super();
		
		allProfesoresStatus = new ListModelList<ProfesorStatus>();		
		listProfesorTMP = new ArrayList<Profesores>();
	}
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
		
		refresh();		
	}
	
	@NotifyChange({"allProfesores", "displayEdit"})
    public void setDisplayEdit(boolean displayEdit) {
        this.displayEdit = displayEdit;
    }
	
	@Command
    public void nuevoProfesor() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("modelPrincipal",allProfesoresStatus );
		parameters.put("gridPrincipal",GridProfesores );
		
		Window window = (Window)Executions.createComponents(
                "/WEB-INF/include/Profesores/AdministracionProfesores/vtnProfesores.zul", null, parameters);
        window.doModal();
    }
	
	@Command
	public void eliminarProfesores(){
		List<Row> components = GridProfesores.getRows().getChildren();
	    final List<Profesores> profesoresDelete = new ArrayList<Profesores>();
	    
	    for(Row row:components){
	      //Row comp = (Row) obj;
	      Checkbox ck = (Checkbox) row.getChildren().get(0);
	      
	      if(ck.isChecked()){
	    	  ProfesorStatus profesorStatus = (ProfesorStatus)row.getValue();
	    	  profesoresDelete.add(profesorStatus.getProfesor());
	      }
	   }
	    
	   if(profesoresDelete.size() == 0){
		   Clients.alert("Debe seleccionar mínimo un registro para continuar", "Error", null);
	   }else{
		   Messagebox.show("Esta seguro que desea continuar?", "Mensaje de Confirmación", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
			
				public void onEvent(Event event) throws Exception {
					// TODO Auto-generated method stub
					if (event.getName().equals("onYes")) {								
						try{
							for(Profesores profesor:profesoresDelete){
								new ProfesoresHomeExt().delete(profesor);
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
		ProfesoresDatos profesorDatos = new  ProfesoresDatos();
		
		allProfesoresStatus = new ListModelList<ProfesorStatus>();
		allProfesoresStatus = genListModel(profesorDatos.getAllProfesores());
		GridProfesores.setModel(allProfesoresStatus);
	}
	
	@Command
	public void selectedCheckBox(@BindingParam("CheckEvent") CheckEvent evt){
		List<Row> rows = GridProfesores.getRows().getChildren();
	    
	    for(Row row:rows){
	      Checkbox ck = (Checkbox) row.getChildren().get(0);
	      ProfesorStatus profesorStatus = (ProfesorStatus)row.getValue();
	      if(!profesorStatus.isEditingStatus())
	    	  ck.setChecked(evt.isChecked());
	   }
	}
	
	@Command
    public void changeEditableStatus(@BindingParam("ProfesorStatus") ProfesorStatus prof) {
		infoUsuarioInicial(prof.getProfesor());
		
		prof.setEditingStatus(!prof.isEditingStatus());
        refreshRowTemplate(prof);
    }
     
    @Command
    public void confirm(@BindingParam("ProfesorStatus") ProfesorStatus prof) {
    	changeEditableStatus(prof);
        refreshRowTemplate(prof);
        
        Profesores profTMP = null;
        boolean flagCambio = false;
        
        for(Profesores profesor:listProfesorTMP){
        	if(profesor.getIdProfesor() == prof.getProfesor().getIdProfesor()){
        		if(profesor.getProfesor() != prof.getProfesor().getProfesor() || profesor.getTitulo() != prof.getProfesor().getTitulo() ||
        				profesor.getMaxHorasPorSemana() != prof.getProfesor().getMaxHorasPorSemana())
        			flagCambio = true;
        		
        		profTMP = profesor;
        		
        		break;
        	}
        }
        
        if (flagCambio){
        	try{
        		Session session = Sessions.getCurrent();
				prof.getProfesor().setUsuarioModifica(Integer.parseInt(session.getAttribute("idUsuario").toString()));
				prof.getProfesor().setFechaModificacion(new Date());
        		new ProfesoresHomeExt().attachDirty(prof.getProfesor());
        		
        		refresh();
        		
        		Clients.showNotification("Modificado correctamente");
        	}catch(RuntimeException re){
        		throw re;
        	}
        }
        
        listProfesorTMP.remove(profTMP);
    }
	
    private void infoUsuarioInicial(Profesores profesor)
    {
    	boolean flag = false;
    	
    	if(listProfesorTMP.size() != 0){    		
    		for(Profesores prof:listProfesorTMP){
    			if(prof.getIdProfesor() == profesor.getIdProfesor()){
    				flag = true;
    				
    				break;
    			}
    		}
    	}
    	
    	if(flag == false){
			listProfesorTMP.add(new Profesores());
			listProfesorTMP.get(listProfesorTMP.size()-1).setIdProfesor(profesor.getIdProfesor());
			listProfesorTMP.get(listProfesorTMP.size()-1).setProfesor(profesor.getProfesor());
			listProfesorTMP.get(listProfesorTMP.size()-1).setTitulo(profesor.getTitulo());
			listProfesorTMP.get(listProfesorTMP.size()-1).setMaxHorasPorSemana(profesor.getMaxHorasPorSemana());

		}
    }
    
    public void refreshRowTemplate(ProfesorStatus profesor) {
        //replace the element in the collection by itself to trigger a model update
    	allProfesoresStatus.set(allProfesoresStatus.indexOf(profesor), profesor);
    }
	
    private ListModelList<ProfesorStatus> genListModel(List<Profesores> lsProfesores){
    	for(Profesores profesor: lsProfesores){
			allProfesoresStatus.add(new ProfesorStatus(profesor, false, false));
		}
    	
    	return allProfesoresStatus;
    }
    
	public ListModelList<ProfesorStatus> getAllProfesores() {
		return allProfesoresStatus;
	}
		
	public boolean isDisplayEdit() {
        return displayEdit;
    }
}
