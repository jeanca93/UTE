package entidades;
// Generated Jul 4, 2016 12:16:49 PM by Hibernate Tools 4.0.0.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Cursos generated by hbm2java
 */
public class Cursos implements java.io.Serializable {

	private String idCurso;
	private String curso;
	private int paralelos;
	private Character estado;
	private Date fechaCreacion;
	private int usuarioCrea;
	private Date FechaModificacion;
	private Integer usuarioModifica;

	public Cursos() {
	}

	public Cursos(String idCurso, String curso, int paralelos, Date fechaCreacion, int usuarioCrea) {
		this.idCurso = idCurso;
		this.curso = curso;
		this.paralelos = paralelos;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
	}

	public Cursos(String idCurso, String curso, int paralelos, Character estado, Date fechaCreacion, int usuarioCrea,
			Date FechaModificacion, Integer usuarioModifica) {
		this.idCurso = idCurso;
		this.curso = curso;
		this.paralelos = paralelos;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
		this.FechaModificacion = FechaModificacion;
		this.usuarioModifica = usuarioModifica;
	}

	public String getIdCurso() {
		return this.idCurso;
	}

	public void setIdCurso(String idCurso) {
		this.idCurso = idCurso;
	}

	public String getCurso() {
		return this.curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public int getParalelos() {
		return this.paralelos;
	}

	public void setParalelos(int paralelos) {
		this.paralelos = paralelos;
	}

	public Character getEstado() {
		return this.estado;
	}

	public void setEstado(Character estado) {
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
		return this.FechaModificacion;
	}

	public void setFechaModificacion(Date FechaModificacion) {
		this.FechaModificacion = FechaModificacion;
	}

	public Integer getUsuarioModifica() {
		return this.usuarioModifica;
	}

	public void setUsuarioModifica(Integer usuarioModifica) {
		this.usuarioModifica = usuarioModifica;
	}

}
