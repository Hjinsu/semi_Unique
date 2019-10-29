<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8");%>
    <% response.setContentType("text/html; charset=UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="js/convertXY.js"></script>
<script type="text/javascript" src="js/weather.js"></script>
<body>
	<!--POP:강수 확률, REH:습도, T3H:3시간기온, TMN:최저기온, TMX:최고기온, SKY:하늘상태  -0~5:맑음, 6~8:구름많음, 9~10:흐림 -->
	<div style="width: 300px">
		<p align="right" id="addrs" style="margin: 0px">인천광역시 중구 중산동</p>
		<table border="1">
			<colgroup>
				<col style="width: 150px; height: 150px;">
				<col style="width: 150px; height: 150px;">
			</colgroup>
			<tbody>
				<tr>
					<td align="center">습도(%)</td>
					<td align="center"><span id="reh"></span></td>
				</tr>
				<tr>
					<td align="center">최저/최고(℃)</td>
					<td align="center"><span id="tmn" style="color: blue"></span><b>/</b><span id="tmx" style="color: red"></span></td>
				</tr>
				<tr>
					<td align="center">현재 온도(℃)</td>
					<td align="center"><span id="t3h"></span></td>
				</tr>
				<tr>
					<td align="center">날씨</td>
					<td align="center"><span id="weather_span"></span><img alt="" src="" id="weather"></td>
				</tr>
			</tbody>
		</table>
		<input type="text" placeholder="위도" id="latitiude">
		<input type="text" placeholder="경도" id="longitude">
		<input type="text" placeholder="날짜" id="date">
		<input type="text" placeholder="시간" id="time">
		<input type="button" value="날씨 보기" onclick="test()">
	</div>
</body>
</html>