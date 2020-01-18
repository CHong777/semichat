<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/views/layout/header_join.jsp" />

<script type="text/javascript">

function popupOpen(){
	var url ="/findinfo/findinfo";
	var winWidth = 700;
	var winHeight = 600;
	var winLeft = Math.ceil(( window.screen.width - winWidth )/2);
	var winTop = Math.ceil(( window.screen.width - winHeight )/2);
	var popupOption = "width=" + winWidth+ ", height=" + winHeight +", left=" + winLeft + ", winTop=" + winTop;
	var myWindow = window.open(url, "", popupOption);

}


$(document).ready(function(){
	//페이지 첫 접속시 입력창으로 포커스 이동
	$("input").eq(0).focus();
	
	//패스워드 입력 창에서 엔터 입력 시 form submit
	$("input").eq(1).keydown(function(key) {
		if(key.keyCode == 13) {
			$(this).parents("form").submit();
		}
	})

	//경고 모달 호출 메서드
	function warningModal(content) {
		$(".modal-contents").text(content);
		$("#defaultModal").modal('show');
	}

	//확인 누르면 ID/PW 일치여부 검사
	$("#btnLogin").click(function() {
		//해시 코드를 이용해 이메일 인증여부 처리,
		  var counselorid = $('#counselorid').val();
		  var counselorpassword = $('#counselorpassword').val();
		  
		  $.ajax({
	           type: 'get',
	           url: '/login/loginCheck', 
	           data: {counselorid: counselorid,
	        	   counselorpassword: counselorpassword   
	           },
	           dataType: "json",
	           success: function(data) {
	         	  console.log(data.result); 
	        	   
	        	  //로그인 ID/PW가 일치하면 (true라면) 메인으로 이동
	              if(data.result == true) {
	            	  $("#loginForm").submit();
	       	        return false;
	              
	              }
	        	  //ID/PW 일치하지 않으면 모달 띄우기
	              else{
	            	  warningModal("아이디 패스워드를  확인해주세요!!");
	            	  return false;
	          	  }
	           }
	        });
		});

	
	//아이디/비밀번호 찾기 버튼 클릭 시
	$("#findidpw").click(function(){
		
		popupOpen();
	})
		
	//회원가입 버튼 클릭 시
		//아이디/비밀번호 찾기 버튼 클릭 시
	$("#joincounselor").click(function(){
		$(location).attr('href', '/join/join');
	})
	
	//경고 모달 호출 메서드
	function warningModal(content) {
		$(".modal-contents").text(content);
		$("#defaultModal").modal('show');
	}
	
	
		
})
</script>

<!-- 경고 모달창 -->
<div class="modal fade" id="defaultModal">
	<div class="modal-dialog">
		<div class="modal-content panel-danger">
			<div class="modal-header panel-heading">
				<h4 class="modal-title">오류 메시지</h4>
			</div>
			<div class="modal-body">
				<p class="modal-contents"></p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<!--// 경고 모달창 -->

<form action="/login/login" method="post" class="form-horizontal" id ="loginForm">
	<div class="form-group">
		<label for="counselorid" class="col-sm-3 col-sm-offset-1 control-label">아이디</label>
		<div class="col-sm-5">
		<input type="text" id="counselorid" name="counselorid" class="form-control" placeholder="Username"/>
		</div>
	</div>
	<div class="form-group">
		<label for="counselorpassword" class="col-sm-3 col-sm-offset-1 control-label">패스워드</label>
		<div class="col-sm-5">
		<input type="password" id="counselorpassword" name="counselorpassword" class="form-control" placeholder="Password"/>
		</div>
	</div>

	<div class="col-sm-offset-5">
		<button type="button" id="btnLogin" class="btn btn-primary"
			width="20%" style="position: relative; left: 161px;">로그인</button>
		<br>
		<br>


		<button type="button" id="findidpw" class="btn btn-primary"
			style="position: relative; left: 56px;">아이디/비밀번호 찾기</button>
		<button type="button" id="joincounselor" class="btn btn-primary"
			style="position: relative; left: 66px;">회원가입</button>
	</div>
</form>


<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>