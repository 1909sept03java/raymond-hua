fetch('http://localhost:8089/P1/session').then(function(response){
	return response.json();
}).then(function(data) {
	console.log(data);
	let selection = data[0];
	console.log(selection);
	return this.redirect(selection);
})
.catch(function(ERR) {
	console.log(ERR);
});

function populate(){
		var div = document.createElement("div");
		var form = document.createElement("form");  
		var fieldset = document.createElement("fieldset");
		var legend = document.createElement("legend");
		var input = document.createElement("input");
		input.name = "amount";
		legend.innerText = "Amount:";
		form.action = "option";
		form.method = "POST";
		div.innerText = "Submit Reimbursement Request";
		div.id = "display";
		fieldset.appendChild(legend);
		fieldset.appendChild(input);
		form.appendChild(fieldset);
		div.appendChild(form);
		document.body.appendChild(div);
		var br = document.createElement("br");
		fieldset.appendChild(br);
		var div2 = document.createElement("div");
		var input2 = document.createElement("input");
		div2.id = "button";
		input2.type = "submit";
		input2.value = "Submit";
		div2.appendChild(input2);
		form.appendChild(div2);
};

function redirect(selection){
	if (selection == 1){
		return this.populate();
	}
	else
		console.log("Hello :o");
}
/*
<div id="display">
<!--  action attribute is the form handler -->
<!--  method attribute is the HTTP method to be used -->
<form action="login" method="POST">
	<fieldset>
		<legend>Username:</legend>
		<input required autocomplete="off" name="username" />
	</fieldset>
	<fieldset>
		<legend>Password:</legend>
		<input required autocomplete="off" type="password" name="password" />
	</fieldset>
	<br>
	<div id = "button">
		<input type="submit" value="Login" />
	</div>
</form>
</div>
*/