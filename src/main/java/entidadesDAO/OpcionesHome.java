package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entidades.Opciones;
import entidadesDAO.MydbBaseHibernateDAO;
import sessionfactory.MydbHibernateSessionFactory;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Opciones.
 * @see entidadesDAO.Opciones
 * @author Hibernate Tools
 */
public class OpcionesHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(OpcionesHome.class);

	public void persist(Opciones transientInstance) {
		Session session = this.getSession();
		
		log.debug("persisting Opciones instance");
		
		try {
			session.persist(transientInstance);
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			
			throw re;
		}
	}

	public void attachDirty(Opciones instance) {
		Session session = this.getSession();
		
		log.debug("attaching dirty Opciones instance");
		
		try {
			session.saveOrUpdate(instance);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void attachClean(Opciones instance) {
		Session session = this.getSession();
		
		log.debug("attaching clean Opciones instance");
		
		try {
			session.lock(instance, LockMode.NONE);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void delete(Opciones persistentInstance) {
		Session session = this.getSession();
		
		log.debug("deleting Opciones instance");
		
		try {
			session.delete(persistentInstance);
			
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			
			throw re;
		}
	}

	public Opciones merge(Opciones detachedInstance) {
		Session session = this.getSession();
		
		log.debug("merging Opciones instance");
		
		try {
			Opciones result = (Opciones) session.merge(detachedInstance);
			
			log.debug("merge successful");
			
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			
			throw re;
		}
	}

	public Opciones findById(java.lang.Integer id) {
		Session session = this.getSession();
		
		log.debug("getting Opciones instance with id: " + id);
		
		try {
			Opciones instance = (Opciones) session.get("entidadesDAO.Opciones", id);
			
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

	public List<Opciones> findByExample(Opciones instance) {
		Session session = this.getSession();
		
		log.debug("finding Opciones instance by example");
		
		try {
			List<Opciones> results = (List<Opciones>) session.createCriteria("entidadesDAO.Opciones").add(create(instance)).list();
			
			log.debug("find by example successful, result size: " + results.size());
			
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			
			throw re;
		}
	}
}
