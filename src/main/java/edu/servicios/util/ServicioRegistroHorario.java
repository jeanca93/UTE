package edu.servicios.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import org.zkoss.zk.ui.Sessions;

import edu.gaha.servicios.modelo.ModeloAsignacionHorario;
import entidades.Generacionhorarios;
import entidadesDAO.GeneracionhorariosHome;
import entidadesDAO.GeneracionhorariosHomeExt;
import entidadesDAO.HorariosdetalleHomeExt;

@Stateless
public class ServicioRegistroHorario 
{
	
	public void registrarHorario(List<ModeloAsignacionHorario> lHorario, String lMensaje) throws Throwable{
		Integer idUsuario = 0;
		String usuario = "", correo = "", asunto = "", cuerpo = "";
		GeneracionhorariosHomeExt genHorariosExt = new GeneracionhorariosHomeExt();
		
		try{
			List<Generacionhorarios> listGenHorarios = genHorariosExt.listGeneracionhorarios();
			
			if (listGenHorarios.size() == 1){
				Generacionhorarios datos = listGenHorarios.get(0);
				
				idUsuario = datos.getUsuarioGenera();
				usuario = datos.getUsuario();
				correo = datos.getCorreo();
				
				asunto = "Generación de Horarios finalizada" + (lMensaje == null || lMensaje.equals("")?"":" con errores");
				cuerpo = "<p style='color:#00008B;'>Estimado " + usuario + ",</p><br>";
				
				lHorario = lHorario==null?new ArrayList<ModeloAsignacionHorario>():lHorario;
				
				if(lHorario.size() > 0){
					HorariosdetalleHomeExt horarioExt = new HorariosdetalleHomeExt();
					
					if(horarioExt.registrarHorarios(lHorario, idUsuario))
						cuerpo += "<p style='color:#00008B;text-align:justify;'>El proceso de generación automática de horarios ha finalizado satisfactoriamente, favor dirigirse al sistema para visualizar la información correspondiente.</p>";
				}else
					cuerpo += (lMensaje  == null || lMensaje.equals("")?
								"<p style='color:#00008B;text-align:justify;'>No se encontró ningun resultado óptimo, favor revisar la disponibilidad del profesor, aulas de clases, horas a la semana de las materias por curso y los profesores.</p>"
								:lMensaje);
			}
		}catch(Throwable ex){
			cuerpo += "<p style='color:#00008B;text-align:justify;'>Imposible realizar el ingreso de la información de los resultados, favor contactarse con su administrador del sistema.</p>";
		}finally{
			if(!cuerpo.equals("")){
				genHorariosExt.eliminarRegistro(idUsuario);
				
				List<String> listaDestinatarios = new ArrayList<String>();
				listaDestinatarios.add((correo.equals("")?"gaha.uefgs.duran@gmail.com":correo));
				
				Mail.generateAndSendEmail(asunto, cuerpo, "gaha.uefgs.duran@gmail.com", listaDestinatarios.toArray(new String[0]));
			}
		}
	}
}
