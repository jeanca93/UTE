package modelo.materias;

import entidades.Materias;

public class MateriaStatus {
	
	private Materias materia;
	private boolean seleccionado;
	private boolean editingStatus;
	
	public MateriaStatus(){
		
	}

	public MateriaStatus(Materias materia, boolean seleccionado, boolean editingStatus) {
		super();
		this.materia = materia;
		this.seleccionado = seleccionado;
		this.editingStatus = editingStatus;
	}

	public Materias getMateria() {
		return materia;
	}

	public void setMateria(Materias materia) {
		this.materia = materia;
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
