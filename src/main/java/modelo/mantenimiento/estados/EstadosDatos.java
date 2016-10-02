package modelo.mantenimiento.estados;

import java.util.ArrayList;
import java.util.List;

import entidades.Estados;
import entidadesDAO.EstadosHomeExt;

public class EstadosDatos 	{
	
	private List<Estados> allEstado = new ArrayList<Estados>();

	public EstadosDatos(){
		EstadosHomeExt estadosExt = new EstadosHomeExt();
		allEstado = estadosExt.listEstados();
	}

	public List<Estados> getAllEstado() {
		return allEstado;
	}

}
