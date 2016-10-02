package edu.servicios.util;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public class ServicioWebIngresoHorario {

	@EJB
	private ServicioRegistroHorario lServicio;
	
	@WebMethod(operationName="registrarGeneracionHorario")
	public 
	@WebResult(name="RESPUESTA")
	RespuestaWsRecepcion registrarGeneracionHorario(
			@WebParam(name="lRecepcion")
			SolicitudWsRecepcion lRecepcion)
	{
		RespuestaWsRecepcion lRespuesta = new RespuestaWsRecepcion();
		try  
		{
			lServicio.registrarHorario(lRecepcion.getlModeloHorario(), lRecepcion.getlMensaje());
			lRespuesta.setlMensaje("DATOS REGISTRADOS CORRECTAMENTE");
			lRespuesta.setlCodigo("100");
		} catch (Throwable lError) {
			lError.printStackTrace();
			lRespuesta.setlMensaje("ERROR AL REGISTRAR DATOS " + lError.getMessage());
			lRespuesta.setlCodigo("500");
		}
		
		return lRespuesta;
		
	}
	
}
