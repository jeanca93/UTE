package modelo.materias;

import entidades.Materias;

public class MateriaStatus {
	
	private Materias materias;
	private boolean seleccionado;
	private boolean editingStatus;
	
	public MateriaStatus(){
		
	}

	public MateriaStatus(Materias materias, boolean seleccionado, boolean editingStatus) {
		super();
		this.materias = materias;
		this.seleccionado = seleccionado;
		this.editingStatus = editingStatus;
	}

	public Materias getMaterias() {
		return materias;
	}

	public void setMaterias(Materias materias) {
		this.materias = materias;
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
