package org.duffy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.duffy.domain.TrashCanVO;

@Mapper
public interface TrashCanMapper {
	
	public List<TrashCanVO> getList(); 
	public void insert(TrashCanVO trash);
}
