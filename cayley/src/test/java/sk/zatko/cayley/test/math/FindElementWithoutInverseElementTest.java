package sk.zatko.cayley.test.math;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import sk.zatko.cayley.math.GroupGenerator;

public class FindElementWithoutInverseElementTest {

	@Test
	public void testFindElementsWithoutInverseElement() {
		
		GroupGenerator generator = new GroupGenerator();
		
		List<String> set = new ArrayList<String>();
		set.add("11");
		set.add("33");
		set.add("02");
		set.add("12");
		set.add("32");
		set.add("22");
		
		List<String> expectedResult = new ArrayList<String>();
		expectedResult.add("02");
		expectedResult.add("22");
		
		assertEquals(expectedResult, generator.findElementsWithoutInverseElement(set, 4));
	}

}
