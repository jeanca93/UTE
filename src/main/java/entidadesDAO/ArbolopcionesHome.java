package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import entidades.Arbolopciones;
import entidades.ArbolopcionesId;
import sessionfactory.MydbHibernateSessionFactory;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Arbolopciones.
 * @see entidadesDAO.Arbolopciones
 * @author Hibernate Tools
 */
public class ArbolopcionesHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(ArbolopcionesHome.class);

	private final SessionFactory sessionFactory = MydbHibernateSessionFactory.getSessionFactory();

	public void persist(Arbolopciones transientInstance) {
		log.debug("persisting Arbolopciones instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Arbolopciones instance) {
		log.debug("attaching dirty Arbolopciones instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Arbolopciones instance) {
		log.debug("attaching clean Arbolopciones instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Arbolopciones persistentInstance) {
		log.debug("deleting Arbolopciones instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Arbolopciones merge(Arbolopciones detachedInstance) {
		log.debug("merging Arbolopciones instance");
		try {
			Arbolopciones result = (Arbolopciones) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Arbolopciones findById(ArbolopcionesId id) {
		log.debug("getting Arbolopciones instance with id: " + id);
		try {
			Arbolopciones instance = (Arbolopciones) sessionFactory.getCurrentSession()
					.get("entidadesDAO.Arbolopciones", id);
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

	public List<Arbolopciones> findByExample(Arbolopciones instance) {
		log.debug("finding Arbolopciones instance by example");
		try {
			List<Arbolopciones> results = (List<Arbolopciones>) sessionFactory.getCurrentSession()
					.createCriteria("entidadesDAO.Arbolopciones").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
