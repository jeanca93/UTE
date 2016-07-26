package entidadesDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
	
	public ArrayList<Materiascursos> listMateriascur(String idCurso) {
		Session session = this.getSession();
    	ArrayList<Materiascursos> results = new ArrayList<Materiascursos>();
		
    	try {
		
			results = (ArrayList<Materiascursos>) session.createQuery("from Materiascursos mc where mc.id.cursos.idCurso=:curso")
					.setString("curso", idCurso)
					.list();	
	
			
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}
	
	public boolean registrarMateriasCursos(List<Materiascursos> listMateriasCursos){
		Session session = this.getSession();
		Transaction tx = null;
    	boolean flag = false;
		
    	try {
    		tx = session.beginTransaction();
    		
    		session.createQuery("delete from Materiascursos pm where pm.id.cursos.idCurso=:curso")
    			.setString("curso", listMateriasCursos.get(0).getId().getCursos().getIdCurso())
    			.executeUpdate();
    		
    		tx.commit();
    		
    		for(Materiascursos matcur:listMateriasCursos){
    			save(matcur);
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
