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
 
package com.apress.beginninggwt.gwtasks.client.manager.security;

import com.apress.beginninggwt.gwtasks.client.model.Account;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Manages all security aspects of the application.
 *
 * @author Uri Boness
 */
public interface SecurityManager {

    /**
     * Creates a new account based on the given account.
     *
     * @param account The account to create.
     * @param callback Will be called with the newly created account.
     */
    void createAccount(Account account, AsyncCallback<Account> callback);

    /**
     * Logs in to the application based on the given authentication. On successful login the authentication will be
     * registered with this manager as the currently logged in user.
     *
     * @param authentication Holds the authentication information for the login.
     * @param callback On a successful login, called with the account associated witht he given authentication.
     */
    void login(Authentication authentication, AsyncCallback<Account> callback);

    /**
     * Logs out the currently authenticated user.
     */
    void logout();

    /**
     * Indicates whether there is a logged in user.
     *
     * @return Whether there is a logged in user.
     */
    boolean isLoggedIn();

    /**
     * Returns the currently logged in authentication.
     *
     * @return The currently logged in authentication.
     */
    Authentication getCurrentAuthentication();

}
