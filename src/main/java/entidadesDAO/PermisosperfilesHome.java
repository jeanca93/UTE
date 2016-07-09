package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import entidades.Permisosperfiles;
import entidades.PermisosperfilesId;
import sessionfactory.MydbHibernateSessionFactory;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Permisosperfiles.
 * @see entidadesDAO.Permisosperfiles
 * @author Hibernate Tools
 */
public class PermisosperfilesHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(PermisosperfilesHome.class);

	private final SessionFactory sessionFactory = MydbHibernateSessionFactory.getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Permisosperfiles transientInstance) {
		log.debug("persisting Permisosperfiles instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Permisosperfiles instance) {
		log.debug("attaching dirty Permisosperfiles instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Permisosperfiles instance) {
		log.debug("attaching clean Permisosperfiles instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Permisosperfiles persistentInstance) {
		log.debug("deleting Permisosperfiles instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Permisosperfiles merge(Permisosperfiles detachedInstance) {
		log.debug("merging Permisosperfiles instance");
		try {
			Permisosperfiles result = (Permisosperfiles) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Permisosperfiles findById(PermisosperfilesId id) {
		log.debug("getting Permisosperfiles instance with id: " + id);
		try {
			Permisosperfiles instance = (Permisosperfiles) sessionFactory.getCurrentSession()
					.get("entidadesDAO.Permisosperfiles", id);
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

	public List<Permisosperfiles> findByExample(Permisosperfiles instance) {
		log.debug("finding Permisosperfiles instance by example");
		try {
			List<Permisosperfiles> results = (List<Permisosperfiles>) sessionFactory.getCurrentSession()
					.createCriteria("entidadesDAO.Permisosperfiles").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
