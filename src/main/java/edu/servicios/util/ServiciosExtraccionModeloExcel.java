package edu.servicios.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import basehibernate.MydbBaseHibernateDAO;

public class ServiciosExtraccionModeloExcel extends MydbBaseHibernateDAO{
	private static final Log log = LogFactory.getLog(ServiciosExtraccionModeloExcel.class);
	
	public String generadorTramasHojasExcel(String lSentenciaDb)
			throws Throwable
	{
		String lResultadoTrama = "";
		
		Session session = this.getSession();
		Query query = session.createSQLQuery(lSentenciaDb);
		//query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		
		String[] columnNames = query.getReturnAliases();
		List<Object[]> listResultados = query.list();
		
		if(listResultados.size() > 0){
			
			for(String column:columnNames){
				lResultadoTrama += column + "|"; 
			}
			
			lResultadoTrama = lResultadoTrama.substring(0, lResultadoTrama.length()-1);
			lResultadoTrama+="=";
			
			for(Object[] obj:listResultados){
				for (Integer lIndiceCol = 1; lIndiceCol <=  columnNames.length; lIndiceCol++)
				{
					lResultadoTrama+=obj[lIndiceCol]+ "|";
				}
				
				lResultadoTrama = lResultadoTrama.substring(0, lResultadoTrama.length()-1);
				lResultadoTrama+="=";
			}
		}
		
		return lResultadoTrama;		
	}
}
