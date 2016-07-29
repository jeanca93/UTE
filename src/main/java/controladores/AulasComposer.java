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
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import entidades.Aulas;
import entidadesDAO.AulasHome;


public class AulasComposer extends GenericForwardComposer<Component>{
	private static final long serialVersionUID = 9L;
	private Textbox txtIdAula, txtAula, txtComentarios;
	private Intbox txtAsientos;
	
	public AulasComposer() {
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
			String idAula = txtIdAula.getValue().trim();
			String aula = txtAula.getValue().trim();
			Integer asientos = txtAsientos.getValue();
			String comentarios = txtComentarios.getValue().trim();
			Session session = Sessions.getCurrent();
				
			new AulasHome().save(new Aulas(idAula,aula,asientos,comentarios, 'A', new Date(), Integer.parseInt(session.getAttribute("idUsuario").toString())));
					
			Messagebox.show("Registro creado correctamente", "Exito", Messagebox.OK,  Messagebox.EXCLAMATION, new EventListener<Event>() {
					
				public void onEvent(Event event) throws Exception {
					// TODO Auto-generated method stub
					Executions.sendRedirect("");
				}
			});

			/*							
			modalDialog.detach();
			
			allAulasStatus = new ListModelList<AulasStatus>();
			genListModel(new AulasDatos().getAllAula());
			ridAulas.setModel(allAulasStatus);
			
			Clients.showNotification("Creado correctamente");
			*/	
		}catch(RuntimeException re){
			throw re;
		}
	}
}
