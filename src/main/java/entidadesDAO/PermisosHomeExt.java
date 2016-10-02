package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;

import entidades.Permisos;

public class PermisosHomeExt extends PermisosHome{

	public static Integer Incio = 1;
	public static Integer Mantenimiento = 2;
	public static Integer SchollarYear = 3;
	public static Integer Cursos = 4;
	public static Integer Materias = 5;
	public static Integer Aulas = 6;
	public static Integer TiposAula = 7;
	public static Integer Profesores = 8;
	public static Integer Usuarios = 9;
	public static Integer AdministraciónUsuarios = 10;
	public static Integer PerfilesUsuarios = 11;
	public static Integer Procesos = 12;
	public static Integer GestorHorariosAcadémicos = 13;
	public static Integer Reportes = 14;
	public static Integer ReporteHorariosAcademicos = 15;
	public static Integer ReporteCargaHorariaProfesores = 16;
	public static Integer ReporteProfesores = 17;
	public static Integer ReporteMaterias = 18;
	public static Integer ReporteAulas = 19;
	public static Integer ReporteAsignaciónMaterias = 20;
	public static Integer ReporteHorariosProfesores = 21;
	
	public PermisosHomeExt() {
		super();
	}
	
	public ArrayList<Permisos> listPermisosActivos() {
    	Session session = this.getSession();
    	ArrayList<Permisos> results = new ArrayList<Permisos>();
		
    	try {
    		StringBuffer sbquery = new StringBuffer();
    		sbquery.append("from Permisos p where p.estados.idEstado=:estado and p.idPermiso <> 1 order by p.idPermiso asc");
    		
    		Query query = session.createQuery(sbquery.toString());
    		query.setInteger("estado", EstadosHomeExt.ESTADO_ACTIVO);
    		
			results = (ArrayList<Permisos>) query.list();			
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}
	
}
