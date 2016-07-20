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

import entidades.Profesores;
import sessionfactory.MydbHibernateSessionFactory;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Profesores.
 * @see entidadesDAO.Profesores
 * @author Hibernate Tools
 */
public class ProfesoresHome extends MydbBaseHibernateDAO{

	private static final Log log = LogFactory.getLog(ProfesoresHome.class);

<<<<<<< HEAD
	//private final SessionFactory sessionFactory = MydbHibernateSessionFactory.getSessionFactory();
	private final Session session = MydbHibernateSessionFactory.getSession();
	
	public void persist(Profesores transientInstance) {
		log.debug("persisting Profesores instance");
		try {
			//sessionFactory.getCurrentSession().persist(transientInstance);
=======
	private final Session session = MydbHibernateSessionFactory.getSession();

	public void persist(Profesores transientInstance) {
		log.debug("persisting Profesores instance");
		try {
>>>>>>> 6e494ce03107f883f67d1999dd4c7f91a6f2d17f
			session.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Profesores instance) {
		Transaction tx = null;
		
		log.debug("attaching dirty Profesores instance");
		try {
<<<<<<< HEAD
			//tx = sessionFactory.getCurrentSession().beginTransaction();
			tx = session.beginTransaction();
			//sessionFactory.getCurrentSession().saveOrUpdate(instance);
			session.saveOrUpdate(instance);
			
=======
			session.saveOrUpdate(instance);
>>>>>>> 6e494ce03107f883f67d1999dd4c7f91a6f2d17f
			log.debug("attach successful");
			
			tx.commit();
		} catch (RuntimeException re) {
			tx.rollback();
			
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Profesores instance) {
		log.debug("attaching clean Profesores instance");
		try {
<<<<<<< HEAD
			//sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			session.lock(instance, LockMode.NONE);
			
=======
			session.lock(instance, LockMode.NONE);
>>>>>>> 6e494ce03107f883f67d1999dd4c7f91a6f2d17f
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Profesores persistentInstance) {
		Transaction tx = null;
		
		log.debug("deleting Profesores instance");
		try {
<<<<<<< HEAD
			//tx = sessionFactory.getCurrentSession().beginTransaction();
			session.beginTransaction();
			
			//sessionFactory.getCurrentSession().delete(persistentInstance);
			session.delete(persistentInstance);
			
=======
			session.delete(persistentInstance);
>>>>>>> 6e494ce03107f883f67d1999dd4c7f91a6f2d17f
			log.debug("delete successful");
			
			tx.commit();
		} catch (RuntimeException re) {
			tx.rollback();
			
			log.error("delete failed", re);
			
			throw re;
		}
	}

	public Profesores merge(Profesores detachedInstance) {
		log.debug("merging Profesores instance");
		try {
<<<<<<< HEAD
			//Profesores result = (Profesores) sessionFactory.getCurrentSession().merge(detachedInstance);
			Profesores result = (Profesores)session.merge(detachedInstance);
			
=======
			Profesores result = (Profesores) session.merge(detachedInstance);
>>>>>>> 6e494ce03107f883f67d1999dd4c7f91a6f2d17f
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Profesores findById(java.lang.Integer id) {
		log.debug("getting Profesores instance with id: " + id);
		try {
<<<<<<< HEAD
			//Profesores instance = (Profesores) sessionFactory.getCurrentSession().get("entidadesDAO.Profesores", id);
			Profesores instance = (Profesores)session.get(Profesores.class, id);
			
=======
			Profesores instance = (Profesores) session.get("entidadesDAO.Profesores", id);
>>>>>>> 6e494ce03107f883f67d1999dd4c7f91a6f2d17f
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
		log.debug("finding Profesores instance by example");
		try {
<<<<<<< HEAD
			//List<Profesores> results = (List<Profesores>) sessionFactory.getCurrentSession()
			//		.createCriteria("entidadesDAO.Profesores").add(create(instance)).list();
			List<Profesores> results = (List<Profesores>) session.createCriteria("entidadesDAO.Profesores").add(create(instance)).list();
			
=======
			List<Profesores> results = (List<Profesores>) session.createCriteria("entidadesDAO.Profesores").add(create(instance)).list();
>>>>>>> 6e494ce03107f883f67d1999dd4c7f91a6f2d17f
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
