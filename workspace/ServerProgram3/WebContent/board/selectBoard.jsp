<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			$('#list_btn').click(function(){
				location.href = '/ServerProgram3/listBoard.do';
			})
			
			$('#delete_btn').click(function(){
				if(confirm('게시글을 삭제할까요?')) {
					location.href = 'ServerProgram3/deleteBoard.do?no=' + ${boardDTO.no};
				}
			})
		})
	</script>
</head>
<body>
	<h1>${boardDTO.no}번 게시글</h1>
	작성자: ${boardDTO.author}<br><br>
	작성일: ${boardDTO.postdate}<br><br>
	작성IP: ${boardDTO.ip}<br><br>
	제목: ${boardDTO.title}<br><br>
	내용<br><br>
	${boardDTO.content}<br><br>
	
	<input type="button" value="삭제하기" id="delete_btn">
	<input type="button" value="목록보기" id="list_btn"><br>
	<hr>
	<form action="/ServerProgram3/insertReply.do">
		<input type="hidden" name="boardNo" value="${boardDTO.no}">
		<input type="text" name="content" placeholder="댓글을 입력하세요."><br>
		<input type="text" name="author" placeholder="작성자">
		<button>작성</button>
	</form>
	<br><br>
	<table>
		<thead>
			<tr>
				<td>${replyDTO.content}</td>
				<td>${replyDTO.author}</td>
				<td>${replyDTO.ip}</td>
				<td>${replyDTO.postdate}</td>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	
	
	
	
	
</body>
</html>