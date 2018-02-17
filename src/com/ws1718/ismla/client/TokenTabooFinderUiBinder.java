package com.ws1718.ismla.client;

import java.util.List;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.ws1718.ismla.shared.ClientTabooWordSummary;
import com.ws1718.ismla.shared.LanguageCodes;

public class TokenTabooFinderUiBinder extends Composite {

	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	private static TokenTabooFinderUiBinderUiBinder uiBinder = GWT.create(TokenTabooFinderUiBinderUiBinder.class);
	
	/**
	 * distance evaluations
	 */
	private static final String CRITICAL = "critical";
	private static final String CONCERNING = "concerning";
	private static final String RELEVANT = "relevant";
	private static final String FINE = "fine";
	private static final String PERFECT = "perfect";
	
	/**
	 * 
	 */
	private static final String CRITICAL_COLOR_CLASS = "footer-red";
	private static final String CONCERNING_COLOR_CLASS = "footer-orange";
	private static final String RELEVANT_COLOR_CLASS = "footer-yellow";
	private static final String FINE_COLOR_CLASS = "footer-limegreen";
	private static final String PERFECT_COLOR_CLASS = "footer-darkgreen";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	private final MyResources resources = GWT.create(MyResources.class);

	interface TokenTabooFinderUiBinderUiBinder extends UiBinder<Widget, TokenTabooFinderUiBinder> {
	}

	public TokenTabooFinderUiBinder() {
		initWidget(uiBinder.createAndBindUi(this));
		sendRequest();

		img_00.setResource(resources.german());
		img_01.setResource(resources.spanish());
		img_02.setResource(resources.persian());
		img_03.setResource(resources.french());
		img_04.setResource(resources.hindi());
		img_05.setResource(resources.italian());
		img_06.setResource(resources.japanese());
		img_07.setResource(resources.korean());
		img_08.setResource(resources.dutch());
		img_09.setResource(resources.polish());
		img_10.setResource(resources.portuguese());
		img_11.setResource(resources.swedish());
		img_12.setResource(resources.thai());
		img_13.setResource(resources.turkish());
		img_14.setResource(resources.chinese());
		img_15.setResource(resources.arabic());
		img_16.setResource(resources.russian());
		
		slots[0] = img_00;
		slots[1] = img_01;
		slots[2] = img_02;
		slots[3] = img_03;
		slots[4] = img_04;
		slots[5] = img_05;
		slots[6] = img_06;
		slots[7] = img_07;
		slots[8] = img_08;
		slots[9] = img_09;
		slots[10] = img_10;
		slots[11] = img_11;
		slots[12] = img_12;
		slots[13] = img_13;
		slots[14] = img_14;
		slots[15] = img_15;
		slots[16] = img_16;
		
		tabooWords[0] = tabooWord_00;
		tabooWords[1] = tabooWord_01;
		tabooWords[2] = tabooWord_02;
		tabooWords[3] = tabooWord_03;
		tabooWords[4] = tabooWord_04;
		tabooWords[5] = tabooWord_05;
		tabooWords[6] = tabooWord_06;
		tabooWords[7] = tabooWord_07;
		tabooWords[8] = tabooWord_08;
		tabooWords[9] = tabooWord_09;
		tabooWords[10] = tabooWord_10;
		tabooWords[11] = tabooWord_11;
		tabooWords[12] = tabooWord_12;
		tabooWords[13] = tabooWord_13;
		tabooWords[14] = tabooWord_14;
		tabooWords[15] = tabooWord_15;
		tabooWords[16] = tabooWord_16;
		
		footers[0] = footer_00;
		footers[1] = footer_01;
		footers[2] = footer_02;
		footers[3] = footer_03;
		footers[4] = footer_04;
		footers[5] = footer_05;
		footers[6] = footer_06;
		footers[7] = footer_07;
		footers[8] = footer_08;
		footers[9] = footer_09;
		footers[10] = footer_10;
		footers[11] = footer_11;
		footers[12] = footer_12;
		footers[13] = footer_13;
		footers[14] = footer_14;
		footers[15] = footer_15;
		footers[16] = footer_16;
		
		status[0] = status_00;
		status[1] = status_01;
		status[2] = status_02;
		status[3] = status_03;
		status[4] = status_04;
		status[5] = status_05;
		status[6] = status_06;
		status[7] = status_07;
		status[8] = status_08;
		status[9] = status_09;
		status[10] = status_10;
		status[11] = status_11;
		status[12] = status_12;
		status[13] = status_13;
		status[14] = status_14;
		status[15] = status_15;
		status[16] = status_16;
		
		languages[0] = language_00;
		languages[1] = language_01;
		languages[2] = language_02;
		languages[3] = language_03;
		languages[4] = language_04;
		languages[5] = language_05;
		languages[6] = language_06;
		languages[7] = language_07;
		languages[8] = language_08;
		languages[9] = language_09;
		languages[10] = language_10;
		languages[11] = language_11;
		languages[12] = language_12;
		languages[13] = language_13;
		languages[14] = language_14;
		languages[15] = language_15;
		languages[16] = language_16;
		
	}

