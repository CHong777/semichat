<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/layout/header.jsp" />

<style type="text/css">
/* 왼쪽 nav 바 */
.left {
	float: left;
	width: 30%;
	height: 500px;
	background: #ffffff;
}

/* 오른쪽 내용 bar */
.right {
	float: right;
	width: 65%;
	height: 500px;
	background: #ffffff;
}

.guide {
/* 	해당 속성을 사용하면 마우스를 갖다댔을때 손모양이 나온다 */
	cursor : pointer;
}

</style>

<script type="text/javascript">
$(document).ready(function() {

	$(".guide_content").hide();

// 	가이드 1 아이디 선택자를 가진 div태그 선택시
	$("#guide_1").click(function() {
		
// 		guide_content 클래스를 가진 모든 태그들을 화면에서 숨긴다
		$(".guide_content").hide();
// 		guide_content1 아이디를 가진 태그를 화면에 보여준다
		$("#guide_content1").show();
		
// 		guide_border라는 이름의 클래스의 CSS border속성의 값을 제거한다
		$(".guide_border").css("border","none");
		
// 		클릭한 선택자($this)의 css border-bottom값을 파란색으로 넣어준다
		$(this).css("border-bottom","1px solid blue");
	});
	
	//이하동문..
	$("#guide_2").click(function() {
		
		$(".guide_content").hide();
		$("#guide_content2").show();
		
		$(".guide_border").css("border","none");
		$(this).css("border-bottom","1px solid blue");
		console.log($(this));
	});
	
	$("#guide_3").click(function() {
		
		$(".guide_content").hide();
		$("#guide_content3").show();
		
		$(".guide_border").css("border","none");
		$(this).css("border-bottom","1px solid blue");
		console.log($(this));
	});
	
	$("#guide_4").click(function() {
		
		$(".guide_content").hide();
		$("#guide_content4").show();
		
		$(".guide_border").css("border","none");
		$(this).css("border-bottom","1px solid blue");
		
		console.log($(this));
	});
	
	$("#guide_5").click(function() {
		
		$(".guide_content").hide();
		$("#guide_content5").show();
		
		$(".guide_border").css("border","none");
		$(this).css("border-bottom","1px solid blue");
		console.log($(this));
	});
	
	
});
</script>

<div class="container">
<div class="left">
<!-- fit-content는 내용의 요소에 맞게 width사이즈를 준다  -->
	<div id="guide_1" class="guide_border" style="height: auto; width: fit-content;"><a class="guide">솔루션 소개</a></div><br><br>
	<div id="guide_2" class="guide_border" style="height: auto; width: fit-content;"><a class="guide">솔루션 기능 및 장점</a></div><br><br>
	<div id="guide_3" class="guide_border" style="height: auto; width: fit-content;"><a class="guide">이용요금</a></div><br><br>
	<div id="guide_4" class="guide_border" style="height: auto; width: fit-content;"><a class="guide">고객사</a></div><br><br>
	<div id="guide_5" class="guide_border" style="height: auto; width: fit-content;"><a class="guide">솔루션 사용 가이드</a></div><br><br>
	</div>

<div class="right">

<div id="guide_content1" class="guide_content">
<img src="/image/guide_1.jpg" width="722px;" height="500px;">
</div>

<div id="guide_content2" class="guide_content">
<img src="/image/guide_2.jpg" width="722px;" height="500px;">
</div>

<div id="guide_content3" class="guide_content">
<img src="/image/guide_3.jpg" width="722px;" height="500px;">
</div>

<div id="guide_content4" class="guide_content">
<img src="/image/guide_4.jpg" width="722px;" height="500px;">
</div>

<div id="guide_content5" class="guide_content">
<img src="/image/guide_5.jpg" width="722px;" height="250px;">
<p></p>
<p>1. 모바일 메신저처럼 실시간/비실시간으로 대화하는 하이브리드 톡 상담 서비스
직관적인 UI와 앱/SMS 푸시 알림, 이모티콘 등 모바일 중심의 다양한 편의 기능
문의 폭주 시, 접수 톡이나 타 채널 모드 전환으로 안정적인 고객 채팅 서비스 운영
결제 페이지 에러 대응이나, 특정 고객서비스를 위한 목적성 플로팅 배너 설정</p>
<p>2. 모바일 메신저처럼 실시간/비실시간으로 대화하는 하이브리드 톡 상담 서비스
직관적인 UI와 앱/SMS 푸시 알림, 이모티콘 등 모바일 중심의 다양한 편의 기능
문의 폭주 시, 접수 톡이나 타 채널 모드 전환으로 안정적인 고객 채팅 서비스 운영
결제 페이지 에러 대응이나, 특정 고객서비스를 위한 목적성 플로팅 배너 설정</p>
</div>


</div>	

</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp" /> 



























