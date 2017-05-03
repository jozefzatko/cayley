package sk.zatko.cayley.test.math;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import sk.zatko.cayley.math.GroupGenerator;

public class AddInverseElementsTest {

	@Test
	public void testAddInverseElements1() {
		
		GroupGenerator generator = new GroupGenerator();
		
		List<String> set = new ArrayList<String>();
		set.add("02");
		set.add("15");
		set.add("44");
		
		List<String> expectedResult = new ArrayList<String>();
		expectedResult.add("02");
		expectedResult.add("15");
		expectedResult.add("44");
		expectedResult.add("04");
		expectedResult.add("51");
		expectedResult.add("22");
		
		assertEquals(expectedResult, generator.addInverseElements(set, 6));
	}
	
	@Test
	public void testAddInverseElements2() {
		
		GroupGenerator generator = new GroupGenerator();
		
		List<String> set = new ArrayList<String>();
		set.add("02");
		set.add("15");
		set.add("44");
		set.add("33");
		
		List<String> expectedResult = new ArrayList<String>();
		expectedResult.add("02");
		expectedResult.add("15");
		expectedResult.add("44");
		expectedResult.add("33");
		expectedResult.add("04");
		expectedResult.add("51");
		expectedResult.add("22");
		
		assertEquals(expectedResult, generator.addInverseElements(set, 6));
	}

}
