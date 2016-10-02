package modelo.mantenimiento.anioescolar;

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
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import entidades.Schollaryear;
import entidadesDAO.EstadosHome;
import entidadesDAO.EstadosHomeExt;
import entidadesDAO.SchollaryearHome;

public class AnioEscolarModel {
	private ListModelList<AnioEscolarStatus> allAnioEscolarStatus;
	private List<Schollaryear> listAnioTMP;
	private boolean displayEdit = true;
	
	@Wire
	private Grid GridAnioEscolar;
	
	public AnioEscolarModel(){
		super();
		
		refresh();
		
		listAnioTMP = new ArrayList<Schollaryear>();
	}
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
	}
	
	@NotifyChange({"allAnioEscolarStatus", "displayEdit"})
    public void setDisplayEdit(boolean displayEdit) {
        this.displayEdit = displayEdit;
    }
	
	@Command
    public void nuevoAnioEscolar() {
		//Map<String, Object> parameters = new HashMap<String, Object>();
		//parameters.put("modelPrincipal",allAnioEscolarStatus );
		//parameters.put("gridPrincipal",GridAnioEscolar );
		
		Window window = (Window)Executions.createComponents(
                "/WEB-INF/include/Mantenimiento/AnioEscolar/vtnAnioEscolar.zul", null, null);
        window.doModal();
    }
	
	@Command
    public void jornadaAcademica() {
		//Map<String, Object> parameters = new HashMap<String, Object>();
		//parameters.put("modelPrincipal",allAnioEscolarStatus );
		//parameters.put("gridPrincipal",GridAnioEscolar );
		
		Window window = (Window)Executions.createComponents(
                "/WEB-INF/include/Mantenimiento/AnioEscolar/vtnJornadaAcademica.zul", null, null);
        window.doModal();
    }
	
	@Command
	public void eliminarAnioEscolar(){
		final List<Schollaryear> anioDelete = new ArrayList<Schollaryear>();
	    
	    for(AnioEscolarStatus anioStatus:allAnioEscolarStatus){
	    	if(anioStatus.isSeleccionado())
	    		anioDelete.add(anioStatus.getAnioescolar());
	    }
	    
	    if(anioDelete.size() == 0){
	    	Clients.alert("Debe seleccionar m&iacute;nimo un registro para continuar", "Error", null);
	    }else{
	    	Messagebox.show("¿Está seguro que desea continuar?", "Mensaje de Confirmación", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
	    		
	    		public void onEvent(Event event) throws Exception {
	    			// TODO Auto-generated method stub
	    			
	    			if (event.getName().equals("onYes")) {
	    				try{
	    					for(Schollaryear aula:anioDelete){
	    						new SchollaryearHome().delete(aula);
	    					}
	    					
	    					BindUtils.postNotifyChange(null, null,this, "*");
	    					
	    					refresh();
	    					
	    					GridAnioEscolar.setModel(allAnioEscolarStatus);
	    					
	    					Clients.showNotification("Registro eliminado correctamente");
	    				}catch(RuntimeException re){
	    					throw re;
	    				}
	    			}
	    		}
	    	});
	    }
	}
	
	@Command
	public void habilitarSchollarYear(){
		List<Schollaryear> listSchollarYear = new ArrayList<Schollaryear>();
		
		for(AnioEscolarStatus schollaryearStatus:allAnioEscolarStatus){
			if(schollaryearStatus.isSeleccionado())
				listSchollarYear.add(schollaryearStatus.getAnioescolar());
		}
		
		if (listSchollarYear.size() > 1)
			Clients.alert("Solo puede seleccionar una año escolar a la vez", "Error", null);
		else if(listSchollarYear.size() == 1){
			int yearHabilitado = 0;
			
			for(AnioEscolarStatus SchollarYearStatus: allAnioEscolarStatus){
				if (SchollarYearStatus.getAnioescolar().getIdSchoolarYear() != listSchollarYear.get(0).getIdSchoolarYear()){
					if (SchollarYearStatus.getAnioescolar().getEstados().getIdEstado() == 1){
						yearHabilitado = SchollarYearStatus.getAnioescolar().getIdSchoolarYear();
						
						break;
					}
				}
			}
			
			if (yearHabilitado == 0)
				modificarEstado(listSchollarYear.get(0), EstadosHomeExt.ESTADO_ACTIVO);
			else if(yearHabilitado == listSchollarYear.get(0).getIdSchoolarYear())
				Clients.alert("Año escolar ya se encuentra habilitado", "Error", null);
			else
				Clients.alert("No puede tener varios años escolares habilitados al mismo tiempo", "Error", null);
		}else
			Clients.alert("Debe seleccionar un año escolar para continuar", "Error", null);
	}
	
	@Command
	public void inhabilitarSchollarYear(){
		List<Schollaryear> listSchollarYear = new ArrayList<Schollaryear>();
		
		for(AnioEscolarStatus schollaryearStatus:allAnioEscolarStatus){
			if(schollaryearStatus.isSeleccionado())
				listSchollarYear.add(schollaryearStatus.getAnioescolar());
		}
		
		if (listSchollarYear.size() > 1)
			Clients.alert("Solo puede seleccionar una año escolar a la vez", "Error", null);
		else if(listSchollarYear.size() == 1){
			if(listSchollarYear.get(0).getEstados().getIdEstado() == 2)
				Clients.alert("Año escolar ya se encuentra inhabilitado", "Error", null);
			else
				modificarEstado(listSchollarYear.get(0), EstadosHomeExt.ESTADO_INACTIVO);
		}else
			Clients.alert("Debe seleccionar un año escolar para continuar", "Error", null);
	}
	
	private void modificarEstado(final Schollaryear schollaryear, final int estado){
		Messagebox.show("¿Está seguro que desea continuar?", "Mensaje de Confirmación", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
    		
    		public void onEvent(Event event) throws Exception {
    			// TODO Auto-generated method stub
    			
    			if (event.getName().equals("onYes")) {    				
    				try{
    					schollaryear.setEstados(new EstadosHome().findById(estado));
        				schollaryear.setFechaModificacion(new Date());
        				Session session = Sessions.getCurrent();
        				schollaryear.setUsuarioModifica(Integer.parseInt(session.getAttribute("idUsuario").toString()));
        				
    					new SchollaryearHome().update(schollaryear);
    		    		
    					Messagebox.show("Registro modificado correctamente", "Exito", Messagebox.OK,  Messagebox.EXCLAMATION, new EventListener<Event>() {
    						
    						public void onEvent(Event event) throws Exception {
    							// TODO Auto-generated method stub
    							Executions.sendRedirect("");
    						}
    					});
    				}catch(RuntimeException re){
    					throw re;
    				}
    			}
    		}
    	});
	}
	
	public void refresh() {
		allAnioEscolarStatus = new ListModelList<AnioEscolarStatus>();
		allAnioEscolarStatus = genListModel(new AnioEscolarDatos(false).getAllAnioEscolar());
	}
	
	@Command
	@NotifyChange("allAnioEscolarStatus")
	public void selectedCheckBox(@BindingParam("CheckEvent") CheckEvent evt){
	    for(AnioEscolarStatus anioStatus:allAnioEscolarStatus){
	      if(!anioStatus.isEditingStatus())
	    	  anioStatus.setSeleccionado(evt.isChecked());
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
        			aniotmp.getDuracionClase() != anio.getAnioescolar().getDuracionClase() ||
        			aniotmp.getMaxHorasSemanaProfesor() != anio.getAnioescolar().getMaxHorasSemanaProfesor())
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
    		listAnioTMP.get(listAnioTMP.size()-1).setDuracionClase(anio.getDuracionClase());
    		listAnioTMP.get(listAnioTMP.size()-1).setMaxHorasSemanaProfesor(anio.getMaxHorasSemanaProfesor());
    		
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
    
	public ListModelList<AnioEscolarStatus> getAllAnioEscolarStatus() {
		return allAnioEscolarStatus;
	}
	
	public boolean isDisplayEdit() {
        return displayEdit;
    }

}
