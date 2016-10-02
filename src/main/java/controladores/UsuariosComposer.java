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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import entidades.Perfilesusuario;
import entidades.Usuarios;
import entidadesDAO.EstadosHome;
import entidadesDAO.PerfilesusuarioHome;
import entidadesDAO.UsuariosHomeExt;
import modelo.mantenimiento.usuarios.administracionUsuarios.UsuarioDatos;

public class UsuariosComposer extends GenericForwardComposer<Component>{
	private ListModelList<Perfilesusuario> allPerfiles = new ListModelList<Perfilesusuario>();
	private static final long serialVersionUID = 3L;
	//private Window modalDialog;
	private Combobox cmbPerfiles;
	private Textbox txtUsuario, txtClave, txtNombres, txtApellidos, txtCorreo;
	//private ListModelList<UsuarioStatus> allUsuariosStatus;
	//private Grid GridUsuarios;
	
	public UsuariosComposer() {
		// TODO Auto-generated constructor stub
		
		for(Perfilesusuario perfil:new UsuarioDatos().getAllPerfiles()){
			allPerfiles.add(perfil);
		}
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		//final Execution execution = Executions.getCurrent();
		//allUsuariosStatus = (ListModelList<UsuarioStatus>)execution.getArg().get("modelPrincipal");
		//GridUsuarios = (Grid)execution.getArg().get("gridPrincipal");
	}
	
	public void onClick$tbbGrabar() throws InterruptedException,
			ParserConfigurationException, SAXException, IOException,
			InstantiationException, IllegalAccessException {		
		if(cmbPerfiles.getSelectedItem() == null){
			
			Clients.showNotification("Debe escoger un perfil valido", Clients.NOTIFICATION_TYPE_ERROR, cmbPerfiles,  null, 0);
		}else{
			try{
				String usuario = txtUsuario.getValue().trim();
				
				if(new UsuariosHomeExt().existeUsuario(usuario) > 0L){
					Clients.showNotification("Usuario ya existente", Clients.NOTIFICATION_TYPE_ERROR, txtUsuario,  null, 0);
				}else{
					String clave = txtClave.getValue().trim();
					if(clave.matches("^(?=.*?\\p{Lu})(?=.*?[\\p{L}&&[^\\p{Lu}]])(?=.*?\\d).*$")){
						String nombres = txtNombres.getValue().toUpperCase();
						String apellidos = txtApellidos.getValue().toUpperCase();
						String correo = txtCorreo.getValue().trim();
						Integer perfil = cmbPerfiles.getSelectedItem().getValue();
						Session session = Sessions.getCurrent();
						
						new UsuariosHomeExt().crearNuevoUsuario(new Usuarios(new PerfilesusuarioHome().findById(perfil), usuario, null, nombres, apellidos, correo,  new EstadosHome().findById(1), new Date(), Integer.parseInt(session.getAttribute("idUsuario").toString())), clave);
								
						Messagebox.show("Registro creado correctamente", "Exito", Messagebox.OK,  Messagebox.EXCLAMATION, new EventListener<Event>() {
							
							public void onEvent(Event event) throws Exception {
								// TODO Auto-generated method stub
								Executions.sendRedirect("");
							}
						});
						
						/*
						modalDialog.detach();
						
						allUsuariosStatus = new ListModelList<UsuarioStatus>();
						genListModel(new UsuarioDatos().getAllUsuarios());
						GridUsuarios.setModel(allUsuariosStatus);
						
						Clients.showNotification("Creado correctamente");
						*/
					}else
						Clients.showNotification("Clave no cumple con las politicas de seguridad establecida", Clients.NOTIFICATION_TYPE_ERROR, txtClave, "end_center", 3000);
				}
			}catch(RuntimeException re){
				throw re;
			}
		}
	}
	
	
	public ListModel<Perfilesusuario> getAllPerfiles() {
		return allPerfiles;
	}
	
	/*
	private void genListModel(List<Usuarios> lsUsuarios){
    	for(Usuarios usr: lsUsuarios){
			allUsuariosStatus.add(new UsuarioStatus(usr, false, false));
		}
    }
    */
}
