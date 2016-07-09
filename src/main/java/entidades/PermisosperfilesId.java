package entidades;
// Generated Jul 4, 2016 12:16:49 PM by Hibernate Tools 4.0.0.Final

/**
 * PermisosperfilesId generated by hbm2java
 */
public class PermisosperfilesId implements java.io.Serializable {

	private int idPerfil;
	private int idPermiso;

	public PermisosperfilesId() {
	}

	public PermisosperfilesId(int idPerfil, int idPermiso) {
		this.idPerfil = idPerfil;
		this.idPermiso = idPermiso;
	}

	public int getIdPerfil() {
		return this.idPerfil;
	}

	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}

	public int getIdPermiso() {
		return this.idPermiso;
	}

	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PermisosperfilesId))
			return false;
		PermisosperfilesId castOther = (PermisosperfilesId) other;

		return (this.getIdPerfil() == castOther.getIdPerfil()) && (this.getIdPermiso() == castOther.getIdPermiso());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdPerfil();
		result = 37 * result + this.getIdPermiso();
		return result;
	}

}