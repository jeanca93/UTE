package modelo.profesores;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.CheckEvent;
import org.zkoss.zk.ui.event.FulfillEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Row;
import org.zkoss.zul.Window;

import entidades.Dias;
import entidades.Dispprofesores;
import entidades.DispprofesoresId;
import entidades.Horas;
import entidades.Profesores;
import entidadesDAO.DiasHomeExt;
import entidadesDAO.DispprofesoresHomeExt;
import entidadesDAO.HorasHomeExt;
import entidadesDAO.ProfesoresmateriasHomeExt;

public class ProfesoresHorariosModel {
	private ListModelList<ProfesoreshorariosStatus> allProfesoreshorariosStatus;
	private List<Horas> allHoras;
	private Profesores profesor;
	@Wire
	private Grid GridProfHoras;
	@Wire
	private Window modalDialog;
	
	public ProfesoresHorariosModel() {
		// TODO Auto-generated constructor stub
		
		allHoras = new ArrayList<Horas>();
		allHoras = new HorasHomeExt().listHorasActivos();
	}
	
	public List<Horas> getAllhoras(){
		return allHoras;
	}
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
		
		Execution execution = Executions.getCurrent();
		profesor = (Profesores)execution.getArg().get("profesor");
		
		allProfesoreshorariosStatus = new ListModelList<ProfesoreshorariosStatus>();
		allProfesoreshorariosStatus = genListModel(new DiasHomeExt().listDiasActivos());
	}
	
	@Command
	public void itemSeleccinado(@BindingParam("SelectEvent") SelectEvent<Listitem, ?> evt){
		String registrado = "Registrado";
		Set<Listitem> itemsSelected= evt.getSelectedItems();
		
		if(itemsSelected.size() == 0)
			registrado = "";		
		
		Listbox listBox = (Listbox)evt.getTarget();
		Bandbox bandBox = (Bandbox)listBox.getParent().getParent();
		
		if(!bandBox.getValue().equals(registrado))
			bandBox.setValue(registrado);
	}
	
	@Command
	public void selectedCheckBox(@BindingParam("CheckEvent") CheckEvent evt){
		List<Row> rows = GridProfHoras.getRows().getChildren();
	    
	    for(Row row:rows){
	      Checkbox ck = (Checkbox) row.getChildren().get(0);
	      ck.setChecked(evt.isChecked());
	    }
	}
	
	@Command
	public void grabarHorarioProfesor(){
		String diaSinHoras = "";
		
		Session session = Sessions.getCurrent();
		Integer idUsuario = Integer.parseInt(session.getAttribute("idUsuario").toString());
		
		List<Row> components = GridProfHoras.getRows().getChildren();
		List<Dispprofesores> dispProfesor = new ArrayList<Dispprofesores>();
		
	    for(Row row:components){
	      ProfesoreshorariosStatus profHorarioStatus = (ProfesoreshorariosStatus)row.getValue();
	      
	      Checkbox ck = (Checkbox) row.getChildren().get(0);
	      
	      if(ck.isChecked()){	    	  
	    	  Listbox lb = (Listbox) row.getChildren().get(3).getChildren().get(0).getChildren().get(0);
	    	  
	    	  Set<Listitem> itemsSelected= lb.getSelectedItems();
	    	  Iterator<Listitem> itr = itemsSelected.iterator();
	    	  
	    	  if(itemsSelected.size() > 0){
		    	  while(itr.hasNext()){
		        	  Listitem listItem = itr.next();
		        	  Horas horaSelecionada = listItem.getValue();
		        	  
		        	  DispprofesoresId id = new DispprofesoresId(profesor, profHorarioStatus.getDias(), horaSelecionada);
		        	  Dispprofesores disprofesores = new Dispprofesores(id, 'A', new Date(), idUsuario);
		        	  
		        	  dispProfesor.add(disprofesores);
		          }
	    	  }else{
	    		  diaSinHoras = profHorarioStatus.getDias().getDia();
	    		  break;
	    	  }
	      }
	   }
	    
	   if(!diaSinHoras.equals(""))
		   Clients.alert("El dia " + diaSinHoras + " no tiene un horario ingresado", "Error", null);
	   else{
		   try{
			   new DispprofesoresHomeExt().registrarDisponibilidadProfesor(dispProfesor);
			   
			   modalDialog.detach();
			   
			   Clients.showNotification("Registrado correctamente");
		   }catch(RuntimeException re){
		   	   throw re;
		   }
	   }
	}
	
	private ListModelList<ProfesoreshorariosStatus> genListModel(List<Dias> lsDias){
		List<DispprofesoresId> lsDispprofesor = new  DispprofesoresHomeExt().listDispprofesor(profesor.getIdProfesor());
		List<Dias> lsDiasSelec = new ArrayList<Dias>();
		List<Horas> lsHoras = new ArrayList<Horas>();
		List<Horarios> lsHorarios = new ArrayList<Horarios>();
		int i = 1;
		
		for(DispprofesoresId id:lsDispprofesor){
			if (!lsDiasSelec.contains(id.getDias())){
				if(lsDiasSelec.size() > 0){
					lsHorarios.add(new Horarios(lsDiasSelec.get(lsDiasSelec.size() - 1), lsHoras));
					
					lsHoras.clear();
				}
				
				lsDiasSelec.add(id.getDias());				
			}
			
			lsHoras.add(id.getHoras());
			
			if(i == lsDispprofesor.size()){
				if(lsDiasSelec.size() != lsHorarios.size()){
					lsHorarios.add(new Horarios(lsDiasSelec.get(lsDiasSelec.size() - 1), lsHoras));
					
					lsHoras.clear();
				}
			}
			
			i++;
		}
		
    	for(Dias dias: lsDias){
    		boolean seleccionado = false;
    		
    		for(Horarios horarios: lsHorarios){
    			if(dias.equals(horarios.getDias())){
    				seleccionado = true;
    				
    				lsHoras = horarios.getListHoras();
    				
    				break;
    			}
    		}
    		
    		allProfesoreshorariosStatus.add(new ProfesoreshorariosStatus(dias, (lsHoras.size() > 0?lsHoras:new ArrayList<Horas>()),seleccionado));
    		
    		lsHoras.clear();
		}
    	
    	return allProfesoreshorariosStatus;
    }
    
	public ListModelList<ProfesoreshorariosStatus> getAllProfesoreshorarios() {
		return allProfesoreshorariosStatus;
	}
}
