package modelo.reportes.reporteProfesores;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zhtml.Filedownload;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkex.zul.Jasperreport;

import modelo.reportes.ReportConfig;
import modelo.reportes.ReportType;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class ReporteProfesoresModel {
	private ReportType reportType;
	private List<ReportType> reportTypesModel;
	private ReportConfig reportConfig;
	private Map<String, Object> parameters;
	@Wire
	private Jasperreport reporteProfesores;
	
	public ReporteProfesoresModel() {
		// TODO Auto-generated constructor stub
		
		reportTypesModel = new ArrayList<ReportType>(
				Arrays.asList(
						new ReportType("PDF", "pdf"), 
						new ReportType("Excel", "xls")));
		
		reportType = reportTypesModel.get(0);
		
		String rutaReporte = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/include/Reportes/ReporteProfesores/Profesores.jasper");
		String rutaImagen = Sessions.getCurrent().getWebApp().getRealPath("/img/Escudo.png");
		
		parameters = new HashMap<String, Object>();
		parameters.put("ImageDir", rutaImagen);
		
		reportConfig = new ReportConfig(rutaReporte);
		reportConfig.setType(reportType);
	}
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws Exception {
		Selectors.wireComponents(view, this, false);
        
		Context ctx = new InitialContext();
        DataSource ds = (DataSource)ctx.lookup("java:/REPORTES");
        
        reporteProfesores.setParameters(parameters);
		reporteProfesores.setDataConnection(ds.getConnection());
	}
	
	@Command("exportExcel")
	public void exportExcel() throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		Context ctx = new InitialContext();
        DataSource ds = (DataSource)ctx.lookup("java:/REPORTES");
		
		JasperPrint print = JasperFillManager.fillReport(reportConfig.getSource(),parameters,ds.getConnection());
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setExporterInput(new SimpleExporterInput(print));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(baos));
		
        exporter.exportReport();
        
        Filedownload.save(baos.toByteArray(), "application/vnd.ms-excel",
                "ReporteProfesores");
	}
	
	public ReportConfig getReportConfig() {
		return reportConfig;
	}
	
	public ReportType getReportType() {
		return reportType;
	}
	
	public Map<String, Object> getParameters(){
		return parameters;
	}
}
