<%@page import="com.life.dto.member_dto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8");%>
    <% response.setContentType("text/html; charset=UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
@import url("css/messagecss.css");
 
</style>
<%
member_dto member_dto = (member_dto)session.getAttribute("member_dto");
%>
</head>
<body>
<form action="whisper.do?command=insertres" method="post">
<table border="1">
		<tr>
			<td>보내는 사람</td>
			<td><input type="text" name="message_send_id" value="<%=member_dto.getMember_id() %>" readonly="readonly" style="border: none;"> </td>
		</tr>
		<tr>
			<td>받는 사람</td>
			<td>
				<input type="text" name="message_receive_id" value="">
			</td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea rows="5" cols="30" name="message_content"></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="보내기">
				<input type="button" value="취소" onclick="location.href='whisper.do?command=message_box'">
			</td>
		</tr>
	</table>
</form>
	
</body>
</html>