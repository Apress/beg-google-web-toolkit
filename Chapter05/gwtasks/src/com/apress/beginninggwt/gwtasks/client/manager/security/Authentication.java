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

/**
 * Holds user login information
 *
 * @author Uri Boness
 */
public class Authentication {

    /**
     * An authentication object representing an anonymous user.
     */
    public final static Authentication ANONYMOUS = new Authentication("anonymous", "anonymous");

    private final String username;
    private final String password;

    /**
     * Constructs a new Authentication with given username and password.
     *
     * @param username The username.
     * @param password The password.
     */
    public Authentication(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the username of this authentication.
     *
     * @return The username of this authentication.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of this authentication.
     *
     * @return The password of this authentication.
     */
    public String getPassword() {
        return password;
    }

}
