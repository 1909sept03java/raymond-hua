fetch('http://localhost:8089/P1/session').then(function(response){
	return response.json();
}).then(function(data) {
	console.log(data);
	let selection = data[0];
	console.log(selection);
	return this.redirect(selection, data);
})
.catch(function(ERR) {
	console.log(ERR);
});

function redirect(selection, data){
	switch (selection){
		case '1':return populate(); break;
		case '2': return viewReimbursement(data[7]); break;
		case '3': return viewReimbursement(data[7]); break;
		case '4': return viewEmployeeInfo(data); break;
		case '5': return updateEmployeeInfo(data); break;
		case '6': return viewEmployeeReimbursement(data[7]); break;
		//case '6': return updateEmployeeReimbursement(data[7]); break;
		case '7': return viewEmployeeReimbursement(data[7]); break;
		case '8': return viewEmployeeReimbursement(data[7]); break;
		case '9': return viewEmployees(data[7]); break;
		//default: console.log("Hello");	break;
	}
};

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

function viewReimbursement(data){
	//GENERATE REIMBURSEMENTS
	for (let i = 0; i < data.length; i++){
		var div = document.createElement("div");
		let status = '';
		switch (data[i].pad){
			case 0: status = 'Pending'; break;
			case 1: status = 'Approved'; break;
			case 2: status = 'Denied'; break;
		}
		div.innerText = "Reimbursement ID: "+ data[i].reimbursement_id + "\nEmployee ID: " + data[i].employee_id
		+ "\nAmount: $" + data[i].amount + "\nStatus: " + status;
		div.id = "display";
		document.body.appendChild(div);
	}
	//RETURN BUTTON BACK TO OPTIONS
	var div = document.createElement("div");
	var form = document.createElement("form");  
	form.action = "option";
	form.method = "POST";
	div.id = "display";
	div.appendChild(form);
	document.body.appendChild(div);
	var div2 = document.createElement("div");
	var input2 = document.createElement("input");
	div2.id = "button";
	input2.type = "submit";
	input2.value = "Return to Option Page";
	div2.appendChild(input2);
	form.appendChild(div2);
};

function viewEmployeeInfo(data){
	var div = document.createElement("div");
	var form = document.createElement("form");  
	form.action = "option";
	form.method = "POST";
	div.id = "display";
	div.innerText = "EMPLOYEE ID: " + data[1] + 
	"\nUSERNAME: " + data[2] +
	"\nPASSWORD: " + data[3] +
	"\nMANAGER ID: " + data[4] +
	"\nMANAGER STATUS: " + data[5];
	div.appendChild(form);
	document.body.appendChild(div);
	var div2 = document.createElement("div");
	var input2 = document.createElement("input");
	div2.id = "button";
	input2.type = "submit";
	input2.value = "Return to Option Page";
	div2.appendChild(input2);
	form.appendChild(div2);
};

function updateEmployeeInfo(data){
	//SHOW PREVIOUS INFO
	var div = document.createElement("div");
	var form = document.createElement("form");  
	form.action = "option";
	form.method = "POST";
	div.id = "display";
	div.innerText = "EMPLOYEE ID: " + data[1] + 
	"\nUSERNAME: " + data[2] +
	"\nPASSWORD: " + data[3] +
	"\nMANAGER ID: " + data[4] +
	"\nMANAGER STATUS: " + data[5];
	//USERNAME FIELDSET
	var fieldset = document.createElement("fieldset");
	var legend = document.createElement("legend");
	var input = document.createElement("input");
	input.name = "newUsername";
	legend.innerText = "New username:";
	fieldset.appendChild(legend);
	fieldset.appendChild(input);
	form.appendChild(fieldset);
	//PASSWORD FIELDSET
	var fieldset2 = document.createElement("fieldset");
	var legend2 = document.createElement("legend");
	var input2 = document.createElement("input");
	input2.name = "newPassword";
	legend2.innerText = "New password:";
	fieldset2.appendChild(legend2);
	fieldset2.appendChild(input2);
	form.appendChild(fieldset2);
	//
	var div2 = document.createElement("div");
	var input2 = document.createElement("input");
	div2.id = "button";
	input2.type = "submit";
	input2.value = "Submit";
	div2.appendChild(input2);
	form.appendChild(div2);
	div.appendChild(form);
	document.body.appendChild(div);
};

function updateEmployeeReimbursement(data){
	//STUFF
}

function viewEmployeeReimbursement(data){
	//GENERATE REIMBURSEMENTS
	for (let i = 0; i < data.length; i++){
		var div = document.createElement("div");
		let status = '';
		switch (data[i].pad){
			case 0: status = 'Pending'; break;
			case 1: status = 'Approved'; break;
			case 2: status = 'Denied'; break;
		}
		div.innerText = "Reimbursement ID: "+ data[i].reimbursement_id + "\nEmployee ID: " + data[i].employee_id
		+ "\nAmount: $" + data[i].amount + "\nStatus: " + status
		div.id = "display";
		document.body.appendChild(div);
	}
	//RETURN BUTTON BACK TO OPTIONS
	var div = document.createElement("div");
	var form = document.createElement("form");  
	form.action = "option";
	form.method = "POST";
	div.id = "display";
	div.appendChild(form);
	document.body.appendChild(div);
	var div2 = document.createElement("div");
	var input2 = document.createElement("input");
	div2.id = "button";
	input2.type = "submit";
	input2.value = "Return to Option Page";
	div2.appendChild(input2);
	form.appendChild(div2);
};

function viewEmployees(data){
	//GENERATE EMPLOYEES
	for (let i = 0; i < data.length; i++){
		var div = document.createElement("div");
		div.innerText = "Employee ID: "+ data[i].employee_id + "\nUsername: " + data[i].username
		+ "\nManager ID: " + data[i].manager_id;
		div.id = "display";
		document.body.appendChild(div);
	}
	//RETURN BUTTON BACK TO OPTIONS
	var div = document.createElement("div");
	var form = document.createElement("form");  
	form.action = "option";
	form.method = "POST";
	div.id = "display";
	div.appendChild(form);
	document.body.appendChild(div);
	var div2 = document.createElement("div");
	var input2 = document.createElement("input");
	div2.id = "button";
	input2.type = "submit";
	input2.value = "Return to Option Page";
	div2.appendChild(input2);
	form.appendChild(div2);
};