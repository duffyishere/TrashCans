package org.duffy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.duffy.domain.TrashCanVO;

@Mapper
public interface TrashCanMapper {
	
	@Select("select * from tbl_trashcan")
	public List<TrashCanVO> getList(); 
	
	@Insert("update tbl_trashcan set lat = #{lat}, lng = #{lng} where serialNumber = #{snum }and boroughName = #{boroughName}")
	public void insert(int snum, String boroughName, Float lat, Float lng);
}
