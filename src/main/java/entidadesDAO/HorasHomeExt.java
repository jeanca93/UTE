package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;

import entidades.Horas;

public class HorasHomeExt extends HorasHome{
	
	public HorasHomeExt(){
		super();
	}
	
	public ArrayList<Horas> listHorasActivos() {
    	Session session = this.getSession();
    	ArrayList<Horas> results = new ArrayList<Horas>();
		
    	try {
    		StringBuffer sbquery = new StringBuffer();
    		sbquery.append("from Horas h where h.estados.idEstado=:estado");
    		
    		Query query = session.createQuery(sbquery.toString());
    		query.setInteger("estado", EstadosHomeExt.ESTADO_ACTIVO);
    		
			results = (ArrayList<Horas>) query.list();			
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}
}
