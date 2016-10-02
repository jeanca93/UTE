package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entidades.Generacionhorarios;

public class GeneracionhorariosHomeExt extends GeneracionhorariosHome {
	
	public ArrayList<Generacionhorarios> listGeneracionhorarios() {
		Session session = this.getSession();
    	ArrayList<Generacionhorarios> results = new ArrayList<Generacionhorarios>();
		
    	try {
    		StringBuffer sbquery = new StringBuffer();
    		sbquery.append("from Generacionhorarios gh order by gh.idGeneracionHorario asc");
    		
    		Query query = session.createQuery(sbquery.toString());
    		
			results = (ArrayList<Generacionhorarios>) query.list();						
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}
	
	public boolean eliminarRegistro(Integer idUsuario){
		Session session = this.getSession();
		session.flush();
    	boolean flag = false;
    	Transaction tx = null;
    	
    	try {
    		StringBuffer sbquery = new StringBuffer();
    		sbquery.append("delete from Generacionhorarios gh where gh.usuarioGenera=:usuario");
    		
    		tx = session.beginTransaction();
    		
    		Query query = session.createQuery(sbquery.toString());
    		query.setInteger("usuario", idUsuario);
    		
			flag = query.executeUpdate()==1?true:false;	
			
			tx.commit();
		} catch (RuntimeException re) {			
			tx.rollback();
			
			throw re;
		}
		
		return flag;
	}
	
	public Object[] validarInformacionInicial() {
		Session session = this.getSession();
    	Object[] obj = null;
    	
    	try {
    		StringBuffer sbquery = new StringBuffer();
    		sbquery.append("SELECT "
    				+ "(SELECT COUNT(*) FROM schollaryear WHERE ID_Estado = 1) AS SchollarYear, "
    				+ "(SELECT COUNT(*) from dias WHERE ID_Estado = 1) AS Dias, "
    				+ "(SELECT COUNT(*) FROM horas WHERE ID_Estado = 1) AS Horas, "
    				+ "(SELECT COUNT(*) FROM tipoaula WHERE ID_Estado = 1) AS TipoAula, "
    				+ "(SELECT COUNT(*) FROM aulas WHERE ID_Estado = 1) AS Aulas, "
    				+ "(SELECT COUNT(*) FROM cursos WHERE ID_Estado = 1) AS Cursos, "
    				+ "(SELECT COUNT(*) FROM materias WHERE ID_Estado = 1) AS Materias, "
    				+ "(SELECT COUNT(*) FROM materiascursos WHERE ID_Estado = 1) AS MateriasCursos, "
    				+ "(SELECT COUNT(*) FROM profesores WHERE ID_Estado = 1) AS Profesores, "
    				+ "(SELECT COUNT(*) FROM profesoresmaterias WHERE ID_Estado = 1) AS ProfesoresMaterias, "
    				+ "(SELECT COUNT(*) FROM dispprofesores WHERE ID_Estado = 1) AS DispProfesores;");
    		    		
    		Query query = session.createSQLQuery(sbquery.toString());
    		
    		obj = (Object[]) query.uniqueResult();				
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return obj;
	}
	
}
