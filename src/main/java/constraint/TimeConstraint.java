package constraint;

import java.io.Serializable;
import java.util.Calendar;
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
	
	@Override
	public void validate(Component comp, Object value) throws WrongValueException {
		// TODO Auto-generated method stub
		if (comp instanceof Timebox) {

			// no need for checking ?
			if (((Timebox) comp).isDisabled())
				return;
			
			if (value == null)
				throw new WrongValueException(comp, "Campo obligatorio");
			else{
				Calendar validaMinutos = Calendar.getInstance();
				validaMinutos.setTime((Date)value);
				int totalMinutos = ((validaMinutos.get(Calendar.HOUR_OF_DAY)*60) + validaMinutos.get(Calendar.MINUTE));
				
				if(totalMinutos < 40)
					throw new WrongValueException(comp, "Duración mínima de clases es de 40 minutos");
				else
					if(totalMinutos % 15 != 0)
						throw new WrongValueException(comp, "Tiempo no permitido, debe ser múltiplo de 15 minutos");
			}
		}
	}
}
