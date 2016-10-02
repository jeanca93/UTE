package modelo.mantenimiento.anioescolar;

import java.util.ArrayList;
import java.util.List;
import entidades.Schollaryear;
import entidadesDAO.SchollaryearHomeExt;

public class AnioEscolarDatos {

	private List<Schollaryear> allSchollaryear = new ArrayList<Schollaryear>();
	
	public AnioEscolarDatos(boolean activos){
		SchollaryearHomeExt anioExt = new SchollaryearHomeExt();
		allSchollaryear = anioExt.listSchollaryearActivos(activos);
	}
	
	public List<Schollaryear> getAllAnioEscolar() {
		return allSchollaryear;
	}
	
}
