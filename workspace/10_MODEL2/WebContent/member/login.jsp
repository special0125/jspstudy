<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<% request.setCharacterEncoding("utf-8"); %>
<jsp:include page="../layout/header.jsp">
	<jsp:param value="로그인" name="title"/>
</jsp:include>
<link rel="stylesheet" href="../assets/css/layout.css">
<link rel="stylesheet" href="../assets/css/login.css">
<script>
	$(document).ready(function(){
		const f = $('#f');
		const id = $('#id');
		const pw = $('#pw');
		const id_message = $('#id_message');
		const pw_message = $('#pw_message');
		f.submit(function(event){
			if (id.val() == '') {
				id_message.text('아이디를 입력하세요');
				id.focus();
				event.preventDefault();
				return false;
			}
			else if (pw.val() == '') {
				id_message.text('');
				pw_message.text('비밀번호를 입력하세요');
				pw.focus();
				event.preventDefault();
				return false;
			}
		})
	})
</script>

<div class="login_form">
	<form id="f" method="post">
		<input type="text" name="id" id="id" placeholder="ID"><br>
		<span class="message" id="id_message"></span><br>
		<input type="password" name="pw" id="pw" placeholder="Password"><br>
		<span class="message" id="pw_message"></span><br>
		<button id="login_btn">로그인</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp" %>