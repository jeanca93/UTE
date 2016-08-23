package modelo.estados;

import java.util.ArrayList;
import java.util.List;

import entidades.Estados;
import entidadesDAO.EstadosHome;

public class EstadosDatos 	{
	
	private List<Estados> allEstados = new ArrayList<Estados>();

	public EstadosDatos(){
		EstadosHome estados = new EstadosHome();
		allEstados.add(estados.findById(1));
		allEstados.add(estados.findById(2));
	}

	public List<Estados> getAllEstados() {
		return allEstados;
	}

}
