package modelo.estados;

import java.util.ArrayList;
import java.util.List;

public class EstadosDatos 	{
	
	private List<String> allEstados = new ArrayList<String>();

	public EstadosDatos(){
		allEstados.add("A");
		allEstados.add("I");
	}

	public List<String> getAllEstados() {
		return allEstados;
	}
	
/*
	private List<Estados> allEstados = new ArrayList<Estados>();
	
	public EstadosDatos(){
		allEstados.add(new Estados('A',"Activo"));
		allEstados.add(new Estados('I',"Inactivo"));
	}
	
	public List<Estados> getAllEstados() {
		return allEstados;
	}
*/

}
