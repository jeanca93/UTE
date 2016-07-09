package entidades;
// Generated Jul 4, 2016 12:16:49 PM by Hibernate Tools 4.0.0.Final

import java.util.Date;

/**
 * Usuarios generated by hbm2java
 */
public class Usuarios implements java.io.Serializable {

	private Integer idUsuario;
	private Perfilesusuario perfilesusuario;
	private String usuario;
	private String password;
	private String nombres;
	private String apellidos;
	private String correo;
	private char estado;
	private Date fechaCreacion;
	private int usuarioCrea;
	private Date fechaModificacion;
	private Integer usuarioModifica;

	public Usuarios() {
	}

	public Usuarios(Perfilesusuario perfilesusuario, String usuario, String password, String nombres, String apellidos,
			char estado, Date fechaCreacion, int usuarioCrea) {
		this.perfilesusuario = perfilesusuario;
		this.usuario = usuario;
		this.password = password;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
	}

	public Usuarios(Perfilesusuario perfilesusuario, String usuario, String password, String nombres, String apellidos,
			String correo, char estado, Date fechaCreacion, int usuarioCrea, Date fechaModificacion,
			Integer usuarioModifica) {
		this.perfilesusuario = perfilesusuario;
		this.usuario = usuario;
		this.password = password;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo = correo;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
		this.fechaModificacion = fechaModificacion;
		this.usuarioModifica = usuarioModifica;
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Perfilesusuario getPerfilesusuario() {
		return this.perfilesusuario;
	}

	public void setPerfilesusuario(Perfilesusuario perfilesusuario) {
		this.perfilesusuario = perfilesusuario;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public char getEstado() {
		return this.estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
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
