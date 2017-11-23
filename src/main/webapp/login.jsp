<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="static/css/genericStyle.css">
	<link rel="stylesheet" type="text/css" href="static/css/loginStyle.css">
	<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Oxygen:400,300,700|Open+Sans:400,300,600,700">
	
	<script type="text/javascript" src="static/js/login.js"></script>
	<script type="text/javascript" src="static/js/jquery-2.1.3.min.js"></script>
</head>
 
<body>
	<div class="welcomeMessage">		
		Welcome to <f class="appName">Travel Stats</f>		
	</div>
	<div class="main">
		<div class="user">
			<img src="user.png" alt="">
		</div>
		<div class="login">
			<div class="inset">
				<!-----start-main---->
				<form action="flightsMap.jsp" id ="loginForm">
			         <div>
						<span><label>Username</label></span>
						<span><input type="text" class="textbox" id="active"></span>
					 </div>
					 <div>
						<span><label>Password</label></span>
					    <span><input type="password" class="password"></span>
					 </div>
					<div class="sign">
						<div class="submit">
						  <input type="submit" onclick="login()" value="LOGIN" >
						</div>
						<span class="forget-pass">
							<a href="#">Forgot Password?</a>
						</span>
							<div class="clear"> </div>
					</div>
				</form>
			</div>
		</div>
		<!-----//end-main---->
		</div>
		<!-----start-copyright---->
		<div class="copy-right">
			<p>Template by <a href="http://w3layouts.com">w3layouts</a></p> 
		</div>
		<div id="trebolLogoDiv">
			Powered by <img src="static/images/trebolLogo.svg" class="trebolLogo">
		</div>
		<!-----//end-copyright---->	 
</body>
</html>