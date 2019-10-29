<%@page import="java.util.Calendar"%>
<%@page import="com.life.dto.schedule_dto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8");%>
    <% response.setContentType("text/html; charset=UTF-8");%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="inc/head.jsp" %>

<section id="schedule-list">
<%
	String year = request.getParameter("year");
	String month = request.getParameter("month");
	String date = request.getParameter("date");
	

	Calendar cal =Calendar.getInstance();
	int today_year = cal.get(Calendar.YEAR);
	int today_month = cal.get(Calendar.MONTH);
	int today_date = cal.get(Calendar.DAY_OF_MONTH);
	
	int a = Integer.parseInt(year);
	int b = Integer.parseInt(month);
	int c = Integer.parseInt(date);
%>
<script type="text/javascript" src="js/jquery-3.4.1.js"></script>

	<h1><%=year+"년 "+month+"월 "+date+"일 " %> 일정표</h1>
	<form action="schedule.do" method="post" id="muldelform">
		<input type="hidden" name="command" value="muldel">
		<input type="hidden" name="year" value="<%=year%>">
		<input type="hidden" name="month" value="<%=month%>">
		<input type="hidden" name="date" value="<%=date%>">
		<jsp:useBean id="util" class="com.life.dao.Util"></jsp:useBean>
		
		<table border="1">
			<col width="50px">
			<col width="50px">
			<col width="300px">
			<col width="200px">
			<col width="100px">
			<tr>
				<th><input type="checkbox" name="all" onclick="allChk(this.checked)"></th>
				<th>번   호</th>
				<th>제   목</th>
				<th>일   정</th>
				<th>작성일</th>
			</tr>
			<c:choose>
				<c:when test="${empty list }">
					<tr>
						<td colspan="5" align="center">------일정이 없습니다.------</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${list }" var="dto">
						<tr>
							<td><input type="checkbox" name="chk" value="${dto.schedule_no }"></td>
							<td>${dto.schedule_no }</td>
							<td><a href="schedule.do?command=detail&schedule_no=${dto.schedule_no}&year=<%=year%>&month=<%=month%>&date=<%=date%>">${dto.schedule_title }</a></td>
							<td>
								<jsp:setProperty property="toDates" name="util" value="${dto.schedule_date }"/>
								<jsp:getProperty property="toDates" name="util"/>
							</td>
							<td>
								<fmt:formatDate value="${dto.schedule_regdate }" pattern="yyyy-MM-dd"/>
							</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<tr>
				<td colspan="5">
				<%
					if(a >= today_year){
						if(b >= today_month){
							if(c >= today_date){
								%>
									<input type="button" value="일정 작성" onclick="location.href='schedule.do?command=insert&year=<%=year%>&month=<%=month%>&date=<%=date%>'">	
								<%
							}
						}
					}
				%>
								
				<input type="submit" value="삭제">
				<input type="button" value="돌아가기" onclick="location.href='schedule.do?command=calendar'">
				</td>
			</tr>
		</table>
	</form>
</section>
<script type="text/javascript" src="js/muldel.js"></script>
<script>
$(function() {
	$("#B-img").attr("style","background-image: url('img/board/board-schedule.png');");
});
</script>
<%@ include file="inc/tail.jsp" %>	

