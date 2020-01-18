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
  <title>: : SEMI CHAT ADMIN : :</title>
  <!-- Bootstrap core CSS -->
  <link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">  

  <!-- Custom styles for this template -->
  <link href="/resources/css/modern-business.css" rel="stylesheet">
  <link href="/resources/css/custom.css" rel="stylesheet">

</head>
<body>
	<header>
		<c:choose>
			<c:when test="${not login}">
				<nav id="nav" class="navbar navbar-expand-lg">
					<div class="container">
						<img src="/image/chat.png" width="40px" height="40px"> <a
							class="navbar-brand col-lg-12" href="../login.jsp"
							style="font-size: 40px;">&nbsp;SEMICHAT ADMIN</a>
					</div>
				</nav>
			</c:when>
			<c:when test="${login }">
				<nav id="nav" class="navbar navbar-expand-lg">
				<div class="container">
					<img src="/image/chat.png" width="40px" height="40px"> <a class="navbar-brand" href="/admin/corporationmanage">&nbsp;SEMICHAT ADMIN</a>
					<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive"
						aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarResponsive">
						<ul class="navbar-nav ml-auto">
							<li class="nav-item"><a class="nav-link" href="/admin/corporationmanage">회원관리</a></li>
							<li class="nav-item"><a class="nav-link" href="/admin/boardmanage">게시판관리</a></li>
							<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPortfolio" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> &nbsp;
								<img src="/image/user.png" width="20px" height="20px">
							</a>
							<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownUser">
								<a class="dropdown-item" href="/login/logout">로그아웃</a> 
							</div>
							</li>
						</ul>
					</div>
				</div>
			</nav>
			</c:when>
		</c:choose>
	</header>
	<div id="wrapper">