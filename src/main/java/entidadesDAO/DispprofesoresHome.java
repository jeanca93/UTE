package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import entidades.Dispprofesores;
import entidades.DispprofesoresId;
import sessionfactory.MydbHibernateSessionFactory;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Dispprofesores.
 * @see entidadesDAO.Dispprofesores
 * @author Hibernate Tools
 */
public class DispprofesoresHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(DispprofesoresHome.class);

	private final SessionFactory sessionFactory = MydbHibernateSessionFactory.getSessionFactory();
	
	public void persist(Dispprofesores transientInstance) {
		log.debug("persisting Dispprofesores instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Dispprofesores instance) {
		log.debug("attaching dirty Dispprofesores instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Dispprofesores instance) {
		log.debug("attaching clean Dispprofesores instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Dispprofesores persistentInstance) {
		log.debug("deleting Dispprofesores instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Dispprofesores merge(Dispprofesores detachedInstance) {
		log.debug("merging Dispprofesores instance");
		try {
			Dispprofesores result = (Dispprofesores) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Dispprofesores findById(DispprofesoresId id) {
		log.debug("getting Dispprofesores instance with id: " + id);
		try {
			Dispprofesores instance = (Dispprofesores) sessionFactory.getCurrentSession()
					.get("entidadesDAO.Dispprofesores", id);
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

	public List<Dispprofesores> findByExample(Dispprofesores instance) {
		log.debug("finding Dispprofesores instance by example");
		try {
			List<Dispprofesores> results = (List<Dispprofesores>) sessionFactory.getCurrentSession()
					.createCriteria("entidadesDAO.Dispprofesores").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
