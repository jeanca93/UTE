package modelo.reportes.reporteAulas;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Filedownload;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkex.zul.Jasperreport;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;

import entidades.Aulas;
import entidades.Schollaryear;
import modelo.mantenimiento.anioescolar.AnioEscolarDatos;
import modelo.mantenimiento.aulas.AulasDatos;
import modelo.reportes.ReportConfig;
import modelo.reportes.ReportType;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class ReporteAulasModel {
	private ListModelList<Schollaryear> allSchollarYear;
	private List<Aulas> allAulas;
	private ReportType reportType;
	private List<ReportType> reportTypesModel;
	private ReportConfig reportConfig;
	private Map<String, Object> parameters;
	@Wire
	private Jasperreport reporteAulas;
	@Wire
	private Combobox cmbSchollarYear, cmbAulas;
	
	public ReporteAulasModel() {
		// TODO Auto-generated constructor stub
		
		Calendar now = Calendar.getInstance();
		
		allSchollarYear = new ListModelList<Schollaryear>();
		
		for(Schollaryear year:new AnioEscolarDatos(false).getAllAnioEscolar()){
			allSchollarYear.add(year);
			
			if((year.getFechaInicio().before(now.getTime()) && year.getFechaFin().after(now.getTime())) ||
				(year.getFechaInicio().equals(now.getTime()) || year.getFechaFin().equals(now.getTime()))){
				allSchollarYear.addToSelection(year);
			}
		}
		
		allAulas = new AulasDatos().getAllAula();
		
		reportTypesModel = new ArrayList<ReportType>(
				Arrays.asList(
						new ReportType("PDF", "pdf"), 
						new ReportType("Excel", "xls")));
		
		reportType = reportTypesModel.get(0);
	}
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws Exception {
		Selectors.wireComponents(view, this, false);
		
	}
	
	@Command("generarReporte")
	@NotifyChange("reportConfig")
	public void generarReporte() throws Exception {
		String rutaReporte = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/include/Reportes/ReporteAulas/ReporteAulas.jasper");
		String rutaImagen = Sessions.getCurrent().getWebApp().getRealPath("/img/Escudo.png");
		String schollarYear = cmbSchollarYear.getSelectedItem().getValue().toString();
		String aula = (cmbAulas.getSelectedItem() == null?"":cmbAulas.getSelectedItem().getValue().toString());
		
		parameters = new HashMap<String, Object>();
		parameters.put("ImageDir", rutaImagen);
		parameters.put("ID_SchollarYear", schollarYear);
		parameters.put("ID_Aula", aula);
		
		reportConfig = new ReportConfig(rutaReporte);
		reportConfig.setType(reportType);
		
		Context ctx = new InitialContext();
        DataSource ds = (DataSource)ctx.lookup("java:/REPORTES");
        
        reporteAulas.setParameters(parameters);
        reporteAulas.setDataConnection(ds.getConnection());
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
                "ReporteAulas");
	}
	
	public List<Schollaryear> getAllSchollarYear(){
		return allSchollarYear;
	}
	
	public List<Aulas> getAllAulas(){
		return allAulas;
	}
	
	public ReportConfig getReportConfig() {
		return reportConfig;
	}
	
	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}
}
