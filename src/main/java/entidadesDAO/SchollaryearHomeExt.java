package entidadesDAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import entidades.Schollaryear;

public class SchollaryearHomeExt  extends SchollaryearHome{
	
	public SchollaryearHomeExt(){
		super();
		
	}
	
    public ArrayList<Schollaryear> listSchollaryearActivos(boolean activos ) {
    	Session session = this.getSession();
    	ArrayList<Schollaryear> results = new ArrayList<Schollaryear>();
		
    	try {
    		StringBuffer sbquery = new StringBuffer();
    		sbquery.append("from Schollaryear sy"); 
    		
    		if(activos)
    			sbquery.append(" where sy.estados.idEstado=:estado");
    		
    		sbquery.append(" order by sy.idSchoolarYear asc");
    		
    		Query query = session.createQuery(sbquery.toString());
    		
    		if(activos)
    			query.setInteger("estado", EstadosHomeExt.ESTADO_ACTIVO);
    		
    		results = (ArrayList<Schollaryear>)query.list();
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}
    
    public Long validaActiveyear(){
    	Session session = this.getSession();
    	Long conteo = 0L;
    	
    	try{
    		StringBuffer sbquery = new StringBuffer();
    		sbquery.append("select count(sy.idSchoolarYear) from Schollaryear sy where sy.estados.idEstado = 1");
        	    		
            Query query = session.createQuery(sbquery.toString());
            
            conteo = (Long) query.uniqueResult();
    	}catch(RuntimeException re){
    		throw re;
    	}
    	
    	return conteo;
    }
    
    public Date obtenerDuracionClase(){
    	Session session = this.getSession();
    	
    	Calendar hora = Calendar.getInstance();
		
		hora.set(Calendar.HOUR_OF_DAY,0);
		hora.set(Calendar.MINUTE,45);
		hora.set(Calendar.SECOND,0);
		hora.set(Calendar.MILLISECOND,0);
		
    	Date duracionClase = hora.getTime();
    	
    	try{
    		StringBuffer sbquery = new StringBuffer();
    		sbquery.append("select sy.duracionClase from Schollaryear sy where sy.estados.idEstado = 1");
        	    		
            Query query = session.createQuery(sbquery.toString());
            
            duracionClase = (Date) query.uniqueResult();
    	}catch(RuntimeException re){
    		throw re;
    	}
    	
    	return duracionClase;
    }
    
    public String validarRangoFechas(Date fechainicio, Date fechafin){
    	Session session = this.getSession();
    	String stSchollarYear = "";
    	
    	try{
    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    		String stFechaInicio = sdf.format(fechainicio);
    		String stFechaFin = sdf.format(fechafin);
    		
    		StringBuffer sbquery = new StringBuffer();
    		sbquery.append("select sy.SchollarYear from Schollaryear sy");
    		sbquery.append(" where (STR_TO_DATE('" + stFechaInicio + "', '%d/%m/%Y') between sy.FechaInicio and sy.FechaFin or STR_TO_DATE('" + stFechaFin + "', '%d/%m/%Y') between sy.FechaInicio and sy.FechaFin)");
        	    		
            Query query = session.createSQLQuery(sbquery.toString());
            
            if (query.uniqueResult() != null)
            	stSchollarYear = query.uniqueResult().toString();
    	}catch(RuntimeException re){
    		throw re;
    	}
    	
    	return stSchollarYear;
    }
    
}
