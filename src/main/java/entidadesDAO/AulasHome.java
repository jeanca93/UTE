package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entidades.Aulas;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Aulas.
 * @see entidadesDAO.Aulas
 * @author Hibernate Tools
 */
public class AulasHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(AulasHome.class);

	public void persist(Aulas transientInstance) {
		Session session = this.getSession();
		
		log.debug("persisting Aulas instance");
		
		try {
			session.persist(transientInstance);
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			
			throw re;
		}
	}

	public void attachDirty(Aulas instance) {
		Session session = this.getSession();
		
		log.debug("attaching dirty Aulas instance");
		
		try {
			session.saveOrUpdate(instance);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}
	
	public void save(Aulas instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("saving Aulas instance");
		
		try {
			tx = session.beginTransaction();
			
			session.save(instance);
			
			log.debug("save successful");
			
			tx.commit();
		} catch (RuntimeException re) {
			tx.rollback();
			
			log.error("save failed", re);
			
			throw re;
		}
	}
	
	public void update(Aulas instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("updating Aulas instance");
		
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

	public void attachClean(Aulas instance) {
		Session session = this.getSession();
		
		log.debug("attaching clean Aulas instance");
		
		try {
			session.lock(instance, LockMode.NONE);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void delete(Aulas persistentInstance) {
		Session session = this.getSession();
		
		log.debug("deleting Aulas instance");
		
		try {
			session.delete(persistentInstance);
			
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			
			throw re;
		}
	}

	public Aulas merge(Aulas detachedInstance) {
		Session session = this.getSession();
		
		log.debug("merging Aulas instance");
		
		try {
			Aulas result = (Aulas) session.merge(detachedInstance);
			
			log.debug("merge successful");
			
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			
			throw re;
		}
	}

	public Aulas findById(java.lang.Integer id) {
		Session session = this.getSession();
		
		log.debug("getting Aulas instance with id: " + id);
		
		try {
			Aulas instance = (Aulas) session.get("entidadesDAO.Aulas", id);
			
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

	public List<Aulas> findByExample(Aulas instance) {
		Session session = this.getSession();
		
		log.debug("finding Aulas instance by example");
		
		try {
			List<Aulas> results = (List<Aulas>) session.createCriteria("entidadesDAO.Aulas")
					.add(create(instance)).list();
			
			log.debug("find by example successful, result size: " + results.size());
			
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			
			throw re;
		}
	}
}
