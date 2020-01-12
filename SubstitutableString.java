public interface SubstitutableString {
	/**
	 * 
	 * @param a
	 * @return the earliest substring delimited by parenthesis that contains no parenthesis.
	 */
	String extractFirstParen(String a); // given a string returns the earliest substring delimited by parenthesis that contains no parenthesis.
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @param c
	 * @return a string equal to a, *except* that every occurance of b in a is substituted for by c.
	 */
	String substituteAll(String a, String b, String c); // return a string equal to a, *except* that every occurance of b in a is substituted for by c.	
}