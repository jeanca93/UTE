package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import entidades.Tipoaula;

public class TipoAulaHomeExt extends TipoAulaHome{
	
	public TipoAulaHomeExt(){
		super();
	}
	
	public ArrayList<Tipoaula> listTipoaulaActivos(boolean activos) {
    	Session session = this.getSession();
    	ArrayList<Tipoaula> results = new ArrayList<Tipoaula>();
		
    	try {
    		Criteria criteria = session.createCriteria(Tipoaula.class);
    		
    		if(activos)
    			criteria.add(Restrictions.eq("estado", 'A'));
    		
    		criteria.addOrder(Order.asc("idTipoaula"));
    		
			results = (ArrayList<Tipoaula>) criteria.list();			
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}
	
}
