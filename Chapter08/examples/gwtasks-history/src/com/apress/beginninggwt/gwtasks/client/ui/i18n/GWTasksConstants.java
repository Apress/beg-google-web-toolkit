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
 
package com.apress.beginninggwt.gwtasks.client.ui.i18n;

import com.google.gwt.i18n.client.Constants;
import com.google.gwt.core.client.GWT;

/**
 * @author Uri Boness
 */
public interface GWTasksConstants extends Constants {


    //============================================= Common Buttons =====================================================

    @Key("button.login")
    @DefaultStringValue("Login")
    String loginButton();

    @Key("button.logout")
    @DefaultStringValue("Logout")
    String logoutButton();

    @Key("button.add")
    @DefaultStringValue("Add")
    String addButton();

    @Key("button.edit")
    @DefaultStringValue("Edit")
    String editButton();

    @Key("button.cancel")
    @DefaultStringValue("Cancel")
    String cancelButton();


    //============================================= Common Errors ======================================================

    @Key("error.required")
    @DefaultStringValue("Required")
    String requiredError();

    @Key("error.invalidEmail")
    @DefaultStringValue("Invalid email")
    String invalidEmailError();


    @Key("error.retypeMismatch")
    @DefaultStringValue("Retype mismatch")
    String retypeMismatchError();

    //============================================ Event Messages ======================================================

    @Key("event.logout.description")
    @DefaultStringValue("User logged out")
    String logoutEventDescription();


    //=========================================== LoginPane Messages ===================================================

    @Key("LoginPane.title")
    @DefaultStringValue("Login")
    String loginPaneTitle();

    @Key("LoginPane.label.username")
    @DefaultStringValue("Username:")
    String loginPaneUsernameLabel();

    @Key("LoginPane.label.password")
    @DefaultStringValue("Password:")
    String loginPanePasswordLabel();

    @Key("LoginPane.tooltip.register")
    @DefaultStringValue("Register")
    String loginPaneRegisterTooltip();

    @Key("LoginPane.error.authentication")
    @DefaultStringValue("Invalid username and/or password")
    String loginPaneAuthenticationError();


    //=================================== RegistrationFormDialogBox Messages ===========================================

    @Key("RegistrationFormDialogBox.title")
    @DefaultStringValue("Registration Form")
    String registrationFormTitle();

    @Key("RegistrationFormDialogBox.label.fullName")
    @DefaultStringValue("Full Name")
    String registrationFormFullNameLabel();

    @Key("RegistrationFormDialogBox.label.email")
    @DefaultStringValue("Email")
    String registrationFormEmailLabel();

    @Key("RegistrationFormDialogBox.label.username")
    @DefaultStringValue("Username")
    String registrationFormUsernameLabel();

    @Key("RegistrationFormDialogBox.label.password")
    @DefaultStringValue("Password")
    String registrationFormPasswordLabel();

    @Key("RegistrationFormDialogBox.label.retyp-password")
    @DefaultStringValue("Retype")
    String registrationFormPasswordRetypeLabel();

    @Key("RegistrationFormDialogBox.button.register")
    @DefaultStringValue("Register")
    String registrationFormRegisterButton();

    @Key("RegistrationFormDialogBox.message.sucessfulRegistration")
    @DefaultStringValue("Account was created successfully")
    String registrationFormSuccessfulRegistrationMessage();


    //=========================================== MainPane Messages ===================================================

    @Key("MainPane.header.title")
    @DefaultStringValue("GWTasks")
    String mainPaneHeaderTitle();


    //=========================================== TaskPane Messages ===================================================

    @Key("TaskPane.title")
    @DefaultStringValue("Tasks")
    String taskPaneTitle();

    @Key("TaskPane.button.remove")
    @DefaultStringValue("Remove Selected Task")
    String taskPaneRemoveButton();

    @Key("TaskPane.button.add")
    @DefaultStringValue("New Task")
    String taskPaneAddButton();

    @Key("TaskPane.table.titleHeader")
    @DefaultStringValue("Title")
    String taskPaneTitleTableHeader();


    //======================================= TaskFormDialogBox Messages ===============================================

    @Key("TaskFormDialogBox.title")
    @DefaultStringValue("Task Form")
    String taskFormTitle();

    @Key("TaskFormDialogBox.label.title")
    @DefaultStringValue("Title")
    String taskFormTitleLabel();

    @Key("TaskFormDialogBox.label.priority")
    @DefaultStringValue("Priority")
    String taskFormPriorityLabel();

    @Key("TaskFormDialogBox.label.description")
    @DefaultStringValue("Description")
    String taskFormDescriptionLabel();

    @Key("TaskFormDialogBox.priority.low")
    @DefaultStringValue("Low")
    String taskFormLowPriority();

    @Key("TaskFormDialogBox.priority.normal")
    @DefaultStringValue("Normal")
    String taskFormNormalPriority();

    @Key("TaskFormDialogBox.priority.high")
    @DefaultStringValue("High")
    String taskFormHighPriority();


    //========================================== CategoryPane Messages =================================================

    @Key("CategoryPane.title")
    @DefaultStringValue("Categories")
    String categoryPaneTitle();

    @Key("CategoryPane.button.add")
    @DefaultStringValue("New Category")
    String categoryPaneAddButton();

    @Key("CategoryPane.button.remove")
    @DefaultStringValue("Remove Selected Category")
    String categoryPaneRemoveButton();


    //====================================== CategoryFormDialogBox Messages ============================================

    @Key("CategoryFormDialogBox.title")
    @DefaultStringValue("Category Form")
    String categoryFormTitle();

    @Key("CategoryFormDialogBox.label.name")
    @DefaultStringValue("Name")
    String categoryFormNameLabel();

    @Key("CategoryFormDialogBox.label.description")
    @DefaultStringValue("Description")
    String categoryFormDescriptionLabel();


    /**
     * The Locator pattern
     */
    public static class Impl {

        private static GWTasksConstants instance;

        public static GWTasksConstants getInstance() {
            if (instance == null) {
                instance = GWT.create(GWTasksConstants.class);
            }
            return instance;
        }
    }
}
