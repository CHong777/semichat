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
	<form action="/admin/corporationmanage" method="get">
		<div style="margin: 1%; float:right">
			<select name="searchType" style="padding:2px 5px">
				<option value="corporationName">기업명</option>
				<option value="ceo">대표명</option>
			</select> <input type="text" id="search" name="search">
			<button>검색</button>
		</div>
	</form>
	<table id="corp" class="table">
	<tr>
		<th width="5%">No</th>
		<th width="25%">기업명</th>
		<th width="10%">대표명</th>
		<th width="12%">가입날짜</th>
		<th width="15%">전화번호</th>
		<th width="10%">사용자수</th>
		<th width="10%">매출</th>
		<th width="8%">관리</th>
	</tr>
	<c:forEach var="iList" items="${list}">
	<tr>
		<td>
			${iList.corporationNo }
			<input type="hidden" name="corporationNo" id="corporationNo" value="${iList.corporationNo }"/>
		</td>
		<td>${iList.corporationName }</td>
		<td>${iList.ceo}</td>
		<td>${iList.registerDate }</td>
		<td>${iList.phoneNum }</td>
		<td>${iList.counselorCnt }</td>
		<td>${iList.sales }</td>
		<td><button id="manage" type="submit" onclick="location.href='/admin/membermanage?corporationNo=${iList.corporationNo }';">관리</button></td>
	</tr>
	</c:forEach>
	</table>
	<jsp:include page="/WEB-INF/views/layout/corporation_paging.jsp"/>
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>







