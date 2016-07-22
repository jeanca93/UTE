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
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;

import entidades.Materias;
import entidadesDAO.MateriasHome;

public class MateriasComposer extends GenericForwardComposer<Component>{
	private static final long serialVersionUID = 4L;
	//private Window modalDialog;
	private Textbox txtMateria,txtIdMateria;
	private Timebox txtDuracionClases, txtHorasSemana;
	//private ListModelList<MateriaStatus> allMateriasStatus;
	//private Grid GridMaterias;

	public MateriasComposer() {
		// TODO Auto-generated constructor stub
		
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
		
			try{
				String idmateria = txtIdMateria.getValue().trim();
				String materia = txtMateria.getValue().trim();
				Date horassemana = txtHorasSemana.getValue();
				Date duracionclases = txtDuracionClases.getValue();
				
				Session session = Sessions.getCurrent();
				
				new MateriasHome().save(new Materias(idmateria,materia, duracionclases, horassemana, 'A', new Date(), Integer.parseInt(session.getAttribute("idUsuario").toString())));
				
				Messagebox.show("Creado correctamente", "Exito", Messagebox.OK,  Messagebox.EXCLAMATION, new EventListener<Event>() {
					
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
			}catch(RuntimeException re){
				throw re;
			}
		
	}
	
	/*
	private void genListModel(List<Materias> lsMaterias){
    	for(Materias mate: lsMaterias){
			allMateriasStatus.add(new MateriaStatus(mate, false, false));
		}
    }
    */
}
