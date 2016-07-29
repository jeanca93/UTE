package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import entidades.Cursos;
import entidades.Estados;

public class EstadosHomeExt extends EstadosHome{

	public EstadosHomeExt() {
		super();
	}
	
	public ArrayList<Estados> listEstados() {
    	Session session = this.getSession();
    	ArrayList<Estados> results = new ArrayList<Estados>();
		
    	try {
			results = (ArrayList<Estados>) session.createCriteria(Estados.class).addOrder(Order.asc("idEstado")).list();			
		} catch (RuntimeException re) {
			
			throw re;
		}
		
		return results;
	}
}
