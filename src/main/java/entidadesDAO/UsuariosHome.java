package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
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

	private final SessionFactory sessionFactory = MydbHibernateSessionFactory.getSessionFactory();

	public void persist(Usuarios transientInstance) {
		log.debug("persisting Usuarios instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Usuarios instance) {
		Transaction tx = null;
		
		log.debug("attaching dirty Usuarios instance");
		try {
			tx = sessionFactory.getCurrentSession().beginTransaction();
			
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			
			log.debug("attach successful");
			
			tx.commit();
		} catch (RuntimeException re) {
			tx.rollback();
			
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void attachClean(Usuarios instance) {
		log.debug("attaching clean Usuarios instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Usuarios persistentInstance) {
		Transaction tx = null;
		
		log.debug("deleting Usuarios instance");
		try {
			tx = sessionFactory.getCurrentSession().beginTransaction();
			
			sessionFactory.getCurrentSession().delete(persistentInstance);
			
			log.debug("delete successful");
			
			tx.commit();
		} catch (RuntimeException re) {
			tx.rollback();
			
			log.error("delete failed", re);
			
			throw re;
		}
	}

	public Usuarios merge(Usuarios detachedInstance) {
		log.debug("merging Usuarios instance");
		try {
			Usuarios result = (Usuarios) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Usuarios findById(java.lang.Integer id) {
		log.debug("getting Usuarios instance with id: " + id);
		try {
			Usuarios instance = (Usuarios) sessionFactory.getCurrentSession().get("entidades.Usuarios", id);
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
		log.debug("finding Usuarios instance by example");
		try {
			List<Usuarios> results = (List<Usuarios>) sessionFactory.getCurrentSession()
					.createCriteria("entidades.Usuarios").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
