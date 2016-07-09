package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import entidades.Materiasrecursos;
import entidades.MateriasrecursosId;
import sessionfactory.MydbHibernateSessionFactory;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Materiasrecursos.
 * @see entidadesDAO.Materiasrecursos
 * @author Hibernate Tools
 */
public class MateriasrecursosHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(MateriasrecursosHome.class);

	private final SessionFactory sessionFactory = MydbHibernateSessionFactory.getSessionFactory();

	public void persist(Materiasrecursos transientInstance) {
		log.debug("persisting Materiasrecursos instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Materiasrecursos instance) {
		log.debug("attaching dirty Materiasrecursos instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Materiasrecursos instance) {
		log.debug("attaching clean Materiasrecursos instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Materiasrecursos persistentInstance) {
		log.debug("deleting Materiasrecursos instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Materiasrecursos merge(Materiasrecursos detachedInstance) {
		log.debug("merging Materiasrecursos instance");
		try {
			Materiasrecursos result = (Materiasrecursos) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Materiasrecursos findById(MateriasrecursosId id) {
		log.debug("getting Materiasrecursos instance with id: " + id);
		try {
			Materiasrecursos instance = (Materiasrecursos) sessionFactory.getCurrentSession()
					.get("entidadesDAO.Materiasrecursos", id);
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

	public List<Materiasrecursos> findByExample(Materiasrecursos instance) {
		log.debug("finding Materiasrecursos instance by example");
		try {
			List<Materiasrecursos> results = (List<Materiasrecursos>) sessionFactory.getCurrentSession()
					.createCriteria("entidadesDAO.Materiasrecursos").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
