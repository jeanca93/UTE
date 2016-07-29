package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import entidades.Cursos;
import entidades.Estados;

public class EstadosHomeExt extends EstadosHome{
	public static Integer ESTADO_ACTIVO = 1;
	public static Integer ESTADO_INACTIVO = 2;
	public static Integer ESTADO_PENDIENTE = 3;
	public static Integer ESTADO_FINALIZADO = 4;
	
	public EstadosHomeExt() {
		super();
	}
	
	public ArrayList<Estados> listEstados() {
    	Session session = this.getSession();
    	ArrayList<Estados> results = new ArrayList<Estados>();
		
    	try {
			results = (ArrayList<Estados>) session.createCriteria(Estados.class)
						.add(Restrictions.in("idEstado", new Integer[]{ESTADO_ACTIVO, ESTADO_INACTIVO}))
						.addOrder(Order.asc("idEstado"))
						.list();			
		} catch (RuntimeException re) {
			
			throw re;
		}
		
		return results;
	}
}
