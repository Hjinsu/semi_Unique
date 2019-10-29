document.getElementById('search_days').value = new Date().toISOString().substring(0,10);
var gauge1;
var gauge2;
onload = function(){
	$.ajax({
		url : "statistics.do",
		type : "post",
		data : "command=today"
	}).done(function(obj){		
		var ss = obj.split("§");
		
		var obj_string1 = ss[0].split("_");
		var obj_string2 = ss[1].split("_");

		var obj_string3 = ss[2];
		var obj_string4 = ss[3];
		
		bar_chart(obj_string1,'chart1');
		bar_chart(obj_string2,'chart2');

	
		var config = liquidFillGaugeDefaultSettings();
    		config.circleColor = "#FF7777";
    		config.textColor = "#FF4444";
    		config.waveTextColor = "#FFAAAA";
    		config.waveColor = "#FFDDDD";
    		config.circleThickness = 0.1;
    		config.textVertPosition = 0.2;
    		config.waveAnimateTime = 2000;
    		config.displayPercent = false;
    		config.minValue = 0;
    		config.maxValue = 1800;    
    	gauge1 = loadLiquidFillGauge("fillgauge1", obj_string3, config);
    	
    	var config1 = liquidFillGaugeDefaultSettings();
    		config1.waveAnimateTime = 2000;
    		config1.displayPercent = false;
    		config1.minValue = 0;
    		config1.maxValue = 1800 * 7;    
    	gauge2= loadLiquidFillGauge("fillgauge2", obj_string4, config1);
    	
	}).fail(function(){
		alert("통신실패");
	});
}
$("#search_button").click(function(){
	const date = $('#search_days').val();
	
	$.ajax({
		url : "statistics.do",
		type : "post",
		data : "command=graph&date="+date
	}).done(function(obj){
		var ss = obj.split("§");
		
		var obj_string1 = ss[0].split("_");
		var obj_string2 = ss[1].split("_");

		var obj_string3 = ss[2];
		var obj_string4 = ss[3];
		
		bar_chart(obj_string1,'chart1');
		bar_chart(obj_string2,'chart2');
	
		gauge1.update(obj_string3);
		gauge2.update(obj_string4);
	
	}).fail(function(){
		alert("통신실패");
	});
	//
})

