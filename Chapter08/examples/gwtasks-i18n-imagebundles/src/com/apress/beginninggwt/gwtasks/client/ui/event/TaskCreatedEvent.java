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
import com.apress.beginninggwt.gwtasks.client.model.Task;
import com.apress.beginninggwt.gwtasks.client.ui.i18n.GWTasksMessages;

/**
 * Triggered whenever a new task is created.
 *
 * @author Uri Boness
 */
public class TaskCreatedEvent extends ApplicationEvent {

    private final Task task;

    /**
     * Constructs a new TaskCreatedEvent with the given created task.
     *
     * @param source The source of this event.
     * @param task The newly created task.
     */
    public TaskCreatedEvent(ApplicationEventSource source, Task task) {
        super(source);
        this.task = task;
    }

    /**
     * {@inheritDoc}
     */
    public String getDescription() {
        return GWTasksMessages.Impl.getInstance().taskCreatedEventDescription(task.getTitle());
    }


    //============================================= Setter/Getter ======================================================

    /**
     * Returns the newly created task.
     *
     * @return The newly created task.
     */
    public Task getTask() {
        return task;
    }
}
