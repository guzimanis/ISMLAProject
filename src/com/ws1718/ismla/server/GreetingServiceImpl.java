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
		String arabicPhon = lt.transcribeArabicToPhonetic(input);
		String russiaRepresentation = lt.transcribeToRussian(input);
		
//		result += arabicPhon + "\n";
//		result += russiaRepresentation + "\n";
		
		
		Map<LanguageCodes, String> phoneticRepresentations = PhoneticTransliterator.getPhoneticRepresentationForAllLanguages(input);
		
		for(LanguageCodes c : phoneticRepresentations.keySet()){
			System.out.println(LanguageCodes.fullLanguageName(c));
			System.out.println(phoneticRepresentations.get(c));
			System.out.println();
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







