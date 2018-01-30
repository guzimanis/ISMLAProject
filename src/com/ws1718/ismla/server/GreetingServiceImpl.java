package com.ws1718.ismla.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sun.webkit.ThemeClient;
import com.ws1718.ismla.client.GreetingService;
import com.ws1718.ismla.server.transliteration.LanguageTransliterator;
import com.ws1718.ismla.server.transliteration.PhoneticTransliterator;
import com.ws1718.ismla.server.transliteration.SourceTransliteration;
import com.ws1718.ismla.shared.LanguageCodes;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	// transliteration source
	private static final String TRANSLIT_LOCATION = "/transcription/";
	private static final String ARABIC_TRANSLIT = TRANSLIT_LOCATION + "Arabic_chat.txt";
	private static final String RUSSIAN_TRANSLIT = TRANSLIT_LOCATION + "Russian_translit.txt";

	// tabu lists source
	private static final String TABU_LOCATION = "/tabuLists/";
	private static final String PREFIX_TABU = TABU_LOCATION + "tabu_";
	private static final String SUFFIX_TABU = ".txt";
	
	//ipa list resources
	private static final String IPA_LOCATION = "/IPA/";
	private static final String IPA_SIMPLE = IPA_LOCATION + "IPA_Simple.txt";

	public String greetServer(String input) throws IllegalArgumentException {

		String result = "done";
		
		/*
		 * equivalence classes of IPA
		 */
		Map<String, String> ipaSimple = getMapForColumns(IPA_SIMPLE, 0, 1);
		
		System.out.println("equivalence classes: ");
		System.out.println("------------------------------------------------------------");
		for(String s : ipaSimple.keySet()){
			String value = ipaSimple.get(s);
			System.out.println(s + " -> " + value);
		}
		
		System.out.println();
		System.out.println("phonetic representation of the input character level");
		System.out.println("-------------------------------------------------------------");

		/*
		 * input transformation to phonetic representation
		 */
		final Map<LanguageCodes, String> phonologicalRepresentationsOfInput = getPhonologicalRepresentationsOfInput(input);
		//create
		Map<LanguageCodes, String> simplePhonologicalRepresentationOfInput = new HashMap<>();
		
		// transformation of phonological representation to simplified version
		for (LanguageCodes c : phonologicalRepresentationsOfInput.keySet()) {
			String phonWord = phonologicalRepresentationsOfInput.get(c);
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < phonWord.length(); i++){
				System.out.print(phonWord.charAt(i) + " -> ");
				if(ipaSimple.containsKey(phonWord.charAt(i) + "")){
					sb.append(ipaSimple.get(phonWord.charAt(i) + ""));
					System.out.print(ipaSimple.get(phonWord.charAt(i) + ""));	
				}
				System.out.println();
			}
			System.out.println();
			simplePhonologicalRepresentationOfInput.put(c, sb.toString());
		}
		
		
		System.out.println();
		System.out.println("phonetic representation of the input word level");
		System.out.println("-------------------------------------------------------------");
		//print
		for(LanguageCodes c : phonologicalRepresentationsOfInput.keySet()){
			String originalPhonWord = phonologicalRepresentationsOfInput.get(c);
			String simplifiedPhonWord = simplePhonologicalRepresentationOfInput.get(c);
			System.out.println(originalPhonWord + " -> " + simplifiedPhonWord);
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("phonetic representation of the tabu words");
		System.out.println("#####################################################################################");
		System.out.println();
		System.out.println();
		System.out.println();

		/*
		 * tabu word transformation to phonetic representation
		 */
		final Map<LanguageCodes, List<String>> phonologicalRepresentationOfTabuWords = getPhonologicalRepresentationOfTabuWords();
		//create
		Map<LanguageCodes, List<String>> simplePhonologicalRepresentationOfTabuWords = new HashMap<>();
		
		//transformation of phonological representation to simplified version
		for (LanguageCodes c : phonologicalRepresentationOfTabuWords.keySet()) {
			System.out.println(c + " tabu words on character and word level");
			System.out.println("-----------------------------------------------------------------------");
			final List<String> tabuWords = phonologicalRepresentationOfTabuWords.get(c);
			List<String> simpleTabuWords = new ArrayList<>();
			for(String tabuWord : tabuWords){
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < tabuWord.length(); i++){
					System.out.print(tabuWord.charAt(i) + " -> ");
					if(ipaSimple.containsKey(tabuWord.charAt(i)+"")){
						sb.append(ipaSimple.get(tabuWord.charAt(i)+""));
						System.out.print(ipaSimple.get(tabuWord.charAt(i) + ""));
					}
					System.out.println();
				}
				System.out.println(tabuWord + " -> " + sb.toString());
				System.out.println();
				simpleTabuWords.add(sb.toString());
			}
			System.out.println();
			System.out.println();
			simplePhonologicalRepresentationOfTabuWords.put(c, simpleTabuWords);
			
		}
		
		//print
