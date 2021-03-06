package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import basehibernate.MydbBaseHibernateDAO;
import entidades.Dias;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Dias.
 * @see entidadesDAO.Dias
 * @author Hibernate Tools
 */
public class DiasHome extends MydbBaseHibernateDAO{
	private static final Log log = LogFactory.getLog(DiasHome.class);

	public void persist(Dias transientInstance) {
		Session session = this.getSession();
		
		log.debug("persisting Dias instance");
		
		try {
			session.persist(transientInstance);
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			
			throw re;
		}
	}
	
	public void save(Dias instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("saving Dias instance");
		
		try {
			tx = session.beginTransaction();
			session.save(instance);
			
			log.debug("attach successful");
			tx.commit();
		} catch (RuntimeException re) {
			tx.rollback();
			log.error("attach failed", re);
			
			throw re;
		}
	}
	
	public void update(Dias instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("updating Dias instance");
		
		try {
			tx = session.beginTransaction();
			
			session.update(instance);
			
			log.debug("update successful");
			
			tx.commit();
		} catch (RuntimeException re) {
			tx.rollback();
			
			log.error("update failed", re);
			
			throw re;
		}
	}

	public void attachDirty(Dias instance) {
		Session session = this.getSession();
		
		log.debug("attaching dirty Dias instance");
		
		try {
			session.saveOrUpdate(instance);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void attachClean(Dias instance) {
		Session session = this.getSession();
		
		log.debug("attaching clean Dias instance");
		
		try {
			session.lock(instance, LockMode.NONE);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void delete(Dias persistentInstance) {
		Session session = this.getSession();
		
		log.debug("deleting Dias instance");
		
		try {
			session.delete(persistentInstance);
			
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			
			throw re;
		}
	}

	public Dias merge(Dias detachedInstance) {
		Session session = this.getSession();
		
		log.debug("merging Dias instance");
		
		try {
			Dias result = (Dias) session.merge(detachedInstance);
			
			log.debug("merge successful");
			
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			
			throw re;
		}
	}

	public Dias findById(java.lang.String id) {
		Session session = this.getSession();
		
		log.debug("getting Dias instance with id: " + id);
		
		try {
			Dias instance = (Dias) session.get("entidades.Dias", id);
			
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

	public List<Dias> findByExample(Dias instance) {
		Session session = this.getSession();
		
		log.debug("finding Dias instance by example");
		
		try {
			List<Dias> results = (List<Dias>) session.createCriteria("entidades.Dias")
					.add(create(instance)).list();
			
			log.debug("find by example successful, result size: " + results.size());
			
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			
			throw re;
		}
	}
}
