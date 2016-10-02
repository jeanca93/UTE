package edu.gaha.servicios.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ModeloAsignacionHorario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String lProfesor;
	private String lMateria;
	private String lAula;
	private String lTipoAula;
	private String lCurso;
	private String lParalelo;
	private List<DiaHora> listaDiaHora;

	public ModeloAsignacionHorario(String lProfesor, String lMateria, String lAula, String lTipoAula, String lCurso) {
		super();
		this.lProfesor = lProfesor;
		this.lMateria = lMateria;
		this.lAula = lAula;
		this.lTipoAula = lTipoAula;
		this.lCurso = lCurso;
	}

	public ModeloAsignacionHorario() {
	}

	public String getlProfesor() {
		return lProfesor;
	}

	public void setlProfesor(String lProfesor) {
		this.lProfesor = lProfesor;
	}

	public String getlMateria() {
		return lMateria;
	}

	public void setlMateria(String lMateria) {
		this.lMateria = lMateria;
	}

	public String getlAula() {
		return lAula;
	}

	public void setlAula(String lAula) {
		this.lAula = lAula;
	}

	public String getlTipoAula() {
		return lTipoAula;
	}

	public void setlTipoAula(String lTipoAula) {
		this.lTipoAula = lTipoAula;
	}

	public String getlCurso() {
		return lCurso;
	}

	public void setlCurso(String lCurso) {
		this.lCurso = lCurso;
	}

	public void aniadirDiaHora(DiaHora lDiaHora) {
		if (listaDiaHora == null) {
			listaDiaHora = new ArrayList<>();
		}
		listaDiaHora.add(lDiaHora);
	}

	public List<DiaHora> getListaDiaHora() {
		return listaDiaHora;
	}

	public void setListaDiaHora(List<DiaHora> listaDiaHora) {
		this.listaDiaHora = listaDiaHora;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return lProfesor + " - " + lMateria + " - " + lCurso + " - " + lParalelo + " - " + lAula;
	}
	public String getlParalelo() {
		return lParalelo;
	}
	public void setlParalelo(String lParalelo) {
		this.lParalelo = lParalelo;
	}

}
