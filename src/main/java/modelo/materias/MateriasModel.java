package modelo.materias;

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

import entidades.Materias;
import entidadesDAO.MateriasHomeExt;

public class MateriasModel {

	private ListModelList<MateriaStatus> allMateriasStatus;
	private List<Materias> listMateriaTMP;
	private boolean displayEdit = true;
	
	@Wire
	private Grid GridMaterias;
	
	public MateriasModel(){
		super();
		
		
		allMateriasStatus = new ListModelList<MateriaStatus>();		
		listMateriaTMP = new ArrayList<Materias>();
	}
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
		
		refresh();		
	}
	
	@NotifyChange({"allMaterias", "displayEdit"})
    public void setDisplayEdit(boolean displayEdit) {
        this.displayEdit = displayEdit;
    }
	
	@Command
    public void nuevaMateria() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("modelPrincipal",allMateriasStatus );
		parameters.put("gridPrincipal",GridMaterias );
		
		Window window = (Window)Executions.createComponents(
                "/WEB-INF/include/Materias/vtnMaterias.zul", null, parameters);
        window.doModal();
    }
	
	@Command
	public void eliminarMaterias(){
		List<Row> components = GridMaterias.getRows().getChildren();
	    final List<Materias> materiaDelete = new ArrayList<Materias>();
	    
	    for(Row row:components){
	      //Row comp = (Row) obj;
	      Checkbox ck = (Checkbox) row.getChildren().get(0);
	      
	      if(ck.isChecked()){
	    	  MateriaStatus materStatus = (MateriaStatus)row.getValue();
	    	  materiaDelete.add(materStatus.getMateria());
	      }
	   }
	    
	   if(materiaDelete.size() == 0){
		   Clients.alert("Debe seleccionar mínimo un registro para continuar", "Error", null);
	   }else{
		   Messagebox.show("Esta seguro que desea continuar?", "Mensaje de Confirmación", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
			
				public void onEvent(Event event) throws Exception {
					// TODO Auto-generated method stub
					if (event.getName().equals("onYes")) {								
						try{
							for(Materias materia:materiaDelete){
								new MateriasHomeExt().delete(materia);
							}
							
							BindUtils.postNotifyChange(null, null,this, "*");
							
							refresh();
							
							Clients.showNotification("Eliminada correctamente");
						}catch(RuntimeException re){
							throw re;
						}
			        }
				}
		   });
	   }
	}
	
	public void refresh() {
		MateriaDatos materiaDatos = new  MateriaDatos();

		allMateriasStatus = new ListModelList<MateriaStatus>();
		allMateriasStatus = genListModel(materiaDatos.getAllMaterias());
		GridMaterias.setModel(allMateriasStatus);
	}
	
	@Command
	public void selectedCheckBox(@BindingParam("CheckEvent") CheckEvent evt){
		List<Row> rows = GridMaterias.getRows().getChildren();
	    
	    for(Row row:rows){
	      Checkbox ck = (Checkbox) row.getChildren().get(0);
	      MateriaStatus mateStatus = (MateriaStatus)row.getValue();
	      if(!mateStatus.isEditingStatus())
	    	  ck.setChecked(evt.isChecked());
	   }
	}
	
	@Command
    public void changeEditableStatus(@BindingParam("MateriaStatus") MateriaStatus mate) {
		infoMateriaInicial(mate.getMateria());
		
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
        	if(materia.getIdMateria() == mate.getMateria().getIdMateria()){
        		if(materia.getMateria() != mate.getMateria().getMateria() || materia.getHorasSemana() != mate.getMateria().getHorasSemana() )
        			flagCambio = true;
        		
        		mateTMP = materia;
        		
        		break;
        	}
        }
        
        if (flagCambio){
        	try{
        		Session session = Sessions.getCurrent();
				mate.getMateria().setUsuarioModifica(Integer.parseInt(session.getAttribute("idUsuario").toString()));
				mate.getMateria().setFechaModificacion(new Date());
        		new MateriasHomeExt().attachDirty(mate.getMateria());
        		
        		refresh();
        		
        		Clients.showNotification("Materia Modificada correctamente");
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
    			if(mat.getMateria() == mate.getMateria()){
    				flag = true;
    				
    				break;
    			}
    		}
    	}
    	
    	if(flag == false){
    		listMateriaTMP.add(new Materias());
    		listMateriaTMP.get(listMateriaTMP.size()-1).setIdMateria(mate.getIdMateria());
    		listMateriaTMP.get(listMateriaTMP.size()-1).setMateria(mate.getMateria());
    		listMateriaTMP.get(listMateriaTMP.size()-1).setHorasSemana(mate.getHorasSemana());
    		
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
    
	public ListModelList<MateriaStatus> getAllMaterias() {
		return allMateriasStatus;
	}
	

		
	public boolean isDisplayEdit() {
        return displayEdit;
    }

}
