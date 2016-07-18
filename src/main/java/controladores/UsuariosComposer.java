package controladores;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import entidades.Perfilesusuario;
import entidades.Usuarios;
import entidadesDAO.PerfilesusuarioHome;
import entidadesDAO.UsuariosHomeExt;
import modelo.usuarios.UsuarioDatos;
import modelo.usuarios.UsuarioStatus;

public class UsuariosComposer extends GenericForwardComposer<Component>{
	private static final long serialVersionUID = 3L;
	private ListModelList<Perfilesusuario> allPerfiles = new ListModelList<Perfilesusuario>();
	private Window modalDialog;
	private Combobox cmbPerfiles;
	private Textbox txtUsuario, txtClave, txtNombres, txtApellidos, txtCorreo;
	private ListModelList<UsuarioStatus> allUsuariosStatus;
	private Grid GridUsuarios;
	
	public UsuariosComposer() {
		// TODO Auto-generated constructor stub
		
		for(Perfilesusuario perfil:new UsuarioDatos().getAllPerfiles()){
			allPerfiles.add(perfil);
		}
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		final Execution execution = Executions.getCurrent();
		allUsuariosStatus = (ListModelList<UsuarioStatus>)execution.getArg().get("modelPrincipal");
		GridUsuarios = (Grid)execution.getArg().get("gridPrincipal");
		
	}
	
	public void onClick$tbbGrabar() throws InterruptedException,
			ParserConfigurationException, SAXException, IOException,
			InstantiationException, IllegalAccessException {		
		if(cmbPerfiles.getSelectedItem() == null){
			
			Clients.showNotification("Debe escoger un perfil valido", Clients.NOTIFICATION_TYPE_ERROR, cmbPerfiles,  null, 0);
		}else{
			try{
				String usuario = txtUsuario.getValue().trim();
				String clave = txtClave.getValue().trim();
				String nombres = txtNombres.getValue();
				String apellidos = txtApellidos.getValue();
				String correo = txtCorreo.getValue().trim();
				Integer perfil = cmbPerfiles.getSelectedItem().getValue();
				Session session = Sessions.getCurrent();
				
				new UsuariosHomeExt().crearNuevoUsuario(new Usuarios(new PerfilesusuarioHome().findById(perfil), usuario, null, nombres, apellidos, correo, 'P', new Date(), Integer.parseInt(session.getAttribute("idUsuario").toString())), clave);
				
				modalDialog.detach();
				
				allUsuariosStatus = new ListModelList<UsuarioStatus>();
				genListModel(new UsuarioDatos().getAllUsuarios());
				GridUsuarios.setModel(allUsuariosStatus);
				
				Clients.showNotification("Creado correctamente");
			}catch(RuntimeException re){
				throw re;
			}
		}
	}
	
	public ListModel<Perfilesusuario> getAllPerfiles() {
		return allPerfiles;
	}
	
	private void genListModel(List<Usuarios> lsUsuarios){
    	for(Usuarios usr: lsUsuarios){
			allUsuariosStatus.add(new UsuarioStatus(usr, false, false));
		}
    }
}
