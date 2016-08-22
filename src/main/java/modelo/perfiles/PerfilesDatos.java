package modelo.perfiles;

import java.util.ArrayList;
import java.util.List;

import entidades.Perfilesusuario;
import entidadesDAO.PerfilesusuarioHomeExt;

public class PerfilesDatos {
	private List<Perfilesusuario> allPerfiles = new ArrayList<Perfilesusuario>();

	public PerfilesDatos(boolean activo){
		PerfilesusuarioHomeExt prfExt = new PerfilesusuarioHomeExt();		
		allPerfiles = prfExt.listPerfilesActivos(activo);		
	}
	
	public List<Perfilesusuario> getAllPerfiles() {
		return allPerfiles;
	}
}
