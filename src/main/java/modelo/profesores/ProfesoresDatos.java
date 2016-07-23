package modelo.profesores;

import java.util.ArrayList;
import java.util.List;

import entidades.Perfilesusuario;
import entidades.Profesores;
import entidades.Usuarios;
import entidadesDAO.PerfilesusuarioHomeExt;
import entidadesDAO.ProfesoresHomeExt;
import entidadesDAO.UsuariosHomeExt;

public class ProfesoresDatos {
	private List<Profesores> allProfesores = new ArrayList<Profesores>();
	
	public ProfesoresDatos(){
		ProfesoresHomeExt profExt = new ProfesoresHomeExt();
		allProfesores = profExt.listProfesoresActivos();
	}
	
	public List<Profesores> getAllProfesores() {
		return allProfesores;
	}
}
