<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8");%>
    <% response.setContentType("text/html; charset=UTF-8");%>
<%@ include file="/inc/head.jsp" %>

<section id="shopping">
<%
List<String> list_href = (List<String>)request.getAttribute("href");
List<String> list_img = (List<String>)request.getAttribute("img");
List<String> list_title = (List<String>)request.getAttribute("title");
List<String> list_price = (List<String>)request.getAttribute("price");
%>

	<input type="text" id="search_shop" placeholder="검색할 물품">
	<input type="button" id="search_button" onclick="search()" value="검색">
	<div style="overflow: scroll; height: 800px">
	<%
	if(list_href != null){
	for(int i =0; i < list_href.size(); ++i){ 
		%>
		<div class="shop_item">
			<a href="<%=list_href.get(i)%>" target="_blank">
				<div class="shop_deal">
					<div class="shop_img">
						<img alt="" src="<%=list_img.get(i)%>" style="width: 148px; height: 148px;" >
					</div>					
					<div class="shop_area">
						<div class="shop_title"><%=list_title.get(i) %></div>
						<div class="shop_price"><%=list_price.get(i) %></div>
					</div>
				</div>
			</a>
		</div>
		<%
		}
	}
	%>
	</div>
</section>	
<script type="text/javascript">
	function search(){
		const search_item = $("#search_shop").val();
		
		location.href = "shopping.do?command=search&search_item=" + search_item;
	}		
</script>
<script>
$(function() {
	$("#B-img").attr("style","background-image: url('img/board/board-shopping.png');");
});
</script>
<%@ include file="/inc/tail.jsp" %>
