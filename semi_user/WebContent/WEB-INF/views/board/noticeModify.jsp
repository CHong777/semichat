<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/layout/header.jsp"/>
<jsp:include page="/WEB-INF/views/layout/header_board.jsp"/>

<!-- 스마트 에디터2 라이브러리 -->
<script type="text/javascript"
 src="/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
// <form>이 submit되면
// 스마트 에디터 내용을 <textarea>반영해주는 함수
function submitContents(elClickedObj) {
	// 에디터의 내용이 textarea에 적용된다.
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);

	try {
		elClickedObj.form.submit(); // <form> submit 수행
	} catch(e) {}
}
</script>

<script type="text/javascript">
$(document).ready(function() {
	
	//작성버튼 동작
	$("#btnModify").click(function() {
		console.log("ㅇㄹㄴㅇㄹㄴㅇ11");
		// 스마트에디터의 내용을 <textarea>에 적용
		submitContents( $("#btnModify") );
		// form submit
		$("form").submit();
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	
	//경고 모달 호출 메서드
	function warningModal(content) {
		
		   $(".modal-contents").text(content);
			$("#defaultModal").modal('show');
		}
});
</script>

<div class="container">
<form action="/notice/modify" method="post">
<input type="hidden" name="counselorid" value="${noticeboard.counselorId }" />
<input type="hidden" name="boardno" value="${noticeboard.nBoardNo }" />
<table class="table" style=" width: 80%; margin: 0 auto; margin-top: 10px;">
	<tr><th style="text-align: center"><input type="text" name="boardtitle" value="${noticeboard.nBoardTitle }"/></th></tr>
	<tr><td><textarea id="content" name="content">${noticeboard.nBoardContent }</textarea></td></tr>	
</table>
</form>


<div class="text-center">	
	<button type="button" id="btnUpaate" class="btn btn-info" data-toggle="modal" data-target="#myModal">등록</button>
	<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
</div>
</div><!-- 컨테이너 끝 -->

<div class="modal fade" id="myModal">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">확인 메시지</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body content">
        	글을 수정하시겠습니까?
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <a href="/notice/list"><input type="submit" id="btnModify" class="btn btn-danger" data-dismiss="modal" value="확인"></a>
      </div>

    </div>
  </div>
</div>



<!-- 스마트 에디터 적용 코드 -->
<!-- <textarea>태그에 스마트 에디터의 스킨을 입히는 코드 -->
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content", // 에디터가 적용되는 <textarea>의 id
	sSkinURI: "/resources/se2/SmartEditor2Skin.html", // 에디터 스킨
	fCreator: "createSEditor2"
});
</script>


<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>  