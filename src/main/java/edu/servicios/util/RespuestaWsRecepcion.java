package edu.servicios.util;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RespuestaWsRecepcion {

	private String lMensaje;
	private String lCodigo;
	public String getlMensaje() {
		return lMensaje;
	}
	public void setlMensaje(String lMensaje) {
		this.lMensaje = lMensaje;
	}
	public String getlCodigo() {
		return lCodigo;
	}
	public void setlCodigo(String lCodigo) {
		this.lCodigo = lCodigo;
	}
	
	
	
	
	
}
