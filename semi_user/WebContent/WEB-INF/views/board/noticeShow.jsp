<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="/WEB-INF/views/layout/header.jsp"/>

<jsp:include page="/WEB-INF/views/layout/header_board.jsp"/>

<script type="text/javascript">
$(document).ready(function() {
	//목록버튼 동작
	$("#btnList").click(function() {
		$(location).attr("href", "/notice/list");
	});
	
	//수정버튼 동작
	$("#btnUpdate").click(function() {
		$(location).attr("href", "/notice/modify?noticeboardno=${noticeboard.nBoardNo }");
	});

	//삭제버튼 동작
	$("#btnDelete").click(function() {
		$(location).attr("href", "/notice/delete?noticeboardno=${noticeboard.nBoardNo }");
	});
	
});
</script>
<style type="text/css">

 .container {
 	margin-bottom: 30px;
 }
 
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
</table>
</div>	

<div class="text-center">	
	<button id="btnList" class="btn btn-primary">목록</button>	
<c:if test="${noticeboard.counselorId eq counselorid }">
	<button id="btnUpdate" class="btn btn-info">수정</button>
	<button id="btnDelete" class="btn btn-danger">삭제</button>
</c:if>
</div>

</div><!-- .container -->


<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>