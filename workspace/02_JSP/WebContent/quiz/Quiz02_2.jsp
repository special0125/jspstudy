<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 예외가 발생하면 이동할 Quiz02_error.jsp 페이지를 등록 --%>
<%@ page errorPage="Quiz02_error.jsp" %>   

<%--
<!DOCTYPE html>
내가 한거 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		request.setCharacterEncoding("utf-8");
		String strAge = request.getParameter("age");
		int age = strAge.isEmpty() ? 0 : Integer.parseInt(strAge);
	%>
	<% if (age < 20) { %>
	<h1>미성년자</h1>
	<p>당신의 나이는 <%=age%>살 이므로 주류 구매가 불가능합니다.</p>
	<a href="/02_JSP/quiz/Quiz02_1.jsp">처음으로 이동</a>
	<% } else { %>
	<h1>성인</h1>
	<p>당신의 나이는 <%=age%>살 이므로 주류 구매가 가능합니다.</p>
	<a href="/02_JSP/quiz/Quiz02_1.jsp">처음으로 이동</a>
	<% } %>
	

</body>
</html> --%>

<%
	request.setCharacterEncoding("utf-8");
	int age = request.getParameter("age").isEmpty() ? 0 : Integer.parseInt(request.getParameter("age"));
	if (age < 20) {
		// forward (request를 그대로 전달한다.)
		request.getRequestDispatcher("Quiz02_kid.jsp").forward(request, response);
	} else {
		request.getRequestDispatcher("Quiz02_adult.jsp").forward(request, response);
	}
%>