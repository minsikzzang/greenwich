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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static com.ifactory.client.greenwich.OpenWeatherConstants.OPEN_WEATHER_ENDPOINT;
import static com.ifactory.client.greenwich.OpenWeatherConstants.OPEN_WEATHER_API_VERSION;

public class Client {
	
	private double lat;
	private double lng;
	private int count;
	private boolean forecast;
	private boolean hourly;
	private Connection conn = null;
	
	public Client() {	
		this.forecast = false;
		this.conn = new Connection();
		this.hourly = false;
	}

	public Client coordinate(double lat, double lng) {
		this.lat = lat;
		this.lng = lng;
		return this;
	}	
	
	public Client forecast(boolean hourly) {
		this.forecast = true;
		this.hourly = hourly;
		return this;
	}	
	
	public Client weather() {
		this.forecast = false;
		return this;
	}
	
	public Client count(int count) {
		this.count = count;
		return this;
	}
	
	private List<Result> getForecast(OpenWeatherUrl url) throws IOException, 
		InterruptedException, ExecutionException {		
		String responseBody = conn.get(url.forecast());
		// System.out.println(responseBody);
		
		JSONParser parser = new JSONParser();
		JSONObject jsonResponse;
		Result result = null;
		try {
			jsonResponse = (JSONObject)parser.parse(responseBody);
			Map<String, Object> city = (Map<String, Object>)jsonResponse.get("city");
			int id = ((Number)city.get("id")).intValue();
			String name = (String)city.get("name");
			Map<String, Object> coord = (Map<String, Object>)city.get("coord");			
			List<Map<String, Object>> list = 
			  (List<Map<String, Object>>)jsonResponse.get("list");
			
			for (Map<String, Object> fc: list) {
			  long timestamp = ((Number)fc.get("dt")).longValue();
			  
			  List<Weather> weathers = new ArrayList<Weather>();	
  				
			  for (Map<String, Object> weather: 
			      (List<Map<String, Object>>)fc.get("weather")) {
  				long wId = ((Number)weather.get("id")).longValue();
  			    String keyword = (String)weather.get("main");
  			    Weather w = new Weather.Builder(wId, keyword)
  			    	.description((String)weather.get("description"))
  			        .icon((String)weather.get("icon"))
  			        .build();
  			    weathers.add(w);
  			}
  			
			  if (url.getHourly()) {
			    Map<String, Object> m = (Map<String, Object>)fc.get("main");
          Main mo = 
            new Main.Builder(((Number)m.get("temp")).doubleValue(),
                             ((Number)m.get("temp_min")).doubleValue(),
                             ((Number)m.get("temp_max")).doubleValue())
                             .pressure(((Number)m.get("pressure")).doubleValue())
                             .humidity(((Number)m.get("humidity")).intValue())
                             .build();
          HourlyForecast.Builder builder = 
            new HourlyForecast.Builder(timestamp).main(mo);
            
          for (Weather w: weathers) {
            builder.addWeather(w);
          }
        } else {
          Map<String, Object> t = (Map<String, Object>)fc.get("temp");
          Temp temp = 
            new Temp.Builder(((Number)t.get("day")).doubleValue(),
                             ((Number)t.get("min")).doubleValue(),
                             ((Number)t.get("max")).doubleValue())
                             .night(((Number)t.get("night")).doubleValue())
                             .evening(((Number)t.get("eve")).doubleValue())
                             .morning(((Number)t.get("morn")).doubleValue())
                             .build();
          DailyForecast.Builder builder = 
            new DailyForecast.Builder(timestamp)
              .humidity(((Number)fc.get("humidity")).intValue())
              .pressure(((Number)fc.get("pressure")).doubleValue())
              .temp(temp);  
              
          for (Weather w: weathers) {
            builder.addWeather(w);
          }  		  
        }
      }
				/*
			Result.Builder builder = new Result.Builder(id, dt, name)
				.lat(((Number)coord.get("lat")).doubleValue())
				.lng(((Number)coord.get("lon")).doubleValue())
				.windSpeed(((Number)wind.get("speed")).intValue())
				.windDegree(((Number)wind.get("deg")).intValue())
				.main(mainTemp);						
			*/			        							
		} catch (ParseException e) {
			e.printStackTrace();
		}				
 
		return null;
	}
	
