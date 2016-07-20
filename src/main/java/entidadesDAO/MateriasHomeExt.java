package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import entidades.Materias;

public class MateriasHomeExt extends MateriasHome{
    public MateriasHomeExt() {
        super();
        
    }
    
    public ArrayList<Materias> listMateriasActivos() {    	
    	Session session = null;
    	ArrayList<Materias> results;
		
    	try {
    		this.getSession().clear();
    		session=  this.getSession();
    		
			results = (ArrayList<Materias>) session.createCriteria(Materias.class).add(Restrictions.eq("estado",'A')).list();			
		} catch (RuntimeException re) {
			
			throw re;
		}
		
		return results;
	}

}
