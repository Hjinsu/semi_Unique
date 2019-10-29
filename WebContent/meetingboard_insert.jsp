<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8");%>
    <% response.setContentType("text/html; charset=UTF-8");%>
<%@ include file="inc/head.jsp" %>

<section id="meetingboard_insert">
	<form action="meetingboard.do" method="post" name="frm">
		<input type="hidden" name="command" value="insertboardres">
		<input type="hidden" value="" name="lat">
		<input type="hidden" value="" name="lng">
		<input type="hidden" value="" name="map_addr">
		<input type="hidden" name="reh" id="weather_reh">
		<input type="hidden" name="tmn" id="weather_tmn">
		<input type="hidden" name="tmx" id="weather_tmx">
		<input type="hidden" name="t3h" id="weather_t3h">
		<input type="hidden" name="sky" id="weather_sky">
		<table >
			<tr>
				<th>글쓴이</th>
				<td><input type="text" name="board_writer" value="${member_dto.member_id }"> </td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
				<select name="board_tag"> 
					<option>관심사 태그 목록</option>
					<option value="운동">운동</option>
					<option value="음식">음식</option>
					<option value="음악">음악</option>
					<option value="여행">여행</option>
					<option value="패션">패션</option>
					<option value="영화">영화</option>
					<option value="뷰티">뷰티</option>
					<option value="건강">건강</option>
					<option value="경제">경제</option>
				</select>
				<input type="text" name="board_title" style="width: 70%; border:0px;">
</td>
			</tr>
			
			<tr>
				<th rowspan="5">조건 선택</th>
				<td>
				<input type="radio" name="board_genderlimit" value="남자만">남자만
				<input type="radio" name="board_genderlimit" value="여자만">여자만
				<input type="radio" name="board_genderlimit" value="누구나">누구나
				</td>
			</tr>
			
			<tr>
				<td>
				<div id="Msize">
				나이제한 : <input type="text" name="board_age_min" type="number" min="0" max="200" id="min" onchange="compare()"> ~
				<input type="text" name="board_age_max" type="number" min="0" max="200" id="max" onchange="compare()">
				</div>
				</td>
			</tr>
			<tr>
				<td>
				최대인원수 : <input type="number" name="board_peoplelimit" min="0" max="50">
				</td>
			</tr>
			
			<tr>
				<td>
				<select name="board_location" > 
					<option>지역선택</option>
					<option value="서울">서울</option>
					<option value="경기">경기</option>
					<option value="충청">충청</option>
					<option value="전라">전라</option>
					<option value="경상">경상</option>
					<option value="강원">강원</option>
					<option value="제주">제주</option>
				</select>
				</td>
			</tr>
			<tr>
				<td>시간제한 : 날짜입력 <input name="board_date" type="datetime-local" value="" id="now_date"></td>
			</tr>
			<tr>
				<th>글내용</th>
				<td>
				<textarea name="board_content" id="ir1" style="width: 80%;"></textarea>
				</td>
			</tr>
			<tr>
				<th>장소</th>
				<td >
					<div class="map_wrap">
			    		<div id="map">
			    			<button type="button" id="" onclick="" style="display:none">감추기</button>
			    		</div>
			
			    		<div id="menu_wrap" class="bg_white" style="height:30%; float: left;">
			        		<div class="option">
			            		<div>
			                    	키워드 : <input type="text" value="이태원"  id="keyword" size="15"> 
										   <input type="button" value="검색하기" onclick="searchPlaces();">
			            		</div>
			        		</div>
			        		<hr>
			        		<ul id="placesList"></ul>
			        		<div id="pagination"></div>
			        		<div class="hAddr">
			        			<span id="centerAddr"></span>
			        		</div>
			    		</div>
			    	</div>
			    </td>
		    </tr>
   			<tr>
		    		<th>날씨</th>
		    		<td style="position:relative;">
		    		<div id="weather_info">
						<p align="right" id="addrs" style="margin: 0px"></p>
						<table border="1">
							<colgroup>
								<col style="width: 25%; height: 50%;">
								<col style="width: 25%; height: 50%;">
								<col style="width: 25%; height: 50%;">
								<col style="width: 25%; height: 50%;">
							</colgroup>
							<tbody>
							
								<tr>
									<th align="center">습도(%)</th>
									<th align="center">최저/최고(℃)</th>
									<th align="center">현재 온도(℃)</th>
									<th align="center">날씨</th>
								</tr>
								<tr>
									<td align="center"><span id="reh" ></span></td>
									<td align="center"><span id="tmn" style="color: blue"></span><b>/</b><span id="tmx" style="color: red"></span></td>
									<td align="center"><span id="t3h"></span></td>
									<td align="center"><span id="weather_span" ></span><img alt="" src="" id="weather"></td>
								</tr>
							</tbody>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="submit" value="새글입력" onclick="submitContents(this)">
				</td>
			</tr>
		</table>
	</form>
</section>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c54788c8f1925b6e8dd99b858e1c6f11&libraries=services"></script>
<script type="text/javascript" src="js/mapS.js"></script>
<script type="text/javascript">
	onload = function(){
		document.getElementById('now_date').value = new Date().toISOString().slice(0,16);
	}
</script>
<script type="text/javascript">
var oEditors = [];

nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "ir1",
    sSkinURI: "/Life_Style/smarteditor/SmartEditor2Skin.html",
    fCreator: "createSEditor2"
});
//‘저장’ 버튼을 누르는 등 저장을 위한 액션을 했을 때 submitContents가 호출된다고 가정한다.
function submitContents(elClickedObj) {
    // 에디터의 내용이 textarea에 적용된다.
    oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
    // 에디터의 내용에 대한 값 검증은 이곳에서
    // document.getElementById("ir1").value를 이용해서 처리한다.
    try {
        elClickedObj.form.submit();
    } catch(e) {}
}
$(function() {
	$("#B-img").attr("style","background-image: url('img/board/board-meeting2.png');");
});


</script>
<%@ include file="inc/tail.jsp" %>