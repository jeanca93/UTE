
package edu.servicios.util;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import edu.gaha.servicios.modelo.ModeloAsignacionHorario;

@XmlRootElement
public class SolicitudWsRecepcion 
{

	private List<ModeloAsignacionHorario> lModeloHorario;
	private String lMensaje;
	
	public List<ModeloAsignacionHorario> getlModeloHorario() {
		return lModeloHorario;
	}
	
	public void setlModeloHorario(List<ModeloAsignacionHorario> lModeloHorario) {
		this.lModeloHorario = lModeloHorario;
	}
	
	public String getlMensaje() {
		return lMensaje;
	}
	
	public void setlMensaje(String lMensaje) {
		this.lMensaje = lMensaje;
	}
		
}
