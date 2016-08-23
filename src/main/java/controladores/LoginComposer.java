/*
   Copyright 2016 Jean Carlos Trivino Calderon

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/


package controladores;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.*;

import entidadesDAO.UsuariosHomeExt;

public class LoginComposer extends GenericForwardComposer<Component> {

	private static final long serialVersionUID = 1L;
	private Textbox txtUsuario;
	private Textbox txtClave;
	public static String grupo = "";

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

	}

	public void onClick$btnEntrar() throws InterruptedException,
			ParserConfigurationException, SAXException, IOException,
			InstantiationException, IllegalAccessException {
		entrar();
	}

	public void onOK(Event evt) throws InterruptedException,
			ParserConfigurationException, SAXException, IOException,
			InstantiationException, IllegalAccessException {
		entrar();
		
	}

	public void entrar() throws InterruptedException,
			ParserConfigurationException, SAXException, IOException {
		String user = txtUsuario.getText().trim();
		String password = txtClave.getText().trim();
		
		if(user.length() == 0)
			Clients.showNotification("Debe ingresar un usuario v&aacute;lido","info",txtUsuario,"end_center",3000);		
		else if(password.length() == 0)
			Clients.showNotification("Debe ingresar una contrase�a v&aacute;lida","info",txtClave,"end_center",3000);
		else{
			UsuariosHomeExt userExt = new UsuariosHomeExt();
			
			Integer existeUsuario = userExt.validaUsuario(user, password);
			
			if (existeUsuario > 0){
				Session session = Sessions.getCurrent();
				session.setAttribute("idUsuario", existeUsuario);
				session.setAttribute("usuario", user);
				
				Executions.sendRedirect("/principal.zul");
			}else
				Clients.showNotification("Usuario o contrase�a inv&aacute;lido",Clients.NOTIFICATION_TYPE_ERROR, null, null, 0);
		}
	}

}