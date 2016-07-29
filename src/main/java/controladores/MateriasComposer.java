package controladores;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;

import entidades.Estados;
import entidades.Materias;
import entidades.Perfilesusuario;
import entidades.Tipoaula;
import entidadesDAO.EstadosHome;
import entidadesDAO.MateriasHome;
import entidadesDAO.TipoAulaHome;
import modelo.aulas.AulasDatos;
import modelo.usuarios.UsuarioDatos;

public class MateriasComposer extends GenericForwardComposer<Component>{
	private static final long serialVersionUID = 6L;
	private ListModelList<Tipoaula> allTipoAula = new ListModelList<Tipoaula>();
	private Window modalDialog;
	private Combobox cmbTipoAulas;
	private Textbox txtMateria,txtIdMateria;
	private Timebox txtDuracionClases, txtHorasSemana;
	//private ListModelList<MateriaStatus> allMateriasStatus;
	//private Grid GridMaterias;

	public MateriasComposer() {
		// TODO Auto-generated constructor stub
		for(Tipoaula tipo:new AulasDatos().getAllTipoAula()){
			allTipoAula.add(tipo);
		}
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		//final Execution execution = Executions.getCurrent();
		//allMateriasStatus = (ListModelList<MateriaStatus>)execution.getArg().get("modelPrincipal");
		//GridMaterias = (Grid)execution.getArg().get("gridPrincipal");
		
		Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,45);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);

        txtDuracionClases.setValue(cal.getTime());
	}
	
	public void onClick$tbbGrabar() throws InterruptedException,
			ParserConfigurationException, SAXException, IOException,
			InstantiationException, IllegalAccessException {	
				
			if(cmbTipoAulas.getSelectedItem() == null){
				
				Clients.showNotification("Debe escoger un Tipo de Aula valido", Clients.NOTIFICATION_TYPE_ERROR, cmbTipoAulas,  null, 0);
			}else{
		
			try{
				String idmateria = txtIdMateria.getValue().trim();
				String materia = txtMateria.getValue().trim();
				Date duracionclases = txtDuracionClases.getValue();
				long minutosClases = duracionclases.getTime()/60000L;
				Date horassemana = txtHorasSemana.getValue();
				long minutosSemana = horassemana.getTime()/60000L;
				Integer tipoAula = cmbTipoAulas.getSelectedItem().getValue();
				
				if (minutosClases % 15 == 0){
					if(minutosSemana / minutosClases == 0){
						Session session = Sessions.getCurrent();
						
						new MateriasHome().save(new Materias(idmateria,materia, duracionclases, horassemana,new TipoAulaHome().findById(tipoAula), new EstadosHome().findById(1), new Date(), Integer.parseInt(session.getAttribute("idUsuario").toString())));
						
						Messagebox.show("Registro creado correctamente", "Exito", Messagebox.OK,  Messagebox.EXCLAMATION, new EventListener<Event>() {
							
							public void onEvent(Event event) throws Exception {
								// TODO Auto-generated method stub
								Executions.sendRedirect("");
							}
						});
		
						/*
						modalDialog.detach();
						
						BindUtils.postNotifyChange(null, null, this, "*");
										
						allMateriasStatus = new ListModelList<MateriaStatus>();
						genListModel(new MateriaDatos().getAllMaterias());
						GridMaterias.setModel(allMateriasStatus);
						
						Clients.showNotification("Materia Creada correctamente");
						*/
					}else
						Clients.showNotification("Horas a la Semana debe ser segun duraci&oacute;n de clase", Clients.NOTIFICATION_TYPE_ERROR, txtHorasSemana,  null, 0);
				}else
					Clients.showNotification("Duraci&oacute;n de clases no permitida", Clients.NOTIFICATION_TYPE_ERROR, txtDuracionClases,  null, 0);
			}catch(RuntimeException re){
				throw re;
			}
			}
		
	}
	
	
	public ListModel<Tipoaula> getAllTipoAula() {
		return allTipoAula;
	}
	/*
	private void genListModel(List<Materias> lsMaterias){
    	for(Materias mate: lsMaterias){
			allMateriasStatus.add(new MateriaStatus(mate, false, false));
		}
    }
    */
}
