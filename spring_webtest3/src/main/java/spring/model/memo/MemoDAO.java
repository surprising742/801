package spring.model.memo;

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
public class MemoDAO {
	
	public int total(Map map) {
		int total=0;
		Connection con=DBOpen.open();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		//검색
		String col=(String)map.get("col");
		String word=(String)map.get("word");
		StringBuffer sql=new StringBuffer();
		sql.append(" select count(*) from memo ");
		
		if(word.length()>0&&col.equals("title_content")) {
			sql.append(" where title like '%'||?||'%' ");
			sql.append(" and content like '%'||?||'%' ");
		}else if(word.length()>0) {
			sql.append(" where " +col+ " like '%'||?||'%' ");
		}
		try {
			pstmt=con.prepareStatement(sql.toString());
			if(word.length()>0&&col.equals("title_content")) {
				pstmt.setString(1, word);
				pstmt.setString(2, word);
				
			}else if(word.length()>0) {
				pstmt.setString(1, word);
			}
			rs=pstmt.executeQuery();
			rs.next();
			total=rs.getInt(1);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(con, pstmt, rs);
		}
		
		return total;
	}
	
	

	public List<MemoDTO> list(Map map){
		List<MemoDTO> list=new ArrayList<MemoDTO>();
		Connection con=DBOpen.open();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		//검색 & 페이지 
		String col=(String)map.get("col");
		String word=(String)map.get("word");
		int sno=(Integer)map.get("sno");
		int eno=(Integer)map.get("eno");
		
		
		
		StringBuffer sql=new StringBuffer();
		
		sql.append(" select memono, to_char(wdate,'yyyy-mm-dd') wdate, ");
		sql.append(" viewcnt, title, r");
		sql.append(" from ( ");
		
		sql.append(" 	select memono, wdate, ");
		sql.append(" 	viewcnt, title, rownum r ");
		
		sql.append("	from( ");
		sql.append(" 		select memono, wdate, ");
		sql.append(" 		viewcnt, title ");
		sql.append(" 		from memo ");
		
		if(word.length()>0&&col.equals("title_content")) {
			sql.append(" 	where title like '%'||?||'%' ");
			sql.append(" 	and content like '%'||?||'%' ");
		}else if(word.length()>0) {
			sql.append(" 	where " +col+ " like '%'||?||'%' ");
		}
		
		sql.append(" 		order by memono desc ");
		sql.append("	 ) ");
		sql.append("	) ");
		sql.append(" where r>=? and r<=? ");
		
		try {
			int i=1;
			pstmt=con.prepareStatement(sql.toString());
			
			if(word.length()>0&&col.equals("title_content")) {
				pstmt.setString(i++, word);
				pstmt.setString(i++, word);
				
			}else if(word.length()>0) {
				pstmt.setString(i++, word);
			}
			pstmt.setInt(i++, sno);
			pstmt.setInt(i++, eno);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {//여러개의 데이터
				MemoDTO dto=new MemoDTO();
				dto.setMemono(rs.getInt("memono"));//저장할 것을 가져오는데 가져오는 곳의 결과 값 rs에서 가져오는것이기때문에 rs.get 으로 가져옴
				dto.setWdate(rs.getString("wdate"));
				dto.setViewcnt(rs.getInt("viewcnt"));
				dto.setTitle(rs.getString("title"));
				
				list.add(dto); //list에 dto 값들을 넣어줌
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(con, pstmt, rs);
		}
		return list;
	}
	
	public MemoDTO read(int memono) {
		MemoDTO dto=null;
		
		Connection con=DBOpen.open();
		
		PreparedStatement pstmt=null;
		
		ResultSet rs=null;
		
		StringBuffer sql=new StringBuffer();
		sql.append(" select * from memo ");
		sql.append(" where memono=? ");
		
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, memono);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {//한건의 데이터
				dto=new MemoDTO();
				
				dto.setMemono(rs.getInt("memono"));
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
	
	public void updateViewcnt(int memono) {
		
		Connection con=DBOpen.open();
		
		PreparedStatement pstmt=null;
		
		StringBuffer sql=new StringBuffer();
		sql.append(" update memo ");
		sql.append(" set viewcnt=viewcnt+1 ");//조회수 증가 값
		sql.append(" where memono=? ");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, memono);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(con, pstmt);
		}
	}
	
	public boolean update(MemoDTO dto) {//수정 __ 변수들 값 수정 
		boolean flag=false;
		
		Connection con=DBOpen.open();
		
		PreparedStatement pstmt=null;
		
		StringBuffer sql=new StringBuffer();
		sql.append(" update memo ");
		sql.append(" set ");
		sql.append(" title=?, ");
		sql.append(" content=? ");
		sql.append(" where ");
		sql.append(" memono=? ");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getTitle()); //dto가 가지고 있는 변수 이어주기
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getMemono());
			
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
	
	public boolean create(MemoDTO dto) {
		boolean flag=false;
	
		Connection con=DBOpen.open();
		
		PreparedStatement pstmt=null;
		
		StringBuffer sql=new StringBuffer();
		sql.append(" insert into memo(memono, title, content, wdate) ");
		sql.append(" values(memo_seq.nextval,?,?,sysdate) ");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
	
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
		
			
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
	
	
	public boolean delete(int memono) {
		
		boolean flag=false;
				
		Connection con=DBOpen.open();
		
		PreparedStatement pstmt=null;
		
		StringBuffer sql=new StringBuffer();
				
		sql.append(" delete from memo");
		sql.append(" where memono=? ");
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, memono);
			
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
	
	
	
	
}