	@UiField
	TextBox inputBox;
	@UiField
	Image img_00;
	@UiField
	Image img_01;
	@UiField
	Image img_02;
	@UiField
	Image img_03;
	@UiField
	Image img_04;
	@UiField
	Image img_05;
	@UiField
	Image img_06;
	@UiField
	Image img_07;
	@UiField
	Image img_08;
	@UiField
	Image img_09;
	@UiField
	Image img_10;
	@UiField
	Image img_11;
	@UiField
	Image img_12;
	@UiField
	Image img_13;
	@UiField
	Image img_14;
	@UiField
	Image img_15;
	@UiField
	Image img_16;

	private Image[] slots = new Image[17];

	@UiField
	HTMLPanel tabooWord_00;
	@UiField
	HTMLPanel tabooWord_01;
	@UiField
	HTMLPanel tabooWord_02;
	@UiField
	HTMLPanel tabooWord_03;
	@UiField
	HTMLPanel tabooWord_04;
	@UiField
	HTMLPanel tabooWord_05;
	@UiField
	HTMLPanel tabooWord_06;
	@UiField
	HTMLPanel tabooWord_07;
	@UiField
	HTMLPanel tabooWord_08;
	@UiField
	HTMLPanel tabooWord_09;
	@UiField
	HTMLPanel tabooWord_10;
	@UiField
	HTMLPanel tabooWord_11;
	@UiField
	HTMLPanel tabooWord_12;
	@UiField
	HTMLPanel tabooWord_13;
	@UiField
	HTMLPanel tabooWord_14;
	@UiField
	HTMLPanel tabooWord_15;
	@UiField
	HTMLPanel tabooWord_16;
	
	private HTMLPanel[] tabooWords = new HTMLPanel[17];
	
	@UiField
	HTMLPanel footer_00;
	@UiField
	HTMLPanel footer_01;
	@UiField
	HTMLPanel footer_02;
	@UiField
	HTMLPanel footer_03;
	@UiField
	HTMLPanel footer_04;
	@UiField
	HTMLPanel footer_05;
	@UiField
	HTMLPanel footer_06;
	@UiField
	HTMLPanel footer_07;
	@UiField
	HTMLPanel footer_08;
	@UiField
	HTMLPanel footer_09;
	@UiField
	HTMLPanel footer_10;
	@UiField
	HTMLPanel footer_11;
	@UiField
	HTMLPanel footer_12;
	@UiField
	HTMLPanel footer_13;
	@UiField
	HTMLPanel footer_14;
	@UiField
	HTMLPanel footer_15;
	@UiField
	HTMLPanel footer_16;
	
	private HTMLPanel[] footers = new HTMLPanel[17];
	
	
	@UiField
	HTMLPanel status_00;
	@UiField
	HTMLPanel status_01;
	@UiField
	HTMLPanel status_02;
	@UiField
	HTMLPanel status_03;
	@UiField
	HTMLPanel status_04;
	@UiField
	HTMLPanel status_05;
	@UiField
	HTMLPanel status_06;
	@UiField
	HTMLPanel status_07;
	@UiField
	HTMLPanel status_08;
	@UiField
	HTMLPanel status_09;
	@UiField
	HTMLPanel status_10;
	@UiField
	HTMLPanel status_11;
	@UiField
	HTMLPanel status_12;
	@UiField
	HTMLPanel status_13;
	@UiField
	HTMLPanel status_14;
	@UiField
	HTMLPanel status_15;
	@UiField
	HTMLPanel status_16;
	
