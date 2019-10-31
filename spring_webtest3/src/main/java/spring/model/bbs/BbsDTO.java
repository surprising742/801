package spring.model.bbs;

public class BbsDTO {
	private int bbsno;	// number(7) not null,   --글 일련번호
	private String wname;	//   varchar(20) not null,   --글쓴이
	private String title;	//   varchar(100)    not null,   --제목
	private String content;	// varchar(4000)   not null,   --글 내용
	private String passwd;	//  varchar(15) not null,   --비밀번호
	private int viewcnt;	// number(5)   default 0, --조회수 , 기본값 사용
	private String wdate;	//   date    not null,   --등록 날짜 sysdate
	private int grpno;	//number(7)   default 0,  --부모글번호
	private int indent;	//  number(2)   default 0,  --답변여부, 답변의 깊이
	private int ansnum;	//  number(5)   default 0, --답변순서
	
	
	public BbsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public BbsDTO(int bbsno, String wname, String title, String content, String passwd, int viewcnt, String wdate,
			int grpno, int indent, int ansnum) {
		super();
		this.bbsno = bbsno;
		this.wname = wname;
		this.title = title;
		this.content = content;
		this.passwd = passwd;
		this.viewcnt = viewcnt;
		this.wdate = wdate;
		this.grpno = grpno;
		this.indent = indent;
		this.ansnum = ansnum;
	}


	@Override
	public String toString() {
		return "BbsDTO [bbsno=" + bbsno + ", wname=" + wname + ", title=" + title + ", content=" + content + ", passwd="
				+ passwd + ", viewcnt=" + viewcnt + ", wdate=" + wdate + ", grpno=" + grpno + ", indent=" + indent
				+ ", ansnum=" + ansnum + "]";
	}


	public int getBbsno() {
		return bbsno;
	}


	public void setBbsno(int bbsno) {
		this.bbsno = bbsno;
	}


	public String getWname() {
		return wname;
	}


	public void setWname(String wname) {
		this.wname = wname;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getPasswd() {
		return passwd;
	}


	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}


	public int getViewcnt() {
		return viewcnt;
	}


	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}


	public String getWdate() {
		return wdate;
	}


	public void setWdate(String wdate) {
		this.wdate = wdate;
	}


	public int getGrpno() {
		return grpno;
	}


	public void setGrpno(int grpno) {
		this.grpno = grpno;
	}


	public int getIndent() {
		return indent;
	}


	public void setIndent(int indent) {
		this.indent = indent;
	}


	public int getAnsnum() {
		return ansnum;
	}


	public void setAnsnum(int ansnum) {
		this.ansnum = ansnum;
	}
		
}
