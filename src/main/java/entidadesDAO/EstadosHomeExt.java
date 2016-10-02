package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import entidades.Estados;

public class EstadosHomeExt extends EstadosHome{
	public static Integer ESTADO_ACTIVO = 1;
	public static Integer ESTADO_INACTIVO = 2;
	public static Integer ESTADO_FINALIZADO = 3;
	
	public EstadosHomeExt() {
		super();
	}
	
	public ArrayList<Estados> listEstados() {
    	Session session = this.getSession();
    	ArrayList<Estados> results = new ArrayList<Estados>();
		
    	try {
    		StringBuffer sbquery = new StringBuffer();
    		sbquery.append("from Estados estados where estados.idEstado in (1,2) order by estados.idEstado asc");
    		
    		Query query = session.createQuery(sbquery.toString());
    		
    		results = (ArrayList<Estados>) query.list();
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}
}
