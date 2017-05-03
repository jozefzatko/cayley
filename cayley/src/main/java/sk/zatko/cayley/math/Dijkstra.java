package sk.zatko.cayley.math;

import java.util.Comparator;
import java.util.PriorityQueue;

import sk.zatko.cayley.models.Edge;
import sk.zatko.cayley.models.Graph;
import sk.zatko.cayley.models.Vertex;

/**
 * Simplified implementation of Dijkstra algorithm
 * Use attributes of Graph model
 * 
 * @author Jozef Zaťko
 */
public class Dijkstra {

	private Graph graph;
	
	public Dijkstra(Graph graph) {
		
		this.graph = graph;
		
	}
	
	/**
	 * Find distances from initial vertex to all others vertices
	 * 
	 * @param initialVertex
	 */
	public void findDistances(Vertex initialVertex) {
		
		PriorityQueue<Vertex> exploredVertices =  new PriorityQueue<Vertex>(this.graph.getVertices().size(), new VertexDistanceComparator());
		
		for (Vertex v : this.graph.getVertices()) {
			
			v.setDistance(Integer.MAX_VALUE);
			v.setVisited(false);
		}
		initialVertex.setDistance(0);
		
		
		for (Edge e: initialVertex.getSources()) {
			
			e.getData().getTarget().setDistance(1);
			exploredVertices.add(e.getData().getTarget());

		}
		initialVertex.setVisited(true);
		
		
		while (exploredVertices.size() > 0) {
			
			Vertex actual = exploredVertices.remove();
			
			for (Edge e: actual.getSources()) {
				
				Vertex target = e.getData().getTarget();
				
				if (target.isVisited() == true) {
					continue;
				}
				
				if (exploredVertices.contains(target) == false && target.isVisited() == false) {
					exploredVertices.add(target);
				}
				
				if (target.getDistance() > actual.getDistance() + 1) {
					target.setDistance(actual.getDistance() + 1);
				}
			}
			
			actual.setVisited(true);
		}
	}
	
	class VertexDistanceComparator implements Comparator<Vertex> {
		
		@Override
		public int compare(Vertex v1, Vertex v2) {
			
			if (v1.getDistance() < v2.getDistance()) {
				return -1;
			}
			
			if (v1.getDistance() > v2.getDistance()) {
				return 1;
			}
			
			return 0;
		}
	}
	
}
