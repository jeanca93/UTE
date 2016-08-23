package entidades;
// Generated Jul 4, 2016 12:16:49 PM by Hibernate Tools 4.0.0.Final

import java.util.Date;

/**
 * Dispprofesores generated by hbm2java
 */
public class Dispprofesores implements java.io.Serializable {

	private DispprofesoresId id;
	private Estados estados;
	private Date fechaCreacion;
	private int usuarioCrea;
	private Date fechaModificacion;
	private Integer usuarioModifica;

	public Dispprofesores() {
	}
	
	public Dispprofesores(DispprofesoresId id, Estados estados,
			Date fechaCreacion, int usuarioCrea) {
		this.id = id;
		this.estados = estados;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
	}

	public Dispprofesores(DispprofesoresId id, Estados estados,
			Date fechaCreacion, int usuarioCrea, Date fechaModificacion, Integer usuarioModifica) {
		this.id = id;
		this.estados = estados;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
		this.fechaModificacion = fechaModificacion;
		this.usuarioModifica = usuarioModifica;
	}

	public DispprofesoresId getId() {
		return this.id;
	}

	public void setId(DispprofesoresId id) {
		this.id = id;
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
