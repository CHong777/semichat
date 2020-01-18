<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<script>

//추가
var fromID= "semichat"; 
var No;

var hi = 0;
var st = "";

//같은 이가 여러번 보낼때 이름 판별할 변수
var re_send = 0;

function autoClosingAlert(selector, delay) {
	var alert = $(selector).alert();
	alert.show();
	window.setTimeout(function() {alert.hide()}, delay);
}

// 세미챗에서 리스트를 누르면 ClientDB의 ChatRead를 1로 만들어주는 함수
// 이것도 근데 굳이 Ajax가 필요한가 훔
// 아몰랑 다 ajax로 해버렷
function chatRead(chatProfileNo) {
	
	No = chatProfileNo;
	
	  $.ajax({
    	  type: 'get',
       	url: '/chat/chatRead', 
       	data: {
       		chatProfileNo : chatProfileNo,
       	},
       	dataType: "json",
       	success: function(data) {
//      	  console.log(data.result + "입니다람쥐"); 
    		
          	if(data.result != 1) {
          		autoClosingAlert('#warningMessage', 2000);
        	  }
          	 return false;
       	}
   	 });
}

//메시지 전송 함수
function submitFunction(){
			
			//데이터 설정
			// toID는 custom.js에서 설정
			var chatContent = $('#chatContent').val();
			
			//데이터 출력 테스트
// 			console.log("--------------------------------");
// 			console.log("챗프로필 번호  : " + chatProfileNo);
// 			console.log("챗 내용(CONTENT) : " + chatContent);
	
	 		  $.ajax({
	        	  type: 'get',
	           	url: '/chat/chatsubmit', 
	           	data: {
	           		chatProfileNo : chatProfileNo,
	           		chatContent : chatContent
	           	},
	           	dataType: "json",
	           	success: function(data) {
// 	         	  console.log(data.result + "입니다람쥐"); 
	        		  // 실패
	        		  
	        		// 여기서 Ajax로 메시지 전송 실패만 모달로 보여주는데 메시지 전송은 어차피 무적권 성공인데
	        		// 우린 굳이 Ajax 써야하는건가?
	        		// 딱 기능이 진짜 메시지 전송하고 끝이고 Ajax의 result로 받아오는것도 warningMessage 띄어주고 끝남
	        		// 그걸로 뭐 하는게 아니라, 근데 일단 씀
	        		// => if문에 걸릴일이 절대 없고, successMesage 모달 없애자고 했는데
	        		// 그럼 진짜 메시지 전송 요기 부분에서만큼은 Ajax 쓸 필요가 없음
	        		// 근데 일단 씀
	              	if(data.result != 1) {
	              		autoClosingAlert('#warningMessage', 2000);
	            	  }
	                return false;
	           	}
	       	 });
			$('#chatContent').val('');
		}
		
//채팅 리스츠 출력 메서드
var lastID=0;
function chatListFunction(type, chatProfileNo) {
      $.ajax({
         type:"POST",
         url:"/chat/list",
	
         data: {
        	chatProfileNo:chatProfileNo,
            listType:type
         }, 
         dataType: "json",
         success:function(data) {
        	 
        	 hi = 0;
        	 
//         	 console.log("리턴 길이 입니다. " + data.result.length);
            if(data=="") return;
            var result = data.result;
            for(var i=0;i<result.length;i++) {
               if(result[i][0].value.replace(/&nbsp;/gi,'')==fromID){
                  result[i][0].value=fromID
               }
               
               console.log("프롬 : (" + (i+1) +")" + result[i][0].value);
               console.log("챗 컨텐트 : (" + (i+1) +")" + result[i][2].value);
               console.log("챗 타임 : (" + (i+1) +")" + result[i][3].value);
               
               console.log("-----------------------------------------------");
               
               addChat(result[i][0].value, result[i][2].value, result[i][3].value, result.length);
            }
            st = "";
            lastID=Number(data.last);
         }
      });
   }	
	
