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
import com.apress.beginninggwt.gwtasks.client.service.RemoteTaskService;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * A remote implementation of the {@link SecurityManager} which is based on GWT RPC by using the
 * {@link RemoteTaskService}.
 *
 * @author Roald Bankras
 */
public class RemoteSecurityManager implements SecurityManager {

    // holds the currently authenticated user.
    private static Authentication authentication = Authentication.ANONYMOUS;

    /**
     * {@inheritDoc}
     */
    public void createAccount(Account account, final AsyncCallback<Account> callback) {
        RemoteTaskService.Locator.getInstance().createAccount(account, callback);
    }

    /**
     * {@inheritDoc}
     */
    public void login(Authentication authentication, final AsyncCallback<Account> callback) {
        RemoteTaskService.Locator.getInstance().login(authentication, new AsyncCallback<Account>() {
            public void onFailure(Throwable throwable) {
                callback.onFailure(throwable);
            }

            public void onSuccess(Account account) {
                setAuthentication(new Authentication(account.getUsername(), account.getPassword()));
                callback.onSuccess(account);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    public void logout() {
        authentication = Authentication.ANONYMOUS;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isLoggedIn() {
        return authentication != Authentication.ANONYMOUS;
    }

    /**
     * {@inheritDoc}
     */
    public Authentication getCurrentAuthentication() {
        return authentication;
    }

    /**
     * {@inheritDoc}
     */
    protected static void setAuthentication(Authentication authentication) {
        RemoteSecurityManager.authentication = authentication;
    }

}
