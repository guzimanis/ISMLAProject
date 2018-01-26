package com.ws1718.ismla.server.transliteration;

import java.util.HashMap;
import java.util.Map;

import org.northeuralex.util.convert.GeneralTransliterator;

import com.ws1718.ismla.shared.LanguageCodes;

public class PhoneticTransliteration {
	
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

}
