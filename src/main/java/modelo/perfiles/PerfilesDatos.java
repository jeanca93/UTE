package modelo.perfiles;

import java.util.ArrayList;
import java.util.List;

import entidades.Perfilesusuario;
import entidades.Usuarios;
import entidadesDAO.PerfilesusuarioHomeExt;
import entidadesDAO.UsuariosHomeExt;

public class PerfilesDatos {
	private List<Perfilesusuario> allPerfiles = new ArrayList<Perfilesusuario>();

	public PerfilesDatos(){
		PerfilesusuarioHomeExt prfExt = new PerfilesusuarioHomeExt();		
		allPerfiles = prfExt.listPerfilesActivos(false);		
	}
	
	public List<Perfilesusuario> getAllPerfiles() {
		return allPerfiles;
	}
}
