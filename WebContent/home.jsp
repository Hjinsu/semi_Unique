<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-control", "no-store");
	response.setHeader("Expires", "0");
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
<title>Insert title here</title>
<style >
@font-face {
	font-family: "wrap";
	src: url("css/Goyang.ttf") format("truetype");
} 
</style>
</head>
<link rel="stylesheet" href="css/login_regist.css">
<link rel="stylesheet" href="css/home.css">
<link rel="stylesheet" href="css/motion-css/motion/motion.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script type="text/javascript" src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="js/regist.js"></script>

<body>
<div id="wrap">
                <header>
                    <div style="image-orientation: 2rad; " >
                            <a id="mainlogo" href="home.jsp"><img src="img/logo.png"; style="width: 10%; float: left ;"></a>
                    </div>
                   <div id="non_login" style="float: right;">
						<!--<input type="button" name="btn" onclick="registForm()" value="회원가입" />  -->
							<input type="button" name="btn" onclick="loginForm()" value="로그인" />
						<!--<input type="button" name="btn" id="link" value="테스트" onclick="sessionchk()"/>  -->
					</div>
					<div id="on_login" style="float:right">
							<input type="button" name="btn" id="logout" value="로그아웃" onclick="logout()"/>
					</div>
                    <div id="sun">
                         	<img src="img/sun.png";>
                    </div>
                    <div id="cloud">
                            <img src="img/cloud1.png"; id="cloud1">
                            <img src="img/cloud2.png"; id="cloud2">
                            <img src="img/cloud3.png"; id="cloud3">
                            <img src="img/cloud2.png"; id="cloud4">
                            <img src="img/cloud3.png"; id="cloud5">
                            <img src="img/cloud1.png"; id="cloud6">
                    </div>
                    <div id="backimg">
                        <img src="img/citys.png";>
                    </div>
                    <div class="car">
                            <img src="img/car.png" alt="드라이브 GOGO!">
                            <div class="circle"><i></i><i></i><i></i></div>
                            <div class="circle"><i></i><i></i><i></i></div>
                            <p class="smoke">
                                <span></span>
                                <span></span>
                                <span></span>
                            </p>
                        </div>
                     
                   
	<input type="hidden" id="hiddenval" value="null" />
	
	
	<div id="login" class="animation back-in-down">
	<p><img id="login_logo" src="img/logo2.png"></p>
			<table cellspacing="10">
				</br></br></br>
				<caption>로 그 인</caption>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="id" />
				</tr>
				<tr>
					<td>패스워드</td>
					<td><input type="password" name="pw" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center" style="position: relative; right: -8%;">
						<input type="button" value="회원가입" onclick="registForm()">
						<input type="button" value="로그인" id="loginajax"/> 
						<input type="button" value="취소" onclick="closeWin()">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<h4>id, pw가 기억나지 않는다구요?</h4>
						<a style="text-decoration:none" href="javascript:/js/regist.js" onclick="idsearch()">id 찾기</a>&emsp;&emsp;
						<a style="text-decoration:none" href="javascript:/js/regist.js" onclick="pwsearch()">pw 찾기</a>
					</td>
				</tr>
			</table>
		<div>
			&nbsp;&nbsp;&emsp;&emsp;<a id="kakao-login-btn"></a> 
		</div>
	</div>
	
	<div id="kakao_regist">
		<p><img id="regist_logo" src="img/logo2.png"></p>
			<input type="hidden" name="command" value="regist" />
			<table  style="height: 80%;"><br/><br/><br/> 			
				<caption>회원가입</caption>
				<tr>
					<td>아 이 디</td>
					<td>
						<input type="text" name="id" id='kakaoid' value=" " readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>이 름</td>
					<td><input type="text" name="name" id="kakaoname" placeholder="한글만"/></td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td><input type="text" name="birth" id="kakaobirth" style="width:60%;" placeholder="주민번호 앞 6자리"/></td>
				</tr>
				<tr>
					<td>주 소</td>
					<td>
						<input type="text" name="addr" id="kakaoaddr1" readonly="readonly" style="width:30%;"  /> &nbsp;
						<input type="button" id="kakaofindaddr" value="주소 찾기" /><br/>
						<input type="text" name="addr" id="kakaoaddr2" readonly="readonly" style="width:80%;"/>
					</td>
				</tr>
				<tr>
					<td>상세주소</td>
					<td>
						<input type="text" name="addr" id="kakaoaddr3" style="width:80%;"/>
					</td>
				</tr>
				<tr>
					<td>휴대전화</td>
					<td>
						<input type="text" id="kakaophone" name="phone" placeholder="-제외 숫자만 적어주세요"/> 
					</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" name="email" id="kakaoemail" value="" readonly="readonly"></td>
				</tr>
				<tr>
					<td>성 별</td>
					<td>
						<input type="radio" name='kakaogender' value='남' />남 
						<input type="radio" name='kakaogender' value='여' />여
					</td>
				</tr>
				<tr>
					<td>관심분야</td>
					<td>
						<input type="checkbox" name="kakaointerest" value="운동" />운동 
						<input type="checkbox" name="kakaointerest" value="음식" />음식 
						<input type="checkbox" name="kakaointerest" value="음악" />음악<br/> 
						<input type="checkbox" name="kakaointerest" value="여행" />여행
						<input type="checkbox" name="kakaointerest" value="패션" />패션 
						<input type="checkbox" name="kakaointerest" value="영화" />영화<br/> 
						<input type="checkbox" name="kakaointerest" value="뷰티" />뷰티 
						<input type="checkbox" name="kakaointerest" value="건강" />건강
						<input type="checkbox" name="kakaointerest" value="경제" />경제
					</td>
				</tr>
				<tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="가입하기" id="kakaosignupajax" /> &nbsp;&nbsp;
						<input type="button" value="취소"  onclick="kakao_closeWin()" />
					</td>
				</tr>
		</table>
	</div>

	
	<div id="regist">
		<p><img id="regist_logo" src="img/logo2.png"></p>
			<input type="hidden" name="command" value="regist" />
			<table cellspacing="10">
				</br></br>
				<caption>회 원 가 입</caption></br>
				<tr>
					<td>아이디</td>
					<td>
						<input type="text" name="id" id="originid" placeholder="영어,숫자 4~8글자"/>&nbsp;
						<input type="button" value="중복체크" id="idbutton" />
					</td>
				</tr>
				<tr>
					<td>패스워드</td>
					<td><input type="password" name="pw" id="originpw" placeholder="영문,숫자,특문 6~10글자 "/></td>
				</tr>
				<tr>
					<td>이 름</td>
					<td><input type="text" name="name" id="originname" placeholder="한글만"/></td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td><input type="text" name="birth" id="originbirth" style="width:60%;" placeholder="주민번호 앞 6자리"/></td>
				</tr>
				<tr>
					<td>주 소</td>
					<td>
						<input type="text" name="addr" id="originaddr1" readonly="readonly" style="width:30%;"  />&nbsp;
						<input type="button" id="originfindaddr" value="주소 찾기" /><br/>
						<input type="text" name="addr" id="originaddr2" readonly="readonly" style="width:80%;"/>
					</td>
				</tr>
				<tr>
					<td>상세주소</td>
					<td>
						<input type="text" name="addr" id="originaddr3" style="width:80%;"/>
					</td>
				</tr>
				<tr>
					<td>휴대전화</td>
					<td>
						<input type="text" id="originphone" name="phone" placeholder="-제외 숫자만 적어주세요"/> 
					</td>
				</tr>
				<tr>
					<td>이메일</td>
					<td>
						<input type="text" name="email" id="originemail"/>&nbsp;
						<input type="button" value="이메일 인증" id="emailchk"/><br/> 
						<input type="text" placeholder="인증 번호" id="userwriternum"/>&nbsp;
						<input type="button" value="인 증"  id="ranchk" onclick="randomchk()" />
					</td>
				</tr>
				<tr>
					<td>성 별</td>
					<td>
						<input type="radio" name='origingender' value='남' />남 
						<input type="radio" name='origingender' value='여' />여
					</td>
				</tr>
				<tr>
					<td>관심분야</td>
					<td>
						<input type="checkbox" name="origininterest" value="운동" />운동 
						<input type="checkbox" name="origininterest" value="음식" />음식 
						<input type="checkbox" name="origininterest" value="음악" />음악<br/> 
						<input type="checkbox" name="origininterest" value="여행" />여행
						<input type="checkbox" name="origininterest" value="패션" />패션 
						<input type="checkbox" name="origininterest" value="영화" />영화<br/> 
						<input type="checkbox" name="origininterest" value="뷰티" />뷰티 
						<input type="checkbox" name="origininterest" value="건강" />건강
						<input type="checkbox" name="origininterest" value="경제" />경제
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="가입하기" id="signupajax" />&nbsp;&nbsp;
						<input type="button" value="취소" onclick="closeWin()" />
					</td>
				</tr>
			</table>
	</div>
	</header>
        <footer >
                <div style="float: left">
                    <input type="checkbox" id="footericon">
                    <label for="footericon">
                        <span></span>
                        <span></span>
                        <span></span>
                    </label>
                    <div class="footer">
                        <br/><br/>
                        <h1>Life_Style</h1>
                        <br/><br/>
                        <h3>
                            팀명 :  Unique 
                        </h3>
                            <br/>
                        <h3>   
                            팀원 소개 : 한진수, 이동규, 임재현, 하준성, 김형준
                        </h3>
                        <br/>
                        <h3>    
                            개요 : 바쁜 현대인들을 위한 웹어플리케이션으로 손쉽게 접근 및 활용할수 있는<br/>
                                사용자 개인의 라이프 관리를 목적으로한 웹어플리케이션이며 유일하고 편한<br/>
                                접근방식의 CSS반응형으로 만들어진 웹어플리케이션이다. 
                        
                        </h3>
                    </div>
                </div>    
                <div style="float: right">
                    <input type="checkbox" id="footericon2">
                    <label for="footericon2">
                        <span></span>
                        <span></span>
                        <span></span>
                    </label>
                    <div class="menubar" style="width: 50%; padding: 1px;  ">
                        <div id="menu1" >
                        	<input type="hidden" id="urllink1" value="myinformation.do?command=memberinfo&id=" />
                            <a style="text-decoration:none; color:white;" href="javascript:/js/regist.js" onclick="sessionchk1();" >내정보</a>
                        </div>
                        <div id="menu2" >
                       	    <input type="hidden" id="urllink2" value="meetingboard.do?command=meetingboard&page=1" />
                            <a style="text-decoration:none; "  href="javascript:/js/regist.js" onclick="sessionchk2();" >게시판</a>
                        </div>
                        <div id="menu3">
                        	<input type="hidden" id="urllink3" value="shopping.do?command=shop_main" />
                            <a style="text-decoration:none; color:white;" href="javascript:/js/regist.js" onclick="sessionchk3();" >쇼핑</a>
                        </div>
                        <div id="menu4">
                        	<input type="hidden" id="urllink4" value="health.do?command=health&page=1" />
                            <a style="text-decoration:none; "  href="javascript:/js/regist.js" onclick="sessionchk4();" >건강</a>
                        </div>
                        <div id="menu5">
                        	<input type="hidden" id="urllink5" value="account.do?command=account&page=1" />
                            <a style="text-decoration:none; color:white;"  href="javascript:/js/regist.js" onclick="sessionchk5();" >가계부</a>
                        </div>
                        <div id="menu6">
                        	<input type="hidden" id="urllink6" value="statistics.do?command=statistics" />
                            <a style="text-decoration:none; "  href="javascript:/js/regist.js" onclick="sessionchk6();" >통계&그래프</a>
                        </div>
                            
                    </div>
                </div>    
                    
                
                
    
        </footer>
    </div>
	
	
	<script type="text/javascript" src="js/kakao_login.js"></script>
	<script type="text/javascript">
		window.onbeforeunload = unloadPage();

		function unloadPage() {

			var id = "" + "${member_dto.member_id}";
			if (!id) {
				//alert("로그인 안된상태");
				document.getElementById("non_login").style.display = "block";
				document.getElementById("on_login").style.display = "none";
			} else {
				//alert("로그인 된상태");
				document.getElementById("non_login").style.display = "none";
				document.getElementById("on_login").style.display = "block";
				document.getElementById("hiddenval").value = id;
			}
		}
	</script>
	<iframe name="pop" width="0" height="0" style="display: none;" ></iframe>
	<!-- 
	<a href="myinformation.do?command=memberinfo&id=아이디">내정보이동</a>
	<a href="meetingboard.do?command=meetingboard&page=1">만남게시판이동</a>	
	<a href="shopping.do?command=shop_main">쇼핑 이동</a>
	<a href="schedule.do?command=calendar">내 스케쥴 이동</a>
	<a href="#" onclick="open('whisper.do?command=message_box','메세지 함','width=360, height=300')">메세지 함</a>
	<a href="http://192.168.10.6:8090/?id=${member_dto.member_id}" target="_blank">채팅 이동</a>
	<a href="health.do?command=health">건강 관리</a>
	<a href="account.do?command=account">가계부</a>
	<a href="statistics.do?command=statistics">통계</a>
	 -->
</body>
</html>