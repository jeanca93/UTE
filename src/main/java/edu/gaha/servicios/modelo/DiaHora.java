package edu.gaha.servicios.modelo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DiaHora implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String lIdDia;
	private String lIdHora;
	
	public DiaHora()
	{
		
	}

	public String getlIdDia() {
		return lIdDia;
	}

	public void setlIdDia(String lIdDia) {
		this.lIdDia = lIdDia;
	}

	public String getlIdHora() {
		return lIdHora;
	}

	public void setlIdHora(String lIdHora) {
		this.lIdHora = lIdHora;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return lIdDia + " - " + lIdHora;
	}

}


