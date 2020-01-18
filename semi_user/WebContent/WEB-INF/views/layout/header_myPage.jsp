<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!-- 게시판 메뉴 바 --> 

    
<script type="text/javascript">
$(document).ready(function() {
   
   $("#inqButton").click(function() {
        $(location).attr("href", "/inquiry/list");
      });
   
   $("#use").click(function() {
        $(location).attr("href", "/board/service");
      });
   });
</script>
    
    
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
<tr>
<th><a class="btn btn-lg btn-secondary btn-block b-btn" id = "btnMyWriting" style="left: 130px; color: white;" href="/mypage/boardlist">작성 글 관리</a></th>
<th><a class="btn btn-lg btn-secondary btn-block b-btn" id = "btnMyClient" style="left: 140px; color: white;" href="/mypage/myclient">고객리스트 관리</a></th>
<th><a class="btn btn-lg btn-secondary btn-block b-btn" id = "btnmypageInfo" style="left: 150px;" href="/mypage/modifyinfo">내 정보 관리</a></th>
<th><a class="btn btn-lg btn-secondary btn-block b-btn" id = "btnWithdrawal" style="left: 160px; color: white;" href="/mypage/withdrawal">회원탈퇴 신청</a></th>
</tr>
</table>
</div>
</div>





          