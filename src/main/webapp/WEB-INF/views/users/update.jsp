<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="/img/favicon.png" rel="shortcut icon" type="image/x-icon">
<link href="/css/common.css" rel="stylesheet"/>

<style>

	table {width:100%;}
	
	td {
		padding:5px 10px;
		text-align:center
		}
		
	input[type=text],input[type=number],input[type=password],input[type=email] {
		width:100%;
		}

	td:nth-of-type(1) {
		background-color: black;
		color: white;
		border-bottom: 1px solid white;
		}

	tr:last-child >td {
		background: white;
		border: 1px solid black;
		}
		
	input[type=button],input[type=submit] {
		width:100px;
		}
	
</style>

</head> 
<body>
	<main>
	  <h2>사용자 수정</h2>
	  <form action="/Users/Update" method="post">
	   <table>
	     <tr>
	       <td><span class=red>*</span>아이디</td>
	       <td><input type="text" name="userid" value="${user.userid}" readonly></td>
	     </tr>
	     <tr>
	       <td><span class=red>*</span>현재 비밀번호</td>
	       <td><input type="password" name="oldpwd" id="passwdold"></td>
	     </tr>
	     <tr>
	       <td><span class=red>*</span>새 비밀번호</td>
	       <td><input type="password" name="passwd" id="passwd"></td>
	     </tr>
	     <tr>
	       <td><span class=red>*</span>새 비밀번호 확인</td>
	       <td><input type="password" id="passwd2"></td>
	     </tr>
	     <tr>
	       <td><span class=red>*</span>이름</td>
	       <td><input type="text" name="username" value="${user.username}"></td>
	     </tr>
	     <tr>
	       <td>이메일</td>
	       <td><input type="email" name="email" value="${user.email}"></td>
	     </tr>
	     <tr>
	       <td>포인트</td>
	       <td>${user.upoint}</td>
	     </tr>
	     <tr>
	       <td>가입일</td>
	       <td>${user.regdate}</td>
	     </tr>
	     <tr>
	       <td colspan="2">
	         <input type="submit" value="수정">
	         <input type="button" value="목록" onclick="location.href='/Users/List'">
	       </td>
	     </tr>
	   </table>
	  
	  </form>
	  
	</main>
	
	<script>
	
		const formEl      = document.querySelector('form');
		const useridEl    = document.querySelector('[name="userid"]');
		const passwdoldEl = document.querySelector('#passwdold');
		const passwdEl    = document.querySelector('#passwd');
		const passwd2El   = document.querySelector('#passwd2');
		const usernameEl  = document.querySelector('[name="username"]');
		
		formEl.addEventListener('submit', function (e) {
			
			// 아이디 체크 
			if(useridEl.value.trim() == '') {
				alert('아이디를 입력하세요')
				useridEL.focus();
				e.preventDefault()  // 이벤트 취소
				e.stopPropagation() // 이벤트 버블링 방지
				return;
			}
			
			// 현재비밀번호 체크
			if(passwdoldEl.value.trim() == '') {
				alert('현재 비밀번호를 입력하세요')
				passwdoldEl.focus();
				e.preventDefault()  // 이벤트 취소
				e.stopPropagation() // 이벤트 버블링 방지
				return;
			}
			
			// 현재비밀번호값 == 조회된 비밀번호 확인 체크 
			if(passwdoldEl.value != '${user.passwd}') {
				alert('현재 비밀번호가 일치하지 않습니다')
				passwdoldEl.focus();
				e.preventDefault()  // 이벤트 취소
				e.stopPropagation() // 이벤트 버블링 방지
				return;
			}
			
			// 비밀번호 체크 
			if(passwdEl.value.trim() == '') {
				alert('비밀번호를 입력하세요')
				passwdEl.focus();
				e.preventDefault()  // 이벤트 취소
				e.stopPropagation() // 이벤트 버블링 방지
				return;
			}			
			
			// 비밀번호값 == 비밀번호확인 체크 
			if(passwd2El.value != passwdEL.value) {
				alert('비밀번호가 일치하지 않습니다')
				passwd2El.focus();
				e.preventDefault()  // 이벤트 취소
				e.stopPropagation() // 이벤트 버블링 방지
				return;
			}
			// 이름 체크 
			if(usernameEl.value.trim() == '') {
				alert('이름을 입력하세요')
				usernameEl.focus();
				e.preventDefault()  // 이벤트 취소
				e.stopPropagation() // 이벤트 버블링 방지
				return;
			}
			
		})
	
	
	</script>
</body>
</html>