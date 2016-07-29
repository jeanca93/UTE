package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;

import entidades.Materias;

public class MateriasHomeExt extends MateriasHome{
	
	public MateriasHomeExt(){
		super();
		
	}
	
    public ArrayList<Materias> listMateriasActivos(boolean activos) {
    	Session session = this.getSession();
    	ArrayList<Materias> results = new ArrayList<Materias>();
		
    	try {
    		StringBuffer sbquery = new StringBuffer();
    		sbquery.append("from Materias m");
    		    		
    		if(activos)
    			sbquery.append(" where m.estados.idEstado=:estado");
    		
    		sbquery.append(" order by m.fechaCreacion asc");
    		
    		Query query = session.createQuery(sbquery.toString());
    		
    		if(activos)
    			query.setInteger("estado", EstadosHomeExt.ESTADO_ACTIVO);
    		
			results = (ArrayList<Materias>) query.list();
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}

}
