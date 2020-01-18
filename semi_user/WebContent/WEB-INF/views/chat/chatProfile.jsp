<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<!-- 현재 날짜 시간 구하기 -->
<jsp:useBean id="currTime" class="java.util.Date" />

<script type="text/javascript">

//메신저(카카오톡, 라인 등) checkbox눌렸는지 확인 - 2019-12-01 현석
//자바스크립트로 ajax짜려고 했으나 제이쿼리로 노선변경 
//input 태그마다  onClick="submit_messenger(this.childNodes[1].childNodes[1]); 해줘야하는 불편함이있음

// function submit_messenger(cb) {
// 	var messengerNo = cb.value;
// 	var is_checked = cb.checked; //체크된 상태(true)
// }

	var messengerNoArray = ""; // 메신저 번호 나열
	var messengerNo = ""; 	   // 메신저 번호(1:카카오톡, 2:라인..)
	var search_str = ""; 	   // 검색어
	var status = "";     	   // 채팅 프로필 상태
	
	$(document).ready(function() {

		//ajax 1초마다 한번씩 전송하기 
		setInterval(function(){
			//파라미터 가져오기
			var param = getParam();
			
			submit_ajax(param);
			
		}, 1000);
		
		//검색조건1 search - 2019-12-02 현석
		$("#my-search").click(function(){
			
			//파라미터 가져오기
			var param = getParam();
			
			//ajax전송하기
			submit_ajax(param);
		});
		
		//검색조건2 checkbox - 2019-12-01 현석
		$("div .messenger-check").click(function() {
			
			//2-1
			search_str = $("input[name=search]").val();
			
			messengerNoArray = ""; //선택된 메신저 번호들
			messengerNo = $(this).children().children().val(); //현재 선택된 메신저 번호
			
			//현재 선택한 메신저가 체크되어있는 상태가 아니라면 같이 추가해줘야한다.
			if (!$(this).children().children().is(":checked")) messengerNoArray += messengerNo; 
			else messengerNoArray = "";
			
			//선택된 메신저 번호들 불러오기
			$("input[name=messenger]:checked").each(function() {
				
				if(messengerNo == $(this).val()) return true;
				
				if (messengerNoArray == "") messengerNoArray = $(this).val();
				else messengerNoArray = messengerNoArray + ", " + $(this).val();
			});
			
			//2-3 상태(NEW, 진행중, 완료)
			status = $("#chatStatus .active").val();
			
			//최종 파라미터
			var param = {
				search_str : search_str,
				messengerNoArray : messengerNoArray,
				status : status
			};
			
			//ajax전송하기
			submit_ajax(param);

		});
		
		//검색조건 3 : 상태 new, 진행중, 완료  -클릭시 상태변경 by현석 2019-11-26 
		$("#chatStatus li").click(function() {
			
			//1-1
			search_str = $("input[name=search]").val();
			
			//CSS 처리
			$("#chatStatus li").removeClass("active");
			$(this).addClass("active");
			
			//검색조건 2번 : 선택된 메신저 번호들 불러오기
			messengerNo = "";
			messengerNoArray = "";
			$("input[name=messenger]:checked").each(function() {
				
				if(messengerNo == $(this).val()) return true;
				if (messengerNoArray == "") messengerNoArray = $(this).val();
				else messengerNoArray = messengerNoArray + ", " + $(this).val();
			
			});
			
			//검색조건 3번 : 상태(NEW, 진행중, 완료)
			var status = $(this).val();
			
			//최종 파라미터
			var param = {
				search_str : search_str,
				messengerNoArray : messengerNoArray,
				status : status
			}
			
			//ajax전송하기
			submit_ajax(param);
			
		});
		
		//채팅프로필 검색조건으로 ajax 전송하기 - 2019-12-02 현석 
		function submit_ajax(param) {
			
			console.log(param);
			$.ajax({
				type : "POST",
				url : "/chat/profile",
				data : {
					messengerNoArray : param.messengerNoArray,
					status : param.status,
					search_str : param.search_str
				},
				dataType : "json",
				async : false,
				error : function() {
					alert("통신실패!!!!");
				},
				success : function(data) {
					if(data != 'null'){
							get_chatProfileList(data.result);
						}
					}
				});
// 			}, 1000);
		}
	});
