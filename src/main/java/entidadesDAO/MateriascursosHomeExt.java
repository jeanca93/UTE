package entidadesDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import entidades.Materiascursos;


public class MateriascursosHomeExt extends MateriascursosHome{
	
	public MateriascursosHomeExt(){		
		super();
		
	}
	
	public ArrayList<Materiascursos> listMateriascursos(String idCurso) {
		Session session = this.getSession();		
		ArrayList<Materiascursos> results = new ArrayList<Materiascursos>();
		
		try{
			StringBuffer sbquery = new StringBuffer();
    		sbquery.append("from Materiascursos mc");
    		
    		if(!(idCurso.equals("") || idCurso.equals(null)))
    			sbquery.append(" where mc.id.cursos.idCurso=:curso");
    		
    		Query query = session.createQuery(sbquery.toString());
    		
    		if(!(idCurso.equals("") || idCurso.equals(null))){
    			query.setString("curso", idCurso);
    		}
    		
			results = (ArrayList<Materiascursos>) query.list();
		}catch (RuntimeException re) {
			throw re;	
		}
		
		return results;		
	}
	
	public boolean registrarMateriasCursos(List<Materiascursos> listMateriasCursos){
		Session session = this.getSession();
		session.flush();
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
