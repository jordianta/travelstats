var MAP_CONTAINER = "map-canvas";
var DATA_MAP_CONTAINER ="datamaps-container";
var DEFAULT_ANIMATION_SPEED = 3000;
var NUM_STEPS = 500; //Change this to set animation resolution
var TIME_PER_STEP = 5; //Change this to alter animation speed
var map;
var animated = true;

function getColorArray(size) {

    var colors = [];

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
    // 0 means opposit colors, 1 means same colors
    return (r + g + b) / 3 < 0.4;
}

function colorIsTooDarkOrTooLight(r, g, b) {
    var darkness = 0.2126 * r + 0.7152 * g + 0.0722 * b; // per ITU-R BT.709
    return darkness < 90 && darkness < 120;
}

var colorArray = getColorArray(16);
var index = 0;

/*var YEARS_COLORS = [
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
  	{"year":2018, "color": "#" + colorArray[index++]}
  ];*/

  var YEARS_COLORS = [
  	{"year":1996, "color": "#00CCFE"},
  	{"year":2000, "color": "#FE9200"},
  	{"year":2005, "color": "#52B225"},
  	{"year":2006, "color": "#F1EF38"},
  	{"year":2007, "color": "#C32313"},
  	{"year":2008, "color": "#503EDB"},
  	{"year":2009, "color": "#E908FB"},
  	{"year":2010, "color": "#017303"},
  	{"year":2011, "color": "#FA8807"},
  	{"year":2012, "color": "#09C1AB"},
  	{"year":2013, "color": "#FAF705"},
  	{"year":2014, "color": "#D6040E"},
  	{"year":2015, "color": "#0417D6"},
  	{"year":2016, "color": "#04D60E"},
  	{"year":2017, "color": "#900C3F"},
  	{"year":2018, "color": "#FED713"},
      {"year":2019, "color": "#fe18a0"}
  ];

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
var flights = loadFlights();

function initializeAll() {

	setEvents();

	checkAllYearsSelectors();

	initMap();
}

// initialize map
function initMap() {

    //adapt sizes to the screen
    document.getElementById(MAP_CONTAINER).clientWidth *= 0.95;
    document.getElementById(MAP_CONTAINER).clientHeight *= 0.95;

    map = new google.maps.Map(document.getElementById(MAP_CONTAINER), {
        zoom: 3,
        center: {
            lat: 31.390205,
            lng: 2.154007
        },
        backgroundColor: '#fcfcfc',
        scrollwheel: true,
        fullscreenControl: true,
        fullscreenControlOptions: true,
        rotateControl: true,
        rotateControlOptions: true,
        tilt: 45
    });

    addFlights();
}

function addFlights() {

    var flightsInMap = [];
    var flightsArray = [];

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

            var flightData = {
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

			flightsArray.push(flightData);
//		    drawFlight(flight.origin.latitude, flight.origin.longitude, flight.destination.latitude, flight.destination.longitude, getColor(year));
		}
    });
	flightsArray.reverse();

    tempArchInsert(flightsArray, flightsInMap);
}

function tempArchInsert(flightsToInsert, flightsInserted) {

    if (flightsToInsert.length > 0) {
        var insertedFlightsNumber = flightsInserted.length;
        var flight = flightsToInsert.pop();

        flightsInserted.push(flight);

        clearCityLabels();

        //paint the flight data into the lower panel
		if(animated) {
			$("#depCityFlightInfoPanel").html(flight.originCityName);
			$("#depCodeCityFlightInfoPanel").html("(" + flight.originCity + ")");
			$("#arrCityFlightInfoPanel").html(flight.destinationCityName);
			$("#arrCodeCityFlightInfoPanel").html("(" + flight.destinationCity + ")");
			$("#depDateFlightInfoPanel").html(flight.date);
			$("#carrierFlightInfoPanel").html(flight.carrier);
			$("#distanceFlightInfoPanel").html(numberWithThousandSeparator(flight.distance) + " km");

			$(".flightInfoPanel").show();
		}

        drawFlight(flight.origin.latitude, flight.origin.longitude, flight.destination.latitude, flight.destination.longitude, getColor(flight.flightYear), flightsToInsert, flightsInserted);

//tempArchInsert(map, flightsToInsert, flightsInserted);
        /*tmpArchInsertedTimeOut = setTimeout(function () {
            tempArchInsert(map, flightsToInsert, flightsInserted)
        }, DEFAULT_ANIMATION_SPEED);*/

    }else{
		clearCityLabels();
		hideFlightPanel();
    }
}

function drawFlight(originLat, originLng, destinationLat, destinationLng, color, flightsToInsert, flightsInserted) {

    var origin = new google.maps.LatLng(originLat, originLng);
    var destination = new google.maps.LatLng(destinationLat, destinationLng);

    createAirportMarker(origin.lat(), origin.lng(), color);

    var line = new google.maps.Polyline({
        path: [
            0,
            0
        ],
        strokeOpacity: 0,
                  icons: [{
                    icon: createLineSymbol(color),
                    offset: '0',
                    repeat: '20px'
                  }],
        geodesic: true,
        map: map
    });

    var plane = new google.maps.Marker({
            map: map,
            position: {
                lat: origin.lat(),
                lng: origin.lng()
            },
            icon: createIcon("red")
        });

     var step = 0;
     var currentPosition = null;
     var interval = setInterval(function() {
         if (++step > NUM_STEPS) {
             clearInterval(interval);
             createAirportMarker(currentPosition.lat(), currentPosition.lng(), color);
             plane.setMap(null);
             tempArchInsert(flightsToInsert, flightsInserted);
         } else {
             currentPosition = google.maps.geometry.spherical.interpolate(origin, destination, step/NUM_STEPS);
             line.setPath([origin, currentPosition]);
             plane.setPosition( new google.maps.LatLng( currentPosition.lat(), currentPosition.lng() ) );
         }
     }, TIME_PER_STEP);
}

function createAirportMarker(originLat, originLng, color) {
    new google.maps.Marker({
            position: {
                        lat: originLat,
                        lng: originLng
                    },
            map: map,
            draggable: false,
            icon: createIcon(color),
            zIndex : -20
        });
}

function createIcon(color) {
    return {
        path: "M-20,0a20,20 0 1,0 40,0a20,20 0 1,0 -40,0",
        fillColor: color,
        fillOpacity: .8,
        anchor: new google.maps.Point(0, 0),
        strokeWeight: 2,
        strokeOpacity: 1,
        strokeColor: color,
        scale: 0.3
    };
}

function createLineSymbol(color) {
    return {
        path: 'M 0,-1 0,1',
        strokeOpacity: 1,
        strokeColor: color,
        fillOpacity: 10,
        scale: 3
      };
}

// load map
//google.maps.event.addDomListener(window, 'load', initMap);



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
						url: "/api/flights/",
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
