function search2(){
	
	var search = $("#search option:selected").val()
	var searchtxt = $("#searchtxt").val()
	alert(search)
	alert(searchtxt)
	location.href=
		"meetingboard.do?command=meetingboard&search="+search+"&searchtxt="+searchtxt+"&page=1"
		
}


function compare(){
	
	var min = $("#min").val()
	var max = $("#max").val()
	
	if(max !=""){
	if(min > max){
		alert("값을 다시입력해주세요")
		$("[name=board_age_max]").focus()
		
	}
	}
	
}