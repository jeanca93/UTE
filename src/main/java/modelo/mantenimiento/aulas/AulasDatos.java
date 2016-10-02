package modelo.mantenimiento.aulas;

import java.util.ArrayList;
import java.util.List;

import entidades.Aulas;
import entidades.Tipoaula;
import entidadesDAO.AulasHomeExt;
import entidadesDAO.TipoAulaHomeExt;

public class AulasDatos {

	private List<Aulas> allAulas = new ArrayList<Aulas>();
	
	public AulasDatos(){
		AulasHomeExt aulaExt = new AulasHomeExt();
		allAulas = aulaExt.listAulasActivas();
	}
	
	public List<Aulas> getAllAula() {
		return allAulas;
	}
	
}
