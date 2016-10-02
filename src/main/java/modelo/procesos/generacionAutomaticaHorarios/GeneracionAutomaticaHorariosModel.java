package modelo.procesos.generacionAutomaticaHorarios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkex.zul.Jasperreport;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Toolbar;

import edu.gaha.servicios.integracion.ServicioIntegracionGeneradorHorario;
import entidades.Generacionhorarios;
import entidadesDAO.GeneracionhorariosHome;
import entidadesDAO.GeneracionhorariosHomeExt;
import modelo.reportes.ReportConfig;
import modelo.reportes.ReportType;

public class GeneracionAutomaticaHorariosModel {
	private List<ReportType> reportTypesModel;
	private ReportType reportType;
	private ReportConfig reportConfig;
	@Wire
	private Jasperreport reporteHorariosAcademicos;
	@Wire
	private Toolbar tbReports;
	
	public GeneracionAutomaticaHorariosModel() {
		reportTypesModel = new ArrayList<ReportType>(
				Arrays.asList(
						new ReportType("PDF", "pdf"), 
						new ReportType("Excel", "xls")));
		
		reportType = reportTypesModel.get(0);
	}
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws Exception {
		Selectors.wireComponents(view, this, false);
        /*
		Toolbarbutton tbbGenHorarios = new Toolbarbutton();
		tbbGenHorarios.setLabel("Generación de Horarios");
		tbbGenHorarios.setImage("/img/agregar.png");
		tbbGenHorarios.setParent(tbReports);
		*/		
	}
	
	@Command("generarHorarios")
	@NotifyChange("reportConfig")
	public void generarHorarios() throws Exception {
		Messagebox.show("Comenzará el proceo de generación de horarios de clases ¿Está seguro que desea continuar?", "Mensaje de Confirmación", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
			
			public void onEvent(Event event) throws Exception {
				// TODO Auto-generated method stub
				if (event.getName().equals("onYes")) {
					GeneracionhorariosHomeExt genHorariosExt = new GeneracionhorariosHomeExt();
					
					Object[] obj = genHorariosExt.validarInformacionInicial();
					
					Integer countSchollarYear = Integer.parseInt(obj[0].toString());
					Integer countDias = Integer.parseInt(obj[1].toString());
					Integer countHoras = Integer.parseInt(obj[2].toString());
					Integer countTipoAula = Integer.parseInt(obj[3].toString());
					Integer countAulas = Integer.parseInt(obj[4].toString());
					Integer countCursos = Integer.parseInt(obj[5].toString());
					Integer countMaterias = Integer.parseInt(obj[6].toString());
					Integer countMateriasCursos = Integer.parseInt(obj[7].toString());
					Integer countProfesores = Integer.parseInt(obj[8].toString());
					Integer countProfesoresMaterias = Integer.parseInt(obj[9].toString());
					Integer countDispProfesores = Integer.parseInt(obj[10].toString());
					
					String mensaje = "";
					
					if(countSchollarYear == 0)
						mensaje = "Debe ingresar o habilitar un año lectivo para continua";
					else{
						if(countDias <5)
							mensaje = "Debe ingresar mínimo cinco días a la semana, actualmente tiene " + countDias + " días";
						else{
							if(countHoras <9)
								mensaje = "Debe ingresar mínimo nueve periodos de clases al día, actualmente tiene " + countHoras + " periodos";
							else{
								if(countTipoAula <1)
									mensaje = "No tiene tipos de aulas ingresadas y/o habilitadas";
								else{
									if(countAulas <1)
										mensaje = "No tiene aulas ingresadas y/o habilitadas";
									else{
										if(countCursos <1)
											mensaje = "No tiene cursos disponibles ingresados y/o habilitados";
										else{
											if(countMaterias <1)
												mensaje = "No tiene materias aptas ingresadas y/o habilitadas";
											else{
												if(countMateriasCursos <1)
													mensaje = "No tiene asignadas materias por cursos que se enseñan en el plantel";
												else{
													if(countProfesores <1)
														mensaje = "No tiene profesores ingresados y/o habilitados";
													else{
														if(countProfesoresMaterias <1)
															mensaje = "No tiene asignadas materias por cursos que pueden impartir los profesores";
														else{
															if(countDispProfesores <1)
																mensaje = "No tiene asignadas la disponibilidades de los profesores";
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
					
					if(mensaje.equals("")){
						Integer idUsuario = Integer.parseInt(Sessions.getCurrent().getAttribute("idUsuario").toString());
						String usuario = Sessions.getCurrent().getAttribute("usuario").toString();
						String correo = Sessions.getCurrent().getAttribute("correo").toString();
						
						try{						
							List<Generacionhorarios> listGeneracionHorarios = genHorariosExt.listGeneracionhorarios();
							
							if(listGeneracionHorarios.size() == 0){							
								Generacionhorarios genHorarios = new Generacionhorarios(idUsuario, usuario, correo, new Date());
								
								new GeneracionhorariosHome().save(genHorarios);
								
								Context ctx = new InitialContext();
						        DataSource ds = (DataSource)ctx.lookup("java:/REPORTES");
						        
						        ServicioIntegracionGeneradorHorario.transferirTramasHorario(ds.getConnection());
								
								Clients.showNotification("La generación de los horarios durará aproximadamente una hora y se le notificará a su correo cuando finalize");
								
								//String rutaReporte = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/include/Reportes/ReporteAsignacionMaterias/AsignacionMaterias.jasper");
								//String rutaImagen = Sessions.getCurrent().getWebApp().getRealPath("/img/Escudo.png");
								
								//reportConfig = new ReportConfig(rutaReporte);
								//reportConfig.setType(reportType);
								
								//Map<String, Object> parameters = new HashMap<String, Object>();
								//parameters.put("ImageDir", rutaImagen);
										
								//Context ctx = new InitialContext();
						        //DataSource ds = (DataSource)ctx.lookup("java:/REPORTES");
								
						        //reporteHorariosAcademicos.setParameters(parameters);
								//reporteHorariosAcademicos.setDataConnection(ds.getConnection());
							}else
								Messagebox.show("Existe un proceso de generación en curso por favor espere que este termine antes de continuar", "Error",  Messagebox.OK,  Messagebox.ERROR);
						}catch(Throwable re){
							genHorariosExt.eliminarRegistro(idUsuario);
							
							throw new Exception("Error al realizar la transferencia de datos, para la generacion del horario");
						}
					}else
						Messagebox.show(mensaje, "Error", Messagebox.OK, Messagebox.INFORMATION);
		        }
			}
	   });
	}
	
	/*
	protected void crearPidEjecucion(String lNombreArchivo, String lUsuario) throws IOException 
	{
		File archivo = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()+lNombreArchivo);
		PrintWriter lEscritor = null;
		try
		{
			if (!archivo.exists())
				archivo.createNewFile();
			
			lEscritor = new PrintWriter(new FileWriter(archivo));
			SimpleDateFormat  lFormatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			lEscritor.println("USUARIO:"+lUsuario +"|FECHA:"+lFormatoFecha.format(new Date()));
			lEscritor.flush();
		}catch(Throwable lError)
		{
			lError.printStackTrace();
		}finally {
			if (lEscritor!=null)
			
				lEscritor.close();
			
		}
	
		
	}
	*/
	
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
