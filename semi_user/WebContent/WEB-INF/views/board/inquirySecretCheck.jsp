<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:choose>
<c:when test="${result eq '0' }">
<input type="password" id="secretpw" name="secretpw" placeholder="비밀번호 입력">
</c:when>
<c:when test="${result ne '0'  }">
</c:when>
</c:choose>