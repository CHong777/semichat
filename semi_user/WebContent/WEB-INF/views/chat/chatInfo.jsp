<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 데이트피커 -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script>
/* 2019-11-29 유진 : 고객정보 상담일자 입력 데이트피커 */
$.datepicker.setDefaults({
	dateFormat: 'yy-mm-dd',
	prevText: '이전 달',
	nextText: '다음 달',
	monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	dayNames: ['일', '월', '화', '수', '목', '금', '토'],
	dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
	dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
	showMonthAfterYear: true,
	yearSuffix: '년'
});

$(document).ready(function() {
	/* 2019-11-29 유진 : 고객정보 상담일자 입력 데이트피커 */
	$("#lastChatDate").datepicker();
	
	/* 2019-11-29 유진 : 고객정보 작성값 초기화 */
	$("#reset_info").click(function(){
		$("#clientName").attr("value", "");
		$("#clientPhoneNum").attr("value", "");
		$("#lastChatDate").attr("value", "");
		$("#counselorName").attr("value", "");
		$("#chatMemo").html("");
	});
	/* 2019-11-29 유진 : 고객관리 닉네임 작성값 초기화 */
	$("#reset_nick").click(function(){
		$("#clientNick").attr("value", "");
	});

	/* 2019-12-01 유진 : 고정매칭 버튼 click ajax */
	$("#fixed").click(function(){
		var clientInfoNo = $("#myClientInfoNo").val();
// 		console.log(clientInfoNo);
		$.ajax({
			type:"post",
			url:"/chat/fixedMatch",
			data:{clientInfoNo:clientInfoNo},
			dataType:"json",
			success: function(data){
// 				console.log("click fixed "+data.result.myclientinfo[0]);
				// 상태에 따라 버튼 변화
				if(data.result.myclientinfo[0]==0){
					$("#fixed").css("background-color","white");
					$("#fixed").attr("value","고정매칭");
				} else {
					$("#fixed").css("background-color","#4ea1d3");
					$("#fixed").attr("value","매칭 중");
				}
				
			},
			error : console.log("에러")
			});
		});

	/* 2019-12-01 유진 : 차단 버튼 click ajax */
	$("#block").click(function(){
		var clientInfoNo = $("#myClientInfoNo").val();
// 		console.log(clientInfoNo);
		$.ajax({
			type:"post",
			url:"/chat/block",
			data:{clientInfoNo:clientInfoNo},
			dataType:"json",
			success: function(data){
// 				console.log("click block "+data.result.myclientinfo[0]);
				// 상태에 따라 버튼 변화
				if(data.result.myclientinfo[0]==0){
					$("#block").css("background-color","white");
					$("#block").attr("value","차단");
				} else {
					$("#block").css("background-color","red");
					$("#block").attr("value","차단 중");
				}
				
			},
			error : console.log("에러")
			});
		});
	
	/* 2019-11-30 유진 : 자주 쓰는 답변 추가하면 div에 나타내기*/
	$('#set').click(function(){
		var target = document.getElementById("add");
		var text=target.options[target.selectedIndex].text;
		
		// 드롭 박스에 서 선택 시 frequentReply div에 추가
		$('#frequentReply').append("<div class='use'>"
				+"<input name='reply' class='btn-sm reply' type='button' value='"+text+"'style='margin-right:5px;'/>"
				+"<input name='delete' class='btn-sm delete' type='button' value='X'/>"
				+"</div>");
	});
	/* 2019-11-30 유진 : 자주 쓰는 답변 div 삭제 */
	$('#frequentReply').on("click","input.delete", function(){
		$(this).parent().remove();
	})
	
	/* 2019-12-01 유진 : 고객정보 등록 ajax*/
	$('#register').click(function(){
		var formData=$('#client').serializeArray();
		console.log(formData)
		$.ajax({
			type:"post",
			url:"/chat/register/myclientinfo",
			data:{
				clientInfoNo:formData[0].value,
				clientName:formData[1].value,
				clientPhoneNum:formData[2].value,
				lastChatDate:formData[3].value,
				counselorName:formData[4].value,
				chatMemo:formData[5].value},
			dataType:"json",
			success:function(data){
				console.log("되니ㅣㅣ")
			},
			error: function(){
				console.log("에러ㅓ")
			}
		})
	})
	
	/* 2019-12-01 유진 : 닉네임 등록 ajax */
	$('#alias').click(function(){
		var formData=$('#nick').serializeArray();
		console.log(formData[0].value)
		$.ajax({
			type:"post",
			url:"/chat/myclientalias",
			data:{clientNick:formData[0].value,
				clientInfoNo:formData[1].value},
			dataType:"json",
			success: function(data){
				console.log("되니123")
			},
			error : function(){
				console.log("에러123")
			}
		});
	});
	
});
</script> 

