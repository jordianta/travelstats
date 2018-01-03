function initializeAll() {

    var airports = loadAllAirports();
    var carriers = loadAllCarriers();
	
	createSelectOptionsForAirports(airports, "origin");
	createSelectOptionsForAirports(airports, "destination");
	createSelectOptionsForCarriers(carriers);

    $('#addFlightForm').on('submit', function(e){
                                             e.preventDefault();
                                             addFlight();
                                         });

    $(function() {
        $("#tabs").tabs();
    });
}

function loadAllAirports() {
	return loadAll("/api/airports");
}

function loadAllCarriers() {
	return loadAll("/api/carriers");
}

function loadAll(path) {
    var serverResponse = $.ajax({
						dataType: "json",
						url: path,
						async: false
					}).responseText;

	return JSON.parse(serverResponse);
}

function createSelectOptionsForAirports(airports, selectId) {
    var $select = $('#' + selectId);

	$.each(airports, function(index, airport)
    {
        $select.append('<option value=' + airport.id + '>' + airport.name + ' (' + airport.iataCode + ')</option>');
    });
}

function createSelectOptionsForCarriers(carriers) {
    var $select = $('#carrier');

	$.each(carriers, function(index, carrier)
    {
        $select.append('<option value=' + carrier.id + '>' + carrier.name + ' (' + carrier.iataCode + ')</option>');
    });
}



function addFlight() {
//    var formData = JSON.stringify($("#addFlightForm").serialize());
    var formData = $("#addFlightForm").serializeJSON()

    $.ajax({
      type: "POST",
      url: "/api/flights",
      data: formData,
      success: function(){alert("Flight added!")},
      error: function(){alert("Something was wrong...")},
      dataType: "json",
      contentType : "application/json"
    });
}