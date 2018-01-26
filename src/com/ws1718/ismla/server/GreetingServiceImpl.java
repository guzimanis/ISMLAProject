package com.ws1718.ismla.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.northeuralex.util.convert.GeneralTransliterator;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ws1718.ismla.client.GreetingService;
import com.ws1718.ismla.server.transliteration.LanguageTransliterator;
import com.ws1718.ismla.server.transliteration.PhoneticTransliteration;
import com.ws1718.ismla.shared.LanguageCodes;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {
	


	public String greetServer(String input) throws IllegalArgumentException {
		
		LanguageTransliterator lt = new LanguageTransliterator();
		
		String result = "";
//		String russiaRepresentation = lt.transcribeToRussian(input);
//		String arabicPhon = lt.transcribeArabicToPhonetic(input);
//		
//
//		result += arabicPhon + "\n";
//		result += russiaRepresentation + "\n";
		
		
		Map<LanguageCodes, String> phoneticRepresentations = PhoneticTransliteration.getPhoneticRepresentationForAllLanguages(input);
		
		for(LanguageCodes c : phoneticRepresentations.keySet()){
			System.out.println(LanguageCodes.fullLanguageName(c));
			System.out.println(phoneticRepresentations.get(c));
			System.out.println();
		}
		

		return result;
	}
	
	
	
}
