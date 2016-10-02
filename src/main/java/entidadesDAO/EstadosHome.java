package entidadesDAO;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import basehibernate.MydbBaseHibernateDAO;
import entidades.Estados;
import entidades.Tipoaula;

import static org.hibernate.criterion.Example.create;

public class EstadosHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(EstadosHome.class);

	public void persist(Estados transientInstance) {
		Session session = this.getSession();
		
		log.debug("persisting Estados instance");
		
		try {
			session.persist(transientInstance);
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			
			throw re;
		}
	}

	public void attachDirty(Estados instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("attaching dirty Estados instance");
		
		try {
			tx = session.beginTransaction();
			
			session.saveOrUpdate(instance);
			
			log.debug("attach successful");
			
			tx.commit();
		} catch (RuntimeException re) {
			tx.rollback();
			
			log.error("attach failed", re);
			
			throw re;
		}
	}
	
	public void save(Estados instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("attaching dirty Estados instance");
		
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
	
	public void update(Estados instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("attaching dirty Estados instance");
		
		try {
			tx = session.beginTransaction();
			
			session.update(instance);
			
			log.debug("attach successful");
			
			tx.commit();
		} catch (RuntimeException re) {
			tx.rollback();
			
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void attachClean(Estados instance) {
		Session session = this.getSession();
		
		log.debug("attaching clean Estados instance");
		
		try {
			session.lock(instance, LockMode.NONE);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void delete(Estados persistentInstance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("deleting Estados instance");
		
		try {
			tx = session.beginTransaction();
			
			session.delete(persistentInstance);
			
			log.debug("delete successful");
			
			tx.commit();
		} catch (RuntimeException re) {
			tx.rollback();
			
			log.error("delete failed", re);
			
			throw re;
		}
	}

	public Estados merge(Estados detachedInstance) {
		Session session = this.getSession();
		
		log.debug("merging Estados instance");
		
		try {
			Estados result = (Estados) session.merge(detachedInstance);
			
			log.debug("merge successful");
			
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			
			throw re;
		}
	}

	public Estados findById(java.lang.Integer id) {
		Session session = this.getSession();
		
		log.debug("getting Estados instance with id: " + id);
		
		try {
			Estados instance = (Estados) session.get("entidades.Estados", id);
			
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

	public List<Estados> findByExample(Estados instance) {
		Session session = this.getSession();
		
		log.debug("finding Estados instance by example");
		
		try {
			List<Estados> results = (List<Estados>) session.createCriteria("entidades.Estados").add(create(instance)).list();
			
			log.debug("find by example successful, result size: " + results.size());
			
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			
			throw re;
		}
	}
	
}
