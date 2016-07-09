package modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.TreeNode;

import entidades.Opciones;
import entidadesDAO.OpcionesHomeExt;

public class OpcionesList {
	public final static String Category = "Category";
    public final static String Opciones = "Opcion";
    private OpcionesTreeNode root;
    
    public OpcionesList(String usuario) {
    	OpcionesHomeExt opExt = new OpcionesHomeExt();
		List<Opciones> listOp = opExt.listMenus(usuario,false,null);
		
		OpcionesTreeNode[] treeOpcion = new OpcionesTreeNode[RegistrosXnivel(listOp,1,null)];
		
		ArrayList<OpcionesTreeNode[]> listOpciones = null;
		Opcion opcion = null;
		Opcion opcion2 = null;
		
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		
		for(Opciones ops: listOp){
			if(ops.getOrden().split("-").length == 1){
				if (opExt.conteoOpciones(usuario, ops.getIdOpcion()) == 1){
					treeOpcion[count1] = new OpcionesTreeNode(new Opcion(ops.getIdOpcion(), ops.getTituloMenu(),ops.getUrl(),ops.getImagen()));
					
					count1 ++;
				}else{
					if (listOpciones != null){
						if(listOpciones.size() > 0){
							treeOpcion[count1] = new OpcionesTreeNode(opcion, listOpciones.get(0),true);
							
							count1 ++;
							
							listOpciones = null;					
						}
					}
					
					opcion = new Opcion(ops.getIdOpcion(), ops.getTituloMenu());
				}
				
				count2 = 0;
			}else{
				if (listOpciones == null){					
					listOpciones = new ArrayList<OpcionesTreeNode[]>();
					listOpciones.add(new OpcionesTreeNode[RegistrosXnivel(listOp, ops.getOrden().split("-").length, ops.getOpcionContenedora())]);
					
					opcion2 = new Opcion(ops.getIdOpcion(), ops.getTituloMenu());
					
					if (opExt.conteoOpciones(usuario, ops.getIdOpcion()) == 1)
						listOpciones.get(0)[count2] = new OpcionesTreeNode(opcion2);
				}else{
					if (ops.getOpcionContenedora() == opcion2.getIdOpcion()){
						if (listOpciones.size() == 1)
							listOpciones.add(new OpcionesTreeNode[RegistrosXnivel(listOp, ops.getOrden().split("-").length, ops.getOpcionContenedora())]);
						
						listOpciones.get(1)[count3] = new OpcionesTreeNode(new Opcion(ops.getIdOpcion(),ops.getTituloMenu(),ops.getUrl(),ops.getImagen()));
						
						if (ops.getIdOpcion() == opExt.verificaUltimo(usuario, ops.getOpcionContenedora()))
						{
							listOpciones.get(0)[count2] = new OpcionesTreeNode(opcion2, listOpciones.get(1),true);
							listOpciones.remove(1);
							
							count3 = 0;
							
							if (ops.getOrden().split("-").length == 2 && count2 > 0)
								count2 ++;
						}else					
							count3 ++;
					}else{
						if (ops.getOrden().split("-").length == 2)
							count2 ++;
						
						if (opExt.conteoOpciones(usuario, ops.getIdOpcion()) == 1){
							opcion2 = new Opcion(ops.getIdOpcion(), ops.getTituloMenu(), ops.getUrl(),ops.getImagen());
							
							listOpciones.get(0)[count2] = new OpcionesTreeNode(opcion2);
						}else
							opcion2 = new Opcion(ops.getIdOpcion(), ops.getTituloMenu());
					}
					
					Boolean flag = true, flag2 = true;
					
					if (ops.getOrden().split("-").length == 3){
						if (ops.getOpcionContenedora() != opExt.verificaUltimo(usuario, opExt.opcionPadreInicial(ops.getIdOpcion())))
						{
							flag = false;
						}
					}else{
						if (opExt.conteoOpciones(usuario, ops.getIdOpcion()) > 1)
							flag2 = false;
					}
							
					
					if ((ops.getIdOpcion() == opExt.verificaUltimo(usuario, ops.getOpcionContenedora())) && (flag == true) && (flag2 == true) && (count1 == treeOpcion.length-1))
					{
						if (listOpciones != null){
							if(listOpciones.size() > 0){
								treeOpcion[count1] = new OpcionesTreeNode(opcion, listOpciones.get(0),true);
								
								count1 = 0;
								
								opcion = null;
								
								listOpciones = null;
								
							}
						}
					}
				}
			}
		}
		
		root = new OpcionesTreeNode(null, treeOpcion, true);
		root.setOpen(true);
		
		/*
        root = new OpcionesTreeNode(null,
            new OpcionesTreeNode[] {
                new OpcionesTreeNode(new Opcion("Friend"),new OpcionesTreeNode[] {
                    new OpcionesTreeNode(new Opcion("High School"), new OpcionesTreeNode[] {
                        new OpcionesTreeNode(new Opcion("Fernando Terrell", null, "/img/brut.png")),
                        new OpcionesTreeNode(new Opcion("Stanley Larson", null, "/img/brut.png"))
                    },true),
                    new OpcionesTreeNode(new Opcion("University"), new OpcionesTreeNode[] {
                        new OpcionesTreeNode(new Opcion("Camryn Breanna", null, "/img/brut.png")),
                        new OpcionesTreeNode(new Opcion("Juliana Isabela",null,"/img/brut.png")),
                        new OpcionesTreeNode(new Opcion("Holden Craig",null, "/img/brut.png"))
                    },true),
                    new OpcionesTreeNode(new Opcion("Emma Jones",null, "/img/brut.png")),
                    new OpcionesTreeNode(new Opcion("Eric Franklin",null,  "/img/brut.png")),
                    new OpcionesTreeNode(new Opcion("Alfred Wong",null, "/img/brut.png")),
                    new OpcionesTreeNode(new Opcion("Miguel Soto", null, "/img/brut.png"))
                },true),
                new OpcionesTreeNode(new Opcion("Work"),new OpcionesTreeNode[] {
                    new OpcionesTreeNode(new Opcion("Andrew Willis", null, "/img/brut.png")),
                    new OpcionesTreeNode(new Opcion("Russell Thomas",null,  "/img/brut.png")),
                    new OpcionesTreeNode(new Opcion("Donovan Marcus", null, "/img/brut.png"))
                },true)
            },true
        );*/
    }
    
    public OpcionesTreeNode getRoot() {
        return root;
    }
    
    private Integer RegistrosXnivel(List<Opciones> lista, int nivel, Integer padre){
    	Integer conteo = 0;
    	
    	for(Opciones ops: lista){
    		
    		if(ops.getOrden().split("-").length == nivel && ops.getOpcionContenedora() == padre)
    			conteo += 1;    		
    	}
    	
		return conteo;
    	
    }
}