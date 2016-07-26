package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import entidades.Cursos;


public class CursosHomeExt extends CursosHome{
	private Session session;

    public CursosHomeExt() {

        super();

        session = getSession();
        
    }
    
    public ArrayList<Cursos> listCursosActivos() {
    	ArrayList<Cursos> results;
		
    	try {
			results = (ArrayList<Cursos>) session.createCriteria(Cursos.class).addOrder(Order.asc("idCurso")).list();			
		} catch (RuntimeException re) {
			
			throw re;
		}
		
		return results;
	}

}
