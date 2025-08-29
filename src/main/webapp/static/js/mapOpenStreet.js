var MAP_CONTAINER = "map-canvas";
var map;
var ui;
var markers = new Array();
var mapPinIcon = L.icon({
                     iconUrl: 'static/images/mapPinIcon.png',
                     iconSize: [25, 25]
                 });
var mapPinIconNew = L.icon({
                       iconUrl: 'static/images/mapPinIconNew.png',
                       iconSize: [25, 25]
                   });

var searchService = null

function initializeAll() {
	createMap();
	addPlaces(loadPlaces());
}

// initialize map
function createMap() {

    //adapt sizes to the screen
    document.getElementById(MAP_CONTAINER).clientWidth *= 0.95;
    document.getElementById(MAP_CONTAINER).clientHeight *= 0.95;

    map = L.map(MAP_CONTAINER).setView([31.390205, 2.154007], 2.5);

    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

    var searchControl = new L.esri.Controls.Geosearch().addTo(map);
    var results = new L.LayerGroup().addTo(map);
    searchControl.on('results', function(data){
        results.clearLayers();
        for (var i = data.results.length - 1; i >= 0; i--) {
            var result = data.results[i];
            console.log(result);
            results.addLayer(createCandidatePlaceMarker(result.latlng.lat, result.latlng.lng, result.text));
        }
    });

    window.addEventListener('resize', function () {
        map.getViewPort().resize();
    });
}

function loadPlaces() {

	var serverResponse = $.ajax({
						dataType: "json",
						url: "/api/places",
						async: false
					}).responseText;

	return JSON.parse(serverResponse);
}

function addPlaces(places) {
    $.each(places, function (i, place) {
        createPlaceMarker(place.id, place.latitude, place.longitude, place.name)
    });
}


function createPlaceMarker(id, latitude, longitude, description) {
    var marker = L.marker([latitude, longitude], {icon: mapPinIcon})
    .addTo(map)
    .bindPopup(createPlaceContent(id, description));

    markers.push(marker);
}

function createPlaceContent(id, description) {
    return "<div>" + description + "<p align='right'><a href='javascript:deletePlace(" + id + ");'><img src='static/images/bin.png' width='15%'></a></div>";
}

function deletePlace(id) {
    $.ajax({
        type: "DELETE",
        url: "/api/places/" + id,
        success: function(msg){
            deleteAllPlaces();
            addPlaces(loadPlaces());
        }
    });
}

function deleteAllPlaces() {
    for(i = 0;i < markers.length;i++) {
        map.removeLayer(markers[i]);
    }
    markers = new Array();
}

function createCandidatePlaceMarker(latitude, longitude, name) {
    var coords = {lat: latitude, lng: longitude};
    var marker = L.marker([latitude, longitude], {icon: mapPinIconNew})
    .addTo(map)
    .bindPopup(createCandidatePlaceContent(latitude, longitude, name));

    markers.push(marker);

    return marker;
}

function createCandidatePlaceContent(latitude, longitude, description) {
    return "<div>" + description + "<p align='right'><a href='javascript:addPlace(" + latitude + "," + longitude + ",\"" + description.replace(/'/g, "&#39;") + "\");'><img src='static/images/add.png' width='15%'></a></div>";
}

function addPlace(latitude, longitude, name) {
    $.ajax({
        type: "POST",
        url: "/api/places",
        data: JSON.stringify({ "latitude": latitude, "longitude": longitude, "name": name }),
	    contentType: "application/json; charset=utf-8",
        success: function(msg){
            deleteAllPlaces();
            addPlaces(loadPlaces());
        }
    });
}
