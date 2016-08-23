package modelo.anioescolar;

import java.util.ArrayList;
import java.util.List;

import entidades.Dias;
import entidadesDAO.DiasHomeExt;

public class DiasDatos {
	private List<Dias> allDias = new ArrayList<Dias>();

	public DiasDatos(boolean activos) {
		DiasHomeExt diasExt = new DiasHomeExt();
		allDias = diasExt.listDiasActivos(activos);
	}
	
	public List<Dias> getAllDias() {
		return allDias;
	}
}
