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

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.apress.beginninggwt.gwtasks.client.manager.ManagerRegistry;
import com.apress.beginninggwt.gwtasks.client.manager.ui.UIManager;
import com.apress.beginninggwt.gwtasks.client.manager.data.DataManager;
import com.apress.beginninggwt.gwtasks.client.manager.security.*;
import com.apress.beginninggwt.gwtasks.client.manager.security.SecurityManager;
import com.apress.beginninggwt.gwtasks.client.model.Account;

/**
 * @author Bram Smeets
 */
public class LoginPaneTests extends GWTTestCase {

    private LoginPane loginPane;

    public String getModuleName() {
        return "com.apress.beginninggwt.gwtasks.DefaultModule";
    }

    protected void gwtSetUp() throws Exception {
        super.gwtSetUp();

        loginPane = new LoginPane(new MockManagerRegistry());
    }

    public void testHandleLogin() {
        loginPane.handleLogin();
    }

    private class MockManagerRegistry implements ManagerRegistry {
        public SecurityManager getSecurityManager() {
            return new MockSecurityManager();
        }

        public DataManager getDataManager() {
            return null;
        }

        public UIManager getUIManager() {
            return null;
        }
    }

    private class MockSecurityManager implements SecurityManager {
        public void createAccount(Account account, AsyncCallback<Account> callback) {
            // do nothing
        }

        public void login(Authentication authentication, AsyncCallback<Account> callback) {
            // do nothing
        }

        public void logout() {
            // do nothing
        }

        public boolean isLoggedIn() {
            return false;
        }

        public Authentication getCurrentAuthentication() {
            return null;
        }
    }
    
}
