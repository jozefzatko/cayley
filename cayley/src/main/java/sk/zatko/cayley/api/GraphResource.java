package sk.zatko.cayley.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import sk.zatko.cayley.math.GeneratingSet;
import sk.zatko.cayley.math.Group;
import sk.zatko.cayley.math.GroupGenerator;
import sk.zatko.cayley.models.Graph;

/**
 * REST API for Cayley graph of cyclic group Zn x Zn
 * with minimal vertex degree and diameter 2
 * 
 * @author Jozef Zaťko
 */
@Path("/graph")
public class GraphResource {

	private static final Logger log = Logger.getLogger(GraphResource.class);
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Graph getGraph(@PathParam("id") int n) {
		
		Graph graph = null;
		Group group = new Group(n);
		GeneratingSet generatingSet = null;
		
		int expectedVertexDegree = 2*n - 2;
		
		boolean graphFound;
		
		// lets start from expected degree and then try to find graph with smaller degree
		for (int d = expectedVertexDegree; d>=1; d--) {
			
			List<GeneratingSet> set = new GroupGenerator().generate(group.getVertexSet(), d, n);
		
			graphFound = false;
			
			// Test all generating sets
			for (GeneratingSet gs : set) {
				
				group.setGeneratingSet(gs);
				Graph g = group.generateGraph();
				
				if (g.getDiameter() == 2) {
					graph = g;
					generatingSet = gs;
					graphFound = true;
					break;
				}
			}
			
			// if there is no graph for given d, there will be no graph for d-1
			// so we can break the cycle
			if (graphFound == false) {
				break;
			}
		}
		
		log.info("Graph found: " + generatingSet.toString());

		graph.reduceMultipleEdges();
		return graph;
	}
}
