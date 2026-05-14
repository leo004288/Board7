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
		text-align:center;
		}
	
	td:nth-of-type(1) {
		background-color: black;
		color: white;
		border-bottom: 1px solid white;
		}
		
	input[type=text],input[type=number],input[type=password],input[type=email] {
		width:100%;
		}
		
	input[type=button],input[type=submit] {
		width:100px;
		}
		
	input[name=userid] {
		width: 60%
		}
	
	#table1 {
		margin-bottom: 150px;
		td {
			&:nth-of-type(1){
				width: 150px;
				background-color: black;
				color: white;
				}
			&:nth-of-type(2){
				width: 150px;
				background-color: white;
				color: black;
				}
			&:nth-of-type(3){
				width: 150px;
				background-color: black;
				color: white;
				border-bottom: 1px solid white;
				}
			&:nth-of-type(4){
				width: 150px;
				background-color: white;
				color: black;
				}
			}
		}
	
	#table1 tr:last-of-type > td {
		background: white;
		border: 1px solid #808080;
		}
	
	#table1 tr:nth-of-type(3) td:nth-of-type(2) {
		text-align:left;
		}
	
	#table1 tr:nth-of-type(4) {
		height:400px;
		td:nth-of-type(2) {
			text-align: left;
			vertical-align: baseline;
			}
		}
	
	.menu {
		margin-top: 10px;
		td {
			border: 1px solid white;
			}
		}
	
	textarea {
		width:100%;
		height:400px;
		}
		
/* 	input[name=title], textarea { */
/* 		padding: 5px; */
/* 		} */
	
</style>

</head> 
<body>
	<main>
	<%@include file="/WEB-INF/include/menus.jsp" %>
	  <h2>게시글 수정 [${menu_name} 게시판]</h2>
	  
	  <form action="/Board/Update?idx=${board.idx}" method="post">
	  <input type="hidden" name="menu_id" value="${menu_id}"></input>
	   <table id="table1">
	     <tr>
	     	<td>글번호</td>
	     	<td>${board.idx}</td>
	     	<td>조회수</td>
	     	<td>${board.hit}</td>
	     </tr> 
	     <tr>
	     	<td>작성자</td>
	     	<td>${board.writer}</td>
	     	<td>작성일</td>
	     	<td>${board.regdate}</td>
	     </tr> 
	     <tr>
	       <td><span class=red>*</span>제목</td>
	       <td colspan="3"><input type="text" name="title" value="${board.title}"></td>
	     </tr>  
	     <tr>
	       <td>내용</td>
	       <td colspan="3"><textarea name="content">${board.content}</textarea></td>
	     </tr>
	     <tr>
	       <td colspan="4">
	         <input type="submit" value="수정">
	         <input type="button" value="목록" onclick="location.href='/Board/List?menu_id=${menu_id}'">
	       </td>
	     </tr>
	   </table>
	  </form>
	 
	</main>
	
<!-- javascript -->
	
</body>
</html>