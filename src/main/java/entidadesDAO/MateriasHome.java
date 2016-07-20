package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entidades.Materias;
import sessionfactory.MydbHibernateSessionFactory;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Materias.
 * @see entidadesDAO.Materias
 * @author Hibernate Tools
 */
public class MateriasHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(MateriasHome.class);

	private final Session session = MydbHibernateSessionFactory.getSession();

	public void persist(Materias transientInstance) {
		log.debug("persisting Materias instance");
		try {
			session.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Materias instance) {
		Transaction tx = null;
		
		log.debug("attaching dirty Materias instance");
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(instance);
			log.debug("attach successful");
			tx.commit();			
		} catch (RuntimeException re) {
			tx.commit();			
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Materias instance) {
		log.debug("attaching clean Materias instance");
		try {
			session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Materias persistentInstance) {

		Transaction tx = null;

		log.debug("deleting Materias instance");
		try {
			tx = session.beginTransaction();
		
			session.delete(persistentInstance);
			log.debug("delete successful");
			tx.commit();			
			
		} catch (RuntimeException re) {
			tx.commit();					
			log.error("delete failed", re);
			throw re;
		}
	}

	public Materias merge(Materias detachedInstance) {
		log.debug("merging Materias instance");
		try {
			Materias result = (Materias) session.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Materias findById(java.lang.Integer id) {
		log.debug("getting Materias instance with id: " + id);
		try {
			Materias instance = (Materias) session.get("entidadesDAO.Materias", id);
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

	public List<Materias> findByExample(Materias instance) {
		log.debug("finding Materias instance by example");
		try {
			List<Materias> results = (List<Materias>) session
					.createCriteria("entidadesDAO.Materias").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
