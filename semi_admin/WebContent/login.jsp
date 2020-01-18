<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<script type="text/javascript">
$(document).ready(function(){
	//페이지 첫 접속시 입력창으로 포커스 이동
	$("input").eq(0).focus();
	
	//패스워드 입력 창에서 엔터 입력 시 form submit
	$("input").eq(1).keydown(function(key) {
		if(key.keyCode == 13) {
			$(this).parents("form").submit();
		}
	})

})
</script>

<form action="/login/login" method="post" class="form-horizontal" id ="loginForm" style="position:relative; left:500px">
	<div class="form-group">
		<label for="adminId" class="col-sm-3 col-sm-offset-1 control-label">아이디</label>
		<div class="col-sm-5">
		<input type="text" id="adminId" name="adminId" class="form-control" placeholder="AdminID"/>
		</div>
	</div>
	<div class="form-group">
		<label for="adminPw" class="col-sm-3 col-sm-offset-1 control-label">패스워드</label>
		<div class="col-sm-5">
		<input type="password" id="adminPw" name="adminPw" class="form-control" placeholder="Password"/>
		</div>
	</div>

	<div class="col-sm-offset-5">
		<button type="submit" id="btnLogin" class="btn btn-primary"
			width="20%" style="position: relative; left: 300px;">로그인</button>
	</div>
</form>


<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>