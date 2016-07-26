package entidadesDAO;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;

import basehibernate.MydbBaseHibernateDAO;
import entidades.Estados;

import static org.hibernate.criterion.Example.create;

public class EstadosHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(HorasHome.class);

	public void persist(Estados transientInstance) {
		Session session = this.getSession();
		
		log.debug("persisting Horas instance");
		
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
		
		log.debug("attaching dirty Horas instance");
		
		try {
			session.saveOrUpdate(instance);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void attachClean(Estados instance) {
		Session session = this.getSession();
		
		log.debug("attaching clean Horas instance");
		
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
		
		log.debug("deleting Horas instance");
		
		try {
			session.delete(persistentInstance);
			
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			
			throw re;
		}
	}

	public Estados merge(Estados detachedInstance) {
		Session session = this.getSession();
		
		log.debug("merging Horas instance");
		
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
		
		log.debug("getting Horas instance with id: " + id);
		
		try {
			Estados instance = (Estados) session.get("entidadesDAO.Horas", id);
			
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
		
		log.debug("finding Horas instance by example");
		
		try {
			List<Estados> results = (List<Estados>) session.createCriteria("entidadesDAO.Horas")
					.add(create(instance)).list();
			
			log.debug("find by example successful, result size: " + results.size());
			
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			
			throw re;
		}
	}
	
}
