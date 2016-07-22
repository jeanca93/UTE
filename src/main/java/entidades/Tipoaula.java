package entidades;

public class Tipoaula implements java.io.Serializable{
	private Integer idTipoaula;
	private String tipoaula;
	private char estado;
	
	public Tipoaula() {
	}

	public Tipoaula(Integer idTipoaula, String tipoaula, char estado) {
		this.idTipoaula = idTipoaula;
		this.tipoaula = tipoaula;
		this.estado = estado;
	}
	
	public Integer getIdTipoaula() {
		return idTipoaula;
	}

	public void setIdTipoaula(Integer idTipoaula) {
		this.idTipoaula = idTipoaula;
	}

	public String getTipoaula() {
		return tipoaula;
	}

	public void setTipoaula(String tipoaula) {
		this.tipoaula = tipoaula;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}
	
}
