package entidades;
// Generated Jul 4, 2016 12:16:49 PM by Hibernate Tools 4.0.0.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Horarioscabecera generated by hbm2java
 */
public class Horarioscabecera implements java.io.Serializable {

	private Integer idHorarioCab;
	private Schollaryear schollaryear;
	private Estados estados;
	private Date fechaCreacion;
	private int usuarioCrea;
	private Date fechaModificacion;
	private Integer usuarioModifica;

	public Horarioscabecera() {
	}

	public Horarioscabecera(Schollaryear schollaryear, Estados estados, Date fechaCreacion, int usuarioCrea) {
		this.schollaryear = schollaryear;
		this.estados = estados;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
	}

	public Horarioscabecera(Schollaryear schollaryear, Estados estados, Date fechaCreacion, int usuarioCrea,
			Date fechaModificacion, Integer usuarioModifica) {
		this.schollaryear = schollaryear;
		this.estados = estados;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
		this.fechaModificacion = fechaModificacion;
		this.usuarioModifica = usuarioModifica;
	}

	public Integer getIdHorarioCab() {
		return this.idHorarioCab;
	}

	public void setIdHorarioCab(Integer idHorarioCab) {
		this.idHorarioCab = idHorarioCab;
	}

	public Schollaryear getSchollaryear() {
		return this.schollaryear;
	}

	public void setSchollaryear(Schollaryear schollaryear) {
		this.schollaryear = schollaryear;
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
