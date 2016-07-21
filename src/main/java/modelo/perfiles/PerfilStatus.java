package modelo.perfiles;

import entidades.Perfilesusuario;

public class PerfilStatus {
	private Perfilesusuario perfil;
	private boolean seleccionado;
	private boolean editingStatus;
	
	public PerfilStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PerfilStatus(Perfilesusuario perfil, boolean seleccionado, boolean editingStatus) {
		super();
		this.perfil = perfil;
		this.seleccionado = seleccionado;
		this.editingStatus = editingStatus;
	}

	public Perfilesusuario getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfilesusuario perfil) {
		this.perfil = perfil;
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
