<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<jsp:include page="/WEB-INF/views/layout/header.jsp" />
<jsp:include page="/WEB-INF/views/layout/header_board.jsp" />
<style type="text/css">
#content table {
	text-align : center;
	border-bottom : 1px solid #ccc
}
#content th {
	text-align : center;
	background-color:#4ea1d3;
	color:white;
}

#content tr td:nth-child(2) {
	text-align : left;
}
#content tr td:not(:first-child) {
	border-left : 1px solid #ccc;
}
div {
	text-align : center;
}
td a {
	text-decoration:none;
}
td a:hover {
	text-decoration:none;
}
#notice {
   background-color: #4ea1d3;
   border-color: #4ea1d3;
   color: white;
}
</style>
<div class="container">
	<form action="/admin/noticemanage" method="get">
		<div style="margin: 1%; float:right">
			<select name="searchType" style="padding:2px 5px">
				<option value="nboardtitle">제목</option>
				<option value="counselorId">작성자</option>
			</select> <input type="text" id="search" name="search">
			<button>검색</button>
		</div>
	</form>
	<table id="content" class="table">
	<tr>
		<th width="10%">No</th>
		<th width="50%">제목</th>
		<th width="20%">작성자</th>
		<th width="10%">작성일</th>
		<th width="10%">조회수</th>

	</tr>
	<c:forEach var="iList" items="${list}">
	<tr>
		<td>${iList.nBoardNo }</td>
		<td><a href="/admin/noticeview?boardNo=${iList.nBoardNo }">${iList.nBoardTitle }</a></td>
		<td>${iList.counselorId }</td>
		<td>${iList.nBoardDate }</td>
		<td>${iList.nBoardView }</td>
	</tr>
	</c:forEach>
	</table>
	
	<a href="/admin/noticewrite"><button class="btn btn-md b-btn" style="float: right; background-color: #4ea1d3; color: white;">작성</button></a>
	
	<jsp:include page="/WEB-INF/views/layout/notice_paging.jsp"/>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>