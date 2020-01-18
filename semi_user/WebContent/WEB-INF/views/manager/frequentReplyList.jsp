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

<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js" charset="utf-8"></script>
<script type="text/javascript">

//추가 버튼 클릭시
function registerFrequentlyReply(){
	
	//textarea, 등록버튼 활성화
	$('#textarea').attr('disabled', false);
	$("#ok").attr("disabled", false);
	
}

$(document).ready(function(){
	//답변 등록
	 $(".ok").click(function(){
		var frequentReplyContent = "";
		frequentReplyContent = $("#textarea").val();
		
		//확인 버튼 클릭 시 답변 등록 컨트롤러로 이동
		  $("#registerok").click(function() {
			  $(location).attr("href", "/manager/replyRegister?frequentReplyContent="+frequentReplyContent);
		   })
  	})
  	
  	 //답변 삭제
    $(".deleteFreqReply").click(function(){
		var frequentReplyNo = "";
		frequentReplyNo = $(this).attr("data-frequentReplyNo");
		
		//확인 버튼 클릭 시 답변 삭제 컨트롤러로 이동
		  $("#deleteReply").click(function() {
			  $(location).attr("href", "/manager/replyDelete?frequentReplyNo="+frequentReplyNo);
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
.jumbotron{
	position: relative;
    left: 150px;
    height: 448px; 
    overflow:auto;
}

#replyText{
	padding: 50px;
}

#minus{
	position: relative;
    left: 235px;
    bottom: 35px;
}

#registerdelete{
	position: relative;
    left: 255px;
    width: 200px;
}

#frequentlyReplyTitle{
	width: 170px;
    position: relative;
    left: 684px;
    bottom: 57px;
}

#textarea{
	float: right;
    position: relative;
    right: 120px;
    height: 450px;
    width: 300px;
    resize: none;
}

#ok{
	float: right;
    position: relative;
    right: 300px;
    top: 463px;
}

#register{
	position: relative;
    left: 280px;
    bottom: 20px;
}

</style>
<div class="container">
<div id ="replyText">
	<h1 style="text-align:center;">자주쓰는 답변</h1>
</div>

<div>
<h1 id ="registerdelete">등록/삭제</h1>
</div>
<div>
<h1 id ="frequentlyReplyTitle">답변 등록</h1>
<button id = "ok" class="btn btn-lg b-btn ok" style="background-color: #4ea1d3; color: white;" 
data-toggle="modal" data-target="#registerModal" disabled>완료</button>
</div>
<textarea id = "textarea" disabled></textarea>
<div class="col-lg-4"></div>
        <div class="col-lg-4">
                    <div class ="jumbotron" style="padding-top:20px;">
                
	                    <div class ="form-group">
	                      	<c:forEach var="frequentReplyList" items="${list}">
	              				<h3>${frequentReplyList.frequentReplyContent }</h3>
	              				<button class = "btn btn-danger deleteFreqReply" data-toggle="modal"  id ="minus"
	              				data-frequentReplyNo="${ frequentReplyList.frequentReplyNo}" data-target="#DeleteReplyModal">-</button>
	               				<br>
	         				</c:forEach>
	                    </div>
	                    <div class ="form-group">
                    </div>
            </div> 
        </div> 
       <div class="col-lg-4"></div>
       <div>
       <button id = "register" onclick = "registerFrequentlyReply();" class="btn btn-lg b-btn" style="background-color: #4ea1d3; color: white;">추가</button>
       </div>

<div class="modal fade" id="registerModal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">자주쓰는 답변 등록</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <!-- Modal body -->
      <div class="modal-body content">
        	등록 하시겠습니까?
      </div>
      <!-- Modal footer -->
      <div class="modal-footer">
        <button id = "registerok" class="btn btn-success" data-dismiss="modal">확인</button>
      </div>

    </div>
  </div>
</div>



<div class="modal fade" id="DeleteReplyModal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">자주쓰는 답변 삭제</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>
      <!-- Modal body -->
      <div class="modal-body content">
        	삭제 하시겠습니까?
      </div>
      <!-- Modal footer -->
      <div class="modal-footer">
        <button id = "deleteReply" class="btn btn-danger" data-dismiss="modal">확인</button>
      </div>

    </div>
  </div>
</div>


</div> <!-- container -->
<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>