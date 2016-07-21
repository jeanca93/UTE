package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import entidades.Aulas;

public class AulasHomeExt extends AulasHome{
	private Session session;

    public AulasHomeExt() {

        super();

        session = getSession();
        
    }
    
    public ArrayList<Aulas> listAulasActivas() {
    	ArrayList<Aulas> results;
		
    	try {
			results = (ArrayList<Aulas>) session.createCriteria(Aulas.class).addOrder(Order.asc("idAula")).list();			
		} catch (RuntimeException re) {
			
			throw re;
		}
		
		return results;
	}

}
