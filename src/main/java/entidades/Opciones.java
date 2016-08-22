package entidades;
// Generated Jul 4, 2016 12:16:49 PM by Hibernate Tools 4.0.0.Final

import java.util.Date;

/**
 * Opciones generated by hbm2java
 */
public class Opciones implements java.io.Serializable {

	private Integer idOpcion;
	private Permisos permisos;
	private String opcion;
	private Integer opcionContenedora;
	private String orden;
	private String tituloMenu;
	private String imagen;
	private String url;
	private Estados estados;
	private Date fechaCreacion;
	private int usuarioCrea;
	private Date fechaModificacion;
	private Integer usuarioModifica;

	public Opciones() {
	}

	public Opciones(String opcion, String orden, String tituloMenu, Estados estados, Date fechaCreacion, int usuarioCrea) {
		this.opcion = opcion;
		this.orden = orden;
		this.tituloMenu = tituloMenu;
		this.estados = estados;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
	}

	public Opciones(Permisos permisos, String opcion, Integer opcionContenedora, String orden, String tituloMenu,
			String imagen, String url, Estados estados, Date fechaCreacion, int usuarioCrea, Date fechaModificacion, 
			Integer usuarioModifica) {
		this.permisos = permisos;
		this.opcion = opcion;
		this.opcionContenedora = opcionContenedora;
		this.orden = orden;
		this.tituloMenu = tituloMenu;
		this.imagen = imagen;
		this.url = url;
		this.estados = estados;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
		this.fechaModificacion = fechaModificacion;
		this.usuarioModifica = usuarioModifica;
	}

	public Integer getIdOpcion() {
		return this.idOpcion;
	}

	public void setIdOpcion(Integer idOpcion) {
		this.idOpcion = idOpcion;
	}

	public Permisos getPermisos() {
		return this.permisos;
	}

	public void setPermisos(Permisos permisos) {
		this.permisos = permisos;
	}

	public String getOpcion() {
		return this.opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public Integer getOpcionContenedora() {
		return this.opcionContenedora;
	}

	public void setOpcionContenedora(Integer opcionContenedora) {
		this.opcionContenedora = opcionContenedora;
	}

	public String getOrden() {
		return this.orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public String getTituloMenu() {
		return this.tituloMenu;
	}

	public void setTituloMenu(String tituloMenu) {
		this.tituloMenu = tituloMenu;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Estados getEstados() {
		return this.estados;
	}

	public void setEstados(Estados estados) {
		this.estados = estados;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getUsuarioCrea() {
		return this.usuarioCrea;
	}

	public void setUsuarioCrea(int usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	public Date getFechaModificacion() {
		return this.fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Integer getUsuarioModifica() {
		return this.usuarioModifica;
	}

	public void setUsuarioModifica(Integer usuarioModifica) {
		this.usuarioModifica = usuarioModifica;
	}

}
