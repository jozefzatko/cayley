package sk.zatko.cayley.models;

import java.awt.Color;

/**
 * Graph edge model
 * 
 * @author Jozef Zaťko
 */
public class Edge {

	private EdgeData data;
	
	public Edge() {
		
	}
	
	public Edge(String id, Color color, Vertex source, Vertex target, String label) {
		
		this.data = new EdgeData(id, color, source, target, label);
	}

	public EdgeData getData() {
		return data;
	}

	public void setData(EdgeData data) {
		this.data = data;
	}
	
}
