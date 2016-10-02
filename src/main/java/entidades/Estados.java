package entidades;

import java.util.Date;

import org.hibernate.proxy.HibernateProxyHelper;

public class Estados implements java.io.Serializable {
	private Integer idEstado;
	private String estado;
	private String descripcion;
	private Date fechaCreacion;
	private int usuarioCrea;
	private Date fechaModificacion;
	private Integer usuarioModifica;
	
	public Estados() {
		HibernateProxyHelper.getClassWithoutInitializingProxy(getClass());
	}
	
	public Estados(String estado, Date fechaCreacion, int usuarioCrea) {
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
	}
	
	public Estados(String estado, String descripcion, Date fechaCreacion, int usuarioCrea,
			Date fechaModificacion, Integer usuarioModifica) {
		this.estado = estado;
		this.descripcion = descripcion;
		this.fechaCreacion = fechaCreacion;
		this.usuarioCrea = usuarioCrea;
		this.fechaModificacion = fechaModificacion;
		this.usuarioModifica = usuarioModifica;
	}
	
	public Integer getIdEstado() {
		return idEstado;
	}
	
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public int getUsuarioCrea() {
		return usuarioCrea;
	}

	public void setUsuarioCrea(int usuarioCrea) {
		this.usuarioCrea = usuarioCrea;
	}
	
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Integer getUsuarioModifica() {
		return usuarioModifica;
	}

	public void setUsuarioModifica(Integer usuarioModifica) {
		this.usuarioModifica = usuarioModifica;
	}
	
}
