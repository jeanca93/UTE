package modelo.mantenimiento.usuarios.administracionPerfiles;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.CheckEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import entidades.Perfilesusuario;
import entidades.Permisos;
import entidades.Permisosperfiles;
import entidades.PermisosperfilesId;
import entidadesDAO.EstadosHome;
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
	@NotifyChange("allPermisosStatus")
	public void selectedCheckBox(@BindingParam("CheckEvent") CheckEvent evt){
		for(PermisosStatus permisosStatus:allPermisosStatus){
			permisosStatus.setSeleccionado(evt.isChecked());
		}
	}
	
	@Command
	@NotifyChange("allPermisosStatus")
	public void validaActivado(@BindingParam("CheckEvent") CheckEvent evt, @BindingParam("PermisosStatus") PermisosStatus permStatus){
		List<Integer> lstIDPermisosDescativar = new ArrayList<Integer>();
		
		if(permStatus.getPermisos().getIdPermiso() == PermisosHomeExt.Mantenimiento){
			lstIDPermisosDescativar.add(PermisosHomeExt.SchollarYear);
			lstIDPermisosDescativar.add(PermisosHomeExt.Cursos);
			lstIDPermisosDescativar.add(PermisosHomeExt.Materias);
			lstIDPermisosDescativar.add(PermisosHomeExt.Aulas);
			lstIDPermisosDescativar.add(PermisosHomeExt.TiposAula);
			lstIDPermisosDescativar.add(PermisosHomeExt.Profesores);
			lstIDPermisosDescativar.add(PermisosHomeExt.Usuarios);
			lstIDPermisosDescativar.add(PermisosHomeExt.AdministraciónUsuarios);
			lstIDPermisosDescativar.add(PermisosHomeExt.PerfilesUsuarios);
		}else if(permStatus.getPermisos().getIdPermiso() == PermisosHomeExt.Usuarios){
			lstIDPermisosDescativar.add(PermisosHomeExt.AdministraciónUsuarios);
			lstIDPermisosDescativar.add(PermisosHomeExt.PerfilesUsuarios);
		}else if(permStatus.getPermisos().getIdPermiso() == PermisosHomeExt.Procesos)
			lstIDPermisosDescativar.add(PermisosHomeExt.GestorHorariosAcadémicos);
		else if(permStatus.getPermisos().getIdPermiso() == PermisosHomeExt.Reportes){
			lstIDPermisosDescativar.add(PermisosHomeExt.ReporteMaterias);
			lstIDPermisosDescativar.add(PermisosHomeExt.ReporteProfesores);
			lstIDPermisosDescativar.add(PermisosHomeExt.ReporteAsignaciónMaterias);
			lstIDPermisosDescativar.add(PermisosHomeExt.ReporteHorariosProfesores);
			lstIDPermisosDescativar.add(PermisosHomeExt.ReporteHorariosAcademicos);
			lstIDPermisosDescativar.add(PermisosHomeExt.ReporteCargaHorariaProfesores);
			lstIDPermisosDescativar.add(PermisosHomeExt.ReporteAulas);
		}
		
		for(PermisosStatus permisosStatus:allPermisosStatus){
			for(Integer idPermiso:lstIDPermisosDescativar){
				if(permisosStatus.getPermisos().getIdPermiso() == idPermiso){
					permisosStatus.setDesactivado(evt.isChecked());
					
					boolean seleccionado = true;
					
					if(!evt.isChecked())
						seleccionado = false;
					
					if(permisosStatus.isSeleccionado() != seleccionado)
						permisosStatus.setSeleccionado(seleccionado);
					
					break;
				}
			}
		}
	}
	
	@Command
	public void grabarPermisosPerfiles(){
	    List<Permisos> listPermisos = new ArrayList<Permisos>();
	    
	    for(PermisosStatus permisosStatus:allPermisosStatus){
	    	if(permisosStatus.isSeleccionado())
	    		listPermisos.add(permisosStatus.getPermisos());
	    }
	    
	    if(listPermisos.size() == 0){
	    	Clients.alert("Debe seleccionar un permiso m&iacute;nimo para continuar", "Error", null);
	    }else{
	    	try{
		    	Session session = Sessions.getCurrent();
		    	Integer idUsuario = Integer.parseInt(session.getAttribute("idUsuario").toString());
		    	
		    	List<Permisosperfiles> listPermisosperfiles = new ArrayList<Permisosperfiles>();
		    	
		    	for(Permisos permisos:listPermisos){
		    		Permisosperfiles permisosperfiles = new Permisosperfiles();
		    		
		    		PermisosperfilesId id = new PermisosperfilesId();
		    		id.setPerfilesusuario(perfil);
		    		id.setPermisos(permisos);
		    		
		    		permisosperfiles.setId(id);
		    		permisosperfiles.setEstados(new EstadosHome().findById(1));
		    		permisosperfiles.setFechaCreacion(new Date());
		    		permisosperfiles.setUsuarioCrea(idUsuario);
		    		
		    		listPermisosperfiles.add(permisosperfiles);
		    	}
	    	
	    		new PermisosperfilesHomeExt().registrarPermisosPerfil(listPermisosperfiles, idUsuario);
	    		
	    		modalDialog.detach();
	    		
	    		Clients.showNotification("Permisos asignados correctamente");
	    	}catch(RuntimeException re){
	    		throw re;
	    	}
	    }
	}
	
	private ListModelList<PermisosStatus> genListModel(List<Permisos> lsPermisos){
		List<Permisos> listPermisosSelect = new PermisosperfilesHomeExt().listPermisosperfiles(perfil.getIdPerfilUsuario());
		
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
    
	public ListModelList<PermisosStatus> getAllPermisosStatus() {
		return allPermisosStatus;
	}
	
}
