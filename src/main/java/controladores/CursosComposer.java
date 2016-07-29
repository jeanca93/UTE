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

import entidades.Cursos;
import entidadesDAO.CursosHome;

public class CursosComposer extends GenericForwardComposer<Component>{
	private static final long serialVersionUID = 7L;
	//private Window modalDialog;
	//private Combobox cmbEstados;
	private Textbox txtIdCurso, txtCurso;
	private Intbox txtParalelo;
	//private ListModelList<AulasStatus> allAulasStatus;
	//private Grid GridAulas;
	
	public CursosComposer() {
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		//final Execution execution = Executions.getCurrent();
		//allAulasStatus = (ListModelList<AulasStatus>)execution.getArg().get("modelPrincipal");
		//GridAulas = (Grid)execution.getArg().get("gridPrincipal");
		
	}
	
	public void onClick$tbbGrabar() throws InterruptedException,
			ParserConfigurationException, SAXException, IOException,
			InstantiationException, IllegalAccessException {		
		//if(cmbEstados.getSelectedItem() == null){
			
		//	Clients.showNotification("Debe escoger un perfil valido", Clients.NOTIFICATION_TYPE_ERROR, cmbEstados,  null, 0);
		//}else{
			try{
				String idCurso = txtIdCurso.getValue().trim();
				String curso = txtCurso.getValue().trim();
				Integer paralelos = txtParalelo.getValue();
			//	char estados = cmbEstados.getSelectedItem().getValue();
				Session session = Sessions.getCurrent();
				
				new CursosHome().save(new Cursos(idCurso,curso,paralelos,'A', new Date(), Integer.parseInt(session.getAttribute("idUsuario").toString())));
				
				Messagebox.show("Registro creado correctamente", "Exito", Messagebox.OK,  Messagebox.EXCLAMATION, new EventListener<Event>() {
					
					public void onEvent(Event event) throws Exception {
						// TODO Auto-generated method stub
						Executions.sendRedirect("");
					}
				});

				/*							
				modalDialog.detach();
				
				allAulasStatus = new ListModelList<AulasStatus>();
				genListModel(new AulasDatos().getAllAula());
				GridAulas.setModel(allAulasStatus);
				
				Clients.showNotification("Creado correctamente");
				*/	
			}catch(RuntimeException re){
				throw re;
			}
		//}
	}
	
	/*
	private void genListModel(List<Aulas> lsAulas){
    	for(Aulas aul: lsAulas){
			allAulasStatus.add(new AulasStatus(aul, false, false));
		}
    }
	*/

}
