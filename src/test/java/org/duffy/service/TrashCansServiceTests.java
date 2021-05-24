package org.duffy.service;

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
	public void testGetLocaion() {
		
		String location = "대전광역시 유성구 궁동";
		Float[] coords = service.findGeoPoint(location);

		System.out.println(location + ": " + coords[0] + ", " + coords[1]);
	}
}
