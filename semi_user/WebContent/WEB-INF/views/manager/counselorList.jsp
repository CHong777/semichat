<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@page import="dto.InquiryBoard"%>
<%@page import="java.util.List"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 검색조건 쿼리스트링 가져오기 -->
<c:forEach var="i" items="${paging.search }">
<c:if test="${i.key=='searchType' }">
   <c:set var="searchType" value="${i.value }"/>
</c:if>
<c:if test="${i.key=='keyword' }">
   <c:set var="keyword" value="${i.value }"/>
</c:if>
</c:forEach>
<c:set var="query" value="&searchType=${searchType}&keyword=${keyword }"/>
<!--  -->



<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js" charset="utf-8"></script>

<jsp:include page="/WEB-INF/views/layout/header.jsp"/>
<jsp:include page="/WEB-INF/views/layout/header_manager.jsp"/>

<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js" charset="utf-8"></script>
<script type="text/javascript">

function popupOpen(counselorNo){
	var url ="/manager/profile?counselorNo=" + counselorNo;
	var winWidth = 400;
	var winHeight = 600;
	var winLeft = Math.ceil(( window.screen.width - winWidth )/2);
	var winTop = Math.ceil(( window.screen.width - winHeight )/2);
	var popupOption = "width=" + winWidth+ ", height=" + winHeight +", left=" + winLeft + ", winTop=" + winTop;
	var myWindow = window.open(url, "", popupOption);

}

$(document).ready(function(){
   
});

</script>  


<style type="text/css">

#tablestyle {
    background-color:  #4ea1d3; 
   color: white;
}


th, td{
   
   text-align: center;
}

tr td:not(:first-child), tr th:not(:first-child) {
   border-left: 0.4px solid lightgray;
}

tr td:nth-child(2) {
   text-align: left;
}

option {
   
}

td {
 border-bottom: 0.4px solid lightgray;
}

#inqButton {
   background-color: #4ea1d3;
   border-color: #4ea1d3;
   color: white;
}

#counselorShow {
   background-color: #4ea1d3;
   border-color: #4ea1d3;
   color: white;
}

</style>
<div class="container">

<form action="/manager/counselormanage" method="get">
<div style="margin: 1%">
<select name="searchType">
	<option value="counselorname">이름</option>
	<option value="counselorposition">직책</option>
</select>
<input type="text" id="search" name="search">
<button>검색</button>
</div>
</form>

<!-- <form action="/inquiry/listdelete" method="post"> -->
<table class="table table-hover" style="text-align: center;">

   <tr id="tablestyle">
<!--    	  <th style="width: 5%"><input type="checkbox" class="chk" id="checkAll" name="checkAll"> -->
      <th style="width: 10%">No</th>
      <th style="width: 10%">이름</th>
      <th style="width: 35%">직책</th>
      <th style="width: 25%">가입일</th>
      <th style="width: 20%">정보</th>      
   </tr>
         <c:forEach var="counselorList" items="${list}">
            <tr>	
               <td>${counselorList.rnum }</td>
               <td>${counselorList.counselorName }</td>
               <td>${ counselorList.counselorPosition}</td>
               <td>${ counselorList.counselorSigndate}</td>
               <td><button onclick = "popupOpen( ${ counselorList.counselorNo});">프로필</button></td>
            </tr>
         </c:forEach>

</table>

<jsp:include page="/WEB-INF/views/layout/manager_paging.jsp" />  
</div> <!-- container -->
<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>

    