package constraint;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;

public class MaximoMateriasConstraint  implements Constraint, Serializable {
	 public MaximoMateriasConstraint() {
		// TODO Auto-generated constructor stub
		 super();
	}
	public void validate(Component comp, Object value) throws WrongValueException {
		// TODO Auto-generated method stub
		if (comp instanceof Intbox) {

			// no need for checking ?
			if (((Intbox) comp).isDisabled())
				return;
			
			if (((Integer)value).equals(null))
				throw new WrongValueException(comp, "Campo obligatorio");
			else{
				if ((Integer)value> 15 )
					throw new WrongValueException(comp, "Cantidad de Horas no permitidas");
				
					
			}
		}
	}
	
	

}
