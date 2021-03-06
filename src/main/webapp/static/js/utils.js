function RGBA(red,green,blue,alpha) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = alpha;
    this.getCSS = function() {
        return "rgba("+this.red+","+this.green+","+this.blue+","+this.alpha+")";
    }
}

function hexToRgb(hex, alpha) {
    var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
    return result ? {
        r: parseInt(result[1], 16),
        g: parseInt(result[2], 16),
        b: parseInt(result[3], 16),
        a: alpha
    } : null;
}


function hexToRgba(hex, alpha) {
    var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
    return 'rgba('
    + parseInt(result[1], 16) + ','
    + parseInt(result[2], 16) + ','
    + parseInt(result[3], 16) + ','
    + alpha
    + ')';
}

function changeColorOpacity(color, alpha) {
	
	var rgbColor = hexToRgb(color);
	
	var red = rgbColor.r;
	var green = rgbColor.g;
	var blue = rgbColor.b;
	
	return RGBA(red, green, blue, alpha);	
}

function numberWithThousandSeparator(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}

function getRandomId() {
    var S4 = function() {
       return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
    };
    return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
}

function logout() {
	$("#logoutForm").submit();
}