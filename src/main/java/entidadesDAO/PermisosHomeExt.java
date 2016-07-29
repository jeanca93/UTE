package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import entidades.Permisos;

public class PermisosHomeExt extends PermisosHome{

	public PermisosHomeExt() {
		super();
	}
	
	public ArrayList<Permisos> listPermisosActivos() {
    	Session session = this.getSession();
    	ArrayList<Permisos> results = new ArrayList<Permisos>();
		
    	try {
			results = (ArrayList<Permisos>) session.createCriteria(Permisos.class)
						.add(Restrictions.eq("estado",'A'))
						.addOrder(Order.asc("idPermiso"))
						.list();			
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}
	
}
