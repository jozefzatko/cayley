package sk.zatko.cayley.test.math;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import sk.zatko.cayley.math.GroupGenerator;

public class FindSmallersFromElementPairsTest {

	@Test
	public void testFindSmallersFromElementPairs() {
		
		GroupGenerator generator = new GroupGenerator();
		
		List<String> set = new ArrayList<String>();
		set.add("01");
		set.add("03");
		set.add("11");
		set.add("33");
		set.add("12");
		set.add("32");
		set.add("22");
		
		List<String> expectedResult = new ArrayList<String>();
		expectedResult.add("01");
		expectedResult.add("11");
		expectedResult.add("12");
		
		assertEquals(expectedResult, generator.findSmallersFromElementPairs(set, 4));
	}

}
