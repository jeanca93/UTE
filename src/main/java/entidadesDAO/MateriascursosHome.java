package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import basehibernate.MydbBaseHibernateDAO;
import entidades.Materiascursos;
import entidades.MateriascursosId;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Materiascursos.
 * @see entidadesDAO.Materiascursos
 * @author Hibernate Tools
 */
public class MateriascursosHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(MateriascursosHome.class);

	public void persist(Materiascursos transientInstance) {
		Session session = this.getSession();
		
		log.debug("persisting Materiascursos instance");
		
		try {
			session.persist(transientInstance);
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			
			throw re;
		}
	}

	public void attachDirty(Materiascursos instance) {
		Session session = this.getSession();
		
		log.debug("attaching dirty Materiascursos instance");
		
		try {
			session.saveOrUpdate(instance);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}
	
	public void save(Materiascursos instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("saving Materiascursos instance");
		
		try {
			tx = session.beginTransaction();
			
			session.save(instance);
			
			log.debug("save successful");
			
			tx.commit();
		} catch (RuntimeException re) {
			tx.rollback();
			
			log.error("save failed", re);
			
			throw re;
		}
	}
	
	public void update(Materiascursos instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("updating Materiascursos instance");
		
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

	public void attachClean(Materiascursos instance) {
		Session session = this.getSession();
		
		log.debug("attaching clean Materiascursos instance");
		
		try {
			session.lock(instance, LockMode.NONE);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void delete(Materiascursos persistentInstance) {
		Session session = this.getSession();
		
		log.debug("deleting Materiascursos instance");
		
		try {
			session.delete(persistentInstance);
			
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			
			throw re;
		}
	}

	public Materiascursos merge(Materiascursos detachedInstance) {
		Session session = this.getSession();
		
		log.debug("merging Materiascursos instance");
		
		try {
			Materiascursos result = (Materiascursos) session.merge(detachedInstance);
			
			log.debug("merge successful");
			
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			
			throw re;
		}
	}

	public Materiascursos findById(MateriascursosId id) {
		Session session = this.getSession();
		
		log.debug("getting Materiascursos instance with id: " + id);
		
		try {
			Materiascursos instance = (Materiascursos) session
					.get("entidadesDAO.Materiascursos", id);
			
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

	public List<Materiascursos> findByExample(Materiascursos instance) {
		Session session = this.getSession();
		
		log.debug("finding Materiascursos instance by example");
		
		try {
			List<Materiascursos> results = (List<Materiascursos>) session.createCriteria("entidadesDAO.Materiascursos").add(create(instance)).list();
			
			log.debug("find by example successful, result size: " + results.size());
			
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			
			throw re;
		}
	}
}
