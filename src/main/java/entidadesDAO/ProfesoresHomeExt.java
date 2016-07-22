package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import entidades.Profesores;


public class ProfesoresHomeExt extends ProfesoresHome{
	
	public ProfesoresHomeExt(){		
		super();
		
	}
	
	public ArrayList<Profesores> listProfesoresActivos() {
		Session session = this.getSession();
    	ArrayList<Profesores> results = new ArrayList<Profesores>();
		
    	try {
			results = (ArrayList<Profesores>) session.createCriteria(Profesores.class)
						.add(Restrictions.eq("estado", 'A'))
						.addOrder(Order.asc("idProfesor"))
						.list();						
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}
}
