package modelo.mantenimiento.profesores;

import entidades.Profesores;

public class ProfesorStatus {
	private Profesores profesor;
	private boolean seleccionado;
	private boolean editingStatus;
	
	public ProfesorStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProfesorStatus(Profesores profesor, boolean seleccionado, boolean editingStatus) {
		super();
		this.profesor = profesor;
		this.seleccionado = seleccionado;
		this.editingStatus = editingStatus;
	}

	public Profesores getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesores profesor) {
		this.profesor = profesor;
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
