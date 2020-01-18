package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import dto.InquiryBoardFile;
import service.face.InquiryBoardService;
import service.impl.InquiryBoardServiceImpl;

/**
 * Servlet implementation class InquiryDownloadController
 */
@WebServlet("/admin/filedown")
public class InquiryDownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InquiryBoardService inquiryBoardService = new InquiryBoardServiceImpl();
 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//다운로드 대상 파일 정보 조회하기
		InquiryBoardFile downFile = inquiryBoardService.getFile(req);
//		System.out.println(downFile); 
		
		//다운로드용 File 객체 만들기
		String path = getServletContext().getRealPath("upload"); //경로
		String filename = downFile.getStoredName(); //파일이름
		
		File file = new File(path, filename);	
		
		//파일 다운로드 시키기 - 응답 정보 객체를 설정한다
		//Response Message의 Header를 수정한다
		
		//응답 body의 길이 설정
		resp.setHeader("Content-length", String.valueOf(file.length()));
		
		//응답 데이터의 형식(MIME-Type)
		resp.setContentType("application/octet-stream"); //이진파일의 형태
		
		//응답 파일의 저장위치 지정하기
		resp.setHeader("Content-Disposition", "attachment;fileName=" 
				+ new String(downFile.getOriginalName().getBytes("utf-8"), "8859_1") + ";");
		
		
		// - - - 응답 메시지의 응답 Body(본문) 작성 - - -
		InputStream is = new FileInputStream(file);
		
		// 파일 출력 스트림(브라우저)
		OutputStream os = resp.getOutputStream();
		
		// 파일 입력 -> 브라우저 출력
		IOUtils.copy(is, os);
		
		os.flush();
		
		is.close();
		os.close();
	}
}
