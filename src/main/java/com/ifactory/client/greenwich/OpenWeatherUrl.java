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
package com.ifactory.client.greenwich;

public class OpenWeatherUrl {
	
	private String url;
	private String version;
	private double lat;
	private double lng;
	private int cnt;
	private boolean weather;
	private boolean forecast;
	private boolean hourly;
	
	public OpenWeatherUrl(String url) {
		this.url = url;
		this.weather = false;
		this.forecast = false;
		this.cnt = 0;
		this.hourly = false;
	}
	
	public OpenWeatherUrl lat(double lat) {
		this.lat = lat;
		return this;
	}
	
	public OpenWeatherUrl lng(double lng) {
		this.lng = lng;
		return this;
	}
	
	public OpenWeatherUrl cnt(int cnt) {
		this.cnt = cnt;
		return this;
	}
	
	public OpenWeatherUrl version(String version) {
		this.version = version;
		return this;
	}
	
	public OpenWeatherUrl weather() {
		this.weather = true;
		return this;
	}
	
	public OpenWeatherUrl forecast() {
		this.forecast = true;
		return this;
	}
	
	public OpenWeatherUrl hourly() {
	  this.hourly = true;
	  return this;
	}

  public boolean getHourly() {
    return this.hourly;
  }
  
	public String toString() {
		String command = "weather";
		if (this.weather) {
			command = "weather";
		} else if (this.forecast) {
		  command = "forecast" + (this.hourly != true ? "/daily" : "");
		}		
		return this.url + "/data/" + version + "/" + command +"?lat=" + 
  			this.lat + "&lon=" + this.lng + "&mode=json" + 
  			(this.cnt > 0 ? ("&cnt=" + Integer.toString(this.cnt)) : "");
	}		
}
