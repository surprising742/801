package spring.model.mapper;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import spring.model.bbs.ReplyDTO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
		})

public class ReplyMapperTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ReplyMapperTest.class);

	@Autowired
	private ReplyMapper mapper;

	@Test
	@Ignore
	public void testMapper() {
		logger.info("mapper:" + mapper.getClass().getName());
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Ignore
	public void testList() {
//		fail("Not yet implemented");
		Map map = new HashMap();
		map.put("bbsno", 2);
		map.put("sno", 1);
		map.put("eno", 5);
		List<ReplyDTO> list = mapper.list(map);
		logger.info("list:" + list + "\n");

	}

	@Test
//	@Ignore
	public void testTotal() {// rcount와 같음
//		fail("Not yet implemented");
		int bbsno=2;
		int cnt= mapper.total(bbsno);
		logger.info("total:"+cnt);
	}

	@Test
	@Ignore
	public void testCreate() {
//		fail("Not yet implemented");
		ReplyDTO dto = new ReplyDTO();
		dto.setBbsno(2);
		dto.setContent("댓글1");
		dto.setId("user1");
		assertTrue(mapper.create(dto) > 0);
	}

	@Test
	@Ignore
	public void testRead() {
//		fail("Not yet implemented");
		ReplyDTO dto = mapper.read(3);
		logger.info("dto:" + dto);

	}

	@Test
	@Ignore
	public void testDelete() {
//		fail("Not yet implemented");
		int rnum=4;
		assertTrue(mapper.delete(rnum)>0);
	}

	@Test
	@Ignore
	public void testUpdate() {
		ReplyDTO dto = new ReplyDTO();
		dto.setContent("댓글2로변경");
		dto.setRnum(4);
		assertTrue(mapper.update(dto) > 0);

	}

	@Test
	public void testRcount() {
//		fail("Not yet implemented");
	}

}
