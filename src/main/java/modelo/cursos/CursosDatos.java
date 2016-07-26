package modelo.cursos;

import java.util.ArrayList;
import java.util.List;

import entidades.Cursos;
import entidadesDAO.CursosHomeExt;

public class CursosDatos {

	private List<Cursos> allCursos = new ArrayList<Cursos>();
	
	public CursosDatos(){
		CursosHomeExt cursoExt = new CursosHomeExt();
		allCursos = cursoExt.listCursosActivos();	

	}
	
	public List<Cursos> getAllCurso() {
		return allCursos;
	}
}
