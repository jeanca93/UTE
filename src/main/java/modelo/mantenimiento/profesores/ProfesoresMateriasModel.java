package modelo.mantenimiento.profesores;

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

import entidades.Materiascursos;
import entidades.Profesores;
import entidades.Profesoresmaterias;
import entidades.ProfesoresmateriasId;
import entidadesDAO.EstadosHome;
import entidadesDAO.ProfesoresmateriasHomeExt;
import modelo.mantenimiento.materiascursos.MateriascursosDatos;
import modelo.mantenimiento.materiascursos.MateriascursosStatus;

public class ProfesoresMateriasModel {
	private ListModelList<MateriascursosStatus> allMateriascursosStatus;
	private Profesores profesor;
	@Wire
	private Grid GridProfMaterias;
	@Wire
	private Window modalDialog;
	
	public ProfesoresMateriasModel() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
		
		Execution execution = Executions.getCurrent();
		profesor = (Profesores)execution.getArg().get("profesor");
		
		allMateriascursosStatus = new ListModelList<MateriascursosStatus>();
		allMateriascursosStatus = genListModel(new  MateriascursosDatos("").getAllMateriascursos());
	}
	
	@Command
	@NotifyChange("allMateriascursosStatus")
	public void selectedCheckBox(@BindingParam("CheckEvent") CheckEvent evt){
		for(MateriascursosStatus mateCursosStatus:allMateriascursosStatus){
			if(!mateCursosStatus.isEditingStatus())
				mateCursosStatus.setSeleccionado(evt.isChecked());
	   }
	}
	
	@Command
	public void grabarProfMaterias(){
		List<Materiascursos> profMateriascursos = new ArrayList<Materiascursos>();
		
		for(MateriascursosStatus mateCursosStatus:allMateriascursosStatus){
	    	if(mateCursosStatus.isSeleccionado())
	    		profMateriascursos.add(mateCursosStatus.getMateriascursos());
	    }
		
		if(profMateriascursos.size() == 0){
			Clients.alert("Debe seleccionar una materia m&iacute;nimo para continuar", "Error", null);
		}else{
			Session session = Sessions.getCurrent();
			Integer idUsuario = Integer.parseInt(session.getAttribute("idUsuario").toString());
			
			List<Profesoresmaterias> listProfMaterias = new ArrayList<Profesoresmaterias>();
			
			for(Materiascursos matCur:profMateriascursos){
				Profesoresmaterias profmaterias = new Profesoresmaterias();
				
				ProfesoresmateriasId id = new ProfesoresmateriasId();
				id.setMateriascursos(matCur);
				id.setProfesores(profesor);
				
				profmaterias.setId(id);
				profmaterias.setEstados(new EstadosHome().findById(1));
				profmaterias.setFechaCreacion(new Date());
				profmaterias.setUsuarioCrea(idUsuario);
				
				listProfMaterias.add(profmaterias);
			}
			
			try{
				new ProfesoresmateriasHomeExt().registrarMateriasProfesor(listProfMaterias);
				
				modalDialog.detach();
				
				Clients.showNotification("Materias por cursos asignadas correctamente");
			}catch(RuntimeException re){
				throw re;
			}
		}
	}
	
	private ListModelList<MateriascursosStatus> genListModel(List<Materiascursos> lsMateriasCursos){
		List<Materiascursos> listProfesoresmaterias = new ProfesoresmateriasHomeExt().listProfesoresmaterias(profesor.getIdProfesor());
		
    	for(Materiascursos mateCursos: lsMateriasCursos){
    		boolean seleccionado = false;
    		
    		for(Materiascursos mateCursosProfesor: listProfesoresmaterias){
    			if(mateCursos.equals(mateCursosProfesor)){
    				seleccionado = true;
    				break;
    			}
    		}
    		
    		allMateriascursosStatus.add(new MateriascursosStatus(mateCursos, seleccionado, false));
		}
    	
    	return allMateriascursosStatus;
    }
    
	public ListModelList<MateriascursosStatus> getAllMateriascursosStatus() {
		return allMateriascursosStatus;
	}
}
