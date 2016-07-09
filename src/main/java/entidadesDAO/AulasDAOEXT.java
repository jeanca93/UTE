/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidadesDAO;

import entidades.Aulas;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author USUARIO
 */
public class AulasDAOEXT extends AulasDAO {

    private Session sesion;

    public AulasDAOEXT() {

        super();

        sesion = this.getSession();

    }

    /**
     *
     * Consulta los Usuario por usuario y clave
     *
     *
     * @param usuario
     * @param clave
     *
     *
     * @return Usuario
     *
     */
    public Aulas consultarPorUsuarioClave(String usuario, String clave) {

        StringBuffer bquery = new StringBuffer();

        String query = "";

        bquery.append("from Aulas a where a.usuario = '"+usuario+"' and a.clave = '"+clave+"' ");

        query = bquery.toString();

        Query hquery = sesion.createQuery(query);        

        Aulas usuariotab = (Aulas)hquery.uniqueResult();

        return usuariotab;

    }
    
    /**
     *
     * Consulta los Usuario por usuario
     *
     *
     * @param usuario
     *
     *
     * @return Usuario
     *
     */
    public Aulas consultarPorUsuario(String usuario) {

        StringBuffer bquery = new StringBuffer();

        String query = "";

        bquery.append("from Aulas a where a.usuario = '"+usuario+"' ");

        query = bquery.toString();

        Query hquery = sesion.createQuery(query);        

        Aulas usuariotab = (Aulas)hquery.uniqueResult();

        return usuariotab;

    }

    /**
     *
     * Consulta el siguiente codigo de Usuario
     *
     *
     *
     * @return
     *
     * Integer cod Usuario
     *
     */
    public Long consultarCodUsuario() {

        StringBuffer bquery = new StringBuffer();

        String query = "";

        bquery.append("select max(a.id) from Aulas a ");

        query = bquery.toString();

        Query hquery = sesion.createQuery(query);

        Long secuencia = (Long) hquery.uniqueResult();

        return (secuencia != null ? secuencia + 1 : 1);

    }

}