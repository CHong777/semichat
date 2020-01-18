<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js" charset="utf-8"></script>
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>
<jsp:include page="/WEB-INF/views/layout/header_myPage.jsp"/>


<script type="text/javascript">
$(document).ready(function() {

	var idNickCheck = 0;
	
	var nicknamecheck = false;
	
  //닉네임 중복 검사(1 = 중복 / 0 = 중복 아님)
    function nicknameCheckFunction(){
	 
        var nickname = $("#nickname").val();
        console.log(nickname);
        $.ajax({
           type: 'get',
           url: '/modify/nickcheck', 
           data: {nickname: nickname},
           dataType: "json",
           success: function(data) {
//         	  console.log(data.result); 
        	   
              if(data.result == 1) {
            	  //1 : 닉네임이 중복
       			$("#nickname_check").text("사용중인 닉네임 입니다.");
       			$("#nickname_check").css("color", "red");
				$("#btnsubmit").attr("disabled", true);
       			 $('#nickname').focus();
    			 $("#btnJoin").attr("disabled", true);
       	        return false;
              
              }
              // 0 : 닉네임 중복 없음
              else {
            	  var divNickname = $('#divNickname');
            	  //빈칸 입력 했을경우
                  if($('#nickname').val()==""){
                       $("#nickname_check").text("닉네임을 입력해 주세요");
                       $("#nickname_check").css("color", "red");
                       $('#nickname').focus();
               			idNickCheck = 0; 
                  }
            	  // 제대로 입력했을 경우
                  else{
                    $("#nickname_check").text("사용할 수 있는 닉네임 입니다.");
                    $("#nickname_check").css("color", "green");
                    $('#email').focus();
              	  	idNickCheck = 1; 
                 }
                  
            	  //id , nick 둘다 중복 안되면 abled
         		  if(idNickCheck == 1){
            		  $("#btnsubmit").attr("disabled", false)
         		  }
            	  //id, nick 중하나라도 중복 되면 disabled
         		  else{
         			 $("#btnJoin").attr("disabled", true)
         		  }
        	    return false;
              }
           }
        });
     }
	
	//페이지 첫 접속 시 입력창으로 포커스 이동(안됨)
	$("input").eq(0).focus();
	

	//수정 버튼 클릭 시 form submit
	$("#btnsubmit").click(function() {
		$(this).parents("form").submit();
	})
	
	//닉네임 중복 검사
	$("#nicknameCheck").click(function() {
		nicknamecheck = true;
		nicknameCheckFunction();
	})
	
	
	 $(function(){

       //이름 - 한글만 입력할 수 있도록, 한글이 아니라면 빈칸으로 대체됨(지워짐)
         $(".onlyHangul").keyup(function(event){
             if (!(event.keyCode >=37 && event.keyCode<=40)) {
                 var inputVal = $(this).val();
                 $(this).val(inputVal.replace(/[a-z0-9]/gi,''));
             }
         });
      
         //휴대폰 번호 - 숫자만 입력할 수 있도록, 숫자가 아니라면 빈칸으로 대체됨(지워짐)
         $(".onlyNumber").keyup(function(event){
             if (!(event.keyCode >=37 && event.keyCode<=40)) {
                 var inputVal = $(this).val();
                 $(this).val(inputVal.replace(/[^0-9]/gi,''));
             }
         });
        
          
     });
	
	
	//수정 버튼 클릭
	   $("#btnsubmit").click(function(){
		   
		    // 비밀번호 정규식 검사
	        // 숫자, 대/소문자, 특수문자 중 2가지 이상을 조합한 8~15자리
	     var password= $("#password").val();  // pw 입력
	    	var check1 = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,15}$/.test(password);   //영문,숫자
	    	var check2 = /^(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{8,15}$/.test(password);  //영문,특수문자
	    	var check3 = /^(?=.*[^a-zA-Z0-9])(?=.*[0-9]).{8,15}$/.test(password);  //특수문자, 숫자
		   
		   
		   //이름
        if($('#name').val()==""){
       	 $(".content").text('이름을 입력해 주세요');
       	 $("#changeModal").modal('show');
       	 
            $('#name').focus();
            return false;
        }
         
        //닉네임
        else if($('#nickname').val()==""){
     	   
             
         	  $(".content").text('닉네임을 입력해 주세요');
               $("#changeModal").modal('show');
             	 
                  $('#nickname').focus();
                  return false;
         	   
           
        }
		   
     // 닉네임 중복 버튼 한번 이상 눌렀을 경우
     else if(!nicknamecheck){
     	   $(".content").text('닉네임을 중복체크를 해주세요');
            $("#changeModal").modal('show');
            return false;
        }
		   
        //휴대폰 번호
       else if($('#phoneNumber').val()==""){
             
       	 $(".content").text('휴대폰 번호를 입력해 주세요');
       	 $("#changeModal").modal('show');
            $('#phoneNumber').focus();
            return false;
        }
        
        //비밀번호 검사
      else  if($('#password').val()==""){
       	 
       	 $(".content").text('비밀번호를 입력해 주세요');
       	 $("#changeModal").modal('show');
             
            $('#password').focus();
            return false;
        }
         
        //비밀번호 확인
      else  if($('#passwordCheck').val()==""){
             
       	 $(".content").text('비밀번호 확인을 입력해 주세요');
       	 $("#changeModal").modal('show');
       	 
            $('#passwordCheck').focus();
            return false;
        }
         
        //비밀번호 비교
      else  if($('#password').val()!=$('#passwordCheck').val() || $('#passwordCheck').val()==""){
             
       	 $(".content").text('비밀번호가 일치하지 않습니다.');
       	 $("#changeModal").modal('show');
       	 
            $('#passwordCheck').focus();
            return false;
        }
	
	  //비밀번호 정규식 검사
      else if(!(check1||check2||check3)){

    		 $(".content").text('숫자, 대/소문자, 특수문자 중 2가지 이상을 조합한 8~15로 입력해주세요');
           	 $("#changeModal").modal('show');
           return false;
    	}

	  //모든조건 만족했을때(모두 입력 했을때)
      else{
    	  $.ajax({
        	  type: 'post',
           	url: '/mypage/modifyinfo', 
           	data: {name: $('#name').val(),
        		   nickName : $('#nickname').val(),
        		   phoneNumber : $('#phoneNumber').val(),
        		   password : $('#password').val()
           	},
           	dataType: "json",
           	success: function(data) {
//	         	console.log(data.result + "입니다람쥐"); 
        	   
        		  // 성공시, 수정 완료 모달 띄우기
              	if(data.result == 1) {
              		$(".content").text('수정이 완료되었습니다.');
              		$("#myModalSubmit").modal({backdrop: 'static', keyboard: false});
              		
        	 	    return false;
              
            	  }
            	  // 0 : 실패
            	  else {
	        		  return false;
             	 }
           	}
       	 });
      }
	    
	   	//확인 버튼 누르면 입력 내용 지워지도록 새로고침
        $("#complete").click(function(){
     	   $(location).attr('href', '/mypage/modifyinfo');
        }) 	
        
        //X버튼 눌러도 입력 내용 지워지도록 새로고침
        $("#completeX").click(function(){
     	   $(location).attr('href', '/mypage/modifyinfo');
        }) 	
       
	   })
	   $("#btnCancel").click(function(){
	      history.go(-1);
	   })
	
});


