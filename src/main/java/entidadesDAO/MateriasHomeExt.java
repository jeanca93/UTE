package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import entidades.Materias;

public class MateriasHomeExt extends MateriasHome{
	
	public MateriasHomeExt(){
		super();
		
	}
    public ArrayList<Materias> listMateriasActivos() {
    	Session session = this.getSession();
    	ArrayList<Materias> results = new ArrayList<Materias>();
		
    	try {
			results = (ArrayList<Materias>) session.createCriteria(Materias.class)
						.add(Restrictions.eq("estado",'A'))
						.list();			
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}

}
