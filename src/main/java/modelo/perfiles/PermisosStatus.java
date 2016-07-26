package modelo.perfiles;

import entidades.Permisos;

public class PermisosStatus {
	private Permisos permisos;
	private boolean seleccionado;
	
	public PermisosStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PermisosStatus(Permisos permisos, boolean seleccionado) {
		super();
		this.permisos = permisos;
		this.seleccionado = seleccionado;
	}

	public Permisos getPermisos() {
		return permisos;
	}
	public void setPermisos(Permisos permisos) {
		this.permisos = permisos;
	}
	public boolean isSeleccionado() {
		return seleccionado;
	}
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
	
}
