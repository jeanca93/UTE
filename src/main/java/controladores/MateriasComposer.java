package controladores;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.zkoss.bind.BindUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import entidades.Materias;
import entidadesDAO.MateriasHomeExt;
import modelo.materias.MateriaDatos;
import modelo.materias.MateriaStatus;

public class MateriasComposer extends GenericForwardComposer<Component>{
	private static final long serialVersionUID = 4L;
	private Window modalDialog;
	private Textbox txtMateria,txtIdMateria;
	private Intbox txtHorasSemana;
	private ListModelList<MateriaStatus> allMateriasStatus;
	private Grid GridMaterias;

	public MateriasComposer() {
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		final Execution execution = Executions.getCurrent();
		allMateriasStatus = (ListModelList<MateriaStatus>)execution.getArg().get("modelPrincipal");
		GridMaterias = (Grid)execution.getArg().get("gridPrincipal");
		
	}
	
	public void onClick$tbbGrabar() throws InterruptedException,
			ParserConfigurationException, SAXException, IOException,
			InstantiationException, IllegalAccessException {		
		
			try{
				String idmateria = txtIdMateria.getValue().trim();
				String materia = txtMateria.getValue().trim();
				Integer horassemana = txtHorasSemana.getValue();
				
				Session session = Sessions.getCurrent();
				
				new MateriasHomeExt().attachDirty(new Materias(idmateria,materia, horassemana, 'A', new Date(), Integer.parseInt(session.getAttribute("idUsuario").toString())));
				
				modalDialog.detach();
								
				allMateriasStatus = new ListModelList<MateriaStatus>();
				genListModel(new MateriaDatos().getAllMaterias());
				GridMaterias.setModel(allMateriasStatus);
				
				Clients.showNotification("Materia Creada correctamente");
			}catch(RuntimeException re){
				throw re;
			}
		
	}
	

	private void genListModel(List<Materias> lsMaterias){
    	for(Materias mate: lsMaterias){
			allMateriasStatus.add(new MateriaStatus(mate, false, false));
		}
    }
}
