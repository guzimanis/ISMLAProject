package com.ws1718.ismla.server.transliteration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.northeuralex.util.convert.GeneralTransliterator;

import com.ws1718.ismla.shared.LanguageCodes;

public class PhoneticTransliterator {
	
	/**
	 * maps the languages to their corresponding phonetic representation of the input
	 * @param input
	 * @return a map
	 */
	public static Map<LanguageCodes, String> getPhoneticRepresentationForAllLanguages(String input){
		Map<LanguageCodes, String> rval = new HashMap<>();
		
		rval.put(LanguageCodes.DEU, GeneralTransliterator.orthToIPA(LanguageCodes.DEU.code(), input));
		rval.put(LanguageCodes.SPA, GeneralTransliterator.orthToIPA(LanguageCodes.SPA.code(), input));
		rval.put(LanguageCodes.FAS, GeneralTransliterator.prncToIPA(LanguageCodes.FAS.code(), input));
		rval.put(LanguageCodes.HIN, GeneralTransliterator.prncToIPA(LanguageCodes.HIN.code(), input));
		rval.put(LanguageCodes.ITA, GeneralTransliterator.orthToIPA(LanguageCodes.ITA.code(), input));
		rval.put(LanguageCodes.JPN, GeneralTransliterator.prncToIPA(LanguageCodes.JPN.code(), input));
		rval.put(LanguageCodes.KOR, GeneralTransliterator.orthToIPA(LanguageCodes.KOR.code(), input));
		rval.put(LanguageCodes.NLD, GeneralTransliterator.orthToIPA(LanguageCodes.NLD.code(), input));
		rval.put(LanguageCodes.POL, GeneralTransliterator.orthToIPA(LanguageCodes.POL.code(), input));
		rval.put(LanguageCodes.POR, GeneralTransliterator.orthToIPA(LanguageCodes.POR.code(), input));
		rval.put(LanguageCodes.SWE, GeneralTransliterator.orthToIPA(LanguageCodes.SWE.code(), input));
		rval.put(LanguageCodes.TUR, GeneralTransliterator.orthToIPA(LanguageCodes.TUR.code(), input));
		rval.put(LanguageCodes.CMN, GeneralTransliterator.prncToIPA(LanguageCodes.CMN.code(), input));
	
//		String inputAR = GeneralTransliterator.orthToIPA("ara", arabicPhon);
//		String inputRU = GeneralTransliterator.orthToIPA("rus", russiaRepresentation);
		
		String inputEN = "";
		String inputFR = "";
		String inputTH = "";

		return rval;
	}
	
	/**
	 * transforms the input token to phonetic representation of the given language
	 * @param input
	 * @param c language code
	 * @return list
	 */
	public static List<String> getPhoneticRepresentation(String input, LanguageCodes c){
		List<String> rval = new ArrayList<>();
		
		if( c.equals(LanguageCodes.DEU) || c.equals(LanguageCodes.SPA) || c.equals(LanguageCodes.ITA) ||
				c.equals(LanguageCodes.KOR) || c.equals(LanguageCodes.NLD) || c.equals(LanguageCodes.POL) ||
				c.equals(LanguageCodes.POR) || c.equals(LanguageCodes.SWE) || c.equals(LanguageCodes.TUR) ){
			
			rval.add(GeneralTransliterator.orthToIPA(c.code(), input));
			
		}else if ( c.equals(LanguageCodes.FAS) || c.equals(LanguageCodes.HIN) || c.equals(LanguageCodes.JPN) ||
				c.equals(LanguageCodes.CMN)){
			
			rval.add(GeneralTransliterator.prncToIPA(c.code(), input));
			
		}
		
		return rval;
	}
	
	/**
	 * transforms each of the input tokens to phonetic representation of the given language
	 * @param inputs list of tokens
	 * @param c language code
	 * @return list
	 */
	public static List<String> getPhoneticRepresentationForList(List<String> inputs, LanguageCodes c){
		List<String> rval = new ArrayList<>();
		
		if( c.equals(LanguageCodes.DEU) || c.equals(LanguageCodes.SPA) || c.equals(LanguageCodes.ITA) ||
				c.equals(LanguageCodes.KOR) || c.equals(LanguageCodes.NLD) || c.equals(LanguageCodes.POL) ||
				c.equals(LanguageCodes.POR) || c.equals(LanguageCodes.SWE) || c.equals(LanguageCodes.TUR) ){
			
			for(String s : inputs){
				rval.add(GeneralTransliterator.orthToIPA(c.code(), s));
			}
			
		}else if ( c.equals(LanguageCodes.FAS) || c.equals(LanguageCodes.HIN) || c.equals(LanguageCodes.JPN) ||
				c.equals(LanguageCodes.CMN)){
			
			for(String s : inputs){
				rval.add(GeneralTransliterator.prncToIPA(c.code(), s));
			}
			
		}
		
		return rval;
	}

}
