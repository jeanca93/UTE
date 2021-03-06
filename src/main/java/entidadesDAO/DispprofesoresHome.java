package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import basehibernate.MydbBaseHibernateDAO;
import entidades.Dispprofesores;
import entidades.DispprofesoresId;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Dispprofesores.
 * @see entidadesDAO.Dispprofesores
 * @author Hibernate Tools
 */
public class DispprofesoresHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(DispprofesoresHome.class);
	
	public void persist(Dispprofesores transientInstance) {
		Session session = this.getSession();
		
		log.debug("persisting Dispprofesores instance");
		
		try {
			session.persist(transientInstance);
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			
			throw re;
		}
	}

	public void attachDirty(Dispprofesores instance) {
		Session session = this.getSession();
		
		log.debug("attaching dirty Dispprofesores instance");
		
		try {
			session.saveOrUpdate(instance);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}
	
	public void save(Dispprofesores instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("saving Dispprofesores instance");
		
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
	
	public void update(Dispprofesores instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("updating Dispprofesores instance");
		
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

	public void attachClean(Dispprofesores instance) {
		Session session = this.getSession();
		
		log.debug("attaching clean Dispprofesores instance");
		
		try {
			session.lock(instance, LockMode.NONE);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void delete(Dispprofesores persistentInstance) {
		Session session = this.getSession();
		
		log.debug("deleting Dispprofesores instance");
		
		try {
			session.delete(persistentInstance);
			
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			
			throw re;
		}
	}

	public Dispprofesores merge(Dispprofesores detachedInstance) {
		Session session = this.getSession();
		
		log.debug("merging Dispprofesores instance");
		
		try {
			Dispprofesores result = (Dispprofesores) session.merge(detachedInstance);
			
			log.debug("merge successful");
			
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			
			throw re;
		}
	}

	public Dispprofesores findById(DispprofesoresId id) {
		Session session = this.getSession();
		
		log.debug("getting Dispprofesores instance with id: " + id);
		
		try {
			Dispprofesores instance = (Dispprofesores) session
					.get("entidadesDAO.Dispprofesores", id);
			
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

	public List<Dispprofesores> findByExample(Dispprofesores instance) {
		Session session = this.getSession();
		
		log.debug("finding Dispprofesores instance by example");
		
		try {
			List<Dispprofesores> results = (List<Dispprofesores>) session
					.createCriteria("entidadesDAO.Dispprofesores").add(create(instance)).list();
			
			log.debug("find by example successful, result size: " + results.size());
			
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			
			throw re;
		}
	}
}
