<%@page import="com.life.dto.member_dto"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8");%>
    <% response.setContentType("text/html; charset=UTF-8");%>
    <%@ include file="inc/head.jsp" %>
    
<section id="schedule-insert">
<%
	int year = Integer.parseInt(request.getParameter("year"));
	int month = Integer.parseInt(request.getParameter("month"));
	int date = Integer.parseInt(request.getParameter("date"));
	
	Calendar cal =Calendar.getInstance();
	int hour = cal.get(Calendar.HOUR_OF_DAY);
	int min = cal.get(Calendar.MINUTE);

	int today_hour = cal.get(Calendar.HOUR_OF_DAY);
	int today_min = cal.get(Calendar.MINUTE);
	
	member_dto member_dto = (member_dto)session.getAttribute("member_dto");
%>

<h1>일정 작성하기</h1>
	<form action="schedule.do" method="post">
		<input type="hidden" name="command" value="insertcal">
		
		<table border="1">
			<tr>
				<th>ID</th>
				<td><input type="text" name="id" value="<%=member_dto.getMember_id() %>" readonly="readonly"></td>
			</tr>
			<tr>
				<th>일정</th>
				<td>
					<input type="text" readonly="readonly" name="year" value="<%=year%>">년
					<input type="text" readonly="readonly" name="month" value="<%=month%>">월
					<input type="text" readonly="readonly" name="date" value="<%=date%>">일
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
				<th>제목</th>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" name="content"></textarea></td>
			</tr>
			<tr>				
				<td colspan="2">
					<input type="submit" value="일정 작성">
					<input type="button" value="돌아가기" onclick="location.href='schedule.do?command=list&year=<%=year%>&month=<%=month%>&date=<%=date%>'">
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