package sk.zatko.cayley.test.math;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import sk.zatko.cayley.math.GroupGenerator;

public class IsGeneratingSetTest {

	@Test
	public void everyElementHasInverseElement1() {
		
		GroupGenerator generator = new GroupGenerator();
		
		List<String> set = new ArrayList<String>();
		set.add("02");
		set.add("11");
		set.add("33");
		set.add("12");
		set.add("32");
		
		assertEquals(true, generator.isGeneratingSet(set, 4));
	}
	
	@Test
	public void everyElementHasInverseElement2() {
		
		GroupGenerator generator = new GroupGenerator();
		
		List<String> set = new ArrayList<String>();
		set.add("01");
		set.add("05");
		set.add("42");
		set.add("24");
		
		assertEquals(true, generator.isGeneratingSet(set, 6));
	}
	
	@Test
	public void everyElementHasInverseElement3() {
		
		GroupGenerator generator = new GroupGenerator();
		
		List<String> set = new ArrayList<String>();
		set.add("03");
		set.add("33");
		
		assertEquals(true, generator.isGeneratingSet(set, 6));
	}
	
	@Test
	public void cannotContainNeutralElement() {
		
		GroupGenerator generator = new GroupGenerator();
		
		List<String> set = new ArrayList<String>();
		set.add("00");
		set.add("11");
		set.add("33");
		
		assertEquals(false, generator.isGeneratingSet(set, 4));
	}
	
	@Test
	public void elementDoeNotHaveInverseElement() {
		
		GroupGenerator generator = new GroupGenerator();
		
		List<String> set = new ArrayList<String>();
		set.add("01");
		set.add("40");
		set.add("05");
		
		assertEquals(false, generator.isGeneratingSet(set, 6));
	}
	
	@Test
	public void cannotContainDuplicateElements() {
		
		GroupGenerator generator = new GroupGenerator();
		
		List<String> set = new ArrayList<String>();
		set.add("13");
		set.add("31");
		set.add("13");
		set.add("31");
		
		assertEquals(false, generator.isGeneratingSet(set, 4));
	}

}
