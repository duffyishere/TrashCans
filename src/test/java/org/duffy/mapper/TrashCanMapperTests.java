package org.duffy.mapper;

import org.duffy.domain.TrashCanVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TrashCanMapperTests {

	@Setter(onMethod_ = @Autowired)
	private TrashCanMapper mapper;
	
	@Test
	public void testGetList() {
		
		mapper.getList().forEach(log::info);
	}
	
	@Test
	public void testInsert() {

		TrashCanVO trash = new TrashCanVO();
		trash.setTno(6532L);
		trash.setLat(null);
		trash.setLng(null);
		
		mapper.insert(trash);
	}
}
