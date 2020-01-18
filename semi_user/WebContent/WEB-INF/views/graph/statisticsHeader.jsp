<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/layout/header.jsp"/>    
    
<!-- JQ-PLOT -->
<link rel="stylesheet" type="text/css" href="/resources/jqplot/jquery.jqplot.css" />
<script type="text/javascript" src="/resources/jqplot/jquery.jqplot.js"></script>
<script type="text/javascript" src="/resources/jqplot/jqplot.categoryAxisRenderer.js"/></script>
<script type="text/javascript" src="/resources/jqplot/jqplot.barRenderer.js"></script>

<!-- 게시판 메뉴 바 -->     
<style type="text/css">
#b-menu {
	margin-top: 70px;
}

#b-menu a{
	position: relative;
	width: 200px;
}
</style>

<div class="container">

<div id="b-menu">
<table>
<tbody><tr>
<th><a id="month" class="btn btn-lg btn-secondary btn-block b-btn" style="left: 200px;" href="/statistics/monthly">월별 통계</a></th>
<th><a id="day" class="btn btn-lg btn-secondary btn-block b-btn" style="left: 250px;" href="/statistics/daily">일별 통계</a></th>
<th><a id="keyword" class="btn btn-lg btn-secondary btn-block b-btn" style="left: 300px;" href="/statistics/keyword">키워드별 통계</a></th>
</tr>
</tbody>
</table>
</div>

</div>






          


