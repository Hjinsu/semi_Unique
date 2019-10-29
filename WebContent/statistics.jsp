<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8");%>
<%@ include file="/inc/head.jsp" %>

<section id="statistics">
<div id="PT">
	<input type="date" placeholder="검색" id="search_days">
	<input type="button" value="검색" id="search_button">
	
	<div id="TB">
		<div id="chart1"></div>
		<div id="chart2"></div>
	</div>
	<br>
	<br>
	<br>   
	<div>
		<div id="fillgauge1" >
			<svg width="300px" height="250"></svg>
		</div>
		<div id="fillgauge2" >
			<svg width="300px" height="250"></svg>
		</div>
	</div>
</div> 
</section> 
<script type="text/javascript" src="js/statistics.js"></script>
<script>
$(function() {
	$("#B-img").attr("style","background-image: url('img/board/board-presentation.png');");
});
</script>

<%@ include file="/inc/tail.jsp" %>