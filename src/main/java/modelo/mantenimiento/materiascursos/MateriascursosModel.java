package modelo.mantenimiento.materiascursos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.CheckEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import entidades.Cursos;
import entidades.Materias;
import entidades.Materiascursos;
import entidades.MateriascursosId;
import entidadesDAO.EstadosHome;
import entidadesDAO.MateriascursosHomeExt;
import modelo.mantenimiento.materias.MateriaDatos;

public class MateriascursosModel {
	private ListModelList<MateriasHorasSemanaStatus> allMateriaStatus;
	private Cursos curso;
	@Wire
	private Window modalDialog;
	@Wire
	private Grid GridMateriasCurso;
	
	public MateriascursosModel() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
		
		Execution execution = Executions.getCurrent();
		curso = (Cursos)execution.getArg().get("curso");
		
		allMateriaStatus = new ListModelList<MateriasHorasSemanaStatus>();
		allMateriaStatus = genListModel(new  MateriaDatos(true).getAllMaterias());
	}
	
	@Command
	@NotifyChange("allMateriaStatus")
	public void selectedCheckBox(@BindingParam("CheckEvent") CheckEvent evt){
		for(MateriasHorasSemanaStatus mateStatus:allMateriaStatus){
			if(!mateStatus.isEditingStatus())
				mateStatus.setSeleccionado(evt.isChecked());
	   }
	}
	
	@Command
	public void grabarMateriasCursos(){
		List<MateriasHorasSemanaStatus> materiascursos = new ArrayList<MateriasHorasSemanaStatus>();
	    
	    for(MateriasHorasSemanaStatus mateStatus:allMateriaStatus){
	    	if(mateStatus.isSeleccionado())
	    		materiascursos.add(mateStatus);
	    }
	    
	    if(materiascursos.size() == 0){
	    	Clients.alert("Debe seleccionar una materia m&iacute;nimo para continuar", "Error", null);
	    }else{
	    	Session session = Sessions.getCurrent();
	    	Integer idUsuario = Integer.parseInt(session.getAttribute("idUsuario").toString());
	    	
	    	List<Materiascursos> listMateriasCur = new ArrayList<Materiascursos>();
	    	
	    	for(MateriasHorasSemanaStatus materiasHorassemana:materiascursos){
	    		Materiascursos materiascur = new Materiascursos();
	    		
	    		MateriascursosId id = new MateriascursosId();
	    		
	    		id.setCursos(curso);
	    		id.setMaterias(materiasHorassemana.getMaterias());
	    		
	    		materiascur.setId(id);
	    		materiascur.setHorasSemana(materiasHorassemana.getHorassemana());
	    		materiascur.setEstados(new EstadosHome().findById(1));
	    		materiascur.setFechaCreacion(new Date());
	    		materiascur.setUsuarioCrea(idUsuario);
	    		
	    		listMateriasCur.add(materiascur);
	    	}
	    	
	    	try{
	    		new MateriascursosHomeExt().registrarMateriasCursos(listMateriasCur);
	    		
	    		modalDialog.detach();
	    		
	    		Clients.showNotification("Materias asignadas correctamente");
	    	}catch(RuntimeException re){
	    		throw re;
	    	}
	    }
	}
	
	private ListModelList<MateriasHorasSemanaStatus> genListModel(List<Materias> lsMaterias){
		List<Materiascursos> listMateriasCurso = new MateriascursosDatos(curso.getIdCurso()).getAllMateriascursos();
		
		int horassemana = 1;
		
    	for(Materias mate: lsMaterias){
    		boolean seleccionado = false;
    		
    		for(Materiascursos mateCursos: listMateriasCurso){
    			if(mateCursos.getId().getMaterias().equals(mate)){
    				horassemana = mateCursos.getHorasSemana();
    				seleccionado = true;
    				break;
    			}
    		}
    		
    		allMateriaStatus.add(new MateriasHorasSemanaStatus(mate, horassemana, seleccionado, false));
		}
    	
    	return allMateriaStatus;
    }
    
	public ListModelList<MateriasHorasSemanaStatus> getAllMateriasStatus() {
		return allMateriaStatus;
	}

}
