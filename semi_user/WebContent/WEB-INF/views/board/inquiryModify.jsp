<%@ page import="dto.InquiryBoard" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />    
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <!-- 스마트 에디터2 라이브러리 -->
<script type="text/javascript" src="/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js" charset="utf-8"></script>
<script type="text/javascript">
$(document).ready(function(){
   

       // 라디오버튼 클릭시 이벤트 발생
       $("input:radio[name=chk]").click(function(){
    
          var secretChecked = $("input[name=chk]:checked").val();
          
//           console.log("라디오 체크 : " + secretChecked);
          
          $.ajax({
               type : "get",
               url : "/inquiry/secret",
               data : {
                  secretChecked : secretChecked
               },
               dataType : "html",
               success : function(data) {
//                   console.log("성공")
//                   console.log(data)

                  $("#showpw").html(data)
               },
               error : function() {
//                   console.log("실패");
               }
            });
       });
   
      

});


</script>  
<style type="text/css">

#wrapper {
margin-bottom: 40px;
}
</style>
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
   $("#btnCancel").click(function(){
      history.go(-1);
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
 
</style>
<div class="container">
<!-- 경고 모달창 -->
<!--// 경고 모달창 -->
<div style="margin: 0 auto; margin-top: 70px;">
<form action="/inquiry/modify"  method="post" enctype="multipart/form-data">
<div style="background: lightgray; border: 1px solid lightgray; padding: 10px; width: 80%; margin: 0 auto; text-align: center;">
문의사항
</div>
<input type="hidden" name="iBoardNo" value="${board.iBoardNo }" />
<table class="table" style="margin: 0 auto; margin-top: 10px; width: 80%;">
   <tr style="background-color: lightgray;">
      <th style="text-align: center; width: 100%;" >
      <select name="inquiryType" style="margin: 0 auto; padding: 3px;">
         <option>건의사항</option>
         <option>등급문의</option>
         <option>오류문의</option>
      </select>
      <input type="text" id="title" name="title" placeholder="제목을 입력하세요." style="width: 80%;" value="${board.iBoardTitle }">
      </th>
      </tr>
      <tr  style="background-color: lightgray;">
      <th><input type="file" id="file" name="file"></th>
      </tr>
   <tr>
      <td style="background-color: white; text-align: center;">
         <textarea cols="70%" rows="20%" style="resize: none;"  id="content" name="content">
         ${ board.iBoardContent}
         </textarea>
      </td>
   </tr>
   <tr>
		
		<td>첨부파일<a href="/inquiry/filedown?fileno=${file.fileNo }">${ file.originalName}</a></td>
   </tr>
</table>
<div style="float: right; position: relative; bottom: 34px;">
<input type="radio"  name="chk" value="0">비공개
<input type="radio"  name="chk" value="1" checked="checked">전체공개

<div id = "showpw" style="float: inherit">
</div>
</div>
</form>
</div>   

<br>


<div style="margin-bottom: 65px;">
<a href="/inquiry/list"><button class="btn btn-md b-btn" style="float: left; background-color: #4ea1d3; color: white;">목록</button></a>
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
        	글을 수정하시겠습니까?
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <a href="/inquiry/list"><input type="submit" id="btnUpdate1" class="btn btn-danger" data-dismiss="modal" value="확인"></a>
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