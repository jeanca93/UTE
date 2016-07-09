package entidadesDAO;

import org.hibernate.Session;


/**
 * Data access interface for domain model
 * @author MyEclipse Persistence Tools
 */
public interface IMydbBaseHibernateDAO {
	public Session getSession();
}
