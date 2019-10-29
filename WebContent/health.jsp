<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<%@ include file="inc/head.jsp" %>

<section id="health">
	<div id="updiv">
		<h1>건강 관리</h1>
		<form action="health.do?command=insertres" method="post">
			<table>
				<tr>
					<th>몸무게</th>
					<td><input type="text" name="weight" value=""></td>
				</tr>
				<tr>
					<th>먹은 음식</th>
					<td>
						<input type="text" id="food" name="food" value="">
						<input type="button" value="먹은거" onclick="search();"> 
						<span id="search"></span>
					</td>
				</tr>
				<tr>
					<th>칼로리</th>
					<td><input type="text" name="kcal" id="kcal"></td>
				</tr>
				<tr>
					<td>
						<div id="desc_div"></div>
					</td>
					<td>
						<div id="amount_div">
							
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="입력"> 
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="downdiv">
		<table border="1">
			<col width="200">
			<col width="200">
			<col width="500">
			<col width="500">
			<col width="300">
			<tr>
				<th>ID</th>
				<th>몸무게</th>
				<th>먹은 음식</th>
				<th>먹은 양</th>
				<th>날짜</th>
			</tr>
			<c:choose>
				<c:when test="${empty list }">
					<tr>
						<td colspan="4">-----입력된 정보가 없습니다.------</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach begin="${paging.startboard }" end="${paging.endboard }" var="i">
						<tr>
							<td>${list.get(i-1).health_id }</td>
							<td>${list.get(i-1).health_weight }</td>
							<td>${list.get(i-1).health_food }</td>
							<td>${list.get(i-1).health_kcal }Kcal</td>
							<td><fmt:formatDate value="${list.get(i-1).health_regdate }" pattern="yyyy-MM-dd"/></td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<tr align="center">
				<td colspan="5" >
					<a href="health.do?command=health&page=${1 }"><img alt="" src="img/board/buttoniconSL.png"></a>
					<a href="health.do?command=health&page=${paging.startpage - 1 }"> <img alt="" src="img/board/buttonicon1l.png"> </a>
					<c:if test="${paging.page >= 2 }">
						<a href="health.do?command=health&page=${paging.startpage - 1  }">...</a>
					</c:if>
					<c:forEach begin="${paging.startpage }" end="${paging.endpage }" var="i">
						<a href="health.do?command=health&page=${i }" id="fsize">${i }</a>
					</c:forEach>
					<c:if test="${paging.page < paging.totalpage - 2 }">
						<a href="health.do?command=health&page=${paging.startpage + 1  }">...</a>
					</c:if>
					<a href="health.do?command=health&page=${paging.startpage + 1 }"> <img alt="" src="img/board/buttonicon1r.png"> </a>
					<a href="health.do?command=health&page=${paging.totalpage }"> <img alt="" src="img/board/buttoniconSR.png"> </a>
				</td>
			</tr>
		</table>
	</div>
</section>
<%@ include file="/inc/tail.jsp" %>
<script>
$(function() {
	$("#B-img").attr("style","background-image: url('img/board/board-health.png');");
});
</script>