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
	#table2 {margin-bottom:150px;}
	
	td {
		padding:5px 10px;
		text-align:center
		}
		
	input[type=text],input[type=number],input[type=password],input[type=email] {
		width:100%;
		}
	
	textarea {
		width:100%;
		height:300px;
		}
	
	td:nth-of-type(1) {
		background-color: black;
		color: white;
		border-bottom: 1px solid white;
		}

	#table2 tr:last-of-type > td {
		background: white;
		border: 1px solid #808080;
		}
		
	input[type=button],input[type=submit] {
		width:100px;
		}
		
	#btnAddFile {
		width:70px;}
	
	input[name=userid] {
		width: 60%
		}
	
	.menu {
		margin-top: 10px;
		td {
			border: 1px solid white;
			}
		}
	
</style>

</head> 
<body>
	<main>
	<%@include file="/WEB-INF/include/menuspdspaging.jsp" %>
	  <h2 class="h2"><b id="mname"></b>자료실 새 글 쓰기 </h2>
	  
	  <form action="/Pds/Write" method="post" enctype="multipart/form-data">
	  <input type="hidden" name="menu_id" value="${map.menu_id}"></input>
	  <input type="hidden" name="nowpage" value="${map.nowpage}"></input>
	   <table id="table2">
	     <tr>
	       <td><span class=red>*</span>제목</td>
	       <td><input type="text" name="title"></td>
	     </tr>
	     <tr>
	       <td><span class=red>*</span>작성자</td>
	       <td><input type="text" name="writer" value="${sessionScope.login.userid}" ></td>
	     </tr>  
	     <tr>
	       <td>내용</td>
	       <td><textarea name="content"></textarea></td>
	     </tr>
	     <tr>
	       <td>파일</td>
	       <td>
	       	 <input type="button" id="btnAddFile" value="파일추가">(최대 100MByte)
	       	 <div id="tdfile">
	       	  <input type="file" name="upfile" class="upfile" value="upfile" multiple><br>
			 </div>	       
	       </td>
	     </tr>
	     <tr>
	       <td colspan="2">
	         <input type="submit" value="추가">
	         <input type="button" value="목록" id="goList">
	       </td>
	     </tr>
	   </table>
	  </form>
	 
	</main>
	
<!-- Javascript 코딩 : client validation --> 
		<script>
	
		// 메뉴제목출력
		const mnameEl     = document.querySelector('#mname')
		let   menunameEl  = document.querySelector('.menu .active')
		
		mnameEl.innerHTML = menunameEl.innerHTML
		
		// 목록으로 이동
		const goListEl    = document.querySelector('#goList') 
		goListEl.onclick  = function() {
			location.href = '/Pds/List?menu_id=${map.menu_id}&nowpage=${map.nowpage}'
		}
		
		// 파일입력창 추가
		const btnAddFileEl = document.querySelector("#btnAddFile")
		const tdfileEl     = document.querySelector("#tdfile") 
		btnAddFileEl.addEventListener('click', function() {
			tdfileEl.innerHTML += '<input type="file" name="upfile" class="upfile" value="upfile" multiple><br>'
		})
		
// 		const btnAddFileEl = document.querySelector("#btnAddFile")
// 		const tdfileEl     = document.querySelector("#tdfile")
// 		let   tag          = '<input type="file" name="upfile" class="upfile" value="upfile" multiple><br>'
// 		let   html         = tdfileEl.innerHTML
// // 		js에서 실행할때 새로 추가된 버튼은 이벤트가 한번만 작동
// // 		해결 : 이벤트를 부모 element에 설정
// 		btnAddFileEl.addEventListener('click', function(e) { 
// 			console.dir(e.target)  // #btnAddFile, .upfile
// 			if(e.target.id == 'btnAddFile') {
// 			html              += tag
// 			tdfileEl.innerHTML = html
// 			}
// 		})
		
		// 입력항목 체크
		
		</script>
</body>
</html>