function initializeAll() {

    var airports = loadAllAirports();
    var carriers = loadAllCarriers();

    createSelectOptionsForAirports(airports, "origin");
    createSelectOptionsForAirports(airports, "destination");
    createSelectOptionsForCarriers(carriers);

    $('#addFlightForm').on('submit', function (e) {
        e.preventDefault();
        addFlight();
    });

    $(function () {
        $("#tabs").tabs();
    });


    $("#distance").click(function () {
        var origin = $("#origin option:selected").val();
        var destination = $("#destination option:selected").val();

        var originLat = 0;
        var originLon = 0;
        var destinationLat = 0;
        var destinationLon = 0;

        $.each(airports, function (key, airport) {
            if (airport.id === origin) {
                originLat = airport.latitude;
                originLon = airport.longitude;
            }
            if (airport.id === destination) {
                destinationLat = airport.latitude;
                destinationLon = airport.longitude;
            }
        });

        if (originLat !== 0 && originLon !== 0 && destinationLat !== 0 && destinationLon !== 0) {
            var geod = GeographicLib.Geodesic.WGS84, r;
            r = geod.Inverse(originLat, originLon, destinationLat, destinationLon);
            var distance = Math.round(r.s12.toFixed(3) / 1000);
            $("#distance").val(distance);
        }
    });

    $("#number").click(function () {
        var carrierSelected = $("#carrier option:selected").val();

        $.each(carriers, function (key, carrier) {
            if (carrier.id == carrierSelected) {
                $("#number").val(carrier.iataCode)
                return;
            }
        });
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
    $select.append('<option></option>');

    $.each(airports, function (index, airport) {
        $select.append('<option value=' + airport.id + '>' + airport.name + ' (' + airport.iataCode + ')</option>');
    });

    setAutocompleteCapabilities(selectId);
}

function createSelectOptionsForCarriers(carriers) {
    var $select = $('#carrier');
    $select.append('<option></option>');

    $.each(carriers, function (index, carrier) {
        $select.append('<option value=' + carrier.id + '>' + carrier.name + ' (' + carrier.iataCode + ')</option>');
    });

    setAutocompleteCapabilities("carrier");
}

function setAutocompleteCapabilities(selectId) {

    $.widget("custom.combobox", {
        _create: function () {
            this.wrapper = $("<span>")
                .addClass("custom-combobox")
                .insertAfter(this.element);

            this.element.hide();
            this._createAutocomplete();
            this._createShowAllButton();
        },

        _createAutocomplete: function () {
            var selected = this.element.children(":selected"),
                value = selected.val() ? selected.text() : "";

            this.input = $("<input>")
                .appendTo(this.wrapper)
                .val(value)
                .attr("title", "")
                .addClass("custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left")
                .autocomplete({
                    delay: 0,
                    minLength: 0,
                    source: $.proxy(this, "_source")
                })
                .tooltip({
                    classes: {
                        "ui-tooltip": "ui-state-highlight"
                    }
                });

            this._on(this.input, {
                autocompleteselect: function (event, ui) {
                    ui.item.option.selected = true;
                    this._trigger("select", event, {
                        item: ui.item.option
                    });
                },

                autocompletechange: "_removeIfInvalid"
            });
        },

        _createShowAllButton: function () {
            var input = this.input,
                wasOpen = false;

            $("<a>")
                .attr("tabIndex", -1)
                .tooltip()
                .appendTo(this.wrapper)
                .button({
                    icons: {
                        primary: "ui-icon-triangle-1-s"
                    },
                    text: false
                })
                .removeClass("ui-corner-all")
                .addClass("custom-combobox-toggle ui-corner-right")
                .on("mousedown", function () {
                    wasOpen = input.autocomplete("widget").is(":visible");
                })
                .on("click", function () {
                    input.trigger("focus");

                    // Close if already visible
                    if (wasOpen) {
                        return;
                    }

                    // Pass empty string as value to search for, displaying all results
                    input.autocomplete("search", "");
                });
        },

        _source: function (request, response) {
            var matcher = new RegExp($.ui.autocomplete.escapeRegex(request.term), "i");
            response(this.element.children("option").map(function () {
                var text = $(this).text();
                if (this.value && (!request.term || matcher.test(text)))
                    return {
                        label: text,
                        value: text,
                        option: this
                    };
            }));
        },

        _removeIfInvalid: function (event, ui) {

            // Selected an item, nothing to do
            if (ui.item) {
                return;
            }

            // Search for a match (case-insensitive)
            var value = this.input.val(),
                valueLowerCase = value.toLowerCase(),
                valid = false;
            this.element.children("option").each(function () {
                if ($(this).text().toLowerCase() === valueLowerCase) {
                    this.selected = valid = true;
                    return false;
                }
            });

            // Found a match, nothing to do
            if (valid) {
                return;
            }

            // Remove invalid value
            this.input
                .val("")
                .attr("title", value + " didn't match any item")
                .tooltip("open");
            this.element.val("");
            this._delay(function () {
                this.input.tooltip("close").attr("title", "");
            }, 2500);
            this.input.autocomplete("instance").term = "";
        },

        _destroy: function () {
            this.wrapper.remove();
            this.element.show();
        }
    });

    $('#' + selectId).combobox();

}


function addFlight() {
//    var formData = JSON.stringify($("#addFlightForm").serialize());
    var formData = $("#addFlightForm").serializeJSON()

    $.ajax({
        type: "POST",
        url: "/api/flights",
        data: formData,
        success: function () {
            alert("Flight added!")
        },
        error: function () {
            alert("Something was wrong...")
        },
        dataType: "json",
        contentType: "application/json"
    });
}