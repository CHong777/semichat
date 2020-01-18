package service.impl.board;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.face.board.InquiryBoardDao;
import dao.impl.board.InquiryBoardDaoImpl;
import dto.InquiryBoard;
import dto.InquiryBoardFile;
import service.face.board.InquiryBoardService;
import util.Paging;

public class InquiryBoardServiceImpl implements InquiryBoardService{

   private InquiryBoardDao inquiryBoardDao = new InquiryBoardDaoImpl();
   private InquiryBoard inquiryBoard = new InquiryBoard();
   
   @Override
   public Paging getPaging(HttpServletRequest req) {
      
      //요청파라미터 curPage를 파싱
      
      String param = req.getParameter("curPage");
      int curPage = 0;
      if(param!=null && !"".equals(param)) {
         curPage = Integer.parseInt(param);
      }
      
      String searchType = req.getParameter("searchType");
      String search = req.getParameter("search");
      
      Map<String, String> map = new HashMap<String, String>();
      
      if(searchType!=null & !"".equals(searchType)) {
         map.put("searchType",searchType);
      }
      
      if(search!=null && !"".equals(search)) {
         map.put("search", search);
      }
      
      //Board TB와 curPage 값을 이용한 Paging 객체를 생성하고 반환         
      int totalCount = inquiryBoardDao.selectCntAll(map);
         
//      System.out.println(totalCount);
      //Paging 객체 생성
      Paging paging = new Paging(totalCount, curPage);
      
//      System.out.println(paging);
      paging.setSearch(map);
      
      System.out.println("MAP : " +map);
      
      return paging;
   }

   @Override
   public List getList(Paging paging) {
      return inquiryBoardDao.selectAll(paging);
   }

   @Override
   public void hit(InquiryBoard board) {
      inquiryBoardDao.updateHit(board);
      
   }

   @Override
   public InquiryBoard getBoardno(HttpServletRequest req) {
      String param = req.getParameter("iboardno");
      int iboardno = 0;
      System.out.println("파람검사 서비스 : " + param);
      
      //전달 값이 없으면 종료
//      if(param == null)   return null;
      
      // 전달 파라미터를 int형으로 변환
      if(param != null && !"".equals(param)) {
    	  iboardno = Integer.parseInt(param);
    	  
      }
      System.out.println(iboardno);
      //전달 파라미터를 DTO(모델)에 담기
      
      inquiryBoard.setiBoardNo(iboardno);
      
      System.out.println("getBoardno = " + iboardno);
      System.out.println("getBoardno에서 board객체 = " + inquiryBoard);
      
      
      //객체 반환
      return inquiryBoard;
      
   }

   @Override
   public InquiryBoard view(InquiryBoard board) {
      
//      System.out.println("서비스 보드 번호 : " + board.getCounselorNo());
      
      return inquiryBoardDao.selectBoardByBoardno(board);
   }

