package entidadesDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import entidades.Permisosperfiles;

public class PermisosperfilesHomeExt extends PermisosperfilesHome{

	public PermisosperfilesHomeExt() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Permisosperfiles> listPermisosActivos() {
		Session session = this.getSession();
    	ArrayList<Permisosperfiles> results = new ArrayList<Permisosperfiles>();
		
    	try {
			results = (ArrayList<Permisosperfiles>) session.createCriteria(Permisosperfiles.class)
						.add(Restrictions.eq("estado", 'A'))
						.list();						
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}
	
	public boolean registrarPermisosPerfil(List<Permisosperfiles> listPermisosPerfil){
		Session session = this.getSession();
		Transaction tx = null;
    	boolean flag = false;
		
    	try {
    		tx = session.beginTransaction();
    		
    		session.createQuery("delete from Permisosperfiles pp where pp.id.perfilesusuario.idPerfilUsuario=:perfil")
    			.setInteger("perfil", listPermisosPerfil.get(0).getId().getPerfilesusuario().getIdPerfilUsuario())
    			.executeUpdate();
    		
    		tx.commit();
    		
    		for(Permisosperfiles permisosperfiles:listPermisosPerfil){
    			save(permisosperfiles);
    		}
    		
    		flag = true;
		} catch (RuntimeException re) {
			if(tx.isActive())
				tx.rollback();
			
			throw re;
		}
    	
    	return flag;
	}

}
