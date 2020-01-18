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

#content tr td:nth-child(3) {
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
#inquiry {
   background-color: #4ea1d3;
   border-color: #4ea1d3;
   color: white;
}
</style>

<div class="container">
	<form action="/admin/inquirymanage" method="get">
		<div style="margin: 1%; float:right">
			<select name="searchType" style="padding:2px 5px">
				<option value="iboardtitle">제목</option>
				<option value="counselorId">작성자</option>
			</select> <input type="text" id="search" name="search">
			<button>검색</button>
		</div>
	</form>
	<table id="content" class="table">
	<tr>
		<th style="width: 10%">번호</th>
     	<th style="width: 10%">문의유형</th>
      	<th style="width: 35%">제목</th>
      	<th style="width: 20%">작성자</th>
      	<th style="width: 15%">작성일</th>      
      	<th style="width: 10%">조회수</th>   
	</tr>
	<c:forEach var="iList" items="${list}">
	<tr>
		<td>${iList.iBoardNo }</td>
		<td>${iList.iBoardInquiryType }</td>
		<c:if test="${iList.iBoardAnswer eq '[답변 완료]'}">
		<td><a href="/admin/inquiryview?iboardNo=${iList.iBoardNo }">${iList.iBoardTitle }</a>&nbsp;&nbsp;
			<a style="color:skyblue">${iList.iBoardAnswer}</a></td>
		</c:if>
		<c:if test="${iList.iBoardAnswer eq '[답변진행중]'}">
		<td><a href="/admin/inquiryview?iboardNo=${iList.iBoardNo }">${iList.iBoardTitle }</a>&nbsp;&nbsp;
			<a>${iList.iBoardAnswer}</a></td>
		</c:if>
		<td>${iList.counselorId }</td>
		<td>${iList.iBoardDate }</td>
		<td>${iList.iBoardViews }</td>
	</tr>
	</c:forEach>
	</table>
		
	<jsp:include page="/WEB-INF/views/layout/inquirymanage_paging.jsp"/>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>