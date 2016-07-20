package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import entidades.Materias;

public class MateriasHomeExt extends MateriasHome{
	private Session session;

    public MateriasHomeExt() {

        super();

        session = getSession();
        
    }
    
    public ArrayList<Materias> listMateriasActivos() {
    	ArrayList<Materias> results;
		
    	try {
			results = (ArrayList<Materias>) session.createCriteria(Materias.class).add(Restrictions.eq("estado",'A')).list();			
		} catch (RuntimeException re) {
			
			throw re;
		}
		
		return results;
	}

}
