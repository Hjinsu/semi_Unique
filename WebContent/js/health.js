let total_desc='';
let total_kcal = '';
	function search() {
		const DESC_KOR = $("#food").val();
		const NUTR_CONT1 = $("#cal").val();
		
		$.ajax({
			url : 'health.do?' + 'command=food&' + 'DESC_KOR='
					+ DESC_KOR 	
					+ '&nutr_cont1=' + NUTR_CONT1,
			dataType : 'text',
			success : function(msg) {
				let tmp = msg.split("§");
				total_desc = tmp[0].split("/");
				total_kcal = tmp[1].split("/");
				let del_desc = [];
				let del_kcal = [];
					
				for(let i = 0; i < total_desc.length; ++i ) {
				   $.each(total_desc,function(i,val){
					   if(del_desc.indexOf(val) == -1) { 
						   //total_kcal.splice(i,1);   
						   del_desc.push(val);
					   }
				   });
				}
				var desc_div = document.getElementById("desc_div");
				
				while(desc_div.hasChildNodes()){
					desc_div.removeChild(desc_div.firstChild);
				}
				for(let i = 0; i < total_desc.length; ++i) {
					$("#desc_div").css({
						'overflow-y' : 'scroll',
						'height' : '300px',
						'width' : '200px'
					}).append(
							"<p class = 'desc' onclick ='amount(this)'>"
									+ total_desc[i] + "</p>");
				}
			},
			errror : function(error) {
				alert("통신 실패");
				}
			});
	}
	
	function calcul(msg) {
		let SERVING = $("#amount").val();
		SERVING *= 1;
		let cal_kcal;
		for(let i = 0; i < total_desc.length; ++i) {
			if(msg == total_desc[i]) {
				total_kcal[i];
				total_kcal[i] *= 1;
				cal_kcal = SERVING * total_kcal[i];
			}
		}
		$("#kcal").val(cal_kcal);
	}
	
	function amount(msg) {
		msg.setAttribute("style",'background-color : lightblue;');
		for(let i = 0; i < total_desc.length; ++i) {
			if(msg.innerHTML == total_desc[i]) {
				total_desc[i];
				total_kcal[i];
				
				var c = document.getElementById("amount_div");
				while(c.hasChildNodes()){
					c.removeChild(c.firstChild);
				}
				
				$("#amount_div").append(
										"<input type=\"number\" id=\"amount\" name=\"amount\">" +
										"<input type = 'button' value='입력'' id='' onclick='calcul(\""+total_desc[i]+"\");'>"
									);
			break;
		}
	}
}