</script>

<style type="text/css">

body {
   font-size: 13px;
   margin: 0px auto;
}

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
   cursor:pointer;
}

#tabs li a {
   color: #fff;
   text-decoration: none;
}

#tabs li.current {
   background-color: #e1e1e1;
}

#tabs li.current a {
   color: #000;
   text-decoration: none;
}

#tabs li a.remove {
   color: #f00;
   margin-left: 10px;
}

#content {
   background-color: #e1e1e1;
}

#content p {
   margin: 0;
   padding: 20px 20px 100px 20px;
}

#documents {
   margin: 0;
   padding: 0;
}

/* 현석's CSS  2019-11-25*/

/* --------------------------------------------- */

/* 메신저 체크박스 div (카카오톡, 라인, ...) */
.messenger-checkbox {
   background-color : lightblue;
   padding : 5%;
   color : white;
}

.messenger-checkbox label {
	padding : 2%;
/* 	margin-bottom : 0px; /* 부트스트랩 막음 */ 
}

/*각 메신저들 크기(카카오톡, 라인 등등)*/
.messenger-check {
	display : inline-block;
	margin : 2%;
	white-space : nowrap; /*인라인 블록에서 한줄바꿈 없애줌!!*/
}

.messenger-check label {
	padding : 5px;
	cursor : pointer !important;
}

/* 채팅 프로필 상태 박스(div) */
.chatstatus {
   margin-top : 5%;
   margin-bottom : 5%;
}

.chatstatus ul {
	width : auto; display : block;  /* 부모크기 그대로 가져오기 */  
	text-align : center; /*고정크기 정해줘야 li들이 가운데 정렬됨 */
}

/* 채팅 프로필 상태(New, 진행중, 완료) */
.chatstatus li {
   background-color : #fff;
   display : inline-block; /*li들의 가운데 정렬을 위해 사용*/
   padding : 4%;
   margin : 1%;
   width : 30%;
   text-align : center; 
   zoom : 1; *display:inline; /*다른 브라우저에서 깨짐 문제 해결*/
   cursor : pointer;
}

/* 채팅 프로필 상태 li 부트스트랩 파랑색으로 나오는거 검은색으로 바꿈 */
.chatstatus li a {
   color : black;
   size : 20px;
}

/* 상태 표시된것 노란색으로 new , 진행중, 완료 - 2019-12-02 서현석 */
#chatStatus .active {
	background-color : yellow;
}

/* 채팅 프로필 리스트 DIV */
.chatProfileList {
   background-color : lightblue;
   height : 420px;
   overflow : auto; /*ul에서도 되지만 가끔 안먹는 문제 발생한다고함*/
}

/* 채팅 프로필 리스트 ul 동그라미 없애기*/
.chatProfileList ul {
   list-style: none;
   padding : 0px;
}

.chatProfileList li {
   padding: 3%;
}

/* fontAwesome 45도회전 - 채팅프로필 pin아이콘에 사용 */
.fa-rotate-45 {-webkit-transform:rotate(45deg); -moz-transform:rotate(45deg); -ms-transform:rotate(45deg); -o-transform:rotate(45deg); transform:rotate(45deg);}

/* 현석's CSS  끝 */
</style>

