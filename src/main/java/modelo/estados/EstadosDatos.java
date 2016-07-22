package modelo.estados;

import java.util.ArrayList;
import java.util.List;

public class EstadosDatos 	{
private List<Estados> allEstados = new ArrayList<Estados>();

public EstadosDatos(){
	allEstados.add(new Estados('A',"Activo"));
	allEstados.add(new Estados('I',"Inactivo"));
}

public List<Estados> getAllEstados() {
	return allEstados;
}


}
