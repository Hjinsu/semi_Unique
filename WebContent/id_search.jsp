<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
@import url("css/login_regist.css");
@font-face {
	font-family: "wrap";
	src: url("css/Goyang.ttf") format("truetype");
}
#wrap{
	font-family: "wrap";
}
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">

let rand; //let타입은 상수 개념입니다 . 변수 재선언, 재할당 모두 불가능
$(function(){
	$("#emailchk").click(function(){
		var email = document.getElementById("originemail").value;
		
		if(email == ''){
			alert("이메일이 올바른지 확인해 주세요");
		}else{
			$.ajax({
				url : "emailchk.do?command=emailchk&email="+ email,
				method : "post",
				data : {
					'email' : email
				},
				dataType : 'text',
				success : function(msg) {
					
					rand = msg;
					if (isNaN(msg)==false) {
						alert("이메일로 인증 번호가 발송 되었습니다");			
					}else{
						alert("이메일이 올바른지 확인해 주세요");
					}
				},					
			})
		}
	})
});

function idsearch(){
	var userwriternum = document.getElementById("userwriternum").value;
	var email = document.getElementById("originemail").value;
	
	if(userwriternum.trim() == rand.trim()){
		//alert(userwriternum);
		//alert(rand);
		$.ajax({
			url : "emailchk.do?command=idsearchchk&email="+ email,
			method : "post",
			dataType : 'text',
			success : function(msg) {
				if(isNaN(msg)==false){
					alert("이메일로 ID가 발송 되었습니다 ")
				}else if(msg.trim=="카카오".trim){
					alert("본메일과 id로 등록된 계정은 카카오 계정입니다. 카카오로 로그인 해주세요.")
				}else{
					alert("가입된 email이 아닙니다")
				}
				self.close();
			},	
		})
	}else{
		alert("인증번호가 틀렸습니다.다시 확인해주세요");
	}	
}

</script>
</head>
<body>

	<table id="wrap">
		<tr>
			<td>가입시 등록한 이메일을 기입후 '이메일 인증' 버튼을 눌러주세요</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="email" id="originemail" placeholder="email을 적어주세요"/>
				<input type="button" value="이메일 인증" id="emailchk"/><br/> 
				<input type="text" placeholder="인증 번호" id="userwriternum"/>
				<input type="button" value="인 증"  id="ranchk" onclick="idsearch()" />
			</td>
		</tr>
	</table>

</body>
</html>