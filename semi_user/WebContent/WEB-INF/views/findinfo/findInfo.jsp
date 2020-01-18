<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/WEB-INF/views/layout/header_pop.jsp"/>

<script type="text/javascript">
$(document).ready(function() {
	
	//경고 모달 호출 메서드
	function warningModal(content) {
		$(".modal-contents").text(content);
		$("#defaultModal").modal('show');
	}
	
	//성공 모달 호출 메서드
	function successModal(content) {
		$(".modal-contents").text(content);
		$("#successModal").modal('show');
	}
	
	//아이디 찾기 버튼 클릭
	$("#idFind").click(function() {
		
         var divEmail = $('#divEmail');
         var divPhone = $('#divPhone');

         //이메일 검사
         if($('#counselorEmail').val()==""){
             warningModal("이메일을 입력하여 주시기 바랍니다.");
              
             divEmail.removeClass("has-success");
             divEmail.addClass("has-error");
             $('#counselorEmail').focus();
             return false;
         }
         //휴대폰 번호 검사
         else if($('#counselorPhoneNumber').val()==""){
             warningModal("휴대폰 번호를 입력하여 주시기 바랍니다.");
              
             divPhone.removeClass("has-success");
             divPhone.addClass("has-error");
             $('#counselorPhoneNumber').focus();
             return false;
         }
		// 둘다 입력한 경우
         else{
			var counselorEmail = $("#counselorEmail").val();
			var counselorPhoneNumber = $("#counselorPhoneNumber").val();
	      	  $.ajax({
	        	  type: 'get',
	           	url: '/findinfo/findid', 
	           	data: {counselorEmail: counselorEmail,
	        		   counselorPhoneNumber : counselorPhoneNumber
	           	},
	           	dataType: "json",
	           	success: function(data) {
// 	         	  console.log(data.result + "입니다람쥐"); 
	        	   
	        		  // 성공
	              	if(data.result == 1) {
	            		  successModal("아이디 찾기 성공! 메일을 확인해 주세요!");
	        	 	    return false;
	              
	            	  }
	            	  // 0 : 실패
	            	  else {
	            		  warningModal("등록된 아이디가 없습니다!");
		        		  return false;
	             	 }
	           	}
	       	 });
       	  }
		});
	
	//비밀번호 찾기 버튼 클릭
	$("#pwFind").click(function() {
		
         var divName = $('#divName');
         var divId = $('#divId');
          
         //이름 검사
         if($('#counselorName').val()==""){
             warningModal("이름을 입력하여 주시기 바랍니다.");
              
             divName.removeClass("has-success");
             divName.addClass("has-error");
             $('#counselorName').focus();
             return false;
         }
         
         //아이디 검사
         else if($('#counselorId').val()==""){
             warningModal("아이디를 입력하여 주시기 바랍니다.");
              
             divPhone.removeClass("has-success");
             divPhone.addClass("has-error");
             $('#counselorId').focus();
             return false;
         }
         
		// 둘다 입력한 경우
         else{
			var counselorName = $("#counselorName").val();
			var counselorId = $("#counselorId").val();
	      	  $.ajax({
	        	  type: 'get',
	           	url: '/findinfo/findpw', 
	           	data: {counselorName: counselorName,
	           		counselorId : counselorId
	           	},
	           	dataType: "json",
	           	success: function(data) {
	         	  console.log(data.result + "입니다람쥐"); 
	        	   
	        		  // 성공
	              	if(data.result == 1) {
	            		  successModal("임시 비밀번호가 전송 되었습니다! 메일을 확인해 주세요!");
	        	 	    return false;
	              
	            	  }

					// 실패
	            	  else {
	            		  warningModal("등록된 비밀번호가 없습니다!");
		        		  return false;
	             	 }
	           	}
	       	 });
       	  }
		});
		
	//취소 버튼 클릭시 팝업창 꺼지게
	$("#findCancal").click(function(){
		
		window.close();
	})
	
	
	});
</script>

<!-- 경고 모달창 -->
<div class="modal fade" id="successModal">
	<div class="modal-dialog">
		<div class="modal-content panel-success">
			<div class="modal-header panel-heading">
				<h4 class="modal-title">아이디 찾기 성공!</h4>
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

<!-- 성공 모달창 -->
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

<!--// 성공 모달창 -->


<!-- Page Content -->
	<div class="container" style="margin-bottom : 10%;">
		<!-- 아이디 찾기 -->
		<div  style="margin : 20%;">
			<h3>아이디 찾기</h3>
				<div class="form-group" id = "divEmail">
					<label for="counselorEmail">이메일</label> <input type="email"
						class="form-control" id="counselorEmail" name = "counselorEmail"
						placeholder="이메일을 입력하세요">
				</div>
				<div class="form-group" id = "divPhone">
					<label for="counselorPhoneNumber">전화번호</label> <input type="text"
						class="form-control" id="counselorPhoneNumber" name = "counselorPhoneNumber"
						placeholder="010-1234-5678">
				</div>
				<button type="submit" id = "idFind" class="btn btn-default" style="background-color:#ccc; float:right;">찾기</button>
		</div>
		<!-- 비번 찾기 -->
		<div style="margin : 20%;">
			<h3>비밀번호 찾기</h3>
				<div class="form-group" id = "divName">
					<label for="counselorName">이름</label> <input type="text"
						class="form-control" id="counselorName" name = "counselorName"
						placeholder="성함을 입력하세요">
				</div>
				<div class="form-group" id = "divId">
					<label for="counselorId">아이디</label> <input
						type="text" class="form-control" id="counselorId" name = "counselorId"
						placeholder="아이디">
				</div>
				<button type="submit" id = "pwFind" class="btn btn-default" style="background-color:#ccc; float:right;">찾기</button>
		</div>
		
		<!-- 취소 버튼 -->
		<div style="text-align:center;">
			<button id = "findCancal" class="btn btn-danger" style="background-color:#ccc;">취소</button>
		</div>
	</div>
	<!-- /.container -->
<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
