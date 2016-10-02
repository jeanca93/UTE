package constraint;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Textbox;

public class NoSpacesConstraint implements Constraint, Serializable{
	private static final long serialVersionUID = 10L;

	public NoSpacesConstraint() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	@Override  
	public void validate(Component comp, Object value) throws WrongValueException {
		// TODO Auto-generated method stub
		if (comp instanceof Textbox) {

			// no need for checking ?
			if (((Textbox) comp).isDisabled())
				return;
			
			if (((String)value).equals(""))
				throw new WrongValueException(comp, "Campo obligatorio");
			else{
				if (((String)value).length() < 5 || ((String)value).length() > 15)
					throw new WrongValueException(comp, "Usuario debe tener m�nimo 5 caracteres y m�ximo 15 caracteres");
				else{
					if (StringUtils.countMatches((String)value, " ") > 0 )
						throw new WrongValueException(comp, "Espacios no son permitidos");
					
					Pattern regex = Pattern.compile("[^A-Za-z0-9\\.]");
					Matcher matcher = regex.matcher((String)value);
					if(matcher.find())
						throw new WrongValueException(comp, "No se permite caracteres especiales");
				}
			}
		}
	}
}
