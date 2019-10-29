<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/statistics.css">
    <title>Life-Style</title>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
	<script type="text/javascript" src="/Life_Style/smarteditor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
	<script type="text/javascript" src="http://d3js.org/d3.v3.min.js"></script>
	<script type="text/javascript" src="js/liquidFillGauge.js"></script>
	<script type="text/javascript" src="js/chart.js"></script>
	<script type="text/javascript" src="js/health.js"></script>
	
	<style>
		@import url("css/mapC.css");
		@font-face {font-family: "wrap";src: url("css/Goyang.ttf") format("truetype");} 
	</style>
    <style type="text/css">
    	/* Layout Start */
        *{ padding: 0; margin: 0; }
        body{background: #f7f4f5; }
        #wrap {width: 1200px; margin: 0 auto; font-family: "wrap";}    
        #wrap header {width: 100%; height: 300px;  background-repeat: no-repeat;  background-position: center; border-image-width: 100%;}
        #wrap header #iconmenu img{width: 5%;float: right;padding-left: 2%;}
        #wrap main { width: 100%; position:relative; }
        #wrap main nav { positon:absolute;  width:20%; float:left; text-align: center;  }
        #wrap main nav span img{ display:block; width: 30%; margin: auto; padding-bottom: 30%;}
        #wrap main section{ positon:absolute; float:right; width: 80%; align-content: center;  }
        #wrap footer { clear:both; display:block; padding:10px; height:44px; width:100%; text-align:center; }
        #wrap footer img{width: 60px;position: relative;top: 0px;}
        #wrap #openFooter { clear:both; display:none; padding:10px; height:44px; width:100%; text-align:center; }
        #wrap .foot {width: 100%; height: 150px; position: relative;}
        #wrap .foot fieldset {display: inline; border: 0px;}
        #wrap .foot li {display: none; position: absolute;}
        #wrap .foot fieldset:hover li {display: block;}
		#wrap .dioing { display: flex;justify-content: center;}
		#wrap .color-bg-start { background-color: salmon;}
		#wrap .bg-animate-color {animation: random-bg .5s linear infinite;}
		@keyframes random-bg {from {filter: hue-rotate(0);} to {filter: hue-rotate(360deg);}}
		input[type='button'] {background-color: salmon;color: white;padding: 5px;border: none;transition: all .3s ease;border-radius: 15px;
		letter-spacing: 4px;text-transform: uppercase;outline: none;align-self: center;cursor: pointer;font-weight: bold; margin-left: 4px;}
		input[type='submit'] {background-color: salmon;color: white;padding: 5px;border: none;transition: all .3s ease;border-radius: 15px;
		letter-spacing: 4px;text-transform: uppercase;outline: none;align-self: center;cursor: pointer;font-weight: bold; margin-left: 4px;}
		input[type='button']:hover {animation: random-bg .5s linear infinite, grow 1300ms ease infinite;}
		input[type='submit']:hover {animation: random-bg .5s linear infinite, grow 1300ms ease infinite;}
		.start-fun {background-color: #fff !important;color: salmon !important;}
		@keyframes grow {0% {transform: scale(1);}14% {transform: scale(1.3);}28% {transform: scale(1);}42% {transform: scale(1.3);}70% {transform: scale(1);}}
        @keyframes circle {0% {transform: rotate(0deg)}100% {transform: rotate(-360deg)}};
        /*menuCSS Start*/
        .myinfo:after{content:"";}
        .myinfo:hover:after{ position: absolute; content:"내정보"; background-color: rgb(252, 6, 6);color: rgb(0, 0, 0);left: 13%;top: 45%;font-size: 15px;
        padding: 3px;white-space: nowrap;border-top-left-radius: 50px;border-top-right-radius: 50px;border-bottom-right-radius: 50px;
        transition: all 0.1s; z-index: 50; text-decoration: none;}
        .heartbeat:after{content:"";}
        .heartbeat:hover:after{ position: absolute; content:"건강"; background-color: rgb(252, 6, 6);color: rgb(0, 0, 0);left: 13%;top: 130px;font-size: 15px;
        padding: 3px;white-space: nowrap;border-top-left-radius: 50px;border-top-right-radius: 50px;border-bottom-right-radius: 50px;
        transition: all 0.1s; z-index: 50; text-decoration: none;}
        .schedule:after{content:"";}
        .schedule:hover:after{ position: absolute; content:"스케줄"; background-color: rgb(252, 6, 6);color: rgb(0, 0, 0);left: 13%;top: 270px;font-size: 15px;
        padding: 3px;white-space: nowrap;border-top-left-radius: 50px;border-top-right-radius: 50px;border-bottom-right-radius: 50px;
        transition: all 0.1s; z-index: 50; text-decoration: none;}
        .group:after{content:"";}
        .group:hover:after{ position: absolute; content:"게시판"; background-color: rgb(252, 6, 6);color: rgb(0, 0, 0);left: 13%;top: 420px;font-size: 15px;
        padding: 3px;white-space: nowrap;border-top-left-radius: 50px;border-top-right-radius: 50px;border-bottom-right-radius: 50px;
        transition: all 0.1s; z-index: 50; text-decoration: none;}
        .account:after{content:"";}
        .account:hover:after{ position: absolute; content:"가계부"; background-color: rgb(252, 6, 6);color: rgb(0, 0, 0);left: 13%;top: 550px;font-size: 15px;
        padding: 3px;white-space: nowrap;border-top-left-radius: 50px;border-top-right-radius: 50px;border-bottom-right-radius: 50px;
        transition: all 0.1s; z-index: 50; text-decoration: none;}
        .presentation:after{content:"";}
        .presentation:hover:after{ position: absolute; content:"그래프"; background-color: rgb(252, 6, 6);color: rgb(0, 0, 0);left: 13%;top: 690px;font-size: 15px;
        padding: 3px;white-space: nowrap;border-top-left-radius: 50px;border-top-right-radius: 50px;border-bottom-right-radius: 50px;
        transition: all 0.1s; z-index: 50; text-decoration: none;}
        .shopping:after{content:"";}
        .shopping:hover:after{ position: absolute; content:"쇼핑"; background-color: rgb(252, 6, 6);color: rgb(0, 0, 0);left: 13%;top: 850px;font-size: 15px;
        padding: 3px;white-space: nowrap;border-top-left-radius: 50px;border-top-right-radius: 50px;border-bottom-right-radius: 50px;
        transition: all 0.1s; z-index: 50; text-decoration: none;}
        /*menuCSS End*/
        /* meetingboard Start */
        #wrap main #meetingboard { padding:10px 0; }
        #wrap main #meetingboard table { width:100%; border-collapse:collapse; border:1px #ccc solid; top-margin:5px; }
        #wrap main #meetingboard table th { padding:5px 0 5px 0; border:1px #ccc solid; text-align:center; }
        #wrap main #meetingboard table td { padding:5px 0 5px 0; border:1px #ccc solid; text-align:center; }
        #wrap main #meetingboard .chk { vertical-align: middle; }
        /* meetingboard End */
        
        /* meetingboard_insert Start */
        #wrap main #meetingboard_insert { padding:10px 0; }
        #wrap main #meetingboard_insert table { width:100%; border-collapse:collapse; border:0px #f4e4e4 solid; top-margin:5px; }
        #wrap main #meetingboard_insert table th { padding:5px 0 5px 0; border: 1px #fdfdfd solid;background: #eccbbb; }
        #wrap main #meetingboard_insert table td { padding:5px 0 5px 0; border-top:0.5px #f4e4e4 solid; }
        #wrap main #meetingboard_insert input{border: 0px;}
        #wrap main #meetingboard_insert input[name='board_writer'] { width:90%; height:21px; vertical-align: middle; }
        #wrap main #meetingboard_insert input[name='board_title'] { width:73%; height:21px; vertical-align: middle; }
        #wrap main #meetingboard_insert select { height:21px; vertical-align: middle; font-family: "wrap";  border: 0px;}
        #wrap main #meetingboard_insert .map_wrap { height:300px; }
        #wrap main #meetingboard_insert #map { width:100%;height:100%;position:relative;overflow:hidden; }
        #wrap main #meetingboard_insert #weather_info { width:100%;right:0;top:0 } 
        /* meetingboard_insert End */
        
        /* meetingboard_selectone Start */
        #wrap main #meetingboard_selectone { padding:10px 0; }
        #wrap main #meetingboard_selectone table { width:100%; border-collapse:collapse; border:0px #ccc solid; top-margin:5px; }
        #wrap main #meetingboard_selectone table th { padding:5px 0 5px 0; border:1px #fdfdfd  solid; border-radius: 1px; background: #eccbbb;}
        #wrap main #meetingboard_selectone table td { padding:5px 0 5px 0; border-top:0.5px #f4e4e4 solid;  border-radius: 1px;}
        #wrap main #meetingboard_selectone input[name='board_writer'] { width:90%; height:21px; vertical-align: middle; }
        #wrap main #meetingboard_selectone input[name='board_title'] { width:73%; height:21px; vertical-align: middle; }
        #wrap main #meetingboard_selectone select { height:21px; vertical-align: middle; font-family: "wrap";  border: 0px;}
        #wrap main #meetingboard_selectone .map_wrap { height:300px; }
        #wrap main #meetingboard_selectone #map { width:55%;height:100%;position:relative;overflow:hidden; }
        #wrap main #meetingboard_selectone #weather_info { width:40%;position:absolute;right:0;top:0 } 
        /* meetingboard_selectone End */
        
        /* meetingboard_update Start */
        #wrap main #meetingboard_update { padding:10px 0; }
        #wrap main #meetingboard_update table { width:100%; border-collapse:collapse; border:0px #ccc solid; top-margin:5px; }
        #wrap main #meetingboard_update table th { padding:5px 0 5px 0; border:1px #fdfdfd  solid; border-radius: 1px; background: #eccbbb;}
        #wrap main #meetingboard_update table td { padding:5px 0 5px 0; border-top:0.5px #f4e4e4 solid;  border-radius: 1px;}
        #wrap main #meetingboard_update input[name='board_writer'] { width:90%; height:21px; vertical-align: middle; }
        #wrap main #meetingboard_update input[name='board_title'] { width:73%; height:21px; vertical-align: middle; }
        #wrap main #meetingboard_update select { height:21px; vertical-align: middle; font-family: "wrap";  border: 0px;}
        #wrap main #meetingboard_update .map_wrap { height:300px; }
        #wrap main #meetingboard_update #map { width:55%;height:100%;position:relative;overflow:hidden; }
        #wrap main #meetingboard_update #weather_info { width:100%;right:0;top:0 } 
        /* meetingboard_update End */

		/* myinformation Start */
        #wrap main #myinformation { padding:10px 0; }
        #wrap main #myinformation div img {width: 100%; padding-top: 2%; padding-bottom: 5%; opacity: 0.6;}
        #wrap main #myinformation table { width:100%; border-collapse:collapse; border:0px #ccc solid; margin:auto; }
        #wrap main #myinformation table th { padding:5px 0 5px 0; border:1px #fdfdfd  solid; border-radius: 1px; background: #eccbbb;}
        #wrap main #myinformation table td { padding:5px 0 5px 0; border-top:0.5px #f4e4e4 solid;  border-radius: 1px;}
        #wrap main #myinformation input {   vertical-align: middle; border: 0px; }
        #wrap main #myinformation 
        /* myinformation End */
        
        /* meetingboard_list Start */
        #wrap main #meetingboard_list { padding:10px 0; }
        #wrap main #meetingboard_list div img { width: 25px;padding: 0px 1px 0 0;}
        #wrap main #meetingboard_list a {text-decoration:none;}
        #wrap main #meetingboard_list #fsize {font-size: 25px;}
        #wrap main #meetingboard_list table { width:100%; border-collapse:collapse; border:0px #ccc solid; margin:auto; }
        #wrap main #meetingboard_list table th { padding:5px 0 5px 0; border:1px #fdfdfd  solid; border-radius: 1px; background: #eccbbb;}
        #wrap main #meetingboard_list table td { padding:5px 0 5px 0; border-top:0.5px #f4e4e4 solid;  border-radius: 1px;}
        #wrap main #meetingboard_list input { vertical-align: middle; border: 0px; }
        #wrap main #meetingboard_list 
        /* meetingboard_list End */
        
        /* schedule Start */
        #wrap main #schedule { padding:10px 0; }
        #wrap main #schedule div img {width: 100%; opacity: 0.6;}
        #wrap main #schedule table { width:100%; border-collapse:collapse; border:0px #ccc solid; margin:auto; }
        #wrap main #schedule table th { padding:5px 0 5px 0; border:1px #fdfdfd  solid; border-radius: 1px; background: #eccbbb;}
        #wrap main #schedule table td { padding:5px 0 5px 0; border-top:0.5px #f4e4e4 solid;  border-radius: 1px;}
        #wrap main #schedule #calendar a img{width: 30px;padding: 0px 2px 0px 2px;}
        #wrap main #schedule #calendar span {font-size: 25px; padding: 0 5px 0 5px;}
        /* schedule End */
		
		/* shopping Start */
        #wrap main #shopping .shop_item{width: 630px;height: 175px;padding: 40px 40px;border-bottom: 1.5px solid gray;}
		#wrap main #shopping .shop_area{padding: 2px 172px;}
		#wrap main #shopping .shop_img{width: 148px; height: 148px;float: left;}
		#wrap main #shopping a{text-decoration: none;color: black;}
		#wrap main #shopping .shop_price{font-weight: bold;}
	    #wrap main #shopping .left{color: blue;}
	    /* shopping End */
	    
	    /* account Start */
        #wrap main #account { padding:10px 0; }
        #wrap main #account #updiv table { width:50%; border-collapse:collapse; border:0px #ccc solid; margin:auto; }
        #wrap main #account #updiv table th { padding:5px 0 5px 0; border:1px #fdfdfd  solid; border-radius: 1px; background: #eccbbb;}
        #wrap main #account #updiv table td { padding:5px 0 5px 0; border-top:0.5px #f4e4e4 solid;  border-radius: 1px;}
       	#wrap main #account #downdiv table { width:100%; border-collapse:collapse; border:0px #ccc solid; margin:auto; }
        #wrap main #account #downdiv table th { padding:5px 0 5px 0; border:1px #fdfdfd  solid; border-radius: 1px; background: #eccbbb;}
        #wrap main #account #downdiv table td { padding:5px 0 5px 0; border-top:0.5px #f4e4e4 solid;  border-radius: 1px;}
        #wrap main #account #fsize {font-size: 25px;}
        #wrap main #account div img { width: 25px;padding: 0px 1px 0 0;}
        #wrap main #account a {text-decoration:none;}
        #wrap main #account #calendar 
        /* account End */
        
        /* health Start */
        #wrap main #health { padding:10px 0; }
        #wrap main #health #updiv table { width:50%; border-collapse:collapse; border:0px #ccc solid; margin:auto; }
        #wrap main #health #updiv table th { padding:5px 0 5px 0; border:1px #fdfdfd  solid; border-radius: 1px; background: #eccbbb;}
        #wrap main #health #updiv table td { padding:5px 0 5px 0; border-top:0.5px #f4e4e4 solid;  border-radius: 1px;}
       	#wrap main #health #downdiv table { width:100%; border-collapse:collapse; border:0px #ccc solid; margin:auto; }
        #wrap main #health #downdiv table th { padding:5px 0 5px 0; border:1px #fdfdfd  solid; border-radius: 1px; background: #eccbbb;}
        #wrap main #health #downdiv table td { padding:5px 0 5px 0; border-top:0.5px #f4e4e4 solid;  border-radius: 1px;}
        #wrap main #health #fsize {font-size: 25px;}
        #wrap main #health a {text-decoration:none;}
        #wrap main #health div img { width: 25px;padding: 0px 1px 0 0;}
        /* health End */
        
        /* statistics Start */
        #wrap main #statistics #PT {position: relative;top: 139px;}
        #wrap main #statistics #TB {padding-top: 50px;padding-bottom: 55px;}
        
        /* statistics Start */
	    
        /* 화면 너비 0~ 1200px */
        @media (max-width: 1200px){
            #wrap{width: 100%;  }
            #wrap section{float: right; width: 100%; }
            #wrap table{width: 100%;}
            #wrap main nav span img {display: block;width: 30%;margin: auto;padding-bottom: 24%; padding-top:4% ;}
            #wrap header #iconmenu img{width: 6%;float: right;padding-left: 2%;}
            #wrap main #meetingboard_insert textarea[name='board_title'] { width:70%; height:21px; vertical-align: middle; }
            #wrap main #meetingboard_insert .map_wrap { height:300px; margin-bottom:1px; }
            #wrap main #meetingboard_insert #map { width:100%;}
            #wrap main #meetingboard_insert #weather_info { position:inherit; width:100%;}
        }
        
        /* 화면 너비 0~ 768px */
        @media (max-width: 768px){
            #wrap{width: 100%;  }
            #wrap main{ width: 100%; position: relative; }
            #wrap section{width: 100%; float: right; }
            #wrap table{width: 100%;}
            #wrap main nav span img {display: block;width: 30%;margin: auto;padding-bottom: 38%; padding-top:4% ;}
            #wrap header #iconmenu img{width: 6%;float: right;padding-left: 2%;}
            #wrap main #meetingboard_insert textarea[name='board_title'] { width:50%; height:21px; vertical-align: middle; }
            #wrap main #meetingboard_insert .map_wrap { height:300px; margin-bottom:1px; }
            #wrap main #meetingboard_insert #map { width:100%;}
            #wrap main #meetingboard_insert #weather_info { position:inherit; width:100%;}
       		#wrap main #meetingboard_insert #Msize input {width: 36%;}
        }
        
        /* 화면 너비 0~ 480px */
        @media (max-width: 480px){
            #wrap{width: 100%;  }
            #wrap header { height: 200px; background-size:cover; }
            #wrap header #iconmenu img{width: 8%;float: right;padding-left: 2%;}
            #wrap main nav { float: none; width: 100%; }
            #wrap main nav span { display:inline; }
            #wrap main nav span img {width: 10%;padding: 1%;display: inline;} 
            #wrap main section{ float: none; width: 100%; }
            #wrap footer img{width: 60px;position: relative;top: 0px;}
            #wrap table{ width: 100%; }
            
            #wrap main #meetingboard_insert textarea[name='board_title'] { width:50%; height:21px; vertical-align: middle; }
            #wrap main #meetingboard_insert .map_wrap { height:300px; margin-bottom:1px; }
            #wrap main #meetingboard_insert #map { width:100%; }
            #wrap main #meetingboard_insert #weather_info { position:inherit; width:100%;}
            #wrap main #meetingboard_insert #Msize input {width: 35%;}
        }
        /* Layout End */
        #pop_up{
	border: 1px;
	font-size : 12px;
	width: 80px;
	height: 47px;
	background-color: rgb(199, 202, 202);
	display: none;
}
#pop_up span{
	margin: 2px;
	cursor: pointer;
}
    </style>
    <script>
    $(document).ready(function() {
    	$("footer").click(function() {
    		$(this).fadeOut();
    		$("#openFooter").fadeIn();
    		//$(this).animate({height:0}, 1000).hide();
    		//$("#openFooter").show().animate({height:'44px'}, 1000);
    	});
    	$("#openFooter").click(function() {
    		$(this).fadeOut();
    		$("footer").fadeIn();
    		//$(this).animate({height:0}, 1000).hide();
    		//$("footer").show().animate({height:'44px'}, 1000);
    	});
    });
    $("input[type='button']", "input[type='submit']").on('click', function(event) {
    	  $(this).toggleClass('start-fun');
    	  var $dioing = $('.dioing');
    	  $dioing.toggleClass('color-bg-start')
    	    .toggleClass('bg-animate-color');
    	});
    </script>
    <script type="text/javascript">

