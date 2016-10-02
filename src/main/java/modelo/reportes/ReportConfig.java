package modelo.reportes;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

public class ReportConfig {	
	private String source;
	private JRDataSource dataSource;
	private ReportType type;

	public ReportConfig() {
		// TODO Auto-generated constructor stub
	}
	
	public ReportConfig(String source) {
		this.source = source;
	}
	
	public ReportType getType() {
		return type;
	}
	
	public void setType(ReportType selectedReportType) {
		this.type = selectedReportType;
	}

	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public JRDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(JRDataSource reportDataSource) {
		this.dataSource = reportDataSource;
	}
}
