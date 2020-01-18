<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <!-- Bootstrap core JavaScript -->
  <script src="/resources/vendor/jquery/jquery.min.js"></script>
  <script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <title>: : : SEMI CHAT : : :</title>
  <!-- Bootstrap core CSS -->
  <link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- custom.js -->
  <script src="/resources/js/custom.js"></script>
  
  <!-- Custom styles for this template -->
  <link href="/resources/css/modern-business.css" rel="stylesheet">
  <link href="/resources/css/custom.css" rel="stylesheet">
  <link href="/resources/css/chat.css" rel="stylesheet">
  
  <!-- 돋보기 -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<!-- <link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css"> -->
<style type="text/css">
 .modal-backdrop {
   z-index: 1;
}
</style>
</head>
<body>
	<header>
		<c:choose>
			<c:when test="${not login}">
				<nav id="nav" class="navbar navbar-expand-lg">
				<div class="container">
					<img src="/image/chat.png" width="40px" height="40px"> <a class="navbar-brand" href="/main.jsp" style="font-size: 40px;">&nbsp;SEMICHAT</a>
					<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive"
						aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarResponsive">
						<ul class="navbar-nav ml-auto">
							<li class="nav-item"><a class="nav-link" href="/join/join">회원가입</a></li>
							<li class="nav-item"><a class="nav-link" href="/login/login">로그인</a></li>
						</ul>
					</div>
				</div>
			</nav>
			</c:when>
			<c:when test="${login }">
				<nav id="nav" class="navbar navbar-expand-lg">
				<div class="container">
					<img src="/image/chat.png" width="40px" height="40px"> <a class="navbar-brand" href="../main.jsp">&nbsp;SEMICHAT</a>
					<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive"
						aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarResponsive">
						<ul class="navbar-nav ml-auto">
							<li class="nav-item"><a class="nav-link" href="/guide">가이드</a></li>
							
							<!-- 홍철 추가 12/04 기업매니저 승인시만 보이게 -->
							<c:if test="${managerConfirm eq 1}">
								<li class="nav-item"><a id = "chatDesk" class="nav-link" href="/chat/main">상담데스크</a></li>
							</c:if>
							
							<c:if test="${managerConfirm eq 0}">
								<li class="nav-item"><a id = "chatDesk" class="nav-link" data-toggle="modal" href="#managerModal">상담데스크</a></li>
							</c:if>
							<!-- 홍철 추가 12/04 기업매니저 승인시만 보이게 -->
							
							<li class="nav-item"><a class="nav-link" href="/notice/list">게시판</a></li>
							<li class="nav-item"><a class="nav-link" href="/statistics/monthly">통계</a></li>
							
							<!-- 홍철 추가 12/05 포지션이 manager한테만 보이게 -->
								<c:if test="${counselorPosition eq 'manager'}">
									<li class="nav-item"><a class="nav-link" href="/manager/frequentReply">환경설정</a></li>
								</c:if>
								<!-- 홍철 추가 12/05 포지션이 manager한테만 보이게 -->
							
							<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPortfolio" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> &nbsp;
								<img src="/image/user.png" width="20px" height="20px">
							</a>
							<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownUser">
								<a class="dropdown-item" href="/login/logout">로그아웃</a> 
								<a class="dropdown-item" href="/pw/authentication">마이페이지</a> 
								<!-- 홍철 추가 12/04 포지션이 manager한테만 보이게 -->
								<c:if test="${counselorPosition eq 'manager'}">
									<a class="dropdown-item" href="/manager/counselormanage">상담원 관리</a>
								</c:if>
								<!-- 홍철 추가 12/04 포지션이 manager한테만 보이게 -->
							</div>
							</li>
						</ul>
					</div>
				</div>
			</nav>
			</c:when>
		</c:choose>
	</header>
	<div class="modal fade" id="managerModal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">오류 메시지</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
     
      <div class="modal-body content">
        	기업 매니저 승인 후 이용 가능합니다.
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="submit" class="btn btn-danger" data-dismiss="modal">확인</button>
      </div>

    </div>
  </div>
</div>
	<div id="wrapper">