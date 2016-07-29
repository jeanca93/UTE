package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;

import basehibernate.MydbBaseHibernateDAO;
import entidades.Horas;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Horas.
 * @see entidadesDAO.Horas
 * @author Hibernate Tools
 */
public class HorasHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(HorasHome.class);

	public void persist(Horas transientInstance) {
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

	public void attachDirty(Horas instance) {
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

	public void attachClean(Horas instance) {
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

	public void delete(Horas persistentInstance) {
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

	public Horas merge(Horas detachedInstance) {
		Session session = this.getSession();
		
		log.debug("merging Horas instance");
		
		try {
			Horas result = (Horas) session.merge(detachedInstance);
			
			log.debug("merge successful");
			
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			
			throw re;
		}
	}

	public Horas findById(java.lang.Integer id) {
		Session session = this.getSession();
		
		log.debug("getting Horas instance with id: " + id);
		
		try {
			Horas instance = (Horas) session.get("entidadesDAO.Horas", id);
			
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

	public List<Horas> findByExample(Horas instance) {
		Session session = this.getSession();
		
		log.debug("finding Horas instance by example");
		
		try {
			List<Horas> results = (List<Horas>) session.createCriteria("entidadesDAO.Horas")
					.add(create(instance)).list();
			
			log.debug("find by example successful, result size: " + results.size());
			
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			
			throw re;
		}
	}
}
