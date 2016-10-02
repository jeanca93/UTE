package modelo.mantenimiento.cursos;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import entidades.Cursos;
import entidades.Estados;
import entidadesDAO.CursosHome;
import modelo.mantenimiento.estados.EstadosDatos;

public class CursosModel {
	private ListModelList<CursosStatus> allCursosStatus;
	private List<Cursos> listCursoTMP;
	private List<Estados> allEstados;
	private boolean displayEdit = true;
		
	@Wire
	private Grid GridCursos;
		
	public CursosModel(){
		super();
			
		allEstados = new ArrayList<Estados>();
		allEstados = new EstadosDatos().getAllEstado();

		allCursosStatus = new ListModelList<CursosStatus>();
		allCursosStatus = genListModel(new CursosDatos().getAllCurso());
		
		listCursoTMP = new ArrayList<Cursos>();
	}
		
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
	}
		
	@NotifyChange({"allCursosStatus", "displayEdit"})
	public void setDisplayEdit(boolean displayEdit) {
		this.displayEdit = displayEdit;
	}
		
	@Command
	public void nuevoCurso() {
		//Map<String, Object> parameters = new HashMap<String, Object>();
		//parameters.put("modelPrincipal",allAulasStatus );
		//parameters.put("gridPrincipal",GridAulas );
			
		Window window = (Window)Executions.createComponents(
	               "/WEB-INF/include/Mantenimiento/Cursos/vtnCursos.zul", null, null);
		window.doModal();
	}
		
		
	@Command
	public void materiasCurso() {
		List<Cursos> materiaCur = new ArrayList<Cursos>();
		
		for(CursosStatus cursoStatus:allCursosStatus){
			if(cursoStatus.isSeleccionado())
				materiaCur.add(cursoStatus.getCurso());
		}
		
		if(materiaCur.size() > 1){
			Clients.alert("Solo puede seleccionar una materia a la vez", "Error", null);
		}else if(materiaCur.size() == 1){
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("curso",materiaCur.get(0));
			
			Window window = (Window)Executions.createComponents(
					"/WEB-INF/include/Mantenimiento/Cursos/vtnMateriasCursos.zul", null, parameters);
			
			window.doModal();
		}else
			Clients.alert("Debe seleccionar un materia para continuar", "Error", null);
	}
	
	@Command
	public void eliminarCursos(){
		final List<Cursos> cursoDelete = new ArrayList<Cursos>();
		
		for(CursosStatus curStatus:allCursosStatus){
			if(curStatus.isSeleccionado())
				cursoDelete.add(curStatus.getCurso());
		}
		
		if(cursoDelete.size() == 0){
			Clients.alert("Debe seleccionar m&iacute;nimo un registro para continuar", "Error", null);
		}else{
			Messagebox.show("¿Está seguro que desea continuar?", "Mensaje de Confirmación", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
				
				public void onEvent(Event event) throws Exception {
					// TODO Auto-generated method stub
					
					if (event.getName().equals("onYes")) {
						try{
							for(Cursos curso:cursoDelete){
								new CursosHome().delete(curso);
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
		allCursosStatus = new ListModelList<CursosStatus>();
		allCursosStatus = genListModel(new CursosDatos().getAllCurso());
		GridCursos.setModel(allCursosStatus);
	}
		
	@Command
	@NotifyChange("allCursosStatus")
	public void selectedCheckBox(@BindingParam("CheckEvent") CheckEvent evt){
	    for(CursosStatus curStatus:allCursosStatus){
	    	if(!curStatus.isEditingStatus())
	    		curStatus.setSeleccionado(evt.isChecked());
	    }
	}
		
	@Command
	public void changeEditableStatus(@BindingParam("CursosStatus") CursosStatus cur) {
		infoCursoInicial(cur.getCurso());
			
		cur.setEditingStatus(!cur.isEditingStatus());
		refreshRowTemplate(cur);
	}
	
	@Command
	public void confirm(@BindingParam("CursosStatus") CursosStatus cur) {
	   	changeEditableStatus(cur);
	   	refreshRowTemplate(cur);
	   	
	   	Cursos curTMP = null;
	   	boolean flagCambio = false;
	   	
	   	for(Cursos cursos:listCursoTMP){
	       	if(cursos.getIdCurso() == cur.getCurso().getIdCurso()){
	       		if(cursos.getCurso() != cur.getCurso().getCurso() || 
	       		   cursos.getParalelos() != cur.getCurso().getParalelos() ||
	       		   cursos.getEstados() != cur.getCurso().getEstados())
	       			flagCambio = true;
	       		
	       			curTMP = cursos;
	        		
	        		break;
	        	}
	    }
	   	
	   	if (flagCambio){
	       	try{
	       		Session session = Sessions.getCurrent();
	       		cur.getCurso().setUsuarioModifica(Integer.parseInt(session.getAttribute("idUsuario").toString()));
	       		cur.getCurso().setfechaModificacion(new Date());
	       		new CursosHome().update(cur.getCurso());
	        		
	       		Clients.showNotification("Registro modificado correctamente");
	       	}catch(RuntimeException re){
	       		throw re;
	       	}
	    }
	        
	    listCursoTMP.remove(curTMP);
	}
	
	private void infoCursoInicial(Cursos cur){
		boolean flag = false;
	    	
	    if(listCursoTMP.size() != 0){    		
	    	for(Cursos cu:listCursoTMP){
	    		if(cu.getIdCurso() == cur.getIdCurso()){
	    			flag = true;
	    				
	    			break;
	    		}
	    	}
	    }
	    	
	    if(flag == false){
	    	listCursoTMP.add(new Cursos());
	    	listCursoTMP.get(listCursoTMP.size()-1).setIdCurso(cur.getIdCurso());
	    	listCursoTMP.get(listCursoTMP.size()-1).setCurso(cur.getCurso());
	    	listCursoTMP.get(listCursoTMP.size()-1).setParalelos(cur.getParalelos());
	    	listCursoTMP.get(listCursoTMP.size()-1).setEstados(cur.getEstados());	    		
		}
	}
	
	public void refreshRowTemplate(CursosStatus cur) {
		//replace the element in the collection by itself to trigger a model update
	    allCursosStatus.set(allCursosStatus.indexOf(cur), cur);
	}
	
	private ListModelList<CursosStatus> genListModel(List<Cursos> lsCursos){
	   	for(Cursos aul: lsCursos){
	   		allCursosStatus.add(new CursosStatus(aul, false, false));
		}
	    	
	   	return allCursosStatus;
	}
	    
	public ListModelList<CursosStatus> getAllCursosStatus() {
		return allCursosStatus;
	}
		
	public List<Estados> getAllEstados() {
		return allEstados;
	}
		
	public boolean isDisplayEdit() {
	       return displayEdit;
	}	

}
