package entidadesDAO;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.gaha.servicios.modelo.DiaHora;
import edu.gaha.servicios.modelo.ModeloAsignacionHorario;

public class HorariosdetalleHomeExt extends HorariosdetalleHome{
	public boolean registrarHorarios(List<ModeloAsignacionHorario> lHorario, int usuarioCrea){
		Session session = this.getSession();  
		session.flush();
		Transaction tx = null;
		Boolean flag = false;
		
		try{	
			System.out.println("********************************************");
			System.out.println("\nINICIO DE INSERCION DE RESULTADOS");
			
			tx = session.beginTransaction();
			
			StringBuffer sbquery = new StringBuffer();
			sbquery.append("INSERT INTO horarioscabecera(ID_SchollarYear, ID_Estado, FechaCreacion, UsuarioCrea) VALUES");
			sbquery.append("((SELECT ID_SchoolarYear FROM gaha.schollaryear WHERE ID_Estado=1),1,SYSDATE()," + usuarioCrea + ");");
						
			System.out.println("\n" + sbquery.toString());
			
			Query query = session.createSQLQuery(sbquery.toString());	        
	        flag = (query.executeUpdate() ==1?true:false);
	        
	        sbquery = new StringBuffer();
			sbquery.append("INSERT INTO horariosdetalle(ID_HorarioCab, ID_Profesor, ID_Materia, ID_Curso, Paralelo, ID_Aula, ID_Dia, ID_Hora, ID_Estado, FechaCreacion, UsuarioCrea) VALUES");
				
			for(ModeloAsignacionHorario reg:lHorario){						
				List<DiaHora> listDiaHoras = reg.getListaDiaHora();
			
				for(DiaHora reg2:listDiaHoras){
					sbquery.append("((SELECT MAX(ID_HorarioCab) FROM gaha.horarioscabecera WHERE ID_Estado=1)," + reg.getlProfesor() + ",'" + reg.getlMateria() + "','" + reg.getlCurso() + "','" + reg.getlParalelo() + "','" + reg.getlAula() + "'," + reg2.getlIdDia() + "," + reg2.getlIdHora() + ",1,SYSDATE()," + usuarioCrea + ")");
					
					String signoInsert = ",";
					
					if((listDiaHoras.indexOf(reg2) == listDiaHoras.size() -1) && (lHorario.indexOf(reg) == lHorario.size() -1))
						signoInsert = ";";
					
					sbquery.append(signoInsert);
				}
			}
			
			System.out.println("\n" + sbquery.toString());
			
			query = session.createSQLQuery(sbquery.toString());	        
	        flag = (query.executeUpdate() ==1?true:false);
	        
	        sbquery = new StringBuffer("UPDATE schollaryear SET ID_Estado = 3 WHERE ID_Estado=1;");
			
	        System.out.println("\n" + sbquery.toString());
	        
			query = session.createSQLQuery(sbquery.toString());	        
	        flag = (query.executeUpdate() ==1?true:false);
			
	        tx.commit();
	        
	        System.out.println("\nFIN DE INSERCION DE RESULTADOS");
			
			System.out.println("********************************************");
		}catch(Throwable re){
			tx.rollback();
			
			throw re;
		}
		
		return flag;
	}
}
