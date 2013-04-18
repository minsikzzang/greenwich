/**
 * Copyright 2013 iFactory Ltd.
 * 
 * Min Kim (minsikzzang@gmail.com)
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.ifactory.client.openweather;

import org.jboss.netty.handler.codec.http.*;

import com.twitter.finagle.Codec;
import com.twitter.finagle.Service;
import com.twitter.finagle.builder.ClientBuilder;
import com.twitter.finagle.http.Http;
import com.twitter.util.FutureEventListener;

public class Client {	
    public static void main(String[] args) {
    	Service<HttpRequest, HttpResponse> client = ClientBuilder.safeBuild(
    			ClientBuilder.get()
    				.codec(Http())
    				.hosts("www.google.com:80")
    				.hostConnectionLimit(1));

    	// Issue a request, get a response:
    	HttpRequest request = new DefaultHttpRequest(
    			HttpVersion.HTTP_1_1, HttpMethod.GET, "/"); 
    			
    	client.apply(request).addEventListener(new FutureEventListener<HttpResponse>() {
    		public void onSuccess(HttpResponse response) {                                        
    			System.out.println("received response: " + response);
    		}
    			  
    		public void onFailure(Throwable cause) {
    			System.out.println("failed with cause: " + cause);
    		}
    	});
    }	
}
