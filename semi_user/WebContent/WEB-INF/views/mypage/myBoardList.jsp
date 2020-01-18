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
function updateBookMark(book,no,type){
	
	$(location).attr('href', '/mypage/bookmark?book='+book+"&no="+no+"&type="+type);
	    	console.log(book);
	};
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

#btnMyWriting {
   background-color: #4ea1d3;
   border-color: #4ea1d3;
   color: white;
}

</style>
<div class="container">

<form action="/mypage/boardlist" method="get">
<div style="margin: 1%; float: right;">
<select name="searchType">
   <option value="nboardtitle">제목</option>
</select>
<input type="text" id="search" name="search">
<button>검색</button>
</div>
</form>

<form action="/mypage/boarddelete" method="post">
<table class="table table-hover" style="text-align: center;">

   <tr id="tablestyle">
      <th style="width: 10%">번호</th>
      <th style="width: 10%">게시글 유형</th>
      <th style="width: 35%">제목</th>
      <th style="width: 15%">작성자</th>
      <th style="width: 10%">작성일</th>
      <th style="width: 10%">조회수</th>      
      <th style="width: 10%">즐겨찾기</th>            
   </tr>
         <c:forEach var="iList" items="${list}">
            <tr>	
               <td><input type="hidden" name="iBoardNo" class="iBoardNo" value="${iList.iBoardNo }"/>${iList.iBoardNo }</td>
               <td><input type="hidden" name="boardSorting" value="${iList.boardSorting }"/>${iList.boardSorting }</td>
               <td>
		<c:choose>
		<c:when test="${iList.boardSorting eq '공지사항' }">
			<a href="./noticeshow?noticeboardno=${iList.iBoardNo }" style="text-decoration: none;">${iList.iBoardTitle}</a>
				<c:set var="type" value="공"/>
		</c:when>
		<c:when test="${iList.boardSorting eq '문의사항' }">
			<a href="./inquiryshow?iboardno=${iList.iBoardNo }" style="text-decoration: none;">${iList.iBoardTitle}</a>
			<c:set var="type" value="문"/>
<%-- 	   		<a href="../inquiry/show?iboardno=${iList.iBoardNo }" style="text-decoration: none;">${iList.iBoardTitle}</a> --%>
	   	</c:when>
	   </c:choose>
       	       <a>${iList.iBoardAnswer}</a>
               </td>
               <td>${ iList.counselorId}</td>
               <td>${ iList.iBoardDate}</td>
               <td>${ iList.iBoardViews}</td>
               <c:choose>
               <c:when test="${iList.nBoardBookMark  eq 'N'}">
               <td id="bookMark"><a onclick="updateBookMark('${ iList.nBoardBookMark}','${iList.iBoardNo }','${type }');"><img name="book" src="/image/star_white.png" width="25" height="25"></a></td>	               
               </c:when>
               <c:when test="${iList.nBoardBookMark  eq 'Y'}">
               <td id="bookMark"><a onclick="updateBookMark('${ iList.nBoardBookMark}','${iList.iBoardNo }','${type }');"><img name="bookstar" src="/image/star.png" width="25" height="25"></a></td>	               
				</c:when>
            	</c:choose>
            </tr>

         </c:forEach>

</table>

<!-- <button class="btn btn-md btn-danger b-btn" style="float: left;">체크삭제</button> -->

</form>

<jsp:include page="/WEB-INF/views/layout/my_paging.jsp" />  
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
