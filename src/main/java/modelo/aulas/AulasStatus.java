package modelo.aulas;

import entidades.Aulas;

public class AulasStatus {
	
	private Aulas aula;
	private boolean seleccionado;
	private boolean editingStatus;
	
	public AulasStatus(){
		
	}

	public AulasStatus(Aulas aula, boolean seleccionado, boolean editingStatus) {
		super();
		this.aula = aula;
		this.seleccionado = seleccionado;
		this.editingStatus = editingStatus;
	}

	public Aulas getAula() {
		return aula;
	}

	public void setAula(Aulas aula) {
		this.aula = aula;
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
