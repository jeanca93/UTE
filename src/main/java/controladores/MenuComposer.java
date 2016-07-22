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

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.*;

import modelo.opciones.AdvancedTreeModel;
import modelo.opciones.OpcionesList;
import modelo.opciones.OpcionesTreeRenderer;

/**
 *
 * @author Ban
 */

public class MenuComposer extends GenericForwardComposer<Component> {
	private static final long serialVersionUID = 2L;
	private Tree mtree;	
	private Center centerLayout;
	private West westLayout;
	private Include icdEspacio;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		String user = (session.getAttribute("usuario")!= null?session.getAttribute("usuario").toString():"");
		
		if(user.length() == 0)
			Executions.sendRedirect("/index.zul");
		else{
			String pagina = (session.getAttribute("pagina")!= null?session.getAttribute("pagina").toString():"");
			
			if(pagina.trim().length() != 0)
				icdEspacio.setSrc(pagina);
		}
		
		Clients.showBusy("Cargando...");
		
		AdvancedTreeModel opcionesTreeModel = new AdvancedTreeModel(new OpcionesList(user).getRoot());
		mtree.setItemRenderer(new OpcionesTreeRenderer(centerLayout, icdEspacio));
		mtree.setModel(opcionesTreeModel);
		
		Clients.clearBusy();
	}
}