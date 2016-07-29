package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;

import entidades.Permisos;

public class PermisosHomeExt extends PermisosHome{

	public PermisosHomeExt() {
		super();
	}
	
	public ArrayList<Permisos> listPermisosActivos() {
    	Session session = this.getSession();
    	ArrayList<Permisos> results = new ArrayList<Permisos>();
		
    	try {
    		StringBuffer sbquery = new StringBuffer();
    		sbquery.append("from Permisos p where p.estados.idEstado=:estado order by p.idPermiso asc");
    		
    		Query query = session.createQuery(sbquery.toString());
    		query.setInteger("estado", EstadosHomeExt.ESTADO_ACTIVO);
    		
			results = (ArrayList<Permisos>) query.list();			
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}
	
}
