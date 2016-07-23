package constraint;

import java.io.Serializable;
import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Timebox;

public class MaximoMateriasConstraint  implements Constraint, Serializable {
	 public MaximoMateriasConstraint() {
		// TODO Auto-generated constructor stub
		 super();
	}
	public void validate(Component comp, Object value) throws WrongValueException {
		// TODO Auto-generated method stub
		if (comp instanceof Timebox) {

			// no need for checking ?
			if (((Timebox) comp).isDisabled())
				return;
			
			if (((Date)value).equals(null))
				throw new WrongValueException(comp, "Campo obligatorio");
			else{
				if (((Date)value).getHours() > 24 )
					throw new WrongValueException(comp, "Horas no permitidas");
				
					
			}
		}
	}
	
	

}
