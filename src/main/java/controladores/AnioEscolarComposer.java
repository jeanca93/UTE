package controladores;

import java.io.IOException;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import entidades.Schollaryear;
import entidadesDAO.SchollaryearHome;

public class AnioEscolarComposer extends GenericForwardComposer<Component>{
	private static final long serialVersionUID = 4L;
	private Textbox txtAnioEscolar;
	private Datebox txtFechaInicio, txtFechaFin;
	

	public AnioEscolarComposer() {
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
        txtFechaInicio.setValue(new Date());
        txtFechaFin.setValue(new Date());
	}
	
	public void onClick$tbbGrabar() throws InterruptedException,
			ParserConfigurationException, SAXException, IOException,
			InstantiationException, IllegalAccessException {		
		
			try{
				
				String anioescolar = txtAnioEscolar.getValue().trim();
				Date fechainicio = txtFechaInicio.getValue();
				Date fechafin = txtFechaFin.getValue();
				
				Session session = Sessions.getCurrent();
				
				new SchollaryearHome().save(new Schollaryear(anioescolar, fechainicio, fechafin, 'A', new Date(), Integer.parseInt(session.getAttribute("idUsuario").toString())));
		
				Messagebox.show("Creado correctamente", "Exito", Messagebox.OK,  Messagebox.EXCLAMATION, new EventListener<Event>() {
					
					public void onEvent(Event event) throws Exception {
						// TODO Auto-generated method stub
						Executions.sendRedirect("");
					}
				});

				
			}catch(RuntimeException re){
				throw re;
			}
		
	}
	
}
