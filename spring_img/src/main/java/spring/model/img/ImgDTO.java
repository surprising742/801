package spring.model.img;

import org.springframework.web.multipart.MultipartFile;

public class ImgDTO {
	private int imgno;
	private String wname;
	private String title;
	private String content;
	private String passwd;
	private String filename;
	private MultipartFile filenameMf;
	
	public ImgDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ImgDTO [imgno=" + imgno + ", wname=" + wname + ", title=" + title + ", content=" + content + ", passwd="
				+ passwd + ", filename=" + filename + ", filenameMf=" + filenameMf + "]";
	}

	public ImgDTO(int imgno, String wname, String title, String content, String passwd, String filename,
			MultipartFile filenameMf) {
		super();
		this.imgno = imgno;
		this.wname = wname;
		this.title = title;
		this.content = content;
		this.passwd = passwd;
		this.filename = filename;
		this.filenameMf = filenameMf;
	}

	public int getImgno() {
		return imgno;
	}

	public void setImgno(int imgno) {
		this.imgno = imgno;
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public MultipartFile getFilenameMf() {
		return filenameMf;
	}

	public void setFilenameMf(MultipartFile filenameMf) {
		this.filenameMf = filenameMf;
	}
}
