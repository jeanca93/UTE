package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import entidades.Permisos;
import sessionfactory.MydbHibernateSessionFactory;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Permisos.
 * @see entidadesDAO.Permisos
 * @author Hibernate Tools
 */
public class PermisosHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(PermisosHome.class);

	private final SessionFactory sessionFactory = MydbHibernateSessionFactory.getSessionFactory();

	public void persist(Permisos transientInstance) {
		log.debug("persisting Permisos instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Permisos instance) {
		log.debug("attaching dirty Permisos instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Permisos instance) {
		log.debug("attaching clean Permisos instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Permisos persistentInstance) {
		log.debug("deleting Permisos instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Permisos merge(Permisos detachedInstance) {
		log.debug("merging Permisos instance");
		try {
			Permisos result = (Permisos) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Permisos findById(java.lang.Integer id) {
		log.debug("getting Permisos instance with id: " + id);
		try {
			Permisos instance = (Permisos) sessionFactory.getCurrentSession().get("entidadesDAO.Permisos", id);
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

	public List<Permisos> findByExample(Permisos instance) {
		log.debug("finding Permisos instance by example");
		try {
			List<Permisos> results = (List<Permisos>) sessionFactory.getCurrentSession()
					.createCriteria("entidadesDAO.Permisos").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
