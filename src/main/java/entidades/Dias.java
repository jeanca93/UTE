package entidades;
// Generated Jul 4, 2016 12:16:49 PM by Hibernate Tools 4.0.0.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Dias generated by hbm2java
 */
public class Dias implements java.io.Serializable {

	private Integer idDia;
	private String dia;
	private Estados estados;
	private Date fechaCreacion;
	private int usuarioCrea;
	private Date fechaModificacion;
	private Integer usuarioModifica;

	public Dias() {
	}

	public Dias(Integer idDia, String dia, Estados estados, Date fechaCreacion, int usuarioCrea) {
		this.idDia = idDia;
		this.dia = dia;
		this.estados = estados;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
	}

	public Dias(Integer idDia, String dia, Estados estados, Date fechaCreacion, int usuarioCrea, Date fechaModificacion,
			Integer usuarioModifica) {
		this.idDia = idDia;
		this.dia = dia;
		this.estados = estados;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
		this.fechaModificacion = fechaModificacion;
		this.usuarioModifica = usuarioModifica;
	}

	public Integer getIdDia() {
		return this.idDia;
	}

	public void setIdDia(Integer idDia) {
		this.idDia = idDia;
	}

	public String getDia() {
		return this.dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
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
