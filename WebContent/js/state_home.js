window.onbeforeunload = unloadPage();


		function unloadPage() {

			var id = "" + "${member_dto.member_id}";
			alert(id);
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