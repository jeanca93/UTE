package entidades;
// Generated Jul 4, 2016 12:16:49 PM by Hibernate Tools 4.0.0.Final

/**
 * ProfesoresmateriasId generated by hbm2java
 */
public class ProfesoresmateriasId implements java.io.Serializable {

	private int idProfesor;
	private String idMateria;
	private String idCurso;

	public ProfesoresmateriasId() {
	}

	public ProfesoresmateriasId(int idProfesor, String idMateria, String idCurso) {
		this.idProfesor = idProfesor;
		this.idMateria = idMateria;
		this.idCurso = idCurso;
	}

	public int getIdProfesor() {
		return this.idProfesor;
	}

	public void setIdProfesor(int idProfesor) {
		this.idProfesor = idProfesor;
	}

	public String getIdMateria() {
		return this.idMateria;
	}

	public void setIdMateria(String idMateria) {
		this.idMateria = idMateria;
	}

	public String getIdCurso() {
		return this.idCurso;
	}

	public void setIdCurso(String idCurso) {
		this.idCurso = idCurso;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProfesoresmateriasId))
			return false;
		ProfesoresmateriasId castOther = (ProfesoresmateriasId) other;

		return (this.getIdProfesor() == castOther.getIdProfesor())
				&& ((this.getIdMateria() == castOther.getIdMateria()) || (this.getIdMateria() != null
						&& castOther.getIdMateria() != null && this.getIdMateria().equals(castOther.getIdMateria())))
				&& ((this.getIdCurso() == castOther.getIdCurso()) || (this.getIdCurso() != null
						&& castOther.getIdCurso() != null && this.getIdCurso().equals(castOther.getIdCurso())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdProfesor();
		result = 37 * result + (getIdMateria() == null ? 0 : this.getIdMateria().hashCode());
		result = 37 * result + (getIdCurso() == null ? 0 : this.getIdCurso().hashCode());
		return result;
	}

}