<!--1. 채팅 프로필 리스트 검색-->
<div class="form-group row justify-content-center" style="margin: 1px;">
		<!-- search -->
		<div>
			<form id="chatProfileForm" name="chatProfileForm" method="post">
			<!-- 검색창 -->
			<div class="input-group mb-3">
				<input type="text" name="search" class="form-control" placeholder="Search"
					aria-label="Search" aria-describedby="my-search">
				<div class="input-group-append">
					<span class="input-group-text" id="my-search"><i
						class="fa fa-search"></i></span>
				</div>
			</div>
			<!-- 검색창 끝 -->

			<!-- 체크박스(카카오톡, 라인) 시작 -->
			<div class="messenger-checkbox">
				<div class="btn-group-toggle messenger-check" data-toggle="buttons">
					<label class="btn btn-outline-info active">
						<input type="checkbox" id="kakao" name="messenger" value="1" checked>
						카카오톡
					</label>
				</div>
				
				<div class="btn-group-toggle messenger-check" data-toggle="buttons">
					<label class="btn btn-outline-info">
						<input type="checkbox" id="line" name="messenger" value="2"> 라인
					</label>
				</div>
				<div class="btn-group-toggle messenger-check btn-group" data-toggle="buttons">
					<label class="btn btn-outline-info"> <input type="checkbox"
						id="instagram" name="messenger" value="3"> 인스타그램
					</label>
				</div>
				<div class="btn-group-toggle messenger-check" data-toggle="buttons">
					<label class="btn btn-outline-info"> <input type="checkbox"
						id="facebook" name="messenger" value="4"> 페이스북
					</label>
				</div>
				<div class="btn-group-toggle messenger-check" data-toggle="buttons">
					<label class="btn btn-outline-info"> <input type="checkbox"
						id="wichat" name="messenger" value="5"> 위챗
					</label>
				</div>
			</div>
			<!-- 체크박스 끝 -->

			<!-- 채팅 진행 상태 -->
			<div class="chatstatus">
				<ul class="nav nav-pills" id="chatStatus">
					<li role="presentation" value="0" class="active">New</li>
					<li role="presentation" value="1">진행중</li>
					<li role="presentation" value="2">완료</li>
				</ul>
			</div>
			<!-- 채팅 진행 상태 끝 -->
		</form>
	</div>
	<!-- search 끝 -->
</div>
<!-- 2019-11-25 서현석 -->

<!-- 2. 채팅 프로필 리스트 목록 doGet시 나옴 -->
<div class="chatProfileList">
	<ul id="chatProfileList">
		<c:forEach items="${chatProfileList }" var="chatProfile">
			<li class="chatProfile">
				<!-- 채팅 프로필 카드-->
				<div class="card bg-lignt text-dark"
					data-chatProfileNo="${chatProfile.chatProfileNo }"
					data-clientID="${chatProfile.clientID}"
					data-clientInfoNo="${chatProfile.clientInfoNo }"
					style="margin-bottom: 0px !important; cursor: pointer;">
					<!-- 채팅 프로필 내용 -->
					<div class="card-body" style="padding : 1.0rem; position:relative;">
						<!-- 뱃지 툴팁(안읽은 메시지 갯수) -->
						<c:if test="${chatProfile.unReadChatCount ne 0 } ">
							<span class="badge badge-pill badge-danger" style="position:absolute; top:-5px; right:-5px; z-index:1; font-size:1.0em;">${chatProfile.unReadChatCount }</span>
						</c:if>
						<div>
							<h5 class="card-title" style="display: inline;">${chatProfile.lastChatContent }</h5>
							<i class="fas fa-thumbtack fa-rotate-45"
								style="font-size: 20px; color: black; float: right;"></i>
							<p style="display: inline; float: right; margin-right: 10px; margin-bottom:0px;">${chatProfile.lastChatTime}</p>
						</div>
						<div>
							<!-- 메신저 이미지 불러오기 -->
							<c:choose>
								<c:when test="${chatProfile.messengerNo == 1 }">
									<img src="/icon/kakao-talk.png" width="15px" height="15px" />
								</c:when>
								<c:when test="${chatProfile.messengerNo == 2 }">
									<img src="/icon/line.png" width="15px" height="15px" />
								</c:when>
								<c:when test="${chatProfile.messengerNo == 3 }">
									<img src="/icon/instagram.png" width="15px" height="15px" />
								</c:when>
								<c:when test="${chatProfile.messengerNo == 4 }">
									<img src="/icon/messenger.png" width="15px" height="15px" />
								</c:when>
								<c:when test="${chatProfile.messengerNo == 5 }">
									<img src="/icon/wechat.png" width="15px" height="15px" />
								</c:when>
								<c:otherwise>
									<img src="/icon/no-image.png" width="15px" height="15px" />
								</c:otherwise>
							</c:choose>
							<!-- ------ -->
							${chatProfile.clientID }
							<span class="badge badge-pill badge-success" style=" float:right; padding:2%; width:50%; ">매칭전</span>
						</div>
					</div>
				</div>
			</li>
		</c:forEach>
	</ul>
</div>


