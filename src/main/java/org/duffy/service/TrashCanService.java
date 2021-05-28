package org.duffy.service;

import java.util.List;

import org.duffy.domain.TrashCanVO;

public interface TrashCanService {

	public List<TrashCanVO> getList();
	public List<List<Float>> callAPI();
	public void insertAPI();
}
