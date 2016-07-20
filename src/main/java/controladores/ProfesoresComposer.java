package controladores;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import entidades.Profesores;
import entidadesDAO.ProfesoresHomeExt;
import modelo.profesores.ProfesorStatus;
import modelo.profesores.ProfesoresDatos;

public class ProfesoresComposer extends GenericForwardComposer<Component>{
	private static final long serialVersionUID = 6L;
	private Window modalDialog;
	private Textbox txtProfesor, txtTitulo;
	private Intbox txtMaxhoras;
	private ListModelList<ProfesorStatus> allProfesoresStatus;
	private Grid GridProfesores;
	
	public ProfesoresComposer() {
		// TODO Auto-generated constructor stub
		
		super();
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		final Execution execution = Executions.getCurrent();
		allProfesoresStatus = (ListModelList<ProfesorStatus>)execution.getArg().get("modelPrincipal");
		GridProfesores = (Grid)execution.getArg().get("gridPrincipal");
		
	}
	
	public void onClick$tbbGrabar() throws InterruptedException,
			ParserConfigurationException, SAXException, IOException,
			InstantiationException, IllegalAccessException {		
		try{
			String profesor = txtProfesor.getValue();
			String titulo = txtTitulo.getValue();
			Integer maxhoras = txtMaxhoras.getValue();
			
			new ProfesoresHomeExt().attachDirty(new Profesores(profesor, titulo, maxhoras, 'A', new Date(),Integer.parseInt(session.getAttribute("idUsuario").toString())));
			
			modalDialog.detach();
			
			allProfesoresStatus = new ListModelList<ProfesorStatus>();
			genListModel(new ProfesoresDatos().getAllProfesores());
			GridProfesores.setModel(allProfesoresStatus);
			
			Clients.showNotification("Creado correctamente");
		}catch(RuntimeException re){
			throw re;
		}
	}
	
	private void genListModel(List<Profesores> lsProfesores){
    	for(Profesores profesor: lsProfesores){
			allProfesoresStatus.add(new ProfesorStatus(profesor, false, false));
		}
    }
}
