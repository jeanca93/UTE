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
	private Date usuarioModificacion;
	private Integer usuarioModifica;
	private Set<Materiascursos> materiascursoses = new HashSet<Materiascursos>(0);

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
			Date usuarioModificacion, Integer usuarioModifica, Set<Materiascursos> materiascursoses) {
		this.idCurso = idCurso;
		this.curso = curso;
		this.paralelos = paralelos;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
		this.usuarioModificacion = usuarioModificacion;
		this.usuarioModifica = usuarioModifica;
		this.materiascursoses = materiascursoses;
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

	public Date getUsuarioModificacion() {
		return this.usuarioModificacion;
	}

	public void setUsuarioModificacion(Date usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public Integer getUsuarioModifica() {
		return this.usuarioModifica;
	}

	public void setUsuarioModifica(Integer usuarioModifica) {
		this.usuarioModifica = usuarioModifica;
	}

	public Set<Materiascursos> getMateriascursoses() {
		return this.materiascursoses;
	}

	public void setMateriascursoses(Set<Materiascursos> materiascursoses) {
		this.materiascursoses = materiascursoses;
	}

}
