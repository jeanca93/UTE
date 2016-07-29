package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import entidades.Materias;

public class MateriasHomeExt extends MateriasHome{
	
	public MateriasHomeExt(){
		super();
		
	}
	
    public ArrayList<Materias> listMateriasActivos(boolean activos) {
    	Session session = this.getSession();
    	ArrayList<Materias> results = new ArrayList<Materias>();
		
    	try {
    		Criteria criteria = session.createCriteria(Materias.class);
    		
    		if(activos)
    			criteria.add(Restrictions.eq("estado", 'A'));
    		
    		criteria.addOrder(Order.asc("fechaCreacion"));
    		
			results = (ArrayList<Materias>) criteria.list();
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}

}
