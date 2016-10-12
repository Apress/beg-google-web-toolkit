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

/**
 * Serves as a parent for all application level events. It holds the {@link ApplicationEventSource source} that triggered
 * the event and enforces each event implementation to provide an appropriate description for the event.
 *
 * @author Uri Boness
 */
public abstract class ApplicationEvent {

    private final ApplicationEventSource source;

    /**
     * Constructs a new AppliationEvent which is triggered by the given {@link ApplicationEventSource source}.
     *
     * @param source The {@link ApplicationEventSource source} that triggers this event.
     */
    protected ApplicationEvent(ApplicationEventSource source) {
        this.source = source;
    }

    /**
     * Returns the {@link ApplicationEventSource source} that triggered this event.
     *
     * @return The {@link ApplicationEventSource source} that triggered this event.
     */
    public ApplicationEventSource getSource() {
        return source;
    }

    /**
     * Returns the description of this event. This can be a dynamic description that changes between event instances of
     * the same type.
     *
     * @return The description of this event.
     */
    public abstract String getDescription();

}
