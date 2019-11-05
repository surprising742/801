package spring.utility.img;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 오픈라이브러리를 이용한 모듈(jakarta FileUpload API) 
 */
public class UploadSave {

	private HashMap<String,String[]> parameterMap; // 일반 <INPUT> 폼태그
	private HashMap<String,FileItem[]> fileItemMap; // 전송된 <FILE> 태그 객체가 저장

	public HashMap getFileItemMap() {
		return fileItemMap;
	}

	public void setFileItemMap(HashMap fileItemMap) {
		this.fileItemMap = fileItemMap;
	} 

	/**
	 * 매개변수 있는 생성자
	 * 
	 * @param request
	 *            HttpServletRequest 객체
	 * @param threshold
	 *            메모리에 저장할 최대크기
	 * @param max
	 *            업로드할 최대 파일크기
	 * @param repositoryPath
	 *            업로드 경로
	 * @throws FileUploadException
	 */
	public UploadSave(HttpServletRequest request, int threshold, int max, String repositoryPath)
			throws FileUploadException {

		parsing(request, threshold, max, repositoryPath);
	}

	/**
	 * 가장 중요한 부분임 ※
	 * 
	 * 일반 입력 필드와 파일 필드를 MAP에 저장
	 * 
	 * @param request
	 *            HttpServletRequest 객체
	 * @param threshold
	 *            메모리에 저장할 최대크기
	 * @param max
	 *            업로드할 최대++++ 파일크기
	 * @param repositoryPath
	 *            업로드 경로
	 * @throws FileUploadException
	 */
	private void parsing(HttpServletRequest request, int threshold, int max, String repositoryPath)
			throws FileUploadException {

		if (ServletFileUpload.isMultipartContent(request)) {

			parameterMap = new HashMap<String,String[]>();//일반파라미터저장(String)
			fileItemMap = new HashMap<String,FileItem[]>();//파일정보저장(fileItem)

			DiskFileItemFactory diskFileUpload = new DiskFileItemFactory();
			if (threshold != -1) {
				diskFileUpload.setSizeThreshold(threshold);
			}
			if (repositoryPath != null) {
				diskFileUpload.setRepository(new File(repositoryPath));
			}

			ServletFileUpload upload = new ServletFileUpload(diskFileUpload);

			upload.setSizeMax(max);
			upload.setHeaderEncoding("utf-8");

			List<FileItem> list = upload.parseRequest(request);
			Iterator<FileItem> iter = list.iterator();
			while (iter.hasNext()) {
				FileItem fileItem = iter.next();
				String name = fileItem.getFieldName();

				if (fileItem.isFormField()) { // 일반 폼 태그인지 검사
					// 일반 폼태그라면 처리
					String value = fileItem.getString();
					String[] values = parameterMap.get(name);
					if (values == null) {
						values = new String[1];
						values[0] = encode(value) ;
					} else {
						String[] tempValues = new String[values.length + 1];
						System.arraycopy(values, 0, tempValues, 0, values.length);
						tempValues[tempValues.length - 1] = encode(value);
						values = tempValues;
					}
					parameterMap.put(name, values);
				} else { // 파일 태그라면 처리
					System.out.println("파일 태그 발견됨 태그명: " + name);
					System.out.println("파일 태그 발견됨 태그명: " + fileItem.getName());
					FileItem[] items = fileItemMap.get(name);
					if (items == null) {
						items = new FileItem[1];
					    items[0] = fileItem;
					} else {
						FileItem[] tempItems = new FileItem[items.length + 1];
						System.arraycopy(items, 0, tempItems, 0, items.length);
						tempItems[tempItems.length - 1] = fileItem;
						items = tempItems;
					}
					fileItemMap.put(name, items);
				}
			}
		}
	}
	
	/**
	 * 전송받은 파일의 갯수를 리턴
	 * 
	 * @return
	 */
	public int getFileCount() {
		return this.fileItemMap.size();
	}

	/**
	 * 주어진 파라미터에 대한 값을 리턴
	 */
	public String getParameter(String name) {
	
		String[] values = parameterMap.get(name);
		if (values == null)
			return null;
		return values[0];
		
	}

	/**
	 * 파라미터의 값들을 리턴
	 */
	public String[] getParameterValues(String name) {
		
		return  parameterMap.get(name);
		
	}

	/**
	 * 전체 파라미터의 이름을 리턴
	 */
	public Iterator getParameterNames() {
		
		return parameterMap.keySet().iterator();

	}

	/**
	 * 지정한 파라미터 이름과 관련된 FileItem을 리턴합니다.
	 * 
	 * @param name
	 * @return
	 */
	public FileItem getFileItem(String name) {
	

		FileItem[] items = (FileItem[]) fileItemMap.get(name);
		if (items == null)
			return null;
		return items[0];

		
	}

	/**
	 * 여러 fileItem을 리턴
	 */
	public FileItem[] getFileItems(String name) {
		
	    return (FileItem[]) fileItemMap.get(name);
		
	}


	/**
	 * 서버에 올라온 파일을 서버의 디스크에 저장 파일명 처리: data.txt -> (0)data.txt ->(1)data.txt ->
	 * (2)data.txt..
	 * 
	 * @param fileItem
	 *            저장할 파일 객체
	 * @param upDir
	 *            저장할 서버의 폴더
	 * @return 저장된 파일명
	 */
	public static String saveFile(FileItem fileItem, String upDir) {

		String filename = ""; // 업로드 파일명

		// 파일 태그
		System.out.println("전송된 파일명: " + fileItem.getName());

		// 폴더 구분자 추출 d://temp/test/test.txt
		int idx = fileItem.getName().lastIndexOf("\\"); // 윈도우 기반

		if (idx == -1) { // 유닉스, 리눅스 기반
			idx = fileItem.getName().lastIndexOf("/");
		}

		// 순수 파일명 추출
		filename = fileItem.getName().substring(idx + 1);

		try {
			// -----------------------------------------------
			// 대상 폴더에 저장할 파일 객체 생성, 폴더 + 파일명
			// -----------------------------------------------
			File uploadedFile = new File(upDir, filename);

			// 올릴려는 파일과 같은 이름이 존재하면 중복파일 처리
			if (uploadedFile.exists() == true) {
				for (int k = 0; true; k++) {
					// 파일명 중복을 피하기 위한 일련 번호를 생성하여
					// 파일명으로 조합
					uploadedFile = new File(upDir, "(" + k + ")" + filename);
					// 조합된 파일명이 존재하지 않는다면, 일련번호가
					// 붙은 파일명 다시 생성
					// 존재하지 않는 경우
					// !을 이용해 false일 경우 참으로 처리되도록 변환
					if (!(uploadedFile.exists() == true)) {
						filename = "(" + k + ")" + filename;
						break;
					}
				}
			}

			// storage 폴더에 파일명 저장
			fileItem.write(uploadedFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return filename; // 업로드된 파일명 리턴
	}

	/**
	 * 파일을 삭제합니다.
	 */
	public static boolean deleteFile(String folder, String fileName) {
		boolean ret = false;

		try {
			if (fileName != null) { // 기존에 파일이 존재하는 경우 삭제
				File file = new File(folder + "/" + fileName);
				ret = file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	/**
	 * FileUpload 1.2, 1.3 한글 변환
	 * 
	 * @param str
	 * @return
	 */
	public static synchronized String encode(String str) {
		String corean = null;
		try {
			corean = new String(str.getBytes("ISO-8859-1"), "UTF-8");
			// corean= new String(str.getBytes("ISO-8859-1"), "KSC5601");
		} catch (Exception e) {
			return corean;
		}
		return corean;
	}

}