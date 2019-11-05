package spring.model.mapper;

import java.util.Map;

import spring.model.img.ImgDTO;

public interface ImgMapper {
	ImgDTO read(int imgno);
	
	Map imgRead (int imgno);
}
