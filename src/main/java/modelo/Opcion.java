package modelo;

public class Opcion {
	private final Integer idOpcion;
	private final String name;
    private final String src;
    private final String profilepic;
    private final Boolean isCategory;
    
    public Opcion(Integer idOpcion, String category) {
        this.idOpcion = idOpcion;
        this.name = category;
        this.src = null;
        this.profilepic = null;
        
        this.isCategory = true;
    }
 
    public Opcion(Integer idOpcion, String name, String src, String profilepic) {
        this.idOpcion = idOpcion;
    	this.name = name;
        this.src = src;
        this.profilepic = profilepic;
        
        this.isCategory =  false;       
    }
    
    public Opcion(Integer idOpcion, String name, String src, String profilepic, Boolean category) {
        this.idOpcion = idOpcion;
    	this.name = name;
        this.src = src;
        this.profilepic = profilepic;
        
        this.isCategory = category;        
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
 
    public String getProfilepic() {
        return profilepic;
    }
    
    public Boolean getIsCategory(){
    	return isCategory;
    }
}
