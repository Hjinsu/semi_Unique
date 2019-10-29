<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8");%>
    <% response.setContentType("text/html; charset=UTF-8");%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
@import url("css/messagecss.css");
 
</style>
</head>
<body>
	<table border="1">
		<tr>
			<td>보내는 사람</td>
			<td>${message_dto.message_send_id }</td>
		</tr>
		<tr>
			<td>받는 사람</td>
			<td>${message_dto.message_receive_id }</td>
		</tr>
		<tr>
			<td>보낸 날짜</td>
			<td><fmt:formatDate value="${message_dto.message_regdate }" pattern="yyyy-MM-dd"/></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea rows="5" cols="30" readonly="readonly">${message_dto.message_content }</textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="답장" onclick="location.href='whisper.do?command=resend&message_receive_id=${message_dto.message_send_id }'">
				<input type="button" value="삭제" onclick="location.href='whisper.do?command=delete&message_no=${message_dto.message_no }&message_receive_id=${message_dto.message_receive_id }'">
				<input type="button" value="목록으로" onclick="location.href='whisper.do?command=message_box'">
			</td>
		</tr>
	</table>
</body>
</html>