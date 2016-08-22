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
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import entidades.Tipoaula;
import entidadesDAO.EstadosHome;
import entidadesDAO.TipoAulaHome;

public class TipoAulaComposer extends GenericForwardComposer<Component>{
	private static final long serialVersionUID = 10L;
	private Textbox txtTipoAula;
	
	public TipoAulaComposer() {
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
	}
	
	public void onClick$tbbGrabar() throws InterruptedException,
	ParserConfigurationException, SAXException, IOException,
	InstantiationException, IllegalAccessException {		

	try{
		String tipoaula = txtTipoAula.getValue();
		
		Session session = Sessions.getCurrent();
		
		new TipoAulaHome().save(new Tipoaula(tipoaula, new EstadosHome().findById(1), new Date(), Integer.parseInt(session.getAttribute("idUsuario").toString())));

		Messagebox.show("Registro creado correctamente", "Exito", Messagebox.OK,  Messagebox.EXCLAMATION, new EventListener<Event>() {
			
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
