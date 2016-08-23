package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import entidades.Aulas;

public class AulasHomeExt extends AulasHome{
	
    public AulasHomeExt() {
        super();        
    }
    
    public ArrayList<Aulas> listAulasActivas() {
    	Session session = getSession();
    	ArrayList<Aulas> results = new ArrayList<Aulas>();
		
    	try {
			results = (ArrayList<Aulas>) session.createCriteria(Aulas.class).addOrder(Order.asc("idAula"))
						.addOrder(Order.asc("fechaCreacion"))
						.list();			
		} catch (RuntimeException re) {
			
			throw re;
		}
		
		return results;
	}

}
