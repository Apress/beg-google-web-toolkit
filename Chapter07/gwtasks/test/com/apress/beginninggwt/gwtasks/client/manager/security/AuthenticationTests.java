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

import junit.framework.TestCase;

/**
 * @author Bram Smeets
 */
public class AuthenticationTests extends TestCase {

    public void testConstructor() {
        Authentication authentication = new Authentication(null, null);
        assertNull(authentication.getUsername());
        assertNull(authentication.getPassword());

        authentication = new Authentication("username", "password");
        assertNotNull(authentication.getUsername());
        assertEquals(authentication.getUsername(), "username");
        assertNotNull(authentication.getPassword());
        assertEquals(authentication.getPassword(), "password");
    }

    public void testDefaultConstructor() {
        Authentication authentication = new Authentication();
        assertNotNull(authentication.getUsername());
        assertEquals(authentication.getUsername(), "anonymous");
        assertNotNull(authentication.getPassword());
        assertEquals(authentication.getPassword(), "anonymous");
    }

}
