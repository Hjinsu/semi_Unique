<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-control", "no-store");
	response.setHeader("Expires", "0");
%>

<%@page import="com.life.biz.member_biz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8");%>
    <% response.setContentType("text/html; charset=UTF-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ include file="inc/head.jsp" %>

<section id="myinformation">
<div id="board-infoup"><img alt="" src="img/board/board-infoup.png"></div>
<form action="myinformation.do" method="get">

	<table >
	
		<tr>
			<th>아이디</th>
			<td>${member_dto.member_id }</td>
		</tr>
		<tr id="pwtag">
			<th>비번</th>
			<td><input type="password" id="pw" value="${member_dto.member_pw }"></td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${member_dto.member_name }</td>
		</tr>
		<tr>
			<th>생일</th>
			<td>${member_dto.member_birth }</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>
				<input type="text" name="addr" id="addr1" readonly="readonly"  value='addrB[0]'  /> 
				<input type="button" id="findaddr" value="주소 찾기" /><br/>
				<input type="text" name="addr" id="addr2" readonly="readonly"  value='addrB[1]' />
				<input type="text" name="addr" id="addr3"  value='addrB[2]' />
			</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><input type="text" id="phone" value="${member_dto.member_phone }"></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${member_dto.member_email }</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>${member_dto.member_gender }</td>
		</tr>
		<tr>
			<th>관심사</th>
			<td>
				<input type="checkbox" name="interest" value="운동" />운동 
				<input type="checkbox" name="interest" value="음식" />음식 
				<input type="checkbox" name="interest" value="음악" />음악<br/> 
				<input type="checkbox" name="interest" value="여행" />여행
				<input type="checkbox" name="interest" value="패션" />패션 
				<input type="checkbox" name="interest" value="영화" />영화<br/> 
				<input type="checkbox" name="interest" value="뷰티" />뷰티 
				<input type="checkbox" name="interest" value="건강" />건강
				<input type="checkbox" name="interest" value="경제" />경제
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<input type="button" id='update' value="수정" />	
			<input type="button" id='delete' value="탈퇴" />
			
			</td>
		</tr>
		</table>
	</form>
	<div id="board-infodown"><img alt="" src="img/board/board-infodown.png"></div>
</section>
<script type="text/javascript">
$(function() {
	$("#B-img").attr("style","background-image: url('img/board/board-myinfo.png');");
});
</script>

<script type="text/javascript">

	$(function(){
		$("#findaddr").click(function(){
			new daum.Postcode({
			    oncomplete: function(data) {
			    var fullRoadAddr = data.roadAddress;
			    var extraRoadAddr = '';
			    
			    if(data.bname !== '' && data.apartment === 'Y'){
			    	extraRoadAddr += data.bname;
			    }
			    if(data.buildingName !== '' && data.apartment === 'Y'){
			    	extraRoadAddr += (extraRoadAddr !== '' ? ', ' +data.buildingName : data.build)
			    }
			    if(extraRoadAddr !== ''){
			    	extraRoadAddr = ' (' + extraRoadAddr + ')';
			    }
			    if(fullRoadAddr !== ''){
			    	fullRoadAddr += extraRoadAddr;
			    }
				document.getElementById("addr1").value = data.zonecode; // 우편번호
				document.getElementById("addr2").value = fullRoadAddr;
			    }
			}).open();
		})
	});
	
	let interest ='';
	$(function(){
		$("#update").click(function(){
			var bool = 1;
			var pattern_num = /[0-9]/;	// 숫자 

	    	var pattern_eng = /[a-zA-Z]/;	// 문자 

	    	var pattern_spc = /[~!@#$%^&*()_+|<>?:{}]/; // 특수문자

	    	var pattern_kor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/; // 한글체크
			
	    	var id = "${member_dto.member_id}";
			var pw = document.getElementById("pw").value;
			//alert(pw);
			var addr1 = document.getElementById("addr1").value;
			var addr2 = document.getElementById("addr2").value;
			var addr3 = document.getElementById("addr3").value;
			var addr = ''+addr1+'/'+addr2+'/'+addr3;
			//alert(addr);
			var phone =  document.getElementById("phone").value;
			//alert(phone); 
			$("input[type=checkbox][name=interest]:checked").each(function() {
				interest += $(this).val() + "/";
			})
			
			if(!pw || pw.length > 10 || pw.length < 6 || pattern_kor.test(pw)){ 
				bool = 'pw오류';
				alert("pw는 영문,숫자,특문으로 6~10글자로 입력해 주세요.");
			}
			if(!addr || !addr3 || !addr1 || !addr2 || addr3.length > 100){
				bool = '주소 오류';
				alert("주소를 제대로 입력해 주세요.");				
			}
			if(!phone || pattern_kor.test(phone) || pattern_spc.test(phone) ||  pattern_eng.test(phone) || phone.length > 11 || phone.length < 10 || phone.indexOf('-')!= -1){
				bool = '휴대전화 오류';
			 	alert("휴대전화 번호가 올바르지 않습니다.");
			}

			//alert(idapprove);
			//alert(approve);
			if(isNaN(bool) == false){
				var url = "myinformation.do?command=UpdateMemberInfo&id="+id+"&pw="+pw+"&addr="+addr+"&phone="+phone+"&interest="+interest;
				location.href=url;
			}else{
				alert("회원수정 실패 입력정보를 확인 해주세요");
			}
		})
	});
	
	$(function(){
		$("#delete").click(function(){
			var reconfirm = "정말 회원탈퇴 사용 하시겠습니까?"
			var flag = confirm(reconfirm);
			var id = "${member_dto.member_id}";
			if(flag==true){
				location.href="myinformation.do?command=DeleteMemberInfo&id="+id;		
			}
		})
	});
	
	

</script>


<%@ include file="inc/tail.jsp" %>
<script type="text/javascript">

	window.onbeforeunload = unloadPage();
	
	function  unloadPage(){
		
		var id = '${member_dto.member_id}'
		if(id.match("@")){
			document.getElementById('pw').setAttribute('type','hidden');
			document.getElementById('pwtag').setAttribute('style','display:none');
			//document.getElementById('textchk').innerText='주소, 전화번호, 관심사만 수정 가능';
		}
		
		var addrA ='${member_dto.member_addr}'
		var addrB = addrA.split('/');
		
		document.getElementById('addr1').value = addrB[0];
		document.getElementById('addr2').value = addrB[1];
		document.getElementById('addr3').value = addrB[2];
		
		var interestButton = document.getElementsByName("interest");
		var interestA = '${member_dto.member_interest}'
		var interestB = interestA.split('/'); 
		
		for(let a = 0; a < interestB.length; a++){
			for(let b = 0; b < interestButton.length; b++){
				if(interestB[a] === interestButton[b].value){
					//alert(interestB[a]);
					interestButton[b].checked = 'checked';
				}
			}
		}
	}

</script>