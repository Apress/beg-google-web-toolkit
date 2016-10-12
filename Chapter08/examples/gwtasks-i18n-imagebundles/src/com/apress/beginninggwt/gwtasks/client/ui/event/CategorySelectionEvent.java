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
import com.apress.beginninggwt.gwtasks.client.ui.i18n.GWTasksMessages;

/**
 * Triggered whenever a category selection changes. If a category was selected, this event will hold the selected
 * category and it can be fetched by calling {@link #getCategory()}. If a category selection was cleared, calling this
 * method will return <code>null</code>.
 *
 * @author Uri Boness
 */
public class CategorySelectionEvent extends ApplicationEvent {

    private final Category category;

    /**
     * Constructs a new CategorySelectionEvent.
     *
     * @param source The source of this event.
     * @param category The selected category. Can be <code>null</code> to represent that a selection was cleared.
     */
    public CategorySelectionEvent(ApplicationEventSource source, Category category) {
        super(source);
        this.category = category;
    }

    /**
     * {@inheritDoc}
     */
    public String getDescription() {
        if (category == null) {
            return "";
        }
        return GWTasksMessages.Impl.getInstance().categorySelectedEventDescription(category.getName());
    }

    //============================================= Setter/Getter ======================================================

    /**
     * Returns the selected category or <code>null</code> if a selection was cleared.
     *
     * @return The selected category or <code>null</code> if a selection was cleared.
     */
    public Category getCategory() {
        return category;
    }
}
