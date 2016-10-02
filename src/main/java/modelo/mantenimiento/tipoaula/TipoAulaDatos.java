package modelo.mantenimiento.tipoaula;

import java.util.ArrayList;
import java.util.List;

import entidades.Tipoaula;
import entidadesDAO.TipoAulaHomeExt;

public class TipoAulaDatos {
	
	private List<Tipoaula> allTipoAula = new ArrayList<Tipoaula>();
	
	public TipoAulaDatos(boolean activos){
		TipoAulaHomeExt tipoaulaExt = new TipoAulaHomeExt();
		allTipoAula = tipoaulaExt.listTipoaulaActivos(activos);
	}
	
	public List<Tipoaula> getAllTipoAula(){
		return allTipoAula;
	}
	
}
