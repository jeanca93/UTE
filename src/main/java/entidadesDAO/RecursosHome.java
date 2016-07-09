package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import entidades.Recursos;
import sessionfactory.MydbHibernateSessionFactory;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Recursos.
 * @see entidadesDAO.Recursos
 * @author Hibernate Tools
 */
public class RecursosHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(RecursosHome.class);

	private final SessionFactory sessionFactory = MydbHibernateSessionFactory.getSessionFactory();

	public void persist(Recursos transientInstance) {
		log.debug("persisting Recursos instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Recursos instance) {
		log.debug("attaching dirty Recursos instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Recursos instance) {
		log.debug("attaching clean Recursos instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Recursos persistentInstance) {
		log.debug("deleting Recursos instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Recursos merge(Recursos detachedInstance) {
		log.debug("merging Recursos instance");
		try {
			Recursos result = (Recursos) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Recursos findById(java.lang.Integer id) {
		log.debug("getting Recursos instance with id: " + id);
		try {
			Recursos instance = (Recursos) sessionFactory.getCurrentSession().get("entidadesDAO.Recursos", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Recursos> findByExample(Recursos instance) {
		log.debug("finding Recursos instance by example");
		try {
			List<Recursos> results = (List<Recursos>) sessionFactory.getCurrentSession()
					.createCriteria("entidadesDAO.Recursos").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
