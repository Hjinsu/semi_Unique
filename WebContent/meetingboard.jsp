<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
%>
<%
response.setHeader("Pragma","no-cache");
response.setHeader("Cache-control","no-store");
response.setHeader("Expires","0");
%>
<%@ include file="inc/head.jsp" %>

<section id="meetingboard_list">


	<form action="" method="get">
		<div id="center">
			<table border="1">
				<col width="100">
				<col width="300">
				<col width="300">
				<col width="100">
				<col width="100">

				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>제한조건</th>
					<th>글쓴이</th>
					<th>글작성일</th>
					<th>조회수</th>
				</tr>

				<c:choose>
					<c:when test="${empty board_list }">
						<tr>
							<td colspan="6">----작성된 글이 없습니다.----</td>
						</tr>
					</c:when>
					<c:otherwise>

						<c:forEach var="i" begin="${paging.startboard }" end="${paging.endboard }">
							<c:choose>
								<c:when test="${board_list.get(i-1).board_del eq 'N' }">
									<tr>
										<td>${board_list.get(i-1).board_no_seq}</td>

										<td><c:forEach begin="1" end="${board_list.get(i-1).board_tab }">
											RE:</c:forEach> 
											<a href="meetingboard.do?command=selectone&board_no_seq=${board_list.get(i-1).board_no_seq }&board_viewnum=${board_list.get(i-1).board_viewnum}">
												${board_list.get(i-1).board_title}
												</a>
										</td>
										<td>성별:${board_list.get(i-1).board_genderlimit} /나이제한: ${board_list.get(i-1).board_age_min}~${board_list.get(i-1).board_age_max } <br>
										/인원수 제한: ${board_list.get(i-1).board_peoplelimit}명 /관심사 : ${board_list.get(i-1).board_tag} <br>
										/지역: ${board_list.get(i-1).board_location}  </td>
										<td>${board_list.get(i-1).board_writer}</td>
										<td><fmt:formatDate value="${board_list.get(i-1).board_regdate}" pattern="yyyy-MM-dd"/></td>
										<td>${board_list.get(i-1).board_viewnum}</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:choose>
									<c:when test="${board_list.get(i-1).board_del eq 'Y' }">
									<tr>
										<td>${board_list.get(i-1).board_no_seq}</td>
										<td colspan="5">
										<c:forEach begin="1" end="${board_list.get(i-1).board_tab }">
										RE:</c:forEach>
										삭제된 글입니다.
										</td>
									</tr>
									</c:when>
									<c:otherwise>
										<tr>
											<td colspan="6"></td>
										</tr>
									</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<tr>
					<td colspan="6">
					<div class="dioing">
					<input type="button" value="글쓰기" onclick="location.href='meetingboard.do?command=insertboard'">
					</div>
					</td>
				</tr>
				<tr>
					<td colspan="6">
					<div class="dioing">
						<select id="search" class="chk">
							<option value="board_title">제목</option>
							<option value="board_content">내용</option>
							<option value="board_titlecontent">제목+내용</option>
							<option value="board_writer">글쓴이</option>
							<option value="board_tag">태그</option>
						</select>
						
						<input type="text" id="searchtxt">
						<input type="button" value="검색" onclick="search2()">
						</div>
					</td>
				</tr>
				<tr align="center">
					<td colspan="6" >
						<a href="meetingboard.do?command=meetingboard&page=${1 }&search=${search}&searchtxt=${searchtxt}"><img alt="" src="img/board/buttoniconSL.png"></a>
						<a href="meetingboard.do?command=meetingboard&page=${paging.startpage - 1 }&search=${search}&searchtxt=${searchtxt}"> <img alt="" src="img/board/buttonicon1l.png"> </a>
						<c:if test="${paging.page >= 2 }">
							<a href="meetingboard.do?command=meetingboard&page=${paging.startpage - 1  }&search=${search}&searchtxt=${searchtxt}">...</a>
						</c:if>
						<c:forEach begin="${paging.startpage }" end="${paging.endpage }" var="i">
							<a href="meetingboard.do?command=meetingboard&page=${i }&search=${search}&searchtxt=${searchtxt}" id="fsize">${i }</a>
						</c:forEach>
						<c:if test="${paging.page < paging.totalpage - 2 }&search=${search}&searchtxt=${searchtxt}">
							<a href="meetingboard.do?command=meetingboard&page=${paging.startpage + 1  }&search=${search}&searchtxt=${searchtxt}">...</a>
						</c:if>
						<a href="meetingboard.do?command=meetingboard&page=${paging.startpage + 1 }&search=${search}&searchtxt=${searchtxt}"> <img alt="" src="img/board/buttonicon1r.png"> </a>
						<a href="meetingboard.do?command=meetingboard&page=${paging.totalpage }&search=${search}&searchtxt=${searchtxt}"> <img alt="" src="img/board/buttoniconSR.png"> </a>
					</td>
				</tr>
			</table>
		</div>
	</form>
</section>

    <script>
    
$(function() {
	$("#bangbang").attr("src","img/board/board-meetingL.png");
}); 

</script>
<script src='js/meetingboard.js'></script>
<%@ include file="/inc/tail.jsp" %>