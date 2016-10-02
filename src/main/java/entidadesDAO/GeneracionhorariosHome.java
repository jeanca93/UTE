package entidadesDAO;
//Generated Jun 5, 2016 11:08:49 PM by Hibernate Tools 4.0.0.Final

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;

import basehibernate.MydbBaseHibernateDAO;
import entidades.Generacionhorarios;

/**
* Home object for domain model class Generacionhorarios.
* @see entidadesDAO.Generacionhorarios
* @author Hibernate Tools
*/
public class GeneracionhorariosHome extends MydbBaseHibernateDAO{
	private static final Log log = LogFactory.getLog(GeneracionhorariosHome.class);

	public void persist(Generacionhorarios transientInstance) {
		Session session = this.getSession();
		
		log.debug("persisting Generacionhorarios instance");
		
		try {
			session.persist(transientInstance);
			
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			
			throw re;
		}
	}
	
	public void save(Generacionhorarios instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("saving Generacionhorarios instance");
		
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
	
	public void update(Generacionhorarios instance) {
		Session session = this.getSession();
		Transaction tx = null;
		
		log.debug("updating Generacionhorarios instance");
		
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

	public void attachDirty(Generacionhorarios instance) {
		Session session = this.getSession();
		
		log.debug("attaching dirty Generacionhorarios instance");
		
		try {
			session.saveOrUpdate(instance);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void attachClean(Generacionhorarios instance) {
		Session session = this.getSession();
		
		log.debug("attaching clean Generacionhorarios instance");
		
		try {
			session.lock(instance, LockMode.NONE);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			
			throw re;
		}
	}

	public void delete(Generacionhorarios persistentInstance) {
		Session session = this.getSession();
		
		log.debug("deleting Generacionhorarios instance");
		
		try {
			session.delete(persistentInstance);
			
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			
			throw re;
		}
	}

	public Generacionhorarios merge(Generacionhorarios detachedInstance) {
		Session session = this.getSession();
		
		log.debug("merging Generacionhorarios instance");
		
		try {
			Generacionhorarios result = (Generacionhorarios) session.merge(detachedInstance);
			
			log.debug("merge successful");
			
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			
			throw re;
		}
	}

	public Generacionhorarios findById(java.lang.String id) {
		Session session = this.getSession();
		
		log.debug("getting Generacionhorarios instance with id: " + id);
		
		try {
			Generacionhorarios instance = (Generacionhorarios) session.get("entidades.Generacionhorarios", id);
			
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

	public List<Generacionhorarios> findByExample(Generacionhorarios instance) {
		Session session = this.getSession();
		
		log.debug("finding Generacionhorarios instance by example");
		
		try {
			List<Generacionhorarios> results = (List<Generacionhorarios>) session.createCriteria("entidades.Generacionhorarios")
					.add(create(instance)).list();
			
			log.debug("find by example successful, result size: " + results.size());
			
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			
			throw re;
		}
	}
}
