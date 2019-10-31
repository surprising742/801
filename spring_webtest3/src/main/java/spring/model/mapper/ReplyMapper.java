package spring.model.mapper;

import java.util.List;
import java.util.Map;

import spring.model.bbs.ReplyDTO;

public interface ReplyMapper {
	
	List<ReplyDTO> list(Map map);
	int total(int bbsno);
	
}
