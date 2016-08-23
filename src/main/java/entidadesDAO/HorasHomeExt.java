package entidadesDAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entidades.Dias;
import entidades.Horas;

public class HorasHomeExt extends HorasHome{
	
	public HorasHomeExt(){
		super();
	}
	
	public ArrayList<Horas> listHorasActivos(boolean activos) {
    	Session session = this.getSession();
    	ArrayList<Horas> results = new ArrayList<Horas>();
		
    	try {
    		StringBuffer sbquery = new StringBuffer();
    		sbquery.append("from Horas h");
    		
    		if (activos)
    			sbquery.append(" where h.estados.idEstado=:estado");
    		
    		sbquery.append(" order by idHora asc");
    		Query query = session.createQuery(sbquery.toString());
    		
    		if (activos)
    			query.setInteger("estado", EstadosHomeExt.ESTADO_ACTIVO);
    		
			results = (ArrayList<Horas>) query.list();			
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}
	
	public boolean registrarJornadaAcademica(Date horaIni, Date horaFin, Date receso1Ini, Date receso1Fin, Date receso2Ini, Date receso2Fin, List<Dias> diasSeleccionados, Integer usuario){
		Session session = this.getSession();
    	Transaction tx = null;
		boolean flag = false;
		
		try{
			SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm");
			String param_horaIni = localDateFormat.format(horaIni);
			String param_horaFin = localDateFormat.format(horaFin);
			String param_receso1Ini = localDateFormat.format(receso1Ini);
			String param_receso1Fin = localDateFormat.format(receso1Fin);
			String param_receso2Ini = localDateFormat.format(receso2Ini);
			String param_receso2Fin = localDateFormat.format(receso2Fin);
			
			flag = true;
			
			tx = session.beginTransaction();
    		
    		StringBuffer sbquery = new StringBuffer();
    		sbquery.append("call generar_horas('" + param_horaIni + "','" + param_horaFin + "','" + param_receso1Ini + "','" + param_receso1Fin + "','" + param_receso2Ini + "','" + param_receso2Fin + "'," + usuario + ")");
        	
            Query query = session.createSQLQuery(sbquery.toString());
            
            flag = (query.executeUpdate() ==1?true:false);
            
            tx.commit();
			
			for(Dias dia:new DiasHomeExt().listDiasActivos(false)){
				Integer estado = EstadosHomeExt.ESTADO_INACTIVO;
				
				for(Dias diaSelected:diasSeleccionados){
					if(dia.equals(diaSelected)){
						estado = EstadosHomeExt.ESTADO_ACTIVO;
						break;
					}
				}
				
				if(dia.getEstados().getIdEstado() != estado){
					dia.setEstados(new EstadosHome().findById(estado));
					dia.setFechaModificacion(new Date());
					dia.setUsuarioModifica(usuario);
					
					new DiasHome().update(dia);
				}
			}
		}catch(RuntimeException re){
			throw re;
		}
		
		return flag;
	}
}
