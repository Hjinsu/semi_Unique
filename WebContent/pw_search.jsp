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

	function idsearch(){
		var id = document.getElementById("originid").value;
		var email = document.getElementById("originemail").value;
		
		$.ajax({
			url : "emailchk.do?command=pwsearchchk&email="+email+"&id="+ id,
			method : "post",
			dataType : 'text',
			success : function(msg) {
				var pattern_kor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
				
				if(isNaN(msg)==false){
					alert("이메일로 PW가 발송 되었습니다 ")
				}else if(pattern_kor.test(msg)){
					alert("id와 email이 올바르지 않습니다.")
				}else{
					alert("본메일과 id로 등록된 계정은 카카오 계정입니다. 카카오로 로그인 해주세요.")
				}
				self.close();
			},	
		})
	}	

</script>
</head>
<body>

	<table id="wrap">
		<tr>
			<td>가입시 등록한 이메일과 ID를 입력해 주세요</td>
		</tr>
		<tr>
		
			<td>
				<input type="text" name="email" id="originemail" placeholder="email을 적어주세요"/>
			</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="id" id="originid" placeholder="id를 적어주세요"/>
			</td>
		</tr>
		<tr>
			<td>
			<input type="button" value="비밀 번호 찾기"  id="ranchk" onclick="idsearch()" />
			</td>
		</tr>
	</table>

</body>
</html>