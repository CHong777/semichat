<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:import url="/WEB-INF/views/layout/header_join.jsp" />


<script type="text/javascript">
$(document).ready(function() {
	
	//경고 모달 호출 메서드
	function warningModal(content) {
		$(".modal-contents").text(content);
		$("#defaultModal").modal('show');
	}
	
	//확인 누르면 이메일 인증 검사
	$("#ok").click(function() {
		//해시 코드를 이용해 이메일 인증여부 처리,
		  var code = '${pasingCounselor.counselorEmailHash}';
		  
		  $.ajax({
	           type: 'get',
	           url: '/login/emailChecked', 
	           data: {counselorid: '${counselorid}'},
	           dataType: "json",
	           success: function(data) {
// 	         	  console.log(data.result); 
	        	   
	        	  //메일을 인증 했다면 (1이라면) 메인으로 이동
	              if(data.result == 1) {
	            	  location.href = "/main.jsp";
	       	        return false;
	              
	              }
	        	  //인증 안했다면 모달 띄우기
	              else{
	            	  warningModal("이메일 인증을 해주시기 바랍니다.");
	            	  return false;
	          	  }
	           }
	        });
		});
	});
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

<!-- 본문 들어가는 부분 -->
<div id="email-div">
<div class="row text-center" style="width: 100%">
	<div style="width: 50%; float: none; margin: 0 auto">
		<div  class ="finish">
			<h3>로그인 완료</h3>
		</div>
		<div class = "content1">
			<h1>입력하신 이메일로</h1>
			<h1>인증 메일이 발송 되었습니다</h1>
		</div>
		<div class = "content2">
			<h3>이메일 인증을 완료해 주세요</h3>
		</div>
		<div class = "confirmbutton">
			<button type="button" id="ok" class="btn btn-light">확인</button>
		</div>
	</div>
</div>
</div>
<c:import url="/WEB-INF/views/layout/footer.jsp" />