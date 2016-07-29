package modelo.tipoaula;

import entidades.Tipoaula;

public class TipoAulaStatus {
	private Tipoaula tipoaula;
	private boolean seleccionado;
	private boolean editingStatus;
	
	public TipoAulaStatus(Tipoaula tipoaula, boolean seleccionado, boolean editingStatus) {
		super();
		this.tipoaula = tipoaula;
		this.seleccionado = seleccionado;
		this.editingStatus = editingStatus;
	}

	public Tipoaula getTipoaula() {
		return tipoaula;
	}

	public void setTipoaula(Tipoaula tipoaula) {
		this.tipoaula = tipoaula;
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
