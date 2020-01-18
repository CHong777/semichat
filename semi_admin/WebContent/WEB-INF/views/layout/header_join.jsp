<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!-- 회원가입, 로그인 페이지 header -->
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <!-- Bootstrap core JavaScript -->
  <script src="/resources/vendor/jquery/jquery.min.js"></script>
  <script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  
  <title>: : SEMI CHAT ADMIN : : :</title>
  <!-- Bootstrap core CSS -->
  <link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  
  <!-- Bootstrap 3.3.2 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
  
  <!-- Custom styles for this template -->
  <link href="/resources/css/modern-business.css" rel="stylesheet">
  <link href="/resources/css/custom.css" rel="stylesheet">

</head>
<body>

	<header>
		<c:choose>
			<c:when test="${login}">
				<nav id="nav" class="navbar navbar-expand-lg">
					<div class="container">
						<img src="/image/chat.png" width="40px" height="40px"> <a
							class="navbar-brand col-lg-12" href="#"
							style="font-size: 40px;">&nbsp;SEMICHAT ADMIN</a>
					</div>
				</nav>
			</c:when>
			<c:when test="${not login}">
				<nav id="nav" class="navbar navbar-expand-lg">
					<div class="container">
						<img src="/image/chat.png" width="40px" height="40px"> <a
							class="navbar-brand col-lg-12" href="../main.jsp"
							style="font-size: 40px;">&nbsp;SEMICHAT ADMIN</a>
					</div>
				</nav>
			</c:when>
		</c:choose>
	</header>
	<div id="wrapper">
