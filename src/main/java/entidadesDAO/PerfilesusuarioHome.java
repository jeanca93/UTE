package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import entidades.Perfilesusuario;
import sessionfactory.MydbHibernateSessionFactory;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Perfilesusuario.
 * @see entidadesDAO.Perfilesusuario
 * @author Hibernate Tools
 */
public class PerfilesusuarioHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(PerfilesusuarioHome.class);

	private final SessionFactory sessionFactory = MydbHibernateSessionFactory.getSessionFactory();

	public void persist(Perfilesusuario transientInstance) {
		log.debug("persisting Perfilesusuario instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Perfilesusuario instance) {
		log.debug("attaching dirty Perfilesusuario instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Perfilesusuario instance) {
		log.debug("attaching clean Perfilesusuario instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Perfilesusuario persistentInstance) {
		log.debug("deleting Perfilesusuario instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Perfilesusuario merge(Perfilesusuario detachedInstance) {
		log.debug("merging Perfilesusuario instance");
		try {
			Perfilesusuario result = (Perfilesusuario) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Perfilesusuario findById(java.lang.Integer id) {
		log.debug("getting Perfilesusuario instance with id: " + id);
		try {
			Perfilesusuario instance = (Perfilesusuario) sessionFactory.getCurrentSession()
					.get("entidadesDAO.Perfilesusuario", id);
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

	public List<Perfilesusuario> findByExample(Perfilesusuario instance) {
		log.debug("finding Perfilesusuario instance by example");
		try {
			List<Perfilesusuario> results = (List<Perfilesusuario>) sessionFactory.getCurrentSession()
					.createCriteria("entidadesDAO.Perfilesusuario").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
