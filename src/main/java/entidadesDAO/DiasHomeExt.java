package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import entidades.Dias;

public class DiasHomeExt extends DiasHome{

	public DiasHomeExt() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Dias> listDiasActivos() {
		Session session = this.getSession();
    	ArrayList<Dias> results = new ArrayList<Dias>();
		
    	try {
			results = (ArrayList<Dias>) session.createCriteria(Dias.class)
						.add(Restrictions.eq("estado", 'A'))
						.addOrder(Order.asc("idDia"))
						.list();						
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}
}
