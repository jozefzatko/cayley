package sk.zatko.cayley.math;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Generate set of generating groups for Cayley graph
 * 
 * @author Jozef Zaťko
 */
public class GroupGenerator {
	
	/**
	 * Generate List of generating sets based on size of generating set and size of cyclic group Zn x Zn
	 */
	public List<GeneratingSet> generate(List<String> vertexSet, int genSetSize, int n) {
		
		boolean genSetSizeEven = false;
		boolean nEven = false;
		
		if (genSetSize % 2 == 0) {
			genSetSizeEven = true;
		}
		
		if (n % 2 == 0) {
			nEven = true;
		}
		
		if (genSetSizeEven && nEven) {
			
			return genSetSizeEvenAndNEven(vertexSet, genSetSize, n);
		}
		
		if (genSetSizeEven && !nEven) {
			
			return genSetSizeEvenAndNOdd(vertexSet, genSetSize, n);
		}
		
		if (!genSetSizeEven && nEven) {
			
			return genSetSizeOddAndNEven(vertexSet, genSetSize, n);
		}

		return new ArrayList<GeneratingSet>();
		
	}
	
	/**
	 * Return valid generating sets for even size of generating set and even n
	 */
	public List<GeneratingSet> genSetSizeEvenAndNEven(List<String> vertexSet, int genSetSize, int n) {
		
		List<GeneratingSet> generatingSets = new ArrayList<GeneratingSet>();

		List<String> firstHalf = findSmallersFromElementPairs(vertexSet, n);
		List<String> withoutInverse = findElementsWithoutInverseElement(vertexSet, n);
		
		// genSetSize == 2 is a special case
		// 1 element from first half + its inverse, or 2 elements from withoutInverse 
		if (genSetSize == 2) {
			
			List<List<String>> combinations;
			
			combinations = new CombinationGenerator<String>().generateCombinations(firstHalf, genSetSize/2);
			generatingSets.addAll(transformAndAddInvereElements(combinations, n));
			
			combinations = new CombinationGenerator<String>().generateCombinations(withoutInverse, genSetSize);
			generatingSets.addAll(transform(combinations, n));
			
			return generatingSets;
		}
		
		// 2 elements from withoutInverse and genSetSize-2/2 elements from first half + their inverses 
		List<List<String>> fromWithoutInverse = new CombinationGenerator<String>().generateCombinations(withoutInverse, 2);
		List<List<String>> fromFirstHalf = new CombinationGenerator<String>().generateCombinations(firstHalf, (genSetSize-2) / 2);
		
		generatingSets = transformAndAddInvereElements(combine(fromFirstHalf, fromWithoutInverse), n);
		
		return generatingSets;
	}
	
	/**
	 * Return valid generating sets for even size of generating set and odd n
	 */
	public List<GeneratingSet> genSetSizeEvenAndNOdd(List<String> vertexSet, int genSetSize, int n) {
		
		List<String> firstHalf = findSmallersFromElementPairs(vertexSet, n);
		
		List<List<String>> combinations = new CombinationGenerator<String>().generateCombinations(firstHalf, genSetSize/2);
		
		return transformAndAddInvereElements(combinations, n);
	}
	
	/**
	 * Return valid generating sets for odd size of generating set and even n
	 */
	public List<GeneratingSet> genSetSizeOddAndNEven(List<String> vertexSet, int genSetSize, int n) {
		
		List<GeneratingSet> generatingSets = new ArrayList<GeneratingSet>();
		
		List<String> firstHalf = findSmallersFromElementPairs(vertexSet, n);
		List<String> withoutInverse = findElementsWithoutInverseElement(vertexSet, n);
		
		// if size of generating set is 1, then use only elements without inverse element
		if (genSetSize == 1) {
			
			List<List<String>> combinations = new CombinationGenerator<String>().generateCombinations(withoutInverse, genSetSize);
			generatingSets.addAll(transform(combinations, n));
			
			return generatingSets;
		}
		
		// if size of generating set is 3, then use 3 elements without inverse element
		// or 1 element without inverse element and 1 pair of elements
		if (genSetSize == 3) {
			
			List<List<String>> fromWithoutInverse = new CombinationGenerator<String>().generateCombinations(withoutInverse, 3);
			generatingSets.addAll(transform(fromWithoutInverse, n));
			
			
			fromWithoutInverse = new CombinationGenerator<String>().generateCombinations(withoutInverse, 1);
			List<List<String>> fromFirstHalf = new CombinationGenerator<String>().generateCombinations(firstHalf, 1);
			generatingSets.addAll(transformAndAddInvereElements(combine(fromWithoutInverse, fromFirstHalf), n));
			
			return generatingSets;
		}
		
		// use 3 elements without inverse element and (genSetSize-3)/2 pairs of elements
		// or 1 element without inverse element and (genSetSize-1)/2 pairs of elements
		
		List<List<String>> fromWithoutInverse;
		List<List<String>> fromFirstHalf;
		
		fromWithoutInverse = new CombinationGenerator<String>().generateCombinations(withoutInverse, 3);
		fromFirstHalf = new CombinationGenerator<String>().generateCombinations(firstHalf, (genSetSize-3)/2);
		generatingSets.addAll(transformAndAddInvereElements(combine(fromWithoutInverse, fromFirstHalf), n));
		
		fromWithoutInverse = new CombinationGenerator<String>().generateCombinations(withoutInverse, 1);
		fromFirstHalf = new CombinationGenerator<String>().generateCombinations(firstHalf, (genSetSize-1)/2);
		generatingSets.addAll(transformAndAddInvereElements(combine(fromWithoutInverse, fromFirstHalf), n));
		
		return generatingSets;
	}
	
