var ws;

function initiateChatSession() {
	console.log("In initiateChatSession");
	if ("WebSocket" in window) {
		//Defining the serverEnd point of the websocket connection.
		var chatEndpoint = "ws://localhost:8080/chat-stw/chat/";
		// TODO add the channel to the WS URI
		var channel = document.getElementById("channelId").value;
		console.log("canal es " + channel);
		chatEndpoint +=  channel;
		// Opening a web socket	connection	
		ws = new WebSocket(chatEndpoint);
		ws.onopen = function(evt) {onOpen(evt);};
		ws.onmessage = function(evt) {onMessage(evt);}; 
		ws.close = function(evt) { onClose(evt);};
		ws.onerror = function(evt) { onError(evt);};
	} else {
		// The browser doesn't support WebSocket
		alert("WebSocket NOT supported!");
	}
}
 
function onOpen(evt){
	console.log("On Open")
}

function onClose(evt){	
	console.log("onClose");
}

function onMessage(evt){
	var msg = evt.data;	
	console.log("Message received: " + msg);
	
	if (msg == "REFRESH_MSG"){
		console.log("Refresh message received");
		document.getElementById("refresh").click();
	}else{
		var log = document.getElementById("chatlog").value;
		log += msg;
		log += "\n";
		document.getElementById("chatlog").value = log;
	}

}

/**
 * This function is called on a connection error.
 * @param evt the event that contains the error.
 */
function onError(evt){
	ws.close();
}

function sendChatMessage() {
	var textarea = document.getElementById("textbox").value;
	var log = document.getElementById("chatlog").value;
	var name = document.getElementById("displayName").value;
	console.log(name);
	log += name;
	log += "> ";
	log += textarea;
	log += "\n";
	
	var toSend = name;
	toSend += "> ";
	toSend += textarea;
	
	console.log(toSend);
	document.getElementById("chatlog").value = log;
	document.getElementById("textbox").value = "";
	ws.send(toSend);
}

window.onload = function(){
	console.log("onload");
	initiateChatSession();};
