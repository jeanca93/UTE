package modelo.usuarios;

import entidades.Usuarios;

public class UsuarioStatus {
	private Usuarios usuario;
	private boolean seleccionado;
	private boolean editingStatus;
	
	public UsuarioStatus(){
		
	}
	
	public UsuarioStatus(Usuarios usuario, boolean seleccionado, boolean editingStatus) {
		super();
		this.usuario = usuario;
		this.seleccionado = seleccionado;
		this.editingStatus = editingStatus;
	}

	public Usuarios getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}
	
	public boolean isSeleccionado() {
		return seleccionado;
	}
	
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
	
	public boolean isEditingStatus() {
		return editingStatus;
	}
	
	public void setEditingStatus(boolean editingStatus) {
		this.editingStatus = editingStatus;
	}
}
