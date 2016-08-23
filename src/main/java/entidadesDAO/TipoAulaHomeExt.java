package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import entidades.Tipoaula;

public class TipoAulaHomeExt extends TipoAulaHome{
	
	public TipoAulaHomeExt(){
		super();
	}
	
	public ArrayList<Tipoaula> listTipoaulaActivos(boolean activos) {
    	Session session = this.getSession();
    	ArrayList<Tipoaula> results = new ArrayList<Tipoaula>();
		
    	try {
    		StringBuffer sbquery = new StringBuffer();
    		sbquery.append("from Tipoaula ta");
    		    		
    		if(activos)
    			sbquery.append(" where ta.estados.idEstado=:estado");
    		
    		sbquery.append(" order by ta.idTipoaula asc");
    		
    		Query query = session.createQuery(sbquery.toString());
    		
    		if(activos)
    			query.setInteger("estado", EstadosHomeExt.ESTADO_ACTIVO);
    			
			results = (ArrayList<Tipoaula>) query.list();			
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}
	
}
