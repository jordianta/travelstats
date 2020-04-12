var MAP_CONTAINER = "map-canvas";
var map;
var ui;
var mapPinIcon = new H.map.Icon("static/images/mapPinIcon.png", {size: {h: 25, w: 25}});

var platform = new H.service.Platform({
  'apikey': 'OtsOoCOa5LPpTe91mpcU0rQSxdMsOVuQuuedel9OZD4'
});

function initializeAll() {
	createMap();
	addPlaces(loadPlaces());
}

function loadPlaces() {
	
	var serverResponse = $.ajax({
						dataType: "json",
						url: "/api/places/",
						async: false
					}).responseText;
	
	return JSON.parse(serverResponse);
}

// initialize map
function createMap() {

    //adapt sizes to the screen
    document.getElementById(MAP_CONTAINER).clientWidth *= 0.95;
    document.getElementById(MAP_CONTAINER).clientHeight *= 0.95;

    // Obtain the default map types from the platform object:
    var defaultLayers = platform.createDefaultLayers();

    // Instantiate (and display) a map object:
    map = new H.Map(
        document.getElementById(MAP_CONTAINER),
        defaultLayers.vector.normal.map,
        {
          zoom: 3,
          center: {
            lat: 31.390205,
            lng: 2.154007
            },
            pixelRatio: (window.devicePixelRatio && window.devicePixelRatio > 1) ? 2 : 1
        });
    window.addEventListener('resize', function () {
        map.getViewPort().resize();
    });

    // Create the default UI:
    ui = H.ui.UI.createDefault(map, defaultLayers);
    // Create behaviours (mouse control)
    new H.mapevents.Behavior(new H.mapevents.MapEvents(map));
}

function addPlaces(places) {
    $.each(places, function (i, place) {
        createLocationMarker(place.latitude, place.longitude, place.name)
    });
}


function createLocationMarker(latitude, longitude, description) {
    var coords = {lat: latitude, lng: longitude};
    var marker = new H.map.Marker(coords,{ icon: mapPinIcon });
    marker.setData(description);
    marker.addEventListener('tap',  function(e) {addLocationMarkerInfo(e)});
    map.addObject(marker);
}

function addLocationMarkerInfo(event) {
    removeCurrentLocationMarkerInfo();
    var bubble =  new H.ui.InfoBubble(event.target.getGeometry(), { content: event.target.getData() });
    ui.addBubble(bubble);
}

function removeCurrentLocationMarkerInfo() {
    ui.getBubbles().forEach(function(bubble) {
                                ui.removeBubble(bubble);
                            });
}