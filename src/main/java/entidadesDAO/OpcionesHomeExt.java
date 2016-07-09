package entidadesDAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import entidades.Opciones;

/**
*
* @author USUARIO
*/
public class OpcionesHomeExt extends OpcionesHome{
	private Session session;

    public OpcionesHomeExt() {

        super();

        session = getSession();
        
    }
    
    public List<Opciones> listMenus(String usuario, Boolean raiz, Integer idOpcion){
    	StringBuffer sbquery = new StringBuffer();
    	
        sbquery.append("select distinct op from Opciones op, Usuarios u, Permisosperfiles pp, Arbolopciones ao");
        sbquery.append(" where pp.id.idPerfil = u.perfilesusuario.idPerfilUsuario");
        sbquery.append("	and pp.id.idPermiso = op.permisos.idPermiso");
        sbquery.append("	and ao.id.hijo = op.opcionContenedora.idOpcion");
        sbquery.append("	and u.usuario=:usuario and u.estado = 'A' and pp.estado = 'A'");
        //sbquery.append("	and ao.id.profundidad = 0");
        
        if (raiz)
        	sbquery.append("	and op.opcionContenedora is null");
    	
        if (idOpcion != null)
        	sbquery.append("	and op.opcionContenedora.idOpcion =" + idOpcion);
        
        sbquery.append(" order by op.orden asc");
        	
        Query query = session.createQuery(sbquery.toString());
        query.setString("usuario", usuario);
        
        List<Opciones> listResulst = query.list();
        
		return listResulst;	
    }
    
    public Integer conteoOpciones(String usuario, Integer padre){
    	StringBuffer sbquery = new StringBuffer();
    	
        sbquery.append("select count(op.idOpcion) from Opciones op, Usuarios u, Permisosperfiles pp, Arbolopciones ao");
        sbquery.append(" where pp.id.idPerfil = u.perfilesusuario.idPerfilUsuario");
        sbquery.append("	and pp.id.idPermiso = op.permisos.idPermiso");
        sbquery.append("	and ao.id.hijo = op.opcionContenedora.idOpcion");
        sbquery.append("	and u.usuario=:usuario and u.estado = 'A' and pp.estado = 'A'");
    	
        if (padre != null)
        	sbquery.append("	and ao.id.padre =" + padre);
        	
        Query query = session.createQuery(sbquery.toString());
        query.setString("usuario", usuario);
        
        int conteo = Integer.parseInt(query.uniqueResult().toString());
        
    	return conteo;
    }
    
    public Integer verificaUltimo(String usuario, Integer padre){
    	StringBuffer sbquery = new StringBuffer();
    	
        sbquery.append("select max(op.idOpcion) from Opciones op, Usuarios u, Permisosperfiles pp, Arbolopciones ao");
        sbquery.append(" where pp.id.idPerfil = u.perfilesusuario.idPerfilUsuario");
        sbquery.append("	and pp.id.idPermiso = op.permisos.idPermiso");
        sbquery.append("	and ao.id.hijo = op.opcionContenedora.idOpcion");
        sbquery.append("	and u.usuario=:usuario and u.estado = 'A' and pp.estado = 'A'");
    	
        if (padre != null)
        	sbquery.append("	and op.opcionContenedora =" + padre);
        	
        Query query = session.createQuery(sbquery.toString());
        query.setString("usuario", usuario);
        
        Integer ultimo = Integer.parseInt(query.uniqueResult().toString());
        
    	return ultimo;
    }
    
    public Integer opcionPadreInicial(Integer hijo){
    	StringBuffer sbquery = new StringBuffer();
    	
        sbquery.append("select min(ao.id.padre) from Arbolopciones ao where ao.id.hijo = " + hijo);
        	
        Query query = session.createQuery(sbquery.toString());
        
        Integer inicial = Integer.parseInt(query.uniqueResult().toString());
        
    	return inicial;
    }
}
