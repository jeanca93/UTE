package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import entidades.Schollaryear;

public class SchollaryearHomeExt  extends SchollaryearHome{
	
	public SchollaryearHomeExt(){
		super();
		
	}
    public ArrayList<Schollaryear> listSchollaryearActivos() {
    	Session session = this.getSession();
    	ArrayList<Schollaryear> results = new ArrayList<Schollaryear>();
		
    	try {
			results = (ArrayList<Schollaryear>) session.createCriteria(Schollaryear.class)
						.add(Restrictions.eq("estado",'A'))
						.list();			
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}

}
