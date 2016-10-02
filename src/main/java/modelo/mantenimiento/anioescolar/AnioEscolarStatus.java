package modelo.mantenimiento.anioescolar;

import entidades.Schollaryear;

public class AnioEscolarStatus {
	
	private Schollaryear anioescolar;
	private boolean seleccionado;
	private boolean editingStatus;
	
	public AnioEscolarStatus(Schollaryear anioescolar, boolean seleccionado, boolean editingStatus) {
		super();
		this.anioescolar = anioescolar;
		this.seleccionado = seleccionado;
		this.editingStatus = editingStatus;
	}

	public Schollaryear getAnioescolar() {
		return anioescolar;
	}

	public void setAnioescolar(Schollaryear anioescolar) {
		this.anioescolar = anioescolar;
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
