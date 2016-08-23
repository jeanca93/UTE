package modelo.materiascursos;

import java.util.Date;
import entidades.Materias;

public class MateriasHorasSemanaStatus {
	
	private Materias materias;
	private int horassemana;
	private boolean seleccionado;
	private boolean editingStatus;
	
	public MateriasHorasSemanaStatus(){
		
	}
	
	public MateriasHorasSemanaStatus(Materias materias, int horassemana, boolean seleccionado, boolean editingStatus) {
		super();
		this.materias = materias;
		this.horassemana = horassemana;
		this.seleccionado = seleccionado;
		this.editingStatus = editingStatus;
	}
	
	public Materias getMaterias() {
		return materias;
	}
	
	public void setMaterias(Materias materias) {
		this.materias = materias;
	}
	
	public int getHorassemana() {
		return horassemana;
	}
	
	public void setHorassemana(int horassemana) {
		this.horassemana = horassemana;
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