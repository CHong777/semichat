var chatProfileNo; // 자주 쓰여서 전역변수로 변경 12-01 홍철

//Ajax로 보낼 채팅 프로필 From by 2019-12-03 현석
function getParam() {
	
	//1-1
	var search_str = $("input[name=search]").val();
	
	//1-2 선택된 메신저 번호들 불러오기
	messengerNoArray = "";
	messengerNo = ""; 
	
	$("input[name=messenger]:checked").each(function() {
		if(messengerNo == $(this).val()) return true;
		if (messengerNoArray == "") messengerNoArray = $(this).val();
		else messengerNoArray = messengerNoArray + ", " + $(this).val();
	});
	
	//1-3 상태(NEW, 진행중, 완료)
	status = $("#chatStatus .active").val();
	
	//최종 파라미터
	var param = {
		search_str : search_str,
		messengerNoArray : messengerNoArray,
		status : status
	};
	
	return param;
}

		//서버에서 JSON으로 보낸 채팅 프로필 리스트 불러오기 - 2019-12-02 서현석
		function get_chatProfileList(chatProfile){
			
			//내용 비우기
			$("#chatProfileList").remove();
			
// 			$("#chatProfileList").append("<ul id='chatProfileList'>")
			var chatProfileCard = "<ul id='chatProfileList'>";
		
			for(var i in chatProfile){
				
				console.log(chatProfile);
// 				var chatProfileCard = "";
				var counselorid = "";
				if(chatProfile[i].counselorID == null) counselorid = "매칭전";
				else counselorid = chatProfile[i].counselorID;
				
				chatProfileCard += "<li class='chatProfile'>";
				chatProfileCard += "<div class='card bg-light text-dark'";
				chatProfileCard += " data-chatProfileNo = '"+chatProfile[i].chatProfileNo+"'";
				chatProfileCard += " data-clientID = '"+chatProfile[i].clientID+"'";
				chatProfileCard += " data-clientInfoNo = '"+chatProfile[i].clientInfoNo+"'";
				chatProfileCard += " style='margin-bottom : 0px !important; cursor:pointer;'>";
				
				chatProfileCard += "<div class='card-body' style='padding:1.0rem; position:relative;'>";
				
				if(chatProfile[i].unReadChatCount != 0){
					chatProfileCard += "<span class='badge badge-pill badge-danger' style='position:absolute; top:-5px; right:-5px; z-index:1; font-size:1.0em;'>"+chatProfile[i].unReadChatCount+"</span>";	
				}
				chatProfileCard += "<div>";
				chatProfileCard += "<h5 class='card-title' style='display:inline;'>"+chatProfile[i].lastChatContent+"</h5>";
				
				chatProfileCard += "<i class='fas fa-thumbtack fa-rotate-45'";
				chatProfileCard += " style='font-size : 20px; color:black; float:right;'></i>";
				chatProfileCard += "<p style='display:inline; float:right; margin-right:10px; margin-bottom:0px;'>"+chatProfile[i].lastChatTime+"</p>";
				chatProfileCard += "</div>";
				
				switch(chatProfile[i].messengerNo){
				case 1 :
					chatProfileCard += "<img src='/icon/kakao-talk.png' width='15px' height='15px' />";
					break;
				case 2 : 
					chatProfileCard += "<img src='/icon/line.png' width='15px' height='15px' />";
					break;
				case 3 : 
					chatProfileCard += "<img src='/icon/instagram.png' width='15px' height='15px' />";
					break;
				case 4 : 
					chatProfileCard += "<img src='/icon/messenger.png' width='15px' height='15px' />";
					break;
				case 5 :
					chatProfileCard += "<img src='/icon/wechat.png' width='15px' height='15px' />";
					break;
				default :
					"<img src='/icon/no-image.png' width='15px' height='15px' />";
				}
				
				chatProfileCard += chatProfile[i].clientID;
				chatProfileCard += "<span class='badge badge-pill badge-success' style='float:right; padding:2%; width:50%; '>"+counselorid+"</span>";
				chatProfileCard += "</div>";
				chatProfileCard += "</div>";
				chatProfileCard += "</li>";
				
			}
			
			chatProfileCard += "</ul>";
			
			$(".chatProfileList").html(chatProfileCard);
			
		}

