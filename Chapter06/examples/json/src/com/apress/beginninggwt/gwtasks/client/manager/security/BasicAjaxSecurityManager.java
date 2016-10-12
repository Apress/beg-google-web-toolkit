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

import com.apress.beginninggwt.gwtasks.client.manager.Server;
import com.apress.beginninggwt.gwtasks.client.model.Account;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Roald Bankras
 */
public class BasicAjaxSecurityManager implements SecurityManager {

    private Authentication authentication = Authentication.ANONYMOUS;

    public void login(final Authentication authentication, final AsyncCallback<Account> callback) {
        Server.post("login", "username=" + authentication.getUsername()
                        + "&password="+ authentication.getPassword(), new AsyncCallback<String>() {
            public void onFailure(Throwable throwable) {
                callback.onFailure(new AuthenticationException("Could not authenticate '" + authentication.getUsername() + "'"));
            }

            public void onSuccess(String response) {
                Account account = parseAccount(response);
                BasicAjaxSecurityManager.this.authentication = new Authentication(account.getUsername(), account.getPassword());
                callback.onSuccess(account);
            }
        });
    }

    public void createAccount(Account account, final AsyncCallback<Account> callback) {
        JSONObject jsonAccount = new JSONObject();
        jsonAccount.put("fullname", new JSONString(account.getFullName()));
        jsonAccount.put("email", new JSONString(account.getEmail()));
        jsonAccount.put("username", new JSONString(account.getUsername()));
        jsonAccount.put("password", new JSONString(account.getPassword()));
        Server.post("createaccount", jsonAccount.toString(), new AsyncCallback<String>() {
            public void onFailure(Throwable throwable) {
                callback.onFailure(throwable);
            }

            public void onSuccess(String response) {
                Account account = parseAccount(response);
                callback.onSuccess(account);
            }
        });
    }

    public void logout() {
        authentication = Authentication.ANONYMOUS;
    }

    public boolean isLoggedIn() {
        return authentication != Authentication.ANONYMOUS;
    }

    public Authentication getCurrentAuthentication() {
        return authentication;
    }

    private Account parseAccount(String response) {
        Account account = new Account();
        JSONValue responseValue = JSONParser.parse(response);
        JSONObject responseObject = responseValue.isObject();
        if(responseObject != null) {
            account.setId(((Double) responseObject.get("id").isNumber().doubleValue()).longValue());
            account.setFullName(responseObject.get("fullname").isString().stringValue());
            account.setEmail(responseObject.get("email").isString().stringValue());
            account.setUsername(responseObject.get("username").isString().stringValue());
            account.setPassword(responseObject.get("password").isString().stringValue());
        }
        return account;
    }
}
