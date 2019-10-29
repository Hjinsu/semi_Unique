<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8");%>
    <% response.setContentType("text/html; charset=UTF-8");%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/inc/head.jsp" %>

<section id="meetingboard_selectone">
<form action="meetingboard.do?">
	<input type="hidden" name="command" value="comment_insert">
	<input type="hidden" name="board_no_seq" value="${board_dto.board_no_seq }">
	<input type="hidden" id="weather_img" value="${weather_dto.weather_sky}"> 
	<table >
		<tr>
			<th>글번호</th>
			<td >${board_dto.board_no_seq }</td>
		</tr>
		<tr>
			<th>글쓴이</th>
			<td >${board_dto.board_writer }</td>
		</tr>
		
		<tr>
			<th rowspan="6">제약조건</th>
			<td >관심사 : ${board_dto.board_tag }</td> 
		</tr>
		<tr>
			<td >성별 : ${board_dto.board_genderlimit }</td> 
		</tr>
		
		<tr>
			<td >나이 : ${board_dto.board_age_min }~${board_dto.board_age_max }세</td>
		</tr>
		
		<tr>
			<td >최대인원 : ${board_dto.board_peoplelimit } 명</td> 
		</tr>
		
		<tr>
			<td >지역 : ${board_dto.board_location } </td> 
		</tr>
		
		<tr>
			<td>시간 : <fmt:formatDate value="${board_dto.board_timelimit }" pattern="yyyy-MM-dd HH:mm"/> 까지</td>
		</tr>
		
		<tr>
			<th>제목</th>
			<td >${board_dto.board_title }</td>
		</tr>
		<tr>
			<th>글내용</th>
			<td>
				${board_dto.board_content }
				<hr>
				<span id="attend">
					<b>참여자</b><br>
					<c:choose>
						<c:when test="${empty attend } ">
							<span>참여자가 없습니다.</span>
						</c:when>
						<c:otherwise>
							<c:forEach items="${attend }" var="att">
								<span class="attends">${att }</span> 
								<br>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</span>
				
				<input type="button" value="참여하기" onclick="attenduser('${member_dto.member_id}','${board_dto.board_no_seq }','${board_dto.board_viewnum }','${board_dto.board_peoplelimit }')">
			</td>

		</tr>
		<tr>
			<th>글작성일</th>
			<td ><fmt:formatDate value="${board_dto.board_regdate}" pattern="yyyy-MM-dd"/></td>
			
		</tr>
		<tr>
			<th>모임장소</th>
			<td><div id="map" style="width:100%;height:350px;"></div></td>
		</tr>
		<tr>
			<th>날씨</th>
			<td>
			<table border="1">
				<colgroup>
					<col style="width: 25%; height: 50%;">
					<col style="width: 25%; height: 50%;">
					<col style="width: 25%; height: 50%;">
					<col style="width: 25%; height: 50%;">
				</colgroup>
				<tr>
					<th align="center">습도(%)</th>
					<th align="center">최저/최고(℃)</th>
					<th align="center">현재 온도(℃)</th>
					<th align="center">현재 온도(℃)</th>
				</tr>
				<tr>
					<td align="center"><span>${weather_dto.weather_reh}</span></td>
					<td align="center"><span style="color: blue">${weather_dto.weather_tmn }</span><b>/</b><span style="color: red">${weather_dto.weather_tmx }</span></td>
					<td align="center"><span>${weather_dto.weather_t3h}</span></td>
					<td align="center"><img id="weather_id" alt="" src=""> <span id="weather_span"></span></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<div class="dioing">
				<c:if test="${board_dto.board_writer eq member_dto.member_id}">
					<input type="button" value="수정" onclick="location.href='meetingboard.do?command=meetingupdate&board_no_seq=${board_dto.board_no_seq}'">
					<input type="button" value="삭제" onclick="location.href='meetingboard.do?command=meetingdelete&board_no_seq=${board_dto.board_no_seq}'">
				</c:if>
				
			</div>
			</td>
		</tr>

		<tr>
			<th>댓글작성</th>
			<td colspan="2">
			
			<input type="hidden" name="board_no_seq" value="${board_dto.board_no_seq }">
			<input type="hidden" name="board_viewnum" value="${board_dto.board_viewnum }">
			<input type="text" name="comment_writer" value="${member_dto.member_id }">
			<input type="text" name="comment_content" style="width:500px; height:15px"><input type="submit" value="확인">
			</td>
		</tr>
	</table>
	<table>
		<c:forEach items="${comment_list }" var="comment_dto">
			<tr>
				<td style="font-size:12px; font-weight:bold" class="attends">${comment_dto.comment_writer }</td>
				<td>${comment_dto.comment_content }</td>
				<td style="font-size:10px"><fmt:formatDate value="${comment_dto.comment_regdate }" pattern="yyyy-MM-dd HH:mm"/></td>
				<td>
				<div class="dioing">
				<input type="button" value="삭제" onclick="location.href='meetingboard.do?command=comment_delete&comment_no_seq=${comment_dto.comment_no_seq}&board_no_seq=${board_dto.board_no_seq }&board_viewnum=${board_dto.board_viewnum}'">
				</div>
				</td>
			</tr>
		</c:forEach>	
	</table>
</form>	
<div id="pop_up">
	<span id="add_friend" >친구 추가</span><hr>
	<span id="send_message">쪽지 보내기</span>
</div>
</section>

<script type="text/javascript">
$(function() {
	$("#B-img").attr("style","background-image: url('img/board/board-meeting1.png');");
});
</script>	

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c54788c8f1925b6e8dd99b858e1c6f11&libraries=services"></script>
<script type="text/javascript" src="js/meetingboard_select.js"></script>
<script type="text/javascript">
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(${map_dto.map_latitude} , ${map_dto.map_longitude}), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption);

// 마커가 표시될 위치입니다 
var markerPosition  = new kakao.maps.LatLng(${map_dto.map_latitude} , ${map_dto.map_longitude}); 

// 마커를 생성합니다
var marker = new kakao.maps.Marker({
    position: markerPosition
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);

var iwContent = '<div style="padding:5px;">${map_dto.map_place} <br><a href="https://map.kakao.com/link/map/${map_dto.map_place},${map_dto.map_latitude},${map_dto.map_longitude}" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/${map_dto.map_place},${map_dto.map_latitude},${map_dto.map_longitude}" style="color:blue" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    iwPosition = new kakao.maps.LatLng(${map_dto.map_latitude} , ${map_dto.map_longitude}); //인포윈도우 표시 위치입니다

// 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({
    position : iwPosition, 
    content : iwContent 
});
  
// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
infowindow.open(map, marker); 


</script>
<%@ include file="/inc/tail.jsp" %>