package modelo;

import org.zkoss.zul.DefaultTreeNode;

public class OpcionesTreeNode extends DefaultTreeNode<Opcion> {
	private static final long serialVersionUID = -7012663776755277499L;
    
    private boolean open = false;
 
    public OpcionesTreeNode(Opcion data, DefaultTreeNode<Opcion>[] children) {
        super(data, children);
    }
 
    public OpcionesTreeNode(Opcion data, DefaultTreeNode<Opcion>[] children, boolean open) {
        super(data, children);
        setOpen(open);
    }
    
    public OpcionesTreeNode(Opcion data) {
        super(data);
    }
     
    public boolean isOpen() {
        return open;
    }
 
    public void setOpen(boolean open) {
        this.open = open;
    }
}
