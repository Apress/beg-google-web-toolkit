/*
 * Copyright 2002-2008 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.apress.beginninggwt.chap4.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.Window;

/**
 * @author Uri Boness 
 */
public class UserRegistrationForm implements EntryPoint {

	private PasswordTextBox passwordField;
	private Label passwordErrorLabel;
	private PasswordTextBox retypePasswordField;
	private Label retypePasswordErrorLabel;

	public void onModuleLoad() {
		// full name field
		Label fullNameLabel = new Label("Full Name:");
		final TextBox fullNameField = new TextBox();
		RootPanel.get("fullNameLabel").add(fullNameLabel);
		RootPanel.get("fullNameField").add(fullNameField);
		
		// email field
		Label emailLabel = new Label("Email:");
		final TextBox emailField = new TextBox();
		RootPanel.get("emailLabel").add(emailLabel);
		RootPanel.get("emailField").add(emailField);
		
		// username field
		Label usernameLabel = new Label("Username:");
		final TextBox usernameField = new TextBox();
		RootPanel.get("usernameLabel").add(usernameLabel);
		RootPanel.get("usernameField").add(usernameField);
		
		// password field
		Label passwordLabel = new Label("Password:");
		passwordField = new PasswordTextBox();
		passwordErrorLabel = new Label();
		passwordErrorLabel.addStyleName("errorLabel");
		RootPanel.get("passwordLabel").add(passwordLabel);
		RootPanel.get("passwordField").add(passwordField);
		RootPanel.get("passwordErrorLabel").add(passwordErrorLabel);
		
		// retype password field
		Label retypePasswordLabel = new Label("Retype Password:");
		retypePasswordField = new PasswordTextBox();
		retypePasswordErrorLabel = new Label();
		retypePasswordErrorLabel.addStyleName("errorLabel");
		RootPanel.get("retypePasswordLabel").add(retypePasswordLabel);
		RootPanel.get("retypePasswordField").add(retypePasswordField);
		RootPanel.get("retypePasswordErrorLabel").add(retypePasswordErrorLabel);
		
		// the button that will submit the form
		Button submitButton = new Button("Register");
		RootPanel.get("submitButton").add(submitButton);
		submitButton.addClickListener(new ClickListener() {
			public void onClick(Widget widget) {
				if (validateFormInput()) {
					AccountInfo accountInfo = new AccountInfo();
					accountInfo.setFullName(usernameField.getText());
					accountInfo.setEmail(emailField.getText());
					accountInfo.setUsername(usernameField.getText());
					accountInfo.setPassword(passwordField.getText());
					register(accountInfo);
				}
			}
		});
	}
	
	/**
	* Registers the user in the system.
	*/
	protected void register(AccountInfo accountInfo) {
		Window.alert(accountInfo.toString());
	}
	
	/**
	 * Validates the form input and returns whether the input is valid or not.
	 */
	protected boolean validateFormInput() {
		
		// validating the password is not empty
		String password = passwordField.getText();
		boolean passwordIsValid = password.length() > 0;
		passwordErrorLabel.setText(passwordIsValid ? "" : "Required");
		
		// validating that the retyped password matches the password
		String retypedPassword = retypePasswordField.getText();
		boolean retypedIsValid = retypedPassword.equals(password);
		retypePasswordErrorLabel.setText(retypedIsValid ? "" : "Incorrect");
		
		return passwordIsValid && retypedIsValid;
	}
}
