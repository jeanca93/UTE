package entidadesDAO;

import org.hibernate.Query;
import org.hibernate.Session;

public class UsuariosHomeExt extends UsuariosHome{
	private Session session;

    public UsuariosHomeExt() {

        super();

        session = getSession();
        
    }
    
    public Boolean validaUsuario(String user, String password){
    	StringBuffer sbquery = new StringBuffer();

        sbquery.append("select valida_login('" + user + "','" + password + "')");
    	
        Query query = session.createSQLQuery(sbquery.toString());
        
        boolean existe = (Boolean) query.uniqueResult();
        
		return existe;
    	
    }
}
