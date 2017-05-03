package sk.zatko.cayley.models;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import sk.zatko.cayley.math.Dijkstra;

/**
 * Cayley graph model of cyclic group Zn x Zn
 * 
 * @author Jozef Zaťko
 */
public class Graph {

	@JsonIgnore
	private static final double GRAPH_DRAW_DIAMETER = 250.0;
	
	@JsonProperty("nodes")
	private List<Vertex> vertices;
	
	private List<Edge> edges;
	
	public Graph(List<Vertex> vertices, List<Edge> edges) {
		
		this.vertices = vertices;
		this.edges = edges;
	}
	
	/**
	 * Cayley graphs of cyclic groups do not have oriented edges
	 * Two edges between 2 vertices should be reduced to single one
	 */
	public void reduceMultipleEdges() {
		
		for(Edge e : this.edges) {
			
			Vertex source = e.getData().getSource();
			Vertex target = e.getData().getTarget();

			for (Edge eTargeting : source.getTargets()) {
					
				if (eTargeting.getData().getSource().equals(target)) {
				
					if (eTargeting.getData().getColor().equals(Color.WHITE)
							|| e.getData().getColor().equals(Color.WHITE)) {
						continue;
					}
					
					eTargeting.getData().setColor(Color.WHITE);
					
					break;
				}
			}
		}
		
		for (Iterator<Edge> iterator = this.edges.iterator(); iterator.hasNext();) {
		    
			Edge e = iterator.next();
		    
		    if (e.getData().getColor().equals(Color.WHITE)) {
				iterator.remove();
			}
		}
		
	}
	
	/**
	 * Compute graph diameter using custom Dijkstra algorithm implementation
	 */
	@JsonIgnore
	public int getDiameter() {
		
		Dijkstra dijktra = new Dijkstra(this);
		dijktra.findDistances(this.getVertices().get(0));
		
		int maxDist = Integer.MIN_VALUE;
		
		for (Vertex v : this.vertices) {
			
			if (v.getDistance() > maxDist) {
				maxDist = v.getDistance();
			}
		}
		
		return maxDist;
	}
	
	public List<Vertex> getVertices() {
		return vertices;
	}

	public void setVertices(List<Vertex> vertices) {
		this.vertices = vertices;
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	
}
