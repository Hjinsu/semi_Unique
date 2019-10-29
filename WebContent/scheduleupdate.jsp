<%@page import="java.util.Calendar"%>
<%@page import="com.life.dto.schedule_dto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8");%>
<%@ include file="inc/head.jsp" %>

<section id="schedule-update">
<%
	schedule_dto dto = (schedule_dto)request.getAttribute("schedule_dto");
	int hour = Integer.parseInt(dto.getSchedule_date().substring(8, 10));
	int min = Integer.parseInt(dto.getSchedule_date().substring(10, 12));
	
	Calendar cal =Calendar.getInstance();	
	int today_hour = cal.get(Calendar.HOUR_OF_DAY);
	int today_min = cal.get(Calendar.MINUTE);
%>

<form action="schedule.do?command=updateres" method="post">
		<input type="hidden" name="schedule_no" value="${schedule_dto.schedule_no}">
		
		<table border="1">
		<jsp:useBean id="util" class="com.life.dao.Util"></jsp:useBean>
			<tr>
				<th>제목 </th>
				<td colspan="3"> <input type="text" value="<%=dto.getSchedule_title() %>" name="schedule_title"> </td>
			</tr>
			<tr>
				<th>일정 날짜 </th>
				<td>
				<%=	dto.getSchedule_date().substring(0, 4)%>년
				<%=	dto.getSchedule_date().substring(4, 6)%>월
				<%=	dto.getSchedule_date().substring(6, 8)%>일
				<select name="hour">
						<%
							for(; today_hour < 24; ++today_hour){
								%>
								<option value="<%=today_hour%>" <%=hour==today_hour?"selected":"" %>><%=today_hour%></option>
								<%
							}
						%>
					</select>시
					<select name="min">
						<%
							for(; today_min < 60; ++today_min){
								%>
								<option value="<%=today_min%>" <%=min==today_min?"selected":"" %>><%=today_min%></option>
								<%
							}
						%>
					</select>분
				</td>
			</tr>
			<tr>
				<th>내용 </th>
				<td colspan="3"> <textarea rows="10" cols="60" name="schedule_content">${schedule_dto.schedule_content}</textarea> </td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="submit" value="수정하기">
				<input type="button" value="취소" onclick="location.href='schedule.do?command=detail&schedule_no=${schedule_dto.schedule_no}'">
				</td>
			</tr>
		</table>
	</form>
</section>	
<script>
$(function() {
	$("#B-img").attr("style","background-image: url('img/board/board-schedule.png');");
});
</script>
<%@ include file="inc/tail.jsp" %>