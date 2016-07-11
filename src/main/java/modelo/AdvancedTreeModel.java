package modelo;

import org.zkoss.zul.DefaultTreeModel;

public class AdvancedTreeModel extends DefaultTreeModel<Opcion>{
	private static final long serialVersionUID = -5513180500300189445L;
    
    public AdvancedTreeModel(OpcionesTreeNode opcionesTreeNode) {
        super(opcionesTreeNode);
    }
}
