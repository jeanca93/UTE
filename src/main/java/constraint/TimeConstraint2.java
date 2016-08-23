package constraint;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Timebox;

public class TimeConstraint2 implements Constraint, Serializable{
	private static final long serialVersionUID = 13L;
	
	public TimeConstraint2() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void validate(Component arg0, Object arg1) throws WrongValueException {
		// TODO Auto-generated method stub
		if (arg0 instanceof Timebox) {

			// no need for checking ?
			if (((Timebox) arg0).isDisabled())
				return;
			
			if (arg1 == null)
				throw new WrongValueException(arg0, "Campo obligatorio");
			else{
				Calendar validaMinutos = Calendar.getInstance();
				validaMinutos.setTime((Date)arg1);
				int totalMinutos = ((validaMinutos.get(Calendar.HOUR_OF_DAY)*60) + validaMinutos.get(Calendar.MINUTE));
				
				if(totalMinutos < 15)
					throw new WrongValueException(arg0, "Duración mínima de recesos es de 15 minutos");
				else
					if(totalMinutos % 5 != 0)
						throw new WrongValueException(arg0, "Tiempo no permitido, debe ser múltiplo de 5 minutos");
			}
		}
	}
}
