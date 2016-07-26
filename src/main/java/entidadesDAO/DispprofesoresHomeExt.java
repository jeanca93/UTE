package entidadesDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entidades.Dispprofesores;
import entidades.DispprofesoresId;

public class DispprofesoresHomeExt extends DispprofesoresHome{

	public DispprofesoresHomeExt() {
		super();
	}
	
	public ArrayList<DispprofesoresId> listDispprofesor(Integer idProfesor) {
		Session session = this.getSession();
    	ArrayList<DispprofesoresId> results = new ArrayList<DispprofesoresId>();
		
    	try {
    		results = (ArrayList<DispprofesoresId>) session.createQuery("select dp.id from Dispprofesores dp where dp.id.profesores.idProfesor=:profesor")
					.setInteger("profesor", idProfesor)
					.list();
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}
	
	public boolean registrarDisponibilidadProfesor(List<Dispprofesores> dispProfesor){
		Session session = this.getSession();
		Transaction tx = null;
		boolean flag = false;
		
		try{
			tx = session.beginTransaction();
    		
    		session.createQuery("delete from Dispprofesores dp where dp.id.profesores.idProfesor=:profesor")
    			.setInteger("profesor", dispProfesor.get(0).getId().getProfesores().getIdProfesor())
    			.executeUpdate();
    		
    		tx.commit();
    		
    		for(Dispprofesores dispProf:dispProfesor){
    			save(dispProf);
    		}
    		
			flag = true;
		}catch(RuntimeException re){			
			throw re;
		}
		
		return flag;
	}

}