function compare(){
	
	var min = $("#min").val()
	var max = $("#max").val()
	
	if(max !="" && min != ""){
		if(min >= max){
			alert("범위에 맞게 설정하세요.");
			//location.href="meetingboard.do?command=insertboard"
		}
	}
}

</script>
<script src='js/meetingboard.js'></script>
</head>
<body>
    <div id="wrap">
        <header id="B-img" style="background-image: url(''); image-orientation: 2rad; ">
            <div style=" float: left;">
                    <a href="home.jsp"><img src="img/board/board-logo.png"; style="width: 70%;"></a>
            </div>
            <div style=" float:left;">
            	<img id="bangbang" src=""; style="width: 55%; position: relative; right: -35%; animation: circle infinite linear 10s;">
            </div>
            <div id="iconmenu">
            	<a href="#" onclick="open('whisper.do?command=message_box','메세지 함','width=360, height=300')"><img src="img/board/paper-plane.png"></a>
            	<a href="#" onclick="open('http://localhost:8090/?id=${member_dto.member_id}','채팅창','width=500, height=700')"target="_blank"><img src="img/board/chatting.png"></a>
            	<a href="#" onclick="open('friend.do?command=friend_box','메세지 함','width=360, height=300')"><img src="img/board/friendship.png"></a>
            </div>
            
        </header>
        <main>
                <nav>
                     <span><a class="myinfo" href="myinformation.do?command=memberinfo&id="><img src="img/board/myinfo.png" ></a></span>
                     <span><a class="heartbeat" href="health.do?command=health&page=1"><img src="img/board/heartbeat.png" ></a></span>
                     <span><a class="schedule" href="schedule.do?command=calendar"><img src="img/board/schedule.png" ></a></span>
                     <span><a class="group" href="meetingboard.do?command=meetingboard&page=1"><img src="img/board/group.png" ></a></span>
                     <span><a class="account" href="account.do?command=account&page=1"><img src="img/board/account.png" ></a></span>
                     <span><a class="presentation" href="statistics.do?command=statistics"><img src="img/board/presentation.png" ></a></span>
                     <span><a class="shopping" href="shopping.do?command=shop_main"><img src="img/board/shopping.png" ></a></span>
                </nav>