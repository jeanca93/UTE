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
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;

import entidades.Cursos;
import entidadesDAO.CursosHome;
import entidadesDAO.EstadosHome;

public class CursosComposer extends GenericForwardComposer<Component>{
	private static final long serialVersionUID = 7L;
	private Textbox txtIdCurso, txtCurso;
	private Spinner txtParalelo;
	
	public CursosComposer() {
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		//final Execution execution = Executions.getCurrent();
		//allAulasStatus = (ListModelList<AulasStatus>)execution.getArg().get("modelPrincipal");
		//GridAulas = (Grid)execution.getArg().get("gridPrincipal");
		
	}
	
	public void onClick$tbbGrabar() throws InterruptedException,
			ParserConfigurationException, SAXException, IOException,
			InstantiationException, IllegalAccessException {
		try{
			String idCurso = txtIdCurso.getValue().trim();
			String curso = txtCurso.getValue().toUpperCase();
			
			if (idCurso.length() > 3){
				Integer paralelos = txtParalelo.getValue();
				Session session = Sessions.getCurrent();
					
				new CursosHome().save(new Cursos(idCurso,curso,paralelos, new EstadosHome().findById(1), new Date(), Integer.parseInt(session.getAttribute("idUsuario").toString())));
					
				Messagebox.show("Registro creado correctamente", "Exito", Messagebox.OK,  Messagebox.EXCLAMATION, new EventListener<Event>() {
					
					public void onEvent(Event event) throws Exception {
						// TODO Auto-generated method stub
						Executions.sendRedirect("");
					}
				});
			}else
				Clients.showNotification("La longitud mínima del texto de ID del curso es 3", Clients.NOTIFICATION_TYPE_ERROR, txtIdCurso, "end_center", 0);
		}catch(RuntimeException re){
			throw re;
		}
	}
	
	/*
	private void genListModel(List<Aulas> lsAulas){
    	for(Aulas aul: lsAulas){
			allAulasStatus.add(new AulasStatus(aul, false, false));
		}
    }
	*/

}
