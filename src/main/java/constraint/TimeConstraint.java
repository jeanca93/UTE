package constraint;

import java.io.Serializable;
import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Timebox;

public class TimeConstraint implements Constraint, Serializable{
	private static final long serialVersionUID = 11L;
	
	public TimeConstraint() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public void validate(Component comp, Object value) throws WrongValueException {
		// TODO Auto-generated method stub
		if (comp instanceof Timebox) {

			// no need for checking ?
			if (((Timebox) comp).isDisabled())
				return;
			
			if (value == null)
				throw new WrongValueException(comp, "Campo obligatorio");
			else{
				if((((Date)value).getTime()/60000L) % 15 != 0)
					throw new WrongValueException(comp, "Tiempo no permitido, debe ser múltiplo de 15 minutos");
			}
		}
	}
}
