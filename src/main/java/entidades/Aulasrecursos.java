package entidades;
// Generated Jul 4, 2016 12:16:49 PM by Hibernate Tools 4.0.0.Final

import java.util.Date;

public class Aulasrecursos implements java.io.Serializable {

	private AulasrecursosId id;
	private int stockAsignado;
	private Date fechaIni;
	private Date fechaFin;
	private char estado;
	private Date fechaCreacion;
	private int usuarioCrea;
	private Date fechaModificacion;
	private Integer usuarioModifica;

	public Aulasrecursos() {
	}

	public Aulasrecursos(AulasrecursosId id, int stockAsignado, Date fechaIni,
			char estado, Date fechaCreacion, int usuarioCrea) {
		this.stockAsignado = stockAsignado;
		this.fechaIni = fechaIni;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
	}

	public Aulasrecursos(AulasrecursosId id, int stockAsignado, Date fechaIni,
			Date fechaFin, char estado, Date fechaCreacion, int usuarioCrea, Date fechaModificacion,
			Integer usuarioModifica) {
		this.id = id;
		this.stockAsignado = stockAsignado;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
		this.fechaModificacion = fechaModificacion;
		this.usuarioModifica = usuarioModifica;
	}

	public AulasrecursosId getId() {
		return this.id;
	}

	public void setId(AulasrecursosId id) {
		this.id = id;
	}

	public int getStockAsignado() {
		return this.stockAsignado;
	}

	public void setStockAsignado(int stockAsignado) {
		this.stockAsignado = stockAsignado;
	}

	public Date getFechaIni() {
		return this.fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
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
