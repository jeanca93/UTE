package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import entidades.Horas;

public class HorasHomeExt extends HorasHome{
	
	public HorasHomeExt(){
		super();
	}
	
	public ArrayList<Horas> listHorasActivos() {
    	Session session = this.getSession();
    	ArrayList<Horas> results = new ArrayList<Horas>();
		
    	try {
			results = (ArrayList<Horas>) session.createCriteria(Horas.class)
						.add(Restrictions.eq("estado", 'A'))
						.list();			
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}
}