	/**
	 * Combine 2 Lists of lists (Cartesian multiplication)
	 */
	public List<List<String>> combine(List<List<String>> first, List<List<String>> second) {
		
		List<List<String>> mergedList = new ArrayList<List<String>>();
		
		for (List<String> i : first) {
			for (List<String> j : second) {
				
				List<String> merged = new ArrayList<String>();
				merged.addAll(i);
				merged.addAll(j);

				mergedList.add(merged);
			}
		}
		
		return mergedList;
	}
	
	/**
	 * Transform list of lists into list of generating sets and add inverse elements
	 */
	public List<GeneratingSet> transformAndAddInvereElements(List<List<String>> combinations, int genSetSize) {
		
		List<GeneratingSet> result = new ArrayList<GeneratingSet>();
		
		for (List<String> combination : combinations) {
			
			combination = addInverseElements(combination, genSetSize);
			
			if (isGeneratingSet(combination, genSetSize)) {
				result.add(new GeneratingSet(combination));
			}	
		}
		
		return result;
	}
	
	/**
	 * Transform list of lists into list of generating sets
	 */
	public List<GeneratingSet> transform(List<List<String>> combinations, int genSetSize) {
		
		List<GeneratingSet> result = new ArrayList<GeneratingSet>();
		
		for (List<String> combination : combinations) {
			
			if (isGeneratingSet(combination, genSetSize)) {
				result.add(new GeneratingSet(combination));
			}	
		}
		
		return result;
	}
	
	/**
	 * Find lexically smaller element from pairs of elements inverse to each other
	 */
	public List<String> findSmallersFromElementPairs(List<String> vertexSet, int groupSize) {
		
		List<String> resultSet = new ArrayList<String>();
		
		Group group = new Group(groupSize);
		String nString = (groupSize + 1)/2 + "";
		
		for(String element : vertexSet) {
			
			if ((element.substring(0,1).compareTo(nString) < 0)
					&& (("0".equals(element.substring(0,1))) == false)
					&& (("00".equals(group.add(element, element))) == false)) {
				resultSet.add(element);
			}
			
			if ("0".equals(element.substring(0,1)) && (element.substring(1,2).compareTo(nString) < 0)) {
				resultSet.add(element);
			}
		}
		
		return resultSet;
	}
	
	/**
	 * Find elements without inverse element
	 */
	public List<String> findElementsWithoutInverseElement(List<String> vertexSet, int groupSize) {
		
		List<String> resultSet = new ArrayList<String>();
				
		Group group = new Group(groupSize);
		
		for(String element : vertexSet) {
			
			if ("00".equals(group.add(element, element))) {
				resultSet.add(element);
			}
		}
		
		return resultSet;
	}
	
	/**
	 * Add inverse elements into list
	 */
	public List<String> addInverseElements(List<String> elements, int groupSize) {
		
		List<String> inverse = new ArrayList<>(elements.size());
		
		Group group = new Group(groupSize);
		
		for(String s : elements) {
			if ("00".equals(group.add(s, s)) == false) {
				inverse.add(group.findInverseElement(s));
			}
		}
		
		elements.addAll(inverse);
		
		return elements;
	}
	
	/**
	 * Check if given set is a valid generating set for Cayley graphs of cyclic groups Zn x Zn
	 */
	public boolean isGeneratingSet(List<String> combination, int groupSize) {
		
		List<String> set = new ArrayList<String>(combination);
		
		Group group = new Group(groupSize);
		
		while(set.size() > 0) {
			
			// Cannot contain neutral element (00)
			for (String s : set) {
				if("00".equals(s)) {
					return false;
				}
			}
			
			// Cannot have duplicate elements
			if (hasDuplicateElements(set)) {
				return false;
			}
			
			String first = set.get(0);
			
			// Element does not have inverse element
			if (group.add(first, first).equals("00")) {
				
				set.remove(0);
				continue;
			}
			
			// Cannot have single element without inverse one
			if (set.size() == 1) {
				return false;
			}
			
			boolean hasInverseElement = false;
			int inverseElementIndex = -1;
			
			for (int i=1; i<set.size(); i++) {
				
				if (group.add(first, set.get(i)).equals("00")) {
					hasInverseElement = true;
					inverseElementIndex = i;
					
					break;
				}
			}
			
			if (hasInverseElement == false) {
				return false;
			}
			
			set.remove(0);
			set.remove(inverseElementIndex - 1);
		}
		
		return true;
	}
	
	/**
	 * Check if List has duplicate elements
	 */
	public boolean hasDuplicateElements(List<String> list) {
		
		Set<String> set = new HashSet<>();
		
		for (String s : list) {
			
			if (set.add(s) == false) {
				return true;
			}
		}
		
		return false;
	}
}
