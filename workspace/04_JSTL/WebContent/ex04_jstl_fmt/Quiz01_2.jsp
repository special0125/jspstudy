<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<%--
		문제.
		
		1~7 : 미취학아동
		8~13 : 초등학생
		14~16 : 중학생
		17~19 : 고등학생
		20~100 : 성인
		이외 : 오류
	 --%>
	 <c:choose>
	 	<c:when test="${age - 1 ge 0 and age - 7 le 0}">
	 		<c:set var="result" value="미취학아동" />
	 	</c:when>
	 	<c:when test="${age - 13 le 0}">
	 		<c:set var="result" value="초등학생" />
	 	</c:when>
	 	<c:when test="${age - 16 le 0}">
	 		<c:set var="result" value="중학생" />
	 	</c:when>
	 	<c:when test="${age - 19 le 0}">
	 		<c:set var="result" value="고등학생" />
	 	</c:when>
	 	<c:when test="${age - 100 le 0}">
	 		<c:set var="result" value="성인" />
	 	</c:when>
	 	<c:otherwise>
	 		<c:set var="result" value="오류" />
	 	</c:otherwise>
	 </c:choose>
	 
	<h3>${result}</h3>
	
</body>
</html>