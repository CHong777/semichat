<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!-- 게시판 메뉴 바 --> 
    
<style type="text/css">
#b-menu {
	margin-top: 70px;
	position: relative;
    left: 200px;
}

#b-menu a{
	position: relative;
	width: 200px;
}

</style>
<div class="container">
<div id="b-menu">
<table>
<tr>
<th><a id = "counselorShow"class="btn btn-lg btn-secondary btn-block b-btn" style="left: 130px;"href="/manager/counselormanage">상담원 조회</a></th>
<!-- <th><a class="btn btn-lg btn-secondary btn-block b-btn" style="left: 140px;"href="/inquiry/list">문의사항</a></th> -->
<th><a id = "accountManage"class="btn btn-lg btn-secondary btn-block b-btn" style="left: 150px;"href="/manager/accoountmanage">계정관리</a></th>
<!-- <th><a class="btn btn-lg btn-secondary btn-block b-btn" style="left: 160px;"href="/board/service">이용약관</a></th> -->
</tr>
</table>
</div>
</div>