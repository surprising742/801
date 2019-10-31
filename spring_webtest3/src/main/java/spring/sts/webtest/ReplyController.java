package spring.sts.webtest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.model.bbs.ReplyDTO;
import spring.model.mapper.ReplyMapper;
import spring.utility.webtest.Utility;

@RestController
public class ReplyController {

	@Autowired
	private ReplyMapper mapper;
	
	@GetMapping("/bbs/reply/list/{bbsno}/{sno}/{eno}")
	public ResponseEntity<List<ReplyDTO>> getList(
	@PathVariable("bbsno") int bbsno,
	@PathVariable("sno") int sno,
	@PathVariable("eno") int eno
	) {
	 
	 
	Map map = new HashMap();
	map.put("sno", sno);
	map.put("eno", eno);
	map.put("bbsno", bbsno);
	 
	 
	return new ResponseEntity<List<ReplyDTO>>(mapper.list(map), HttpStatus.OK);
	}
	@GetMapping("/bbs/reply/page")
	public ResponseEntity<String> getPage(
	                   @RequestParam("nPage") int nPage,
	                   @RequestParam("nowPage") int nowPage,
	                   @RequestParam("bbsno") int bbsno,
	                   @RequestParam("col") String col,
	                   @RequestParam("word") String word) {
	 
	 
	       int total = mapper.total(bbsno);
	       String url = "read";
	 
	       int recordPerPage = 3; // 한페이지당 출력할 레코드 갯수
	 
	       String paging = Utility.rpaging(total, nowPage, recordPerPage, col, word, url, nPage, bbsno);
	 
	       return new ResponseEntity<String>(paging, HttpStatus.OK);
	 
	}
	 
}
