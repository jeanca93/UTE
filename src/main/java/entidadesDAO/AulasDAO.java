/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesDAO;

import entidades.Aulas;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import basehibernate.MydbBaseHibernateDAO;
import sessionfactory.MydbHibernateSessionFactory;

/**
 *
 * @author USUARIO
 */
public class AulasDAO extends MydbBaseHibernateDAO{

    //private static final Log log = LogFactory.getLog(UsuarioDAO.class);
      // property constants

    public void save(Aulas transientInstance) {
        //log.debug("saving Usuario instance");
        try {
            MydbHibernateSessionFactory.getSession().save(transientInstance);
            //log.debug("save successful");
        } catch (RuntimeException re) {
            //log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Aulas persistentInstance) {
        //log.debug("deleting Aulas instance");
        try {
            MydbHibernateSessionFactory.getSession().delete(persistentInstance);
            //log.debug("delete successful");
        } catch (RuntimeException re) {
            //log.error("delete failed", re);
            throw re;
        }
    }

    public void update(Aulas persistentInstance) {
        //log.debug("update Aulas instance");
        try {
            MydbHibernateSessionFactory.getSession().update(persistentInstance);
            //log.debug("update successful");
        } catch (RuntimeException re) {
            //log.error("update failed", re);
            throw re;
        }
    }

    public Aulas findById(Integer id) {
        //log.debug("getting Aulas instance with id: " + id);
        try {
            Aulas instance = (Aulas) MydbHibernateSessionFactory.getSession().get(
                    "entidades.Aulas", id);
            return instance;
        } catch (RuntimeException re) {
            //log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Aulas instance) {
       // log.debug("finding Usuario instance by example");
        try {
            List results = MydbHibernateSessionFactory.getSession().createCriteria(
                    "parquil.entidades.Aulas")
                    .add(Example.create(instance)).list();
            //log.debug("find by example successful, result size: "
                   // + results.size());
            return results;
        } catch (RuntimeException re) {
            //log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        //log.debug("finding Usuario instance with property: " + propertyName
         //       + ", value: " + value);
        try {
            String queryString = "from Aulas as model where model."
                    + propertyName + "= ?";
            Query queryObject = MydbHibernateSessionFactory.getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            //log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findAll() {
        //log.debug("finding all Usuario instances");
        try {
            String queryString = "from Aulas";
            Query queryObject = MydbHibernateSessionFactory.getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            //log.error("find all failed", re);
            throw re;
        }
    }

    public Aulas merge(Aulas detachedInstance) {
        //log.debug("merging Aulas instance");
        try {
            Aulas result = (Aulas) MydbHibernateSessionFactory.getSession().merge(detachedInstance);
            //log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
           // log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Aulas instance) {
        //log.debug("attaching dirty Usuario instance");
        try {
            MydbHibernateSessionFactory.getSession().saveOrUpdate(instance);
            //log.debug("attach successful");
        } catch (RuntimeException re) {
            //log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Aulas instance) {
        //log.debug("attaching clean Usuario instance");
        try {
            MydbHibernateSessionFactory.getSession().lock(instance, LockMode.NONE);
            //log.debug("attach successful");
        } catch (RuntimeException re) {
            //log.error("attach failed", re);
            throw re;
        }
    }

}
