var COLORS = ["#f66b44", "#FFC900", "#b8213a", "#9a73e6", "#1940e0", "#4fc107"];

var nextColorIndex = 0;

var flightsStatsByYear = loadFlightsStatsByYear();	
var flightsStatsByCarrier = loadFlightsStatsByCarrier();	

function loadFlightsStatsByYear() {
	
	var serverResponse = $.ajax({
						dataType: "json",
						url: "/api/stats/flights/year/",
						async: false
					}).responseText;
	
	return JSON.parse(serverResponse);
}

function loadFlightsStatsByCarrier() {
	
	var serverResponse = $.ajax({
						dataType: "json",
						url: "/api/stats/flights/carrier/",
						async: false
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

    var flightsStatsByYearTable = clone(flightsStatsByYear);

    var totalFlights = getTotalFlights(flightsStatsByYearTable);
    var totalDistance = getTotalDistance(flightsStatsByYearTable);
    var totalAverage = Math.ceil(totalDistance / totalFlights);

    var maxFlights = getMaxFlights(flightsStatsByYearTable);
    var maxDistance = getMaxDistance(flightsStatsByYearTable);
    var maxAverage = getMaxAverage(flightsStatsByYearTable);

    var minFlights = getMinFlights(flightsStatsByYearTable);
    var minDistance = getMinDistance(flightsStatsByYearTable);
    var minAverage = getMinAverage(flightsStatsByYearTable);


    flightsStatsByYearTable.push({  "year" : "Total",
                                    "flights": totalFlights,
                                    "distance": totalDistance,
                                    "average": totalAverage})

	$('#flightsSummary').DataTable( {
		"data": flightsStatsByYearTable,
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
		],
		"createdRow": function( row, data, dataIndex ) {
            if ( data['year'] == "Total" ) {
                $(row).addClass( 'flightsSummaryTotal' );
            }
            if( data['flights'] == maxFlights ) {
                $('td', row).eq(1).addClass('flightsSummaryMax');
            }
            if( data['distance'] == maxDistance ) {
                $('td', row).eq(2).addClass('flightsSummaryMax');
            }
            if( data['average'] == maxAverage ) {
                $('td', row).eq(3).addClass('flightsSummaryMax');
            }
            if( data['flights'] == minFlights ) {
                $('td', row).eq(1).addClass('flightsSummaryMin');
            }
            if( data['distance'] == minDistance ) {
                $('td', row).eq(2).addClass('flightsSummaryMin');
            }
            if( data['average'] == minAverage ) {
                $('td', row).eq(3).addClass('flightsSummaryMin');
            }
        }
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

function getTotalFlights() {
    var totalFlights = 0;
    $.each(flightsStatsByYear, function(i,flightStat) {
        totalFlights += flightStat.flights;
    });
    return totalFlights;
}

function getTotalDistance() {
    var totalDistance = 0;
    $.each(flightsStatsByYear, function(i,flightStat) {
    	totalDistance += flightStat.distance;
    });
    return totalDistance;
}

function getMaxFlights() {
    var maxFlights = 0;
    $.each(flightsStatsByYear, function(i,flightStat) {
            maxFlights = flightStat.flights > maxFlights ? flightStat.flights : maxFlights;
    });
    return maxFlights;
}

function getMaxDistance() {
    var maxDistance = 0;
    $.each(flightsStatsByYear, function(i,flightStat) {
            maxDistance = flightStat.distance > maxDistance ? flightStat.distance : maxDistance;
    });
    return maxDistance;
}

function getMaxAverage() {
    var maxAverage = 0;
    $.each(flightsStatsByYear, function(i,flightStat) {
            maxAverage = flightStat.average > maxAverage ? flightStat.average : maxAverage;
    });
    return maxAverage;
}

function getMinFlights() {
    var minFlights = flightsStatsByYear[0].flights;
    $.each(flightsStatsByYear, function(i,flightStat) {
            minFlights = flightStat.flights < minFlights ? flightStat.flights : minFlights;
    });
    return minFlights;
}

function getMinDistance() {
    var minDistance = flightsStatsByYear[0].distance;
    $.each(flightsStatsByYear, function(i,flightStat) {
            minDistance = flightStat.distance < minDistance ? flightStat.distance : minDistance;
    });
    return minDistance;
}

function getMinAverage() {
    var minAverage = flightsStatsByYear[0].average;
    $.each(flightsStatsByYear, function(i,flightStat) {
            minAverage = flightStat.average < minAverage ? flightStat.average : minAverage;
    });
    return minAverage;
}

function clone(obj) {
     var copy;

     // Handle the 3 simple types, and null or undefined
     if (null == obj || "object" != typeof obj) return obj;

     // Handle Date
     if (obj instanceof Date) {
         copy = new Date();
         copy.setTime(obj.getTime());
         return copy;
     }

     // Handle Array
     if (obj instanceof Array) {
         copy = [];
         for (var i = 0, len = obj.length; i < len; i++) {
             copy[i] = clone(obj[i]);
         }
         return copy;
     }

     // Handle Object
     if (obj instanceof Object) {
         copy = {};
         for (var attr in obj) {
             if (obj.hasOwnProperty(attr)) copy[attr] = clone(obj[attr]);
         }
         return copy;
     }

     throw new Error("Unable to copy obj! Its type isn't supported.");
 }