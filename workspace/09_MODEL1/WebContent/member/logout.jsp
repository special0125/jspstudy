<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 로그아웃
	// session에 저장된 loginDTO를 제거한다.
	session.invalidate();  // 세션 초기화
	response.sendRedirect("/09_MODEL1/index.jsp");


%>