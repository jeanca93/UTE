package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;

import entidades.Dias;

public class DiasHomeExt extends DiasHome{

	public DiasHomeExt() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Dias> listDiasActivos(boolean activos) {
		Session session = this.getSession();
    	ArrayList<Dias> results = new ArrayList<Dias>();
		
    	try {
    		StringBuffer sbquery = new StringBuffer();
    		sbquery.append("from Dias d"); 
    		
    		if(activos)
    			sbquery.append(" where d.estados.idEstado=:estado");
    		
    		sbquery.append(" order by d.idDia asc");
    		
    		Query query = session.createQuery(sbquery.toString());
    		
    		if(activos)
    			query.setInteger("estado", EstadosHomeExt.ESTADO_ACTIVO);
    		
			results = (ArrayList<Dias>) query.list();						
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}
}
