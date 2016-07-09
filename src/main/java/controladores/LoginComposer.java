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
import java.math.BigInteger;

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

import controladores.modelo.Musuario;
import entidadesDAO.UsuariosHomeExt;

/**
 *
 * @author Ban
 */

public class LoginComposer extends GenericForwardComposer {

	private static final long serialVersionUID = -3215403323220236995L;
	private Textbox txtUsuario;
	private Textbox txtClave;
	private String validar = "", texto_desencriptado = "", validar_fecha = "";
	private String[] app = null;
	private Button btnEntrar;
	public static String grupo = "";
	private String app_name = "1";
	private BigInteger e, n;
	private static Window win;
	Musuario musuario = new Musuario();

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		init(minClave());

	}

	public String minClave() {
		String valor = "8";
		return valor;
	}

	private void init(String minClave) throws InstantiationException,
			IllegalAccessException {
		txtUsuario.setFocus(true);
		txtUsuario.setConstraint("no empty: "
				+ "Debe ingresar el nombre de usuario");

		txtClave
				.setConstraint("no empty, /^.*(?=.{" + minClave + ",64}).*$/,no future: " //$NON-NLS-1$
						+ "La clave no es valida." + "\n\n"
						+ "Una clave valida debe:" + "\n\n- "
						+ "Tener al menos " + minClave
						+ " caracteres de longitud.");

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
			Clients.showNotification("Debe ingresar un usuario válido","info",txtUsuario,"end_center",3000);		
		else if(password.length() == 0)
			Clients.showNotification("Debe ingresar una contraseña válida","info",txtClave,"end_center",3000);
		else{
			UsuariosHomeExt userExt = new UsuariosHomeExt();
			
			boolean existeUsuario = userExt.validaUsuario(user, password);
			
			if (existeUsuario){
				Session session = Sessions.getCurrent();
				session.setAttribute("usuario", user);
				
				Executions.sendRedirect("/principal.zul");
			}else
				Clients.showNotification("Usuario o contraseña inválido");
		}
	}

}