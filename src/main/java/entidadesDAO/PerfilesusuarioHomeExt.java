package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import entidades.Perfilesusuario;

public class PerfilesusuarioHomeExt extends  PerfilesusuarioHome{
	
	public PerfilesusuarioHomeExt(){	
		super();
		
	}
	
	public ArrayList<Perfilesusuario> listPerfilesActivos() {
		Session session = this.getSession();
    	ArrayList<Perfilesusuario> results = new ArrayList<Perfilesusuario>();
		
    	try {
			results = (ArrayList<Perfilesusuario>) session.createCriteria(Perfilesusuario.class)
						.add(Restrictions.eq("estado", 'A'))
						.addOrder(Order.asc("idPerfilUsuario"))
						.list();						
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}
}
