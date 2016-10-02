package controladores;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import entidadesDAO.UsuariosHomeExt;

public class ChangePasswordComposer extends GenericForwardComposer<Component>{
	private Textbox txtClaveActual, txtNuevaClave, txtNuevaClaveRepetida;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
	}
	
	public void onClick$tbbGrabar() throws InterruptedException,
			ParserConfigurationException, SAXException, IOException,
			InstantiationException, IllegalAccessException {
		try{
			String claveActual = txtClaveActual.getText().trim();
			String claveNueva = txtNuevaClave.getText().trim();
			String claveNuevaRepetida = txtNuevaClaveRepetida.getText().trim();			
			String user = session.getAttribute("user").toString();
			
			UsuariosHomeExt userExt = new UsuariosHomeExt();			
			Integer existeUsuario = userExt.validaUsuario(user, claveActual);
			
			if (existeUsuario > 0){
				if(claveNueva.matches("^(?=.*?\\p{Lu})(?=.*?[\\p{L}&&[^\\p{Lu}]])(?=.*?\\d).*$")){
					if (claveNueva.equals(claveNuevaRepetida)){
						if (userExt.registrarClave(user, claveNueva, null))
							Messagebox.show("Clave cambiada correctamente", "Exito", Messagebox.OK,  Messagebox.EXCLAMATION, new EventListener<Event>() {
								
								public void onEvent(Event event) throws Exception {
									// TODO Auto-generated method stub
									Executions.sendRedirect("");
								}
							});
						else
							Messagebox.show("Ocurrio un error, consulte al adminsitrador del sistema", "Error", Messagebox.OK, Messagebox.ERROR);
					}else
						Clients.showNotification("Clave nueva no coincide", "error", txtNuevaClaveRepetida, "end_center", 3000);
				}else
					Clients.showNotification("Clave no cumple con las politicas de seguridad establecida", Clients.NOTIFICATION_TYPE_ERROR, txtNuevaClave, "end_center", 3000);
			}else
				Clients.showNotification("Clave actual incorrecta", "error", txtClaveActual, "end_center", 3000);
		}catch(RuntimeException re){
			throw re;
		}
	}
}
