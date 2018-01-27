package com.ws1718.ismla.server;

import java.util.Map;

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
		
		LanguageTransliterator lt = new LanguageTransliterator();
		
		String result = "";
		String arabicPhon = lt.transcribeArabicToPhonetic(input);
		String russiaRepresentation = lt.transcribeToRussian(input);
		
//		
//
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
	
	
	
}
