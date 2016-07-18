package modelo.usuarios;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zul.ListModelList;

import entidades.Perfilesusuario;
import entidades.Usuarios;
import entidadesDAO.PerfilesusuarioHomeExt;
import entidadesDAO.UsuariosHomeExt;

public class UsuarioDatos {
	private List<Usuarios> allUsuarios = new ArrayList<Usuarios>();
	private List<Perfilesusuario> allPerfiles = new ArrayList<Perfilesusuario>();
	
	public UsuarioDatos(){
		UsuariosHomeExt userExt = new UsuariosHomeExt();
		allUsuarios = userExt.listUsuariosActivos();
		
		PerfilesusuarioHomeExt prfExt = new PerfilesusuarioHomeExt();		
		allPerfiles = prfExt.listPerfilesActivos();		
	}
	
	public List<Usuarios> getAllUsuarios() {
		return allUsuarios;
	}
	
	public List<Perfilesusuario> getAllPerfiles() {
		return allPerfiles;
	}
}
