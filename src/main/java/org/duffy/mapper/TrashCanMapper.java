package org.duffy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.duffy.domain.TrashCanVO;

@Mapper
public interface TrashCanMapper {
	
	@Select("select * from tbl_trashcan")
	public List<TrashCanVO> getList(); 
}
