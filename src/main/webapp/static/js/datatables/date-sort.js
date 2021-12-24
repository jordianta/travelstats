/**
 * This plug-in will provide date sorting for the "dd/mm/YYY hh:ii:ss"
 * formatting, which is common in France and other European countries. It can
 * also be quickly adapted for other formatting as required. Furthermore, this
 * date sorting plug-in allows for empty values in the column.
 *
 * Please note that this plug-in is **deprecated*. The
 * [datetime](//datatables.net/blog/2014-12-18) plug-in provides enhanced
 * functionality and flexibility.
 *
 *  @name Date (dd/mm/YYY hh:ii:ss)
 *  @summary Sort date / time in the format `dd/mm/YYYY`
 *  @author [Ronan Guilloux](http://coolforest.net/)
 *  @deprecated
 *
 *  @example
 *    $('#example').dataTable( {
 *       columnDefs: [
 *         { type: 'date-euro', targets: 0 }
 *       ]
 *    } );
 */

 jQuery.extend( jQuery.fn.dataTableExt.oSort, {
    "date-euro-pre": function ( a ) {
        var x;

        if ( $.trim(a) !== '' ) {
            var date = $.trim(a).split('-');
            x = (date[2] + date[1] + date[0]) * 1;
        }
        else {
            x = Infinity;
        }

        return x;
    },

    "date-euro-asc": function ( a, b ) {
        return a - b;
    },

    "date-euro-desc": function ( a, b ) {
        return b - a;
    }
} );