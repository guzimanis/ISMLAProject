package com.ws1718.ismla.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface MyResources extends ClientBundle{
	
	public static final MyResources INSTANCE = GWT.create(MyResources.class);
	
	@Source("images/loading.gif")
	ImageResource ajaxLoader();
	
	@Source("images/german.png")
	ImageResource german();
	
	@Source("images/spanish.png")
	ImageResource spanish();
	
	@Source("images/persian.png")
	ImageResource persian();
	
	@Source("images/french.png")
	ImageResource french();
	
	@Source("images/hindi.png")
	ImageResource hindi();
	
	@Source("images/italian.png")
	ImageResource italian();
	
	@Source("images/japanese.png")
	ImageResource japanese();
	
	@Source("images/korean.png")
	ImageResource korean();
	
	@Source("images/dutch.png")
	ImageResource dutch();
	
	@Source("images/polish.png")
	ImageResource polish();
	
	@Source("images/portuguese.png")
	ImageResource portuguese();
	
	@Source("images/swedish.png")
	ImageResource swedish();
	
	@Source("images/thai.png")
	ImageResource thai();
	
	@Source("images/turkish.png")
	ImageResource turkish();
	
	@Source("images/chinese.png")
	ImageResource chinese();
	
	@Source("images/arabic.png")
	ImageResource arabic();
	
	@Source("images/russian.png")
	ImageResource russian();
}
