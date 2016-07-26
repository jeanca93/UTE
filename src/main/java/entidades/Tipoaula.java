package entidades;

import java.util.Date;

public class Tipoaula implements java.io.Serializable{
	private Integer idTipoaula;
	private String tipoaula;
	private char estado;
	private Date fechaCreacion;
	private int usuarioCrea;
	private Date fechaModificacion;
	private Integer usuarioModifica;
	
	public Tipoaula() {
	}

	public Tipoaula(Integer idTipoaula, String tipoaula, char estado, Date fechaCreacion, int usuarioCrea) {
		this.idTipoaula = idTipoaula;
		this.tipoaula = tipoaula;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
	}
	
	public Tipoaula(Integer idTipoaula, String tipoaula, char estado, Date fechaCreacion, int usuarioCrea,
			Date fechaModificacion, Integer usuarioModifica) {
		this.idTipoaula = idTipoaula;
		this.tipoaula = tipoaula;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
	}
	
	public Integer getIdTipoaula() {
		return idTipoaula;
	}

	public void setIdTipoaula(Integer idTipoaula) {
		this.idTipoaula = idTipoaula;
	}

	public String getTipoaula() {
		return tipoaula;
	}

	public void setTipoaula(String tipoaula) {
		this.tipoaula = tipoaula;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getUsuarioCrea() {
		return usuarioCrea;
	}

	public void setUsuarioCrea(int usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Integer getUsuarioModifica() {
		return usuarioModifica;
	}

	public void setUsuarioModifica(Integer usuarioModifica) {
		this.usuarioModifica = usuarioModifica;
	}
	
}
