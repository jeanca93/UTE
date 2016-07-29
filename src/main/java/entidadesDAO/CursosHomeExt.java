package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import entidades.Cursos;

public class CursosHomeExt extends CursosHome{
	
    public CursosHomeExt() {
        super();        
    }
    
    public ArrayList<Cursos> listCursosActivos() {
    	Session session = this.getSession();
    	ArrayList<Cursos> results = new ArrayList<Cursos>();
		
    	try {
			results = (ArrayList<Cursos>) session.createCriteria(Cursos.class).addOrder(Order.asc("idCurso"))
						.addOrder(Order.asc("fechaCreacion"))
						.list();			
		} catch (RuntimeException re) {
			
			throw re;
		}
		
		return results;
	}

}
