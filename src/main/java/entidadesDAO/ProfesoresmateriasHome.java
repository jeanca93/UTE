package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

import entidades.Profesoresmaterias;
import entidades.ProfesoresmateriasId;
import sessionfactory.MydbHibernateSessionFactory;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Profesoresmaterias.
 * @see entidadesDAO.Profesoresmaterias
 * @author Hibernate Tools
 */
public class ProfesoresmateriasHome extends MydbBaseHibernateDAO {

	private static final Log log = LogFactory.getLog(ProfesoresmateriasHome.class);

	private final SessionFactory sessionFactory = MydbHibernateSessionFactory.getSessionFactory();

	public void persist(Profesoresmaterias transientInstance) {
		log.debug("persisting Profesoresmaterias instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Profesoresmaterias instance) {
		log.debug("attaching dirty Profesoresmaterias instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Profesoresmaterias instance) {
		log.debug("attaching clean Profesoresmaterias instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Profesoresmaterias persistentInstance) {
		log.debug("deleting Profesoresmaterias instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Profesoresmaterias merge(Profesoresmaterias detachedInstance) {
		log.debug("merging Profesoresmaterias instance");
		try {
			Profesoresmaterias result = (Profesoresmaterias) sessionFactory.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Profesoresmaterias findById(ProfesoresmateriasId id) {
		log.debug("getting Profesoresmaterias instance with id: " + id);
		try {
			Profesoresmaterias instance = (Profesoresmaterias) sessionFactory.getCurrentSession()
					.get("entidadesDAO.Profesoresmaterias", id);
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

	public List<Profesoresmaterias> findByExample(Profesoresmaterias instance) {
		log.debug("finding Profesoresmaterias instance by example");
		try {
			List<Profesoresmaterias> results = (List<Profesoresmaterias>) sessionFactory.getCurrentSession()
					.createCriteria("entidadesDAO.Profesoresmaterias").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
