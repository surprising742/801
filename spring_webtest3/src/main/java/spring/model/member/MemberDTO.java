package spring.model.member;

import org.springframework.web.multipart.MultipartFile;

public class MemberDTO {

	private String id;//       VARCHAR(10) NOT NULL, -- 아이디, 중복 안됨, 레코드를 구분하는 컬럼  
	private String passwd;//   VARCHAR(20) NOT NULL, -- 패스워드, 영숫자 조합 
	private String mname;//    VARCHAR(20) NOT NULL, -- 성명, 한글 10자 저장 가능 
	private String tel;//      VARCHAR(14)     NULL, -- 전화번호 
	private String email;//    VARCHAR(50) NOT NULL UNIQUE, -- 전자우편 주소, 중복 안됨 
	private String zipcode;//  VARCHAR(7)      NULL, -- 우편번호, 101-101 
	private String address1;// VARCHAR(150)     NULL, -- 주소 1 
	private String address2;// VARCHAR(50)     NULL, -- 주소 2 
	private String job;//     VARCHAR(20) NOT NULL, -- 직업 
	private String mdate;//    DATE        NOT NULL, -- 가입일     
	private String fname;//    VARCHAR(50) DEFAULT 'member.jpg', -- 회원 사진
	private String grade;//    CHAR(1)     DEFAULT 'H', -- 일반회원: H, 정지: Y, 손님:Z 
	private MultipartFile fnameMF;
	
	
	public MultipartFile getFnameMF() {
		return fnameMF;
	}

	public void setFnameMF(MultipartFile fnameMF) {
		this.fnameMF = fnameMF;
	}

	public MemberDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberDTO(String id, String passwd, String mname, String tel, String email, String zipcode, String address1,
			String address2, String job, String mdate, String fname, String grade) {
		super();
		this.id = id;
		this.passwd = passwd;
		this.mname = mname;
		this.tel = tel;
		this.email = email;
		this.zipcode = zipcode;
		this.address1 = address1;
		this.address2 = address2;
		this.job = job;
		this.mdate = mdate;
		this.fname = fname;
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", passwd=" + passwd + ", mname=" + mname + ", tel=" + tel + ", email=" + email
				+ ", zipcode=" + zipcode + ", address1=" + address1 + ", address2=" + address2 + ", job=" + job
				+ ", mdate=" + mdate + ", fname=" + fname + ", grade=" + grade + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getMdate() {
		return mdate;
	}

	public void setMdate(String mdate) {
		this.mdate = mdate;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
    
}
