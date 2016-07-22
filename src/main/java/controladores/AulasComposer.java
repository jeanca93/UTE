package controladores;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import entidades.Aulas;
import entidadesDAO.AulasHome;
import modelo.aulas.AulasDatos;
import modelo.aulas.AulasStatus;
import modelo.estados.Estados;
import modelo.estados.EstadosDatos;


public class AulasComposer extends GenericForwardComposer<Component>{
	private static final long serialVersionUID = 3L;
	private ListModelList<String> allEstados = new ListModelList<String>();
	private Window modalDialog;
	private Combobox cmbEstados;
	private Textbox txtIdAula, txtAula, txtComentarios;
	private Intbox txtAsientos;
	private ListModelList<AulasStatus> allAulasStatus;
	private Grid GridAulas;
	
	public AulasComposer() {
		// TODO Auto-generated constructor stub
		
		for(String estado:new EstadosDatos().getAllEstados()){
			allEstados.add(estado);
		}
		
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		final Execution execution = Executions.getCurrent();
		allAulasStatus = (ListModelList<AulasStatus>)execution.getArg().get("modelPrincipal");
		GridAulas = (Grid)execution.getArg().get("gridPrincipal");
		
	}
	
	public void onClick$tbbGrabar() throws InterruptedException,
			ParserConfigurationException, SAXException, IOException,
			InstantiationException, IllegalAccessException {		
		if(cmbEstados.getSelectedItem() == null){
			
			Clients.showNotification("Debe escoger un perfil valido", Clients.NOTIFICATION_TYPE_ERROR, cmbEstados,  null, 0);
		}else{
			try{
				String idAula = txtIdAula.getValue().trim();
				String aula = txtAula.getValue().trim();
				Integer asientos = txtAsientos.getValue();
				String comentarios = txtComentarios.getValue().trim();
				char estados = cmbEstados.getSelectedItem().getValue();
				Session session = Sessions.getCurrent();
				
				new AulasHome().save(new Aulas(idAula,aula,asientos,comentarios, 'A', new Date(), Integer.parseInt(session.getAttribute("idUsuario").toString())));
				
				
				Messagebox.show("Creado correctamente", "Exito", Messagebox.OK,  Messagebox.EXCLAMATION, new EventListener<Event>() {
					
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
		}
	}
	
	public ListModel<String> getAllEstados() {
		return allEstados;
	}
	/*
	private void genListModel(List<Aulas> lsAulas){
    	for(Aulas aul: lsAulas){
			allAulasStatus.add(new AulasStatus(aul, false, false));
		}
    }
	*/
}
