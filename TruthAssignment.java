import java.util.ArrayList;

public class TruthAssignment {
	
	private ArrayList<Boolean> boolArr = new ArrayList<Boolean>(); //stores truth values
	private ArrayList<Object> objArr = new ArrayList<Object>(); //stores the array of objects, and assignment is based on boolArr and objArr having the same indexes
	
	/**
	 * Constructs a default TruthAssignment object
	 */
	TruthAssignment(){
		boolArr.add(false);
		objArr.add(new PropositionConstant("a", new LogicalSentence("a")));
	}
	
	/**
	 * Constructs a TruthAssignemnt object given an array of Strings and sets all their values to false
	 * @param The array of Strings
	 */
	TruthAssignment(String[] a){
		for(int k = 0; k < a.length; k++) {
			boolArr.add(false);
			objArr.add(a[k]);
		}
	}
	
	/**
	 * Constructs a TruthAssignemnt object given an array of LogicalSentences and sets all their values to false
	 * @param The array of LogicalSentences
	 */
	TruthAssignment(LogicalSentence[] a){
		for(int k = 0; k < a.length; k++) {
			boolArr.add(false);
			objArr.add(a[k]);
		}
	}
	
	/**
	 * Constructs a TruthAssignemnt object given an array of objects and sets all their truth values according to the given array of booleans
	 * @param The array of objects and array of booleans
	 */
	TruthAssignment(Object[] a, boolean[] b){
		for(int k = 0; k < a.length; k++) {
			boolArr.add(b[k]);
			objArr.add(a[k]);
		}
	}
	
	/**
	 * 
	 * @param The String to check
	 * @return Truth value of a string
	 */
	public boolean truthValue(String str) {
		return boolArr.get(objArr.indexOf(str));
	}
	/**
	 * 
	 * @param The LogicalSentence to check
	 * @return Truth value of a LogicalSentence
	 */
	public boolean truthValue(LogicalSentence a) {
		return boolArr.get(objArr.indexOf(a));
	}
	/**
	 * 
	 * @param The PropositionalConstant to check
	 * @return Truth value of a PropositionalConstant
	 */
	public boolean truthValue(PropositionConstant a) {
		return boolArr.get(objArr.indexOf(a));
	}
}