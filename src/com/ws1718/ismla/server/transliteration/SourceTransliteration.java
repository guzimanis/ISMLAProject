package com.ws1718.ismla.server.transliteration;

import com.ws1718.ismla.shared.LanguageCodes;

public class SourceTransliteration {
	
	private String translit;
	private LanguageCodes languageCode;
	
	public SourceTransliteration(String translit, LanguageCodes languageCode) {
		super();
		this.translit = translit;
		this.languageCode = languageCode;
	}
	
	public String getTranslit() {
		return translit;
	}
	
	public void setTranslit(String translit) {
		this.translit = translit;
	}
	
	public LanguageCodes getLanguageCode() {
		return languageCode;
	}
	
	public void setLanguageCode(LanguageCodes languageCode) {
		this.languageCode = languageCode;
	}
}
