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

import java.util.Map;
import java.util.HashMap;

/**
 * Default in-memory implementation of the {@link SecurityManager}.
 *
 * @author Uri Boness
 */
public class InMemorySecurityManager implements SecurityManager {

    private final static Map<String, Account> accountByUsername = new HashMap<String, Account>();
    private static Authentication authentication = Authentication.ANONYMOUS;
    private static long accountIdCounter = 0;

    /**
     * {@inheritDoc}
     */
    public void createAccount(Account account, AsyncCallback<Account> callback) {
        Account newAccount = new Account(account);
        newAccount.setId(++accountIdCounter);
        accountByUsername.put(newAccount.getUsername(), newAccount);
        callback.onSuccess(newAccount);
    }

    /**
     * {@inheritDoc}
     */
    public void login(Authentication auth, AsyncCallback<Account> callback) {
        if (auth == Authentication.ANONYMOUS) {
            throw new IllegalArgumentException("Cannot login anonymous authentication");
        }
        Account account = accountByUsername.get(auth.getUsername());
        if (account == null || !auth.getPassword().equals(account.getPassword())) {
            callback.onFailure(new AuthenticationException("Could not authenticate '" + auth.getUsername() + "'"));
        } else {
             authentication = new Authentication(account.getUsername(), account.getPassword());
            callback.onSuccess(account);
        }
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

}
