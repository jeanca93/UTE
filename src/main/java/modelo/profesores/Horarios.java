package modelo.profesores;

import java.util.ArrayList;
import java.util.List;

import entidades.Dias;
import entidades.Horas;

public class Horarios {
	private Dias dias;
	private List<Horas> listHoras;
	
	public Horarios() {
		super();
		// TODO Auto-generated constructor stub
		
		this.listHoras = new ArrayList<Horas>();
	}
	
	public Horarios(Dias dias, List<Horas> listHoras) {
		super();
		this.dias = dias;
		this.listHoras = new ArrayList<Horas>(listHoras);
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
	
}
