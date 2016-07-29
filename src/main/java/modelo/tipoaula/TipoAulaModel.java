package modelo.tipoaula;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.CheckEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Window;

import entidades.Estados;
import entidades.Tipoaula;
import entidadesDAO.TipoAulaHome;
import modelo.estados.EstadosDatos;

public class TipoAulaModel {
	private ListModelList<TipoAulaStatus> allTipoAulaStatus;
	private List<Tipoaula> listTipoAulaTMP;
	private List<Estados> allEstados;
	private boolean displayEdit = true;
	
	@Wire
	private Grid GridTipoAula;

	public TipoAulaModel() {
		super();

		allEstados = new ArrayList<Estados>();
		allEstados = new EstadosDatos().getAllEstados();
		
		allTipoAulaStatus = new ListModelList<TipoAulaStatus>();
		allTipoAulaStatus = genListModel(new TipoAulaDatos(false).getAllTipoAula());
		
		listTipoAulaTMP = new ArrayList<Tipoaula>();
	}
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
	}
	
	@NotifyChange({"allTipoAula", "displayEdit"})
    public void setDisplayEdit(boolean displayEdit) {
        this.displayEdit = displayEdit;
    }
	
	@Command
    public void nuevoTipoAula() {
		//Map<String, Object> parameters = new HashMap<String, Object>();
		//parameters.put("modelPrincipal",allAnioEscolarStatus );
		//parameters.put("gridPrincipal",GridAnioEscolar );
		
		Window window = (Window)Executions.createComponents(
                "/WEB-INF/include/TipoAula/vtnTipoAula.zul", null, null);
        window.doModal();
    }
	
	@Command
	public void eliminarTipoAula(){
		List<Row> components = GridTipoAula.getRows().getChildren();
	    final List<Tipoaula> lsTipoaulaDelete = new ArrayList<Tipoaula>();
	    
	    for(Row row:components){
	      Checkbox ck = (Checkbox) row.getChildren().get(0);
	      
	      if(ck.isChecked()){
	    	  TipoAulaStatus tipoaulaStatus = (TipoAulaStatus)row.getValue();
	    	  lsTipoaulaDelete.add(tipoaulaStatus.getTipoaula());
	      }
	   }
	    
	   if(lsTipoaulaDelete.size() == 0){
		   Clients.alert("Debe seleccionar m&iacute;nimo un registro para continuar", "Error", null);
	   }else{
		   Messagebox.show("¿Est&aacute; seguro que desea continuar?", "Mensaje de Confirmaci&oacute;n", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
			
				public void onEvent(Event event) throws Exception {
					// TODO Auto-generated method stub
					if (event.getName().equals("onYes")) {								
						try{
							for(Tipoaula tipoaula:lsTipoaulaDelete){
								new TipoAulaHome().delete(tipoaula);
							}
							
							BindUtils.postNotifyChange(null, null,this, "*");
							
							refresh();
							
							Clients.showNotification("Registro eliminado correctamente");
						}catch(RuntimeException re){
							throw re;
						}
			        }
				}
		   });
	   }
	}
	
	public void refresh() {
		allTipoAulaStatus = new ListModelList<TipoAulaStatus>();
		allTipoAulaStatus = genListModel(new TipoAulaDatos(false).getAllTipoAula());
		GridTipoAula.setModel(allTipoAulaStatus);
	}
	
	@Command
	public void selectedCheckBox(@BindingParam("CheckEvent") CheckEvent evt){
		List<Row> rows = GridTipoAula.getRows().getChildren();
	    
	    for(Row row:rows){
	      Checkbox ck = (Checkbox) row.getChildren().get(0);
	      TipoAulaStatus tipoaualStatus = (TipoAulaStatus)row.getValue();
	      if(!tipoaualStatus.isEditingStatus())
	    	  ck.setChecked(evt.isChecked());
	   }
	}
	
	@Command
    public void changeEditableStatus(@BindingParam("TipoAulaStatus") TipoAulaStatus tipoaula) {
		infoTipoAulaInicial(tipoaula.getTipoaula());
		
		tipoaula.setEditingStatus(!tipoaula.isEditingStatus());
        refreshRowTemplate(tipoaula);
    }
     
    @Command
    public void confirm(@BindingParam("TipoAulaStatus") TipoAulaStatus tipoaula) {
    	changeEditableStatus(tipoaula);
        refreshRowTemplate(tipoaula);
        
        Tipoaula tipoaulaTMP = null;
        boolean flagCambio = false;
        
        for(Tipoaula tipoaulatmp:listTipoAulaTMP){
        	if(tipoaulatmp.getIdTipoaula() == tipoaula.getTipoaula().getIdTipoaula()){
        		if(tipoaulatmp.getTipoaula() != tipoaula.getTipoaula().getTipoaula() || 
        			tipoaulatmp.getEstados() != tipoaula.getTipoaula().getEstados())
        			flagCambio = true;
        		
        		tipoaulaTMP = tipoaulatmp;
        		
        		break;
        	}
        }
        
        if (flagCambio){
        	try{
        		Session session = Sessions.getCurrent();
        		tipoaula.getTipoaula().setUsuarioModifica(Integer.parseInt(session.getAttribute("idUsuario").toString()));
        		tipoaula.getTipoaula().setFechaModificacion(new Date());
        		new TipoAulaHome().update(tipoaula.getTipoaula());
        		
        	//	refresh();
        		
        		Clients.showNotification("Registro modificado correctamente");
        	}catch(RuntimeException re){
        		throw re;
        	}
        }
        
        listTipoAulaTMP.remove(tipoaulaTMP);
    }
	
    private void infoTipoAulaInicial(Tipoaula tipoaula)
    {
    	boolean flag = false;
    	
    	if(listTipoAulaTMP.size() != 0){    		
    		for(Tipoaula ta:listTipoAulaTMP){
    			if(ta.getIdTipoaula() == tipoaula.getIdTipoaula()){
    				flag = true;
    				
    				break;
    			}
    		}
    	}
    	
    	if(flag == false){
    		listTipoAulaTMP.add(new Tipoaula());
    		listTipoAulaTMP.get(listTipoAulaTMP.size()-1).setIdTipoaula(tipoaula.getIdTipoaula());
    		listTipoAulaTMP.get(listTipoAulaTMP.size()-1).setTipoaula(tipoaula.getTipoaula());
    		listTipoAulaTMP.get(listTipoAulaTMP.size()-1).setEstados(tipoaula.getEstados());
		}
    }
    
    public void refreshRowTemplate(TipoAulaStatus tipoaulaStatus) {
        //replace the element in the collection by itself to trigger a model update
    	allTipoAulaStatus.set(allTipoAulaStatus.indexOf(tipoaulaStatus), tipoaulaStatus);
    }
	
	public ListModelList<TipoAulaStatus> genListModel(List<Tipoaula> lsTipoAula){
		for(Tipoaula tipoaula:lsTipoAula){
			allTipoAulaStatus.add(new TipoAulaStatus(tipoaula, false, false));
		}
		
		return allTipoAulaStatus;
	}
	
	public ListModelList<TipoAulaStatus> getAllTipoAula(){
		return allTipoAulaStatus;
	}
	
	public List<Estados> getAllEstados() {
		return allEstados;
	}
	
	public boolean isDisplayEdit() {
        return displayEdit;
    }
	
}
