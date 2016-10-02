package modelo.mantenimiento.materias;

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

import entidades.Estados;
import entidades.Materias;
import entidades.Tipoaula;
import entidadesDAO.MateriasHome;
import modelo.mantenimiento.estados.EstadosDatos;
import modelo.mantenimiento.tipoaula.TipoAulaDatos;

public class MateriasModel {
	private List<Tipoaula> allTipoAulas;
	private List<Estados> allEstados;
	private ListModelList<MateriaStatus> allMateriasStatus;
	private List<Materias> listMateriaTMP;	
	private boolean displayEdit = true;
	
	@Wire
	private Grid GridMaterias;
	
	public MateriasModel(){
		super();
		
		allTipoAulas = new ArrayList<Tipoaula>();
		allTipoAulas = new TipoAulaDatos(true).getAllTipoAula();
		
		allEstados = new ArrayList<Estados>();
		allEstados = new EstadosDatos().getAllEstado();
		
		allMateriasStatus = new ListModelList<MateriaStatus>();
		allMateriasStatus = genListModel(new MateriaDatos(false).getAllMaterias());
		
		listMateriaTMP = new ArrayList<Materias>();
	}
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
	}
	
	@NotifyChange({"allMateriasStatus", "displayEdit"})
    public void setDisplayEdit(boolean displayEdit) {
        this.displayEdit = displayEdit;
    }
	
	@Command
    public void nuevaMateria() {
		/*
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("modelPrincipal",allMateriasStatus );
		parameters.put("gridPrincipal",GridMaterias );
		
		Window window = (Window)Executions.createComponents(
                "/WEB-INF/include/Materias/vtnMaterias.zul", null, parameters);
        window.doModal();
        */
		
		Window window = (Window)Executions.createComponents(
                "/WEB-INF/include/Mantenimiento/Materias/vtnMaterias.zul", null, null);
        window.doModal();
    }
	
	@Command
	public void eliminarMaterias(){
		final List<Materias> materiaDelete = new ArrayList<Materias>();
		
		for(MateriaStatus materStatus:allMateriasStatus){
			if(materStatus.isSeleccionado())
				materiaDelete.add(materStatus.getMaterias());
		}
		
		if(materiaDelete.size() == 0){
			Clients.alert("Debe seleccionar m&iacute;nimo un registro para continuar", "Error", null);
		}else{
			Messagebox.show("¿Está seguro que desea continuar?", "Mensaje de Confirmación", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
				
				public void onEvent(Event event) throws Exception {
					// TODO Auto-generated method stub
					
					if (event.getName().equals("onYes")) {
						try{
							for(Materias materia:materiaDelete){
								new MateriasHome().delete(materia);
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
		allTipoAulas = new  TipoAulaDatos(true).getAllTipoAula();
		
		allMateriasStatus = new ListModelList<MateriaStatus>();
		allMateriasStatus = genListModel(new  MateriaDatos(false).getAllMaterias());
		GridMaterias.setModel(allMateriasStatus);
	}
	
	@Command
	@NotifyChange("allMateriasStatus")
	public void selectedCheckBox(@BindingParam("CheckEvent") CheckEvent evt){
		for(MateriaStatus mateStatus:allMateriasStatus){
			if(!mateStatus.isEditingStatus())
				mateStatus.setSeleccionado(evt.isChecked());
		}
	}
	
	@Command
    public void changeEditableStatus(@BindingParam("MateriaStatus") MateriaStatus mate) {
		infoMateriaInicial(mate.getMaterias());
		
		mate.setEditingStatus(!mate.isEditingStatus());
        refreshRowTemplate(mate);
    }
     
    @Command
    public void confirm(@BindingParam("MateriaStatus") MateriaStatus mate) {
    	changeEditableStatus(mate);
        refreshRowTemplate(mate);
        
        Materias mateTMP = null;
        boolean flagCambio = false;
        
        for(Materias materia:listMateriaTMP){
        	if(materia.getIdMateria() == mate.getMaterias().getIdMateria()){
        		if(materia.getMateria() != mate.getMaterias().getMateria() || 
        				materia.getTipoaula() != mate.getMaterias().getTipoaula() ||
        				materia.getEstados() != mate.getMaterias().getEstados())
        			flagCambio = true;
        		
        		mateTMP = materia;
        		
        		break;
        	}
        }
        
        if (flagCambio){
        	try{
        		Session session = Sessions.getCurrent();
				mate.getMaterias().setUsuarioModifica(Integer.parseInt(session.getAttribute("idUsuario").toString()));
				mate.getMaterias().setFechaModificacion(new Date());
        		new MateriasHome().update(mate.getMaterias());
        		
        		//refresh();
        		
        		Clients.showNotification("Registro modificado correctamente");
        	}catch(RuntimeException re){
        		throw re;
        	}
        }
        
        listMateriaTMP.remove(mateTMP);
    }
	
    private void infoMateriaInicial(Materias mate)
    {
    	boolean flag = false;
    	
    	if(listMateriaTMP.size() != 0){    		
    		for(Materias mat:listMateriaTMP){
    			if(mat.getIdMateria() == mate.getIdMateria()){
    				flag = true;
    				
    				break;
    			}
    		}
    	}
    	
    	if(flag == false){
    		listMateriaTMP.add(new Materias());
    		listMateriaTMP.get(listMateriaTMP.size()-1).setIdMateria(mate.getIdMateria());
    		listMateriaTMP.get(listMateriaTMP.size()-1).setMateria(mate.getMateria());
    		listMateriaTMP.get(listMateriaTMP.size()-1).setTipoaula(mate.getTipoaula());
    		listMateriaTMP.get(listMateriaTMP.size()-1).setEstados(mate.getEstados());
    		
		}
    }
    
    public void refreshRowTemplate(MateriaStatus mate) {
        //replace the element in the collection by itself to trigger a model update
    	allMateriasStatus.set(allMateriasStatus.indexOf(mate), mate);
    }
	
    private ListModelList<MateriaStatus> genListModel(List<Materias> lsMaterias){
    	for(Materias mate: lsMaterias){
			allMateriasStatus.add(new MateriaStatus(mate, false, false));
		}
    	
    	return allMateriasStatus;
    }
    
	public ListModelList<MateriaStatus> getAllMateriasStatus() {
		return allMateriasStatus;
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