	private HTMLPanel[] status = new HTMLPanel[17];
	
	
	@UiField
	HTMLPanel language_00;
	@UiField
	HTMLPanel language_01;
	@UiField
	HTMLPanel language_02;
	@UiField
	HTMLPanel language_03;
	@UiField
	HTMLPanel language_04;
	@UiField
	HTMLPanel language_05;
	@UiField
	HTMLPanel language_06;
	@UiField
	HTMLPanel language_07;
	@UiField
	HTMLPanel language_08;
	@UiField
	HTMLPanel language_09;
	@UiField
	HTMLPanel language_10;
	@UiField
	HTMLPanel language_11;
	@UiField
	HTMLPanel language_12;
	@UiField
	HTMLPanel language_13;
	@UiField
	HTMLPanel language_14;
	@UiField
	HTMLPanel language_15;
	@UiField
	HTMLPanel language_16;
	
	private HTMLPanel[] languages = new HTMLPanel[17];
	
	


	private String getText() {
		return inputBox.getText();
	}

	private void sendRequest() {
		// handle user request
		inputBox.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {

					img_00.setResource(resources.ajaxLoader());
					img_01.setResource(resources.ajaxLoader());
					img_02.setResource(resources.ajaxLoader());
					img_03.setResource(resources.ajaxLoader());
					img_04.setResource(resources.ajaxLoader());
					img_05.setResource(resources.ajaxLoader());
					img_06.setResource(resources.ajaxLoader());
					img_07.setResource(resources.ajaxLoader());
					img_08.setResource(resources.ajaxLoader());
					img_09.setResource(resources.ajaxLoader());
					img_10.setResource(resources.ajaxLoader());
					img_11.setResource(resources.ajaxLoader());
					img_12.setResource(resources.ajaxLoader());
					img_13.setResource(resources.ajaxLoader());
					img_14.setResource(resources.ajaxLoader());
					img_15.setResource(resources.ajaxLoader());
					img_16.setResource(resources.ajaxLoader());

					final String input = getText();
					greetingService.greetServer(input, new AsyncCallback<List<ClientTabooWordSummary>>() {

						@Override
						public void onSuccess(List<ClientTabooWordSummary> result) {

							for (int i = 0; i < result.size(); i++) {
								
								final ClientTabooWordSummary t = result.get(i);
								final LanguageCodes l = t.getLanguage();
								final String tabooWord = t.getTabooWord();
								final float distance = t.getDistanceToInput();
								final String evaluated = distanceEvaluation(distance); 
								final String colorClass = distanceColorClass(distance);

								switch (l) {

								case DEU:
									slots[i].setResource(resources.german());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									footers[i].setStyleName(colorClass);
									status[i].getElement().setInnerHTML(evaluated);
									languages[i].getElement().setInnerHTML(LanguageCodes.fullLanguageName(l).toUpperCase());
									break;
								case SPA:
									slots[i].setResource(resources.spanish());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									footers[i].setStyleName(colorClass);
									status[i].getElement().setInnerHTML(evaluated);
									languages[i].getElement().setInnerHTML(LanguageCodes.fullLanguageName(l).toUpperCase());
									break;
								case FAS:
									slots[i].setResource(resources.persian());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									footers[i].setStyleName(colorClass);
									status[i].getElement().setInnerHTML(evaluated);
									languages[i].getElement().setInnerHTML(LanguageCodes.fullLanguageName(l).toUpperCase());
									break;
								case FRA:
									slots[i].setResource(resources.french());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									footers[i].setStyleName(colorClass);
									status[i].getElement().setInnerHTML(evaluated);
									languages[i].getElement().setInnerHTML(LanguageCodes.fullLanguageName(l).toUpperCase());
									break;
								case HIN:
									slots[i].setResource(resources.hindi());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									footers[i].setStyleName(colorClass);
									status[i].getElement().setInnerHTML(evaluated);
									languages[i].getElement().setInnerHTML(LanguageCodes.fullLanguageName(l).toUpperCase());
									break;
								case ITA:
									slots[i].setResource(resources.italian());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									footers[i].setStyleName(colorClass);
									status[i].getElement().setInnerHTML(evaluated);
									languages[i].getElement().setInnerHTML(LanguageCodes.fullLanguageName(l).toUpperCase());
									break;
								case JPN:
									slots[i].setResource(resources.japanese());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									footers[i].setStyleName(colorClass);
									status[i].getElement().setInnerHTML(evaluated);
									languages[i].getElement().setInnerHTML(LanguageCodes.fullLanguageName(l).toUpperCase());
									break;
								case KOR:
									slots[i].setResource(resources.korean());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									footers[i].setStyleName(colorClass);
									status[i].getElement().setInnerHTML(evaluated);
									languages[i].getElement().setInnerHTML(LanguageCodes.fullLanguageName(l).toUpperCase());
									break;
								case NLD:
									slots[i].setResource(resources.dutch());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									footers[i].setStyleName(colorClass);
									status[i].getElement().setInnerHTML(evaluated);
									languages[i].getElement().setInnerHTML(LanguageCodes.fullLanguageName(l).toUpperCase());
									break;
								case POL:
									slots[i].setResource(resources.polish());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									footers[i].setStyleName(colorClass);
									status[i].getElement().setInnerHTML(evaluated);
									languages[i].getElement().setInnerHTML(LanguageCodes.fullLanguageName(l).toUpperCase());
									break;
								case POR:
									slots[i].setResource(resources.portuguese());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									footers[i].setStyleName(colorClass);
									status[i].getElement().setInnerHTML(evaluated);
									languages[i].getElement().setInnerHTML(LanguageCodes.fullLanguageName(l).toUpperCase());
									break;
								case SWE:
									slots[i].setResource(resources.swedish());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									footers[i].setStyleName(colorClass);
									status[i].getElement().setInnerHTML(evaluated);
									languages[i].getElement().setInnerHTML(LanguageCodes.fullLanguageName(l).toUpperCase());
									break;
								case TH:
									slots[i].setResource(resources.thai());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									footers[i].setStyleName(colorClass);
									status[i].getElement().setInnerHTML(evaluated);
									languages[i].getElement().setInnerHTML(LanguageCodes.fullLanguageName(l).toUpperCase());
									break;
								case TUR:
									slots[i].setResource(resources.turkish());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									footers[i].setStyleName(colorClass);
									status[i].getElement().setInnerHTML(evaluated);
									languages[i].getElement().setInnerHTML(LanguageCodes.fullLanguageName(l).toUpperCase());
									break;
								case CMN:
									slots[i].setResource(resources.chinese());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									footers[i].setStyleName(colorClass);
									status[i].getElement().setInnerHTML(evaluated);
									languages[i].getElement().setInnerHTML(LanguageCodes.fullLanguageName(l).toUpperCase());
									break;
								case ARA:
									slots[i].setResource(resources.arabic());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									footers[i].setStyleName(colorClass);
									status[i].getElement().setInnerHTML(evaluated);
									languages[i].getElement().setInnerHTML(LanguageCodes.fullLanguageName(l).toUpperCase());
									break;
								case RUS:
									slots[i].setResource(resources.russian());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									footers[i].setStyleName(colorClass);
									status[i].getElement().setInnerHTML(evaluated);
									languages[i].getElement().setInnerHTML(LanguageCodes.fullLanguageName(l).toUpperCase());
									break;

								default:
									break;
								}
							}

						}

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							Window.alert(SERVER_ERROR);
						}
					});
				}
			}
		});

	}
	
	private String distanceEvaluation(float distance){
		
		if(distance <= 0.2){
			return CRITICAL;
		}else if(distance <= 0.4){
			return CONCERNING;
		}else if(distance <= 0.6){
			return RELEVANT;
		}else if(distance <= 0.8){
			return FINE;
		}else{
			return PERFECT;
		}
	}
	
	
	private String distanceColorClass(float distance){
		
		if(distance <= 0.2){
			return CRITICAL_COLOR_CLASS;
		}else if(distance <= 0.4){
			return CONCERNING_COLOR_CLASS;
		}else if(distance <= 0.6){
			return RELEVANT_COLOR_CLASS;
		}else if(distance <= 0.8){
			return FINE_COLOR_CLASS;
		}else{
			return PERFECT_COLOR_CLASS;
		}
	}

}
