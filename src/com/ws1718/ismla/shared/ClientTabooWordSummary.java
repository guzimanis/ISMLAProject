package com.ws1718.ismla.shared;

import java.io.Serializable;

public class ClientTabooWordSummary implements Serializable{

	private LanguageCodes language;
	private String tabooWord;
	private String translation;
	float distanceToInput;
	
	public ClientTabooWordSummary() {
		
	}
	
	public ClientTabooWordSummary(LanguageCodes language, String tabooWord, float distanceToInput) {
		super();
		this.language = language;
		this.tabooWord = tabooWord;
		this.distanceToInput = distanceToInput;
	}
	
	public ClientTabooWordSummary(LanguageCodes language, String tabooWord, String translation, float distanceToInput) {
		super();
		this.language = language;
		this.tabooWord = tabooWord;
		this.translation = translation;
		this.distanceToInput = distanceToInput;
	}

	public LanguageCodes getLanguage() {
		return language;
	}

	public void setLanguage(LanguageCodes language) {
		this.language = language;
	}

	public String getTabooWord() {
		return tabooWord;
	}

	public void setTabooWord(String tabooWord) {
		this.tabooWord = tabooWord;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public float getDistanceToInput() {
		return distanceToInput;
	}

	public void setDistanceToInput(float distanceToInput) {
		this.distanceToInput = distanceToInput;
	}





	
}
