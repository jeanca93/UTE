package controladores;

import java.io.IOException;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import entidades.Profesores;
import entidadesDAO.EstadosHome;
import entidadesDAO.ProfesoresHome;

public class ProfesoresComposer extends GenericForwardComposer<Component>{
	private static final long serialVersionUID = 5L;
	//private Window modalDialog;
	private Textbox txtProfesor, txtTitulo;
	//private ListModelList<ProfesorStatus> allProfesoresStatus;
	//private Grid GridProfesores;
	
	public ProfesoresComposer() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		//final Execution execution = Executions.getCurrent();
		//allProfesoresStatus = (ListModelList<ProfesorStatus>)execution.getArg().get("modelPrincipal");
		//GridProfesores = (Grid)execution.getArg().get("gridPrincipal");
	}
	
	public void onClick$tbbGrabar() throws InterruptedException,
			ParserConfigurationException, SAXException, IOException,
			InstantiationException, IllegalAccessException {		
		try{
			String profesor = txtProfesor.getValue().toUpperCase();
			String titulo = txtTitulo.getValue();
			
			new ProfesoresHome().save(new Profesores(profesor, titulo,  new EstadosHome().findById(1), new Date(),Integer.parseInt(session.getAttribute("idUsuario").toString())));
			
			Messagebox.show("Registro creado correctamente", "Exito", Messagebox.OK,  Messagebox.EXCLAMATION, new EventListener<Event>() {
				
				public void onEvent(Event event) throws Exception {
					// TODO Auto-generated method stub
					Executions.sendRedirect("");
				}
			});
			
			/*
			modalDialog.detach();
			
			allProfesoresStatus = new ListModelList<ProfesorStatus>();
			genListModel(new ProfesoresDatos().getAllProfesores());
			GridProfesores.setModel(allProfesoresStatus);
			
			Clients.showNotification("Creado correctamente");
			*/
		}catch(RuntimeException re){
			throw re;
		}
	}
	
	/*
	private void genListModel(List<Profesores> lsProfesores){
    	for(Profesores profesor: lsProfesores){
			allProfesoresStatus.add(new ProfesorStatus(profesor, false, false));
		}
    }
    */
}
