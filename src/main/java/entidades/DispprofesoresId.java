package entidades;
// Generated Jul 4, 2016 12:16:49 PM by Hibernate Tools 4.0.0.Final

/**
 * DispprofesoresId generated by hbm2java
 */
public class DispprofesoresId implements java.io.Serializable {

	private Profesores profesores;
	private Dias dias;
	private Horas horas;

	public DispprofesoresId() {
	}
	
	public DispprofesoresId(Profesores profesores, Dias dias, Horas horas) {
		super();
		this.profesores = profesores;
		this.dias = dias;
		this.horas = horas;
	}

	public Profesores getProfesores() {
		return profesores;
	}

	public void setProfesores(Profesores profesores) {
		this.profesores = profesores;
	}

	public Dias getDias() {
		return dias;
	}

	public void setDias(Dias dias) {
		this.dias = dias;
	}

	public Horas getHoras() {
		return horas;
	}

	public void setHoras(Horas horas) {
		this.horas = horas;
	}
}
