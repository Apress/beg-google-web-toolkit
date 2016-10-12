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
package com.apress.beginninggwt.gwtasks.client.ui.loginpane;

import com.apress.beginninggwt.gwtasks.client.manager.security.SecurityManager;
import com.apress.beginninggwt.gwtasks.client.model.Account;
import com.apress.beginninggwt.gwtasks.client.support.async.Callback;
import com.apress.beginninggwt.gwtasks.client.ui.i18n.GWTasksConstants;
import com.apress.beginninggwt.gwtasks.client.ui.i18n.GWTasksMessages;
import com.apress.beginninggwt.gwtasks.client.ui.image.GWTasksImages;
import com.google.gwt.user.client.ui.*;

/**
 * @author Uri Boness
 */
public class RegistrationFormDialogBox extends DialogBox {

	private final TextBox fullNameField;
    private final Image fullNameErrorImage;

    private final TextBox emailField;
    private final Image emailErrorImage;

    private final TextBox usernameField;
    private final Image usernameErrorImage;

    private final PasswordTextBox passwordField;
    private final Image passwordErrorImage;

    private final PasswordTextBox retypePasswordField;
    private final Image retypePasswordErrorImage;
    
    private final LoginPane loginPane;
    private final SecurityManager securityManager;
    
    public RegistrationFormDialogBox(LoginPane loginPane, SecurityManager securityManager ) {
    	super(false, true);
    	this.loginPane = loginPane;
		this.securityManager = securityManager;

        GWTasksConstants constants = GWTasksConstants.Impl.getInstance();

        setText(constants.registrationFormTitle());
		    	
    	VerticalPanel main = new VerticalPanel();
    	main.setWidth("200px");
		main.setVerticalAlignment(VerticalPanel.ALIGN_TOP);

        // full name field
		Label fullNameLabel = new Label(constants.registrationFormFullNameLabel());
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
		Label emailLabel = new Label(constants.registrationFormEmailLabel());
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
		Label usernameLabel = new Label(constants.registrationFormUsernameLabel());
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
		Label passwordLabel = new Label(constants.registrationFormPasswordLabel());
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
		Label retypePasswordLabel = new Label(constants.registrationFormPasswordRetypeLabel());
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
		Button registerButton = new Button(constants.registrationFormRegisterButton(), new ClickListener() {
            public void onClick(Widget sender) {
                handleRegister();
            }
        });
		Button cancelButton = new Button(constants.cancelButton(), new ClickListener() {
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

            securityManager.createAccount(account, new Callback<Account>() {
                public void onSuccess(Account result) {
                    loginPane.showInfoMessage(GWTasksConstants.Impl.getInstance().registrationFormSuccessfulRegistrationMessage());
                    hide();
                }
            });
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
            fullNameErrorImage.setTitle(GWTasksConstants.Impl.getInstance().requiredError());
            fullNameErrorImage.setVisible(true);
            return false;
        }
        return true;
    }

    protected boolean validateEmail() {
        if (!emailField.getText().trim().matches(".+@.+\\.[a-z]+")) {
            emailErrorImage.setTitle(GWTasksConstants.Impl.getInstance().invalidEmailError());
            emailErrorImage.setVisible(true);
            return false;
        }
        return true;
    }

    protected boolean validateUsername() {
        if (usernameField.getText().trim().length() < 4) {
            usernameErrorImage.setTitle(GWTasksMessages.Impl.getInstance().minLengthError(4));
            usernameErrorImage.setVisible(true);
            return false;
        }
        return true;
    }

    protected boolean validatePassword() {
        String pwd = passwordField.getText().trim();
        boolean pwdValid = pwd.length() >= 4;
        if (!pwdValid) {
            passwordErrorImage.setTitle(GWTasksMessages.Impl.getInstance().minLengthError(4));
            passwordErrorImage.setVisible(true);
        }
        String retyped = retypePasswordField.getText().trim();
        boolean retypeValid = pwd.equals(retyped);
        if (!retypeValid) {
            retypePasswordErrorImage.setTitle(GWTasksConstants.Impl.getInstance().retypeMismatchError());
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
        return GWTasksImages.Impl.getInstance().fieldErrorIcon().createImage();
    }

}
