<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/layout/header.jsp"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<body>


  <!-- Page Content -->
  <div class="container" id="body">
  <div class="left-box">
	<h1>모든 메신저를 한 곳에서, 메신저 통합 플랫폼</h1>
    <h3>고객을 위한 최고의 채팅 솔루션</h3>
    <br>
    <div class="row mb-4">
      <div class="col-md-8 mainbtn">
      <!-- 회원가입 페이지 링크-->

			<!-- 인증 했을경우 상담데스크로 -->
			<c:if test="${managerConfirm eq 1}">
				<a class="btn btn-lg btn-secondary btn-block" style="width: 340px;"href="/chat/main">사용하러가기</a>
			</c:if>
			<!-- 인증  안했을경우 모달 호출 -->
			<c:if test="${managerConfirm eq 0}">
				<a class="btn btn-lg btn-secondary btn-block" style="width: 340px;" data-toggle="modal" href="#mainModal">사용하러가기</a>
			</c:if>
			<!-- 로그인 안했을 경우 모달 호출 -->
			<c:if test="${not login}">
				<a class="btn btn-lg btn-secondary btn-block" style="width: 340px;" data-toggle="modal" href="#loginModal">사용하러가기</a>
			</c:if>

      </div>
      <div class="col-md-12" style="margin-top: 20px;">
        <p>안녕하세요. 최고의 기업들이 선택하는 채팅솔루션 SemiChat에 <br>오신걸 환영합니다!
        SemiChat은 다양한 채팅 메신저 및 상담 채널별로
        각각 관리할 수 있습니다.
        </p>
      </div>
    </div>
    </div>
	<div id="carouselExampleIndicators" class="carousel slide col-md-7 right-box" data-ride="carousel">
      <ol class="carousel-indicators">
        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner" role="listbox">
        <!-- Slide One - Set the background image for this slide in the line below -->
        <div class="carousel-item active" style="background-image: url('/image/carousel1.png')">
        </div>
        <!-- Slide Two - Set the background image for this slide in the line below -->
        <div class="carousel-item" style="background-image: url('/image/carousel2.png')">
        </div>
        <!-- Slide Three - Set the background image for this slide in the line below -->
        <div class="carousel-item" style="background-image: url('/image/carousel3.png')">
        </div>
      </div>
      <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div>
    

    <!-- Call to Action Section -->

	<div class="modal fade" id="mainModal">
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

<!-- 로그인 필요 모달 -->
<div class="modal fade" id="loginModal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">오류 메시지</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
     
      <div class="modal-body content">
        	로그인 후 이용 가능합니다.
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="submit" class="btn btn-danger" data-dismiss="modal">확인</button>
      </div>

    </div>
  </div>
</div>



  </div>
  <!-- /.container -->

  
<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
