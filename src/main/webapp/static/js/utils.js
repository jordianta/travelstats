function RGBA(red,green,blue,alpha) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = alpha;
    this.getCSS = function() {
        return "rgba("+this.red+","+this.green+","+this.blue+","+this.alpha+")";
    }
}

function hexToRgb(hex) {
    var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
    return result ? {
        r: parseInt(result[1], 16),
        g: parseInt(result[2], 16),
        b: parseInt(result[3], 16)
    } : null;
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

function logout() {
	$("#logoutForm").submit();
}