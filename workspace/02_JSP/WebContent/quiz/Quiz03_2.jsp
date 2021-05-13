<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String entertainer = request.getParameter("entertainer");
		
		
	%>
	<form action="/02_JSP/quiz/Quiz03_3.jsp">
		<h3>2. 좋아하는 운동선수는 누구인가요?</h3><br>
		<input type="text" name="player">
		<input type="hidden" name="name" value="<%=name%>">
		<input type="hidden" name="entertainer" value="<%=entertainer%>">
		<button>결과보기</button>
	</form>
</body>
</html>