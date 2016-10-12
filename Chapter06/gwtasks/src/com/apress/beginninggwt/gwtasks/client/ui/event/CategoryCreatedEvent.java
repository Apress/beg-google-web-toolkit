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
import com.apress.beginninggwt.gwtasks.client.model.Category;

/**
 * Triggered whenever a new category is created.
 *
 * @author Uri Boness
 */
public class CategoryCreatedEvent extends ApplicationEvent {

    private final Category category;

    /**
     * Constructs a new CategoryCreatedEvent with the given new category.
     *
     * @param source The source of this event.
     * @param category The newly created category.
     */
    public CategoryCreatedEvent(ApplicationEventSource source, Category category) {
        super(source);
        this.category = category;
    }

    /**
     * {@inheritDoc}
     */
    public String getDescription() {
        return "Created category '" + category.getName() + "'";
    }

    //============================================= Setter/Getter ======================================================

    /**
     * Returns the newly created category.
     *
     * @return The newly created category.
     */
    public Category getCategory() {
        return category;
    }
}
