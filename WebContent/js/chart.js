function bar_chart(ss, id){
	var c = document.getElementById(id);
	
	while(c.hasChildNodes()){
		c.removeChild(c.firstChild);
	}
	
	var obj_label = ss[0];
	var obj_labels = obj_label.split("/");
	
	var obj_value = ss[1];
	var obj_values = obj_value.split("/");
	
	var data = {
			  labels: [ ],
			  series: [
			    {
			      label: '',
			      values: []
			    },]
			};
	
	data.labels = obj_labels;
	data.series[0].values = obj_values;
	
	var max_value = Math.max.apply(null,obj_values) / 100;
	
	var chartWidth       = 300,
		barHeight        = 20,
		groupHeight      = barHeight * data.series.length,
		gapBetweenGroups = 10,
		spaceForLabels   = 150,
		spaceForLegend   = 150;
	// Zip the series data together (first values, second values, etc.)
	var zippedData = [];
	for (var i=0; i<data.labels.length; i++) {
		for (var j=0; j<data.series.length; j++) {
			zippedData.push(data.series[j].values[i]);
		}
	}
	// Color scale
	var color = d3.scale.category20();
	var chartHeight = barHeight * zippedData.length + gapBetweenGroups * data.labels.length;
	
	var x = d3.scale.linear()
	   .domain([0, max_value * 100])
	   .range([0, chartWidth]);
	
	var y = d3.scale.linear()
	   		   .range([chartHeight + gapBetweenGroups, 0]);

	var yAxis = d3.svg.axis()
	    	   	   .scale(y)
	    	   	   .tickFormat('')
	    	   	   .tickSize(0)
	    	   	   .orient("left");
	// Specify the chart area and dimensions
	var chart = d3.select("#"+id).append("svg")
				   .attr("width", chartWidth + 25)
				   .attr("height", chartHeight);
	
	// Create bars
	var bar = chart.selectAll("g")
					 .data(zippedData)
					 .enter().append("g")
					 .attr("transform", function(d, i) {
						 return "translate(" +25+"," + (i * barHeight + gapBetweenGroups * (0.5 + Math.floor(i/data.series.length))) + ")";
					 });
	// Create rectangles of the correct width
	bar.append("rect")
		.attr("fill", function(d,i) { return color(i % data.series.length); })
		.attr("class", "bar")
		.attr("width", x)
		.attr("height", barHeight - 1);
	
	// Add text label in bar
	bar.append("text")
		.attr("x", function(d) { return x(d) - 3; })
		.attr("y", barHeight / 2)
		.attr("fill", "red")
		.attr("dy", ".35em")
		.text(function(d) { return d +'원'; });
	// Draw labels
	bar.append("text")
		.attr("class", "label")
		.attr("x", function(d) { return - 3; })
		.attr("y", groupHeight / 2)
		.attr("dy", ".35em")
		.text(function(d,i) {
				if (i % data.series.length === 0)
					return data.labels[Math.floor(i/data.series.length)];
				else
					return ""});
	chart.append("g")
		 .attr("class", "y axis")
		 .attr("transform", "translate(" + 25 + ", " + -gapBetweenGroups/2 + ")")
		 .call(yAxis);
}
