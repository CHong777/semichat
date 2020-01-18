<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>
<jsp:include page="/WEB-INF/views/layout/header_myPage.jsp"/>
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
<script type="text/javascript">
$(document).ready(function(){

   $("#checkAll").click(function() {
      if($("#checkAll").is(":checked")) {
         $(".chk").prop("checked", true);
      }
      else{
         $(".chk").prop("checked", false);
      }
   });
  
	   
   })
   
	
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
    	  $("#warningModal").modal()
    	 window.location.href = './list';
      }
    	  
   })
   })
   $("#btnCancel").click(function(){
      history.go(-1);
   })
   
  
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

#btnMyClient {
   background-color: #4ea1d3;
   border-color: #4ea1d3;
   color: white;
}

</style>
<div class="container">

<form action="/mypage/myclient" method="get">
<div style="margin: 1%; float: right;">
<select name="searchType">
   <option value="clientname">이름</option>
   <option value="clientnick">고객별칭</option>
</select>
<input type="text" id="search" name="search">
<button>검색</button>
</div>
</form>

<form action="/mypage/clientdelete" method="post">
<table class="table table-hover" style="text-align: center;">

   <tr id="tablestyle">
   	  <th style="width: 5%"><input type="checkbox" class="chk" id="checkAll" name="checkAll">
      <th style="width: 5%">번호</th>
      <th style="width: 10%">이름</th>
      <th style="width: 15%">고객별칭</th>
      <th style="width: 30%">휴대폰번호</th>
      <th style="width: 5%">블랙</th>      
      <th style="width: 20%">마지막 채팅 날짜</th>      
   </tr>
         <c:forEach var="iList" items="${list}">
            <tr>	
      			<td style="padding: 5px;">
      			<label><input type="checkbox" class="chk" id="checkRow"
                  name="checkRow" value="${iList.myClientInfoNo }"></label></td>
                <td>${iList.myClientInfoNo }</td>
                <td>${iList.clientName }</td>
                <td>${iList.clientNick }</td>
                <td>${ iList.clientPhoneNum}</td>
                <td>
                <c:choose>
                <c:when test="${iList.isBlock eq 0}">
                	<a><img src="/image/star_white.png" width="15" height="15"></a>
                </c:when>
                <c:when test="${iList.isBlock eq 1}">
                	<a><img src="/image/star_black.png" width="15" height="15"></a>                	
                </c:when>
                </c:choose>
 				</td>
                <td>${ iList.lastChatDate}</td>
            </tr>

         </c:forEach>

</table>

<button class="btn btn-md btn-danger b-btn" style="float: left;">체크삭제</button>

</form>

<jsp:include page="/WEB-INF/views/layout/myclient_paging.jsp" />  
<!-- 비밀번호 입력 모달 -->
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
<div class="modal fade" id="warningModal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">경고 메시지</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
     
      <div class="modal-body content">
        	비밀번호가 틀렸습니다.
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="submit" class="btn btn-danger" data-dismiss="modal">확인</button>
      </div>

    </div>
  </div>
</div>

</div> <!-- container -->



<jsp:include page="/WEB-INF/views/layout/footer.jsp" />   
