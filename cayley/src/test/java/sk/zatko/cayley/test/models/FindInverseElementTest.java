package sk.zatko.cayley.test.models;

import static org.junit.Assert.*;

import org.junit.Test;

import sk.zatko.cayley.math.Group;

public class FindInverseElementTest {

	@Test
	public void testFindInverseElement1() {
		
		Group group = new Group(4);
		
		assertEquals("00", group.findInverseElement("00"));
	}
	
	@Test
	public void testFindInverseElement2() {
		
		Group group = new Group(4);
		
		assertEquals("02", group.findInverseElement("02"));
	}
	
	@Test
	public void testFindInverseElement3() {
		
		Group group = new Group(4);
		
		assertEquals("03", group.findInverseElement("01"));
	}
	
	@Test
	public void testFindInverseElement4() {
		
		Group group = new Group(5);
		
		assertEquals("14", group.findInverseElement("41"));
	}
	
	@Test
	public void testFindInverseElement5() {
		
		Group group = new Group(6);
		
		assertEquals("33", group.findInverseElement("33"));
	}

}
