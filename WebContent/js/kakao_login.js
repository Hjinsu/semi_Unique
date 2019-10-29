Kakao.init('54d41e5b3b3be5b3aeb962bde0e357a9');
Kakao.Auth.createLoginButton({
	container : '#kakao-login-btn',
	success : function(authObj) {
		Kakao.API.request({
			url : '/v1/user/me',
			success : function(res) {
				var userID = 'kakao@'+ res.properties.nickname;
				var userEmail = res.kaccount_email;
				$.ajax({
					url : "login.do?command=kakaologin&userID="
						+ userID
						+ "&userEmail="
						+ userEmail,
						method : "post",
						data : {
							'userID' : userID,
							'userEmail' : userEmail
							},
							datatype : 'json',
							async : false,
							success : function(msg) {
								const ss = msg.split("/");
								
								if (ss.length < 2) {
									
									closeWin();
									document.getElementById("non_login").style.display = "none";
									document.getElementById("on_login").style.display = "block";
									$("#mainID").text(
											userID + " 환영합니다");
									$("#hiddenval").val(userID);
									} else {
										alert("연동 정보외 추가 입력 사항이 필요합니다.\n회원가입 페이지로 이동합니다");
										kakao_closeWin();
										kakao_registForm(ss[0],
												ss[1]);
										}
								}
							})
							},
							fail : function(error) {
								alert(JSON.stringify(error));
								}
							});
		},
		fail : function(err) {
			alert(JSON.stringify(err));
			}
		});