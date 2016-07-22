package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entidades.Profesores;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Profesores.
 * @see entidadesDAO.Profesores
 * @author Hibernate Tools
 */
public class ProfesoresHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(ProfesoresHome.class);

	public void persist(Profesores transientInstance) {
		Session session = this.getSession();
		
		log.debug("persisting Profesores instance");
		
		try {
			session.persist(transientInstance);
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			
			throw re;
		}
	}

	public void attachDirty(Profesores instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("attaching dirty Profesores instance");
		
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
	
	public void save(Profesores instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("attaching dirty Usuarios instance");
		
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
	
	public void update(Profesores instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("attaching dirty Usuarios instance");
		
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

	public void attachClean(Profesores instance) {
		Session session = this.getSession();
		
		log.debug("attaching clean Profesores instance");
		
		try {
			session.lock(instance, LockMode.NONE);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void delete(Profesores persistentInstance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("deleting Profesores instance");
		
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

	public Profesores merge(Profesores detachedInstance) {
		Session session = this.getSession();
		
		log.debug("merging Profesores instance");
		
		try {
			Profesores result = (Profesores)session.merge(detachedInstance);
			
			log.debug("merge successful");
			
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			
			throw re;
		}
	}

	public Profesores findById(java.lang.Integer id) {
		Session session = this.getSession();
		
		log.debug("getting Profesores instance with id: " + id);
		
		try {
			Profesores instance = (Profesores)session.get(Profesores.class, id);
			
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

	public List<Profesores> findByExample(Profesores instance) {
		Session session = this.getSession();
		
		log.debug("finding Profesores instance by example");
		
		try {
			List<Profesores> results = (List<Profesores>) session.createCriteria("entidadesDAO.Profesores").add(create(instance)).list();
			
			log.debug("find by example successful, result size: " + results.size());
			
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			
			throw re;
		}
	}
}
