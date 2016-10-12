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
 
package com.apress.beginninggwt.gwtasks.client.support.event;

import java.util.ArrayList;

/**
 * A helper class that helps implementing {@link ApplicationEventSource application event sources}. This class
 * serves as a collection of application event listeners. It is also possible to fire events to all the contained
 * listeners via the {@link #fireEvent(ApplicationEvent)} method.
 *
 * @author Uri Boness
 */
public class ApplicationEventListenerCollection extends ArrayList<ApplicationEventListener> {

    /**
     * Fires the given event to all the contained listeners.
     *
     * @param event The event to be fired.
     */
    public void fireEvent(ApplicationEvent event) {
        for (ApplicationEventListener listener : this) {
            listener.handle(event);
        }
    }

}
