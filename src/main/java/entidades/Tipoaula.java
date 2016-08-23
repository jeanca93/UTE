package entidades;

import java.util.Date;

public class Tipoaula implements java.io.Serializable{
	private Integer idTipoaula;
	private String tipoaula;
	private Estados estados;
	private Date fechaCreacion;
	private int usuarioCrea;
	private Date fechaModificacion;
	private Integer usuarioModifica;
	
	public Tipoaula() {
	}

	public Tipoaula(String tipoaula, Estados estados, Date fechaCreacion, int usuarioCrea) {
		this.tipoaula = tipoaula;
		this.estados = estados;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
	}
	
	public Tipoaula(String tipoaula, Estados estados, Date fechaCreacion, int usuarioCrea,
			Date fechaModificacion, Integer usuarioModifica) {
		this.tipoaula = tipoaula;
		this.estados = estados;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
		this.fechaModificacion = fechaModificacion;
		this.usuarioModifica = usuarioModifica;
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

	public Estados getEstados() {
		return estados;
	}

	public void setEstados(Estados estados) {
		this.estados = estados;
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
