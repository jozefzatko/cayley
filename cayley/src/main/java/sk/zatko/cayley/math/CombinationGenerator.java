package sk.zatko.cayley.math;

import java.util.ArrayList;
import java.util.List;

import org.paukov.combinatorics.Factory;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;

/**
 * Use com.googlecode.combinatoricslib to generate combinations of given elements
 * 
 * @author Jozef Zaťko
 */
public class CombinationGenerator<E> {

	/**
	 * Generate combinations of given elements
	 */
	public List<List<E>> generateCombinations(List<E> elements, int setSize) {
		
		List<List<E>> results = new ArrayList<List<E>>();
		
		ICombinatoricsVector<E> initialVector = Factory.createVector(elements);
		Generator<E> generator = Factory.createSimpleCombinationGenerator(initialVector, setSize);
		
		for (ICombinatoricsVector<E> combination : generator) {
			results.add(combination.getVector());
		}
		
		return results;
	}
	
}
