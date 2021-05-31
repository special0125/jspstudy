<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8");%>
<jsp:include page="../layout/header.jsp">
	<jsp:param value="게시글 작성" name="title"/>
</jsp:include>

<link rel="stylesheet" href="../assets/css/layout.css">
<style>
	.insert_form {
		width: 800px;
		margin: 50px auto;
	}
	.text_box {
		width: 500px;
		height: 50px;
	}
	#content {
	height: 200px;
	}

</style>
<script>
	$(document).ready(function(){
		const f = $('#f');
		const title = $('#title');
		const insert_btn = $('#insert_btn');
		insert_btn.click(function(){
			if (title.val() == '') {
				alert('제목은 필수입니다.');
				title.focus();
				return;
			}
			f.attr('action', '/10_MODEL2/insertBoard.b');
			f.submit();
		})
		const list_btn = $('#list_btn');
		list_btn.click(function(){
			location.href = '/10_MODEL2/selectListBoardPage.b';
		})
		
	})
</script>
<div class="insert_form">
	
	<form id="f" method="post" enctype="multipart/form-data">
		<p class="title">작성자</p>
		<input type="text" id="author" name="author" value="${loginDTO.id}" readonly class="text_box">
		<p class="title">제목</p>
		<input type="text" id="title" name="title" autofocus class="text_box"><br>
		<p class="title">첨부</p>
		<input type="file" id="filename" name="filename">
		<p class="content">내용</p>
		<textarea id="content" name="content" class="text_box"></textarea> <br><br>
		<input type="hidden" name="ip" value="<%=request.getRemoteAddr()%>">
		<input type="button" value="저장하기" id="insert_btn">		
		<input type="reset" value="작성초기화" id="reset_btn">		
		<input type="button" value="목록보기" id="list_btn">		
	</form>
</div>


<%@ include file="../layout/footer.jsp" %>

