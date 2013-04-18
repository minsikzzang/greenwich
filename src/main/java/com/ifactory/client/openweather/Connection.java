package com.ifactory.client.openweather;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

public class Connection implements IDisposable {
	private AsyncHttpClient client = new AsyncHttpClient();
	
	public Connection() {
		
	}
	
	public String get(OpenWeatherUrl url) throws IOException, 
			InterruptedException, ExecutionException {		
		Future<Response> f = client.prepareGet(url.toString()).execute();
		Response r = f.get();		
		return r.getResponseBody();
	}
	
	public void dispose() {
		client.closeAsynchronously();		
	}	
}
