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

import entidades.Perfilesusuario;
import entidadesDAO.PerfilesusuarioHome;

public class PerfilesComposer extends GenericForwardComposer<Component>{
	private static final long serialVersionUID = 4L;
	//private Window modalDialog;
	private Textbox txtPerfil, txtComentarios;
	//private ListModelList<PerfilStatus> allPerfilesStatus;
	//private Grid GridPerfiles;
	
	public PerfilesComposer() {
		// TODO Auto-generated constructor stub
		
		super();
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		//final Execution execution = Executions.getCurrent();
		//allPerfilesStatus = (ListModelList<PerfilStatus>)execution.getArg().get("modelPrincipal");
		//GridPerfiles = (Grid)execution.getArg().get("gridPrincipal");	
	}
	
	public void onClick$tbbGrabar() throws InterruptedException,
			ParserConfigurationException, SAXException, IOException,
			InstantiationException, IllegalAccessException {		
		try{
			String perfil = txtPerfil.getValue();
			String comentarios = txtComentarios.getValue();
			Session session = Sessions.getCurrent();
			
			new PerfilesusuarioHome().save(new Perfilesusuario(perfil, comentarios, 'A', new Date(),Integer.parseInt(session.getAttribute("idUsuario").toString())));
			
			Messagebox.show("Registro creado correctamente", "Exito", Messagebox.OK,  Messagebox.EXCLAMATION, new EventListener<Event>() {
				
				public void onEvent(Event event) throws Exception {
					// TODO Auto-generated method stub
					Executions.sendRedirect("");
				}
			});
			
			/*
			modalDialog.detach();
			
			allPerfilesStatus = new ListModelList<PerfilStatus>();
			genListModel(new PerfilesDatos().getAllPerfiles());
			GridPerfiles.setModel(allPerfilesStatus);
			
			Clients.showNotification("Creado correctamente");
			*/
		}catch(RuntimeException re){
			throw re;
		}
	}
	
	/*
	private void genListModel(List<Perfilesusuario> lsPerfiles){
    	for(Perfilesusuario perfil: lsPerfiles){
			allPerfilesStatus.add(new PerfilStatus(perfil, false, false));
			
		}
    }
    */
}