</script>
<style>


/* 회원가입 메시지(로고) */
#modifylogo {
	text-align: center;
}

/* 빨간색으로 나와야할 글씨 스타일  지정 */
#redText{
	color:red;
}

.modal-backdrop {
   z-index: 1;
}

#content {
    position: relative;
    left: 29%;
}

#nicknameCheck {
	position: relative;
    left: 180px;
    top: 37px;
}

#btnsubmit {
	position: relative;
    right: 327px;
}

#btnmypageInfo {
   background-color: #4ea1d3;
   border-color: #4ea1d3;
   color: white;
}
</style>

<div class="container">

<h4 id = modifylogo>정보 수정</h4>

<!-- 경고 모달창 -->
           
<div class="modal fade" id="changeModal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">경고 메시지</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body content">
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <a href="/mypage/modifyinfo"><input type="submit" id="btnWrite1" class="btn btn-danger" data-dismiss="modal" value="확인"></a>
      </div>

    </div>
  </div>
</div>
            <!--// 경고 모달창 -->
            <hr/>
            
            
<!-- 확인 모달창 -->      
<div class="modal fade" id="myModalSubmit">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">확인 메시지</h4>
        <button type="button" id="completeX" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body content">
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button id = "complete"type="submit" class="btn btn-success" data-dismiss="modal">확인</button>
      </div>

    </div>
  </div>
