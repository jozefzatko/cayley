package sk.zatko.cayley.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Graph vertex model
 * 
 * @author Jozef Zaťko
 */
public class Vertex {

	private VertexData data;
	
	@JsonIgnore private List<Edge> sources;
	@JsonIgnore private List<Edge> targets;
	
	// used by Dijkstra algorithm
	@JsonIgnore private int distance;
	
	// used by Dijkstra algorithm
	@JsonIgnore private boolean isVisited;
	
	public Vertex() {
		
		this(UUID.randomUUID().toString());
	}
	
	public Vertex(String id) {

		this.data = new VertexData(id);
		
		this.sources = new ArrayList<Edge>();
		this.targets = new ArrayList<Edge>();
	}
	
	public VertexData getData() {
		return data;
	}

	public void setData(VertexData data) {
		this.data = data;
	}
	
	public List<Edge> getSources() {
		return sources;
	}
	
	public void setSources(List<Edge> sources) {
		this.sources = sources;
	}
	
	public List<Edge> getTargets() {
		return targets;
	}
	
	public void setTargets(List<Edge> targets) {
		this.targets = targets;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int dist) {
		this.distance = dist;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}
	
}
