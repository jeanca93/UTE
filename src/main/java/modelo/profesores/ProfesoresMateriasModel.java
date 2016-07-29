package modelo.profesores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.CheckEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.Window;

import entidades.Materiascursos;
import entidades.Profesores;
import entidades.Profesoresmaterias;
import entidades.ProfesoresmateriasId;
import entidadesDAO.EstadosHome;
import entidadesDAO.ProfesoresmateriasHomeExt;
import modelo.materias.MateriaStatus;
import modelo.materiascursos.MateriascursosDatos;
import modelo.materiascursos.MateriascursosStatus;

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
	public void selectedCheckBox(@BindingParam("CheckEvent") CheckEvent evt){
		List<Row> rows = GridProfMaterias.getRows().getChildren();
	    
	    for(Row row:rows){
	      Checkbox ck = (Checkbox) row.getChildren().get(0);
	      MateriascursosStatus mateCursosStatus = (MateriascursosStatus)row.getValue();
	      if(!mateCursosStatus.isEditingStatus())
	    	  ck.setChecked(evt.isChecked());
	   }
	}
	
	@Command
	public void grabarProfMaterias(){
		List<Row> components = GridProfMaterias.getRows().getChildren();
	    List<Materiascursos> profMateriascursos = new ArrayList<Materiascursos>();
	    
	    for(Row row:components){
	      Checkbox ck = (Checkbox) row.getChildren().get(0);
	      
	      if(ck.isChecked()){
	    	  MateriascursosStatus mateCursosStatus = (MateriascursosStatus)row.getValue();
	    	  profMateriascursos.add(mateCursosStatus.getMateriascursos());
	      }
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
			   
			   Clients.showNotification("Registro(s) creado(s) correctamente");
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
    
	public ListModelList<MateriascursosStatus> getAllMateriascursos() {
		return allMateriascursosStatus;
	}
}
