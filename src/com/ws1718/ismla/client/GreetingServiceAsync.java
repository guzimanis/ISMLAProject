package com.ws1718.ismla.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.ws1718.ismla.shared.ClientTabooWordSummary;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String name, AsyncCallback<List<ClientTabooWordSummary>> callback);
}
