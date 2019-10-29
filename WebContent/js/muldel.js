function allChk(bool){
		$('#muldelform input').each(function(){
   			this.checked = bool;
		});
	};
	
	function Chk(bool){
		if(bool == false)
			$('#muldelform input').eq(0).prop("checked", false);
	};
	
	$(function(){
		$('#muldelform').submit(function(){
			if($('#muldelform input:checked').length == 0){
				alert("하나 이상 체크해 주세요!");
				return false;
			};
		});		
	});
