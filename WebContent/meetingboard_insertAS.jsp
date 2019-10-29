<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8");%>
    <% response.setContentType("text/html; charset=UTF-8");%>
<%@ include file="inc/head.jsp" %>

<section id="meetingboard_insertAS">
	<form action="meetingboard.do" method="post">
		<input type="hidden" name="command" value="insertASres">
		<input type="hidden" name="board_no_seq" value="${insertAS_dto.board_no_seq }">
		<table border="1">
			<tr>
				<th>글쓴이</th>
				<td><input type="text" name="board_writer"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="board_title"></td>
			</tr>
			<tr>
				<th>글내용</th>
				<td>
					<textarea name="board_content" id="ir1" ></textarea>
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