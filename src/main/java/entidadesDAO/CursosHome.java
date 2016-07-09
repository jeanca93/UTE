package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import entidades.Cursos;
import sessionfactory.MydbHibernateSessionFactory;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Cursos.
 * @see entidadesDAO.Cursos
 * @author Hibernate Tools
 */
public class CursosHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(CursosHome.class);

	private final SessionFactory sessionFactory = MydbHibernateSessionFactory.getSessionFactory();
	
	public void persist(Cursos transientInstance) {
		log.debug("persisting Cursos instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Cursos instance) {
		log.debug("attaching dirty Cursos instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Cursos instance) {
		log.debug("attaching clean Cursos instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Cursos persistentInstance) {
		log.debug("deleting Cursos instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Cursos merge(Cursos detachedInstance) {
		log.debug("merging Cursos instance");
		try {
			Cursos result = (Cursos) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Cursos findById(java.lang.Integer id) {
		log.debug("getting Cursos instance with id: " + id);
		try {
			Cursos instance = (Cursos) sessionFactory.getCurrentSession().get("entidadesDAO.Cursos", id);
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

	public List<Cursos> findByExample(Cursos instance) {
		log.debug("finding Cursos instance by example");
		try {
			List<Cursos> results = (List<Cursos>) sessionFactory.getCurrentSession()
					.createCriteria("entidadesDAO.Cursos").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
