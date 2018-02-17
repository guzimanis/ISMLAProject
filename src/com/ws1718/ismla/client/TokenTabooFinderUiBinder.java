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
								ClientTabooWordSummary t = result.get(i);
								LanguageCodes l = t.getLanguage();
								String tabooWord = t.getTabooWord();
								

								switch (l) {

								case DEU:
									slots[i].setResource(resources.german());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									break;
								case SPA:
									slots[i].setResource(resources.spanish());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									break;
								case FAS:
									slots[i].setResource(resources.persian());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									break;
								case FRA:
									slots[i].setResource(resources.french());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									break;
								case HIN:
									slots[i].setResource(resources.hindi());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									break;
								case ITA:
									slots[i].setResource(resources.italian());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									break;
								case JPN:
									slots[i].setResource(resources.japanese());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									break;
								case KOR:
									slots[i].setResource(resources.korean());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									break;
								case NLD:
									slots[i].setResource(resources.dutch());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									break;
								case POL:
									slots[i].setResource(resources.polish());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									break;
								case POR:
									slots[i].setResource(resources.portuguese());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									break;
								case SWE:
									slots[i].setResource(resources.swedish());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									break;
								case TH:
									slots[i].setResource(resources.thai());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									break;
								case TUR:
									slots[i].setResource(resources.turkish());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									break;
								case CMN:
									slots[i].setResource(resources.chinese());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									break;
								case ARA:
									slots[i].setResource(resources.arabic());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									break;
								case RUS:
									slots[i].setResource(resources.russian());
									tabooWords[i].getElement().setInnerHTML(tabooWord);
									break;

								default:
									break;
								}
							}

//							for (ClientTabooWordSummary t : result) {
//								Window.alert(t.getDistanceToInput() + " " + t.getTabooWord() + " "
//										+ LanguageCodes.fullLanguageName(t.getLanguage()));
//							}

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

}
