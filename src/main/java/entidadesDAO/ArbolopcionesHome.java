package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;

import basehibernate.MydbBaseHibernateDAO;
import entidades.Arbolopciones;
import entidades.ArbolopcionesId;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Arbolopciones.
 * @see entidadesDAO.Arbolopciones
 * @author Hibernate Tools
 */
public class ArbolopcionesHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(ArbolopcionesHome.class);

	public void persist(Arbolopciones transientInstance) {
		Session session = this.getSession();
		
		log.debug("persisting Arbolopciones instance");
		
		try {
			session.persist(transientInstance);
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			
			throw re;
		}
	}

	public void attachDirty(Arbolopciones instance) {
		Session session = this.getSession();
		
		log.debug("attaching dirty Arbolopciones instance");
		
		try {
			session.saveOrUpdate(instance);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void attachClean(Arbolopciones instance) {
		Session session = this.getSession();
		
		log.debug("attaching clean Arbolopciones instance");
		
		try {
			session.lock(instance, LockMode.NONE);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void delete(Arbolopciones persistentInstance) {
		Session session = this.getSession();
		
		log.debug("deleting Arbolopciones instance");
		
		try {
			session.delete(persistentInstance);
			
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			
			throw re;
		}
	}

	public Arbolopciones merge(Arbolopciones detachedInstance) {
		Session session = this.getSession();
		
		log.debug("merging Arbolopciones instance");
		
		try {
			Arbolopciones result = (Arbolopciones) session.merge(detachedInstance);
			
			log.debug("merge successful");
			
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			
			throw re;
		}
	}

	public Arbolopciones findById(ArbolopcionesId id) {
		Session session = this.getSession();
		
		log.debug("getting Arbolopciones instance with id: " + id);
		
		try {
			Arbolopciones instance = (Arbolopciones) session
					.get("entidadesDAO.Arbolopciones", id);
			
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

	public List<Arbolopciones> findByExample(Arbolopciones instance) {
		Session session = this.getSession();
		
		log.debug("finding Arbolopciones instance by example");
		
		try {
			List<Arbolopciones> results = (List<Arbolopciones>) session.createCriteria("entidadesDAO.Arbolopciones").add(create(instance)).list();
			
			log.debug("find by example successful, result size: " + results.size());
			
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			
			throw re;
		}
	}
}
