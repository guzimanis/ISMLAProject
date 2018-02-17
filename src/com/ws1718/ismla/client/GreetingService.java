package com.ws1718.ismla.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.ws1718.ismla.shared.ClientTabooWordSummary;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	List<ClientTabooWordSummary> greetServer(String name) throws IllegalArgumentException;
}
