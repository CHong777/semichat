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



$(document).ready(function(){

	//승인 버튼 클릭
	 $(".accountcheck").click(function(){
		var counselorNo = "";
		counselorNo = $(this).attr("data-counselorNo");
		
		//확인 버튼 클릭 시 승인 컨트롤러로 이동
		  $("#approve").click(function() {
			  $(location).attr("href", "/manager/approve?counselorNo="+counselorNo);
		   })
   })
   
   //탈퇴 버튼 클릭
    $(".withdrawalCheck").click(function(){
		var counselorNo = "";
		counselorNo = $(this).attr("data-counselorNo");
		
		//확인 버튼 클릭 시 탈퇴 컨트롤러로 이동
		  $("#withdrawal").click(function() {
			  $(location).attr("href", "/manager/withdrawal?counselorNo="+counselorNo);
		   })
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

#accountManage {
   background-color: #4ea1d3;
   border-color: #4ea1d3;
   color: white;
}

</style>
<div class="container">

<form action="/manager/accoountmanage" method="get">
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
      <th style="width: 25%">직책</th>
      <th style="width: 20%">가입일</th>
      <th style="width: 15%">상태</th>
      <th style="width: 10%">승인</th>
      <th style="width: 10%">탈퇴</th>
      
   </tr>
         <c:forEach var="counselorList" items="${list}">
            <tr>	
               <td>${counselorList.rnum }</td>
               <td>${counselorList.counselorName }</td>
               <td>${ counselorList.counselorPosition}</td>
               <td>${counselorList.counselorSigndate}</td>
				
				<!-- 상태 표시 -->
				
				<!-- 기업매니저 승인이 0 이면 승인신청 표시 -->
				<c:if test="${counselorList.managerConfirm eq 0}">
					<td>승인 신청</td>
				</c:if>
				
				<!-- 기업매니저 승인이 1이고,  탈퇴신청이 0 이면 승인 완료 표시-->
				<c:if test="${counselorList.managerConfirm eq 1 && counselorList.counselorWithdrawalchecked eq 0}">
					<td>승인 완료</td>
				</c:if>
				
				<!-- 기업매니저 승인이 1이고,  탈퇴신청이 1 이면 탈퇴 표시-->
				<c:if test="${counselorList.managerConfirm eq 1 && counselorList.counselorWithdrawalchecked eq 1}">
					<td>탈퇴 신청</td>
				</c:if>
				<!-- 상태 표시 -->

				<!-- 승인버튼 표시, 매니저 승인이 0일 경우만 표시  --> 
				<c:choose>
					<c:when test="${counselorList.managerConfirm eq 0}">
						<td><button class = "accountcheck" data-toggle="modal" data-counselorNo="${counselorList.counselorNo}" data-target="#accountModal">승인</button></td>
					</c:when>
					<c:otherwise>
						<td></td>
					</c:otherwise>
				</c:choose>
				<!-- 승인버튼 표시, 매니저 승인이 0일 경우만 표시  --> 
				<!-- 매니저 승인이 1일때만 탈퇴버튼 표시 -->
				<c:choose>
					<c:when test="${counselorList.managerConfirm eq 1}">
						<td><button class = "withdrawalCheck" data-toggle="modal" data-counselorNo="${counselorList.counselorNo}" data-target="#withdrawalModal">탈퇴</button>
					</c:when>
					<c:otherwise>
						<td></td>
					</c:otherwise>
				</c:choose>
            </tr>
         </c:forEach>
</table>
<jsp:include page="/WEB-INF/views/layout/manager_account_paging.jsp" />  
	<div class="modal fade" id="accountModal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">승인 처리</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <!-- Modal body -->
      <div class="modal-body content">
        	승인 하시겠습니까?
      </div>
      <!-- Modal footer -->
      <div class="modal-footer">
        <button id = "approve" class="btn btn-success" data-dismiss="modal">확인</button>
      </div>

    </div>
  </div>
</div>

<div class="modal fade" id="withdrawalModal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">탈퇴 처리</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <!-- Modal body -->
      <div class="modal-body content">
        	탈퇴 처리 하시겠습니까?
      </div>
      <!-- Modal footer -->
      <div class="modal-footer">
        <button id = "withdrawal" class="btn btn-danger" data-dismiss="modal">확인</button>
      </div>

    </div>
  </div>
</div>

</div> <!-- container -->
<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>

    