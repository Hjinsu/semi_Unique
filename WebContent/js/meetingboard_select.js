function attenduser(member_id,board_no_seq,board_viewnum,board_peoplelimit){
	const attend_user = document.getElementsByClassName("attends");
	let bool = false;
	if(attend_user.length == 0)
		location.href = 'meetingboard.do?command=attendupdate&attend_user='+member_id+'&board_no_seq='+board_no_seq+'&board_dto.board_viewnum='+board_viewnum;
	else{
		for(let i = 0; i <attend_user.length; ++i ){
			if(attend_user[i].innerHTML != ''){
				if(attend_user[i].innerHTML == member_id){
					bool = true;
					alert('이미 참여 하였습니다.');
					break;
				}
			}
			if(!bool){
				if(parseInt(board_peoplelimit) <= attend_user[i].length){
					alert("모임의 인원이 꽉 찼습니다.");
					break;
				}else{
					location.href = 'meetingboard.do?command=attendupdate&attend_user='+member_id+'&board_no_seq='+board_no_seq+'&board_dto.board_viewnum='+board_viewnum;
					break;
				}
			}
	}
	
	}	
}

$(function(){
	let click_id = '';
	$('html').click(function(e){
		if($(e.target).hasClass("attends")){
			const X = window.event.clientX + window.pageXOffset;
			const Y = window.event.clientY + window.pageYOffset;
				
			$("#pop_up").css({"display" : "block"});
			$("#pop_up").offset({top : Y, left: X});
			click_id = $(e.target).html();
		}else if($(e.target).is("#send_message")){
			send_message(click_id);
		}else if($(e.target).is("#add_friend")){
			add_friend(click_id);
		}else{
			$("#pop_up").css({"display" : "none"});
		}
	})
	
	
	const weather_img = $("#weather_img").val();
	
	if(weather_img === 'clear sky'){
		$("#weather_id").attr("src","img/weather/sun.png");
	}else if (weather_img === 'drizzle' || 'light intensity drizzle' || 'heavy intensity drizzle' ||
								'drizzle rain' || 'shower rain and drizzle' || 'heavy shower rain and drizzle' ||
								'shower drizzle' || 'light intensity drizzle rain' || 'heavy intensity drizzle rain'){
		$("#weather_id").attr("src","img/weather/Drizzle.png");
	}else if (weather_img === 'light rain' || 'moderate rain' || 'heavy intensity rain' || 'very heavy rain' || 'extreme rain'){
		$("#weather_id").attr("src","img/weather/rain.png");
	}else if (weather_img === 'thunderstorm with light rain' || 'thunderstorm with rain' || 'thunderstorm with heavy rain' ||
								'light thunderstorm' || 'thunderstorm' || 'heavy thunderstorm' || 'ragged thunderstorm' ||
								'thunderstorm with light drizzle' || 'thunderstorm with drizzle' || 'thunderstorm with heavy drizzle'){
		$("#weather_id").attr("src","img/weather/rain_thunder.png");
	}else if (weather_img === 'few clouds' || 'scattered clouds' || 'broken clouds' || 'overcast clouds'){
		$("#weather_id").attr("src","img/weather/broken_cloud.png");
		}else if (weather_img === 'mist' || 'smoke' || 'haze' || 'fog'){
			$("#weather_id").attr("src","img/weather/mist.png");
	}else{
		$("#weather_span").html(weather_img);	
	}
})
function send_message(id){
	window.open('whisper.do?command=resend&message_receive_id='+id,'메세지 함','width=360, height=300');
}

function add_friend(id){
	
$.ajax({
	url : "friend.do",
	type : "post",
	data : 'command=insert&friend_id='+id
}).done(function(data){
})
}

