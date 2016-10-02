package modelo.opciones;

import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Center;
import org.zkoss.zul.Include;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;

public class OpcionesTreeRenderer implements TreeitemRenderer<OpcionesTreeNode>{
	private final Center centerLayout;
	private Include include;
	
	public OpcionesTreeRenderer(Center centerLayout, Include include){
		this.centerLayout = centerLayout;
		this.include = include;
	}
	
	public void render(Treeitem item, OpcionesTreeNode data, int index) throws Exception {
		// TODO Auto-generated method stub
		OpcionesTreeNode otn = data;
        final Opcion opcion = (Opcion) otn.getData();
        Treerow dataRow = new Treerow();
        dataRow.setParent(item);
        item.setValue(otn);
        item.setOpen(otn.isOpen());
        
        Treecell treeCell = new Treecell();
        treeCell.setLabel(opcion.getName());
        
        if (!opcion.getIsCategory()) { // Contact Row
            treeCell.setImage(opcion.getProfilepic());
            treeCell.setStyle("font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;font-size: 14px;border-bottom: thin solid #ddd;");

            treeCell.addEventListener("onClick",
    				new EventListener<Event>() {
    				 
    					public void onEvent(Event event) {
    						
    							try {
    								Clients.showBusy(centerLayout, "Procesando...");
    								
    								include.setSrc(opcion.getSrc());
    								
    								Session session = Sessions.getCurrent();
    								session.setAttribute("pagina", opcion.getSrc());
    								
    								Clients.clearBusy(centerLayout);
    							} catch (UiException ex) {
    								Clients.clearBusy(centerLayout);
    								
    								Clients.showNotification("Menú no disponible");
    							}			
    						
    					}
    		});
        } else { // Category Row
        	treeCell.setStyle("font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;font-size: 16px;border-bottom: thin solid #ddd;");
        	
        	if (opcion.getIdOpcion() ==1){
        		treeCell.setImage(opcion.getProfilepic());
        		
        		treeCell.addEventListener("onClick",
        				new EventListener<Event>() {
        				 
        					public void onEvent(Event event) {
        						
        							try {
        								Clients.showBusy(centerLayout, "Procesando...");
        								
        								include.setSrc(opcion.getSrc());
        								
        								Session session = Sessions.getCurrent();
        								session.setAttribute("pagina", opcion.getSrc());
        								
        								Clients.clearBusy(centerLayout);
        							} catch (UiException ex) {
        								Clients.clearBusy(centerLayout);
        								
        								Clients.showNotification("Menú no disponible");
        							}			
        						
        					}
        		});
        	}
        }
        
        dataRow.appendChild(treeCell);
	}
	
}
