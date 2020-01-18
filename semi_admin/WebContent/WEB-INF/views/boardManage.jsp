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

#content tr td:nth-child(4) {
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
#board {
   background-color: #4ea1d3;
   border-color: #4ea1d3;
   color: white;
}
</style>
<div class="container">
	<form action="/admin/boardmanage" method="get">
		<div style="margin: 1%; float:right">
			<select name="searchType" style="padding:2px 5px">
				<option value="corporationName">기업명</option>
				<option value="nboardtitle">제목</option>
				<option value="counselorId">작성자</option>
				<option value="boardSorting">게시판</option>
			</select> <input type="text" id="search" name="search">
			<button>검색</button>
		</div>
	</form>
	<table id="content" class="table">
	<tr>
		<th width="5%">No</th>
		<th width="15%">기업명</th>
		<th width="10%">게시판</th>
		<th width="35%">제목</th>
		<th width="15%">작성자</th>
		<th width="10%">작성일</th>
		<th width="10%">조회수</th>

	</tr>
	<c:forEach var="iList" items="${list}">
	<tr>
		<td>${iList.adminBoardNo }</td>
		<td>${iList.corporationname }</td>
		<td>${iList.boardSorting}</td>
		<td><a href="/admin/view?boardSorting=${iList.boardSorting}&boardNo=${iList.boardNo }">${iList.title }</a></td>
		<td>${iList.writer }</td>
		<td>${iList.writeDate }</td>
		<td>${iList.views }</td>
	</tr>
	</c:forEach>
	</table>
	<jsp:include page="/WEB-INF/views/layout/boardmanage_paging.jsp"/>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>