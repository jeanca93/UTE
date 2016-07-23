package entidadesDAO;
// Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entidades.Profesoresmaterias;
import entidades.ProfesoresmateriasId;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Profesoresmaterias.
 * @see entidadesDAO.Profesoresmaterias
 * @author Hibernate Tools
 */
public class ProfesoresmateriasHome extends MydbBaseHibernateDAO {

	private static final Log log = LogFactory.getLog(ProfesoresmateriasHome.class);
	
	public void persist(Profesoresmaterias transientInstance) {
		Session session = this.getSession();
		
		log.debug("persisting Profesoresmaterias instance");
		
		try {
			session.persist(transientInstance);
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			
			throw re;
		}
	}

	public void attachDirty(Profesoresmaterias instance) {
		Session session = this.getSession();
		
		log.debug("attaching dirty Profesoresmaterias instance");
		
		try {
			session.saveOrUpdate(instance);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}
	
	public void save(Profesoresmaterias instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("saving Profesoresmaterias Usuarios instance");
		
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
	
	public void update(Profesoresmaterias instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("updating Profesoresmaterias instance");
		
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

	public void attachClean(Profesoresmaterias instance) {
		Session session = this.getSession();
		
		log.debug("attaching clean Profesoresmaterias instance");
		
		try {
			session.lock(instance, LockMode.NONE);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void delete(Profesoresmaterias persistentInstance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("deleting Profesoresmaterias instance");
		
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

	public Profesoresmaterias merge(Profesoresmaterias detachedInstance) {
		Session session = this.getSession();
		
		log.debug("merging Profesoresmaterias instance");
		try {
			Profesoresmaterias result = (Profesoresmaterias) session.merge(detachedInstance);
			
			log.debug("merge successful");
			
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			
			throw re;
		}
	}

	public Profesoresmaterias findById(ProfesoresmateriasId id) {
		Session session = this.getSession();
		
		log.debug("getting Profesoresmaterias instance with id: " + id);
		
		try {
			Profesoresmaterias instance = (Profesoresmaterias) session
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
		Session session = this.getSession();
		
		log.debug("finding Profesoresmaterias instance by example");
		
		try {
			List<Profesoresmaterias> results = (List<Profesoresmaterias>) session.createCriteria("entidadesDAO.Profesoresmaterias").add(create(instance)).list();
			
			log.debug("find by example successful, result size: " + results.size());
			
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			
			throw re;
		}
	}
}
