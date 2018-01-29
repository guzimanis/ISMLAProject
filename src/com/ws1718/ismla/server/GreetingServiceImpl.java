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
	
	//transliteration source
	private static final String TRANSLIT_LOCATION = "/transcription/";
	private static final String ARABIC_TRANSLIT = TRANSLIT_LOCATION + "Arabic_chat.txt";
	private static final String RUSSIAN_TRANSLIT = TRANSLIT_LOCATION + "Russian_translit.txt";
	
	//tabu lists source
	private static final String TABU_LOCATION = "/tabuLists/";
	private static final String PREFIX = TABU_LOCATION + "tabu_";
	private static final String SUFFIX = ".txt";

	public String greetServer(String input) throws IllegalArgumentException {
		
		String result = "done";
		
		/*
		 * input transformation to phonetic representation
		 */
		final List<String> arabicChat = getLinesFromFile(ARABIC_TRANSLIT);
		String arabicInputTransliterated = LanguageTransliterator.transcribeArabicToPhonetic(input, arabicChat);
		
		final List<String> russianTranslit = getLinesFromFile(RUSSIAN_TRANSLIT);
		String russianInputTransliterated = LanguageTransliterator.transcribeToRussian(input, russianTranslit);
		
		List<SourceTransliteration> transliterations = new ArrayList<>();
		transliterations.add(new SourceTransliteration(russianInputTransliterated, LanguageCodes.RUS));
		transliterations.add(new SourceTransliteration(arabicInputTransliterated, LanguageCodes.ARA));
		
		final Map<LanguageCodes, String> phoneticRepresentations = PhoneticTransliterator.getPhoneticRepresentationForAllLanguages(input, transliterations);
		
		//phonetic mapping of the input for all languages
		for(LanguageCodes c : phoneticRepresentations.keySet()){
			System.out.println(LanguageCodes.fullLanguageName(c));
			System.out.println(phoneticRepresentations.get(c));
			System.out.println();
		}
		
		
		
		/*
		 * tabu word transformation to phonetic representation
		 */
		
		//mapping of languages to their phonetic representation of tabu words
		Map<LanguageCodes, List<String>> phoneticTabuWords = new HashMap<LanguageCodes, List<String>>();
		
		for(LanguageCodes c : LanguageCodes.values()){
			if( c.equals(LanguageCodes.UNKNOWN) || c.equals(LanguageCodes.EN) || c.equals(LanguageCodes.FRA)
					|| c.equals(LanguageCodes.TH)  || c.equals(LanguageCodes.CMN) || c.equals(LanguageCodes.JPN)
					|| c.equals(LanguageCodes.FAS) || c.equals(LanguageCodes.ARA) ) continue;
			
			final List<String> tabuList = getLinesFromFile(PREFIX + c + SUFFIX);
			final List<String> phonetics = PhoneticTransliterator.getPhoneticRepresentationForList(tabuList, c);
			
			//fill the |language -> tabu words| map
			phoneticTabuWords.put(c, phonetics);
		}
		
		
		/*
		 * persisch
		 * 
		 */
		LanguageCodes fas = LanguageCodes.FAS;
		final List<String> tabuListPersian = getLinesFromFile(PREFIX + fas + SUFFIX);
		List<String> tabuListPersianLatin = new ArrayList<>();
		for(String s : tabuListPersian){
			String[] cols = s.split("\t");
			if(cols.length >= 2){
				tabuListPersianLatin.add(cols[1]);
			}
		}
		
		//phonetics
		final List<String> persianPhonetics = PhoneticTransliterator.getPhoneticRepresentationForList(tabuListPersianLatin, LanguageCodes.FAS);
		//fill the |language -> tabu words| map
		phoneticTabuWords.put(fas, persianPhonetics);
		
		
		/*
		 * Japanisch
		 * 
		 */
		LanguageCodes jpn = LanguageCodes.JPN;
		final List<String> tabuListJapanese = getLinesFromFile(PREFIX + jpn + SUFFIX);
		List<String> tabuListJapaneseLatin = new ArrayList<>();
		for(String s : tabuListJapanese){
			String[] cols = s.split("\t");
			if(cols.length >= 2){
				tabuListJapaneseLatin.add(cols[1]);
			}
		}
		
		//phonetics
		List<String> japanesePhonetics = PhoneticTransliterator.getPhoneticRepresentationForList(tabuListJapaneseLatin, LanguageCodes.JPN);
		//fill the |language -> tabu words| map
		phoneticTabuWords.put(jpn, japanesePhonetics);
		
		
		
		/*
		 * thai
		 */
		LanguageCodes th = LanguageCodes.TH;
		final List<String> tabuListThai = getLinesFromFile(PREFIX + th + SUFFIX);
		List<String> tabuListThaiPhon = new ArrayList<>();
		for(String s : tabuListThai){
			String[] cols = s.split("\t");
			if(cols.length >= 2){
				tabuListThaiPhon.add(cols[1]);
			}
		}
		//fill the |language -> tabu words| map
		phoneticTabuWords.put(th, tabuListThaiPhon);
		
		
		/*
		 * arabic
		 */
		LanguageCodes ara = LanguageCodes.ARA;
		List<String> tabuListArabic = getLinesFromFile(PREFIX + ara + SUFFIX);
		List<String> tabuListArabicVocalized = new ArrayList<>();
		for(String s : tabuListArabic){
			String[] cols = s.split("\t");
			if(cols.length > 2){
				tabuListArabicVocalized.add(cols[1]);
			}
		}
		
		//phonetics
		List<String> arabicPhonetics = PhoneticTransliterator.getPhoneticRepresentationForList(tabuListArabicVocalized, LanguageCodes.ARA);
		//fill the |language -> tabu words| map
		phoneticTabuWords.put(ara, arabicPhonetics);
		
		
		
		
		//phonetic tabu words
		for(LanguageCodes c : phoneticTabuWords.keySet()){
			List<String> tabuWords = phoneticTabuWords.get(c);
			
			System.out.println(c + " tabu words");
			System.out.println("----------------------------------------------------------");
			for(String s : tabuWords){
				System.out.println(s);
			}
			System.out.println();
			System.out.println();
		}
		
		
		
		
		
		
		return result;
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
	 * @param path
	 * @return
	 */
	private InputStream getInputStream(String path){
		return getServletContext().getResourceAsStream("/WEB-INF/resources/" + path);
	}
	
	/**
	 * creates a buffered reader for reading a file from the resource directory
	 * @param path
	 * @return
	 */
	private BufferedReader getBufferedReader(String path){
		return new BufferedReader(new InputStreamReader(getInputStream(path)));
	}
	
	/**
	 * creates a list of lines for a file from the resource directory
	 * @param path
	 * @return
	 */
	private List<String> getLinesFromFile(String path){
		List<String> rval = new ArrayList<String>();
		
		BufferedReader reader = getBufferedReader(path);
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				if(line.trim().length() > 0){
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







