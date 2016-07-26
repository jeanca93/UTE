package modelo.cursos;

import entidades.Cursos;

public class CursosStatus {

	private Cursos curso;
	private boolean seleccionado;
	private boolean editingStatus;
	
	public CursosStatus(){
		
	}

	public CursosStatus(Cursos curso, boolean seleccionado, boolean editingStatus) {
		super();
		this.curso = curso;
		this.seleccionado = seleccionado;
		this.editingStatus = editingStatus;
	}

	public Cursos getCurso() {
		return curso;
	}

	public void setCurso(Cursos curso) {
		this.curso = curso;
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
