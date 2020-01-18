<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!-- 게시판 메뉴 바 --> 
    
<style type="text/css">
#b-menu {
	margin-top: 70px;
	position:relative;
	left:90px;
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
<th><a id="board" class="btn btn-lg btn-secondary btn-block b-btn" style="left: 130px;"href="/admin/boardmanage">게시글 관리</a></th>
<th><a id="notice" class="btn btn-lg btn-secondary btn-block b-btn" style="left: 140px;"href="/admin/noticemanage">공지사항 관리</a></th>
<th><a id="inquiry" class="btn btn-lg btn-secondary btn-block b-btn" style="left: 150px;"href="/admin/inquirymanage">문의사항 관리</a></th>
</tr>
</table>
</div>
</div>





          