package org.duffy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.duffy.domain.TrashCanVO;
import org.duffy.service.TrashCanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class LocationController {
	
	@Autowired
	private TrashCanService service;

	@GetMapping("/location")
	public Map<String, Object> getLocation(){
		
		List<TrashCanVO> trashes = new ArrayList<TrashCanVO>(service.getList());
		
        Map<String, Object> map = new HashMap<>();
        List<Object> list = new ArrayList<>();


        int count =0;
        
        for(TrashCanVO trash : trashes) {
        	
            Map<String, Double> innerMap = new HashMap<>();
        	
        	log.info(trash.getLat());
        	log.info(trash.getLng());

        	Double lat = trash.getLat();
        	Double lng = trash.getLng();
        	
            innerMap.put("lng", lat);
            innerMap.put("lat", lng);
            list.add(innerMap);
        }

        map.put("positions", list);

        return map;
	}
	
}
