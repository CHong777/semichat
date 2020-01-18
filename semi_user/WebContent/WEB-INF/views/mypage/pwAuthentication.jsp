<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js" charset="utf-8"></script>
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>
<script type="text/javascript">
$(document).ready(function(){
	
	//버튼 클릭시 Ajax 요청
	   $("#btnsubmit").click(function(){

		  //빈칸일 경우
		  if($('#pass').val()==""){
			  $("#inputPwAuthenticationModal").modal({backdrop: 'static', keyboard: false});
		  }
		  
		  //제대로 입력했을 경우
		  else{
			  $.ajax({
	        	  type: 'post',
	           	url: '/pw/authentication', 
	           	data: {pass : $('#pass').val()
	           	},
	           	dataType: "json",
	           	success: function(data) {
	//	         	console.log(data.result + "입니다람쥐"); 
	        	   
	        		  // 비번일치시, 페이지 이동
	              	if(data.result == 1) {
	              		
	              		$(location).attr('href', '/mypage/boardlist');
	        	 	    return false;
	            	  }
	            	  // 0 : 비번 불일치시 모달 호출
	            	  else {
	            		  $("#pwAuthenticationModal").modal({backdrop: 'static', keyboard: false});
	             	 }
	           	}
	       	 });
	   }
	 	   
// 	      $("form").submit();
	   })
	 
	    //비번틀린 모달에서 x눌렀을때 새로고침
  		 $("#pwX").click(function(){
  			$(location).attr('href', '/pw/authentication');
  			return false;
  		})
   
  		//비번틀린 모달에서 확인 눌렀을때 새로고침
    	$("#pwCheckBtn").click(function(){
    		$(location).attr('href', '/pw/authentication');
    		return false;
   		})
   		
   		 //비번입력 안한 모달에서 x눌렀을때 새로고침
  		 $("#inputPwX").click(function(){
  			$(location).attr('href', '/pw/authentication');
  			return false;
  		})
   
  		//비번입력 안한 모달에서 확인 눌렀을때 새로고침
    	$("#inputPwCheckBtn").click(function(){
    		$(location).attr('href', '/pw/authentication');
    		return false;
   		})
	   
	   
	   $("#btnCancel").click(function(){
	      history.go(-1);
	   })
	   
});	   
</script>
<style type="text/css">
form {
	text-align: center;
	position: relative;
	top: 200px;
}

h1 {
	text-align: center;
	position: relative;
	top: 150px;
}

input {
	width: 300px;
	height: 40px;
}

#btnsubmit {
	background-color: #4ea1d3;
	color: white;
	position: relative;
	left: 700px;
	top: 146px;
}

#placediv {
	position: relative;
    left: 345px;
    top: 185px;
}

#pwconfirm {
	position: relative;
    left: 405px;
    top: 160px;

}
</style>


<div class="container">

<!-- 경고 모달창 -->
<div class="modal fade" id="pwAuthenticationModal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">경고 메시지</h4>
        <button id="pwX" type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
     
      <div class="modal-body content">
        	비밀번호가 일치하지 않습니다.
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="submit" id="pwCheckBtn"class="btn btn-danger" data-dismiss="modal">확인</button>
      </div>

    </div>
  </div>
</div>


<!-- 비번 입력하라는 모달 -->
<div class="modal fade" id="inputPwAuthenticationModal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">경고 메시지</h4>
        <button id="inputPwX" type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
     
      <div class="modal-body content">
        	비밀번호를 입력해 주세요
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="submit" id="inputPwCheckBtn"class="btn btn-danger" data-dismiss="modal">확인</button>
      </div>

    </div>
  </div>
</div>


<div id="title"><h1>마이페이지</h1></div>
<!-- <form action="/pw/authentication" method="post"> -->
	<h3 id="pwconfirm">비밀번호를 입력해주세요.</h3>
  <br><br>
  <div id="placediv">
  	<a><img src="/image/key.png" width="30" height="30"></a>
	<input type="password" id="pass" name="pass"/>
  </div>
<!-- </form> -->
<button class="btn btn-md b-btn" id="btnsubmit" type="submit">확인</button>
</div>


<jsp:include page="/WEB-INF/views/layout/footer.jsp" />   
    