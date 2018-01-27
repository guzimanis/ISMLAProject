package com.ws1718.ismla.server.transliteration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.servlet.ServletContext;

import com.google.gwt.dev.cfg.ResourceLoader;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LanguageTransliterator extends RemoteServiceServlet{
	
	/**
	 * transcription from latin to russian 
	 * 
	 * @param input latin token
	 * @return russian token
	 */
	public String transcribeToRussian(String input) {
		
		//auslagern in util package
		InputStream resourceStream = ResourceLoader.class.getResourceAsStream("/WEB-INF/resources/transcription/Russian_translit.txt");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(resourceStream));

		HashMap<String, String> latinToRussianCharacters = new HashMap<>();

		// read file into map
		String line;
		try {

			// read in
			while ((line = reader.readLine()) != null && line.length() > 0) {
				
					String[] cols = line.split("\t");
					
					String russianCharacter = cols[0].trim();
					String russianTranslit = cols[1];

					String[] russianTranslitSymbols = russianTranslit.split("\\s+");

					for (String phon : russianTranslitSymbols) {
						latinToRussianCharacters.put(phon, russianCharacter);
					}
				}
	

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	 * @return phonetic token 
	 */
	public String transcribeArabicToPhonetic(String input) {

		//auslagern util
		
		InputStream resourceStream = getServletContext().getResourceAsStream("/WEB-INF/resources/transcription/Arabic_chat.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(resourceStream));

		HashMap<String, ArrayList<String>> characterToPhonSymobls = new HashMap<>();

		// read file into map
		String line;
		try {

			// read in
			while ((line = reader.readLine()) != null && line.length() > 0) {
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

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
