$(function(){
		$("#originfindaddr").click(function(){
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
				document.getElementById("originaddr1").value = data.zonecode; // 우편번호
				document.getElementById("originaddr2").value = fullRoadAddr;
			    }
			}).open();
		})
	});
	
	
	$(function(){
		$("#kakaofindaddr").click(function(){
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
				document.getElementById("kakaoaddr1").value = data.zonecode; // 우편번호
				document.getElementById("kakaoaddr2").value = fullRoadAddr;
			    }
			}).open();
		})
	});


	let idapprove;
	$(function(){
		$("#idbutton").click(function(){
	    	var pattern_spc = /[~!@#$%^&*()_+|<>?:{}]/;
	    	var pattern_kor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
			var id = document.getElementById("originid").value;
			if(!id || pattern_spc.test(id) || pattern_kor.test(id) || id.length > 8 || id.length < 4){
				alert("id는 영문,숫자로 4~8글자로 입력해 주세요.");
			}else{
				$.ajax({
					url : "signup.do?command=idchk&id="+id,
					method : "post",
					dataType : 'text',
					success : function(msg) {
						//alert(msg);
						if (isNaN(msg)==false) {
							alert("중복된 아이디 입니다");			
						} else{
							var yahoo = "사용 가능한 아이디 입니다. 사용 하시겠습니까?"
							var flag = confirm(yahoo);
							if(flag==true){
								idapprove="Y";	
								document.getElementById("originid").setAttribute('readonly','readonly');
								document.getElementById("idbutton").disabled = "disabled";
							}else {
								
							}
						}
					},					
				})
			}
		})
	});

	let rand; //let타입은 상수 개념입니다 . 변수 재선언, 재할당 모두 불가능
	$(function(){
		$("#emailchk").click(function(){
			var email = document.getElementById("originemail").value;
			//alert(email);
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
						//alert(msg);
						rand = msg;
						if (isNaN(msg)==false) {
							alert("인증 번호가 발송 되었습니다");			
						} else{
							alert("이메일이 올바른지 확인해 주세요");
						}
					},					
				})
			}
		})
	});
	
	let approve;
	function randomchk(){
		var userwriternum = document.getElementById("userwriternum").value;
		
		if(userwriternum.trim() == rand.trim()){
			//alert(userwriternum);
			//alert(rand);
			alert("이메일 인증 성공!")
			approve = "Y";
			document.getElementById("originemail").setAttribute('readonly','readonly');
			document.getElementById("userwriternum").setAttribute('readonly','readonly');
			document.getElementById("emailchk").disabled = "disabled";
			document.getElementById("ranchk").disabled = "disabled";
		}else{
			alert("인증번호가 틀렸습니다.");
		}	
	}
	
	function idsearch(){
		
		open("id_search.jsp","", "width=500px,height=200px");
	}
	
	function pwsearch(){
		open("pw_search.jsp","", "width=500px,height=200px");
	}

	function logout(){
		
		Kakao.Auth.logout();
			
	    popup = window.open("http://developers.kakao.com/logout", "pop", "scrollbars=no,width=0,height=0,left=1000,menubar=false");
		
		document.getElementById("hiddenval").value = "null";

		setTimeout(function(){popup.close()},350);
		
		setTimeout(function(){location.href="login.do?command=logout"},1000);
	}

	
	function sessionchk1(){
		var id = document.getElementById("hiddenval").value;
		var url = document.getElementById("urllink1").value;
		var test ='null';
		//alert(id);
		//alert(test);
		if(id==test){
			alert("로그인이 필요합니다.");	
			loginForm();	
		}else{
			location.href=url+id;				
		}
	}
	
	function sessionchk2(){
		var id = document.getElementById("hiddenval").value;
		var url = document.getElementById("urllink2").value;
		var test ='null';
		//alert(id);
		//alert(test);
		if(id==test){
			alert("로그인이 필요합니다.");	
			loginForm();	
		}else{
			location.href=url;				
		}
	}
	
	function sessionchk3(){
		var id = document.getElementById("hiddenval").value;
		var url = document.getElementById("urllink3").value;
		var test ='null';
		//alert(id);
		//alert(test);
		if(id==test){
			alert("로그인이 필요합니다.");	
			loginForm();	
		}else{
			location.href=url;				
		}
	}
	
	function sessionchk4(){
		var id = document.getElementById("hiddenval").value;
		var url = document.getElementById("urllink4").value;
		var test ='null';
		//alert(id);
		//alert(test);
		if(id==test){
			alert("로그인이 필요합니다.");	
			loginForm();	
		}else{
			location.href=url;				
		}
	}
	
	function sessionchk5(){
		var id = document.getElementById("hiddenval").value;
		var url = document.getElementById("urllink5").value;
		var test ='null';
		//alert(id);
		//alert(test);
		if(id==test){
			alert("로그인이 필요합니다.");	
			loginForm();	
		}else{
			location.href=url;				
		}
	}
	
	function sessionchk6(){
		var id = document.getElementById("hiddenval").value;
		var url = document.getElementById("urllink6").value;
		var test ='null';
		//alert(id);
		//alert(test);
		if(id==test){
			alert("로그인이 필요합니다.");	
			loginForm();	
		}else{
			location.href=url;				
		}
	}
	

	let interest ='';
	$(function(){
		$("#signupajax").click(function(){
			var bool = 1;
			var pattern_num = /[0-9]/;	// 숫자 

	    	var pattern_eng = /[a-zA-Z]/;	// 문자 

	    	var pattern_spc = /[~!@#$%^&*()_+|<>?:{}]/; // 특수문자

	    	var pattern_kor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/; // 한글체크
			
			var id = document.getElementById("originid").value;
			//alert(id);
			var pw = document.getElementById("originpw").value;
			//alert(pw);
			var name = document.getElementById("originname").value;
			//alert(name);
			var birth = document.getElementById("originbirth").value;
			//alert(birth);
			var addr1 = document.getElementById("originaddr1").value;
			var addr2 = document.getElementById("originaddr2").value;
			var addr3 = document.getElementById("originaddr3").value;
			var addr = ''+addr1+'/'+addr2+'/'+addr3;
			//alert(addr);
			var phone =  document.getElementById("originphone").value;
			//alert(phone);
			var email = document.getElementById("originemail").value;
			//alert(email);
			var gender = $("input[type=radio][name=origingender]:checked").val() 
			//alert(gender); 
			$("input[type=checkbox][name=origininterest]:checked").each(function() {
				interest += $(this).val() + "/";
			})
			//alert(interest);
			
			if(!id || pattern_spc.test(id) || pattern_kor.test(id) || id.length > 12 || id.length < 4){ 
				bool = 'id오류';
				alert("id는 영문,숫자로 4~12글자로 입력해 주세요.");
			}
			if(!pw || pw.length > 10 || pw.length < 6 || pattern_kor.test(pw)){ 
				bool = 'pw오류';
				alert("pw는 영문,숫자,특문으로 6~10글자로 입력해 주세요.");
			}
			if(!name || pattern_spc.test(name) || pattern_num.test(name) || pattern_eng.test(name)){
				bool = 'name오류';
				alert("이름은 한글만 입력해 주세요.");
			}
			if(!birth || pattern_spc.test(birth) || pattern_eng.test(birth) || pattern_kor.test(birth) || birth.length != 6){
				bool = 'birth오류';
				alert("생년월일은 주민번호 앞자리 6글자를 입력해 주세요.");
			}
			if(!addr || !addr3 || !addr1 || !addr2 || addr3.length > 100){
				bool = '주소 오류';
				alert("주소를 제대로 입력해 주세요.");				
			}
			if(!phone || pattern_kor.test(phone) || pattern_spc.test(phone) ||  pattern_eng.test(phone) || phone.length > 11 || phone.length < 10 || phone.indexOf('-')!= -1){
				bool = '휴대전화 오류';
			 	alert("휴대전화 번호가 올바르지 않습니다.");
			}
			if(!gender){
				bool = '성별 오류';
				alert("성별 선택이 올바르지 않습니다.")
			}

			//alert(idapprove);
			//alert(approve);
			if(isNaN(bool) == false && idapprove=="Y" && approve=="Y"){
				$.ajax({
					url : "signup.do?command=regist&id="+id+"&pw="+pw+"&name="+name+"&birth="+birth+"&addr="+addr
							+"&phone="+phone+"&email="+email+"&gender="+gender+"&interest="+interest,
					method : "post",
					dataType : 'text',
					success : function(msg) {
						//alert(msg);
						if (isNaN(msg)==true) {
							
						    closeWin();
						} else {
							alert("회원가입 실패 입력정보,ID중복 체크,이메일 인증을 확인 해주세요");
						}
					},
				})
			}else{
				alert("회원가입 실패 입력정보,ID중복 체크,이메일 인증을 확인 해주세요");
			}
		})
	});
	
	let kakaointerest ='';
	$(function(){
		$("#kakaosignupajax").click(function(){
			var kakaobool = 1;
			var pattern_num = /[0-9]/;	// 숫자 

	    	var pattern_eng = /[a-zA-Z]/;	// 문자 

	    	var pattern_spc = /[~!@#$%^&*()_+|<>?:{}]/; // 특수문자

	    	var pattern_kor = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/; // 한글체크
			
			var kakaoid = document.getElementById("kakaoid").value;
			//alert(id);
			var kakaopw = 'kakao5545'
			var kakaoname = document.getElementById("kakaoname").value;
			//alert(name);
			var kakaobirth = document.getElementById("kakaobirth").value;
			//alert(birth);
			var kakaoaddr1 = document.getElementById("kakaoaddr1").value;
			var kakaoaddr2 = document.getElementById("kakaoaddr2").value;
			var kakaoaddr3 = document.getElementById("kakaoaddr3").value;
			var kakaoaddr = ''+kakaoaddr1+'/'+kakaoaddr2+'/'+kakaoaddr3;
			//alert(addr);
			var kakaophone =  document.getElementById("kakaophone").value;
			//alert(phone);
			var kakaoemail = document.getElementById("kakaoemail").value;
			//alert(email);
			var kakaogender = $("input[type=radio][name=kakaogender]:checked").val() 
			//alert(gender); 
			$("input[type=checkbox][name=kakaointerest]:checked").each(function() {
				kakaointerest += $(this).val() + "/";
			})
			//alert(interest);
	
			if(!kakaoname || pattern_spc.test(kakaoname) || pattern_num.test(kakaoname) || pattern_eng.test(kakaoname)){
				kakaobool = 'name오류';
				alert("이름은 한글만 입력해 주세요.");
			}
			if(!kakaobirth || pattern_spc.test(kakaobirth) || pattern_eng.test(kakaobirth) || pattern_kor.test(kakaobirth) || kakaobirth.length != 6){
				kakaobool = 'birth오류';
				alert("생년월일은 주민번호 앞자리 6글자를 입력해 주세요.");
			}
			if(!kakaoaddr || !kakaoaddr3 || !kakaoaddr1 || !kakaoaddr2 || kakaoaddr3.length > 100){
				kakaobool = '주소 오류';
				alert("주소를 제대로 입력해 주세요.");				
			}
			if(!kakaophone || pattern_kor.test(kakaophone) || pattern_spc.test(kakaophone) ||  pattern_eng.test(kakaophone) || kakaophone.length > 11 || kakaophone.length < 10 || kakaophone.indexOf('-')!= -1){
				kakaobool = '휴대전화 오류';
			 	alert("휴대전화 번호가 올바르지 않습니다.");
			}
			if(!kakaogender){
				kakaobool = '성별 오류';
				alert("성별 선택이 올바르지 않습니다.")
			}

			if(isNaN(kakaobool)==false ){
				$.ajax({
					url : "signup.do?command=kakaoregist&kakaoid="+kakaoid+"&kakaopw="+kakaopw+"&kakaoname="+kakaoname+"&kakaobirth="+kakaobirth+"&kakaoaddr="+kakaoaddr
							+"&kakaophone="+kakaophone+"&kakaoemail="+kakaoemail+"&kakaogender="+kakaogender+"&kakaointerest="+kakaointerest,
					method : "post",
					dataType : 'text',
					success : function(msg) {
						
						if (isNaN(msg)==true) {
							
						    closeWin();
						} else {
							alert("회원가입 실패 입력정보를 확인 해주세요");
						}
					},
				})
			}else{
				alert("회원가입 실패 입력정보를 확인 해주세요");
			}
		})
	});
	

	$(function(){
		$("#loginajax").click(function(){
			var id = document.getElementsByName("id")[0].value;
			var pw = document.getElementsByName("pw")[0].value;
			if(!id || !pw){
				alert("로그인 실패,id 및 pw를 확인해 주세요");
			}else{
				$.ajax({
					url : "login.do?command=login&id="+ id +"&pw="+ pw,
					method : "post",
					success : function(msg) {
					
						if (msg!=2) {
							
							closeWin();
							document.getElementById("non_login").style.display = "none";
							document.getElementById("on_login").style.display = "block";
							$("#mainID").text(id+" 환영합니다");
							$("#hiddenval").val(id);
						} else{
							alert("로그인 실패,id 및 pw를 확인해 주세요");
						}
					}
				})
			}
		});
	});

	function registForm() {
		document.getElementById("regist").style.display = "block";

		var btns = document.getElementsByName("btn");
		for ( var i in btns) {
			btns[i].disabled = "disabled";
		}
	}
	
	function kakao_registForm(id, email) {
		document.getElementById("kakao_regist").style.display = "block";

		var btns = document.getElementsByName("btn");
		document.getElementById("kakaoid").value = id;
		document.getElementById("kakaoemail").value = email;
		for ( var i in btns) {
			btns[i].disabled = "disabled";
		}
	}

	function loginForm() {
		document.getElementById("login").style.display = "block";

		var btns = document.getElementsByName("btn");
		for ( var i in btns) {
			btns[i].disabled = "disabled";
		}
	}

	function closeWin() {
		document.getElementById("kakao_regist").style.display = "none";
		document.getElementById("regist").style.display = "none";
		document.getElementById("login").style.display = "none";
		
		approve = '';
		idapprove = '';
		
		
		
		document.getElementById("originemail").readOnly = false;
		document.getElementById("userwriternum").readOnly = false;
		document.getElementById("emailchk").removeAttribute('disabled');
		document.getElementById("ranchk").removeAttribute('disabled');
		
		document.getElementById("originid").readOnly = false;
		document.getElementById("idbutton").removeAttribute('disabled');
		
		$("input[name=id]").val('');
		$("input[name=pw]").val('');
		$("input[name=name]").val('');
		$("input[name=birth]").val('');
		$("input[name=addr]").val('');
		$("input[name=phone]").val('');
		$("input[name=email]").val('');
		$("input[id=userwriternum]").val('');
		$("input[type=radio]").prop("checked", false);
		$("input[type=checkbox]").prop("checked", false);
				
		var btns = document.getElementsByName("btn");
		for ( var i in btns) {
			btns[i].disabled = "";
		}
	}
	
	function kakao_closeWin() {
		document.getElementById("kakao_regist").style.display = "none";
		document.getElementById("regist").style.display = "none";
		document.getElementById("login").style.display = "none";
		
		approve = '';
		idapprove = '';
		
		$("input[name=pw]").val('');
		$("input[name=name]").val('');
		$("input[name=birth]").val('');
		$("input[name=addr]").val('');
		$("input[name=phone]").val('');
		$("input[id=userwriternum]").val('');
		$("input[type=radio]").prop("checked", false);
		$("input[type=checkbox]").prop("checked", false);
				
		var btns = document.getElementsByName("btn");
		for ( var i in btns) {
			btns[i].disabled = "";
		}
	}