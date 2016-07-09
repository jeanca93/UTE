package modelo;

public class Opcion {
	private final Integer idOpcion;
	private final String name;
    private final String category;
    private final String src;
    private final String profilepic;
    
    public Opcion(Integer idOpcion, String category) {
        this.idOpcion = idOpcion;
    	this.category = category;
        this.name = null;
        this.profilepic = null;
        this.src = null;
    }
 
    public Opcion(Integer idOpcion, String name, String src, String profilepic) {
        this.idOpcion = idOpcion;
    	this.name = name;
        this.src = src;
        this.profilepic = profilepic;
        this.category = null;
    }
    
    public Integer getIdOpcion(){
    	return idOpcion;
    }
    
    public String getName() {
        return name;
    }
    
    public String getSrc(){
    	return src;
    }
 
    public String getCategory() {
        return category;
    }
 
    public String getProfilepic() {
        return profilepic;
    }    
}
