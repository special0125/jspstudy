<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<h3>일반 forEach 연습</h3>
	<c:forEach var="i" begin="${param.min}" end="${param.max}">
		<font size="${i}">
			폰트 사이즈 : ${i}<br>
		</font>
	</c:forEach>
	<h3>향상 forEach 연습</h3>
	<ul>
		<c:forEach var="food" items="${paramValues.foods}" varStatus="n">
			<li>${n.count}번째 음식 : ${food}</li>
		</c:forEach>
	</ul>
</body>
</html>