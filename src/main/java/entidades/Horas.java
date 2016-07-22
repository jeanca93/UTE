package entidades;
// Generated Jul 4, 2016 12:16:49 PM by Hibernate Tools 4.0.0.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Horas generated by hbm2java
 */
public class Horas implements java.io.Serializable {

	private Integer idHora;
	private Date horaIni;
	private Date horaFin;
	private String comentarios;
	private char estado;
	private Date fechaCreacion;
	private int usuarioCrea;
	private Date fechaModificacion;
	private Integer usuarioModifica;

	public Horas() {
	}

	public Horas(Date horaIni, Date horaFin, char estado, Date fechaCreacion, int usuarioCrea) {
		this.horaIni = horaIni;
		this.horaFin = horaFin;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
	}

	public Horas(Date horaIni, Date horaFin, String comentarios, char estado, Date fechaCreacion, int usuarioCrea,
			Date fechaModificacion, Integer usuarioModifica) {
		this.horaIni = horaIni;
		this.horaFin = horaFin;
		this.comentarios = comentarios;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
		this.fechaModificacion = fechaModificacion;
		this.usuarioModifica = usuarioModifica;
	}

	public Integer getIdHora() {
		return this.idHora;
	}

	public void setIdHora(Integer idHora) {
		this.idHora = idHora;
	}

	public Date getHoraIni() {
		return this.horaIni;
	}

	public void setHoraIni(Date horaIni) {
		this.horaIni = horaIni;
	}

	public Date getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	public String getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
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
