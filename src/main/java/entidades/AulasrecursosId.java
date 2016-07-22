package entidades;
// Generated Jul 4, 2016 12:16:49 PM by Hibernate Tools 4.0.0.Final

public class AulasrecursosId implements java.io.Serializable {

	private Aulas aulas;
	private Recursos recursos;

	public AulasrecursosId() {
	}

	public AulasrecursosId(Aulas aulas, Recursos recursos) {
		super();
		this.aulas = aulas;
		this.recursos = recursos;
	}

	public Aulas getAulas() {
		return aulas;
	}

	public void setAulas(Aulas aulas) {
		this.aulas = aulas;
	}

	public Recursos getRecursos() {
		return recursos;
	}

	public void setRecursos(Recursos recursos) {
		this.recursos = recursos;
	}
	
}
