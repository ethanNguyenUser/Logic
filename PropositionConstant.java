public class PropositionConstant {
	String name;
	LogicalSentence simple;
	PropositionConstant(String s, LogicalSentence a){
		if(s.length() == 1) 
			name = s;
		else
			name = "a";
		if(LogicalSentence.isSimple(a))
			simple = a;
		else
			simple = new LogicalSentence("a");
		
	}
}
