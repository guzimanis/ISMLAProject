package com.ws1718.ismla.shared;


public enum LanguageCodes {
	
	DEU("deu"),
	EN("en"),
	SPA("spa"),
	FAS("fas"),
	FRA("fra"),
	HIN("hin"),
	ITA("ita"),
	JPN("jpn"),
	KOR("kor"),
	NLD("nld"),
	POL("pol"),
	POR("por"),
	SWE("swe"),
	TH("th"),
	TUR("tur"),
	CMN("cmn"),
	ARA("ara"),
	RUS("rus"),
	
	UNKNOWN("unknown");
	
	
	private String languageCode;
	
	LanguageCodes(String languageCode) {
		this.languageCode = languageCode;
	}
	
	public String code(){
		return languageCode;
	}
	
	
	/**
	 * returns the full language name for a language code
	 */
	public static String fullLanguageName(LanguageCodes c) {
		switch (c) {
		case DEU:
			return "german";
		case EN:
			return "english";
		case SPA:
			return "spanish";
		case FAS:
			return "persian";
		case FRA:
			return "french";
		case HIN:
			return "hindi";
		case ITA:
			return "italian";
		case JPN:
			return "japanese";
		case KOR:
			return "korean";
		case NLD:
			return "dutch";
		case POL:
			return "polish";
		case POR:
			return "portuguese";
		case SWE:
			return "swedish";
		case TH:
			return "thai";
		case TUR:
			return "turkish";
		case CMN:
			return "chinese";
		case ARA:
			return "arabic";
		case RUS:
			return "russian";
			
		default:
			return "unknown";
		}
		
	}
	
	
	/**
	 * returns the language code for a language name
	 */
	public static LanguageCodes languageCodeForLanguage(String language) {
		switch (language.toLowerCase()) {
		case "german":
			return DEU;
		case "english":
			return EN;
		case "spanish":
			return SPA;
		case "persian":
			return FAS;
		case "france":
			return FRA;
		case "hindi":
			return HIN;
		case "italian":
			return ITA;
		case "japanese":
			return JPN;
		case "korean":
			return KOR;
		case "dutch":
			return NLD;
		case "polish":
			return POL;
		case "portuguese":
			return POR;
		case "swedish":
			return SWE;
		case "thai":
			return TH;
		case "turkish":
			return TUR;
		case "chinese":
			return CMN;
		case "arabic":
			return ARA;
		case "russian":
			return RUS;
			
		default:
			return UNKNOWN;
		}
		
	}
}
