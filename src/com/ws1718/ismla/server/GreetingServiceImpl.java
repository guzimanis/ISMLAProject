package com.ws1718.ismla.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletContext;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ws1718.ismla.client.GreetingService;
import com.ws1718.ismla.server.transliteration.LanguageTransliterator;
import com.ws1718.ismla.server.transliteration.PhoneticTransliterator;
import com.ws1718.ismla.shared.LanguageCodes;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {
	


	public String greetServer(String input) throws IllegalArgumentException {
		
		
		List<String> listofLines = getLinesFromFile("/transcription/Arabic_chat.txt");
		for(String s : listofLines){
			System.out.println(s);
		}
		
		LanguageTransliterator lt = new LanguageTransliterator();
		
		String result = "";
//		String arabicPhon = lt.transcribeArabicToPhonetic(input);
//		String russiaRepresentation = lt.transcribeToRussian(input);
		
//		result += arabicPhon + "\n";
//		result += russiaRepresentation + "\n";
		
		/**
		 * input
		 */
		Map<LanguageCodes, String> phoneticRepresentations = PhoneticTransliterator.getPhoneticRepresentationForAllLanguages(input);
		
		for(LanguageCodes c : phoneticRepresentations.keySet()){
			System.out.println(LanguageCodes.fullLanguageName(c));
			System.out.println(phoneticRepresentations.get(c));
			System.out.println();
		}
		
		
		/**
		 * tabu 
		 */
		for(LanguageCodes c : LanguageCodes.values()){
			if( c.equals(LanguageCodes.UNKNOWN) || c.equals(LanguageCodes.EN) || c.equals(LanguageCodes.FRA)
					|| c.equals(LanguageCodes.TH)  || c.equals(LanguageCodes.CMN) || c.equals(LanguageCodes.JPN)
					|| c.equals(LanguageCodes.FAS) || c.equals(LanguageCodes.ARA) ) continue;
			
			System.out.println("-------------------------------------------------------------");
			System.out.println(LanguageCodes.fullLanguageName(c) + "/" + c + " tabu words:");
			List<String> tabuList = getLinesFromFile("/tabuLists/tabu_" + c + ".txt");
			
			List<String> phonetics = PhoneticTransliterator.getPhoneticRepresentationForList(tabuList, c);
			//real words
			for(int i = 0; i < tabuList.size(); i++){
				System.out.println(tabuList.get(i) + " -> " + phonetics.get(i));
			}
			System.out.println();
			System.out.println();
		}
		
		
		/*
		 * persisch
		 * 
		 */
		System.out.println("--------------------------------------------------------------------");
		System.out.println("persian tabu words");
		List<String> tabuListPersian = getLinesFromFile("/tabuLists/tabu_FAS.txt");
		List<String> tabuListPersianLatin = new ArrayList<>();
		for(String s : tabuListPersian){
			String[] cols = s.split("\t");
			if(cols.length >= 2){
				tabuListPersianLatin.add(cols[1]);
			}
		}
		
		//phonetics
		List<String> persianPhonetics = PhoneticTransliterator.getPhoneticRepresentationForList(tabuListPersianLatin, LanguageCodes.FAS);
		for(String s : persianPhonetics){
			System.out.println(s);
		}
		
		
		/*
		 * Japanisch
		 * 
		 */
		System.out.println("--------------------------------------------------------------------");
		System.out.println("japanese tabu words");
		List<String> tabuListJapanese = getLinesFromFile("/tabuLists/tabu_JPN.txt");
		List<String> tabuListJapaneseLatin = new ArrayList<>();
		for(String s : tabuListJapanese){
			String[] cols = s.split("\t");
			if(cols.length >= 2){
				tabuListJapaneseLatin.add(cols[1]);
			}
		}
		
		//phonetics
		List<String> japanesePhonetics = PhoneticTransliterator.getPhoneticRepresentationForList(tabuListJapaneseLatin, LanguageCodes.JPN);
		for(String s : japanesePhonetics){
			System.out.println(s);
		}
		
		
		
		/*
		 * thai
		 */
		System.out.println("--------------------------------------------------------------------");
		System.out.println("thai tabu words");
		List<String> tabuListThai = getLinesFromFile("/tabuLists/tabu_TH.txt");
		List<String> tabuListThaiPhon = new ArrayList<>();
		for(String s : tabuListThai){
			String[] cols = s.split("\t");
			if(cols.length >= 2){
				tabuListThaiPhon.add(cols[1]);
			}
		}
		for(String s : tabuListThaiPhon){
			System.out.println(s);
		}
		
		
		/*
		 * arabic
		 */
		System.out.println("--------------------------------------------------------------------");
		System.out.println("arabic tabu words");
		List<String> tabuListArabic = getLinesFromFile("/tabuLists/tabu_ARA.txt");
		List<String> tabuListArabicVocalized = new ArrayList<>();
		for(String s : tabuListArabic){
			String[] cols = s.split("\t");
			if(cols.length > 2){
				tabuListArabicVocalized.add(cols[1]);
			}
		}
		
		//phonetics
		List<String> arabicPhonetics = PhoneticTransliterator.getPhoneticRepresentationForList(tabuListArabicVocalized, LanguageCodes.ARA);
		for(String s : arabicPhonetics){
			System.out.println(s);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		return result;
	}
	
	
	/**
	 * 
	 * creates the input stream to read files from the resource directory
	 * @param path
	 * @return
	 */
	public InputStream getInputStream(String path){
		return getServletContext().getResourceAsStream("/WEB-INF/resources/" + path);
	}
	
	/**
	 * creates a buffered reader for reading a file from the resource directory
	 * @param path
	 * @return
	 */
	public BufferedReader getBufferedReader(String path){
		return new BufferedReader(new InputStreamReader(getInputStream(path)));
	}
	
	/**
	 * creates a list of lines for a file from the resource directory
	 * @param path
	 * @return
	 */
	public List<String> getLinesFromFile(String path){
		List<String> rval = new ArrayList<String>();
		
		BufferedReader reader = getBufferedReader(path);
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				if(line.isEmpty()) continue;
				rval.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rval;
	}
	
}