	private Result getWeather(OpenWeatherUrl url) throws IOException, 
		InterruptedException, ExecutionException {
		String responseBody = conn.get(url.weather());
	  // System.out.println(responseBody);
	  
		JSONParser parser = new JSONParser();
		JSONObject jsonResponse;
		Result result = null;
		try {
			jsonResponse = (JSONObject) parser.parse(responseBody);
			int id = ((Number)jsonResponse.get("id")).intValue();
			long dt = ((Number)jsonResponse.get("dt")).longValue();
			String name = (String)jsonResponse.get("name");
			Map<String, Object> coord = 
				(Map<String, Object>)jsonResponse.get("coord");
			Map<String, Object> main =
				(Map<String, Object>)jsonResponse.get("main");
			Map<String, Object> wind =
				(Map<String, Object>)jsonResponse.get("wind");
			/*
			Map<String, Object> cloud =
				(Map<String, Object>)jsonResponse.get("cloud");
			Map<String, Object> rain =
				(Map<String, Object>)jsonResponse.get("rain");
				*/
			List<Map<String, Object>> weathers =
				(List<Map<String, Object>>)jsonResponse.get("weather");

			Main mo = 
			  new Main.Builder(((Number)main.get("temp")).doubleValue(),
			                   ((Number)main.get("temp_min")).doubleValue(),
			                   ((Number)main.get("temp_max")).doubleValue())
			                   .pressure(((Number)main.get("pressure")).intValue())
			                   .humidity(((Number)main.get("humidity")).intValue())
			                   .build();
					
			Result.Builder builder = new Result.Builder(id, dt, name)
				.lat(((Number)coord.get("lat")).doubleValue())
				.lng(((Number)coord.get("lon")).doubleValue())
				.windSpeed(((Number)wind.get("speed")).intValue())
				.windDegree(((Number)wind.get("deg")).intValue())
				.main(mo);
						
			for (Map<String, Object> weather: weathers) {
				long wId = ((Number)weather.get("id")).longValue();
			    String keyword = (String)weather.get("main");
			    Weather w = new Weather.Builder(wId, keyword)
			    	.description((String)weather.get("description"))
			        .icon((String)weather.get("icon"))
			        .build();
			    builder.addWeather(w);
			}
			result = builder.build();			        							
		} catch (ParseException e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	public void get(IWeatherListener listener) {
		OpenWeatherUrl url = new OpenWeatherUrl(OPEN_WEATHER_ENDPOINT)
			.lat(lat)
			.lng(lng)			
			.version(OPEN_WEATHER_API_VERSION);

		try {
			if (this.forecast) {	
				listener.onForecast(
				  getForecast(url.hourly(this.hourly).cnt(this.count)), 
				  conn);
			} else {
				listener.onWeather(getWeather(url), conn);
			}		
		} catch (IOException e) {
			e.printStackTrace();
			listener.onError();
		} catch (InterruptedException e) {
			e.printStackTrace();
			listener.onError();
		} catch (ExecutionException e) {
			e.printStackTrace();
			listener.onError();
		}		
	}	
	
  public static void main(String[] args) {
    
    class Listener implements IWeatherListener {
  	  
  	  public void onForecast(List<Result> forecasts, Connection conn) {
  	  }

    	public void onWeather(Result weather, Connection conn) {
        System.out.println(weather.getName() + ": " + weather.getMain().getTemp() + 
    			" C - " + weather.getWeathers().get(0).getDescription() + 
    			", High:" + weather.getMain().getTempMax() + ", Low: " + 
    			weather.getMain().getTempMin());
    			
    		conn.close();
    	}
    	
    	public void onError() {
    	  
    	}
  	}
    
    if (args.length < 2) {
      System.out.println("Latitude and Longitude are required!!!");
      return;
    }
      
    final double lat = Double.parseDouble(args[0]);
    final double lng = Double.parseDouble(args[1]);
    	
    Listener l = new Listener();    
    Client c = new Client();    
    c.coordinate(lat, lng).count(2).forecast(true).get(l);           
    c.coordinate(lat, lng).count(2).forecast(false).get(l);           
    c.coordinate(lat, lng).weather().get(l);          
  }
}
