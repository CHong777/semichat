<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.2.4.min.js" charset="utf-8"></script>


<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.2.4.min.js" charset="utf-8"></script>

<!-- Bootstrap 3.3.2 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">
.mainframe {
	padding: 0;
	margin-top: 50px;
	background: #f7f7f7;
}

.logoframe {
	height: 100px;
	padding-top: 30px;
	padding-bottom: 20px;
	border-bottom: 2px solid #e9e9e9;
}

.memberframe, .sendframe {
	padding: 30px;
	border-bottom: 2px solid #e9e9e9;
	text-align: center;
}

.slogan {
	font-size: 20px;
}

.profileplace {
	height: 200px;
	margin: 0;
	padding: 0;
}

.profileplace img {
	height: 155px;
}

ul{
	margin-top: 63px;
    margin-bottom: 10px;
    text-align: center;
    list-style: none;
    text-decoration: none;
}

#m_photo{
 height: 200px;
    padding: 29px;
    position: relative;
    left: 17px;
    top: 46px;
}
@media ( max-width :990px) {
	#dash1, #dash2 {
		visibility: hidden;
	}
}
</style>
<body>

	
		<div class="container">
		<img class="img-responsive center-block" id=m_photo
									name="m_photo" src="/image/profile.png">
					<div>
						<ul>
								<li>이름</li>
								<li><h3>${counselorProfile.counselorName}</h3></li>
							
								<li>아이디</li>
								<li><h3>${counselorProfile.counselorId}</h3></li>
								
								<li>닉네임</li>
								<li><h3>${counselorProfile.counselorNickname}</h3></li>
								
								<li>이메일</li>
								<li><h3>${counselorProfile.counselorEmail}</h3></li>
								
								<li>휴대폰 번호</li>
								<li><h3>${counselorProfile.counselorPhonenumber}</h3></li>
								
								<li>가입 날짜</li>
								<li><h3>${counselorProfile.counselorSigndate}</h3></li>
								
								<li>등급</li>
								<li><h3>${counselorProfile.counselorPosition}</h3></li>
								
								<li>기업번호</li>
								<li><h3>${counselorProfile.corporationNo}</h3></li>
						</ul>
					</div>
		</div>
	<!-- container -->