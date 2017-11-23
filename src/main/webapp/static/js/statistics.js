var COLORS = ["#f66b44", "#FFC900", "#b8213a", "#9a73e6", "#1940e0", "#4fc107"];

var nextColorIndex = 0;

var flightsStatsByYear = loadFlightsStatsByYear();	
var flightsStatsByCarrier = loadFlightsStatsByCarrier();	

function loadFlightsStatsByYear() {
	
	var serverResponse = $.ajax({
						dataType: "json",
						//url: "http://localhost:8080/engine/booking/v2/searchBookings;user=qa;password=prueba;locale=es_ES;buyerEmail=sqa@edreams.com",
						url: "static/json/flightsStatsByYear.json",
						async: false//,
						//success: function(data) {
							//return data.response;
						//}
					}).responseText;
	
	return JSON.parse(serverResponse);
}

function loadFlightsStatsByCarrier() {
	
	var serverResponse = $.ajax({
						dataType: "json",
						//url: "http://localhost:8080/engine/booking/v2/searchBookings;user=qa;password=prueba;locale=es_ES;buyerEmail=sqa@edreams.com",
						url: "static/json/flightsStatsByCarrier.json",
						async: false//,
						//success: function(data) {
							//return data.response;
						//}
					}).responseText;
	
	return JSON.parse(serverResponse);
}

function initializeAll() {
	
	createAverageData();
	createFlightStatsTable();
	createFlightsByYearChart();	
	createDistanceByYearChart();	
	createAverageByYearChart();
	createFlightsByCarrierChart();	
	createDistanceByCarrierChart();	
	createAverageByCarrierChart();
	
	$(function() {
		$("#tabs").tabs();
	});
}

function createAverageData() {
	
	$.each(flightsStatsByYear, function(i,flightStat) {
		flightStat.average = Math.ceil(flightStat.distance / flightStat.flights);		
	});
}

function createFlightStatsTable() {
	
	$('#flightsSummary').DataTable( {
		"data": flightsStatsByYear,
		"columns": [
			{ "data": "year" },
			{ "data": "flights" },
			{ "data": "distance" },
			{ "data": "average" }
		],
		"dom": "rtip",
		"pageLength": 20,
		"orderClasses": true,
		"columnDefs": [{
						  "targets": [0,1],
						  "className": "flightsSummaryColumn"
						},
						{
						  "targets": [2,3],
						  "className": "flightsSummaryColumn thousandSeparator"
						}
		]			
	});	

	$(".thousandSeparator").each(function() {
		$(this).html(numberWithThousandSeparator($(this).html()) + ($.isNumeric($(this).html()) ? " Km" : ""));
	});	
}

function createFlightsByYearChart() {
	
	var labelsValues = [];
	var dataValues = [];
	
	var firstYear = flightsStatsByYear[0].year;
	var lastYear = flightsStatsByYear[flightsStatsByYear.length - 1].year;
	
	for(var i = firstYear; i <= lastYear; i++) {
		labelsValues.push(i);
		
		var flights = 0;
		
		$.each(flightsStatsByYear, function(j,flightStat) {
			if(flightStat.year == i) {
				flights = flightStat.flights;
			}
		});
		
		dataValues.push(flights);		
	}
	
	createLineChart(dataValues, labelsValues, "Flights By Year", "yearFlightsChart");
}

function createDistanceByYearChart() {
	
	var labelsValues = [];
	var dataValues = [];
	
	var firstYear = flightsStatsByYear[0].year;
	var lastYear = flightsStatsByYear[flightsStatsByYear.length - 1].year;
	
	for(var i = firstYear; i <= lastYear; i++) {
		labelsValues.push(i);
		
		var distance = 0;
		
		$.each(flightsStatsByYear, function(j,flightStat) {
			if(flightStat.year == i) {
				distance = flightStat.distance;
			}
		});
		
		dataValues.push(distance);		
	}
	
	createLineChart(dataValues, labelsValues, "Distance By Year", "yearDistanceChart");
}

function createAverageByYearChart() {
	
	var labelsValues = [];
	var dataValues = [];
	
	var firstYear = flightsStatsByYear[0].year;
	var lastYear = flightsStatsByYear[flightsStatsByYear.length - 1].year;
	
	for(var i = firstYear; i <= lastYear; i++) {
		labelsValues.push(i);
		
		var average = 0;
		
		$.each(flightsStatsByYear, function(j,flightStat) {
			if(flightStat.year == i) {
				average = flightStat.average;
			}
		});
		
		dataValues.push(average);		
	}
	
	createLineChart(dataValues, labelsValues, "Average By Year", "yearAverageChart");
}



function createFlightsByCarrierChart() {
	
	var labelsValues = [];
	var dataValues = [];
	
	$.each(flightsStatsByCarrier, function(j,flightStat) {		
		labelsValues.push(flightStat.carrier);
		dataValues.push(flightStat.flights);
	});
	
	createBarChart(dataValues, labelsValues, "Flights By Carrier", "carrierFlightsChart");
}

function createDistanceByCarrierChart() {
	
	var labelsValues = [];
	var dataValues = [];
	
	$.each(flightsStatsByCarrier, function(j,flightStat) {		
		labelsValues.push(flightStat.carrier);
		dataValues.push(flightStat.distance);
	});
	
	createBarChart(dataValues, labelsValues, "Distance By Carrier", "carrierDistanceChart");
}

function createAverageByCarrierChart() {
	
	var labelsValues = [];
	var dataValues = [];
	
	$.each(flightsStatsByCarrier, function(j,flightStat) {		
		labelsValues.push(flightStat.carrier);
		dataValues.push(flightStat.average);
	});
	
	createBarChart(dataValues, labelsValues, "Average By Carrier", "carrierAverageChart");
}

function createLineChart(dataValues, labelsValues, labelName, canvasID) {	
	
	var data = {
		labels: labelsValues,
		datasets: [
			{
				label: labelName,
				fillColor: "rgba(220,220,220,0.2)",
				strokeColor: "#FF0084",
				pointColor: "#0073EA",
				pointStrokeColor: "#0073EA",
				pointHighlightFill: "#FF0084",
				pointHighlightStroke: "rgba(220,220,220,1)",
				data: dataValues
			}]
	};
		
	var ctx = $("#" + canvasID).get(0).getContext("2d");
	
	var myLineChart = new Chart(ctx).Line(data, lineOptions);	
}

function createPolarAreaChart(data, canvasID) {
		
	var ctx = $("#" + canvasID).get(0).getContext("2d");
	
	var myPolarChart = new Chart(ctx).Polar(data, polarAreaOptions);	
}

function createBarChart(dataValues, labelsValues, labelName, canvasID) {
	
	var data = {
		labels: labelsValues,
		datasets: [
			{
				label: labelName,
				fillColor: "#0073EA",
				strokeColor: "black",
				highlightFill: "#FF0084",
				highlightStroke: "black",
				data: dataValues
			}]
	};
		
	var ctx = $("#" + canvasID).get(0).getContext("2d");
	
	var myBarChart = new Chart(ctx).Bar(data, barOptions);	
}

function getColor() {

    if (COLORS.length == nextColorIndex) {
        nextColorIndex = 0;
    }
    return COLORS[nextColorIndex++];
}