package spring.model.memo;

public class MemoDTO {

	private int memono; 	// number(6)    not null,
	private String title; 	//  varchar(100)    not null,
	private String content;	// varchar(4000)   not null,
	private String wdate;	//   date    not null,
	private int viewcnt;	// number(5)   default 0,
	
	
	public MemoDTO(int memono, String title, String content, String wdate, int viewcnt) {
		super();
		this.memono = memono;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.viewcnt = viewcnt;
	}


	public MemoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "MemoDTO [memono=" + memono + ", title=" + title + ", content=" + content + ", wdate=" + wdate
				+ ", viewcnt=" + viewcnt + "]";
	}


	public int getMemono() {
		return memono;
	}


	public void setMemono(int memono) {
		this.memono = memono;
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


	public String getWdate() {
		return wdate;
	}


	public void setWdate(String wdate) {
		this.wdate = wdate;
	}


	public int getViewcnt() {
		return viewcnt;
	}


	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	
	
	
	
}
