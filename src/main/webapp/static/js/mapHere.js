var MAP_CONTAINER = "map-canvas";
var map;
var ui;
var mapPinIcon = new H.map.Icon("static/images/mapPinIcon.png", {size: {h: 25, w: 25}});
var mapPinIconNew = new H.map.Icon("static/images/mapPinIconNew.png", {size: {h: 25, w: 25}});

var platform = new H.service.Platform({
  'apikey': 'OtsOoCOa5LPpTe91mpcU0rQSxdMsOVuQuuedel9OZD4'
});

var searchService = platform.getSearchService();

function initializeAll() {
	createMap();
	addPlaces(loadPlaces());
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
    var coords = {lat: latitude, lng: longitude};
    var marker = new H.map.Marker(coords,{ icon: mapPinIcon });
    marker.setData(createPlaceContent(id, description));
    marker.addEventListener('tap',  function(e) {createPlaceBubble(e)});
    map.addObject(marker);
}

function createPlaceBubble(event) {
    removeAllPlacesBubbles();
    var bubble =  new H.ui.InfoBubble(event.target.getGeometry(), { content: event.target.getData() });
    ui.addBubble(bubble);
}

function removeAllPlacesBubbles() {
    ui.getBubbles().forEach(function(bubble) {
                                ui.removeBubble(bubble);
                            });
}

function createPlaceContent(id, description) {
    return "<div>" + description + "<p align='right'><a href='javascript:deletePlace(" + id + ");'><img src='static/images/bin.png' width='25%'></a></div>";
}

function deletePlace(id) {
    $.ajax({
        type: "DELETE",
        url: "/api/places/" + id,
        success: function(msg){
            map.removeObjects(map.getObjects());
            addPlaces(loadPlaces());
            removeAllPlacesBubbles();
        }
    });
}

function createCandidatePlaceMarker(latitude, longitude, name) {
    var coords = {lat: latitude, lng: longitude};
    var marker = new H.map.Marker(coords,{ icon: mapPinIconNew });
    marker.setData(createCandidatePlaceContent(latitude, longitude, name));
    marker.addEventListener('tap',  function(e) {createCandidatePlaceBubble(e)});
    map.addObject(marker);
    map.setZoom(6);
    map.setCenter({lat: latitude, lng: longitude});
}

function createCandidatePlaceContent(latitude, longitude, description) {
    return "<div>" + description + "<p align='right'><a href='javascript:addPlace(" + latitude + "," + longitude + ",\"" + description.replace(/'/g, "&#39;") + "\");'><img src='static/images/add.png' width='25%'></a></div>";
}

function addPlace(latitude, longitude, name) {
    $.ajax({
        type: "POST",
        url: "/api/places",
        data: JSON.stringify({ "latitude": latitude, "longitude": longitude, "name": name }),
	    contentType: "application/json; charset=utf-8",
        success: function(msg){
            map.removeObjects(map.getObjects());
            addPlaces(loadPlaces());
            removeAllPlacesBubbles();
        }
    });
}

function createCandidatePlaceBubble(event) {
    removeAllPlacesBubbles();
    var bubble =  new H.ui.InfoBubble(event.target.getGeometry(), { content: event.target.getData() });
    ui.addBubble(bubble);
}

 $(document).ready(function(){
    $('#search-form').keyup(function(){
       var query = $(this).val();
       if(query != '')
       {
            searchService.geocode({
              q: query
            }, (result) => {
              var list = "<ul class='list-unstyled'>"
              // Add a marker for each location found
              result.items.forEach((item) => {
                list += "<li onclick='createCandidatePlaceMarker(" + item.position.lat + "," +  item.position.lng + ",\"" + item.title.replace(/'/g, "&#39;") + "\")'>" + item.title.replace(/'/g, "&#39;") + "</li>";
              });
              list += "</ul>";
              $('#search-results').fadeIn();
              $('#search-results').html(list);
            },(error) => {console.log(error);});
       }
    });
    $(document).on('click', 'li', function(){
       console.log("click");
       $('#search-form').val($(this).text());
       $('#search-results').fadeOut();
    });
 });
