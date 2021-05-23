package org.duffy.service;

import java.util.List;

import org.duffy.domain.TrashCanVO;
import org.duffy.mapper.TrashCanMapper;
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

	
}
