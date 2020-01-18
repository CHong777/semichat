<%@page import="dto.Corporation"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    

<style type="text/css">
table {
	text-align : center;
	border-bottom : 1px solid #ccc
}
th {
	text-align : center;
	background-color:#4ea1d3;
	color:white;
}

tr td:nth-child(2) {
	text-align : left;
}
tr td:not(:first-child) {
	border-left : 1px solid #ccc;
}
div {
	text-align : center;
}
button{
	width:60px;
	border:0;
	outline:0;
	padding:5px;
}
h2 a{
	text-decoration:none;
	color:#4ea1d3;
}
h2 a:hover{
	text-decoration:none;
}
</style>
<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<div class="container">
	<h2><a href="/admin/corporationmanage">기업목록</a></h2>
	<form action="/admin/membermanage" method="get">
		<div style="margin: 1%; float:right">
		<input type="hidden" name="corporationNo" id="corporationNo" value="${list[1].corporationNo }"/>
			<select name="searchType" style="padding:2px 5px">
				<option value="counselorName">이름</option>
				<option value="counselorId">아이디</option>
			</select> <input type="text" id="search" name="search">
			<button>검색</button>
		</div>
	</form>
	<table id="counselor" class="table">
	<tr>
		<th width="10%">No</th>
		<th width="15%">이름</th>
		<th width="15%">아이디</th>
		<th width="25%">이메일</th>
		<th width="20%">전화번호</th>
		<th width="15%">직급</th>
	</tr>
	<c:forEach var="iList" items="${list}">
	<tr>
		<td>${iList.counselorNo }</td>
		<td>${iList.counselorName }</td>
		<td>${iList.counselorId}</td>
		<td>${iList.counselorEmail }</td>
		<td>${iList.counselorPhonenumber }</td>
		<td>${iList.counselorPosition }</td>
	</tr>
	</c:forEach>
	</table>
	<jsp:include page="/WEB-INF/views/layout/member_paging.jsp"/>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>