//		for(LanguageCodes c : phonologicalRepresentationOfTabuWords.keySet()){
//			List<String> tabuWords = phonologicalRepresentationOfTabuWords.get(c);
//			List<String> simpleTabuWords = simplePhonologicalRepresentationOfTabuWords.get(c);
//			
//			System.out.println(c + " tabu words");
//			System.out.println("--------------------------------------------------------------------------");
//			for(int i = 0; i < tabuWords.size(); i++){
//				System.out.println(tabuWords.get(i) + " -> " + simpleTabuWords.get(i));
//			}
//			System.out.println();
//			System.out.println();
//		}
		
		

		return result;
	}

	/**
	 * creates a mapping of all languages to the corresponding phonological
	 * representation of the input
	 * 
	 * @param input
	 *            a word written in latin characters
	 * @return a map
	 */
	private Map<LanguageCodes, String> getPhonologicalRepresentationsOfInput(String input) {

		final List<String> arabicChat = getLinesFromFile(ARABIC_TRANSLIT);
		String arabicInputTransliterated = LanguageTransliterator.transcribeArabicToPhonetic(input, arabicChat);

		final List<String> russianTranslit = getLinesFromFile(RUSSIAN_TRANSLIT);
		String russianInputTransliterated = LanguageTransliterator.transcribeToRussian(input, russianTranslit);

		List<SourceTransliteration> transliterations = new ArrayList<>();
		transliterations.add(new SourceTransliteration(russianInputTransliterated, LanguageCodes.RUS));
		transliterations.add(new SourceTransliteration(arabicInputTransliterated, LanguageCodes.ARA));

		return PhoneticTransliterator.getPhoneticRepresentationForAllLanguages(input, transliterations);
	}

	
	/**
	 * creates a mapping of all languages to the phonetic representation of the corresponding tabu words
	 * @return a map
	 */
	private Map<LanguageCodes, List<String>> getPhonologicalRepresentationOfTabuWords() {
		Map<LanguageCodes, List<String>> rval = new HashMap<LanguageCodes, List<String>>();

		for (LanguageCodes c : LanguageCodes.values()) {
			if (c.equals(LanguageCodes.UNKNOWN) || c.equals(LanguageCodes.EN) || c.equals(LanguageCodes.FRA)
					|| c.equals(LanguageCodes.TH) || c.equals(LanguageCodes.CMN) || c.equals(LanguageCodes.JPN)
					|| c.equals(LanguageCodes.FAS) || c.equals(LanguageCodes.ARA))
				continue;

			final List<String> tabuList = getLinesFromFile(PREFIX_TABU + c + SUFFIX_TABU);
			final List<String> phonetics = PhoneticTransliterator.getPhoneticRepresentationForList(tabuList, c);

			// fill the |language -> tabu words| map
			rval.put(c, phonetics);
		}

		/*
		 * persisch
		 */
		LanguageCodes fas = LanguageCodes.FAS;
		rval.put(fas, phonologicalRepresentationForToTranscribeLanguage(fas, 1));

		/*
		 * japanese
		 */
		LanguageCodes jpn = LanguageCodes.JPN;
		rval.put(jpn, phonologicalRepresentationForToTranscribeLanguage(jpn, 1));

		/*
		 * thai
		 */
		LanguageCodes th = LanguageCodes.TH;
		rval.put(th, getColumnForLanguage(th, 1));

		/*
		 * arabic
		 */
		LanguageCodes ara = LanguageCodes.ARA;
		rval.put(ara, phonologicalRepresentationForToTranscribeLanguage(ara, 1));

		return rval;
	}
	
	/**
	 * creates a list of phonological representations for languages which have to be transcribed via external resources
	 * @param c the specific language
	 * @param col the files column where the respective information can be found
	 * @return a list
	 */
	private List<String> phonologicalRepresentationForToTranscribeLanguage(LanguageCodes c, int col){
		List<String> tabuListTranscribed = getColumnForLanguage(c, col);
		//phonetics
		return PhoneticTransliterator.getPhoneticRepresentationForList(tabuListTranscribed, c);
	}
	
	/**
	 * extracts a specific column from a file for a specific language
	 * @param c language
	 * @param col column of the file
	 * @return list of tokens
	 */
	private List<String> getColumnForLanguage(LanguageCodes c, int col){
		
		final List<String> tabuList = getLinesFromFile(PREFIX_TABU + c + SUFFIX_TABU);
		List<String> tabuListTranscribed = new ArrayList<>();
		for(String s : tabuList){
			String[] cols = s.split("\t");
			if (cols.length > 2) {
				tabuListTranscribed.add(cols[col]);
			}
		}
		
		return tabuListTranscribed;
	}
	
	/**
	 * reades a file and creates a map based on the given columns
	 * where the elements of the one column will be the keys in the map 
	 * and the elements of the other column will be the values in the map
	 * 
	 * @param path to the file
	 * @param keyColumn the column which elements will be the keys
	 * @param valueColumn the column which elements will be the values
	 * @return
	 */
	private Map<String, String> getMapForColumns(String path, int keyColumn, int valueColumn){
		Map<String, String> rval = new HashMap<>();
		
		final List<String> lines = getLinesFromFile(path);
		for(String line : lines){
			String[] cols = line.split("\t");
			int numberOfColumns = cols.length -1;
			if(numberOfColumns >= keyColumn && numberOfColumns >= valueColumn){
				rval.put(cols[keyColumn], cols[valueColumn]);
			}
		}
		
		return rval;
	}

	/***
	 * 
	 * START: reading util
	 * 
	 * 
	 */

	/**
	 * 
	 * creates the input stream to read files from the resource directory
	 * 
	 * @param path
	 * @return
	 */
	private InputStream getInputStream(String path) {
		return getServletContext().getResourceAsStream("/WEB-INF/resources/" + path);
	}

	/**
	 * creates a buffered reader for reading a file from the resource directory
	 * 
	 * @param path
	 * @return
	 */
	private BufferedReader getBufferedReader(String path) {
		return new BufferedReader(new InputStreamReader(getInputStream(path)));
	}

	/**
	 * creates a list of lines for a file from the resource directory
	 * 
	 * @param path
	 * @return
	 */
	private List<String> getLinesFromFile(String path) {
		List<String> rval = new ArrayList<String>();

		BufferedReader reader = getBufferedReader(path);
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				if (line.trim().length() > 0) {
					rval.add(line);
				}
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rval;
	}

	/***
	 * 
	 * END: reading util
	 * 
	 * 
	 */

}
