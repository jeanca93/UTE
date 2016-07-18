package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import entidades.Perfilesusuario;

public class PerfilesusuarioHomeExt extends  PerfilesusuarioHome{
	private Session session;
	
	public PerfilesusuarioHomeExt(){
		
		super();
		
		session = getSession();
		
	}
	
	public ArrayList<Perfilesusuario> listPerfilesActivos() {
    	ArrayList<Perfilesusuario> results;
		
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
