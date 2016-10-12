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

import com.apress.beginninggwt.gwtasks.client.manager.ManagerRegistry;
import com.apress.beginninggwt.gwtasks.client.manager.security.Authentication;
import com.apress.beginninggwt.gwtasks.client.manager.security.AuthenticationException;
import com.apress.beginninggwt.gwtasks.client.model.Account;
import com.apress.beginninggwt.gwtasks.client.support.async.Callback;
import com.apress.beginninggwt.gwtasks.client.support.widget.CenterPanel;
import com.apress.beginninggwt.gwtasks.client.support.widget.TitledPanel;
import com.apress.beginninggwt.gwtasks.client.ui.Pane;
import com.apress.beginninggwt.gwtasks.client.ui.image.GWTasksImages;
import com.apress.beginninggwt.gwtasks.client.ui.i18n.GWTasksConstants;
import com.apress.beginninggwt.gwtasks.client.ui.event.LoginEvent;
import com.google.gwt.user.client.ui.*;

/**
 * A {@link Pane} showing the login page.
 *
 * @author Uri Boness
 */
public class LoginPane extends Pane {

	private final Label messageLabel;
    private final TextBox usernameField;
    private final PasswordTextBox passwordField;

    /**
     * Construts a new LoginPane with a given manager registry.
     *
     * @param managerRegistry The manager registry to associated with this pane.
     */
    public LoginPane(ManagerRegistry managerRegistry) {
        super(managerRegistry);

        GWTasksConstants constants = GWTasksConstants.Impl.getInstance();

        VerticalPanel content = new VerticalPanel();
        content.setSize("100%", "100%");

        messageLabel = new Label();
        messageLabel.setStyleName("MessageLabel");
        content.add(messageLabel);

        Grid grid = new Grid(3, 2);
        grid.setWidget(0, 0, createFieldLabel(constants.loginPaneUsernameLabel()));
        usernameField = new TextBox();
        grid.setWidget(0, 1, usernameField);
        grid.setWidget(1, 0, createFieldLabel(constants.loginPanePasswordLabel()));
 		passwordField = new PasswordTextBox();
        grid.setWidget(1, 1, passwordField);        
        Button loginButton = new Button(constants.loginButton(), new ClickListener() {
            public void onClick(Widget sender) {
                handleLogin();
            }
        });
        grid.setWidget(2, 1, loginButton);
        grid.getCellFormatter().setStyleName(2, 1, "ButtonPanel");
        grid.getCellFormatter().setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_RIGHT);
        content.add(grid);
        grid.setStyleName("Form");
        
        TitledPanel main = new TitledPanel(constants.loginPaneTitle(), new CenterPanel(content));
		main.setContentHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        Image registerImage = GWTasksImages.Impl.getInstance().editIcon().createImage();
        registerImage.setTitle(constants.loginPaneRegisterTooltip());
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
        Authentication authentication = new Authentication(username, password);
        getManagerRegistry().getSecurityManager().login(authentication, new Callback<Account>() {
            public void onSuccess(Account account) {
                clearMessage();
                fireEvent(new LoginEvent(LoginPane.this, account.getUsername()));
            }

            public void onFailure(Throwable caught) {
                if (caught instanceof AuthenticationException) {
                    showErrorMessage(GWTasksConstants.Impl.getInstance().loginPaneAuthenticationError());
                    passwordField.setText("");
                } else {
                    super.onFailure(caught);
                }
            }
        });
	}

    protected void handleRegister() {
    	RegistrationFormDialogBox dialog = new RegistrationFormDialogBox(this, getManagerRegistry().getSecurityManager());
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
