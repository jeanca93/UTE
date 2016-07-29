package entidadesDAO;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import basehibernate.MydbBaseHibernateDAO;
import entidades.Tipoaula;

public class TipoAulaHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(TipoAulaHome.class);

	public void persist(Tipoaula transientInstance) {
		Session session = this.getSession();
		
		log.debug("persisting Tipoaula instance");
		
		try {
			session.persist(transientInstance);
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			
			throw re;
		}
	}

	public void attachDirty(Tipoaula instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("attaching dirty Tipoaula instance");
		
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
	
	public void save(Tipoaula instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("attaching dirty Tipoaula instance");
		
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
	
	public void update(Tipoaula instance) {
		//this.getSession().clear();
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("attaching dirty Tipoaula instance");
		
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

	public void attachClean(Tipoaula instance) {
		Session session = this.getSession();
		
		log.debug("attaching clean Tipoaula instance");
		
		try {
			session.lock(instance, LockMode.NONE);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void delete(Tipoaula persistentInstance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("deleting Tipoaula instance");
		
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

	public Tipoaula merge(Tipoaula detachedInstance) {
		Session session = this.getSession();
		
		log.debug("merging Tipoaula instance");
		
		try {
			Tipoaula result = (Tipoaula) session.merge(detachedInstance);
			
			log.debug("merge successful");
			
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			
			throw re;
		}
	}

	public Tipoaula findById(java.lang.Integer id) {
		Session session = this.getSession();
		
		log.debug("getting Tipoaula instance with id: " + id);
		
		try {
			Tipoaula instance = (Tipoaula) session.get("entidades.Tipoaula", id);
			
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

	public List<Tipoaula> findByExample(Tipoaula instance) {
		Session session = this.getSession();
		
		log.debug("finding Tipoaula instance by example");
		
		try {
			List<Tipoaula> results = (List<Tipoaula>) session.createCriteria("entidades.Tipoaula").add(create(instance)).list();
			
			log.debug("find by example successful, result size: " + results.size());
			
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			
			throw re;
		}
	}
	
}
