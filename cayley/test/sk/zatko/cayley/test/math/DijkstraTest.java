package sk.zatko.cayley.test.math;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sk.zatko.cayley.math.GeneratingSet;
import sk.zatko.cayley.math.Group;
import sk.zatko.cayley.models.Graph;

public class DijkstraTest {

	private GeneratingSet generatingSet;
	
	@Before
	public void init() {
		
		GeneratingSet gs = new GeneratingSet();
		
		gs.getValues().add("01");
		gs.getValues().add("02");
		gs.getValues().add("12");
		gs.getValues().add("21");
		
		this.generatingSet = gs;
	}
	
	@Test
	public void diameterTest1() {
		
		Graph graph = new Group(3, this.generatingSet).generateGraph();
		
		assertEquals(2, graph.getDiameter());
	}
	
	@Test
	public void diameterTest2() {
		
		Graph graph = new Group(4, this.generatingSet).generateGraph();
		
		assertEquals(3, graph.getDiameter());
	}

}