$(document).ready(function() {
	var clientInfoNo=0;
//	var client=new Array();
//	var myClient=new Array();
	
	var clientInfo={};
	var myClientInfo={};
	
	var pageCount = 0; //처음 화면 내용창 안뜨게 하기 위해
	
	//처음화면이면 내용창 지움
	$("#chatdesk").hide();

	//채팅프로필 클릭 이벤트**
	//documents -> chatProfileList로 변경  by현석 2019-11-26
	//Ajax후 가져온 엘리먼트 태그들에게 동적바인딩 걸기위해 click -> on으로 바꿈
	//	$(".chatProfile").click(function() {
	$(document).on("click","li.chatProfile", function(){
		
		var clientInfoNo = $(this).children().attr("data-clientInfoNo");
		chatProfileNo = $(this).children().attr("data-chatprofileNo");
		
		//채팅프로필 form 가져오기
		var param = getParam();
		param.status = 1;
		
		//Ajax를 통해 채팅 프로필 상세 정보들을 가져온다  by 현석 2019-12-03
		var obj = getChatProfileDetail(chatProfileNo, clientInfoNo, param);
		
//		setClientInfo(obj.clientInfo);
//		setChat(obj.chat);
		
		$("#chatdesk").show();
	
		//챗팅 읽음 여부 구현위한 메서드 호출 - 홍철 추가 12-01
	    chatRead(chatProfileNo)
		
		//채팅 프로필 클릭시 New->진행중
		get_chatProfileList(obj.chatProfileList);
		
		//css - new->진행중
		$("#chatStatus .active").removeClass("active");
		$("#chatStatus li").eq(1).addClass("active");
		
		//css - 채팅 프로필 클릭시 테두리 색상 변경 + 나머지 채팅 프로필 테두리는 원위치
		$("li.chatProfile").children().css("border","2px solid cyan");
		$(".chatProfile [data-chatprofileno != "+chatProfileNo+"]").css("border","none");
		
		// 2019 12 03 유진 - client 정보 가져오기
		clientInfo=set_clientInfo(clientInfoNo);
		myClientInfo=set_myClientInfo(clientInfoNo);
		
		//채팅창 이동시 이미 있으면 move 없으면 add
		console.log(clientInfo)
		console.log(myClientInfo)
		
		if ($("#chatTab_" + chatProfileNo).length == 0)
			addTab($(this).children(),clientInfo,myClientInfo);
		else{
			moveTab($(this).children());
		}
        
	});

	// 대화창 탭 이동 - 동적바인딩은 부모에 걸어야한다! 2019-11-25
	$("#tabs").on("click", "a.tab",function() {
		
		$('#chatContent').html("");
		
		clientInfoNo = $(this).attr("data-clientInfoNo");
		chatProfileNo = $(this).attr("data-chatProfileno");
		var contentname = $(this).attr("id") + "_content";

		// hide all other tabs
		$("#content p").hide();
		$("#tabs li").removeClass("current");

		// show current tab
		$("#" + contentname).show();
		$(this).parent().addClass("current");
		
		// 2019 12 03 유진 : 탭 이동 시 client 정보 가져오기
		clientInfo=set_clientInfo(clientInfoNo);
		myClientInfo=set_myClientInfo(clientInfoNo);

		//선택된 채팅 프로필 테두리 색상 변경 + 나머지 채팅 프로필 테두리는 원위치
		$(".chatProfile [data-chatProfileno = "+chatProfileNo+"]").css("border","2px solid cyan");
		$(".chatProfile [data-chatprofileno != "+chatProfileNo+"]").css("border","none");
	
	});

	// 대화창 탭 닫기
	$("#tabs").on('click', "a.remove", function() {

		$('#chatContent').html("");

		//x버튼 눌렀을때
		pageCount--;
		console.log("x버튼 누를때 값 : " + pageCount);

		//pageCount가 0이면 처음화면
		//pageCount가 1이면 처음화면 아님
		if(pageCount == 0){
			$("#chatdesk").hide();
			clear();	// 2019 12 03 유진 고객 정보 지워주기
		}
		else
			$("#chatdesk").show();


		// Get the tab name
		var tabid = $(this).parent().find(".tab").attr("id");
		
		// remove tab and related content
		var contentname = tabid + "_content";
		$("#" + contentname).remove();
		$(this).parent().remove();

		//현재 열려있는 탭이 없다면 이전탭으로 열기
		if ($("#tabs li.current").length == 0 && $("#tabs li").length > 0) {

			// 이전 탭 찾기  
			var lastChatTab = $("#tabs li:last-child");
			lastChatTab.addClass("current");
			// 2019 12 03 유진 - 마지막 탭의 clientInfoNo 얻어오기
			var clientInfoNo = $(lastChatTab).find("a.tab").attr("data-clientinfono");
		
			// 이전 탭 아이디 찾아서 채팅창과 연결
			var lastChatTabId = $(lastChatTab).find("a.tab").attr("id");
			$("#" + lastChatTabId + "_content").show();
			
			// 2019 12 03 마지막 탭의 client 정보 가져오기
			clientInfo=set_clientInfo(clientInfoNo);
			myClientInfo=set_myClientInfo(clientInfoNo);

			//홍철 추가/ 챗프로파일 넘버 이전 탭 번호로 지정
	         //탭번호로 부터 챗 프로파일 넘버 추출
	         var TabNo = lastChatTabId.substr(8,1);
	         chatProfileNo = TabNo;
		}
		
		//파라미터 가져오기
		var param = getParam();
		
		//파라미터 전달
		$.ajax({
			type : "GET",
			url : "/chat/profile/close",
			data : {
				messengerNoArray : param.messengerNoArray,
				status : param.status,
				search_str : param.search_str,
				chatProfileNo : chatProfileNo,
				clientInfoNo : clientInfoNo
			},
			dataType : "json",
			error : function() {
				alert("ajax오류ㅠ_ㅠ");
			},
			success : function() {
				
			}
		});
		
	});
	
	//2019-11-30 현석 - chatTab이동
	function moveTab(link) {

		chatProfileNo = $(link).attr("data-chatProfileno");
		var contentname = chatProfileNo+ "_content";
		
		// 모든 탭 css 제거
		$("#content p").hide();
		$("#tabs li").removeClass("current");

		// 현재 탭 css추가
		$("#" + contentname+"").show();
		$("#chatTab_"+chatProfileNo).parent().addClass("current");

		// 탭 찾아서 채팅창과 연결
		var chatTabId = $("#chatTab_"+chatProfileNo).find("a.tab").attr("id");
		$("#" + chatTabId + "_content").show();
		
	}
	
	//chatTab 생성
	function addTab(link, clientInfo, myClientInfo) {

		pageCount++;
		console.log(clientInfo["clientName"]);
		console.log(myClientInfo);
		
		//chatTab 넘버, 클라이언트 아이디 설정 -> 2019 12 03 유진 clientInfoNo 추가
		var clientInfoNo = $(link).attr("data-clientInfoNo");
		var chatProfileNo = $(link).attr("data-chatProfileNo");
		var clientID = $(link).attr("data-clientId");

		//클라이언트 ID 받아오기 12-01 홍철
		var clientID = $(link).attr("data-clientID");

		// hide other tabs
		$("#tabs li").removeClass("current");
		$("#content p").hide();

		// 새탭 및 채팅창 추가 -> 2019 12 03 유진 data-clientInfoNo 추가
		$("#tabs").append("<li class='current'><a class='tab' id='" +"chatTab_"+
				chatProfileNo + "' data-chatProfileNo='"+chatProfileNo+"' data-clientInfoNo='"+clientInfoNo+"'>"+clientID+ 
		"</a><a class='remove'>x</a></li>");

		$("#content").append("<p id='" + $(link).attr("data-chatProfileNo") + "_content'>" + 
				$(link).attr("title") + "</p>");

		// set the newly added tab as current
		$("#" + $(link).attr("data-chatProfileNo") + "_content").show();
	}
	
	//	chatProfile 클릭시 채팅 프로필 상세데이터 Ajax로 가져오기 - 2019-12-03 현석
	function getChatProfileDetail(chatProfileNo, clientInfoNo, param) {
		
		var obj = {};
		
		$.ajax({
			type : "GET",
			url : "/chat/profile/detail",
			data : {
				messengerNoArray : param.messengerNoArray,
				status : param.status,
				search_str : param.search_str,
				chatProfileNo : chatProfileNo,
				clientInfoNo : clientInfoNo
			},
			async : false,
			dataType : "json",
			error : function() {
				alert("ajax오류!");
			},
			success : function(data) {
				console.log(data);
				if(data != null) obj = data.result;
			}
		});
		
		return obj;
	}

	// 2019 12 03 유진 clientInfo 가져오기
	function set_clientInfo(clientInfoNo){
		$.ajax({
			type:"get",
			url:"/chat/register/myclientinfo",
			data:{clientInfoNo:clientInfoNo},
			dataType:"json",
			async:false,
			success: function(data){
				$("#info").attr("class","info_"+clientInfoNo);
				// 고객 정보에 나타내 줄 정보들 set해주기
				$("#clientInfoNo").attr("value", data.result.clientinfo[0]);
				// clientName
				if(data.result.clientinfo[1]=='null'){
					$("#clientName").attr("value", "");
				} else {
					$("#clientName").attr("value", data.result.clientinfo[1]);
					clientInfo.clientName=data.result.clientinfo[1];
				}
				// clientPhoneNum
				if(data.result.clientinfo[2]=='null'){
					$("#clientPhoneNum").attr("value", "");
				} else {
					$("#clientPhoneNum").attr("value", data.result.clientinfo[2]);
					clientInfo.clientPhoneNum=data.result.clientinfo[2];
				}
				// lastChatDate
				if(data.result.clientinfo[3]=='null'){
					$("#lastChatDate").attr("value", "");
				} else {
					$("#lastChatDate").attr("value", data.result.clientinfo[3]);
					clientInfo.lastChatDate=data.result.clientinfo[3];
				}
				// counselorName
				if(data.result.clientinfo[4]=='null'){
					$("#counselorName").attr("value", "");
				} else {
					$("#counselorName").attr("value", data.result.clientinfo[4]);
					clientInfo.counselorName=data.result.clientinfo[4];
				}
				// chatMemo
				if(data.result.clientinfo[5]=='null'){
					$("#chatMemo").html("");
				} else {
					$("#chatMemo").html(data.result.clientinfo[5]);
					clientInfo.chatMemo=data.result.clientinfo[5];
				}
				
//				client[pageCount]=clientInfo;
			},
			error : function(request,status,error){  /* 에이젝스 에러 메세지 확인 */ 
				alert("code:"+request.status+"\n"+"message:"
						+request.responseText+"\n"+"error:"+error); 
			}
		})
		return clientInfo;
	}
	
	// 2019 12 03 유진 clientNick 가져오기
	function set_myClientInfo(clientInfoNo){
		$.ajax({
			type:"get",
			url:"/chat/myclientalias",
			data:{clientInfoNo:clientInfoNo},
			dataType:"json",
			async:false,
			success:function(data){
//				console.log(data);
				$("#myClientInfoNo").attr("value",data.result.myclientinfo[6]);
				// clientNick
				if(data.result.myclientinfo[5]=='null'){
					$("#clientNick").attr("value","");								
				} else {
					$("#clientNick").attr("value",data.result.myclientinfo[5]);	
					myClientInfo.clientNick=data.result.myclientinfo[5]
				}
//				myClient[pageCount]=myClientInfo;

				// 차단, 고정 매칭 상태에 따라 버튼에 변화
				if(data.result.myclientinfo[3]==1){
					$("#block").css("background-color","red");
					$("#block").attr("value","차단 중");
				} else {
					$("#block").css("background-color","white");
					$("#block").attr("value","차단");
				}
				if(data.result.myclientinfo[4]==1){
					$("#fixed").css("background-color","#4ea1d3");
					$("#fixed").attr("value","매칭 중");
				} else {
					$("#fixed").css("background-color","white");
					$("#fixed").attr("value","고정매칭");
				}
				
			}
		});
		return myClientInfo;
	}

	// 2019 12 03 유진 - 더 이상 탭이 존재하지 않을 때 고객정보 지워주기
	function clear(){
		$("#clientInfoNo").attr("value", "");
		$("#clientName").attr("value", "");
		$("#clientPhoneNum").attr("value", "");
		$("#lastChatDate").attr("value", "");
		$("#counselorName").attr("value", "");
		$("#chatMemo").html("");
		$("#clientNick").attr("value", "");
		$("#block").css("background-color","white");
		$("#block").attr("value","차단");
		$("#fixed").css("background-color","white");
		$("#fixed").attr("value","고정매칭");
	}
	
	/* 2019-11-30 유진 : 자주 쓰는 답변 선택 시 채팅 입력 창에 나타내주기  (수정필요)*/
	$('#frequentReply').on("click","input.reply", function(){
		var text=$(this).val();
		$('#chatContent').attr("value",text);
		
	})
});

      