function addChat(chatName, chatContent, chatTime, length) {
	
	console.log("add Chat가 몇번째 호출 ? "  + (hi+1) );
	

    var chatContentHtml = "";
    var chatFloat = "";
    var chatImg = "";
    //시간 위치
    var timelocation = "";
    
    var check = 0;
    
    if(fromID == chatName) {
       chatContentHtml = '<div class="triangle-right right"><h4 class="media-heading">'+chatContent+
       '</h4></div>';
       
       chatImg = "";
       chatName = "";
       
       timelocation = '<span class="small pull-right" style="display:block; float:right;">';
       
       //초기화
       re_send = 0;
       
    } else {
       chatContentHtml = '<div class="triangle-right left" style="padding:8px;"><h4 class="media-heading">'
       +chatContent+
       '</h4></div>';
   
//		CSS 문제로 잠깐 뻄       
//        한번만 보낼 때(re_send가 0이면 연속해서 보내는게 아님), chatName는 매개변수로 애초에 가져와서 설정해줄 필요 없고 걍 낻두면 됨
//        if(re_send == 0){
//        	 chatImg = '<img class="media-object img-circle" style="width:30px; height:30px;" src="/image/user.png" alt="">';
//        }
//        //여러번 보낼때는 chatName를 감춰야하니까 빈칸으로 초기화
//        else{
//     	   chatImg = '<span style="width:100%;"></span>'
//     	   chatName= "";
//        }

       timelocation = '<span class="small pull-left" style="display:block; float:left;">';
       
       // 1 증가
       re_send++;
    }
   
    console.log("렝스 먼데"  + length);
    
			st += '<div class="col-lg-12">'
			st += '<div class="media">'
// 			st += '<div class="media-body" style="width:100%" >'
// 			st += '<a>'
// 			st += '<a class="pull-left" href="#">'
// 			st += chatImg
// 			st += '</a>'
// 			st += '<h5>'
// 			st += '<h5 class="media-heading">'
// 			st += chatName
// 			st += '</h5>'
// 			st += '</div>'
			st += '<div class="chat-content" style="width:100%;">'
			st += chatContentHtml
			st += timelocation
			st += chatTime
			st += '</span>'
 			st += '</div>'
			st += '</div>'
			st += '</div>'

// 		$('#content').append(
// 				chatFloat + '<div class="col-lg-12">' + '<div class="media">'
// 						+ '<div class="media-body">'
// 						+ '<a class="pull-left" href="#">' + chatImg + '</a>'
// 						+ '<h5 class="media-heading">' + chatName + '</h5>'
// 						+ '</div>' + '<div class = "chat-content">'
// 						+ chatContentHtml + timelocation + chatTime + '</span>'
// 						+ '</div>' + '</div>' + '</div>');

		$('#content').html(st);
// 		$('#content').scrollTop($('#content')[0].scrollHeight);

	}

	function getInfiniteChat() {
		setInterval(function() {
// 			console.log("no몇 ? " + No);
			chatListFunction(lastID, chatProfileNo);
		}, 1000);
	}
</script>


<style type="text/css">
body {
	font-size: 13px;
	margin: 0px auto;
}

/* .panal .panal-footer { */
/*     background: yellow; */
/*     margin-bottom: 50px; */
/* } */

#tabs {
	margin: 0;
	padding: 0;
	list-style: none;
	overflow: hidden;
}

#tabs li {
	float: left;
	display: block;
	padding: 5px;
	background-color: #bbb;
	margin-right: 5px;
}

#tabs li a {
	color: #fff;
	text-decoration: none;
}

#tabs li.current {
	background-color: #b3d7ff;
}

#content.current {
	background-color: #b3d7ff;
}

/* #content li.current { */
/* 	background-color: #b3d7ff; */
/* } */

#tabs li.current a {
	color: #000;
	text-decoration: none;
}

#tabs li a.remove {
	color: #f00;
	margin-left: 10px;
}


#content current a {
	color: #000;
	text-decoration: none;
}

#content {  
  	background-color: white;  
}  

#content p {
	margin: 0;
	padding: 20px 20px 100px 20px;
}

/* #chatdesk { */
/* 	float: left; */
/* 	margin: 0 20px 0 0; */
/* } */


#content {  
 	width:100%;
 	background-color: #b3d7ff;  
}  

</style>

<div id="chatdesk">
	<ul id="tabs">
		<!-- Tabs go here -->
	</ul>
	<div class="container bootstrap snippet" id = "chatText">
		<div class="col-xs-12">
			<div class="panal panel-info">
				<div class="panel-heading">
					실시간 상담중
					<div class="clearfix"></div>
				</div>
				<div class="panel-body chat-widget" style="height: 70%;">
					<div id="content" style="overflow-y: auto; height: 600px"></div>
					<div id = "storagy" style='display:none;'></div>
				</div>
				<div class="panal-footer">
					<div class="row" style="height: 30%; padding-left: 15px;">
						<div class="form-group col-xs-10" style="width: 90%;">
							<textarea id="chatContent" class="form-control"
								placeholder="메시지를 입력하세요" maxlength="100"
								onkeydown="if(event.keyCode==13){ if(!event.shiftKey){ event.preventDefault(); submitFunction();}}"></textarea>
						</div>
						<div class="form-group col-xs-1" style="width: 10%">
							<div class="text-center">
								<button type="button" class="btn btn-info pull-right"
									onclick="submitFunction();">전송</button>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="alert alert-success" id="successMessage" style="display:none;">
		<strong>메시지 전송에 성공했습니다</strong>
	</div>
	<div class="alert alert-danger" id="dangerMessage" style="display:none;">
		<strong>이름과 내용을 모두 입력해주세요</strong>
	</div>
	<div class="alert alert-warning" id="warningMessage" style="display:none;">
		<strong>응답데이터가 0 이긴 함</strong>
	</div>
	<!-- Tab chatcontent goes here -->
</div>

<script type="text/javascript">
		$(document).ready(function() {
			chatListFunction('0', No);
			getInfiniteChat();
		});
</script>