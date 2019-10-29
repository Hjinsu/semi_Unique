<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8");%>
    <% response.setContentType("text/html; charset=UTF-8");%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="inc/head.jsp" %>

<section id="schedule-detail">
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

	<form action="schedule.do?command=update" method="post">
		<input type="hidden" name="schedule_no" value="${schedule_dto.schedule_no}">
		
		<table border="1">
		<jsp:useBean id="util" class="com.life.dao.Util"></jsp:useBean>
			<tr>
				<th>제목 </th>
				<td colspan="3">${schedule_dto.schedule_title}</td>
			</tr>
			<tr>
				<th>일정 날짜 </th>
				<td>
					<jsp:setProperty property="toDates" name="util" value="${schedule_dto.schedule_date }"/>
					<jsp:getProperty property="toDates" name="util"/>
				</td>
				<th>작성일 </th>
				<td><fmt:formatDate value="${schedule_dto.schedule_regdate }" pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>
				<th>내용 </th>
				<td colspan="3"> <textarea rows="10" cols="60" readonly="readonly">${schedule_dto.schedule_content}</textarea> </td>
			</tr>
			<c:if test="${schedule_dto.schedule_board_no eq 0}">
				<tr>
					<td colspan="4">
					<%
						if(a >= today_year){
							if(b >= today_month){
								if(c >= today_date){
									%>
										<input type="submit" value="수정">	
									<%
								}
							}
						}
					%>
						<input type="button" value="삭제" onclick="location.href='schedule.do?command=del&schedule_no=${schedule_dto.schedule_no}&year=<%=year%>&month=<%=month%>&date=<%=date%>'">
						<input type="button" value="목록으로" onclick="location.href='schedule.do?command=list&year=<%=year%>&month=<%=month%>&date=<%=date%>'">
					</td>
				</tr>
			</c:if>
		</table>
	</form>
</section>
<script>
$(function() {
	$("#B-img").attr("style","background-image: url('img/board/board-schedule.png');");
});
</script>	
<%@ include file="inc/tail.jsp" %>