<style type="text/css">
#customerInfo, #customerNick, #template{
	margin:20px;
	margin-bottom:40px;
}
#template{
	margin-top:45px;
}
#info input, #info textarea, #info_btn input,#nick input, .reply{
	padding-left:10px;
	margin-bottom:4px;
	width:100%;
}
#info_btn{
	width:40%;
	float: right;
	display:flex;
}
#nick_btn{
	width:58%;
	margin-right:-3px;
	display:flex;
}
#status_btn{
	width:100%;
	float: right;
	display:flex;
}

.right label{
	color:white;
	font-size:17px;
}
#register, #reset_info, #reset_nick, #alias, #fixed, #block, #set, .reply{
	background-color:#fff;
	border:0;
	outline:0;
	padding:5px;
}
.reply{ 
	text-align:left; 
	padding-left:10px;
} 
#set{
	height:35px;
	width:60px;
	margin-top:1px;
}
#ui-datepicker-div{
	width:334px;
}
#frequentReply{
	margin-top:5px;
	background-color:lightblue;
	width:100%;
	height:135px;
	padding:10px;
	overflow: auto;
}
.use{
	display:flex;
}
.delete{
	width:50px; 
	color:red; 
	border:none; 
	outline:none; 
	background:none
}
</style>


<div id="customerInfo">
	<label for="info">고객정보</label>
	<form method="post" id="client">
		<div id="info">
			<input class="form-control" name="clientInfoNo" id="clientInfoNo" placeholder="고객번호"/>
			<input class="form-control" type="text" id="clientName" name="clientName" placeholder="이름"/>
			<input class="form-control" type="text" id="clientPhoneNum" name="clientPhoneNum" placeholder="전화번호"/>
			<input class="form-control" type="text" id="lastChatDate" name="lastChatDate" placeholder="최근 상담일" autocomplete="off"/>
			<input class="form-control" type="text" id="counselorName" name="counselorName" placeholder="최근 상담자"/>
			<textarea class="form-control" id="chatMemo" name="chatMemo" placeholder="상담내용" rows="3"></textarea>
		</div>
		<div id="info_btn">
			<input class="btn-sm" id="register" type="button" name="action" value="저장" style="margin-right:5px"/>
<!-- 			<input class="btn-sm" id="register" type="submit" name="action" formaction="/chat/register/myclientinfo" value="저장" style="margin-right:5px"/> -->
			<input class="btn-sm" id="reset_info" type="button" name="action" value="초기화"/>
		</div>	
	</form>
</div>

<div id="customerNick">
	<label for="nick">고객 관리</label>
	<form method="post" id="nick">
	<div id="nick" style="display:flex">
		<input class="form-control" type="text" id="clientNick" name="clientNick" placeholder="닉네임" style="margin-right:5px"/>
		<input type="hidden" name="clientInfoNo" id="myClientInfoNo"/>
		<div id="nick_btn">
		<input class="btn-sm" id="alias" type="button" name="action" value="등록" style="margin-right:5px"/>
		<input class="btn-sm" id="reset_nick" type="button" name="action" value="초기화" style="margin-right:5px"/>
		</div>
	</div>	
		<div id="status_btn">
		<input class="btn-sm" id="fixed" type="button" name="action" value="고정매칭" style="margin-right:5px"/>
		<input class="btn-sm" id="block" type="button" name="action" value="차단"/>
		</div>
	</form>
</div>
<div id="template">
	<label for="template" style="margin-top:5px; margin-right:9px">자주 쓰는 답변</label>
	<div id="select" style="display:flex">
		<select name="add" class="form-control" id="add" style="margin-right:5px;">
		<c:forEach var="replyList" items="${replyList}" varStatus="i">
            <option value="${replyList.frequentReplyNo }">${replyList.frequentReplyContent}</option>
          </c:forEach>
         </select>
		<input class="btn-sm" id="set" type="submit" name="action" value="추가"/>
	</div>
	<form method="post">
		<div id="frequentReply"></div>
	</form>
</div>