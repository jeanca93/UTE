package modelo.mantenimiento.aulas;

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

import entidades.Aulas;
import entidades.Estados;
import entidades.Tipoaula;
import entidadesDAO.AulasHome;
import modelo.mantenimiento.estados.EstadosDatos;
import modelo.mantenimiento.tipoaula.TipoAulaDatos;

public class AulasModel {
	private List<Tipoaula> allTipoAulas;
	private ListModelList<AulasStatus> allAulasStatus;
	private List<Aulas> listAulaTMP;
	private List<Estados> allEstados;
	private boolean displayEdit = true;
	
	@Wire
	private Grid GridAulas;
	
	public AulasModel(){
		super();
		
		allEstados = new ArrayList<Estados>();
		allEstados = new EstadosDatos().getAllEstado();
		
		allTipoAulas = new ArrayList<Tipoaula>();
		allTipoAulas = new TipoAulaDatos(true).getAllTipoAula();

		allAulasStatus = new ListModelList<AulasStatus>();
		allAulasStatus = genListModel(new AulasDatos().getAllAula());
		
		listAulaTMP = new ArrayList<Aulas>();
	}
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
	}
	
	@NotifyChange({"allAulasStatus", "displayEdit"})
    public void setDisplayEdit(boolean displayEdit) {
        this.displayEdit = displayEdit;
    }
	
	@Command
    public void nuevaAula() {
		//Map<String, Object> parameters = new HashMap<String, Object>();
		//parameters.put("modelPrincipal",allAulasStatus );
		//parameters.put("gridPrincipal",GridAulas );
		
		Window window = (Window)Executions.createComponents(
                "/WEB-INF/include/Mantenimiento/Aulas/vtnAulas.zul", null, null);
        window.doModal();
    }
	
	@Command
	public void eliminarAulas(){
	    final List<Aulas> aulaDelete = new ArrayList<Aulas>();
	    
	    for(AulasStatus aulStatus:allAulasStatus){
	    	if(aulStatus.isSeleccionado())
	    		aulaDelete.add(aulStatus.getAula());
	    }
	    
	    if(aulaDelete.size() == 0){
	    	Clients.alert("Debe seleccionar mínimo un registro para continuar", "Error", null);
	    }else{
	    	Messagebox.show("¿Está seguro que desea continuar?", "Mensaje de Confirmación", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
	    		
	    		public void onEvent(Event event) throws Exception {
	    			// TODO Auto-generated method stub
	    			
	    			if (event.getName().equals("onYes")) {
	    				try{
	    					for(Aulas aula:aulaDelete){
	    						new AulasHome().delete(aula);
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
		allTipoAulas = new ArrayList<Tipoaula>();
		allTipoAulas = new TipoAulaDatos(true).getAllTipoAula();
		
		allAulasStatus = new ListModelList<AulasStatus>();
		allAulasStatus = genListModel(new AulasDatos().getAllAula());
		GridAulas.setModel(allAulasStatus);
	}
	
	@Command
	@NotifyChange("allAulasStatus")
	public void selectedCheckBox(@BindingParam("CheckEvent") CheckEvent evt){
		for(AulasStatus aulStatus:allAulasStatus){
			if(!aulStatus.isEditingStatus())
				aulStatus.setSeleccionado(evt.isChecked());
		}
	}
	
	@Command
    public void changeEditableStatus(@BindingParam("AulasStatus") AulasStatus aul) {
		infoAulaInicial(aul.getAula());
		
		aul.setEditingStatus(!aul.isEditingStatus());
        refreshRowTemplate(aul);
    }
     
    @Command
    public void confirm(@BindingParam("AulasStatus") AulasStatus aul) {
    	changeEditableStatus(aul);
        refreshRowTemplate(aul);
        
        Aulas aulTMP = null;
        boolean flagCambio = false;
        
        for(Aulas aulas:listAulaTMP){
        	if(aulas.getIdAula() == aul.getAula().getIdAula()){
        		if(aulas.getAula() != aul.getAula().getAula() || 
        		   aulas.getAsientos() != aul.getAula().getAsientos() ||
        		   aulas.getComentarios() != aul.getAula().getComentarios()  ||
        		   aulas.getTipoaula() != aul.getAula().getTipoaula() ||
        		   aulas.getEstados() != aul.getAula().getEstados())
        			flagCambio = true;
        		
        		aulTMP = aulas;
        		
        		break;
        	}
        }
        
        if (flagCambio){
        	try{
        		Session session = Sessions.getCurrent();
        		aul.getAula().setUsuarioModifica(Integer.parseInt(session.getAttribute("idUsuario").toString()));
        		aul.getAula().setFechaModificacion(new Date());
        		new AulasHome().update(aul.getAula());

        		//refresh();
        		
        		Clients.showNotification("Registro modificado correctamente");
        	}catch(RuntimeException re){
        		throw re;
        	}
        }
        
        listAulaTMP.remove(aulTMP);
    }
	
    private void infoAulaInicial(Aulas aul)
    {
    	boolean flag = false;
    	
    	if(listAulaTMP.size() != 0){    		
    		for(Aulas au:listAulaTMP){
    			if(au.getIdAula() == aul.getIdAula()){
    				flag = true;
    				
    				break;
    			}
    		}
    	}
    	
    	if(flag == false){
    		listAulaTMP.add(new Aulas());
    		listAulaTMP.get(listAulaTMP.size()-1).setIdAula(aul.getIdAula());
    		listAulaTMP.get(listAulaTMP.size()-1).setAula(aul.getAula());
    		listAulaTMP.get(listAulaTMP.size()-1).setAsientos(aul.getAsientos());
    		listAulaTMP.get(listAulaTMP.size()-1).setComentarios(aul.getComentarios());
    		listAulaTMP.get(listAulaTMP.size()-1).setTipoaula(aul.getTipoaula());
    		listAulaTMP.get(listAulaTMP.size()-1).setEstados(aul.getEstados());
    		
		}
    }
    
    public void refreshRowTemplate(AulasStatus aul) {
        //replace the element in the collection by itself to trigger a model update
    	allAulasStatus.set(allAulasStatus.indexOf(aul), aul);
    }
	
    private ListModelList<AulasStatus> genListModel(List<Aulas> lsAulas){
    	for(Aulas aul: lsAulas){
			allAulasStatus.add(new AulasStatus(aul, false, false));
		}
    	
    	return allAulasStatus;
    }
    
	public ListModelList<AulasStatus> getAllAulasStatus() {
		return allAulasStatus;
	}
	
	public List<Tipoaula> getAllTipoAulas() {
		return allTipoAulas;
	}
	
	
	public List<Estados> getAllEstados() {
		return allEstados;
	}
	
	public boolean isDisplayEdit() {
        return displayEdit;
    }	

}
