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
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import entidades.Aulas;
import entidades.Tipoaula;
import entidadesDAO.AulasHome;
import entidadesDAO.EstadosHome;
import entidadesDAO.TipoAulaHome;
import modelo.mantenimiento.tipoaula.TipoAulaDatos;

public class AulasComposer extends GenericForwardComposer<Component>{
	private static final long serialVersionUID = 9L;
	private ListModelList<Tipoaula> allTipoAula = new ListModelList<Tipoaula>();
	//private Window modalDialog;
	private Combobox cmbAulas;
	private Textbox txtIdAula, txtAula, txtComentarios;
	private Intbox txtAsientos;
	
	public AulasComposer() {
		// TODO Auto-generated constructor stub
		for(Tipoaula tipo:new TipoAulaDatos(true).getAllTipoAula()){
			allTipoAula.add(tipo);
		}
		
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
	}
	
	public void onClick$tbbGrabar() throws InterruptedException,
			ParserConfigurationException, SAXException, IOException,
			InstantiationException, IllegalAccessException {
		if(cmbAulas.getSelectedItem() == null){
			
			Clients.showNotification("Debe escoger un tipo de aula valido", Clients.NOTIFICATION_TYPE_ERROR, cmbAulas,  null, 0);
		}else
		{
				try{
					String idAula = txtIdAula.getValue().trim();
					String aula = txtAula.getValue().trim().toUpperCase();
					Integer asientos = txtAsientos.getValue();
					String comentarios = txtComentarios.getValue();
					Integer tipoAula = cmbAulas.getSelectedItem().getValue();
					Session session = Sessions.getCurrent();
				
					new AulasHome().save(new Aulas(idAula,aula,new TipoAulaHome().findById(tipoAula),asientos,comentarios, new EstadosHome().findById(1), new Date(), Integer.parseInt(session.getAttribute("idUsuario").toString())));
					
					Messagebox.show("Registro creado correctamente", "Exito", Messagebox.OK,  Messagebox.EXCLAMATION, new EventListener<Event>() {
					
					public void onEvent(Event event) throws Exception {
						// TODO Auto-generated method stub
						Executions.sendRedirect("");
					}
				});
							
				}catch(RuntimeException re){
					throw re;
				}
			}
		
	}
	public ListModel<Tipoaula> getAllTipoAula() {
		return allTipoAula;
	}
}
