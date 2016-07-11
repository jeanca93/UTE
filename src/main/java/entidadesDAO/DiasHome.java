package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import entidades.Dias;
import sessionfactory.MydbHibernateSessionFactory;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Dias.
 * @see entidadesDAO.Dias
 * @author Hibernate Tools
 */
public class DiasHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(DiasHome.class);

	private final SessionFactory sessionFactory = MydbHibernateSessionFactory.getSessionFactory();

	public void persist(Dias transientInstance) {
		log.debug("persisting Dias instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Dias instance) {
		log.debug("attaching dirty Dias instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Dias instance) {
		log.debug("attaching clean Dias instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Dias persistentInstance) {
		log.debug("deleting Dias instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Dias merge(Dias detachedInstance) {
		log.debug("merging Dias instance");
		try {
			Dias result = (Dias) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Dias findById(java.lang.String id) {
		log.debug("getting Dias instance with id: " + id);
		try {
			Dias instance = (Dias) sessionFactory.getCurrentSession().get("entidadesDAO.Dias", id);
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

	public List<Dias> findByExample(Dias instance) {
		log.debug("finding Dias instance by example");
		try {
			List<Dias> results = (List<Dias>) sessionFactory.getCurrentSession().createCriteria("entidadesDAO.Dias")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
