package modelo.usuarios;

import java.io.Serializable;

import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import entidades.Perfilesusuario;

public class ComboBoxPerfilesRenderer implements ComboitemRenderer, Serializable{
	private static final long serialVersionUID = 5L;
	
	public void render(Comboitem item, Object data, int itemIndex)
		    throws Exception {
		Perfilesusuario option = (Perfilesusuario) data;
		item.setValue(option.getIdPerfilUsuario());
		item.setLabel(option.getPerfil());
	}
}
