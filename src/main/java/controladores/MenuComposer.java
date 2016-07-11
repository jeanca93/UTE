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

import java.rmi.RemoteException;
import java.util.List;

import org.hibernate.service.spi.ServiceException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.*;


import entidades.Opciones;
import entidadesDAO.OpcionesHomeExt;
import modelo.AdvancedTreeModel;
import modelo.OpcionesList;
import modelo.OpcionesTreeRenderer;

/**
 *
 * @author Ban
 */

public class MenuComposer extends GenericForwardComposer<Component> {

	private Menubar mnbMenu;
	private Include icdEspacio;
	
	private Menu menu;
	private Menu menu2;
	private Menupopup menupopup;
	private Menuitem menuitem;
	
	private Tree mtree;
	
	private Center centerLayout;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		Menupopup menupopupTMP = null;
		
		OpcionesHomeExt opExt = new OpcionesHomeExt();
		List<Opciones> listOp = opExt.listMenus("admin",false,null);
		
		for(final Opciones ops: listOp){
			if (ops.getOpcionContenedora() == null){			
				if (ops.getOpcion().toUpperCase().equals("INICIO")){
					mnbMenu.appendChild(addMenuItem(ops));
				}else{
					if (menu == null)
						menu = new Menu();
					menu.setLabel(ops.getTituloMenu());
					mnbMenu.appendChild(menu);
				}
			}else{
				if (menupopup == null)
					menupopup = new Menupopup();
				
				if (ops.getOrden().split("-").length == 2)
				{
					if (opExt.conteoOpciones("admin", ops.getIdOpcion()) > 1)
					{
						if (menu2 == null)
							menu2 = new Menu();
						menu2.setLabel(ops.getTituloMenu());
					}else{
						menupopup.appendChild(addMenuItem(ops));
					}				
					
				}else if (ops.getOrden().split("-").length == 3)
				{
					
					if (menupopupTMP == null)
						menupopupTMP = new Menupopup();
										
					menupopupTMP.appendChild(addMenuItem(ops));
					
					if (ops.getIdOpcion() == opExt.verificaUltimo("admin", ops.getOpcionContenedora()))
					{
						menu2.appendChild(menupopupTMP);
						
						menupopup.appendChild(menu2);
						
						menupopupTMP = null;
						menu2 = null;
					}
				}	
				
				Boolean flag = true, flag2 = true;
				
				if (ops.getOrden().split("-").length == 3){
					if (ops.getOpcionContenedora() != opExt.verificaUltimo("admin", opExt.opcionPadreInicial(ops.getIdOpcion())))
					{
						flag = false;
					}
				}else{
					if (opExt.conteoOpciones("admin", ops.getIdOpcion()) > 1)
						flag2 = false;
				}
						
				
				if ((ops.getIdOpcion() == opExt.verificaUltimo("admin", ops.getOpcionContenedora())) && (flag == true) && (flag2 == true))
				{
					if (menupopup.getChildren().size() > 0){
						menu.appendChild(menupopup);
						
						menupopup = null;
						menu = null;
					}
				}
			}
		}
		AdvancedTreeModel opcionesTreeModel = new AdvancedTreeModel(new OpcionesList(session.getAttribute("usuario").toString()).getRoot());
		mtree.setItemRenderer(new OpcionesTreeRenderer(centerLayout, icdEspacio));
		mtree.setModel(opcionesTreeModel);
        
		/*
		for(final Opciones ops: listOp){
			final Treeitem treeitemPadre = new Treeitem();
			treerowPadre = new Treerow();
			treecellPadre = new Treecell();
			treecellPadre.setLabel("Personas");
			treechildren = new Treechildren();
			if (ops.getOpcionContenedora() == null){			
				if (ops.getOpcion().toUpperCase().equals("INICIO")){
					mnbMenu.appendChild(addMenuItem(ops));
				}else{
					if (menu == null)
						menu = new Menu();
					menu.setLabel(ops.getTituloMenu());
					mnbMenu.appendChild(menu);
				}
			}else{
				if (menupopup == null)
					menupopup = new Menupopup();
				
				if (ops.getOrden().split("-").length == 2)
				{
					if (opExt.conteoOpciones("admin", ops.getIdOpcion()) > 1)
					{
						if (menu2 == null)
							menu2 = new Menu();
						menu2.setLabel(ops.getTituloMenu());
					}else{
						menupopup.appendChild(addMenuItem(ops));
					}				
					
				}else if (ops.getOrden().split("-").length == 3)
				{
					
					if (menupopupTMP == null)
						menupopupTMP = new Menupopup();
										
					menupopupTMP.appendChild(addMenuItem(ops));
					
					if (ops.getIdOpcion() == opExt.verificaUltimo("admin", ops.getOpcionContenedora()))
					{
						menu2.appendChild(menupopupTMP);
						
						menupopup.appendChild(menu2);
						
						menupopupTMP = null;
						menu2 = null;
					}
				}	
				
				Boolean flag = true, flag2 = true;
				
				if (ops.getOrden().split("-").length == 3){
					if (ops.getOpcionContenedora() != opExt.verificaUltimo("admin", opExt.opcionPadreInicial(ops.getIdOpcion())))
					{
						flag = false;
					}
				}else{
					if (opExt.conteoOpciones("admin", ops.getIdOpcion()) > 1)
						flag2 = false;
				}
						
				
				if ((ops.getIdOpcion() == opExt.verificaUltimo("admin", ops.getOpcionContenedora())) && (flag == true) && (flag2 == true))
				{
					if (menupopup.getChildren().size() > 0){
						menu.appendChild(menupopup);
						
						menupopup = null;
						menu = null;
					}
				}
			}
		}*/
	}
	
	private Menuitem addMenuItem(final Opciones ops)
	{
		menuitem = new Menuitem();
		
		menuitem.setLabel(ops.getTituloMenu());
		if (ops.getUrl() != null){
			menuitem.addEventListener(Events.ON_CLICK, new org.zkoss.zk.ui.event.EventListener() {

                public void onEvent(final org.zkoss.zk.ui.event.Event event) throws InterruptedException, Exception, RemoteException, ServiceException {
                	icdEspacio.setSrc(ops.getUrl());	
                }
            });
		}
		
		return menuitem;
	}
}