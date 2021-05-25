<%@page import="dao.MemberDAO"%>
<%@page import="dto.MemberDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		.container {
			width: 600px;
			margin: 100px auto;
			text-align: center;
		}
		table {
			border-collapse: collapse;
			width 100%;
		}
		td {
			border: 1px solid black;
		}
	</style>
</head>
<body>
	<%
		// 1. 미션
		// ArrayList에 전체 회원을 담아 오기
		List<MemberDTO> list = MemberDAO.getInstance().selectAll();
		pageContext.setAttribute("list", list);  // EL사용하기
	%>
	<div class="container">
		<table>
			<thead>
				<tr>
					<td>회원번호</td>
					<td>아이디</td>
					<td>비밀번호</td>
					<td>이름</td>
					<td>이메일</td>
					<td>가입일</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="dto" items="${list}">
					<tr>
						<td>${dto.no}</td>
						<td>${dto.id}</td>
						<td>${dto.pw}</td>
						<td>${dto.name}</td>
						<td>${dto.email}</td>
						<td>${dto.regdate}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6">
						<input type="button" value="되돌아가기" onclick="history.back()">
					</td>
				</tr>
		</table>
	</div>
</body>
</html>