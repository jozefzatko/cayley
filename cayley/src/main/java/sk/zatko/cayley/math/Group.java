package sk.zatko.cayley.math;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import sk.zatko.cayley.models.Edge;
import sk.zatko.cayley.models.Graph;
import sk.zatko.cayley.models.Vertex;

/**
 * Representation of Zn x Zn cyclic group
 * 
 * @author Jozef Zaťko
 */
public class Group {

	private int n;
	
	private List<String> vertexSet;
	private GeneratingSet generatingSet;
	
	public Group(int n) {
		
		this(n, new GeneratingSet());
	}
	
	public Group(int n, GeneratingSet generatingSet) {
		
		this.n = n;
		this.vertexSet = new ArrayList<String>(n*n);
		this.generatingSet = generatingSet;
		
		generateVertexSet();
	}

	/**
	 * Create a return Graph object based on vertex and edge set
	 */
	public Graph generateGraph() {
		
		List<Vertex> vertices = generateVertices();
		List<Edge> edges = generateEdges(vertices);
		
		return new Graph(vertices, edges);
	}
	
	/**
	 * Generate list of vertices models based on vertex set
	 */
	private List<Vertex> generateVertices() {
		
		List<Vertex> vertices = new ArrayList<Vertex>();
		
		for(String vertex : this.vertexSet) {
			
			vertices.add(new Vertex(vertex));
		}
		
		return vertices;
	}
	
	/**
	 * Generate Cayley graph edges based on generating set
	 */
	private List<Edge> generateEdges(List<Vertex> vertices) {
		
		List<Edge> edges = new ArrayList<Edge>();
		
		int i = 1;
		
		for(Vertex v : vertices) {
			
			Vertex source = v;
			Vertex target = null;
			
			for(String g : this.generatingSet.getValues()) {
				
				String result = add(v.getData().getId(), g);
				
				for(Vertex v1 : vertices) {
					
					if(result.equals(v1.getData().getId())) {
						target = v1;
					}
				}
				
				Edge e = new Edge("e" + i, Color.GREEN, source, target, v.getData().getId() + " + " + g);
				
				e.getData().getSource().getSources().add(e);
				e.getData().getTarget().getTargets().add(e);
				
				edges.add(e);
				
				i++;
			}
		}
		
		return edges;
	}
	
	/**
	 * Math addition of two elements of Zn x Zn cyclic group
	 */
	public String add(String source, String genElement) {
		
		int s1 = Integer.parseInt(source.substring(0,1));	
		int s2 = Integer.parseInt(source.substring(1,2));
		
		int g1 = Integer.parseInt(genElement.substring(0,1));
		int g2 = Integer.parseInt(genElement.substring(1,2));
		
		int t1 = (s1 + g1) % n;
		int t2 = (s2 + g2) % n;
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(t1);
		sb.append(t2);
		
		return sb.toString();
	}
	
	/**
	 * Find inverse element in cyclic group Zn x Zn
	 */
	public String findInverseElement(String element) {
		
		int e1 = Integer.parseInt(element.substring(0,1));
		int e2 = Integer.parseInt(element.substring(1,2));
		
		int i1 = (this.n - e1 + n) % this.n;
		int i2 = (this.n - e2 + n) % this.n;
		
		return i1 + "" + i2;
	}
	
	/**
	 * Generate vertex set - elements of cyclic group Zn x Zn
	 */
	private void generateVertexSet() {
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				
				StringBuilder sb = new StringBuilder();
				
				sb.append(i);
				sb.append(j);
				
				vertexSet.add(sb.toString());
			}
		}
	}
	

	public GeneratingSet getGeneratingSet() {
		return generatingSet;
	}

	public void setGeneratingSet(GeneratingSet generatingSet) {
		this.generatingSet = generatingSet;
	}

	public int getN() {
		return n;
	}

	public List<String> getVertexSet() {
		return vertexSet;
	}
	
}
