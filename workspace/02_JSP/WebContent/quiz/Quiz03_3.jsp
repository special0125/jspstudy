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
		String player = request.getParameter("player");
	%>
	<h3><%=name%> 님의 선호도 조사 결과</h3>
	<ul>
		<li>좋아하는 연예인: <%=entertainer%></li>
		<li>좋아하는 운동선수: <%=player%></li>
	</ul>
	<input type="button" value="처음부터 다시하기" onclick="fn()">
	
	<script>
		function fn() {
			location.href = '/02_JSP/quiz/Quiz03_1.jsp';
		}
	</script>
</body>
</html>