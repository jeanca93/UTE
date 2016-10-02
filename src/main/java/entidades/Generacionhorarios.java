package entidades;

import java.util.Date;

public class Generacionhorarios implements java.io.Serializable {
	private Integer idGeneracionHorario;
	private int usuarioGenera;
	private String usuario;
	private String correo;
	private Date fechaGeneracion;
	
	public Generacionhorarios() {
	}

	public Generacionhorarios(int usuarioGenera, String usuario, String correo,
			Date fechaGeneracion) {
		this.usuarioGenera = usuarioGenera;
		this.usuario = usuario;
		this.correo = correo;
		this.fechaGeneracion = fechaGeneracion;
	}

	public Integer getIdGeneracionHorario() {
		return idGeneracionHorario;
	}

	public void setIdGeneracionHorario(Integer idGeneracionHorario) {
		this.idGeneracionHorario = idGeneracionHorario;
	}

	public int getUsuarioGenera() {
		return usuarioGenera;
	}

	public void setUsuarioGenera(int usuarioGenera) {
		this.usuarioGenera = usuarioGenera;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(Date fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}
}
