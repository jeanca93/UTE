package modelo.mantenimiento.usuarios.administracionUsuarios;

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
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import entidades.Estados;
import entidades.Perfilesusuario;
import entidades.Usuarios;
import entidadesDAO.UsuariosHome;
import modelo.mantenimiento.estados.EstadosDatos;

public class UsuariosModel {
	private List<Perfilesusuario> allPerfiles;
	private ListModelList<UsuarioStatus> allUsuariosStatus;
	private List<Estados> allEstados;
	private List<Usuarios> listUserTMP;
	private boolean displayEdit = true;
	
	@Wire
	private Grid GridUsuarios;
	
	public UsuariosModel(){
		super();
		
		UsuarioDatos userDatos = new  UsuarioDatos();		
		
		allUsuariosStatus = new ListModelList<UsuarioStatus>();
		allUsuariosStatus = genListModel(userDatos.getAllUsuarios());
		
		allPerfiles = new ArrayList<Perfilesusuario>();
		allPerfiles = userDatos.getAllPerfiles();
		
		allEstados = new ArrayList<Estados>();
		allEstados = new EstadosDatos().getAllEstado();
		
		listUserTMP = new ArrayList<Usuarios>();
	}
	
	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view){
		Selectors.wireComponents(view, this, false);
	}
	
	@NotifyChange({"allUsuariosStatus", "displayEdit"})
    public void setDisplayEdit(boolean displayEdit) {
        this.displayEdit = displayEdit;
    }
	
	@Command
    public void nuevoUsuario() {
		/*
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("modelPrincipal",allUsuariosStatus );
		parameters.put("gridPrincipal",GridUsuarios );
		
		Window window = (Window)Executions.createComponents(
                "/WEB-INF/include/Usuarios/AdministracionUsuarios/vtnUsuarios.zul", null, parameters);
        window.doModal();
        */
		
		Window window = (Window)Executions.createComponents(
                "/WEB-INF/include/Mantenimiento/Usuarios/AdministracionUsuarios/vtnUsuarios.zul", null, null);
        window.doModal();
        
    }
	
	@Command
	public void eliminarUsuarios(){
	    final List<Usuarios> usersDelete = new ArrayList<Usuarios>();
	    
	    for(UsuarioStatus userStatus:allUsuariosStatus){
	    	if(userStatus.isSeleccionado())
	    		usersDelete.add(userStatus.getUsuario());
	    }
	    
	    if(usersDelete.size() == 0){
	    	Clients.alert("Debe seleccionar m&iacute;nimo un registro para continuar", "Error", null);
	    }else{
	    	Messagebox.show("¿Está seguro que desea continuar?", "Mensaje de Confirmaci�n", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
	    		
	    		public void onEvent(Event event) throws Exception {
	    			// TODO Auto-generated method stub
	    			
	    			if (event.getName().equals("onYes")) {
	    				try{
	    					for(Usuarios usuario:usersDelete){
	    						new UsuariosHome().delete(usuario);
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
		UsuarioDatos userDatos = new  UsuarioDatos();
		
		allPerfiles = new ArrayList<Perfilesusuario>();
		allPerfiles = userDatos.getAllPerfiles();
		
		allUsuariosStatus = new ListModelList<UsuarioStatus>();
		allUsuariosStatus = genListModel(userDatos.getAllUsuarios());
		GridUsuarios.setModel(allUsuariosStatus);
	}
	
	@Command
	@NotifyChange("allUsuariosStatus")
	public void selectedCheckBox(@BindingParam("CheckEvent") CheckEvent evt){
		for(UsuarioStatus userStatus:allUsuariosStatus){
			if(!userStatus.isEditingStatus())
				userStatus.setSeleccionado(evt.isChecked());
		}
	}
	
	@Command
    public void changeEditableStatus(@BindingParam("UsuarioStatus") UsuarioStatus usr) {
		infoUsuarioInicial(usr.getUsuario());
		
		usr.setEditingStatus(!usr.isEditingStatus());
        refreshRowTemplate(usr);
    }
     
    @Command
    public void confirm(@BindingParam("UsuarioStatus") UsuarioStatus usr) {
    	changeEditableStatus(usr);
        refreshRowTemplate(usr);
        
        Usuarios usrTMP = null;
        boolean flagCambio = false;
        
        for(Usuarios usuario:listUserTMP){
        	if(usuario.getIdUsuario() == usr.getUsuario().getIdUsuario()){
        		if(usuario.getNombres() != usr.getUsuario().getNombres() || usuario.getApellidos() != usr.getUsuario().getApellidos() ||
        				usuario.getUsuario() != usr.getUsuario().getUsuario() || usuario.getCorreo() != usr.getUsuario().getCorreo() ||
        				usuario.getPerfilesusuario() != usr.getUsuario().getPerfilesusuario() || usuario.getEstados() != usr.getUsuario().getEstados())
        			flagCambio = true;
        		
        		usrTMP = usuario;
        		
        		break;
        	}
        }
        
        if (flagCambio){
        	try{
        		Session session = Sessions.getCurrent();
				usr.getUsuario().setUsuarioModifica(Integer.parseInt(session.getAttribute("idUsuario").toString()));
        		usr.getUsuario().setFechaModificacion(new Date());
        		new UsuariosHome().update(usr.getUsuario());
        		
        		//refresh();
        		
        		Clients.showNotification("Registro modificado correctamente");
        	}catch(RuntimeException re){
        		throw re;
        	}
        }
        
        listUserTMP.remove(usrTMP);
    }
	
    private void infoUsuarioInicial(Usuarios user)
    {
    	boolean flag = false;
    	
    	if(listUserTMP.size() != 0){    		
    		for(Usuarios usr:listUserTMP){
    			if(usr.getIdUsuario() == user.getIdUsuario()){
    				flag = true;
    				
    				break;
    			}
    		}
    	}
    	
    	if(flag == false){
			listUserTMP.add(new Usuarios());
			listUserTMP.get(listUserTMP.size()-1).setIdUsuario(user.getIdUsuario());
			listUserTMP.get(listUserTMP.size()-1).setNombres(user.getNombres());
			listUserTMP.get(listUserTMP.size()-1).setApellidos(user.getApellidos());
			listUserTMP.get(listUserTMP.size()-1).setUsuario(user.getUsuario());
			listUserTMP.get(listUserTMP.size()-1).setCorreo(user.getCorreo());
			listUserTMP.get(listUserTMP.size()-1).setPerfilesusuario(user.getPerfilesusuario());
			listUserTMP.get(listUserTMP.size()-1).setEstados(user.getEstados());
		}
    }
    
    public void refreshRowTemplate(UsuarioStatus usr) {
        //replace the element in the collection by itself to trigger a model update
    	allUsuariosStatus.set(allUsuariosStatus.indexOf(usr), usr);
    }
	
    private ListModelList<UsuarioStatus> genListModel(List<Usuarios> lsUsuarios){
    	for(Usuarios usr: lsUsuarios){
			allUsuariosStatus.add(new UsuarioStatus(usr, false, false));
		}
    	
    	return allUsuariosStatus;
    }
    
	public ListModelList<UsuarioStatus> getAllUsuariosStatus() {
		return allUsuariosStatus;
	}
	
	public List<Perfilesusuario> getAllPerfiles() {
		return allPerfiles;
	}
	
	public List<Estados> getAllEstados() {
		return allEstados;
	}
		
	public boolean isDisplayEdit() {
        return displayEdit;
    }
}
