$(function(){
    	$(".countview").hover(function(){
    		const aCountView = $(this);
    		const year = $(".y").text().trim();
    		let month = $(".m").text().trim();
    		
    		let cDate = aCountView.text().trim();
    		
    		if(month.length < 2)
    			month = '0'+month;
    		if(cDate.length < 2)
    			cDate = '0'+cDate;
    			
    		const yyyyMMdd = year+month+cDate;
    		
    		$.ajax({
    			url : "schedule.do",
    			type : "post",
    			data : 'command=cpreview&id=kh&yyyyMMdd='+yyyyMMdd,
    			dataType : "json",
    			async : false
    		}).done(function(obj){
    			const count = obj.cnt;
				aCountView.after("<div class='cPreview'>"+count+"</div>");
    		}).fail(function(){
    			alert("통신ㅅ ㅣㄹ패");
    		});
    		
    	},function(){
    		$(".cPreview").remove();
    	});
    })