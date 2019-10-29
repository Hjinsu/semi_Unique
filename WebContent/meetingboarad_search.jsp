<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>만남게시판</title>
<style type="text/css">
#side {
	position: relative;
	top: 50px;
}

#center {
	position: relative;
	left: 200px;
	top: -150px;
}
</style>



</head>

<body>
	<span style="color: blue; font-size: 30px;">Life Style 
		<span style="color: black; font-size: 20px;"> 
			<span>상단메뉴</span> 
			<span>메뉴1</span>
			<span>메뉴2</span> 
			<span>메뉴3</span> 
			<span>메뉴4</span>
		</span>
	</span>

	<div id="side">
		<p style="color: red; font-size: 20px;">사이드 메뉴</p>
		<table>
			<tr>
				<td>메뉴1</td>
			</tr>
			<tr>
				<td>메뉴2</td>
			</tr>
			<tr>
				<td>메뉴3</td>
			</tr>
			<tr>
				<td>메뉴4</td>
			</tr>
			<tr>
				<td>메뉴5</td>
			</tr>
		</table>
	</div>
	<form action="" method="get">
		<div id="center">
			<table border="1">
				<col width="100">
				<col width="300">
				<col width="300">
				<col width="100">
				<col width="100">

				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>제한조건</th>
					<th>글쓴이</th>
					<th>글작성일</th>
					<th>조회수</th>
				</tr>

				<c:choose>
					<c:when test="${empty titlesearch_list }">
						<tr>
							<td colspan="6">----작성된 글이 없습니다.----</td>
						</tr>
					</c:when>
					<c:otherwise>

						<c:forEach items="${titlesearch_list }">
							<td>${board_list[i].board_no_seq}</td>
							<td>${board_list[i].board_genderlimit}</td>
							<td>${board_list[i].board_writer}</td>
							<td>${board_list[i].board_regdate}</td>
							<td>${board_list[i].board_viewnum}</td>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				
			
			</table>
		</div>
	</form>
</body>
</html>