   @Override
   public void write(HttpServletRequest req, HttpServletResponse resp) {
      
      
      InquiryBoardFile inquiryBoardFile = null;

      resp.setContentType("text/html;charset=UTF-8");
      HttpSession session = req.getSession();

      
      //응답 객체 출력 스트림 얻기
      PrintWriter out = null;
      try {
         out = resp.getWriter();
      } catch (IOException e) {
         e.printStackTrace();
      }
      
      
      //encrype이 multipart/form-data가 맞는지 확인
      boolean isMultipart = false;
      isMultipart = ServletFileUpload.isMultipartContent(req);
      
      //1-1. multipart/form-data 인코딩으로 전송되지 않았을 경우
      if( !isMultipart) {
         out.append("<h1>enctype이 multipart/form-data가 아님</h1>");
         out.append("<a href='/commons/fileupload'>이동</a>");
         
         return;
      }
      
      
      //업로드된 파일 처리하는 아이템팩토리 객체 생성
      DiskFileItemFactory factory = null;
      factory = new DiskFileItemFactory();
      
      //업로드된 아이템이 용량이 적당히 작으면 메모리에서 처리
      int maxMem = 1 * 1024 * 1024; // 1MB
      factory.setSizeThreshold(maxMem);
      
      //용량이 적당히 크면 임시파일을 만들어서 처리(디스크)
      ServletContext context = req.getServletContext();
      String path = context.getRealPath("tmp");
      File repository = new File(path);
      
      //TEST
//      System.out.println("repository : " + repository);
      
      factory.setRepository(repository);

      //업로드 허용 용량 기준을 넘지 않을 경우에만 업로드 처리
      int maxFile = 10 * 1024 * 1024; //10MB
      
      //파일 업로드 객체 생성
      ServletFileUpload upload = null;
      upload = new ServletFileUpload(factory);
      
      //파일 업로드 용량제한 설정 : 10MB
      upload.setFileSizeMax(maxFile);
      
      //업로드된 데이터 추출(파싱)
      List<FileItem> items = null;
      try {
         items = upload.parseRequest(req);
      } catch (FileUploadException e) {
         e.printStackTrace();
      }
      
      //파싱된 데이터 처리하기
      Iterator<FileItem> iter = items.iterator();
      
      //모든 요청정보 처리
      while( iter.hasNext()) {
         FileItem item = iter.next();
         //빈 파일 처리
         if(item.getSize() <= 0) continue;
         
         //일반적인 요청 데이터 처리
         if(item.isFormField()) {
            if(inquiryBoard == null)
               inquiryBoard = new InquiryBoard();

            //key값에 따라 처리방식 다르게 하기
            String key = item.getFieldName();
            
            if("title".equals(key)) {
               try {
                  inquiryBoard.setiBoardTitle(item.getString("utf-8"));
               } catch (UnsupportedEncodingException e) {
                  e.printStackTrace();
               }
            
            } else if("content".equals(key)) {
               try {
                  inquiryBoard.setiBoardContent(item.getString("utf-8"));
               } catch (UnsupportedEncodingException e) {
                  e.printStackTrace();
               }
               
            } else if("counselorId".equals(key)) {
               try {
                  inquiryBoard.setCounselorId(item.getString("utf-8"));
               } catch (UnsupportedEncodingException e) {
                  e.printStackTrace();
               }
            
            } else if("secretpw".equals(key)) {
               try {
                  inquiryBoard.setiBoardSecretPw(item.getString("utf-8"));
               } catch (UnsupportedEncodingException e) {
                  e.printStackTrace();
               }
                  
            } else if("inquiryType".equals(key)) {
               try {
                  inquiryBoard.setiBoardInquiryType(item.getString("utf-8"));
               } catch (UnsupportedEncodingException e) {
                  e.printStackTrace();
               }
            } else if("chk".equals(key)) {
               try {
                  inquiryBoard.setiBoardSecret(item.getString("utf-8"));
               } catch (UnsupportedEncodingException e) {
                  e.printStackTrace();
               }      
               
            } // key값 비교 if
            
//            System.out.println("인쿼리보드 객체 : "+inquiryBoard);
         } else // 파일처리
            
         {
            inquiryBoardFile = new InquiryBoardFile();

            //UUID 생성
            UUID uuid = UUID.randomUUID();

            //12자리 uid 얻기
            String u = uuid.toString().split("-")[4];
            //------------------
            
            //로컬 파일 저장소에 파일 저장하기
         
            //로컬 저장소 파일 객체
            File up = new File(
                  context.getRealPath("upload"),
                  item.getName() + "_" + u);
               //파일의 경로는 "/upload
               //파일의 이름은 "원본명_uid"
            
//            System.out.println(up);
         
            
            inquiryBoardFile.setOriginalName(item.getName());
            inquiryBoardFile.setStoredName(item.getName() + "_" + u);
            
            //DAO를 통해 DB에 INSERT
            


            
            // - - -처리가 완료된 파일 업로드 하기 - - -
            // 로컬 저장소에 실제 저장
            
            try {
               item.write(up); //실제 업로드
               item.delete(); //임시 파일 삭제
            } catch (Exception e) {
               e.printStackTrace();
            }
            // - - - - - - - - - - - - - - - - - - - - -
            
            
         }//파일 처리 if         
      }//요청파라미터 처리 while
      String id = (String) req.getSession().getAttribute("counselorid");
   
      
      inquiryBoard.setCounselorId(id);
   
      int boardno = inquiryBoardDao.selectBoardno();
      inquiryBoard.setiBoardNo(boardno);
      

      
      
      inquiryBoardDao.insert(inquiryBoard);
      
      System.out.println("write에서 inquiryBoard " +inquiryBoard);
      System.out.println("write에서 boardno =" +boardno);
      if( inquiryBoardFile != null ) {
//         System.out.println("if 안 실행되면 안됨");
         inquiryBoardFile.setiBoardNo(boardno);
         inquiryBoardDao.insertFile(inquiryBoardFile);   
      }
      
   }

   @Override
   public InquiryBoardFile getFileInfo(InquiryBoard board) {
      
      return inquiryBoardDao.getFileInfo(board);
   }

   @Override
   public InquiryBoardFile getFile(HttpServletRequest req) {
      //요청파라미터 fileno 얻기
      InquiryBoardFile downFile = getFileno(req);

      //파일 정보 얻기
      getFile(downFile);

      //반환
      return downFile;
   }

