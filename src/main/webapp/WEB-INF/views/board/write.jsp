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
	<%@include file="/WEB-INF/include/menus.jsp" %>
	  <h2>새 글 쓰기 [${menu_name} 게시판]</h2>
	  
	  <form action="/Board/Write" method="post">
	  <input type="hidden" name="menu_id" value="${menu_id}"></input>
	   <table id="table2">
	     <tr>
	       <td><span class=red>*</span>제목</td>
	       <td><input type="text" name="title"></td>
	     </tr>
	     <tr>
	       <td><span class=red>*</span>작성자</td>
	       <td><input type="text" name="writer" value="${sessionScope.login.userid}" readonly></td>
	     </tr>  
	     <tr>
	       <td>내용</td>
	       <td><textarea name="content"></textarea></td>
	     </tr>
	     <tr>
	       <td colspan="2">
	         <input type="submit" value="추가">
	         <input type="button" value="목록"
	          onclick="location.href='/Board/List?menu_id=${menu_id}'">
	       </td>
	     </tr>
	   </table>
	  </form>
	 
	</main>
	
<!-- javascript -->
	
</body>
</html>