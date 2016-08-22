package modelo.usuarios;

import java.util.ArrayList;
import java.util.List;

import entidades.Perfilesusuario;
import entidades.Usuarios;
import entidadesDAO.UsuariosHomeExt;
import modelo.perfiles.PerfilesDatos;

public class UsuarioDatos {
	private List<Usuarios> allUsuarios = new ArrayList<Usuarios>();
	private List<Perfilesusuario> allPerfiles = new ArrayList<Perfilesusuario>();
	
	public UsuarioDatos(){
		UsuariosHomeExt userExt = new UsuariosHomeExt();
		allUsuarios = userExt.listUsuariosActivos();
				
		allPerfiles = new PerfilesDatos(true).getAllPerfiles();		
	}
	
	public List<Usuarios> getAllUsuarios() {
		return allUsuarios;
	}
	
	public List<Perfilesusuario> getAllPerfiles() {
		return allPerfiles;
	}
}