   @Override
   public void getFile(InquiryBoardFile downFile) {
      inquiryBoardDao.selectByFileno(downFile);
      
   }
   
   
   private InquiryBoardFile getFileno(HttpServletRequest req) {
      //전달파라미터 받기
      String param = req.getParameter("fileno");


      //int형으로 형변환
      int fileno = 0;
      if(param!=null && !"".equals(param)) {
         fileno = Integer.parseInt(param);
      }


      //DTO에 저장
      InquiryBoardFile uploadFile = new InquiryBoardFile();
      uploadFile.setFileNo(fileno);

      return uploadFile;
   }

   @Override
   public void update(HttpServletRequest req, HttpServletResponse resp) {
      
      //빈파일 판단 변수
      int flag = 0;
      
      int iBoardNo = 0;
      
      InquiryBoardFile inquiryBoardFile = null;

      resp.setContentType("text/html;charset=UTF-8");
      HttpSession session = req.getSession();

      
      //응답 객체 출력 스트림 얻기
      PrintWriter out = null;
      try {
         out = resp.getWriter();
      } catch (IOException e) {
         e.printStackTrace();
      }
      
      
      //encrype이 multipart/form-data가 맞는지 확인
      boolean isMultipart = false;
      isMultipart = ServletFileUpload.isMultipartContent(req);
      
      //1-1. multipart/form-data 인코딩으로 전송되지 않았을 경우
      if( !isMultipart) {
         out.append("<h1>enctype이 multipart/form-data가 아님</h1>");
         out.append("<a href='/commons/fileupload'>이동</a>");
         
         return;
      }
      
      
      //업로드된 파일 처리하는 아이템팩토리 객체 생성
      DiskFileItemFactory factory = null;
      factory = new DiskFileItemFactory();
      
      //업로드된 아이템이 용량이 적당히 작으면 메모리에서 처리
      int maxMem = 1 * 1024 * 1024; // 1MB
      factory.setSizeThreshold(maxMem);
      
      //용량이 적당히 크면 임시파일을 만들어서 처리(디스크)
      ServletContext context = req.getServletContext();
      String path = context.getRealPath("tmp");
      File repository = new File(path);
      
      //TEST
//      System.out.println("repository : " + repository);
      
      factory.setRepository(repository);

      //업로드 허용 용량 기준을 넘지 않을 경우에만 업로드 처리
      int maxFile = 10 * 1024 * 1024; //10MB
      
      //파일 업로드 객체 생성
      ServletFileUpload upload = null;
      upload = new ServletFileUpload(factory);
      
      //파일 업로드 용량제한 설정 : 10MB
      upload.setFileSizeMax(maxFile);
      
      //업로드된 데이터 추출(파싱)
      List<FileItem> items = null;
      try {
         items = upload.parseRequest(req);
      } catch (FileUploadException e) {
         e.printStackTrace();
      }
      
      //파싱된 데이터 처리하기
      Iterator<FileItem> iter = items.iterator();
      
      //모든 요청정보 처리
      while( iter.hasNext()) {
         FileItem item = iter.next();
         //빈 파일 처리
         if(item.getSize() <= 0) {
            
            flag = -1;
            continue;
         }
            
            
         
         //일반적인 요청 데이터 처리
         if(item.isFormField()) {
            if(inquiryBoard == null)
               inquiryBoard = new InquiryBoard();

            //key값에 따라 처리방식 다르게 하기
            String key = item.getFieldName();
            
            if("title".equals(key)) {
               try {
                  inquiryBoard.setiBoardTitle(item.getString("utf-8"));
               } catch (UnsupportedEncodingException e) {
                  e.printStackTrace();
               }
            
            }
            else if("iBoardNo".equals(key)) {
               try {
                  iBoardNo = Integer.parseInt(item.getString("utf-8"));
               } catch (UnsupportedEncodingException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
            }   
            
            else if("content".equals(key)) {
               try {
                  inquiryBoard.setiBoardContent(item.getString("utf-8"));
               } catch (UnsupportedEncodingException e) {
                  e.printStackTrace();
               }
               
            } else if("counselorId".equals(key)) {
               try {
                  inquiryBoard.setCounselorId(item.getString("utf-8"));
               } catch (UnsupportedEncodingException e) {
                  e.printStackTrace();
               }
            
            } else if("secretpw".equals(key)) {
               try {
                  inquiryBoard.setiBoardSecretPw(item.getString("utf-8"));
               } catch (UnsupportedEncodingException e) {
                  e.printStackTrace();
               }
                  
            } else if("inquiryType".equals(key)) {
               try {
                  inquiryBoard.setiBoardInquiryType(item.getString("utf-8"));
               } catch (UnsupportedEncodingException e) {
                  e.printStackTrace();
               }
            } else if("chk".equals(key)) {
               try {
                  inquiryBoard.setiBoardSecret(item.getString("utf-8"));
               } catch (UnsupportedEncodingException e) {
                  e.printStackTrace();
               }      
            } else if("".equals(key)) {
               try {
                  inquiryBoard.setiBoardSecret(item.getString("utf-8"));
               } catch (UnsupportedEncodingException e) {
                  e.printStackTrace();
               }      

            } // key값 비교 if
            
//            System.out.println("인쿼리보드 객체 : "+inquiryBoard);
         } else // 파일처리
            
         {
            inquiryBoardFile = new InquiryBoardFile();

            //UUID 생성
            UUID uuid = UUID.randomUUID();

            //12자리 uid 얻기
            String u = uuid.toString().split("-")[4];
            //------------------
            
            //로컬 파일 저장소에 파일 저장하기
         
            //로컬 저장소 파일 객체
            File up = new File(
                  context.getRealPath("upload"),
                  item.getName() + "_" + u);
               //파일의 경로는 "/upload
               //파일의 이름은 "원본명_uid"
            
//            System.out.println(up);
         
            
            inquiryBoardFile.setOriginalName(item.getName());
            inquiryBoardFile.setStoredName(item.getName() + "_" + u);
            
            //DAO를 통해 DB에 INSERT
            
            // - - -처리가 완료된 파일 업로드 하기 - - -
            // 로컬 저장소에 실제 저장
            
            try {
               item.write(up); //실제 업로드
               item.delete(); //임시 파일 삭제
            } catch (Exception e) {
               e.printStackTrace();
            }
            // - - - - - - - - - - - - - - - - - - - - -
            
            
         }//파일 처리 if         
      }//요청파라미터 처리 while
      String id = (String) req.getSession().getAttribute("counselorid");
   
      
      
      inquiryBoard.setCounselorId(id);
   
//      int boardno = inquiryBoard.getiBoardNo();
      
//      System.out.println("겟아이보드넘 : " +inquiryBoard.getiBoardNo());
      inquiryBoard.setiBoardNo(iBoardNo);
      
      
      //게시판 업데이트
      inquiryBoardDao.update(inquiryBoard);
      
      System.out.println("아이보드 넘버 뭔데" + iBoardNo);
      
      //flag가 -1이 아니면 파일도 수정 한거임 , 파일업로드햇던게시글
      if( flag != -1 ) {
         inquiryBoardFile.setiBoardNo(iBoardNo);
         System.out.println(inquiryBoardFile + "입니당");
         
         
         //업데이트는  기존파일이 있을경우만 고려하기 때문에 
         // 첨에 파일 없는데 수정하는 경우 고려 못해서 insert delete 해야 할듯
         
//         inquiryBoardDao.updateFile(inquiryBoardFile);   
         
         inquiryBoardDao.delete(inquiryBoardFile);
      
         
         inquiryBoardDao.insertFile(inquiryBoardFile);
      } 
      //빈파일이면 기존 파일 삭제
      else {   
         InquiryBoardFile deleteFile = new InquiryBoardFile();
         deleteFile.setiBoardNo(iBoardNo);
         System.out.println("파일 선택 안한경우 = 널값 :" +  deleteFile.getiBoardNo());
         inquiryBoardDao.delete(deleteFile);
      }
      
      
      //////////////////////////////
      //홍철 코드 추가 - 삭제, 삽입 방식으로
      
//      inquiryBoardFile.setiBoardNo(boardno);
//      
//      
//      //지우고
//      inquiryBoardDao.delete(inquiryBoardFile);
//      
//      //삽입
//      inquiryBoardDao.insertFile(inquiryBoardFile);
//      
//      //////////////////////////////////////////
//      
      
   }

   @Override
   public InquiryBoardFile getFileNo(HttpServletRequest req) {
      
      String param = req.getParameter("iboardno");
      
      //int형으로 형변환
      int iboardno = 0;
      if(param!=null && !"".equals(param)) {
         iboardno = Integer.parseInt(param);
      }


      //DTO에 저장
      InquiryBoardFile uploadFile = new InquiryBoardFile();
      uploadFile.setInquiryBoardno(iboardno);

      return uploadFile;
   }

   @Override
   public void delete(InquiryBoardFile file) {
      inquiryBoardDao.delete(file);

   }

   @Override
   public void delete(InquiryBoard deleteBoard) {
      inquiryBoardDao.delete(deleteBoard);
      
   }

@Override
public InquiryBoard getBoardpw(InquiryBoard board) {
	
	return inquiryBoardDao.selectBoardByBoardno(board);
}

}