function send_message(id){
	window.open('whisper.do?command=resend&message_receive_id='+id,'메세지 함','width=360, height=300');
}

function del_friend(id){
	location.href='friend.do?command=del_friend&friend_id='+id;
}