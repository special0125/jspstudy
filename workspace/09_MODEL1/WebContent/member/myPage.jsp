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
			const pw_btn = $('#pw_btn');
			pw_btn.on('click', function(){
				alert('비밀번호 변경 페이지로 이동합니다.');
				location.href = 'pwPage.jsp';
			})
			
			<%-- 내가 작성한 코드 --%>
			<%--
			const change_btn = $('#change_btn');
			const name = $('#name');
			const email = $('#email');
			const f = $('#f');
			change_btn.on('click', function(event){
				
				if (name.val() == '${loginDTO.name}' || name.val() == '') {
					alert('이름을 확인하세요.');
					event.preventDefault();
					return false;
				}else if (email.val() == ''){
					alert('이메일은 필수 입니다.')
					event.preventDefault();
					return false;
				}else{					
					alert('정보를 수정합니다.');
					f.attr('action', 'updateMember.jsp');
					f.submit();
				}
			})
			--%>

			const change_btn = $('#change_btn');
			const email = $('#email');
			const name = $('#name');
			const f = $('#f');
			change_btn.on('click', function(){
				if(name.val() == '${loginDTO.name}' && email.val() == '${loginDTO.email}') {
					alert('수정할 내용이 없습니다.');
					return;
				}else if (email.val() == ''){
					alert('이메일은 필수입니다.');
					event.preventDefault();
					return false;
				}
				f.attr('action', 'updateMember.jsp');
				f.submit();
			})
			
			const return_btn = $('#return_btn');
			return_btn.on('click', function(){
				location.href = '/09_MODEL1/index.jsp';
			})
			
			const leave_btn = $('#leave_btn');
			leave_btn.on('click', function(){
				if (confirm('정말로 탈퇴하시겠습니까?')) {
					location.href = 'leave.jsp';
				}
			})
			
			
			
		})
	</script>
	<style>
		.container {
			width: 500px;
			margin: 100px auto;
			text-align: center;
		}
		table {
			width: 100%;
		}
	</style>
</head>
<body>
	<div class="container">
		<form id="f" method="post">
			<table border="1">
				<thead>
					<tr>
						<td colspan="2">${loginDTO.name}님 정보</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>회원번호</td>
						<td>${loginDTO.no}</td>
					</tr>
					<tr>
						<td>아이디</td>
						<td>${loginDTO.id}</td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="button" value="비밀번호 변경" id="pw_btn"></td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" id="name" name="name" value="${loginDTO.name}"></td>
					</tr>
					<tr>
						<td>이메일</td>
						<td><input type="text"id="email" name="email" value="${loginDTO.email}"></td>
					</tr>
					<tr>
						<td>가입일</td>
						<td>${loginDTO.regdate}</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<input type="button" value="개인정보 수정하기" id="change_btn">
							<input type="button" value="되돌아가기" id="return_btn">
							<input type="button" value="회원탈퇴" id="leave_btn">
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
</body>
</html>