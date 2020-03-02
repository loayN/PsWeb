<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tomcat WebSocket</title>
</head>
<body>
	<form>
		<input id="message" type="text">
		<input onclick="wsSendMessage();" value="Echo" type="button">
		<input onclick="wsCloseConnection();" value="Disconnect" type="button">
	</form>
	<br>
	<textarea id="echoText" rows="5" cols="30"></textarea>
	<script type="text/javascript">
		var webSocket = new WebSocket("ws://localhost:8080/PswebMW/endpoint");
		var echoText = document.getElementById("echoText");
		echoText.value = "";
		var message = document.getElementById("message");
		webSocket.onopen = function(message){ wsOpen(message);};
		webSocket.onmessage = function(message){ wsGetMessage(message);};
		webSocket.onclose = function(message){ wsClose(message);};
		webSocket.onerror = function(message){ wsError(message);};
		function wsOpen(message){
			echoText.value += "Connected ... \n";
		}
		function wsSendMessage(){
			webSocket.send(message.value);
			echoText.value += "Message sended to the server : " + message.value + "\n";
			message.value = "";
		}
		function wsCloseConnection(){
			webSocket.close();
		}
		function wsGetMessage(message){
			echoText.value += "Message received from to the server : " + message.data + "\n";
		}
		function wsClose(message){
			echoText.value += "Disconnect ... \n";
		}

		function wserror(message){
			echoText.value += "Error ... \n";
		}
	</script>
</body>
</html>
		