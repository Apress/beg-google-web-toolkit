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
 * Represents a source by which application events can be fired. As such, it provides method to register application
 * event listeners that will be notified of the fired event.
 *
 * @author Uri Boness
 */
public interface ApplicationEventSource {

    /**
     * Adds the given application event listener to this source.
     *
     * @param listener The applcation event listener that will notified of the events fired by this source.
     */
    void addListener(ApplicationEventListener listener);

    /**
     * Removes the given application event listener from this source.
     *
     * @param listener The listener to be removed (if the given listener is not registered with this source, this method
     *        wiill do nothing and exit quietly).
     */
    void removeListener(ApplicationEventListener listener);

    /**
     * Removes all application event listeners registered with this source.
     */
    void clearListeners();

}
