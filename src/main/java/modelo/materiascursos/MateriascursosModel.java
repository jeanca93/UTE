package modelo.materiascursos;

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

import entidades.Cursos;
import entidades.Materias;
import entidades.Materiascursos;
import entidades.MateriascursosId;
import entidadesDAO.EstadosHome;
import entidadesDAO.MateriascursosHomeExt;
import modelo.materias.MateriaDatos;
import modelo.materias.MateriaStatus;

public class MateriascursosModel {
	private ListModelList<MateriaStatus> allMateriaStatus;
	private Cursos curso;
	@Wire
	private Grid GridMateriasCurso;
	@Wire
	private Window modalDialog;
	
	public MateriascursosModel() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
		
		Execution execution = Executions.getCurrent();
		curso = (Cursos)execution.getArg().get("curso");
		
		allMateriaStatus = new ListModelList<MateriaStatus>();
		allMateriaStatus = genListModel(new  MateriaDatos(true).getAllMaterias());
	}
	
	@Command
	public void selectedCheckBox(@BindingParam("CheckEvent") CheckEvent evt){
		List<Row> rows = GridMateriasCurso.getRows().getChildren();
	    
	    for(Row row:rows){
	      Checkbox ck = (Checkbox) row.getChildren().get(0);
	      MateriaStatus mateStatus = (MateriaStatus)row.getValue();
	      if(!mateStatus.isEditingStatus())
	    	  ck.setChecked(evt.isChecked());
	   }
	}
	
	@Command
	public void grabarMateriasCursos(){
		List<Row> components = GridMateriasCurso.getRows().getChildren();
	    List<Materias> materiascursos = new ArrayList<Materias>();
	    
	    for(Row row:components){
	      Checkbox ck = (Checkbox) row.getChildren().get(0);
	      
	      if(ck.isChecked()){
	    	  MateriaStatus mateStatus = (MateriaStatus)row.getValue();
	    	  materiascursos.add(mateStatus.getMaterias());
	      }
	   }
	    
	   if(materiascursos.size() == 0){
		   Clients.alert("Debe seleccionar una materia m&iacute;nimo para continuar", "Error", null);
	   }else{
		   Session session = Sessions.getCurrent();
		   Integer idUsuario = Integer.parseInt(session.getAttribute("idUsuario").toString());
		   
		   List<Materiascursos> listMateriasCur = new ArrayList<Materiascursos>();
		   for(Materias mate:materiascursos){
			   Materiascursos materiascur = new Materiascursos();
			   
			   MateriascursosId id = new MateriascursosId();
			   id.setCursos(curso);
			   id.setMaterias(mate);
			   
			   materiascur.setId(id);
			   materiascur.setEstados(new EstadosHome().findById(1));
			   materiascur.setFechaCreacion(new Date());
			   materiascur.setUsuarioCrea(idUsuario);
			   
			   listMateriasCur.add(materiascur);
		   }
		   
		   try{
			   new MateriascursosHomeExt().registrarMateriasCursos(listMateriasCur);
			   
			   modalDialog.detach();
			   
			   Clients.showNotification("Registro(s) creado(s) correctamente");
		   }catch(RuntimeException re){
			   throw re;
		   }
	   }
	}
	
	private ListModelList<MateriaStatus> genListModel(List<Materias> lsMaterias){
		List<Materiascursos> listMateriasCurso = new MateriascursosDatos(curso.getIdCurso()).getAllMateriascursos();
		
    	for(Materias mate: lsMaterias){
    		boolean seleccionado = false;
    		
    		for(Materiascursos mateCursos: listMateriasCurso){
    			if(mateCursos.getId().getMaterias().equals(mate)){
    				seleccionado = true;
    				break;
    			}
    		}
    		
    		allMateriaStatus.add(new MateriaStatus(mate, seleccionado, false));
		}
    	
    	return allMateriaStatus;
    }
    
	public ListModelList<MateriaStatus> getAllMaterias() {
		return allMateriaStatus;
	}

}
