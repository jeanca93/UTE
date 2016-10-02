package modelo.mantenimiento.profesores;

import java.util.ArrayList;
import java.util.List;

import entidades.Dias;
import entidades.Horas;

public class ProfesoreshorariosStatus {
	private Dias dias;
	private List<Horas> listHoras;
	private boolean seleccionado;
	
	public ProfesoreshorariosStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProfesoreshorariosStatus(Dias dias, List<Horas> listHoras, boolean seleccionado) {
		super();
		this.dias = dias;
		this.listHoras = new ArrayList<Horas>(listHoras);
		this.seleccionado = seleccionado;
	}

	public Dias getDias() {
		return dias;
	}

	public void setDias(Dias dias) {
		this.dias = dias;
	}
	
	public List<Horas> getListHoras() {
		return listHoras;
	}

	public void setListHoras(List<Horas> listHoras) {
		this.listHoras = listHoras;
	}

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
}
