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

package com.apress.beginninggwt.gwtasks.client.support.widget;

import com.apress.beginninggwt.gwtasks.client.support.event.ApplicationEventListener;
import com.apress.beginninggwt.gwtasks.client.support.event.ApplicationEvent;
import com.apress.beginninggwt.gwtasks.client.support.event.ApplicationEventSource;
import com.apress.beginninggwt.gwtasks.client.support.event.ApplicationEventListenerCollection;
import com.google.gwt.user.client.ui.Composite;

/**
 * Serves as a base class for all panes in the application.
 *
 * @author Uri Boness
 */
public abstract class Pane extends Composite implements ApplicationEventSource {

    private final ApplicationEventListenerCollection listeners;

    /**
     * Constructs a new Pane.
     */
    protected Pane() {
        listeners = new ApplicationEventListenerCollection();
    }

    /**
     * {@inheritDoc}
     */
    public void addListener(ApplicationEventListener listener) {
        listeners.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    public void removeListener(ApplicationEventListener listener) {
        listeners.remove(listener);
    }

    /**
     * {@inheritDoc}
     */
    public void clearListeners() {
        listeners.clear();
    }

    /**
     * Fires the given event to all the registerred listener.
     *
     * @param event The event to fire.
     */
    protected void fireEvent(ApplicationEvent event) {
        listeners.fireEvent(event);
    }

}
