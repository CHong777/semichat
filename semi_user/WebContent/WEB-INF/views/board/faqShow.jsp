<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/layout/header_faq.jsp"/> <!-- 헤더 버그 수정해야됨 -현석 2019-12-04 -->

<jsp:include page="/WEB-INF/views/layout/header_board.jsp"/>

<!-- Bootstrap 3.3.2 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<!-- font awssome 버튼 화살표 가져옴 -->
<head>
  <!-- ... -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
  <!-- ... -->
</head>

<br>
<style type="text/css">

#faq {
   background-color: #4ea1d3;
   border-color: #4ea1d3;
   color: white;
}

</style>

<div class="container" style="margin-bottom: 88px;">
<div class="dropdown">
  <button class="btn btn-default" style="width: 838px; text-align: left; position: absolute; left: 126px;" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
    	Q1 - 채팅 플랫폼을 추가할 수 있나요? <i class="fas fa-angle-down" style="float:right"></i>
    <span class=""></span>
  </button>
  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
    <li role="presentation" style="width: 838px; text-align: left; position: absolute; left: 1px;" >채팅 플랫폼은 기본으로 정해진 것만 사용할 수 있습니다. 채팅 플랫폼을 새롭게 추가하고 싶다면 매니저에게 문의하시기 바랍니다. </li>

  </ul>
</div>
</div><!-- 컨테이너 끝 -->

<div class="container" style="margin-bottom: 88px;">
<div class="dropdown">
  <button class="btn btn-default" style="width: 838px; text-align: left; position: absolute; left: 126px;" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
    	Q2 - 차단한 고객의 블랙리스트를 볼 수 있나요? <i class="fas fa-angle-down" style="float:right"></i>
    <span class=""></span>
  </button>
  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
    <li role="presentation" style="width: 838px; text-align: left; position: absolute; left: 1px;">고객을 블랙리스트로 설정하면 프로필 선택란에서 보이지 않습니다. 하지만 차단한 고객의 블랙리스트를 확인할 수는 없습니다.</li>

  </ul>
</div>
</div><!-- 컨테이너 끝 -->

<div class="container" style="margin-bottom: 88px;">
<div class="dropdown">
  <button class="btn btn-default" style="width: 838px; text-align: left; position: absolute; left: 126px;" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
    	Q3 - 차단한 고객을 차단 해제할 수 있나요?  <i class="fas fa-angle-down" style="float:right"></i>
    <span class=""></span>
  </button>
  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
    <li role="presentation" style="width: 838px; text-align: left; position: absolute; left: 1px;">차단한 고객을 다시 차단 해제할 수 없습니다. 고객을 차단할 시 신중히 생각하고 차단을 하시기 바랍니다.</li>

  </ul>
</div>
</div><!-- 컨테이너 끝 -->

<div class="container" style="margin-bottom: 88px;">
<div class="dropdown">
  <button class="btn btn-default" style="width: 838px; text-align: left; position: absolute; left: 126px;" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
    	Q4 - 이전에 상담했던 고객을 알 수 있나요?<i class="fas fa-angle-down" style="float:right"></i>
    <span class=""></span>
  </button>
  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
    <li role="presentation" style="width: 838px; text-align: left; position: absolute; left: 1px;">이전에 상담했던 고객의 정보는 별칭을 등록하여 알아볼 수 있게 할 수 있습니다. 또 고객 정보란을 통해 이전에 상담했던 상담원과 상담내용을 알 수 있습니다.</li>

  </ul>
</div>
</div><!-- 컨테이너 끝 -->

<div class="container" style="margin-bottom: 88px;">
<div class="dropdown">
  <button class="btn btn-default" style="width: 838px; text-align: left; position: absolute; left: 126px;" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
    	Q5 - 알림 설정은 어떻게 하나요? <i class="fas fa-angle-down" style="float:right"></i>
    <span class=""></span>
  </button>
  <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
    <li role="presentation" style="width: 838px; text-align: left; position: absolute; left: 1px;">채팅창 왼쪽 하단 부분에 시계 모양 아이콘을 클릭하면 답장 알림 설정을 사용할 수 있습니다. 채팅 후 안끄고 다른 작업시 시간을 설정하여 팝업으로 알림을 설정할 수 있습니다. </li>

  </ul>
</div>
</div><!-- 컨테이너 끝 -->







	

    


  

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>    