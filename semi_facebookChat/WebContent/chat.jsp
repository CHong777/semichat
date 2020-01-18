<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<%
		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
		
		
		//----------추가--------------------
		String fromID = null;
		if(request.getParameter("fromID")!=null) {
			fromID = (String) request.getParameter("fromID");
		} 
		
		//----------추가--------------------
		
		
		String toID = null;
		if(request.getParameter("toID")!=null) {
			toID = (String) request.getParameter("toID");
		}
		if(userID==null) {
	    	session.setAttribute("messageType", "오류 메시지");
	    	session.setAttribute("messageContent", "현재 로그인이 되어 있지 않습니다");
	    	response.sendRedirect("index.jsp");
	    	return;
	     }
		if(toID==null) {
	    	session.setAttribute("messageType", "오류 메시지");
	    	session.setAttribute("messageContent", "대화 상대가 지정되지 않았습니다");
	    	response.sendRedirect("index.jsp");
	    	return;
	     }
		if(userID.equals(URLDecoder.decode(toID, "UTF-8"))) {
			session.setAttribute("messageType", "오류 메시지");
	    	session.setAttribute("messageContent", "자기 자신에게는 쪽지를 보낼 수 없습니다 ");
	    	response.sendRedirect("index.jsp");
	    	return;
		}
	%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="./css/bootstrap.css">
<link rel="stylesheet" href="./css/custom.css">
<title>채팅서비스</title>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="./js/bootstrap.js"></script>
<script type="text/javascript">

	//같은 이가 여러번 보낼때 이름 판별할 변수
	var re_send = 0;

	function autoClosingAlert(selector, delay) {
		var alert = $(selector).alert();
		alert.show();
		window.setTimeout(function() {alert.hide()}, delay);
	}
	
	function submitFunction(){
		var fromID = '<%= userID %>';
		var toID = '<%= toID %>';
		var chatContent = $('#chatContent').val();
		$.ajax({
			type : "POST",
			url : "./ChatSubmitServlet",
			data : {
				fromID : encodeURIComponent(fromID),
				toID : encodeURIComponent(toID),
				chatContent : encodeURIComponent(chatContent),
			},
			success : function(result) {
				if(result==1) {
					autoClosingAlert('#successMessage', 2000);
				} else if (result==0) {
					autoClosingAlert('#dangerMessage', 2000);					
				} else {
					autoClosingAlert('#warningMessage', 2000);					
				}
			}
		});
		$('#chatContent').val('');
	}
	var lastID=0;
	function chatListFunction(type) {
		var fromID='<%= userID%>'; 
	      var toID='<%= toID%>'; 
	      $.ajax({
	         type:"POST",
	         url:"./ChatListServlet",

	         data: {
	            fromID:encodeURIComponent(fromID),
	            toID:encodeURIComponent(toID),
	            listType:type
	         }, 
	         success:function(data) {
	            if(data=="") return;
	            var parsed = JSON.parse(data);
	            var result = parsed.result;
	            for(var i=0;i<result.length;i++) {
	               if(result[i][0].value.replace(/&nbsp;/gi,'')==fromID){
//	                   result[i][0].value='나';
	//변경
	                  result[i][0].value=fromID
	               }
	               addChat(result[i][0].value, result[i][2].value, result[i][3].value);
	            }
	            lastID=Number(parsed.last);
	         }
	      });
	   }
	function addChat(chatName, chatContent, chatTime) {

        var chatContentHtml = "";
        var chatFloat = "";
        var chatImg = "";
        //시간 위치
        var timelocation = "";
        
        //추가
        var fromID='<%= userID%>'; 
        
        
        if(fromID == chatName) {
           chatContentHtml = '<div class="triangle-right right"><h4 class="media-heading">'+chatContent+
           '</h4></div>';
           
           chatImg = "";
           chatName = "";
           timelocation = '<span class="small pull-right">';
           
           //초기화
           re_send = 0;
           
        } else {
           chatContentHtml = '<div class="triangle-right left"><h4 class="media-heading">'
           +chatContent+
           '</h4></div>';
           //한번만 보낼 때(re_send가 0이면 연속해서 보내는게 아님), chatName는 매개변수로 애초에 가져와서 설정해줄 필요 없고 걍 낻두면 됨
           if(re_send == 0){
           	chatImg = '<img class="media-object img-circle" style="width:30px; height:30px;" src="images/icon.png" alt="">';
           }
           //여러번 보낼때는 chatName를 감춰야하니까 빈칸으로 초기화
           else
        	   chatName= "";
           
           timelocation = '<span class="small pull-left">';
           
           // 1 증가
           re_send++;
        }
        
        $('#chatList').append(chatFloat+
              '<div class="col-lg-12">'+
              '<div class="media">'+
              '<div class="media-body">'+
              '<a class="pull-left" href="#">'+
              chatImg+
              '</a>'+
              '<h5 class="media-heading">'+
              chatName +
              '</h5>'+
              '</div>'+
              '<div class = "chat-content">'+
              chatContentHtml+
              timelocation+
              chatTime+
              '</span>'+
              '</div>'+
              '</div>'+
              '</div>'
              );
        
        $('#chatList').scrollTop($('#chatList')[0].scrollHeight);
        
     }
	   function getInfiniteChat() {
	      setInterval(function() {
	         chatListFunction(lastID);
	      },1000);
	   }

	function getUnread() {
		$.ajax({
			type:"POST",
			url:"./ChatUnreadServlet",
			data:{
				userID:encodeURIComponent('<%=userID%>'),
			},
			success:function(result){
				if(result>=1) {
					showUnread(result);
				} else {
					showUnread('');
				}
			}
		});
	}
	
	function getInfiniteUnread() {
		setInterval(function() {
			getUnread();
		}, 1000);
	}
	
	function showUnread(result) {
		$('#unread').html(result);
	} 
