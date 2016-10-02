package controladores;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;

import entidades.Estados;
import entidades.Schollaryear;
import entidadesDAO.EstadosHome;
import entidadesDAO.SchollaryearHome;
import entidadesDAO.SchollaryearHomeExt;

public class AnioEscolarComposer extends GenericForwardComposer<Component>{
	private static final long serialVersionUID = 8L;
	private Textbox txtAnioEscolar;
	private Datebox txtFechaInicio, txtFechaFin;
	private Timebox txtDuracionClases;
	private Spinner txtMaxhoras;
	
	public AnioEscolarComposer() {
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
        txtFechaInicio.setValue(new Date());
        txtFechaFin.setValue(new Date());
        
        Calendar hora = Calendar.getInstance();
		
		hora.set(Calendar.HOUR_OF_DAY,0);
		hora.set(Calendar.MINUTE,45);
		hora.set(Calendar.SECOND,0);
		hora.set(Calendar.MILLISECOND,0);
		
		txtDuracionClases.setValue(hora.getTime());
	}
	
	public void onClick$tbbGrabar() throws InterruptedException,
			ParserConfigurationException, SAXException, IOException,
			InstantiationException, IllegalAccessException {		
				
			try{
				
				String anioescolar = txtAnioEscolar.getValue().trim().toUpperCase();
				Date fechainicio = txtFechaInicio.getValue();
				Date fechafin = txtFechaFin.getValue();
				Date duracionclases = (Date)txtDuracionClases.getRawValue();
				Integer maxhorassemanaprofesor = txtMaxhoras.getValue();
				
				Calendar validaMinutos = Calendar.getInstance();
				validaMinutos.setTime(duracionclases);
				int totalMinutosClases = ((validaMinutos.get(Calendar.HOUR_OF_DAY)*60) + validaMinutos.get(Calendar.MINUTE));
				
				if (totalMinutosClases % 15 == 0){
					SchollaryearHomeExt schoolExt = new SchollaryearHomeExt();
					
					if (schoolExt.validarRangoFechas(fechainicio, fechafin).equals("")){
						Estados estado = new EstadosHome().findById(2);
						
						if( schoolExt.validaActiveyear() == 0)
							estado = new EstadosHome().findById(1);
						
						Session session = Sessions.getCurrent();
						
						new SchollaryearHome().save(new Schollaryear(anioescolar, fechainicio, fechafin,  duracionclases, maxhorassemanaprofesor, estado, new Date(), Integer.parseInt(session.getAttribute("idUsuario").toString())));
				
						Messagebox.show("Registro creado correctamente", "Exito", Messagebox.OK,  Messagebox.EXCLAMATION, new EventListener<Event>() {
							
							public void onEvent(Event event) throws Exception {
								// TODO Auto-generated method stub
								Executions.sendRedirect("");
							}
						});
					}else
						Messagebox.show("Rango de fechas de año lectivo inválido, no se debe cruzar con alguno sido previamente ingresado", "Error",  Messagebox.OK,  Messagebox.ERROR);
				}else
					Clients.showNotification("Duraci&oacute;n de clases no permitida", Clients.NOTIFICATION_TYPE_ERROR, txtDuracionClases,  null, 0);
			}catch(RuntimeException re){
				throw re;
			}
		
	}
	
}