<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			setTimeout(function(){
				location.href = '/ServerProgram3/listBoard.do';
			}, 300);
		})
	</script>
</head>
<body>
<h1>3초후 게시판으로 이동합니다.</h1>
</body>
</html>