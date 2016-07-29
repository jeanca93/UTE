package modelo.anioescolar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import entidades.Estados;
import entidades.Schollaryear;
import entidadesDAO.SchollaryearHome;
import modelo.estados.EstadosDatos;

public class AnioEscolarModel {
	private ListModelList<AnioEscolarStatus> allAnioEscolarStatus;
	private List<Schollaryear> listAnioTMP;
	private List<Estados> allEstados;
	private boolean displayEdit = true;
	
	@Wire
	private Grid GridAnioEscolar;
	
	public AnioEscolarModel(){
		super();
		
		allEstados = new ArrayList<Estados>();
		allEstados = new EstadosDatos().getAllEstados();

		allAnioEscolarStatus = new ListModelList<AnioEscolarStatus>();
		allAnioEscolarStatus = genListModel(new AnioEscolarDatos().getAllAnioEscolar());
		
		listAnioTMP = new ArrayList<Schollaryear>();
	}
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
	}
	
	@NotifyChange({"allAnioEscolar", "displayEdit"})
    public void setDisplayEdit(boolean displayEdit) {
        this.displayEdit = displayEdit;
    }
	
	@Command
    public void nuevoAnioEscolar() {
		//Map<String, Object> parameters = new HashMap<String, Object>();
		//parameters.put("modelPrincipal",allAnioEscolarStatus );
		//parameters.put("gridPrincipal",GridAnioEscolar );
		
		Window window = (Window)Executions.createComponents(
                "/WEB-INF/include/AnioEscolar/vtnAnioEscolar.zul", null, null);
        window.doModal();
    }
	
	@Command
	public void eliminarAnioEscolar(){
		List<Row> components = GridAnioEscolar.getRows().getChildren();
	    final List<Schollaryear> anioDelete = new ArrayList<Schollaryear>();
	    
	    for(Row row:components){
	      //Row comp = (Row) obj;
	      Checkbox ck = (Checkbox) row.getChildren().get(0);
	      
	      if(ck.isChecked()){
	    	  AnioEscolarStatus anioStatus = (AnioEscolarStatus)row.getValue();
	    	  anioDelete.add(anioStatus.getAnioescolar());
	      }
	   }
	    
	   if(anioDelete.size() == 0){
		   Clients.alert("Debe seleccionar m&iacute;nimo un registro para continuar", "Error", null);
	   }else{
		   Messagebox.show("¿Est&aacute; seguro que desea continuar?", "Mensaje de Confirmaci&oacute;n", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
			
				public void onEvent(Event event) throws Exception {
					// TODO Auto-generated method stub
					if (event.getName().equals("onYes")) {								
						try{
							for(Schollaryear aula:anioDelete){
								new SchollaryearHome().delete(aula);
							}
							
							BindUtils.postNotifyChange(null, null,this, "*");
							
							refresh();
							
							Clients.showNotification("Registro eliminado correctamente");
						}catch(RuntimeException re){
							throw re;
						}
			        }
				}
		   });
	   }
	}
	
	public void refresh() {
		allAnioEscolarStatus = new ListModelList<AnioEscolarStatus>();
		allAnioEscolarStatus = genListModel(new AnioEscolarDatos().getAllAnioEscolar());
		GridAnioEscolar.setModel(allAnioEscolarStatus);
	}
	
	@Command
	public void selectedCheckBox(@BindingParam("CheckEvent") CheckEvent evt){
		List<Row> rows = GridAnioEscolar.getRows().getChildren();
	    
	    for(Row row:rows){
	      Checkbox ck = (Checkbox) row.getChildren().get(0);
	      AnioEscolarStatus anioStatus = (AnioEscolarStatus)row.getValue();
	      if(!anioStatus.isEditingStatus())
	    	  ck.setChecked(evt.isChecked());
	   }
	}
	
	@Command
    public void changeEditableStatus(@BindingParam("AnioEscolarStatus") AnioEscolarStatus anio) {
		infoAnioInicial(anio.getAnioescolar());
		
		anio.setEditingStatus(!anio.isEditingStatus());
        refreshRowTemplate(anio);
    }
     
    @Command
    public void confirm(@BindingParam("AnioEscolarStatus") AnioEscolarStatus anio) {
    	changeEditableStatus(anio);
        refreshRowTemplate(anio);
        
        Schollaryear anioTMP = null;
        boolean flagCambio = false;
        
        for(Schollaryear aniotmp:listAnioTMP){
        	if(aniotmp.getIdSchoolarYear() == anio.getAnioescolar().getIdSchoolarYear()){
        		if(aniotmp.getSchollarYear() != anio.getAnioescolar().getSchollarYear() || 
        			aniotmp.getFechaInicio() != anio.getAnioescolar().getFechaInicio() ||
        			aniotmp.getFechaFin() != anio.getAnioescolar().getFechaFin()  ||
        			aniotmp.getEstados() != anio.getAnioescolar().getEstados())
        			flagCambio = true;
        		
        		anioTMP = aniotmp;
        		
        		break;
        	}
        }
        
        if (flagCambio){
        	try{
        		Session session = Sessions.getCurrent();
        		anio.getAnioescolar().setUsuarioModifica(Integer.parseInt(session.getAttribute("idUsuario").toString()));
        		anio.getAnioescolar().setFechaModificacion(new Date());
        		new SchollaryearHome().update(anio.getAnioescolar());
        		
        	//	refresh();
        		
        		Clients.showNotification("Registro modificado correctamente");
        	}catch(RuntimeException re){
        		throw re;
        	}
        }
        
        listAnioTMP.remove(anioTMP);
    }
	
    private void infoAnioInicial(Schollaryear anio)
    {
    	boolean flag = false;
    	
    	if(listAnioTMP.size() != 0){    		
    		for(Schollaryear an:listAnioTMP){
    			if(an.getIdSchoolarYear() == anio.getIdSchoolarYear()){
    				flag = true;
    				
    				break;
    			}
    		}
    	}
    	
    	if(flag == false){
    		listAnioTMP.add(new Schollaryear());
    		listAnioTMP.get(listAnioTMP.size()-1).setIdSchoolarYear(anio.getIdSchoolarYear());
    		listAnioTMP.get(listAnioTMP.size()-1).setSchollarYear(anio.getSchollarYear());
    		listAnioTMP.get(listAnioTMP.size()-1).setFechaInicio(anio.getFechaInicio());
    		listAnioTMP.get(listAnioTMP.size()-1).setFechaFin(anio.getFechaFin());
    		listAnioTMP.get(listAnioTMP.size()-1).setEstados(anio.getEstados());
    		
		}
    }
    
    public void refreshRowTemplate(AnioEscolarStatus aul) {
        //replace the element in the collection by itself to trigger a model update
    	allAnioEscolarStatus.set(allAnioEscolarStatus.indexOf(aul), aul);
    }
	
    private ListModelList<AnioEscolarStatus> genListModel(List<Schollaryear> lsAnioEscolar){
    	for(Schollaryear aul: lsAnioEscolar){
    		allAnioEscolarStatus.add(new AnioEscolarStatus(aul, false, false));
		}
    	
    	return allAnioEscolarStatus;
    }
    
	public ListModelList<AnioEscolarStatus> getAllAnioEscolar() {
		return allAnioEscolarStatus;
	}
	
	public List<Estados> getAllEstados() {
		return allEstados;
	}

	public boolean isDisplayEdit() {
        return displayEdit;
    }

}
