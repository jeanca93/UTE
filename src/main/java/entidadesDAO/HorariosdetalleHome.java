package entidadesDAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import basehibernate.MydbBaseHibernateDAO;
import entidades.Horariosdetalle;

public class HorariosdetalleHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(HorariosdetalleHome.class);

	public void persist(Horariosdetalle transientInstance) {
		Session session = this.getSession();
		
		log.debug("persisting Horariosdetalle instance");
		
		try {
			session.persist(transientInstance);
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			
			throw re;
		}
	}

	public void attachDirty(Horariosdetalle instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("attaching dirty Horariosdetalle instance");
		
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
	
	public void save(Horariosdetalle instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("attaching dirty Horariosdetalle instance");
		
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
	
	public void update(Horariosdetalle instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("attaching dirty Horariosdetalle instance");
		
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

	public void attachClean(Horariosdetalle instance) {
		Session session = this.getSession();
		
		log.debug("attaching clean Horariosdetalle instance");
		
		try {
			session.lock(instance, LockMode.NONE);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void delete(Horariosdetalle persistentInstance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("deleting Horariosdetalle instance");
		
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

	public Horariosdetalle merge(Horariosdetalle detachedInstance) {
		Session session = this.getSession();
		
		log.debug("merging Horariosdetalle instance");
		
		try {
			Horariosdetalle result = (Horariosdetalle) session.merge(detachedInstance);
			
			log.debug("merge successful");
			
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			
			throw re;
		}
	}

	public Horariosdetalle findById(java.lang.Integer id) {
		Session session = this.getSession();
		
		log.debug("getting Horariosdetalle instance with id: " + id);
		
		try {
			Horariosdetalle instance = (Horariosdetalle) session.get("entidades.Horariosdetalle", id);
			
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

	public List<Horariosdetalle> findByExample(Horariosdetalle instance) {
		Session session = this.getSession();
		
		log.debug("finding Horariosdetalle instance by example");
		
		try {
			List<Horariosdetalle> results = (List<Horariosdetalle>) session.createCriteria("entidades.Horariosdetalle").add(create(instance)).list();
			
			log.debug("find by example successful, result size: " + results.size());
			
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			
			throw re;
		}
	}
	
}
