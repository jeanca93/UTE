package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
<<<<<<< HEAD
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entidades.Aulas;
=======

import basehibernate.MydbBaseHibernateDAO;
>>>>>>> 483854328becc2494595525082c41ae851fdd760
import entidades.Cursos;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Cursos.
 * @see entidadesDAO.Cursos
 * @author Hibernate Tools
 */
public class CursosHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(CursosHome.class);
	
	public void persist(Cursos transientInstance) {
		Session session = this.getSession();
		
		log.debug("persisting Cursos instance");
		
		try {
			session.persist(transientInstance);
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			
			throw re;
		}
	}

	public void attachDirty(Cursos instance) {
		Session session = this.getSession();
		
		log.debug("attaching dirty Cursos instance");
		
		try {
			session.saveOrUpdate(instance);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void save(Cursos instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("saving Cursos instance");
		
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
	
	public void update(Cursos instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("updating Cursos instance");
		
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

	public void attachClean(Cursos instance) {
		Session session = this.getSession();
		
		log.debug("attaching clean Cursos instance");
		
		try {
			session.lock(instance, LockMode.NONE);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void delete(Cursos persistentInstance) {
		Session session = this.getSession();
		
		Transaction tx = null;
		
		log.debug("deleting Cursos instance");
		
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

	public Cursos merge(Cursos detachedInstance) {
		Session session = this.getSession();
		
		log.debug("merging Cursos instance");
		
		try {
			Cursos result = (Cursos) session.merge(detachedInstance);
			
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			
			throw re;
		}
	}

	public Cursos findById(java.lang.Integer id) {
		Session session = this.getSession();
		
		log.debug("getting Cursos instance with id: " + id);
		
		try {
			Cursos instance = (Cursos) session.get("entidadesDAO.Cursos", id);
			
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

	public List<Cursos> findByExample(Cursos instance) {
		Session session = this.getSession();
		
		log.debug("finding Cursos instance by example");
		
		try {
			List<Cursos> results = (List<Cursos>) session
					.createCriteria("entidadesDAO.Cursos").add(create(instance)).list();
			
			log.debug("find by example successful, result size: " + results.size());
			
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			
			throw re;
		}
	}
}
