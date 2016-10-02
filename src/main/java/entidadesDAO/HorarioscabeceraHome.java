package entidadesDAO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import basehibernate.MydbBaseHibernateDAO;
import entidades.Horarioscabecera;

public class HorarioscabeceraHome extends MydbBaseHibernateDAO{
	private static final Log log = LogFactory.getLog(HorarioscabeceraHome.class);

	public void persist(Horarioscabecera transientInstance) {
		Session session = this.getSession();
		
		log.debug("persisting Horarioscabecera instance");
		
		try {
			session.persist(transientInstance);
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			
			throw re;
		}
	}

	public void attachDirty(Horarioscabecera instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("attaching dirty Horarioscabecera instance");
		
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
	
	public void save(Horarioscabecera instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("attaching dirty Horarioscabecera instance");
		
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
	
	public void update(Horarioscabecera instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("attaching dirty Horarioscabecera instance");
		
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

	public void attachClean(Horarioscabecera instance) {
		Session session = this.getSession();
		
		log.debug("attaching clean Horarioscabecera instance");
		
		try {
			session.lock(instance, LockMode.NONE);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void delete(Horarioscabecera persistentInstance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("deleting Horarioscabecera instance");
		
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

	public Horarioscabecera merge(Horarioscabecera detachedInstance) {
		Session session = this.getSession();
		
		log.debug("merging Horarioscabecera instance");
		
		try {
			Horarioscabecera result = (Horarioscabecera) session.merge(detachedInstance);
			
			log.debug("merge successful");
			
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			
			throw re;
		}
	}

	public Horarioscabecera findById(java.lang.Integer id) {
		Session session = this.getSession();
		
		log.debug("getting Horarioscabecera instance with id: " + id);
		
		try {
			Horarioscabecera instance = (Horarioscabecera) session.get("entidades.Horarioscabecera", id);
			
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
}
