package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entidades.Permisosperfiles;
import entidades.PermisosperfilesId;
import sessionfactory.MydbHibernateSessionFactory;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Permisosperfiles.
 * @see entidadesDAO.Permisosperfiles
 * @author Hibernate Tools
 */
public class PermisosperfilesHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(PermisosperfilesHome.class);
	
	public void persist(Permisosperfiles transientInstance) {
		Session session = this.getSession();
		
		log.debug("persisting Permisosperfiles instance");
		
		try {
			session.persist(transientInstance);
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			
			throw re;
		}
	}

	public void attachDirty(Permisosperfiles instance) {
		Session session = this.getSession();
		
		log.debug("attaching dirty Permisosperfiles instance");
		
		try {
			session.saveOrUpdate(instance);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void attachClean(Permisosperfiles instance) {
		Session session = this.getSession();
		
		log.debug("attaching clean Permisosperfiles instance");
		
		try {
			session.lock(instance, LockMode.NONE);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void delete(Permisosperfiles persistentInstance) {
		Session session = this.getSession();
		
		log.debug("deleting Permisosperfiles instance");
		
		try {
			session.delete(persistentInstance);
			
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			
			throw re;
		}
	}

	public Permisosperfiles merge(Permisosperfiles detachedInstance) {
		Session session = this.getSession();
		
		log.debug("merging Permisosperfiles instance");
		
		try {
			Permisosperfiles result = (Permisosperfiles) session.merge(detachedInstance);
			
			log.debug("merge successful");
			
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			
			throw re;
		}
	}

	public Permisosperfiles findById(PermisosperfilesId id) {
		Session session = this.getSession();
		
		log.debug("getting Permisosperfiles instance with id: " + id);
		
		try {
			Permisosperfiles instance = (Permisosperfiles) session
					.get("entidadesDAO.Permisosperfiles", id);
			
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

	public List<Permisosperfiles> findByExample(Permisosperfiles instance) {
		Session session = this.getSession();
		
		log.debug("finding Permisosperfiles instance by example");
		
		try {
			List<Permisosperfiles> results = (List<Permisosperfiles>) session.createCriteria("entidadesDAO.Permisosperfiles").add(create(instance)).list();
			
			log.debug("find by example successful, result size: " + results.size());
			
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			
			throw re;
		}
	}
}
