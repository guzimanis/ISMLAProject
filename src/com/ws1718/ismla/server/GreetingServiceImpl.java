package com.ws1718.ismla.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ws1718.ismla.client.GreetingService;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	/**
	 * arabic transcription
	 * 
	 * @param input
	 * @return
	 */
	public String transcribeToRussian(String input) {

		String russianTranscription = "";

		InputStream resourceStream = getServletContext()
				.getResourceAsStream("/WEB-INF/resources/transcription/Russian_translit.txt");
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

					for (String s : russianTranslitSymbols) {
						System.out.print(s + " # ");
					}
					System.out.println();

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
				
				System.out.println(subCheck);
				
				if (latinToRussianCharacters.containsKey(subCheck)) {
					sb.append(latinToRussianCharacters.get(subCheck));
					
					i += k;
					break;
				}
			}

			
		}

		russianTranscription += sb.toString();

		return russianTranscription;

	}

	/**
	 * arabic transcription
	 * 
	 * @param input
	 * @return
	 */
	public String transcribeArabicToPhonetic(String input) {

		String phoneticInputRepresentation = "";

		InputStream resourceStream = getServletContext()
				.getResourceAsStream("/WEB-INF/resources/transcription/Arabic_chat.txt");
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

				System.out.println("col2 = " + phoneticArabic);

				String[] latinArabicCharacters = latinArabic.split("\\s+");
				String[] phoneticArabicSymbols = phoneticArabic.split("\\s+");

				System.out.println("size of phonlist = " + phoneticArabicSymbols.length);
				for (String s : phoneticArabicSymbols) {
					System.out.print(s + " # ");
				}
				System.out.println();

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
		StringBuilder sbAr = new StringBuilder();
		// iter over characters of input word
		for (int i = 0; i < input.length(); i++) {
			String c = input.charAt(i) + "";

			if (characterToPhonSymobls.containsKey(c)) {
				sbAr.append(characterToPhonSymobls.get(c).get(0));
			}
		}

		phoneticInputRepresentation += sbAr.toString();

		return phoneticInputRepresentation;

	}

	public String greetServer(String input) throws IllegalArgumentException {
		String result = "";

		String arabicPhon = transcribeArabicToPhonetic(input);
		String russiaRepresentation = transcribeToRussian(input);

		result += arabicPhon + "\n";
		result += russiaRepresentation + "\n";

		return result;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html
	 *            the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
}
