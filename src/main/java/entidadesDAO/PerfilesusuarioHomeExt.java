package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import entidades.Perfilesusuario;

public class PerfilesusuarioHomeExt extends  PerfilesusuarioHome{
	
	public PerfilesusuarioHomeExt(){	
		super();
		
	}
	
	public ArrayList<Perfilesusuario> listPerfilesActivos(boolean activos) {
		Session session = this.getSession();
    	ArrayList<Perfilesusuario> results = new ArrayList<Perfilesusuario>();
		
    	try {
    		StringBuffer sbquery = new StringBuffer();
    		sbquery.append("from Perfilesusuario pu");
    		    		
    		if(activos)
    			sbquery.append(" where pu.estados.idEstado=:estado");
    		
    		sbquery.append(" order by pu.idPerfilUsuario asc");
    		
    		Query query = session.createQuery(sbquery.toString());
    		
    		if(activos)
    			query.setInteger("estado", EstadosHomeExt.ESTADO_ACTIVO);
    		
			results = (ArrayList<Perfilesusuario>) query.list();
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}
}
