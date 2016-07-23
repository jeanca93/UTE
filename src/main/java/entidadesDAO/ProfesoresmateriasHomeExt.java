package entidadesDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entidades.Materiascursos;
import entidades.Profesoresmaterias;

public class ProfesoresmateriasHomeExt extends ProfesoresmateriasHome{
	
	public ProfesoresmateriasHomeExt(){	
		super();
		
	}
	
	public ArrayList<Materiascursos> listProfesoresmaterias(Integer idProfesor) {
    	Session session = this.getSession();
    	ArrayList<Materiascursos> results = new ArrayList<Materiascursos>();
		
    	try {
			results = (ArrayList<Materiascursos>) session.createQuery("select pm.id.materiascursos from Profesoresmaterias pm where pm.id.profesores.idProfesor=:profesor")
					.setInteger("profesor", idProfesor)
					.list();	
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}
	
	public boolean registrarMateriasProfesor(List<Profesoresmaterias> listProfMaterias){
		Session session = this.getSession();
		Transaction tx = null;
    	boolean flag = false;
		
    	try {
    		tx = session.beginTransaction();
    		
    		session.createQuery("delete from Profesoresmaterias pm where pm.id.profesores.idProfesor=:profesor")
    			.setInteger("profesor", listProfMaterias.get(0).getId().getProfesores().getIdProfesor())
    			.executeUpdate();
    		
    		tx.commit();
    		
    		for(Profesoresmaterias profMat:listProfMaterias){
    			save(profMat);
    		}
    		
    		flag = true;
		} catch (RuntimeException re) {
			if(tx.isActive())
				tx.rollback();
			
			throw re;
		}
    	
    	return false;
	}	
	
}
