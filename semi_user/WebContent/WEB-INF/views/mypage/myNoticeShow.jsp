<%@page import="dto.InquiryBoardFile"%>
<%@page import="dto.InquiryBoard"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% InquiryBoard board = (InquiryBoard) request.getAttribute("board"); %>   
    <% InquiryBoardFile file = (InquiryBoardFile) request.getAttribute("file"); %> 
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js" charset="utf-8"></script>
<script type="text/javascript">


$(document).ready(function(){
   $("#btnDelete1").click(function(){
	   console.log("잘나옴?");
	  submitContents($("#btnDelete1")); 
	  $("#btnDelete1").submit();

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

 #wrapper {
 	margin-bottom: 50px;
 }
 
 .modal-backdrop {
   z-index: 1;
}
 
</style>  
<div class="container">

<div style="margin: 0 auto; margin-top: 70px;">
<div style="background: lightgray; border: 1px solid lightgray; padding: 10px; width: 80%; margin: 0 auto; text-align: center;">
공지사항
</div>
<table class="table" style=" width: 80%; height:500px; margin: 0 auto; margin-top: 10px;  ">
	<tr style="background-color: lightgray;">
		<th style="text-align: left">${noticeboard.nBoardNo }</th>
		<th>${noticeboard.nBoardTitle }</th>
		<th>${noticeboard.counselorId }</th>
		<th>${noticeboard.nBoardDate }</th>
		<th>${noticeboard.nBoardViews }</th>
	</tr>
	<tr>
		<td colspan="5" style="background-color: lightgray;">
<%-- 		<textarea cols="110px;" rows="15px;" disabled="disabled" style="resize: none; background-color: white;"><%=board.getiBoardContent() %></textarea> --%>
		<div style=" height:500px; background-color: white; ">${noticeboard.nBoardContent }</div>
		</td>
	</tr>	
	<tr>
		<td>첨부파일 </td>
		<td><a href="/inquiry/filedown?fileno=${file.fileNo }">${ file.originalName}</a></td>
	</tr>
</table>
</div>	
<hr>
<br>
<button class="btn btn-md b-btn" style="float: left; background-color: #4ea1d3; color: white; "onclick="location.href='/mypage/boardlist';">목록</button>
<%-- <a href="/inquiry/modify?iboardno=<%=board.getiBoardNo()%>"> --%>
<c:if test="${noticeboard.counselorId eq counselorid }">
<!-- <button class="btn btn-md b-btn btnDelete" id="btnDelete" style="float: right; background-color: #4ea1d3; color: white;" data-toggle="modal" data-target="#myModal">삭제</button> -->
<%-- 	<button class="btn btn-md b-btn" style="float: right; background-color: #4ea1d3; color: white;"id="btnDelete1" onclick="location.href='/inquiry/delete?iboardno=<%=board.getiBoardNo()%>'">삭제</button> --%>
	<button class="btn btn-md b-btn" style="float: right; margin-right: 5px; background-color: #4ea1d3; color: white;" id="btnUpdate" onclick="location.href='/notice/modify?noticeboardno=${noticeboard.nBoardNo }'">수정</button>
</c:if>

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
        	글을 삭제하시겠습니까?
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <input type="submit" id="btnDelete1" class="btn btn-danger" data-dismiss="modal" value="확인" onclick="location.href='#'">
      </div>

    </div>
  </div>
</div>

<%-- <a href="/inquiry/delete?iboardno=<%=board.getiBoardNo()%>"><button id="btnDelete">삭제</button></a> --%>

</div>
<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
