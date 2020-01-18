<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js" charset="utf-8"></script>
<script type="text/javascript">


$(document).ready(function(){

   
   $("#btnDelete1").click(function(){
	   console.log("잘나옴?");
// 	  submitContents($("#btnDelete1")); 
	  $("#btnDelete1").submit();

   })
   
   $("#btnList").click(function(){
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
 
 .modal-backdrop {
   z-index: 1;
}
 
</style>  

<div class="container">
<div style="margin: 0 auto; margin-top: 70px;">
<table class="table" style=" width: 80%; height:500px; margin: 0 auto; margin-top: 10px;  ">
	<tr style="background-color: lightgray;">
		<th style="text-align: left">No</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
	<tr style="background-color: lightgray;">
		<th style="text-align: left">${noticeBoard.nBoardNo }</th>
		<th>${noticeBoard.nBoardTitle }</th>
		<th>${noticeBoard.counselorId }</th>
		<th>${noticeBoard.nBoardDate }</th>
		<th>${noticeBoard.nBoardView }</th>
	</tr>
	<tr>
		<td colspan="7" style="background-color: lightgray;">
		<div style=" height:500px; background-color: white; padding:10px">${noticeBoard.nBoardContent }</div>
		</td>
	</tr>	
</table>
</div>	
<hr>
<br>
<button class="btn btn-md b-btn" id="btnList" style="float: left; background-color: #4ea1d3; color: white;" onclick="location.href='/admin/noticemanage'">목록</button>
<button class="btn btn-md b-btn" style="float: right; margin-right: 5px; background-color: #4ea1d3; color: white;" id="btnUpdate" onclick="location.href='/admin/noticemodify?boardNo=${noticeBoard.nBoardNo}'">수정</button>
<button class="btn btn-md b-btn btnDelete" id="btnDelete" style="float: right; background-color: #4ea1d3; color: white;" data-toggle="modal" data-target="#myModal">삭제</button>

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
        <input type="submit" id="btnDelete1" class="btn btn-danger" data-dismiss="modal" value="확인" onclick="location.href='/admin/noticedelete?boardNo=${noticeBoard.nBoardNo }'">
      </div>

    </div>
  </div>
</div>

<%-- <a href="/inquiry/delete?iboardno=<%=board.getiBoardNo()%>"><button id="btnDelete">삭제</button></a> --%>

</div>


<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>