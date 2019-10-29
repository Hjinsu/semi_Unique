<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="inc/head.jsp" %>

<section id="account">
		<div id="updiv">
		<form action="account.do?command=insertres" method="post">
			<table>
				
				<tr>
					<th>입금 금액 : 
					<input type="text" name="in" placeholder=" 입금 금액 ">
					</th>
				</tr>
				<tr>
					<th>출금 금액 : 
					<input type="text" name="out" placeholder=" 출금 금액 ">
					</th>
				</tr>
				<tr>
					<th>입출금 내용 : 
					<input type="text" name="content" placeholder=" 입출금 내용 ">
					</th>
				</tr>
				<tr>
					<td><input type="submit" value="입력"> 
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="downdiv">
		<h1>가계부</h1>
		<table border="1" >
			<col width="100">
			<col width="200">
			<col width="350">
			<col width="350">
			<col width="400">
			<col width="350">
			<tr>
				<th>번호</th>
				<th>날짜</th>
				<th>입금 금액</th>
				<th>출금 금액</th>
				<th>입출금 내용</th>
				<th>총 자산</th>
			</tr>
			<c:choose>
				<c:when test="${empty list }">
					<tr>
						<td colspan="6">------내역이 존재 하지 않습니다.-------</td>
					</tr>
				</c:when>
				<c:otherwise>
				
					<c:forEach begin="${paging.startboard }" end="${paging.endboard }" var="i">
						<tr>
							<td>${list.get(i-1).account_io_no }</td>
							<td><fmt:formatDate value="${list.get(i-1).account_date }" pattern="yyyy-MM-dd"/></td>
							<td>+ ${list.get(i-1).account_in_cash }원</td>
							<td>- ${list.get(i-1).account_out_cash }원</td>
							<td>${list.get(i-1).account_io_content }</a></td>
							<td>${list.get(i-1).account_totalcash }원</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<tr align="center">
				<td colspan="6" >
					<a href="account.do?command=account&page=${1 }"><img alt="" src="img/board/buttoniconSL.png"></a>
					<a href="account.do?command=account&page=${paging.startpage - 1 }"> <img alt="" src="img/board/buttonicon1l.png"> </a>
					<c:if test="${paging.page >= 2 }">
						<a href="account.do?command=account&page=${paging.startpage - 1  }">...</a>
					</c:if>
					<c:forEach begin="${paging.startpage }" end="${paging.endpage }" var="i">
						<a href="account.do?command=account&page=${i }" id="fsize">${i }</a>
					</c:forEach>
					<c:if test="${paging.page < paging.totalpage - 2 }">
						<a href="account.do?command=account&page=${paging.startpage + 1  }">...</a>
					</c:if>
					<a href="account.do?command=account&page=${paging.startpage + 1 }"> <img alt="" src="img/board/buttonicon1r.png"> </a>
					<a href="account.do?command=account&page=${paging.totalpage }"> <img alt="" src="img/board/buttoniconSR.png"> </a>
				</td>
			</tr>
		</table>
	</div>
</section>
<%@ include file="/inc/tail.jsp" %>
<script>
$(function() {
	$("#B-img").attr("style","background-image: url('img/board/board-account.png');");
});
</script>