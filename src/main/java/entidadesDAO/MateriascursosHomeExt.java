package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Session;

import entidades.Materiascursos;

public class MateriascursosHomeExt extends MateriascursosHome{
	
	public MateriascursosHomeExt(){		
		super();
		
	}
	
	public ArrayList<Materiascursos> listMateriascursos() {
		Session session = this.getSession();
    	ArrayList<Materiascursos> results = new ArrayList<Materiascursos>();
		
    	try {
			results = (ArrayList<Materiascursos>) session.createCriteria(Materiascursos.class).list();						
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}
}