</script>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collpase-1"
				aria-expanded="false">
				<span class="icon-bar"> </span> <span class="icon-bar"> </span> <span
					class="icon-bar"> </span>
			</button>
			<a class="navbar-brand" href="index.jsp"><img class = "facebook" src="face2.jpg" /></a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="index.jsp">메인</a></li>
				<li><a href="find.jsp">친구찾기</a></li>
				<li><a href="box.jsp">메시지함<span id="unread" class="label label-info"></span></a></li>
				
			</ul>
			<%
				if(userID != null) {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-hashpopup="true"
					aria-expanded="false">회원관리 <span class="caret"></span>
				</a>
				<ul class="dropdown-menu">
					<li><a href="logoutAction.jsp">로그아웃</a></li>
				</ul>	
			</li>
			</ul>
			<%
				}
			%>
		</div>
	</nav>
	<div class="container bootstrap snippet">
		<div class="row">
			<div class="col-xs-12">
				<div class="portlet portlet-default">
					<div class="portlet-heading">
						<div class="portlet-title">
							<h4><i class="fa fa-circle text-green"></i>실시간 채팅창</h4>
						</div>
						<div class="clearfix"></div>
					</div>
					<div id="chat" class="panel-collapse collapse in">
						<div id="chatList" class="portlet-body chat-widget" style="overflow-y:auto; width:auto; height:600px;"></div>
					</div>
					<div class="portlet-footer">
						<div class="row" style="height:90px;">
							<div class="form-group col-xs-10">
							<!--  -----------------수정 --------------------------------- -->
								<textarea style="height:80px;" id="chatContent" class="form-control" placeholder="메시지를 입력하세요" maxlength="100"
								onkeydown="if(event.keyCode==13){ if(!event.shiftKey){ event.preventDefault(); submitFunction();}}"></textarea>
							<!--  -----------------수정 --------------------------------- -->
							</div>
							<div class="form-group col-xs-2">
								<button type="button" class="btn btn-light pull-right" onclick="submitFunction();">전송 </button>
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
		<strong>데이터베이스 오류가 발생했습니다</strong>
	</div>
	<%
		String messageContent = null;
		if (session.getAttribute("messageContent") != null) {
			messageContent = (String) session.getAttribute("messageContent");
		}
		String messageType = null;
		if (session.getAttribute("messageType") != null) {
			messageType = (String) session.getAttribute("messageType");
		}
		if (messageContent != null) {
	%>
	<div class="modal fade" id="messageModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="vertical-alignment-helper">
			<div class="modal-dialog vertical-align-center">
				<div
					class="modal-content <%if (messageType.equals("오류 메시지"))
					out.println("panel-warning");
				else
					out.println("panel-success");%>">
					<div class="modal-header panel-heading">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times</span> <span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">
							<%=messageType%>
						</h4>
					</div>
					<div class="modal-body">
						<%=messageContent%>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-light" data-dismiss="modal">확인</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$('#messageModal').modal("show");
		
	</script>
	<%
		session.removeAttribute("messageContent");
			session.removeAttribute("messageType");
		}
	%>
	<script type="text/javascript">
		$(document).ready(function() {
			getUnread();
			chatListFunction('0');
			getInfiniteChat();
			getInfiniteUnread();
		});
	</script>
</body>
</html>