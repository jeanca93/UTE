package modelo.mantenimiento.materiascursos;

import java.util.ArrayList;
import java.util.List;

import entidades.Materiascursos;
import entidadesDAO.MateriascursosHomeExt;

public class MateriascursosDatos {
private List<Materiascursos> allMateriascursos = new ArrayList<Materiascursos>();
	
	public MateriascursosDatos(String idCurso){
		MateriascursosHomeExt matcursosExt = new MateriascursosHomeExt();
		allMateriascursos = matcursosExt.listMateriascursos(idCurso);
	}
	
	public List<Materiascursos> getAllMateriascursos() {
		return allMateriascursos;
	}
}
