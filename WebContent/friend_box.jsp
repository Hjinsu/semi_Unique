<%@page import="com.life.dto.friend_dto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8");%>
    <% response.setContentType("text/html; charset=UTF-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
@import url("css/messagecss.css");
@font-face {font-family: "fontstyle";src: url("css/Goyang.ttf") format("truetype");} 
</style>

<script type="text/javascript" src="js/friend_box.js"></script>

</head>
<body>
	<div id="fontstyle">
		<table border="1">
			<c:choose>
				<c:when test="${empty friend_dto }">
					<tr>
						<td>등록된 친구가 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
				<tr>
					<th colspan="2">친구 목록</th>
					
				</tr>
					<c:if test="${friend_dto.friend_friend_id01 != null }">
					<tr>
						<td>${friend_dto.friend_friend_id01}</td>
						<td>
							<input type="button" value="쪽지 보내기" onclick="send_message('${friend_dto.friend_friend_id01}')">
							<input type="button" value="친구 삭제" onclick="del_friend('${friend_dto.friend_friend_id01}')">
						</td>
					</tr>
					</c:if>
					<c:if test="${friend_dto.friend_friend_id02 != null }">
					<tr>
						<td>${friend_dto.friend_friend_id02}</td>
						<td>
							<input type="button" value="쪽지 보내기" onclick="send_message('${friend_dto.friend_friend_id02}')">
							<input type="button" value="친구 삭제" onclick="del_friend('${friend_dto.friend_friend_id02}')">
						</td>
					</tr>
					</c:if>
					<c:if test="${friend_dto.friend_friend_id03 != null }">
					<tr>
						<td>${friend_dto.friend_friend_id03}</td>
						<td>
							<input type="button" value="쪽지 보내기" onclick="send_message('${friend_dto.friend_friend_id03}')">
							<input type="button" value="친구 삭제" onclick="del_friend('${friend_dto.friend_friend_id03}')">
						</td>
					</tr>
					</c:if>
					<c:if test="${friend_dto.friend_friend_id04 != null }">
					<tr>
						<td>${friend_dto.friend_friend_id04}</td>
						<td>
							<input type="button" value="쪽지 보내기" onclick="send_message('${friend_dto.friend_friend_id04}')">
							<input type="button" value="친구 삭제" onclick="del_friend('${friend_dto.friend_friend_id04}')">
						</td>
					</tr>
					</c:if>
					<c:if test="${friend_dto.friend_friend_id05 != null }">
					<tr>
						<td>${friend_dto.friend_friend_id05}</td>
						<td>
							<input type="button" value="쪽지 보내기" onclick="send_message('${friend_dto.friend_friend_id05}')">
							<input type="button" value="친구 삭제" onclick="del_friend('${friend_dto.friend_friend_id05}')">
						</td>
					</tr>
					</c:if>
					<c:if test="${friend_dto.friend_friend_id06 != null }">
					<tr>
						<td>${friend_dto.friend_friend_id06}</td>
						<td>
							<input type="button" value="쪽지 보내기" onclick="send_message('${friend_dto.friend_friend_id06}')">
							<input type="button" value="친구 삭제" onclick="del_friend('${friend_dto.friend_friend_id06}')">
						</td>
					</tr>
					</c:if>
					<c:if test="${friend_dto.friend_friend_id07 != null }">
					<tr>
						<td>${friend_dto.friend_friend_id07}</td>
						<td>
							<input type="button" value="쪽지 보내기" onclick="send_message('${friend_dto.friend_friend_id07}')">
							<input type="button" value="친구 삭제" onclick="del_friend('${friend_dto.friend_friend_id07}')">
						</td>
					</tr>
					</c:if>
					<c:if test="${friend_dto.friend_friend_id08 != null }">
					<tr>
						<td>${friend_dto.friend_friend_id08}</td>
						<td>
							<input type="button" value="쪽지 보내기" onclick="send_message('${friend_dto.friend_friend_id08}')">
							<input type="button" value="친구 삭제" onclick="del_friend('${friend_dto.friend_friend_id08}')">
						</td>
					</tr>
					</c:if>
					<c:if test="${friend_dto.friend_friend_id09 != null }">
					<tr>
						<td>${friend_dto.friend_friend_id09}</td>
						<td>
							<input type="button" value="쪽지 보내기" onclick="send_message('${friend_dto.friend_friend_id09}')">
							<input type="button" value="친구 삭제" onclick="del_friend('${friend_dto.friend_friend_id09}')">
						</td>
					</tr>
					</c:if>
					<c:if test="${friend_dto.friend_friend_id10 != null }">
					<tr>
						<td>${friend_dto.friend_friend_id10}</td>
						<td>
							<input type="button" value="쪽지 보내기" onclick="send_message('${friend_dto.friend_friend_id10}')">
							<input type="button" value="친구 삭제" onclick="del_friend('${friend_dto.friend_friend_id10}')">
						</td>
					</tr>
					</c:if>
				</c:otherwise>
			</c:choose>
		</table>
	</div>
</body>
</html>