var	MAP_WIDTH = 1350; // local
var	MAP_HEIGHT = 900; // local
//var	MAP_WIDTH = 1650; // tv
//var	MAP_HEIGHT = 1100; // tv
var LEFT_LABEL_TOTAL = MAP_WIDTH - 50; //1300;
var TOP_LABEL_TOTAL = MAP_HEIGHT - 450; //450;
var currentTotal = 0;
var RATIO = 2;

function initializeWorld() {

	$("#map-canvas").width(MAP_WIDTH);
	$("#map-canvas").height(MAP_HEIGHT);
	
	var map = new Datamap({
		element: document.getElementById("map-canvas"),
		projection: 'mercator',				
		fills : getFills(),	
		data: getData(),
		
		geographyConfig: {
			hideAntarctica: true,
			borderWidth: 1,
			borderColor: '#829199'
		}
	});
	
	map.bubbles(getBubbles(), 
		{
			highlightOnHover: false
		}
	);
	
	/*var bubbles = document.getElementsByClassName("datamaps-bubble");
	
	for(var i = 0; i < bubbles.length; i++) {
	
		var bubble = bubbles[i];
		
		var textLabel = JSON.parse(bubble.dataset.info)['name'];
		
		createLabelDiv(bubble.cy.baseVal.value, bubble.cx.baseVal.value, textLabel);
		
		fireOnClickEvent(bubble);
	}*/
	
	var bubbles = document.getElementsByClassName("datamaps-bubble");
	
	var maxRadius = getMaxRadius(bubbles);
	
	for(var i = 0; i < bubbles.length; i++) {
	
		var bubble = bubbles[i];
		
		var jsonInfo = JSON.parse(bubble.dataset.info);
		
		var textLabel = jsonInfo['name'];
		
		var size = calculateSize(maxRadius, jsonInfo['radius']);
		
		createLabelDiv(bubble.cy.baseVal.value, bubble.cx.baseVal.value, textLabel, size, jsonInfo['fillKey']);
		
		fireOnClickEvent(bubble);
	}
	
	//createLabelTotal(getTotalBookings()) ;
	showTotal();
}

function deleteCountriesRandom(number) {

	if(countries.length <= value) {
		return;
	}
	
	var keys = Object.keys(countries);
	
	keys = shuffle(keys);
	
	keys.splice(keys.length - number, keys.length);
	
	$.each(keys, function( index, value ) {
		delete countries[index];
	});

}

function shuffle(o){
    for(var j, x, i = o.length; i; j = Math.floor(Math.random() * i), x = o[--i], o[i] = o[j], o[j] = x);
    return o;
};
		
function getTotalBookings() {

	var total = 0;

	$.each(countries, function(country, val) {
		total += Number(val);
	});
	
	return total;		
}
		
function createLabelTotal(total) {		
	$("#datamaps-container").append('<div class="datamaps-hoverover" id="datamaps-hoverover-total" style="z-index: 10001; position: absolute; display: block; top: ' + TOP_LABEL_TOTAL + 'px; left: ' + LEFT_LABEL_TOTAL + 'px;"><div class="fadered" id="hoverinfoTotal">Total Bookings<p class="timer">' + total + '</p></div></div>')
}
		
function showTotal() {

	createLabelTotal(0) ;

	var total = getTotalBookings();
	
	startfade("fadered");
	
	$('.timer').countTo({
		from: 0,
		to: total,
		speed: 4000,
		refreshInterval: 50,
		//onUpdate: changeBackground(),
		/*onComplete: function(value) {
			console.debug(this);
		}*/
	});		
}

function createLabelDiv(top, left, text, size, id) {

	$("#datamaps-container").append('<div class="datamaps-hoverover" id="datamaps-hoverover-' + id + '" style="z-index: 10001; position: absolute; display: block; top: ' + top + 'px; left: ' + left + 'px;"><div class="hoverinfoBubble" style="font-size: ' + size + 'px">' + text + '</div></div>')
	
	var width = $("#datamaps-hoverover-" + id)[0].offsetWidth;
	var height = $("#datamaps-hoverover-" + id)[0].offsetHeight;
	
	var leftValue = $("#datamaps-hoverover-" + id).position().left;
	var topValue = $("#datamaps-hoverover-" + id).position().top;
	
	leftValue = leftValue - width / 2 + 10;
	topValue = topValue - height / 2 + 10;

	$("#datamaps-hoverover-" + id).css({ top: topValue + 'px', left: leftValue + 'px'});
}