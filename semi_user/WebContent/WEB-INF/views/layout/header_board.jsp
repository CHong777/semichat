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
   
   $("#board").click(function() {
    $(location).attr("href", "/notice/list");
   });
   
   $("#faq").click(function() {
       $(location).attr("href", "/board/faq");
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
<th><a class="btn btn-lg btn-secondary btn-block b-btn" id = "board" style="left: 130px; color: white;">공지사항</a></th>
<th><a class="btn btn-lg btn-secondary btn-block b-btn" id = "inqButton" style="left: 140px; color: white;">문의사항</a></th>
<th><a class="btn btn-lg btn-secondary btn-block b-btn" id = "faq" style="left: 150px; color: white;">FAQ</a></th>
<th><a class="btn btn-lg btn-secondary btn-block b-btn" id = "use" style="left: 160px; color: white;">이용약관</a></th>
</tr>
</table>
</div>
</div>





          