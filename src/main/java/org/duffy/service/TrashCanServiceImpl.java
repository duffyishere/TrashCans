package org.duffy.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.duffy.domain.TrashCanVO;
import org.duffy.mapper.TrashCanMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class TrashCanServiceImpl implements TrashCanService{

	@Setter(onMethod_ = @Autowired)
	private TrashCanMapper mapper;
	
	@Override
	public List<TrashCanVO> getList() {
		
		log.info("get List..........");
		
		return mapper.getList();
	}
	


	public List<List<Float>> callAPI() {
		
		try {
			List<List<Float>> trashCansList = new ArrayList<List<Float>>();
			
			List<TrashCanVO> locationList = new ArrayList<>(mapper.getList());
			
			for(TrashCanVO locations : locationList) {
				log.info(locations.getSerialNumber());
				log.info(locations.getRoadName());
				
				StringBuilder result = new StringBuilder();
				
				String location = URLEncoder.encode(locations.getDetailLocation(), "UTF-8");
				String urlStr = "https://maps.googleapis.com/maps/api/geocode/json?address="+location+"&key=AIzaSyCD5T2nz0R5159zZoIY4Csv20_8Or6bVXI";
				URL url = new URL(urlStr);
				
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(); 
				urlConnection.setRequestMethod("GET");
				
				BufferedReader br;
				
				br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
				
				String returnLine;
				
				while((returnLine = br.readLine()) !=  null) {
					result.append(returnLine+"\n\r");
				}
				
				urlConnection.disconnect();
				
				List<Float> resultList = new ArrayList<Float>(); 
				
				JSONParser p = new JSONParser();
				
		        String Json = result.toString();

				JSONObject dto = (JSONObject) p.parse(Json);

				JSONArray results = (JSONArray) dto.get("results");
				
				String status = (String) dto.get("status");
				
				if(status.equals("ZERO_RESULTS")) {
					
					resultList.add(null);
					resultList.add(null);
					
					trashCansList.add(resultList);
					log.info(trashCansList);
				}
				else {
					
					JSONObject a = (JSONObject) results.get(0);
					
					JSONObject geometry = (JSONObject) a.get("geometry");
					
					JSONObject location1 = (JSONObject) geometry.get("location");
					
					String[] result2 = new String[]{location1.get("lat").toString(), location1.get("lng").toString()};
					
					resultList.add(Float.valueOf(location1.get("lat").toString()));
					resultList.add(Float.valueOf(location1.get("lng").toString()));
					
					trashCansList.add(resultList);
					
				}
			}
			log.info(trashCansList);
			return trashCansList;
			
		}catch(IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}catch(IndexOutOfBoundsException e) {
			
		}
			
		return null;
	}


	@Override
	public void insertAPI() {
		try {
			List<TrashCanVO> locationList = new ArrayList<>(mapper.getList());
			
			for(TrashCanVO locations : locationList) {
				log.info(locations.getSerialNumber());
				log.info(locations.getRoadName());
				
				StringBuilder result = new StringBuilder();
				
				String location = URLEncoder.encode(locations.getDetailLocation(), "UTF-8");
				String urlStr = "https://maps.googleapis.com/maps/api/geocode/json?address="+location+"&key=AIzaSyCD5T2nz0R5159zZoIY4Csv20_8Or6bVXI";
				URL url = new URL(urlStr);
				
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(); 
				urlConnection.setRequestMethod("GET");
				
				BufferedReader br;
				
				br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
				
				String returnLine;
				
				while((returnLine = br.readLine()) !=  null) {
					result.append(returnLine+"\n\r");
				}
				
				urlConnection.disconnect();
				
				List<Float> resultList = new ArrayList<Float>(); 
				
				JSONParser p = new JSONParser();
				
		        String Json = result.toString();

				JSONObject dto = (JSONObject) p.parse(Json);

				JSONArray results = (JSONArray) dto.get("results");
				
				String status = (String) dto.get("status");
				
				if(status.equals("ZERO_RESULTS")) {
					
					resultList.add(null);
					resultList.add(null);
					
					locations.setLat(null);
					locations.setLat(null);
					mapper.insert(locations);
				}
				else {
					
					JSONObject a = (JSONObject) results.get(0);
					
					JSONObject geometry = (JSONObject) a.get("geometry");
					
					JSONObject location1 = (JSONObject) geometry.get("location");
					
					log.info("------------------------------------------");
					log.info(locations.getTno());
					locations.setLat((Double)location1.get("lat"));
					locations.setLng((Double)location1.get("lng"));
					mapper.insert(locations);
					
				}
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}catch(IndexOutOfBoundsException e) {
			
		}
		
	}
	
}
