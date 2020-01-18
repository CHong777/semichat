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
<jsp:include page="/WEB-INF/views/layout/header_board.jsp"/>

<script type="text/javascript">
$(document).ready(function(){
	
   //비밀번호 체크 모달
   $(".title").click(function(){	
	  var realpass = "";
	  var boardno = "";
	  realpass = $(this).attr("data-iBoardPw");
	  boardno = $(this).attr("data-iBoardNo");
	  
// 	  console.log(boardno)
	  $(this).attr("onclick", "location.href='/inquiry/show?iboardno=" + boardno + "';");
	  $("#btnWrite1").click(function() {
      var pass = $("#pass").val();      
//       console.log("11111111111111"+pass);
//       console.log(realpass);
// 	console.log(boardno)
      if(realpass == pass) {
     	 window.location.href = './show?iboardno='+boardno;
      } else {  	
    	  $("#pwErrorModal").modal({backdrop: 'static', keyboard: false});
//     	  alert('비번 틀림여');
      }
    	  
   })
   })
   
    //비번입력 모달에서 x 눌렀을때
   $("#pwInput").click(function(){
  	 	window.location.href = './list';
  })
   
   //비번틀린 모달에서 x눌렀을때
   $("#errorCancal").click(function(){
  	 	window.location.href = './list';
  })
   
  //비번틀린 모달에서 확인 눌렀을때
    $("#errorOk").click(function(){
   	 	window.location.href = './list';
   })
   
   $("#btnCancel").click(function(){
      history.go(-1);
   })
   
   
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

</style>
<div class="container">

<form action="/inquiry/list" method="get">
<div style="margin: 1%; float: right;">
<select name="searchType">
   <option value="iboardtitle">제목</option>
   <option value="counselorId">작성자</option>
   <option value="iBoardInquiryType">문의유형</option>
</select>
<input type="text" id="search" name="search">
<button>검색</button>
</div>
</form>

<form action="/inquiry/listdelete" method="post">
<table class="table table-hover" style="text-align: center;">

   <tr id="tablestyle">
<!--    	  <th style="width: 5%"><input type="checkbox" class="chk" id="checkAll" name="checkAll"> -->
      <th style="width: 10%">번호</th>
      <th style="width: 10%">문의유형</th>
      <th style="width: 35%">제목</th>
      <th style="width: 20%">작성자</th>
      <th style="width: 15%">작성일</th>      
      <th style="width: 10%">조회수</th>      
   </tr>
         <c:forEach var="iList" items="${list}">
            <tr>	
<!--       			<td> -->
<!--       			<label><input type="checkbox" class="chk" id="checkRow" -->
<%--                   name="checkRow" value="${iList.iBoardNo }"></label></td> --%>
                 <td>${iList.iBoardNo }</td>
               <td>${iList.iBoardInquiryType }</td>
               <td>
        <c:choose>
		<c:when test="${iList.iBoardSecret eq 0}">
			<a><img src="/image/locked.png" width="15" height="15"></a>
		</c:when>
		<c:when test="${iList.iBoardSecret eq 1}">
		</c:when>
		</c:choose>
		<c:choose>
		<c:when test="${iList.iBoardSecret eq 1 }">
			<a href="./show?iboardno=${iList.iBoardNo }" style="text-decoration: none;">${iList.iBoardTitle}</a>
		</c:when>
		<c:when test="${iList.iBoardSecret eq 0 }">
			<a class="title" data-backdrop="static" data-keyboard="false" data-toggle="modal" href="#myModal" data-iBoardPw = "${iList.iBoardSecretPw }" data-iBoardNo = "${iList.iBoardNo }" style="text-decoration: none;">${iList.iBoardTitle}</a>
		</c:when>
		</c:choose>	   
       	<c:choose>
		<c:when test="${iList.iBoardAnswer eq '[답변 완료]'}">
			&nbsp;&nbsp;<a style="color:skyblue">${iList.iBoardAnswer}</a>
		</c:when>
		<c:when test="${iList.iBoardAnswer eq '[답변진행중]'}">
			&nbsp;&nbsp;<a>${iList.iBoardAnswer}</a>
		</c:when>
		</c:choose> 
               </td>
               <td>${ iList.counselorId}</td>
               <td>${ iList.iBoardDate}</td>
               <td>${ iList.iBoardViews}</td>
            </tr>

         </c:forEach>

</table>

<!-- <button class="btn btn-md btn-danger b-btn" style="float: left;">체크삭제</button> -->

</form>

<%-- <% if(session.getAttribute("userid") != null) { %> --%>

<a href="/inquiry/write"><button class="btn btn-md b-btn" style="float: right; background-color: #4ea1d3; color: white;">작성</button></a>
<%-- <% } else { %> --%>

<%-- <% } %> --%>
<jsp:include page="/WEB-INF/views/layout/inquiry_paging.jsp" />  
<!-- 비밀번호 입력 모달 -->
<div class="modal fade" id="myModal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">확인 메시지</h4>
        <button id="pwInput"type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
     
      <div class="modal-body content">
        	비밀번호를 입력해주세요.
        	<input type="password" id="pass" value="">
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="submit" id="btnWrite1" class="btn btn-danger" data-dismiss="modal">확인</button>
      </div>

    </div>
  </div>
</div>

<!-- 경고 모달창 -->
<div class="modal fade" id="pwErrorModal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">경고 메시지</h4>
        <button id="errorCancal" type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
     
      <div class="modal-body content">
        	비밀번호가 틀렸습니다.
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="submit" id="errorOk"class="btn btn-danger" data-dismiss="modal">확인</button>
      </div>

    </div>
  </div>
</div>

</div> <!-- container -->
<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>

    