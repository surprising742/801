package spring.sts.img;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import spring.model.img.ImgDTO;
import spring.model.mapper.ImgMapper;
@Controller
public class ImgController {

	@Autowired
	private ImgMapper mapper;
	
	
	@GetMapping("/read")
	public String read(int imgno,Model model) {
		ImgDTO dto = mapper.read(imgno);
		//5개의 이미지를 가져온다
		Map map =mapper.imgRead(imgno);
		
		BigDecimal [] noArr=
			{
					(BigDecimal)map.get("PRE_IMGNO2"),
					(BigDecimal)map.get("PRE_IMGNO1"),
					(BigDecimal)map.get("IMGNO"),
					(BigDecimal)map.get("NEXT_IMGNO1"),
					(BigDecimal)map.get("NEXT_IMGNO2"),
			};
		
		String [] files = 
			{
					(String)map.get("PRE_FILE2"),
					(String)map.get("PRE_FILE1"),
					(String)map.get("FILENAME"),
					(String)map.get("NEXT_FILE1"),
					(String)map.get("NEXT_FILE2"),
			};
		
		model.addAttribute("dto",dto);
		model.addAttribute("noArr",noArr);
		model.addAttribute("files",files);
		
		return "/read";
	}

}
