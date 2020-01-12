import java.util.Stack;

public class LogicalSentence implements SubstitutableString{
	private String sentence;
	private static LogicalSentence logRef = new LogicalSentence("a");
	
	public static final String[][] operatorKey = {
			{"!", "~"},
			{">", "=>"},
			{"=", "<>"},
	};
	
	public static final Operation[] operator = {
			new Operation("!", (op1, op2) -> !op2), //This is filler. Negation takes only one operand, so this operation is unused
			new Operation("&", (op1, op2) -> op1 && op2), 
			new Operation("|", (op1, op2) -> op1 || op2), 
			new Operation(">", (op1, op2) -> (op1 == op2) || op2), 
			new Operation("=", (op1, op2) -> op1 == op2)
	};
	
	/**
	 * Constructs a LogicalSentence object if it is legal
	 * @param String which represents the LogicalSentence
	 */
	LogicalSentence(String str){
		//remove white space
		str = str.replace(" ", "");
		if(legal(str))
			sentence = str;
	}
	
	//Some makeOperator() functions. Essentially takes another operand and returns a complex logical sentence in the form (this, operator, parameter-operand)
	public LogicalSentence makeNegation() { return new LogicalSentence(operator[0].getOperator() + this.sentence); }
	public LogicalSentence makeConjunction(LogicalSentence a) { return new LogicalSentence(sentence + operator[1].getOperator() + a.sentence); }
	public LogicalSentence makeDisjunction(LogicalSentence a) { return new LogicalSentence(sentence + operator[2].getOperator() + a.sentence); }
	public LogicalSentence makeImplication(LogicalSentence a) {	return new LogicalSentence(sentence + operator[3].getOperator() + a.sentence); }
	public LogicalSentence makeEquivilant(LogicalSentence a) { return new LogicalSentence(sentence + operator[4].getOperator() + a.sentence); }
	
	/**
	 * Sets the LogicalSentence if legal
	 * @param A string for the LogicalSentence
	 */
	public void setSentence(String str) { 
		if(legal(str))
			sentence = str;
	}
	
	/**
	 * @return the reformated string version of the logical sentence
	 */
	public String toString() {
		return reformat(sentence);
//		return sentence;
	}
	
	/**
	 * Checks if a LogicalSentence object is legal
	 * @return True if legal, false if not legal
	 */
	public boolean isLegal() { return legal(this.sentence); }
	
	private static boolean legal(String str) {
		if(!matchingParen(str))
			return false;
		//we just can remove all the parentheses and then check for legality
		str = str.replace("(", "");
		str = str.replace(")", "");
		return complex(str);
	}
	
	private static boolean complex(String str) {
		if(simple(str))
			return true;
		//iterates through array of operators
		String op = "";
		for(int k = 4; k >= 1; k--) {
			op = operator[k].getOperator();
			if(str.contains(op))
				return complex(str.substring(0, str.indexOf(op))) &&
						complex(str.substring(str.indexOf(op) + 1));
		}
		//checks negation
		op = operator[0].getOperator();
		int index = str.indexOf(op);
		if(index == 0) //has to equal zero or else illegal
			return complex(str.substring(index + 1));
		//returns false if final result is not simple
		return false;
	}
	
	private static boolean simple(String str) { return str.length() == 1 && Character.isLetter(str.charAt(0)); }
	
	/**
	 * Checks if given LogicalSentence object is simple
	 * @param A LogicalSentence object to check if it is simple
	 * @return True if simple, false if illegal
	 */
	public static boolean isSimple(LogicalSentence a) { return simple(a.sentence); }
	
	private boolean evaluate(String str, TruthAssignment b) {
		//the str may equal either "true" or "false" because inside parentheses are evaluated as the toString() of their boolean values
		if(str.equals("true"))
			return true;
		if(str.equals("false"))
			return false;
		if(hasParen(str))
			return evaluate(
					str.substring(0, firstParenOpenIndex(str)) //first part before parentheses
					+ evaluate(logRef.extractFirstParen(str), b) //this becomes a string of either true or false because of concatenation
					+ str.substring(firstParenCloseIndex(str) + 1), b); //part after parentheses
		//iterates through array of operators
		String op = "";
		for(int k = 4; k >= 1; k--) {
			op = operator[k].getOperator();
			if(str.contains(op))
				return operator[k].getFunction().apply(
						evaluate(str.substring(0, str.lastIndexOf(op)), b), 
						evaluate(str.substring(str.lastIndexOf(op) + 1), b));
		}
		//checks for negation. only checks if it has ! because there will only be the not operator left at the end
		op = operator[0].getOperator();
		if(str.contains(op))
			return !evaluate(str.substring(str.indexOf(op) + 1), b);
		//here we reach a simple sentence, so return its truth value
		return b.truthValue(str);
	}
	/**
	 * Evaluates the overall truth value of the given LogicalSentence
	 * @param The TruthAssignment for the LogicalSentence
	 * @return The evaluated LogicalSentence based on TruthAssignment values and operators
	 */
	public boolean evaluate(TruthAssignment b) {
		return evaluate(sentence, b);
	}
	
	/**
	 * @param The String to extract the first inner-most parentheses from
	 * @return The inside of the first inner-most parentheses
	 */
	@Override
	public String extractFirstParen(String str) {
		for(int k = 0, j = -1; k < str.length(); k++) {
			if(str.charAt(k) == '(')
				j = k; //stores position of first parentheses
			if(j != -1 && str.charAt(k) == ')') //if first close parentheses is found
				return str.substring(j + 1, k);
		}
		return str;
	}
	
	/**
	 * Replaces every instance of String b with String c in String a
	 * @return The new String a
	 */
	@Override
	public String substituteAll(String a, String b, String c) {
		return a.replace(b, c);
	}
	
	//checks if there are parentheses are balanced using a stack
	private static boolean matchingParen(String str) {
		Stack<Character> stack = new Stack<Character>();
		for(int k = 0; k < str.length(); k++) {
			if(str.charAt(k) == '(')
				stack.push('(');
			if(str.charAt(k) == ')' && (stack.isEmpty() || stack.pop() != '('))
				return false;
		}
		return stack.isEmpty();
	}
	
	private static boolean hasParen(String str) {
		if(str.contains("(") || str.contains(")"))
			return true;
		return false;
	}
	
	/**
	 * Replaces the single character logical operators with the standarized logical operators based on operatorKey[]
	 * @param String to reformat
	 * @return The reformated String
	 */
	private static String reformat(String str) {
		String str2 = "" + str; //copies string
		for(int k = operatorKey.length - 1; k >= 0; k--) {
			str2 = str2.replace(operatorKey[k][0], operatorKey[k][1]);
		}
		return str2;
	}
	
	//based on extractFirstParen()
	private static int firstParenOpenIndex(String str) {
		for(int k = 0, j = -1; k < str.length(); k++) {
			if(str.charAt(k) == '(')
				j = k;
			if(j != -1 && str.charAt(k) == ')')
				return j;
		}
		return -1;
	}
	
	//also based on extractFirstParen()
	private static int firstParenCloseIndex(String str) {
		for(int k = 0, j = -1; k < str.length(); k++) {
			if(str.charAt(k) == '(')
				j = k;
			if(j != -1 && str.charAt(k) == ')')
				return k;
		}
		return -1;
	}

}