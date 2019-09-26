package com.revature.service;

import com.revature.beans.Credentials;
import com.revature.beans.User;

public class AuthenticationService {

	
	public User authenticateUser(Credentials creds) {
		User u = null;
		if (creds.getUsername().equals("Merlin") && creds.getPassword().equals("Higgins")) {
			u = new User(6, "Merlin", "Higgins");
		}
		return u;
	}

}