</div>            
                
       <!-- 본문 들어가는 부분 -->
<div id="content">
<!-- <form class="form-horizontal" role="form" method="post" -->
<!-- 	action="/mypage/modifyinfo"> -->
	<div class="form-group" id="divName">
		<label for="name" class="col-sm-3 col-sm-offset-1 control-label">이름</label>
		<div class="col-sm-5">
			<input type="text" class="form-control onlyHangul" id="name" name = "name"
				data-rule-required="true" placeholder="한글만 입력 가능합니다." maxlength="15">
		</div>
	</div>

	<div class="form-group" id="divNickname">
		<label for="nickname" class="col-sm-3 col-sm-offset-1 control-label">닉네임</label>
		<button type="button" id="nicknameCheck" class="btn btn-light">중복 확인</button>
		<div class="col-sm-5">
			<input type="text" class="form-control" id="nickname" name = "nickname"
				data-rule-required="true" placeholder="닉네임" maxlength="15">
				<div class="check_font" id="nickname_check"></div>
		</div>
	</div>
	<div class="form-group" id="divPhoneNumber">
		<label for="phoneNumber" class="col-sm-3 col-sm-offset-1 control-label">휴대폰
			번호</label>
		<div class="col-sm-5">
			<input type="tel" class="form-control onlyNumber" id="phoneNumber" name = "phoneNumber"
				data-rule-required="true" placeholder="-를 제외하고 숫자만 입력하세요."
				maxlength="11">
		</div>
	</div>
	
	<input type="hidden" name="emailChecked" value="0" />
	<div class="form-group" id="divPassword">
		<label for="password" class="col-sm-3 col-sm-offset-1 control-label">비밀번호</label>
		<div class="col-sm-5">
			<input type="password" class="form-control" id="password"
				name="password" data-rule-required="true" placeholder="비밀번호"
				maxlength="30">
		<small id = redText>숫자, 대/소문자, 특수문자 중 2가지 이상을 조합한 8~15자리</small>
		</div>
	</div>
	<div class="form-group" id="divPasswordCheck">
		<label for="passwordCheck" class="col-sm-3 col-sm-offset-1 control-label">비밀번호
			확인</label>
		<div class="col-sm-5">
			<input type="password" class="form-control" id="passwordCheck" name = "passwordCheck"
				data-rule-required="true" placeholder="비밀번호 확인" maxlength="30">
		</div>
	</div>
	<div class="form-group" style="text-align:center;">
		<div class="col-sm-offset-4">
		<button type="button" id="btnsubmit" class="btn btn-primary" style="background-color: #4ea1d3; color: white; border-color: #4ea1d3;">정보 수정</button>
		</div>
	</div>

<!-- </form> -->
</div>	

<!-- <button class="btn btn-md b-btn" id="btnsubmit" type="submit">수정</button> -->
</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" />   
    