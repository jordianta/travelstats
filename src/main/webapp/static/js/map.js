var DEFAULT_ANIMATION_SPEED = 3000;
var MAP_CONTAINER = "map-canvas";
var DATA_MAP_CONTAINER ="datamaps-container";
var BUBBLE_RADIUS = 10;
var BUBBLE_COLOR_ORIGIN = "#CADCFC";//"rgba(184,33,58, 0.75)";
var BUBBLE_COLOR_DESTINATION = "#CADCFC";//"rgba(184,33,58, 0.75)";//"rgba(255, 0, 0, 0.75)";
var BUBBLE_COLOR_POINT = "#00246B";
var MAXIMUM_NUMBER_OF_ARCHES = 0; // if 0 --> no maximum number of arches
var FILL_COLOR = "rgb(211, 216, 222)";

function getColorArray(size) {

    var colors = new Array();

    for(var i = 0;i < size;i++) {
        if(i == 0) {
            colors[i] = getRandomColor();
        } else {
            var previousColor = colors[i - 1];
            var color;
            do {
                color = getRandomColor();
            } while(!colorIsValid(previousColor, color));
            colors[i] = color;
        }
    }
    return colors;
}

function getRandomColor() {
    var letters = '0123456789ABCDEF';
    var color = "";
    for (var i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

function colorIsValid(hex1, hex2) {
    // get red/green/blue int values of hex1
    var r1 = parseInt(hex1.substring(0, 2), 16);
    var g1 = parseInt(hex1.substring(2, 4), 16);
    var b1 = parseInt(hex1.substring(4, 6), 16);
    // get red/green/blue int values of hex2
    var r2 = parseInt(hex2.substring(0, 2), 16);
    var g2 = parseInt(hex2.substring(2, 4), 16);
    var b2 = parseInt(hex2.substring(4, 6), 16);

    if(colorIsTooDarkOrTooLight(r2, g2, b2)) {
        return false;
    }

    // calculate differences between reds, greens and blues
    var r = 255 - Math.abs(r1 - r2);
    var g = 255 - Math.abs(g1 - g2);
    var b = 255 - Math.abs(b1 - b2);
    // limit differences between 0 and 1
    r /= 255;
    g /= 255;
    b /= 255;
    // 0 means opposite colors, 1 means same colors
    return (r + g + b) / 3 < 0.5;
}

function colorIsTooDarkOrTooLight(r, g, b) {
    var darkness = 0.2126 * r + 0.7152 * g + 0.0722 * b; // per ITU-R BT.709
    return darkness < 90 && darkness < 120;
}

var colorArray = getColorArray(22);
var index = 0;

var YEARS_COLORS = [
	{"year":1996, "color": "#" + colorArray[index++]},
	{"year":2000, "color": "#" + colorArray[index++]},
	{"year":2005, "color": "#" + colorArray[index++]},
	{"year":2006, "color": "#" + colorArray[index++]},
	{"year":2007, "color": "#" + colorArray[index++]},
	{"year":2008, "color": "#" + colorArray[index++]},
	{"year":2009, "color": "#" + colorArray[index++]},
	{"year":2010, "color": "#" + colorArray[index++]},
	{"year":2011, "color": "#" + colorArray[index++]},
	{"year":2012, "color": "#" + colorArray[index++]},
	{"year":2013, "color": "#" + colorArray[index++]},
	{"year":2014, "color": "#" + colorArray[index++]},
	{"year":2015, "color": "#" + colorArray[index++]},
	{"year":2016, "color": "#" + colorArray[index++]},
	{"year":2017, "color": "#" + colorArray[index++]},
	{"year":2018, "color": "#" + colorArray[index++]},
	{"year":2019, "color": "#" + colorArray[index++]},
	{"year":2020, "color": "#" + colorArray[index++]},
	{"year":2021, "color": "#" + colorArray[index++]},
	{"year":2022, "color": "#" + colorArray[index++]},
    {"year":2023, "color": "#" + colorArray[index++]},
    {"year":2024, "color": "#" + colorArray[index++]}
];

var mapFills = {};
var countries = {};
var currentPointers = [];
var lastUpdateTimestamp = new Date();
var flights = loadFlights();
var currentYear = 0;
var selectedYears = [];
var tmpArchInsertedTimeOut;
var currentAnimationSpeed = DEFAULT_ANIMATION_SPEED;
var animated = true;
var mapContainerOriginalWidth;
var mapContainerOriginalHeight;

function initializeAll() {

    mapContainerOriginalWidth = document.getElementById(MAP_CONTAINER).clientWidth;
    mapContainerOriginalHeight = document.getElementById(MAP_CONTAINER).clientHeight;

	setEvents();
	
	checkAllYearsSelectors();
	
	initializeMap();
}


function initializeMap() {

    document.getElementById(MAP_CONTAINER).innerHTML ="";
    document.getElementById(DATA_MAP_CONTAINER).innerHTML ="";

    //adapt sizes to the screen
    var mapWidth =  mapContainerOriginalWidth * 0.95;
    var mapHeight =  mapContainerOriginalHeight * 0.95;


    var map = new Datamap({
        element: document.getElementById(MAP_CONTAINER),
        projection: 'mercator',
        height: mapHeight,
        width: mapWidth,
		fills: {
		  defaultFill: FILL_COLOR
		},
		responsive: true
    });
	
	currentPointers = [];

    archChargeFlights(map);
}

function getData() {

    var values = {};

    $.each(countries, function (i, country) {
        var item = { fillKey: country};
        values[country] = item;
    });
    return values;
}

function getColor(year) {
	
	var color = "#000000";
	
	console.log(year);
	
	$.each(YEARS_COLORS, function(index, element) {
		console.log(element);
		if(element.year == year) {
			console.log("OK " + element.color);
			color = element.color;			
		}		
	});

    return color;
}

function archChargeFlights(map) {
    var archsInMap = [];
    var archArray = [];

    $.each(flights, function (i, flight) {

        var orig = flight.origin.iataCode;
        var origName = flight.origin.name;
        var dest = flight.destination.iataCode;
        var destName = flight.destination.name;
		
		var date = flight.date.split("-");
		var day = date[0];
		var month = date[1];
		var year = date[2];
		
		if($.inArray(year, selectedYears) >= 0) {
		
			var dateString = ('0' + day).slice(-2) + "/" + ('0' + month).slice(-2) + "/" + year;

			var arch = {
				origin: {
					latitude: flight.origin.latitude,
					longitude: flight.origin.longitude
				},
				destination: {
					latitude: flight.destination.latitude,
					longitude: flight.destination.longitude
				},
				options: {
					strokeWidth: 2,
					strokeColor: getColor(year),
					greatArc: true
				},
				originCity: orig,
				originCityName: origName,
				destinationCity: dest,
				destinationCityName: destName,
				date:dateString,
				distance:flight.distance,
				carrier:flight.carrier.name,
				flightYear:year
			};

			archArray.push(arch);
		}
    });
	
	archArray.reverse();

    tempArchInsert(map, archArray, archsInMap);
}

function tempArchInsert(map, archsToInsert, archsInserted) {

    if (archsToInsert.length > 0) {
        var insertedArchsNumber = archsInserted.length;
        if(MAXIMUM_NUMBER_OF_ARCHES!=0 && insertedArchsNumber>=MAXIMUM_NUMBER_OF_ARCHES){
            archsInserted = [];
        }

        var arch = archsToInsert.pop();

        archsInserted.push(arch);

        clearCityLabels();

       /* createCityLabel(map, arch.origin.latitude, arch.origin.longitude, arch.originCity);
        createCityLabel(map, arch.destination.latitude, arch.destination.longitude, arch.destinationCity);*/

        //paint the flight data into the lower panel
		if(animated) {
			$("#depCityFlightInfoPanel").html(arch.originCityName);
			$("#depCodeCityFlightInfoPanel").html("(" + arch.originCity + ")");
			$("#arrCityFlightInfoPanel").html(arch.destinationCityName);
			$("#arrCodeCityFlightInfoPanel").html("(" + arch.destinationCity + ")");
			$("#depDateFlightInfoPanel").html(arch.date);
			$("#carrierFlightInfoPanel").html(arch.carrier);
			$("#distanceFlightInfoPanel").html(numberWithThousandSeparator(arch.distance) + " km");		
			
			$(".flightInfoPanel").show();
		}

        createBubbles(map, arch.origin.latitude, arch.origin.longitude, arch.destination.latitude, arch.destination.longitude);

        //insert the arch into the map
        map.arc(archsInserted, {
            greatArc: true,
            animationSpeed: currentAnimationSpeed
        });

        //setTimeout(function () {createCityLabel(map, arch.destination.latitude, arch.destination.longitude, arch.destinationCity)}}, DEFAULT_ANIMATION_SPEED);

        tmpArchInsertedTimeOut = setTimeout(function () {
            tempArchInsert(map, archsToInsert, archsInserted)
        }, currentAnimationSpeed);

    }else{
		
		map.bubbles(currentPointers, {
            highlightOnHover: false
			}
		);
		
		clearCityLabels();
		
		hideFlightPanel();	
		
        /*setTimeout(function () {
            archReload();
        }, currentAnimationSpeed);*/
    }
}

function createCityLabel(map, lat, lng, cityCode) {

    var coords = map.latLngToXY(lat, lng);
    var x = coords[0];
    var y = coords[1];

    if ($("#datamaps-hoverover-" + cityCode).length) {
        return;
    }

    $("#"+DATA_MAP_CONTAINER).append('<div class="datamaps-hoverover" id="datamaps-hoverover-' + cityCode + '" style="z-index: 10001; position: absolute; display: block; top: ' + y + 'px; left: ' + x + 'px;"><div class="hoverinfoBubble">' + cityCode + '</div></div>')

    /*var width = $("#datamaps-hoverover-" + id)[0].offsetWidth;
     var height = $("#datamaps-hoverover-" + id)[0].offsetHeight;

     var leftValue = $("#datamaps-hoverover-" + id).position().left;
     var topValue = $("#datamaps-hoverover-" + id).position().top;

     leftValue = leftValue - width / 2 + 10;
     topValue = topValue - height / 2 + 10;

     $("#datamaps-hoverover-" + id).css({ top: topValue + 'px', left: leftValue + 'px'});*/
}

function clearCityLabels() {
    $("#"+DATA_MAP_CONTAINER).html("");
}

function hideFlightPanel() {
	$(".flightInfoPanel").hide();
	$("#flightImageContainer").hide();
}

function createBubbles(map, latOrigin, lngOrigin, latDestination, lngDestination) {

    var bubbleOrigin = { name: "", radius: BUBBLE_RADIUS, color: BUBBLE_COLOR_ORIGIN, latitude: latOrigin, longitude: lngOrigin};
    var bubbleDestination = { name: "", radius: BUBBLE_RADIUS, color: BUBBLE_COLOR_DESTINATION, latitude: latDestination, longitude: lngDestination};

    var bubblePointOrigin = { name: "", radius: 3, color: BUBBLE_COLOR_POINT, borderColor:BUBBLE_COLOR_POINT, latitude: latOrigin, longitude: lngOrigin};
    var bubblePointDestination = { name: "", radius: 3, color: BUBBLE_COLOR_POINT, borderColor:BUBBLE_COLOR_POINT, latitude: latDestination, longitude: lngDestination};

    currentPointers.push(bubblePointOrigin);
    currentPointers.push(bubblePointDestination);

    var bubbles = [];

    bubbles.push(bubbleOrigin);
    bubbles.push(bubbleDestination);
    bubbles = bubbles.concat(currentPointers);

    map.bubbles(bubbles,
        {
            highlightOnHover: false
        }
    );
}

function paintCurrentDate (){
    var DATE_CONTAINER = "depDateFlightInfoPanel";
    var currentDate = new Date();
    var day = ('0' + currentDate.getDate()).slice(-2);
    var month = ('0' + (currentDate.getMonth() + 1)).slice(-2);
    var year = currentDate.getFullYear();
    document.getElementById(DATE_CONTAINER).innerHTML = day+"/"+month+"/"+year;
}

function loadFlights() {
	
	var serverResponse = $.ajax({
						dataType: "json",
						url: "/api/flights",
						async: false
					}).responseText;
	
	return JSON.parse(serverResponse);
}

function setEvents() {
	 
	$("[id^=checkYear]").click(function() {
		
		var initializeMapFlag = selectedYears.length <= 0;

		var id = $(this).attr('id');

		var year = id.substring(id.indexOf("checkYear") + 9);
		
		clearTimeout(tmpArchInsertedTimeOut);

		if($.inArray(year, selectedYears) >= 0) {
			
			$(this).css('background-color', FILL_COLOR);
			selectedYears.splice(selectedYears.indexOf(year), 1);
			
			if(selectedYears.length <= 0) {
				hideFlightPanel();
			}
			
			if(animated) {
				initializeMap();
			} else {
				$("[id=datamaps-arc-" + year + "]").each(function() {
					$(this).css("visibility", "hidden");
				});
			}			
			console.log("remove " + year); 
		} else {
			$(this).css('background-color', getColor(year));
			selectedYears.push(year);
			
			if(animated) {
				initializeMap();
			} else {
				$("[id=datamaps-arc-" + year + "]").each(function() {
					$(this).css("visibility", "visible");
				});
			}
			console.log("push " + year); 
		}
	});	
	
	$("#checkAll").click(function() {
		
		if(animated) {		
			clearTimeout(tmpArchInsertedTimeOut);
			checkAllYearsSelectors();
			initializeMap();
		
		} else {
			checkAllYearsSelectors();
			showAllArchs();
		}
	});	

	$("#checkClear").click(function() {
		
		selectedYears = [];		
		
		$("[id^=checkYear]").each(function() {			
			$(this).css('background-color', FILL_COLOR);
		});
		
		hideFlightPanel();
		clearTimeout(tmpArchInsertedTimeOut);
		
		if(animated) {
			selectedYears = [];
			initializeMap();
			
		} else {
			hideFlightPanel();
			hideAllArchs();
		}
	});	

	$("#checkAnimated").click(function() {
		
		if(animated) {
			animated = false;
			currentAnimationSpeed = 0;
			$(this).attr("class", "checkAnimated-unselected");
		} else {
			animated = true;
			currentAnimationSpeed = DEFAULT_ANIMATION_SPEED;
			$(this).attr("class", "checkAnimated-selected");
		}
		checkAllYearsSelectors();
		clearTimeout(tmpArchInsertedTimeOut);
		initializeMap();
	});
}

function showAllArchs() {
	$("[id^=datamaps-arc-]").each(function() {
		$(this).css("visibility", "visible");
	});
}

function hideAllArchs() {
	$("[id^=datamaps-arc-]").each(function() {
		$(this).css("visibility", "hidden");
	});
}

function fillSelectedYears() {
	
	selectedYears = [];

	$("[id^=checkYear]").each(function() {

		var id = $(this).attr('id');

		var year = id.substring(id.indexOf("checkYear") + 9);
		
		selectedYears.push(year);
	});
}

function checkAllYearsSelectors() {

	selectedYears = [];	
	 
	$("[id^=checkYear]").each(function() {

		var id = $(this).attr('id');

		var year = id.substring(id.indexOf("checkYear") + 9);

		$(this).css('background-color', getColor(year));
		
		selectedYears.push(year);
	});
}