package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import entidades.Aulas;
import sessionfactory.MydbHibernateSessionFactory;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Aulas.
 * @see entidadesDAO.Aulas
 * @author Hibernate Tools
 */
public class AulasHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(AulasHome.class);

	private final SessionFactory sessionFactory = MydbHibernateSessionFactory.getSessionFactory();

	public void persist(Aulas transientInstance) {
		log.debug("persisting Aulas instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Aulas instance) {
		log.debug("attaching dirty Aulas instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Aulas instance) {
		log.debug("attaching clean Aulas instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Aulas persistentInstance) {
		log.debug("deleting Aulas instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Aulas merge(Aulas detachedInstance) {
		log.debug("merging Aulas instance");
		try {
			Aulas result = (Aulas) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Aulas findById(java.lang.Integer id) {
		log.debug("getting Aulas instance with id: " + id);
		try {
			Aulas instance = (Aulas) sessionFactory.getCurrentSession().get("entidadesDAO.Aulas", id);
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

	public List<Aulas> findByExample(Aulas instance) {
		log.debug("finding Aulas instance by example");
		try {
			List<Aulas> results = (List<Aulas>) sessionFactory.getCurrentSession().createCriteria("entidadesDAO.Aulas")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
