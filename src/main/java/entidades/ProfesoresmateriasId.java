package entidades;
// Generated Jul 4, 2016 12:16:49 PM by Hibernate Tools 4.0.0.Final

/**
 * ProfesoresmateriasId generated by hbm2java
 */
public class ProfesoresmateriasId implements java.io.Serializable {

	private Profesores profesores;
	private Materiascursos materiascursos;

	public ProfesoresmateriasId() {
	}

	public ProfesoresmateriasId(Profesores profesores, Materiascursos materiascursos) {
		super();
		this.profesores = profesores;
		this.materiascursos = materiascursos;
	}

	public Profesores getProfesores() {
		return profesores;
	}

	public void setProfesores(Profesores profesores) {
		this.profesores = profesores;
	}

	public Materiascursos getMateriascursos() {
		return materiascursos;
	}

	public void setMateriascursos(Materiascursos materiascursos) {
		this.materiascursos = materiascursos;
	}
	
}
