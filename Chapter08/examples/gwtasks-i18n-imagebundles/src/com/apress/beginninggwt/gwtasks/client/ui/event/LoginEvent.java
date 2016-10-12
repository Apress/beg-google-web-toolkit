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
 
package com.apress.beginninggwt.gwtasks.client.ui.event;

import com.apress.beginninggwt.gwtasks.client.support.event.ApplicationEvent;
import com.apress.beginninggwt.gwtasks.client.support.event.ApplicationEventSource;
import com.apress.beginninggwt.gwtasks.client.ui.i18n.GWTasksMessages;

/**
 * Triggered when a user succesfully logged in the application.
 *
 * @author Uri Boness
 */
public class LoginEvent extends ApplicationEvent {

    private final String username;

    /**
     * Consructs a new LoginEvent.
     *
     * @param source The source of this event.
     * @param username The username of the logged in user.
     */
    public LoginEvent(ApplicationEventSource source, String username) {
        super(source);
        this.username = username;
    }

    /**
     * {@inheritDoc}
     */
    public String getDescription() {
        return GWTasksMessages.Impl.getInstance().loginEventDescription(username);
    }

    //============================================= Setter/Getter ======================================================

    /**
     * Returns the username of the logged in user.
     *
     * @return The username of the logged in user.
     */
    public String getUsername() {
        return username;
    }
}


