package sk.zatko.cayley.math;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of Cayley graph generating set
 * Subset of cyclic group Zn x Zn
 * 
 * @author Jozef Zaťko
 *
 */
public class GeneratingSet {

	private List<String> values;

	public GeneratingSet() {
		
		this.values = new ArrayList<String>();
	}
	
	public GeneratingSet(List<String> values) {
		
		this.values = values;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("{ ");
		
		for (String s : this.values) {
			sb.append("(" + s + ") ");
		}
		
		sb.append("}");
		
		return sb.toString();
	}
	
	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

}
