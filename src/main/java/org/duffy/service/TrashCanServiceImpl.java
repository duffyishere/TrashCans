package org.duffy.service;

import java.io.IOException;
import java.util.List;

import org.duffy.domain.TrashCanVO;
import org.duffy.mapper.TrashCanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;

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
	

	@Override
	public Float[] findGeoPoint(String location) {
		
		if(location == null) {
			System.out.println("no locatoin............");
			return null;
		}
		
		GeocoderRequest geocoderRequest =  new GeocoderRequestBuilder().setAddress(location).setLanguage("ko").getGeocoderRequest();
		
		try {
			Geocoder geocoder = new Geocoder(); 
			GeocodeResponse geocodeResponse = geocoder.geocode(geocoderRequest);
			
			if(geocodeResponse.getStatus() == GeocoderStatus.OK & !geocodeResponse.getResults().isEmpty()) {
				GeocoderResult geocoderResult = geocodeResponse.getResults().iterator().next();
				LatLng latLng = geocoderResult.getGeometry().getLocation();
				
				Float[] coords = new Float[2];
				coords[0] = latLng.getLat().floatValue();
				coords[1] = latLng.getLng().floatValue();
				
				
				System.out.println("Sucess............");

				return coords;
			}
			else {
				System.out.println("Fail............");
				System.out.println(geocodeResponse.getStatus());
				System.out.println(geocodeResponse.getResults());

			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
				
		return null;
	}
	
}
