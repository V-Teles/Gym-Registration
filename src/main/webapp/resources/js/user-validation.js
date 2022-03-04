function validateForm() {
	
	// error fields
	var theErrorFields = [];
	
	// user form
	var userForm = document.forms["form"];
	
	// check first name
	var firstName = userForm["form:firstName"].value.trim();
	if (firstName == "") {
		theErrorFields.push("First name");
	}

	// check last name
	var lastName = userForm["form:lastName"].value.trim();
	if (lastName == "") {
		theErrorFields.push("Last name");
	}
	
		// check birthday
	var lastName = userForm["form:birthday"].value.trim();
	if (lastName == "") {
		theErrorFields.push("Birthday");
	}
	
		// check phone number
	var lastName = userForm["form:phoneNumber"].value.trim();
	if (lastName == "") {
		theErrorFields.push("Phone number");
	}
	
		// check last name
	var lastName = userForm["form:address"].value.trim();
	if (lastName == "") {
		theErrorFields.push("Address");
	}
	
	// check email
	var email = userForm["form:email"].value.trim();
	if (email == "") {
		theErrorFields.push("Email");
	}
	
	if (theErrorFields.length > 0) {
		alert("Form validation failed. Please add data for following fields: " + theErrorFields);
		return false;
	}
}