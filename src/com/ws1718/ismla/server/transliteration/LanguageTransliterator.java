package com.ws1718.ismla.server.transliteration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LanguageTransliterator extends RemoteServiceServlet{
	
	/**
	 * transcription from latin to russian 
	 * 
	 * @param input latin token
	 * @param fileLines is a list of read in lines from a resource file
	 * @return russian token
	 */
	public static String transcribeToRussian(String input, List<String> fileLines) {

		HashMap<String, String> latinToRussianCharacters = new HashMap<>();

		for(String line : fileLines){
			
			String[] cols = line.split("\t");
			String russianCharacter = cols[0];
			String russianTranslit = cols[1];

			String[] russianTranslitSymbols = russianTranslit.split("\\s+");

			for (String phon : russianTranslitSymbols) {
				latinToRussianCharacters.put(phon, russianCharacter);
			}
			
		}			


		// apply phonetic transformation
		StringBuilder sb = new StringBuilder();
		// iter over characters of input word
		for (int i = 0; i < input.length(); i++) {
			int end = input.length();
			
			String c = input.substring(i, end);
			
			for(int k = c.length()-1; k >= 0; k--){
				String subCheck = c.substring(0, k+1);
				
				if (latinToRussianCharacters.containsKey(subCheck)) {
					sb.append(latinToRussianCharacters.get(subCheck));
					
					i += k;
					break;
				}
			}	
		}

		return sb.toString();
	}
	
	
	
	/**
	 * latin transcription to phonetic version of arabic token
	 * 
	 * @param input latin token
	 * @param fileLines is a list of read in lines from a resource file
	 * @return phonetic token 
	 */
	public static String transcribeArabicToPhonetic(String input, List<String> fileLines) {

		HashMap<String, ArrayList<String>> characterToPhonSymobls = new HashMap<>();

		for(String line : fileLines){

			String[] cols = line.split("\t");
			String arabic = cols[0];
			String latinArabic = cols[1];
			String phoneticArabic = cols[2];

			String[] latinArabicCharacters = latinArabic.split("\\s+");
			String[] phoneticArabicSymbols = phoneticArabic.split("\\s+");

			// create character to phon mapping
			for (String s : latinArabicCharacters) {
				ArrayList<String> phonSymbols = new ArrayList<>();
				for (String phon : phoneticArabicSymbols) {
					phonSymbols.add(phon);
				}

				characterToPhonSymobls.put(s, phonSymbols);
			}
		}
				
			


		// apply phonetic transformation
		StringBuilder sb = new StringBuilder();
		// iter over characters of input word
		for (int i = 0; i < input.length(); i++) {
			String c = input.charAt(i) + "";

			if (characterToPhonSymobls.containsKey(c)) {
				sb.append(characterToPhonSymobls.get(c).get(0));
			}
		}

		return sb.toString();

	}

}
