package modelo.aulas;

import java.util.ArrayList;
import java.util.List;

import entidades.Aulas;
import entidades.Perfilesusuario;
import entidades.Tipoaula;
import entidadesDAO.AulasHomeExt;
import entidadesDAO.PerfilesusuarioHomeExt;
import entidadesDAO.TipoAulaHomeExt;

public class AulasDatos {

	private List<Aulas> allAulas = new ArrayList<Aulas>();
	private List<Tipoaula> allTipoAula = new ArrayList<Tipoaula>();
	
	public AulasDatos(){
		AulasHomeExt aulaExt = new AulasHomeExt();
		allAulas = aulaExt.listAulasActivas();	
		
		TipoAulaHomeExt tipoExt = new TipoAulaHomeExt();		
		allTipoAula = tipoExt.listTipoaulaActivos(true);	

	}
	
	public List<Aulas> getAllAula() {
		return allAulas;
	}
	
	public List<Tipoaula> getAllTipoAula() {
		return allTipoAula;
	}
}
