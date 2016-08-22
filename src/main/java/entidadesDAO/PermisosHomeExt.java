package entidadesDAO;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;

import entidades.Permisos;

public class PermisosHomeExt extends PermisosHome{

	public static int Incio = 1;
	public static int Mantenimiento = 2;
	public static int AñoLectivo = 3;
	public static int AdministraciónAñoLectivo = 4;
	public static int Cursos = 5;
	public static int Materias = 6;
	public static int Aulas = 7;
	public static int TiposAula = 8;
	public static int Profesores = 9;
	public static int AdministracónProfesores = 10;
	public static int HorariosProfesores = 11;
	public static int MateriasProfesor = 12;
	public static int Usuarios = 13;
	public static int AdministraciónUsuarios = 14;
	public static int PerfilesUsuarios = 15;
	public static int PermisosPerfiles = 16;
	public static int Procesos = 17;
	public static int GestorHorariosAcadémicos = 18;
	public static int Reportes = 19;
	public static int ReporteHorariosAcademicos = 20;
	public static int ReporteCargaHorariaProfesores = 21;
	public static int ReporteProfesores = 22;
	public static int ReporteMaterias = 23;
	public static int ReporteAulas = 24;
	public static int ReporteAsignaciónMaterias = 25;
	public static int ReporteHorariosProfesores = 26;
	
	public PermisosHomeExt() {
		super();
	}
	
	public ArrayList<Permisos> listPermisosActivos() {
    	Session session = this.getSession();
    	ArrayList<Permisos> results = new ArrayList<Permisos>();
		
    	try {
    		StringBuffer sbquery = new StringBuffer();
    		sbquery.append("from Permisos p where p.estados.idEstado=:estado order by p.idPermiso asc");
    		
    		Query query = session.createQuery(sbquery.toString());
    		query.setInteger("estado", EstadosHomeExt.ESTADO_ACTIVO);
    		
			results = (ArrayList<Permisos>) query.list();			
		} catch (RuntimeException re) {			
			throw re;
		}
		
		return results;
	}
	
}
