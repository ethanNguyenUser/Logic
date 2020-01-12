public class TestLogicalSentence {
	
	static final String LINE = "--------------------------------------------------------------------------";
	
	public static void main(String[] args) {
		LogicalSentence a = new LogicalSentence("a");
		LogicalSentence b = new LogicalSentence("b");
		LogicalSentence c = new LogicalSentence("c");
		
		System.out.println(LINE);
		System.out.println("test makeOperator()");
		System.out.println("a is: " + a);
		System.out.println("Negation of a: " + a.makeNegation());
		System.out.println("Conjunction of a and b: " + a.makeConjunction(b));
		System.out.println("Disjunction of a and b: " + a.makeDisjunction(b));
		System.out.println("Implication of a to b: " + a.makeImplication(b));
		System.out.println("Equivalance of a and b: " + a.makeEquivilant(b));
		
		System.out.println(LINE);
		System.out.println("test TruthAssignment with String");
		String[] strArr = {"x", "y", "z"};
		TruthAssignment strTruthAssign = new TruthAssignment(strArr);
		System.out.println("x's value in strTruthAssign: " + strTruthAssign.truthValue("x"));
		
		System.out.println(LINE);
		System.out.println("test TruthAssignment with Logical Sentences");
		LogicalSentence[] logArr = {new LogicalSentence("x"), new LogicalSentence("y"), new LogicalSentence("z")};
		TruthAssignment logTruthAssign = new TruthAssignment(logArr);
		System.out.println("x's value in logTruthAssign: " + logTruthAssign.truthValue(logArr[0]));
		
		System.out.println(LINE);
		System.out.println("test TruthAssignment with PropositionConstant and boolean array");
		PropositionConstant[] propArr = {
				new PropositionConstant("x", a),
				new PropositionConstant("y", b),
				new PropositionConstant("z", c)
		};
		boolean[] boolArr = {true, false, true};
		TruthAssignment propTruthAssign = new TruthAssignment(propArr, boolArr);
		System.out.println("x's value in propTruthAssign: " + propTruthAssign.truthValue(propArr[0]));
		
		System.out.println(LINE);
		System.out.println("test extractFirstParen()");
		String str = "((a)(b))";
		System.out.println("extractFirstParen() of " + str + " is " + a.extractFirstParen(str));
		str = "(a)";
		System.out.println("extractFirstParen() of " + str + " is " + a.extractFirstParen(str));
		
		System.out.println(LINE);
		System.out.println("test substituteAll()");
		String strA = "abcde";
		System.out.println("strA before substituteAll() with b: " + strA);
		String strB = a.substituteAll(strA, "b", "c");
		System.out.println("strA after substituteAll() with b: " + strB);
		
		System.out.println(LINE);
		System.out.println("test legal() without parentheses");
		System.out.println("a: " + a);
		System.out.println("a is legal: " + a.isLegal());
		a = b.makeConjunction(a.makeEquivilant(b));
		System.out.println("a: " + a);
		System.out.println("a is legal: " + a.isLegal());
		a.setSentence("a|b&c");
		System.out.println("a: " + a);
		System.out.println("a is legal: " + a.isLegal());
		a.setSentence("!a|!b=c>!n");
		System.out.println("a: " + a);
		System.out.println("a is legal: " + a.isLegal());
		a.setSentence("a!");
		System.out.println("a: " + a);
		System.out.println("a is legal: " + a.isLegal());
		a.setSentence("ab");
		System.out.println("a: " + a);
		System.out.println("a is legal: " + a.isLegal());
		a.setSentence("a!b");
		System.out.println("a: " + a);
		System.out.println("a is legal: " + a.isLegal());
		a.setSentence("&a|c");
		System.out.println("a: " + a);
		System.out.println("a is legal: " + a.isLegal());
		a.setSentence("a!b!");
		System.out.println("a: " + a);
		System.out.println("a is legal: " + a.isLegal());
		a.setSentence("b b");
		System.out.println("a: " + a);
		System.out.println("a is legal: " + a.isLegal());
		a.setSentence("!b!|a");
		System.out.println("a: " + a);
		System.out.println("a is legal: " + a.isLegal());
		
		System.out.println(LINE);
		System.out.println("test legal() with parentheses");
		a.setSentence("(a)");
		System.out.println("a: " + a);
		System.out.println("a is legal: " + a.isLegal());
		a.setSentence("((a|b))");
		System.out.println("a: " + a);
		System.out.println("a is legal: " + a.isLegal());
		a.setSentence("((a|b)>!(!b&c))");
		System.out.println("a: " + a);
		System.out.println("a is legal: " + a.isLegal());
		a.setSentence("!(!(b))&c");
		System.out.println("a: " + a);
		System.out.println("a is legal: " + a.isLegal());
		a.setSentence("(a)>(b)&(c|a)");
		System.out.println("a: " + a);
		System.out.println("a is legal: " + a.isLegal());
		a.setSentence("(a)>(b)&|(c|a)");
		System.out.println("a: " + a);
		System.out.println("a is legal: " + a.isLegal());
		a.setSentence("(a|b|c)&");
		System.out.println("a: " + a);
		System.out.println("a is legal: " + a.isLegal());
		a.setSentence("()");
		System.out.println("a: " + a);
		System.out.println("a is legal: " + a.isLegal());
		
		System.out.println(LINE);
		String[] stringArr = {"b", "c"};
		boolean[] boolArr2 = {true, false};
		TruthAssignment truthAssign2 = new TruthAssignment(stringArr, boolArr2);
		for(int k = 0; k < stringArr.length; k++) {
			System.out.println(stringArr[k] + ": " + boolArr2[k]);
		}
		a.setSentence("b&c");
		System.out.println("a: " + a);
		System.out.println("a value: " + a.evaluate(truthAssign2));
		a.setSentence("b|c");
		System.out.println("a: " + a);
		System.out.println("a value: " + a.evaluate(truthAssign2));
		a.setSentence("b>c");
		System.out.println("a: " + a);
		System.out.println("a value: " + a.evaluate(truthAssign2));
		a.setSentence("b=c");
		System.out.println("a: " + a);
		System.out.println("a value: " + a.evaluate(truthAssign2));
		a.setSentence("b&!c");
		System.out.println("a: " + a);
		System.out.println("a value: " + a.evaluate(truthAssign2));
		a.setSentence("c>c>c");
		System.out.println("a: " + a);
		System.out.println("a value: " + a.evaluate(truthAssign2));
		
		System.out.println(LINE);
		System.out.println("test evaluate() with parentheses");
		a.setSentence("!(b|c)");
		System.out.println("a: " + a);
		System.out.println("a value: " + a.evaluate(truthAssign2));
		a.setSentence("(b|b)&c");
		System.out.println("a: " + a);
		System.out.println("a value: " + a.evaluate(truthAssign2));
		a.setSentence("!(c|(b=b)&c)");
		System.out.println("a: " + a);
		System.out.println("a value: " + a.evaluate(truthAssign2));
		a.setSentence("!(b=c)&!c");
		System.out.println("a: " + a);
		System.out.println("a value: " + a.evaluate(truthAssign2));
		a.setSentence("b&!(b&c)");
		System.out.println("a: " + a);
		System.out.println("a value: " + a.evaluate(truthAssign2));
	}
}
