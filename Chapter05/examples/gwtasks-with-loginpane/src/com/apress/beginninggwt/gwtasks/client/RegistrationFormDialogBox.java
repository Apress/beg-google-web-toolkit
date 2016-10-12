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

package com.apress.beginninggwt.gwtasks.client;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.*;

/**
 * @author Uri Boness
 */
public class RegistrationFormDialogBox extends DialogBox {

	private final static String ERROR_IMAGE_URL = "image/field-error.gif";

	private TextBox fullNameField;
    private Image fullNameErrorImage;

    private TextBox emailField;
    private Image emailErrorImage;

    private TextBox usernameField;
    private Image usernameErrorImage;

    private PasswordTextBox passwordField;
    private Image passwordErrorImage;

    private PasswordTextBox retypePasswordField;
    private Image retypePasswordErrorImage;
    
    private LoginPane loginPane;
    
    public RegistrationFormDialogBox(LoginPane loginPane) {
    	super(false, true);
    	this.loginPane = loginPane;
		setText("Login");
		    	
    	VerticalPanel main = new VerticalPanel();
    	main.setWidth("200px");
		main.setVerticalAlignment(VerticalPanel.ALIGN_TOP);

        // full name field
		Label fullNameLabel = new Label("Full Name");
		fullNameField = new TextBox();
        fullNameErrorImage = createErrorImage();
        fullNameErrorImage.setVisible(false);
        main.add(fullNameLabel);
        HorizontalPanel fieldRow = new HorizontalPanel();
        fieldRow.add(fullNameField);
        addGap(fieldRow, "3px");
        fieldRow.add(fullNameErrorImage);
        fieldRow.setCellVerticalAlignment(fullNameErrorImage, HorizontalPanel.ALIGN_MIDDLE);
        main.add(fieldRow);
        addGap(main, "6px");

        // email field
		Label emailLabel = new Label("Email");
		emailField = new TextBox();
        emailErrorImage = createErrorImage();
        emailErrorImage.setVisible(false);
        main.add(emailLabel);
        fieldRow = new HorizontalPanel();
        fieldRow.add(emailField);
        addGap(fieldRow, "3px");
        fieldRow.add(emailErrorImage);
        fieldRow.setCellVerticalAlignment(emailErrorImage, HorizontalPanel.ALIGN_MIDDLE);
        main.add(fieldRow);
        addGap(main, "6px");

        // username field
		Label usernameLabel = new Label("Username");
		usernameField = new TextBox();
        usernameErrorImage = createErrorImage();
        usernameErrorImage.setVisible(false);
        main.add(usernameLabel);
        fieldRow = new HorizontalPanel();
        fieldRow.add(usernameField);
        addGap(fieldRow, "3px");
        fieldRow.add(usernameErrorImage);
        fieldRow.setCellVerticalAlignment(usernameErrorImage, HorizontalPanel.ALIGN_MIDDLE);
        main.add(fieldRow);
        addGap(main, "6px");

        // password field
		Label passwordLabel = new Label("Password");
		passwordField = new PasswordTextBox();
        passwordErrorImage = createErrorImage();
        passwordErrorImage.setVisible(false);
        main.add(passwordLabel);
        fieldRow = new HorizontalPanel();
        fieldRow.add(passwordField);
        addGap(fieldRow, "3px");
        fieldRow.add(passwordErrorImage);
        fieldRow.setCellVerticalAlignment(passwordErrorImage, HorizontalPanel.ALIGN_MIDDLE);
        main.add(fieldRow);
        addGap(main, "6px");

        // retype password field
		Label retypePasswordLabel = new Label("Retype");
		retypePasswordField = new PasswordTextBox();
        retypePasswordErrorImage = createErrorImage();
        retypePasswordErrorImage.setVisible(false);
        main.add(retypePasswordLabel);
        fieldRow = new HorizontalPanel();
        fieldRow.add(retypePasswordField);
        addGap(fieldRow, "3px");
        fieldRow.add(retypePasswordErrorImage);
        fieldRow.setCellVerticalAlignment(retypePasswordErrorImage, HorizontalPanel.ALIGN_MIDDLE);
        main.add(fieldRow);
        addGap(main, "15px");

        // the button that will submit the form
		Button registerButton = new Button("Regsiter", new ClickListener() {
            public void onClick(Widget sender) {
                handleRegister();
            }
        });
		Button cancelButton = new Button("Cancel", new ClickListener() {
            public void onClick(Widget sender) {
                handleCancel();
            }
        });
        HorizontalPanel buttons = new HorizontalPanel();
        buttons.add(registerButton);
        addGap(buttons, "5px");
        buttons.add(cancelButton);
        main.add(buttons);
        main.setCellVerticalAlignment(buttons, HorizontalPanel.ALIGN_MIDDLE);
        main.setCellHorizontalAlignment(buttons, HorizontalPanel.ALIGN_CENTER);
 		
 		main.setStyleName("DialogContent");
 		setWidget(main);
    }

	protected void handleRegister() {
        if (validate()) {
            Account account = new Account(
                    fullNameField.getText(),
                    emailField.getText(),
                    usernameField.getText(),
                    passwordField.getText());
            // creating an account
            loginPane.showInfoMessage("Account was created successfully");
            hide();
        }
    }

    protected void handleCancel() {
        hide();
    }

    protected void clearErrors() {
        fullNameErrorImage.setVisible(false);
        emailErrorImage.setVisible(false);
        usernameErrorImage.setVisible(false);
        passwordErrorImage.setVisible(false);
        retypePasswordErrorImage.setVisible(false);
    }

    protected boolean validate() {
		clearErrors();
        boolean valid = validateFullName();
        valid = validateEmail() && valid;
        valid = validateUsername() && valid;
        valid = validatePassword() && valid;
        return valid;
    }

    protected boolean validateFullName() {
        if (fullNameField.getText().trim().length() == 0) {
            fullNameErrorImage.setTitle("Required");
            fullNameErrorImage.setVisible(true);
            return false;
        }
        return true;
    }

    protected boolean validateEmail() {
        if (!emailField.getText().trim().matches(".+@.+\\.[a-z]+")) {
            emailErrorImage.setTitle("Invalid email");
            emailErrorImage.setVisible(true);
            return false;
        }
        return true;
    }

    protected boolean validateUsername() {
        if (usernameField.getText().trim().length() < 4) {
            usernameErrorImage.setTitle("At least 4 characters are required");
            usernameErrorImage.setVisible(true);
            return false;
        }
        return true;
    }

    protected boolean validatePassword() {
        String pwd = passwordField.getText().trim();
        boolean pwdValid = pwd.length() >= 4;
        if (!pwdValid) {
            passwordErrorImage.setTitle("At least 4 characters are required");
            passwordErrorImage.setVisible(true);
        }
        String retyped = retypePasswordField.getText().trim();
        boolean retypeValid = pwd.equals(retyped);
        if (!retypeValid) {
            retypePasswordErrorImage.setTitle("Retype mismatch");
            retypePasswordErrorImage.setVisible(true);
        }
        return pwdValid && retypeValid;
    }

	protected void addGap(HorizontalPanel panel, String gap) {
        Label label = new Label();
        label.setWidth(gap);
        panel.add(label);
        panel.setCellWidth(label, gap);
    }

    protected void addGap(VerticalPanel panel, String gap) {
        Label label = new Label();
        panel.add(label);
        panel.setCellHeight(label, gap);
    }

    protected Image createErrorImage() {
        return new Image(ERROR_IMAGE_URL);
    }
}
