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

import com.apress.beginninggwt.gwtasks.client.support.widget.CenterPanel;
import com.apress.beginninggwt.gwtasks.client.support.widget.Pane;
import com.apress.beginninggwt.gwtasks.client.support.widget.TitledPanel;
import com.apress.beginninggwt.gwtasks.client.ui.event.LoginEvent;
import com.google.gwt.user.client.ui.*;

/**
 * @author Uri Boness
 */
public class LoginPane extends Pane {

	private final Label messageLabel;
    private final TextBox usernameField;
    private final PasswordTextBox passwordField;

    public LoginPane() {
        VerticalPanel content = new VerticalPanel();
        content.setSize("100%", "100%");

        messageLabel = new Label();
        messageLabel.setStyleName("MessageLabel");
        content.add(messageLabel);

        Grid grid = new Grid(3, 2);
        grid.setWidget(0, 0, createFieldLabel("Username:"));
        usernameField = new TextBox();
        grid.setWidget(0, 1, usernameField);
        grid.setWidget(1, 0, createFieldLabel("Password:"));
 		passwordField = new PasswordTextBox();
        grid.setWidget(1, 1, passwordField);        
        Button loginButton = new Button("Login", new ClickListener() {
            public void onClick(Widget sender) {
                handleLogin();
            }
        });
        grid.setWidget(2, 1, loginButton);
        grid.getCellFormatter().setStyleName(2, 1, "ButtonPanel");
        grid.getCellFormatter().setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_RIGHT);
        content.add(grid);
        grid.setStyleName("Form");
        
        TitledPanel main = new TitledPanel("Login", new CenterPanel(content));
		main.setContentHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        Image registerImage = new Image("image/edit.gif");
        registerImage.setTitle("Register");
        main.addToolButton(registerImage, new ClickListener() {
            public void onClick(Widget sender) {
                handleRegister();
            }
        });
        main.setSize("350px", "150px");

        initWidget(new CenterPanel(main));
        setStyleName("LoginPane");
    }

    public void reset() {
        clearMessage();
        usernameField.setText("");
        passwordField.setText("");
    }

    protected Widget createFieldLabel(String text) {
        Label label = new Label(text);
        label.setStyleName("FieldLabel");
        return label;
    }

	protected void handleLogin() {
		clearMessage();
		String username = usernameField.getText().trim();
		String password = passwordField.getText().trim();
		if (authenticate(username, password)) {
            fireEvent(new LoginEvent(this, username));
        } else {
            showErrorMessage("Invalid username and/or password");
            passwordField.setText("");
        }
	}

    protected boolean authenticate(String username, String password) {
        clearMessage();
        // authenticate the user
        return true;
    }

    protected void handleRegister() {
    	RegistrationFormDialogBox dialog = new RegistrationFormDialogBox(this);
    	dialog.center();
    	dialog.show();
    }

    protected void clearMessage() {
        messageLabel.removeStyleDependentName("error");
        messageLabel.removeStyleDependentName("info");
        messageLabel.setText("");
        messageLabel.setVisible(false);
    }

    protected void showErrorMessage(String message) {
        showMessage(message, "error");
    }

    protected void showInfoMessage(String message) {
        showMessage(message, "info");
    }

    protected void showMessage(String message, String style) {
        clearMessage();
        messageLabel.addStyleDependentName(style);
        messageLabel.setText(message);
        messageLabel.setVisible(true);
    }

}
