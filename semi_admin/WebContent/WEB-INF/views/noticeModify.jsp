<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />   
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <!-- 스마트 에디터2 라이브러리 -->
<script type="text/javascript" src="/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js" charset="utf-8"></script>
<script type="text/javascript">
function submitContents(elClickedObj) {
    // 에디터의 내용을 textarea에 적용
    oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);

    try {
        elClickedObj.form.submit();
    } catch(e) {}
}


$(document).ready(function(){
   $("#btnUpdate1").click(function(){
	  submitContents($("#btnUpdate1")); 
		   $("form").submit();
   })

   //경고 모달 호출 메서드
	function warningModal(content) {
	
	   $(".modal-contents").text(content);
		$("#defaultModal").modal('show');
	}
      

   
});
</script>
 <style type="text/css">
 #content {
    width: 95%;
 }
 
 
 .container {
 	margin-bottom: 30px;
 }
 
.modal-backdrop {
   z-index: 1;
}
#wrapper {
	margin-bottom:40px;
}
</style>
<div class="container">
<!-- 경고 모달창 -->
<!--// 경고 모달창 -->
<div style="margin: 0 auto; margin-top: 70px;">
<form action="/admin/noticemodify"  method="post">
<div style="background: lightgray; border: 1px solid lightgray; padding: 10px; width: 80%; margin: 0 auto; text-align: center;">
공지사항
</div>
<input type="hidden" name="nBoardNo" value="${board.nBoardNo }" />
<table class="table" style="margin: 0 auto; margin-top: 10px; width: 80%;">
   <tr style="background-color: lightgray;">
   	<th style="text-align: center; width: 40%;">글 번호</th>
   	<th style="text-align:center; width:60%">제목</th>
    </tr>
    <tr style="background-color: lightgray;">
      <td style="text-align: center; width: 20%;" >${board.nBoardNo }</td>
      
      
      <td style="text-align:left; width:80%">
      <input type="text" id="title" name="title" value="${board.nBoardTitle }" style="width:80%">
      </td>
    </tr>
   <tr>
      <td style="background-color: white; text-align: center;" colspan="7">
         <textarea cols="70%" rows="20%" style="resize: none;"  id="content" name="content">
         ${board.nBoardContent}
         </textarea>
      </td>
   </tr>
</table>
</form>
</div>

<br>


<div style="margin-bottom: 65px;">
<a href="/admin/noticeview?boardNo=${board.nBoardNo }"><button class="btn btn-md b-btn" style="float: left; background-color: #4ea1d3; color: white;">취소</button></a>
<button class="btn btn-md b-btn btnUpdate" id="btnUpdate" style="float: right; background-color: #4ea1d3; color: white; float: right;" data-toggle="modal" data-target="#myModal">수정</button>
</div>

<div class="modal fade" id="myModal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">확인 메시지</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body content">
        	공지사항을 수정하시겠습니까?
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <a href="/admin/inquirymanage"><input type="submit" id="btnUpdate1" class="btn btn-danger" data-dismiss="modal" value="확인"></a>
      </div>

    </div>
  </div>
</div>
</div><!-- 컨테이너 끝--> 


<jsp:include page="/WEB-INF/views/layout/footer.jsp" />   

<!-- 스마트 에디터 적용 코드 -->
<!-- <textarea>태그에 스마트 에디터의 스킨을 입히는 코드 -->
<script type="text/javascript">

    var oEditors = []; 
    nhn.husky.EZCreator.createInIFrame({ 
    oAppRef: oEditors, 
   elPlaceHolder: "content", //에디터가 적용되는 <textarea>의 id 
    sSkinURI: "/resources/se2/SmartEditor2Skin.html", 
     fCreator: "createSEditor2" 
    }); 

 </script> 