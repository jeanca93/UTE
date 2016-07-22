package entidadesDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import entidades.Opciones;

/**
*
* @author USUARIO
*/
public class OpcionesHomeExt extends OpcionesHome{

    public OpcionesHomeExt() {
        super();
        
    }
    
    public List<Opciones> listMenus(String usuario, Boolean raiz, Integer idOpcion){
    	Session session = this.getSession();
    	List<Opciones> listResulst = new ArrayList<Opciones>();
    	
    	try{
    		StringBuffer sbquery = new StringBuffer();        	
            sbquery.append("select distinct op from Opciones op, Usuarios u, Permisosperfiles pp, Arbolopciones ao");
            sbquery.append(" where pp.id.perfilesusuario = u.perfilesusuario");
            sbquery.append("	and pp.id.permisos = op.permisos");
            sbquery.append("	and ao.id.hijo = op.idOpcion");
            sbquery.append("	and u.usuario=:usuario and u.estado = 'A' and pp.estado = 'A'");
            //sbquery.append("	and ao.id.profundidad = 0");
            
            if (raiz)
            	sbquery.append("	and op.opcionContenedora is null");
        	
            if (idOpcion != null)
            	sbquery.append("	and op.idOpcion = " + idOpcion);
            
            sbquery.append(" order by op.orden asc");
    		
            Query query = session.createQuery(sbquery.toString());
            query.setString("usuario", usuario);
            
            listResulst = query.list();
    	}catch(RuntimeException re){
    		throw re;
    	}
        
		return listResulst;	
    }
    
    public Integer conteoOpciones(String usuario, Integer padre){
    	Session session = this.getSession();
    	int conteo = 0;
    	
    	try{
    		StringBuffer sbquery = new StringBuffer();        	
            sbquery.append("select count(op.idOpcion) from Opciones op, Usuarios u, Permisosperfiles pp, Arbolopciones ao");
            sbquery.append(" where pp.id.perfilesusuario = u.perfilesusuario");
            sbquery.append("	and pp.id.permisos = op.permisos");
            sbquery.append("	and ao.id.hijo = op.idOpcion");
            sbquery.append("	and u.usuario=:usuario and u.estado = 'A' and pp.estado = 'A'");
        	
            if (padre != null)
            	sbquery.append("	and ao.id.padre = " + padre);
    		
            Query query = session.createQuery(sbquery.toString());
            query.setString("usuario", usuario);
            
            conteo = Integer.parseInt(query.uniqueResult().toString());
    	}catch(RuntimeException re){
    		throw re;
    	}
        
    	return conteo;
    }
    
    public Integer verificaUltimo(String usuario, Integer padre){
    	Session session = this.getSession();
    	Integer ultimo = 0;
    	
    	try{
    		StringBuffer sbquery = new StringBuffer();
            sbquery.append("select max(op.idOpcion) from Opciones op, Usuarios u, Permisosperfiles pp, Arbolopciones ao");
            sbquery.append(" where pp.id.perfilesusuario = u.perfilesusuario");
            sbquery.append("	and pp.id.permisos = op.permisos");
            sbquery.append("	and ao.id.hijo = op.idOpcion");
            sbquery.append("	and u.usuario=:usuario and u.estado = 'A' and pp.estado = 'A'");
        	
            if (padre != null)
            	sbquery.append("	and op.opcionContenedora = " + padre);
            		
            Query query = session.createQuery(sbquery.toString());
            query.setString("usuario", usuario);
            
            ultimo = (query.uniqueResult() != null?Integer.parseInt(query.uniqueResult().toString()):0);
    	}catch(RuntimeException re){
    		throw re;
    	}
        
    	return ultimo;
    }
    
    public Integer opcionPadreInicial(Integer hijo){
    	Session session = this.getSession();
    	Integer inicial = 0;
    	
    	try{
    		StringBuffer sbquery = new StringBuffer();        	
            sbquery.append("select min(ao.id.padre) from Arbolopciones ao where ao.id.hijo = " + hijo);
            
            Query query = session.createQuery(sbquery.toString());
            
            inicial = (query.uniqueResult() != null?Integer.parseInt(query.uniqueResult().toString()):0);
    	}catch(RuntimeException re){
    		throw re;
    	}
        
    	return inicial;
    }
}
