package modelo.perfiles;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.Window;

import entidades.Perfilesusuario;
import entidades.Permisos;
import entidades.Permisosperfiles;
import entidades.PermisosperfilesId;
import entidadesDAO.PermisosHomeExt;
import entidadesDAO.PermisosperfilesHomeExt;

public class PermisosPerfilesModel {
	private ListModelList<PermisosStatus> allPermisosStatus;
	private Perfilesusuario perfil;
	@Wire
	private Grid GridPermisosPerfil;
	@Wire
	private Window modalDialog;
	
	public PermisosPerfilesModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
		
		Execution execution = Executions.getCurrent();
		perfil = (Perfilesusuario)execution.getArg().get("perfil");
		
		allPermisosStatus = new ListModelList<PermisosStatus>();
		allPermisosStatus = genListModel(new PermisosHomeExt().listPermisosActivos());
	}
	
	@Command
	public void selectedCheckBox(@BindingParam("CheckEvent") CheckEvent evt){
		List<Row> rows = GridPermisosPerfil.getRows().getChildren();
	    
	    for(Row row:rows){
	      Checkbox ck = (Checkbox) row.getChildren().get(0);
	      ck.setChecked(evt.isChecked());
	   }
	}
	
	@Command
	public void grabarPermisosPerfiles(){
		List<Row> components = GridPermisosPerfil.getRows().getChildren();
	    List<Permisos> listPermisos = new ArrayList<Permisos>();
	    
	    for(Row row:components){
	      Checkbox ck = (Checkbox) row.getChildren().get(0);
	      
	      if(ck.isChecked()){
	    	  PermisosStatus permisosStatus = (PermisosStatus)row.getValue();
	    	  listPermisos.add(permisosStatus.getPermisos());
	      }
	   }
	    
	   if(listPermisos.size() == 0){
		   Clients.alert("Debe seleccionar un permiso minimo para continuar", "Error", null);
	   }else{
		   Session session = Sessions.getCurrent();
		   Integer idUsuario = Integer.parseInt(session.getAttribute("idUsuario").toString());
		   
		   List<Permisosperfiles> listPermisosperfiles = new ArrayList<Permisosperfiles>();
		   for(Permisos permisos:listPermisos){
			   Permisosperfiles permisosperfiles = new Permisosperfiles();
			   
			   PermisosperfilesId id = new PermisosperfilesId();
			   id.setPerfilesusuario(perfil);
			   id.setPermisos(permisos);
			   
			   permisosperfiles.setId(id);
			   permisosperfiles.setEstado('A');
			   permisosperfiles.setFechaCreacion(new Date());
			   permisosperfiles.setUsuarioCrea(idUsuario);
			   
			   listPermisosperfiles.add(permisosperfiles);
		   }
		   
		   try{
			   new PermisosperfilesHomeExt().registrarPermisosPerfil(listPermisosperfiles);
			   
			   modalDialog.detach();
			   
			   Clients.showNotification("Registrado correctamente");
		   }catch(RuntimeException re){
			   throw re;
		   }
	   }
	}
	
	private ListModelList<PermisosStatus> genListModel(List<Permisos> lsPermisos){
		List<Permisos> listPermisosSelect = new PermisosHomeExt().listPermisosperfiless(perfil.getIdPerfilUsuario());
		
    	for(Permisos permisos: lsPermisos){
    		boolean seleccionado = false;
    		
    		for(Permisos permisosSel: listPermisosSelect){
    			if(permisos.equals(permisosSel)){
    				seleccionado = true;
    				break;
    			}
    		}
    		
    		allPermisosStatus.add(new PermisosStatus(permisos, seleccionado));
		}
    	
    	return allPermisosStatus;
    }
    
	public ListModelList<PermisosStatus> getAllPermisos() {
		return allPermisosStatus;
	}
	
}
