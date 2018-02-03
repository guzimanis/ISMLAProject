package com.ws1718.ismla.server.distance;

public class SimilarityMeasures {
	
	/**
	 * calculates the longest common substring
	 * which can occur anywhere in the string
	 * Example:
	 * s1 = xxxabcdefgh
	 * s2 = abcdefghxxx
	 * This will find the xxx although it is not aligned at the same position inside the two strings.
	 * 
	 * @param s1 first string to compare
	 * @param s2 second string to compare
	 * @return the longest string which appears in both s1 and s2
	 */
	public static String longestCommonSubstring(String s1, String s2) {
		int start = 0;
		int max = 0;
		//iter over first string
		for (int i = 0; i < s1.length(); i++) {
			//iter over second string
			for (int j = 0; j < s2.length(); j++) {
				int common = 0;
				//as long as characters are equal -> increase counter
				while (s1.charAt(i + common) == s2.charAt(j + common)) {
					common++;
					if (((i + common) >= s1.length()) || ((j + common) >= s2.length()))
						break;
				}
				//update the longest
				if (common > max) {
					max = common;
					start = i;
				}
			}
		}
		return s1.substring(start, (start + max));
	}
}
