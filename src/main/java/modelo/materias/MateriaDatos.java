package modelo.materias;

import java.util.ArrayList;
import java.util.List;

import entidades.Materias;
import entidadesDAO.MateriasHomeExt;;

public class MateriaDatos {
	private List<Materias> allMaterias = new ArrayList<Materias>();
	
	public MateriaDatos(boolean activos){
		MateriasHomeExt materiaExt = new MateriasHomeExt();
		allMaterias = materiaExt.listMateriasActivos(activos);
	}
	
	public List<Materias> getAllMaterias() {
		return allMaterias;
	}	
}
