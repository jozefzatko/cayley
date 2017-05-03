package sk.zatko.cayley.models;

/**
 * Vertex data model
 * 
 * @author Jozef Zaťko
 */
public class VertexData {

	private String id;
	
	public VertexData() {
		
	}
	
	public VertexData(String id) {
		
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
		
}
