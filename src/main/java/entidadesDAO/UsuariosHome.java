package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entidades.Usuarios;
import sessionfactory.MydbHibernateSessionFactory;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Usuarios.
 * @see entidadesDAO.Usuarios
 * @author Hibernate Tools
 */
public class UsuariosHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(UsuariosHome.class);

	public void persist(Usuarios transientInstance) {
		Session session = this.getSession();
		
		log.debug("persisting Usuarios instance");
		
		try {
			session.persist(transientInstance);
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			
			throw re;
		}
	}

	public void attachDirty(Usuarios instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("attaching dirty Usuarios instance");
		
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
	
	public void save(Usuarios instance) {
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
	
	public void update(Usuarios instance) {
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

	public void attachClean(Usuarios instance) {
		Session session = this.getSession();
		
		log.debug("attaching clean Usuarios instance");
		
		try {
			session.lock(instance, LockMode.NONE);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void delete(Usuarios persistentInstance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("deleting Usuarios instance");
		
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

	public Usuarios merge(Usuarios detachedInstance) {
		Session session = this.getSession();
		
		log.debug("merging Usuarios instance");
		
		try {
			Usuarios result = (Usuarios) session.merge(detachedInstance);
			
			log.debug("merge successful");
			
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			
			throw re;
		}
	}

	public Usuarios findById(java.lang.Integer id) {
		Session session = this.getSession();
		
		log.debug("getting Usuarios instance with id: " + id);
		
		try {
			Usuarios instance = (Usuarios) session.get("entidades.Usuarios", id);
			
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

	public List<Usuarios> findByExample(Usuarios instance) {
		Session session = this.getSession();
		
		log.debug("finding Usuarios instance by example");
		
		try {
			List<Usuarios> results = (List<Usuarios>) session.createCriteria("entidades.Usuarios").add(create(instance)).list();
			
			log.debug("find by example successful, result size: " + results.size());
			
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			
			throw re;
		}
	}
}
