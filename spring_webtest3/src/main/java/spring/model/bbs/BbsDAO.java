package spring.model.bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import spring.utility.webtest.DBClose;
import spring.utility.webtest.DBOpen;

@Repository
public class BbsDAO {

	public int total(Map map) {//검색했을때의 전체 레코드 수를 불러오는 
		int total=0;
		Connection con=DBOpen.open();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String col=(String)map.get("col");//검색이 되기 위한 map 변수 가져오기
		String word=(String)map.get("word");//검색이 되기 위한 map 변수 가져오기
		StringBuffer sql=new StringBuffer();
		sql.append(" select count(*) from bbs "); // 레코드 값을 전체 가져옴
		if(word.length()>0 && col.equals("title_content")) {
			sql.append(" where title like '%'||?||'%' ");
			sql.append(" or content like '%'||?||'%' ");
		}else if(word.length()>0) {
			sql.append(" where " +col+" like '%'||?||'%' ");
		}
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			if(word.length()>0 && col.equals("title_content")) {
				pstmt.setString(1, word);
				pstmt.setString(2, word);
			}else if(word.length()>0) {
				pstmt.setString(1, word);
			}//word(검색어 입력)의 내용이 있을때만  pstmt
			
			rs=pstmt.executeQuery();
			rs.next(); //값을 확인
			
			total=rs.getInt(1); // 첫번째 인덱스 값 가져오고 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(con, pstmt, rs);
		}		
		return total;
	}
	
	
	public List<BbsDTO> list(Map map){
		List<BbsDTO> list= new ArrayList<BbsDTO>();
		
		Connection con=DBOpen.open();
		
		PreparedStatement pstmt=null;
		
		ResultSet rs=null;
		
		String col=(String)map.get("col");//검색이 되기 위한 map 변수 가져오기
		String word=(String)map.get("word");//검색이 되기 위한 map 변수 가져오기
		
		
		int sno=(Integer)map.get("sno");//r 의 시작번호 _ 페이지를 어디서 부터 어디까지 가져오느냐
		int eno=(Integer)map.get("eno");//r 의 마지막번호
		
		StringBuffer sql=new StringBuffer();
		sql.append(" select bbsno, wname, title, viewcnt, to_char(wdate, 'yyyy-mm-dd') wdate, grpno, indent, ansnum, r ");
		sql.append(" from( ");
		sql.append("     select bbsno, wname, title, viewcnt, wdate, grpno, ");
		sql.append("  	 indent, ansnum, rownum r ");
		sql.append("     from( ");

		
		sql.append(" 		select bbsno, wname, title, viewcnt, wdate, grpno, indent, ansnum ");
		sql.append(" 		from bbs ");
				
			if(word.length()>0 && col.equals("title_content")) {
				sql.append(" where title like '%'||?||'%' ");
				sql.append(" or content like '%'||?||'%' ");
			}else if(word.length()>0) {
				sql.append(" where " +col+" like '%'||?||'%' ");
			}
		//word(검색어 입력)의 내용이 있을때만                             || 연결기호 
			
		sql.append(" 		order by grpno desc, ansnum ");
		
		
		sql.append("         ) ");
		sql.append("     ) ");
		sql.append("  where r>=? and r<=? ");
	
		try {
			int i=0;//컬럼의 순서 
			
			pstmt=con.prepareStatement(sql.toString());
			
			if(word.length()>0 && col.equals("title_content")) {
				pstmt.setString(++i, word);
				pstmt.setString(++i, word);
			}else if(word.length()>0) {
				pstmt.setString(++i, word);
			}//word(검색어 입력)의 내용이 있을때만  pstmt로 
			
			pstmt.setInt(++i, sno);
			pstmt.setInt(++i, eno);
			
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				BbsDTO dto=new BbsDTO();//dto에 다시 값을 저장해주는데
				dto.setBbsno(rs.getInt("bbsno"));//rs가 결과값을 받아서 rs에서 값을 get 
				dto.setWname(rs.getString("wname"));
				dto.setTitle(rs.getString("title"));
				dto.setViewcnt(rs.getInt("viewcnt"));
				dto.setWdate(rs.getString("wdate"));
				dto.setGrpno(rs.getInt("grpno"));
				dto.setIndent(rs.getInt("indent"));
				dto.setAnsnum(rs.getInt("ansnum"));
				
				list.add(dto);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(con, pstmt, rs);
		}
		
		
		return list;
	}
	
	public boolean create(BbsDTO dto) {
		boolean flag=false;
		
		Connection con=DBOpen.open();
		
		PreparedStatement pstmt=null;
		
		StringBuffer sql=new StringBuffer();
		sql.append(" insert into bbs(bbsno, wname, title, content, passwd, wdate, grpno) ");
		sql.append(" values((select nvl(max(bbsno),0)+1 from bbs), ");
		sql.append(" ?,?,?,?,sysdate,(select nvl(max(grpno),0)+1 from bbs)) ");

		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getWname());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getPasswd());
			
			int cnt=pstmt.executeUpdate();
			
			if(cnt>0) {
				flag=true;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(con, pstmt);
		}
			
		return flag;
	}
	
	public void upViewcnt(int bbsno) {
		
		Connection con=DBOpen.open();
		
		PreparedStatement pstmt=null;
		
		StringBuffer sql=new StringBuffer();
		sql.append(" update bbs set viewcnt=viewcnt+1 ");
		sql.append(" where bbsno=? ");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, bbsno);
			
			pstmt.executeUpdate(); // 다른 변수로 받을 필요가 없음 웹페이지에서 증가값을 확인하기때문에
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(con, pstmt);
		}
	}
	
	public BbsDTO read(int bbsno) {
		BbsDTO dto=null;
		
		Connection con=DBOpen.open();
		
		PreparedStatement pstmt=null;
		
		ResultSet rs=null;
		
		StringBuffer sql=new StringBuffer();
		sql.append(" select bbsno, wname, title, content, viewcnt, wdate ");
		sql.append(" from bbs where bbsno=? ");

		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, bbsno);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				dto=new BbsDTO();
				dto.setBbsno(bbsno);
				dto.setWname(rs.getString("wname")); //값을 가지고 있는 rs에서 가져옴
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setViewcnt(rs.getInt("viewcnt"));
				dto.setWdate(rs.getString("wdate"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(con, pstmt, rs);
		}
		return dto;
	}
	
	public boolean passCheck(Map<String, String> map) {
		//하나의 건에서 비밀번호를 확인 하는 작업 map 변수에는 BbsTest에서 put해준 값들이 들어있음
		//BbsTest에서 번호와 비밀번호 값을 이 메소드 안의 map에
		//map은 중복을 허용하지 않음 __ 
	//public int passCheck(int bbsno, String passwd)
		boolean flag=false;
		
		Connection con=DBOpen.open();
		
		PreparedStatement pstmt=null;
		
		ResultSet rs=null;
		
		StringBuffer sql=new StringBuffer();
		sql.append(" select count(bbsno) as cnt ");
		sql.append(" from bbs ");
		sql.append(" where bbsno=? and passwd=? ");
		
		int bbsno=Integer.parseInt(map.get("bbsno")); //맵에 저장되어있는 bbsno랑 passwd 가져오기
		String passwd=(String)map.get("passwd");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, bbsno);
			pstmt.setString(2, passwd);
			
			rs=pstmt.executeQuery();// 무조건 rs에는 결과값이 있음 
			
			rs.next();
			
			int cnt=rs.getInt("cnt");// "cnt"는 sql의 count as cnt 
			
			if(cnt>0) {
				flag=true; 
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(con, pstmt, rs);
		}
		return flag;
	}
	
	
	public boolean update(BbsDTO dto) {
		boolean flag=false;
		
		Connection con=DBOpen.open();
		
		PreparedStatement pstmt=null;
		
		StringBuffer sql=new StringBuffer();
		sql.append(" update bbs set wname=?, title=?, content=? ");
		sql.append(" where bbsno=? ");
				
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getWname());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getBbsno());
			
			int cnt=pstmt.executeUpdate();
			
			if(cnt>0) {
				flag=true;
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(con, pstmt);
		}
		
		return flag;
	}
	
	public boolean delete(int bbsno) {
		boolean flag=false;
		
		Connection con=DBOpen.open();
		
		PreparedStatement pstmt=null;
		
		StringBuffer sql=new StringBuffer();
		sql.append(" delete from bbs ");
		sql.append(" where bbsno=? ");
	
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, bbsno);
			
			int cnt=pstmt.executeUpdate();
			
			if(cnt>0) {
				flag=true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(con, pstmt);
		}
			
		return flag;
	}
	
	public BbsDTO reply_read(int bbsno) {
		BbsDTO dto=null;
		
		Connection con=DBOpen.open();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sql=new StringBuffer();
		sql.append(" select bbsno, grpno, indent, ansnum, title ");
		sql.append(" from bbs ");
		sql.append(" where bbsno=? ");
		
		try {
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, bbsno);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				dto=new BbsDTO();
				dto.setBbsno(rs.getInt("bbsno"));
				dto.setGrpno(rs.getInt("grpno"));
				dto.setIndent(rs.getInt("indent"));
				dto.setAnsnum(rs.getInt("ansnum"));
				dto.setTitle(rs.getString("title"));
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(con, pstmt, rs);
		}
		return dto;
	}
	
	public void reply_ansnum(Map map) {
		Connection con=DBOpen.open();
		PreparedStatement pstmt=null;
		
		int grpno=(Integer)map.get("grpno");
		int ansnum=(Integer)map.get("ansnum");
		
		StringBuffer sql=new StringBuffer();
		sql.append(" update bbs set ansnum=ansnum+1 ");
		sql.append(" where grpno=? and ansnum>? ");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, grpno);//map에서 꺼내온거여서 그대로 
			pstmt.setInt(2, ansnum);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(con, pstmt);
		}
	}
		
	public boolean reply_create(BbsDTO dto) {//sql_insert할 값을 dto에 넣어줌
		boolean flag=false;
		Connection con=DBOpen.open();
		PreparedStatement pstmt=null;
		StringBuffer sql=new StringBuffer();
		sql.append(" insert into bbs(bbsno, wname, title, content, passwd, wdate, ");
		sql.append(" grpno, indent, ansnum, refnum) ");
		sql.append(" values((select nvl(max(bbsno),0)+1 from bbs), ");
		sql.append(" ?,?,?,?,sysdate,?,?,?,?) ");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getWname());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getPasswd());
			pstmt.setInt(5, dto.getGrpno()); //부모의 grpno 같고
			pstmt.setInt(6, dto.getIndent()+1);//부모의 grpno+1 해주고
			pstmt.setInt(7, dto.getAnsnum()+1);
			pstmt.setInt(8, dto.getBbsno());//부모의 글번호를 refnum에 insert 한다
			
			int cnt=pstmt.executeUpdate();
			
			if(cnt>0) {
				flag=true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(con, pstmt);
		}		
		return flag;
	}
	
	public boolean checkRefnum(int bbsno) {
		boolean flag=false;
		
		Connection con=DBOpen.open();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sql=new StringBuffer();
		sql.append(" select count(refnum) ");
		sql.append(" from bbs ");
		sql.append(" where refnum=? ");//refnum이 가지고 있는 개수를 말함
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, bbsno);//?에는 bbsno를 연결해준다
			
			rs=pstmt.executeQuery();
			
			rs.next();
			int cnt=rs.getInt(1);// select문에 대한 순번
			if(cnt>0) flag=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(con, pstmt, rs);
		}
		return flag;
	}
	
}
