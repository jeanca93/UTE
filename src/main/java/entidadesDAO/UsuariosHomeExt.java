package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import entidades.Usuarios;

public class UsuariosHomeExt extends UsuariosHome{
	
    public Integer validaUsuario(String user, String password){
    	Session session = this.getSession();
    	Integer existe = 0;
    	
    	try{
    		StringBuffer sbquery = new StringBuffer();
    		sbquery.append("select valida_login('" + user + "','" + password + "')");
        	    		
            Query query = session.createSQLQuery(sbquery.toString());
            
            existe = (Integer) query.uniqueResult();
    	}catch(RuntimeException re){
    		throw re;	
    	}
        
    	return existe;
    }
    
    public Long existeUsuario(String user){
    	Session session = this.getSession();
    	Long existe = 0L;
    	
    	try{
    		StringBuffer sbquery = new StringBuffer();
    		sbquery.append("select count(u.idUsuario) from Usuarios u where u.usuario=:usuario");
        	    		
            Query query = session.createQuery(sbquery.toString());
            query.setString("usuario", user);
            
            existe = (Long) query.uniqueResult();
    	}catch(RuntimeException re){
    		throw re;	
    	}
        
    	return existe;
    }
    
    public ArrayList<Usuarios> listUsuariosActivos() {
    	Session session = this.getSession();
    	ArrayList<Usuarios> results = new ArrayList<Usuarios>();
		
    	try {
			results = (ArrayList<Usuarios>) session.createCriteria(Usuarios.class)
						.addOrder(Order.asc("idUsuario"))
						.list();			
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}
    
    public Boolean crearNuevoUsuario(Usuarios user, String clave){
    	Session session = this.getSession();
    	session.flush();
    	Boolean flag = false;
    	
    	try{
    		save(user);
    		
    		registrarClave(user.getUsuario(), clave, session);
    	}catch(RuntimeException re){    		
    		throw re;
    	}
    	
    	return flag;
    }
    
    public Boolean registrarClave(String usuario, String clave, Session sessionCrerUsuario){
    	Session session = (sessionCrerUsuario == null?this.getSession():sessionCrerUsuario);
    	Transaction tx = null;
    	Boolean flag = false;
    	
    	try{    		
    		tx = session.beginTransaction();
    		
    		StringBuffer sbquery = new StringBuffer();
    		sbquery.append("call registra_clave('" + usuario + "','" + clave + "')");
        	
            Query query = session.createSQLQuery(sbquery.toString());
            
            flag = (query.executeUpdate() ==1?true:false);
            
            tx.commit();
    	}catch(RuntimeException re){
    		tx.rollback();
    		
    		throw re;
    	}
    	
    	return flag;
    }
    
    public String correoUsuario(Integer usuario){
    	Session session = this.getSession();
    	String correo = "";
    	
    	try{
    		StringBuffer sbquery = new StringBuffer();
            sbquery.append("select u.correo from Usuarios u where u.idUsuario=:usuario");
            		
            Query query = session.createQuery(sbquery.toString());
            query.setInteger("usuario", usuario);
            
            correo = (query.uniqueResult() != null?query.uniqueResult().toString():"");
    	}catch(RuntimeException re){
    		throw re;
    	}
        
    	return correo;
    }
    
}
