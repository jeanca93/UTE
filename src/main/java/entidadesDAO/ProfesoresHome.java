package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import entidades.Profesores;
import sessionfactory.MydbHibernateSessionFactory;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Profesores.
 * @see entidadesDAO.Profesores
 * @author Hibernate Tools
 */
public class ProfesoresHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(ProfesoresHome.class);

	private final SessionFactory sessionFactory = MydbHibernateSessionFactory.getSessionFactory();

	public void persist(Profesores transientInstance) {
		log.debug("persisting Profesores instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Profesores instance) {
		log.debug("attaching dirty Profesores instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Profesores instance) {
		log.debug("attaching clean Profesores instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Profesores persistentInstance) {
		log.debug("deleting Profesores instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Profesores merge(Profesores detachedInstance) {
		log.debug("merging Profesores instance");
		try {
			Profesores result = (Profesores) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Profesores findById(java.lang.Integer id) {
		log.debug("getting Profesores instance with id: " + id);
		try {
			Profesores instance = (Profesores) sessionFactory.getCurrentSession().get("entidadesDAO.Profesores", id);
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

	public List<Profesores> findByExample(Profesores instance) {
		log.debug("finding Profesores instance by example");
		try {
			List<Profesores> results = (List<Profesores>) sessionFactory.getCurrentSession()
					.createCriteria("entidadesDAO.Profesores").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
