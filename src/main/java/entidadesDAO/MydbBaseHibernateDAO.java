package entidadesDAO;

import org.hibernate.Session;
import sessionfactory.MydbHibernateSessionFactory;

/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class MydbBaseHibernateDAO implements IMydbBaseHibernateDAO {
	
	public Session getSession() {
		return MydbHibernateSessionFactory.getSession();
	}
	
}
