package modelo;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;

public class OpcionesTreeRenderer implements TreeitemRenderer<OpcionesTreeNode>{
	private boolean isCategory(Opcion opcion) {
        return opcion.getName() == null;
    }
	
	public void render(Treeitem item, OpcionesTreeNode data, int index) throws Exception {
		// TODO Auto-generated method stub
		OpcionesTreeNode otn = data;
        Opcion opcion = (Opcion) otn.getData();
        Treerow dataRow = new Treerow();
        dataRow.setParent(item);
        //dataRow.setStyle("font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;font-size: 16px;border-bottom: thin solid #ddd;");
        item.setValue(otn);
        item.setOpen(otn.isOpen());

        if (!isCategory(opcion)) { // Contact Row
            //Hlayout hl = new Hlayout();
            //hl.appendChild(new Image(opcion.getProfilepic()));
            //hl.appendChild(new Label(opcion.getName()));
            //hl.setSclass("h-inline-block");
            Treecell treeCell = new Treecell();
            treeCell.setLabel(opcion.getName());
            treeCell.setImage(opcion.getProfilepic());
            treeCell.setStyle("font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;font-size: 14px;border-bottom: thin solid #ddd;");
            //treeCell.appendChild(hl);
            dataRow.appendChild(treeCell);
        } else { // Category Row
        	Treecell treeCell = new Treecell();
        	treeCell.setLabel(opcion.getCategory());
        	treeCell.setStyle("font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;font-size: 16px;border-bottom: thin solid #ddd;");
            dataRow.appendChild(treeCell);
        }
	}
	
}
