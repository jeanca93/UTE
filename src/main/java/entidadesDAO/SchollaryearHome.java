package entidadesDAO;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import basehibernate.MydbBaseHibernateDAO;
import entidades.Schollaryear;

import static org.hibernate.criterion.Example.create;

public class SchollaryearHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(SchollaryearHome.class);

	public void persist(Schollaryear transientInstance) {
		Session session = this.getSession();
		
		log.debug("persisting Schollaryear instance");
		
		try {
			session.persist(transientInstance);
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			
			throw re;
		}
	}

	public void attachDirty(Schollaryear instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("attaching dirty Schollaryear instance");
		
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
	
	public void save(Schollaryear instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("attaching dirty Schollaryear instance");
		
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
	
	public void update(Schollaryear instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("attaching dirty Schollaryear instance");
		
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

	public void attachClean(Schollaryear instance) {
		Session session = this.getSession();
		
		log.debug("attaching clean Schollaryear instance");
		
		try {
			session.lock(instance, LockMode.NONE);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void delete(Schollaryear persistentInstance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("deleting Schollaryear instance");
		
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

	public Schollaryear merge(Schollaryear detachedInstance) {
		Session session = this.getSession();
		
		log.debug("merging Schollaryear instance");
		
		try {
			Schollaryear result = (Schollaryear) session.merge(detachedInstance);
			
			log.debug("merge successful");
			
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			
			throw re;
		}
	}

	public Schollaryear findById(java.lang.Integer id) {
		Session session = this.getSession();
		
		log.debug("getting Schollaryear instance with id: " + id);
		
		try {
			Schollaryear instance = (Schollaryear) session.get("entidades.Schollaryear", id);
			
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

	public List<Schollaryear> findByExample(Schollaryear instance) {
		Session session = this.getSession();
		
		log.debug("finding Schollaryear instance by example");
		
		try {
			List<Schollaryear> results = (List<Schollaryear>) session.createCriteria("entidades.Schollaryear").add(create(instance)).list();
			
			log.debug("find by example successful, result size: " + results.size());
			
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			
			throw re;
		}
	}

}
  