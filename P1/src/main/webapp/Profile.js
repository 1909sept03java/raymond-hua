fetch ("http://localhost:8089/P1/session").then(function(response){
	return response.json();
}).then(function(data){
	console.log(data);
	var div = document.createElement("div");
	div.innerText = data.firstName + " " + data.lastName;	 
	document.body.appendChild(div);
}).catch(function(){
	console.log("Hello");
})
