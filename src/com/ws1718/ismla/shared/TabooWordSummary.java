package com.ws1718.ismla.shared;

import java.io.Serializable;

public class TabooWordSummary implements Serializable{

		private LanguageCodes language;
		private String tabooWord;
		private String phonologicalRepresentationOfTabooWord;
		private String simplefiedPhonologicalRepresentationOfTabooWord;
		float distanceToInput;
		
		public TabooWordSummary() {
			
		}

		public TabooWordSummary(LanguageCodes language, String tabooWord) {
			this.language = language;
			this.tabooWord = tabooWord;
		}

		public TabooWordSummary(LanguageCodes language, String tabooWord,
				String phonologicalRepresentationOfTabooWord) {
			this.language = language;
			this.tabooWord = tabooWord;
			this.phonologicalRepresentationOfTabooWord = phonologicalRepresentationOfTabooWord;
		}

		public TabooWordSummary(LanguageCodes language, String tabooWord, String phonologicalRepresentationOfTabooWord,
				String simplefiedPhonologicalRepresentationOfTabooWord) {
			this.language = language;
			this.tabooWord = tabooWord;
			this.phonologicalRepresentationOfTabooWord = phonologicalRepresentationOfTabooWord;
			this.simplefiedPhonologicalRepresentationOfTabooWord = simplefiedPhonologicalRepresentationOfTabooWord;
		}

		public TabooWordSummary(LanguageCodes language, String tabooWord, String phonologicalRepresentationOfTabooWord,
				String simplefiedPhonologicalRepresentationOfTabooWord, float distanceToInput) {
			this.language = language;
			this.tabooWord = tabooWord;
			this.phonologicalRepresentationOfTabooWord = phonologicalRepresentationOfTabooWord;
			this.simplefiedPhonologicalRepresentationOfTabooWord = simplefiedPhonologicalRepresentationOfTabooWord;
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

		public String getPhonologicalRepresentationOfTabooWord() {
			return phonologicalRepresentationOfTabooWord;
		}

		public void setPhonologicalRepresentationOfTabooWord(String phonologicalRepresentationOfTabooWord) {
			this.phonologicalRepresentationOfTabooWord = phonologicalRepresentationOfTabooWord;
		}

		public String getSimplefiedPhonologicalRepresentationOfTabooWord() {
			return simplefiedPhonologicalRepresentationOfTabooWord;
		}

		public void setSimplefiedPhonologicalRepresentationOfTabooWord(String simplefiedPhonologicalRepresentationOfTabooWord) {
			this.simplefiedPhonologicalRepresentationOfTabooWord = simplefiedPhonologicalRepresentationOfTabooWord;
		}

		public float getDistanceToInput() {
			return distanceToInput;
		}

		public void setDistanceToInput(float distanceToInput) {
			this.distanceToInput = distanceToInput;
		}

		
}
