package org.duffy.service;

import java.util.ArrayList;
import java.util.List;

import org.duffy.mapper.TrashCanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TrashCansServiceTests {

	@Autowired
	private TrashCanService service;
	
	@Test
	public void testGetLIst() {
	
		service.getList().forEach(log::info);
	}
	
	@Test
	public void testCallAPI(){
//		List<Float> list = new ArrayList<Float>(service.callAPI("경복궁역 4번출구"));
//		
//		log.info(list.get(0));
//		log.info(list.get(1));
		
		service.callAPI();
	}
	
	@Test
	public void testInsertAPI() {
		
		service.insertAPI();
	}
	
}
