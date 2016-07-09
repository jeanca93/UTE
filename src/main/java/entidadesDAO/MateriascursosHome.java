package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import entidades.Materiascursos;
import entidades.MateriascursosId;
import sessionfactory.MydbHibernateSessionFactory;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Materiascursos.
 * @see entidadesDAO.Materiascursos
 * @author Hibernate Tools
 */
public class MateriascursosHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(MateriascursosHome.class);

	private final SessionFactory sessionFactory = MydbHibernateSessionFactory.getSessionFactory();

	public void persist(Materiascursos transientInstance) {
		log.debug("persisting Materiascursos instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Materiascursos instance) {
		log.debug("attaching dirty Materiascursos instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Materiascursos instance) {
		log.debug("attaching clean Materiascursos instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Materiascursos persistentInstance) {
		log.debug("deleting Materiascursos instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Materiascursos merge(Materiascursos detachedInstance) {
		log.debug("merging Materiascursos instance");
		try {
			Materiascursos result = (Materiascursos) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Materiascursos findById(MateriascursosId id) {
		log.debug("getting Materiascursos instance with id: " + id);
		try {
			Materiascursos instance = (Materiascursos) sessionFactory.getCurrentSession()
					.get("entidadesDAO.Materiascursos", id);
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

	public List<Materiascursos> findByExample(Materiascursos instance) {
		log.debug("finding Materiascursos instance by example");
		try {
			List<Materiascursos> results = (List<Materiascursos>) sessionFactory.getCurrentSession()
					.createCriteria("entidadesDAO.Materiascursos").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
