package entidades;
// Generated Jul 4, 2016 12:16:49 PM by Hibernate Tools 4.0.0.Final

import java.util.Date;

/**
 * Horarioscabeceratmp generated by hbm2java
 */
public class Horarioscabeceratmp implements java.io.Serializable {

	private Integer idHorarioCabTmp;
	private int idSchollarYear;
	private char estado;
	private Date fechaCreacion;
	private int usuarioCrea;
	private Date fechaModificacion;
	private Integer usuarioModifica;

	public Horarioscabeceratmp() {
	}

	public Horarioscabeceratmp(int idSchollarYear, char estado, Date fechaCreacion, int usuarioCrea) {
		this.idSchollarYear = idSchollarYear;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
	}

	public Horarioscabeceratmp(int idSchollarYear, char estado, Date fechaCreacion, int usuarioCrea,
			Date fechaModificacion, Integer usuarioModifica) {
		this.idSchollarYear = idSchollarYear;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
		this.fechaModificacion = fechaModificacion;
		this.usuarioModifica = usuarioModifica;
	}

	public Integer getIdHorarioCabTmp() {
		return this.idHorarioCabTmp;
	}

	public void setIdHorarioCabTmp(Integer idHorarioCabTmp) {
		this.idHorarioCabTmp = idHorarioCabTmp;
	}

	public int getIdSchollarYear() {
		return this.idSchollarYear;
	}

	public void setIdSchollarYear(int idSchollarYear) {
		this.idSchollarYear = idSchollarYear;
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