package sk.zatko.cayley.test.models;

import static org.junit.Assert.*;

import org.junit.Test;

import sk.zatko.cayley.math.Group;

public class GroupAddTest {

	@Test
	public void addTest1() {
		
		Group group = new Group(3);
		
		String s1 = "12";
		String s2 = "22";
		
		String result = group.add(s1, s2);
		String expected = "01";
		
		assertEquals(expected, result);
	}
	
	@Test
	public void addTest2() {
		
		Group group = new Group(3);
		
		String s1 = "02";
		String s2 = "22";
		
		String result = group.add(s1, s2);
		String expected = "21";
		
		assertEquals(expected, result);
	}
	
	@Test
	public void addTest3() {
		
		Group group = new Group(8);
		
		String s1 = "75";
		String s2 = "75";
		
		String result = group.add(s1, s2);
		String expected = "62";
		
		assertEquals(expected, result);
	}

}
