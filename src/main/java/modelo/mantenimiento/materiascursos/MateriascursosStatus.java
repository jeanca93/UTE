package modelo.mantenimiento.materiascursos;

import entidades.Materiascursos;

public class MateriascursosStatus {
	private Materiascursos materiascursos;
	private boolean seleccionado;
	private boolean editingStatus;
	
	public MateriascursosStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MateriascursosStatus(Materiascursos materiascursos, boolean seleccionado, boolean editingStatus) {
		super();
		this.materiascursos = materiascursos;
		this.seleccionado = seleccionado;
		this.editingStatus = editingStatus;
	}
	
	public Materiascursos getMateriascursos() {
		return materiascursos;
	}
	
	public void setMateriascursos(Materiascursos materiascursos) {
		this.materiascursos = materiascursos;
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
