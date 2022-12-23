var flightsStatsByYear = loadFlightsStatsByYear();
var flightsStatsByCarrier = loadFlightsStatsByCarrier();	
var allFlights = loadAllFlights();
var airportsStats = loadAirportsStats();

function loadFlightsStatsByYear() {
	
	var serverResponse = $.ajax({
						dataType: "json",
						url: "/api/stats/flights/year",
						async: false
					}).responseText;
	
	return JSON.parse(serverResponse);
}

function loadFlightsStatsByCarrier() {
	
	var serverResponse = $.ajax({
						dataType: "json",
						url: "/api/stats/flights/carrier",
						async: false
					}).responseText;
	
	return JSON.parse(serverResponse);
}

function loadAllFlights() {

	var serverResponse = $.ajax({
						dataType: "json",
						url: "/api/flights",
						async: false
					}).responseText;

	return JSON.parse(serverResponse);
}

function loadAirportsStats() {

	var serverResponse = $.ajax({
						dataType: "json",
						url: "/api/stats/airports",
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
	createTimeByYearChart();
	createTimeAverageByYearChart();
	createTimeByCarrierChart();
	createTimeAverageByCarrierChart();
	createAllFlightsTable();
	createAirportsStatsTable();

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
    var totalTime = getTotalTime(flightsStatsByYearTable);
    var totalAverageTime = totalTime / totalFlights;

    var maxFlights = getMaxFlights(flightsStatsByYearTable);
    var maxDistance = getMaxDistance(flightsStatsByYearTable);
    var maxAverage = getMaxAverage(flightsStatsByYearTable);
    var maxTime = getMaxTime(flightsStatsByYearTable);
    var maxAverageTime = getMaxAverageTime(flightsStatsByYearTable);

    var minFlights = getMinFlights(flightsStatsByYearTable);
    var minDistance = getMinDistance(flightsStatsByYearTable);
    var minAverage = getMinAverage(flightsStatsByYearTable);
    var minTime = getMinTime(flightsStatsByYearTable);
    var minAverageTime = getMinAverageTime(flightsStatsByYearTable);


    flightsStatsByYearTable.push({  "year" : "Total",
                                    "flights": totalFlights,
                                    "distance": totalDistance,
                                    "average": totalAverage,
                                    "time": totalTime,
                                    "averageTime": totalAverageTime})

	$('#flightsSummary').DataTable( {
		"data": flightsStatsByYearTable,
		"columns": [
			{ "data": "year" },
			{ "data": "flights" },
			{ "data": "distance" },
			{ "data": "average" },
			{ "data": "time" },
			{ "data": "averageTime" }
		],
		"dom": "rtip",
		"pageLength": 30,
       // "scrollY": 800,
		"orderClasses": true,
		"columnDefs": [{
						  "targets": [0,1],
						  "className": "flightsSummaryColumn"
						},
						{
						  "targets": [2,3],
						  "className": "flightsSummaryColumn",
						  "render": function ( data, type, row, meta ) {
                              return numberWithThousandSeparator(data) + ($.isNumeric(data) ? " Km" : "");
                          },
						},
                        {
                          "targets": [4,5],
                          "className": "flightsSummaryColumn",
                          "render": function ( data, type, row, meta ) {
                               if($.isNumeric(data)) {
                                   var rounded = Math.round(data * 10) / 10;
                                   return rounded + " Hours"
                          	   }
                          }
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
            if( data['time'] == maxTime ) {
                $('td', row).eq(4).addClass('flightsSummaryMax');
            }
            if( data['averageTime'] == maxAverageTime ) {
                $('td', row).eq(5).addClass('flightsSummaryMax');
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
            if( data['time'] == minTime ) {
                $('td', row).eq(4).addClass('flightsSummaryMin');
            }
            if( data['averageTime'] == minAverageTime ) {
                $('td', row).eq(5).addClass('flightsSummaryMin');
            }
        }
	});
}

function createAllFlightsTable() {

	$('#allFlights').DataTable( {
		"data": allFlights,
		"columns": [
		    { "data": "date" },
			{ "data": "number" },
			{ "data": "origin"},
			{ "data": "destination"},
			{ "data": "carrier.name" },
			{ "data": "distance" },
			{ "data": "duration" }
		],
		"dom": "rtip",
		"pageLength": 25,
		"orderClasses": true,
		"columnDefs": [{
						  "targets": [0],
						  "className": "allFlightsColumnRight",
						  "type": "date-euro",
						  "width": "5%"
						},
						{
                          "targets": [1],
                          "className": "allFlightsColumnRight",
                          "width": "5%"
                        },
                        {
                          "targets": [2],
                          "className": "allFlightsColumnLeft",
                          "render": function ( data, type, row, meta ) {
                            return data.name + ' (' + data.iataCode + ')';
                          },
                          "width": "15%"
                        },
                        {
                          "targets": [3],
                          "className": "allFlightsColumnLeft",
                          "render": function ( data, type, row, meta ) {
                            return data.name + ' (' + data.iataCode + ')';
                          },
                          "width": "15%"
                        },
                        {
                          "targets": [4],
                          "className": "allFlightsColumnLeft",
                          "width": "10%"
                        },
                        {
                          "targets": [5],
                          "className": "allFlightsColumnRight",
                          "width": "5%",
                          "render": function ( data, type, row, meta ) {
                              return numberWithThousandSeparator(data) + ($.isNumeric(data) ? " Km" : "");
                          },
                        },
                        {
                          "targets": [6],
                          "className": "allFlightsColumnRight",
                          "width": "5%"
                        }
		]
	});
}

function createAirportsStatsTable() {

	$('#airportsSummary').DataTable( {
		"data": airportsStats,
		"columns": [
		    { "data": "name" },
			{ "data": "origin"},
			{ "data": "destination"},
			{ "data": "total" }
		],
		"dom": "rtip",
		"pageLength": 25,
		"orderClasses": true,
		"columnDefs": [{
						  "targets": [0],
						  "className": "airportsSummaryColumnLeft",
						  "width": "5%"
						},
						{
                          "targets": [1],
                          "className": "airportsSummaryColumnRight",
                          "width": "5%"
                        },
                        {
                          "targets": [2],
                          "className": "airportsSummaryColumnRight",
                          "width": "15%"
                        },
                        {
                          "targets": [3],
                          "className": "airportsSummaryColumnRight",
                          "width": "15%"
                        }
		]
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

function createTimeByYearChart() {

	var labelsValues = [];
	var dataValues = [];

	var firstYear = flightsStatsByYear[0].year;
	var lastYear = flightsStatsByYear[flightsStatsByYear.length - 1].year;

	for(var i = firstYear; i <= lastYear; i++) {
		labelsValues.push(i);

		var time = 0;

		$.each(flightsStatsByYear, function(j,flightStat) {
			if(flightStat.year == i) {
				time = flightStat.time;
			}
		});

		dataValues.push(time);
	}

	createLineChart(dataValues, labelsValues, "Time By Year", "yearTimeChart");
}

function createTimeAverageByYearChart() {

	var labelsValues = [];
	var dataValues = [];

	var firstYear = flightsStatsByYear[0].year;
	var lastYear = flightsStatsByYear[flightsStatsByYear.length - 1].year;

	for(var i = firstYear; i <= lastYear; i++) {
		labelsValues.push(i);

		var averageTime = 0;

		$.each(flightsStatsByYear, function(j,flightStat) {
			if(flightStat.year == i) {
				averageTime = flightStat.averageTime;
			}
		});
		var rounded = Math.round(averageTime * 10) / 10;
		dataValues.push(rounded);
	}

	createLineChart(dataValues, labelsValues, "Average Time By Year", "yearTimeAverageChart");
}

function createTimeByCarrierChart() {

	var labelsValues = [];
	var dataValues = [];

	$.each(flightsStatsByCarrier, function(j,flightStat) {
		labelsValues.push(flightStat.carrier);
		dataValues.push(flightStat.time);
	});

	createBarChart(dataValues, labelsValues, "Time By Carrier", "timeCarrierChart");
}

function createTimeAverageByCarrierChart() {

	var labelsValues = [];
	var dataValues = [];

	$.each(flightsStatsByCarrier, function(j,flightStat) {
		labelsValues.push(flightStat.carrier);
		var rounded = Math.round(flightStat.averageTime * 10) / 10;
		dataValues.push(rounded);
	});

	createBarChart(dataValues, labelsValues, "Average Time By Carrier", "timeCarrierAverageChart");
}

function createLineChart(dataValues, labelsValues, labelName, canvasID) {	
	
	var data = {
		labels: labelsValues,
		datasets: [
			{
				label: labelName,
				fillColor: "#CADCFC",
				strokeColor: "#00246B",
				pointColor: "#8AB6F9",
				pointStrokeColor: "#0073EA",
				pointHighlightFill: "#8AB6F9",
				pointHighlightStroke: "#00246B",
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
				fillColor: "#CADCFC",
				strokeColor: "#00246B",
				highlightFill: "#8AB6F9",
				highlightStroke: "00246B",
				data: dataValues
			}]
	};
		
	var ctx = $("#" + canvasID).get(0).getContext("2d");
	
	var myBarChart = new Chart(ctx).Bar(data, barOptions);	
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

function getTotalTime() {
    var totalTime = 0;
    $.each(flightsStatsByYear, function(i,flightStat) {
    	totalTime += flightStat.time;
    });
    return totalTime;
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

function getMaxTime() {
    var maxTime = 0;
    $.each(flightsStatsByYear, function(i,flightStat) {
            maxTime = flightStat.time > maxTime ? flightStat.time : maxTime;
    });
    return maxTime;
}

function getMaxAverageTime() {
    var maxAverageTime = 0;
    $.each(flightsStatsByYear, function(i,flightStat) {
            maxAverageTime = flightStat.averageTime > maxAverageTime ? flightStat.averageTime : maxAverageTime;
    });
    return maxAverageTime;
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

function getMinTime() {
    var minTime = flightsStatsByYear[0].time;
    $.each(flightsStatsByYear, function(i,flightStat) {
            minTime = flightStat.time < minTime ? flightStat.time : minTime;
    });
    return minTime;
}

function getMinAverageTime() {
    var minAverageTime = flightsStatsByYear[0].averageTime;
    $.each(flightsStatsByYear, function(i,flightStat) {
            minAverageTime = flightStat.averageTime < minAverageTime ? flightStat.averageTime : minAverageTime;
    });
    return minAverageTime;
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