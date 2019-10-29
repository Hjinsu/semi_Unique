<%@page import="com.life.dao.Util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8");%>
    <% response.setContentType("text/html; charset=UTF-8");%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="js/muldel.js"></script>
<body>
<form action="whisper.do" method="post" id="muldelform">
	<input type="hidden" name="command" value="muldel">
	<jsp:useBean id="util" class="com.life.dao.Util"></jsp:useBean>
	<div style="overflow-y:scroll; height: 248px; "> 
	<table border="1">
		<tr>
			<th><input type="checkbox" name="all" onclick="allChk(this.checked)"></th>
			<th>내   용</th>
			<th>보낸사람</th>
			<th>날   짜</th>
		</tr>	
		<c:choose>
				<c:when test="${empty message_list }">
					<tr>
						<td colspan="5" align="center">------받은 쪽지가 없습니다.------</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${message_list }" var="message_dto">
						<tr>
							<td><input type="checkbox" name="chk" value="${message_dto.message_no }"></td>
							<td>
							<c:choose>
								<c:when test="${message_dto.message_read eq 'N'}">
								<img alt="" src="img/message.png" style=" width: 22px; height: 22px;">
									<a href="whisper.do?command=selectone&message_no=${message_dto.message_no}">
										<jsp:setProperty property="messageContent" name="util" value="${message_dto.message_content }"/>
										<jsp:getProperty property="messageContent" name="util"/>
									</a>
								</c:when>
								<c:otherwise>
								<a href="whisper.do?command=selectone&message_no=${message_dto.message_no}">
										<jsp:setProperty property="messageContent" name="util" value="${message_dto.message_content }"/>
										<jsp:getProperty property="messageContent" name="util"/>
									</a>
								</c:otherwise>
							</c:choose>
							</td>
							<td>${message_dto.message_send_id }</td>
							<td>
								<fmt:formatDate value="${message_dto.message_regdate }" pattern="yyyy-MM-dd"/>
							</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
	</table>
	</div>
	<input type="button" value="글쓰기" onclick="location.href='whisper.do?command=insert'">
	<input type="submit" value="삭제">
	<input type="button" onclick="self.close()" value="닫기">
</form>
</body>
</html>