package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entidades.Aulasrecursos;
import entidades.AulasrecursosId;
import sessionfactory.MydbHibernateSessionFactory;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Aulasrecursos.
 * @see entidadesDAO.Aulasrecursos
 * @author Hibernate Tools
 */
public class AulasrecursosHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(AulasrecursosHome.class);
	
	public void persist(Aulasrecursos transientInstance) {
		Session session = this.getSession();
		
		log.debug("persisting Aulasrecursos instance");
		
		try {
			session.persist(transientInstance);
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			
			throw re;
		}
	}

	public void attachDirty(Aulasrecursos instance) {
		Session session = this.getSession();
		
		log.debug("attaching dirty Aulasrecursos instance");
		
		try {
			session.saveOrUpdate(instance);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void attachClean(Aulasrecursos instance) {
		Session session = this.getSession();
		
		log.debug("attaching clean Aulasrecursos instance");
		
		try {
			session.lock(instance, LockMode.NONE);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void delete(Aulasrecursos persistentInstance) {
		Session session = this.getSession();
		
		log.debug("deleting Aulasrecursos instance");
		
		try {
			session.delete(persistentInstance);
			
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			
			throw re;
		}
	}

	public Aulasrecursos merge(Aulasrecursos detachedInstance) {
		Session session = this.getSession();
		
		log.debug("merging Aulasrecursos instance");
		
		try {
			Aulasrecursos result = (Aulasrecursos) session.merge(detachedInstance);
			
			log.debug("merge successful");
			
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			
			throw re;
		}
	}

	public Aulasrecursos findById(AulasrecursosId id) {
		Session session = this.getSession();
		
		log.debug("getting Aulasrecursos instance with id: " + id);
		
		try {
			Aulasrecursos instance = (Aulasrecursos) session
					.get("entidadesDAO.Aulasrecursos", id);
			
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

	public List<Aulasrecursos> findByExample(Aulasrecursos instance) {
		Session session = this.getSession();
		
		log.debug("finding Aulasrecursos instance by example");
		
		try {
			List<Aulasrecursos> results = session.createCriteria("entidadesDAO.Aulasrecursos").add(create(instance)).list();
			
			log.debug("find by example successful, result size: " + results.size());
			
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			
			throw re;
		}
	}
}
