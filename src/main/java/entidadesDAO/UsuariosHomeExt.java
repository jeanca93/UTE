package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import entidades.Usuarios;

public class UsuariosHomeExt extends UsuariosHome{
    public UsuariosHomeExt() {
        super();
        
    }
    
    public Integer validaUsuario(String user, String password){
    	Session session = null;
    	Integer existe;
    	
    	try{
    		this.getSession().clear();
    		session = this.getSession();
    		
    		StringBuffer sbquery = new StringBuffer();
    		sbquery.append("select valida_login('" + user + "','" + password + "')");
        	    		
            Query query = session.createSQLQuery(sbquery.toString());
            
            existe = (Integer) query.uniqueResult();
    	}catch(RuntimeException re){
    		
    		existe = 0;
    		
    		throw re;	
    	}
        
    	return existe;
    }
    
    public ArrayList<Usuarios> listUsuariosActivos() {
    	Session session = null;
    	ArrayList<Usuarios> results;
		
    	try {
    		this.getSession().clear();
    		session = this.getSession();
    		
			results = (ArrayList<Usuarios>) session.createCriteria(Usuarios.class).add(Restrictions.eq("estado", 'A')).list();			
		} catch (RuntimeException re) {
			
			throw re;
		}
		
		return results;
	}
    
    public Boolean crearNuevoUsuario(Usuarios user, String clave){
    	Session session = null;
    	Transaction tx = null;
    	Boolean flag = false;
    	
    	try{
    		attachDirty(user);
    		
    		this.getSession().clear();
    		session = this.getSession();
    		
    		tx = session.beginTransaction();
    		
    		StringBuffer sbquery = new StringBuffer();
    		sbquery.append("call registra_clave('" + user.getUsuario() + "','" + clave + "')");
        	
            Query query = session.createSQLQuery(sbquery.toString());
            
            flag = (query.executeUpdate() ==1?true:false);
            
            tx.commit();
    	}catch(RuntimeException re){
    		tx.rollback();
    		
    		throw re;
    	}
    	
    	return flag;
    }
}
