package modelo.estados;

public class Estados {
	private char codigo;
	private String estado;
	
	public Estados(char codigo, String estado) {
		super();
		this.codigo = codigo;
		this.estado = estado;
	}

	public char getCodigo() {
		return codigo;
	}

	public void setCodigo(char codigo) {
		this.codigo = codigo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
		

}
