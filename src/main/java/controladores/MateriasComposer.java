package controladores;

import java.io.IOException;
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
	private Intbox txtHorasSemana;
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
	}
	
	public void onClick$tbbGrabar() throws InterruptedException,
			ParserConfigurationException, SAXException, IOException,
			InstantiationException, IllegalAccessException {		
		
			try{
				String idmateria = txtIdMateria.getValue().trim();
				String materia = txtMateria.getValue().trim();
				Integer horassemana = txtHorasSemana.getValue();
				
				Session session = Sessions.getCurrent();
				
				new MateriasHome().save(new Materias(idmateria,materia, new Date(), new Date(), 'A', new Date(), Integer.parseInt(session.getAttribute("idUsuario").toString())));
				
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
