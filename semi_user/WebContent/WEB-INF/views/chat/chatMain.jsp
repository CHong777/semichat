<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.ChatProfile"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="/WEB-INF/views/layout/header.jsp"/>


<!-- chatDesk 전용 js -->
<script src="/resources/js/chat.js"></script>
<style type="text/css">

/* 채팅 프로필 리스트 by현석 2019-11-25 */
.left{
   background-color : lightskyblue;
   padding:2%;
}
/* chatinfo by 유진 2019-11-28 */
.right{
   background-color : lightskyblue;
}

</style>

	<div style="padding:30px 30px;">
		<div class="left" style="background-color : lightskyblue; padding:2%;">
			<jsp:include page="./chatProfile.jsp" />
		</div>
		<div class="center">
			<jsp:include page="./chatDesk.jsp" />
		</div>
		<div class="right">
			<jsp:include page="./chatInfo.jsp" />
		</div>
	</div>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>