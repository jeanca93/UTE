package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import entidades.Perfilesusuario;

public class PerfilesusuarioHomeExt extends  PerfilesusuarioHome{
	
	public PerfilesusuarioHomeExt(){	
		super();
		
	}
	
	public ArrayList<Perfilesusuario> listPerfilesActivos(boolean activos) {
		Session session = this.getSession();
    	ArrayList<Perfilesusuario> results = new ArrayList<Perfilesusuario>();
		
    	try {
    		Criteria criteria = session.createCriteria(Perfilesusuario.class);
    		
    		if(activos)
    			criteria.add(Restrictions.eq("estado", 'A'));
    		
    		criteria.addOrder(Order.asc("idPerfilUsuario"));
    		
			results = (ArrayList<Perfilesusuario>) criteria.list();
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}
}
