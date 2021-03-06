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

package com.apress.beginninggwt.gwtasks.client.support.async;

import com.apress.beginninggwt.gwtasks.client.model.Task;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.core.client.GWT;

import java.util.List;

public class TasksCallback extends MyAsyncCallback<List<Task>> {

    public void onSuccess(List<Task> tasks) {
        VerticalPanel taskPanel = new VerticalPanel();
        for(Task task : tasks) {
            GWT.log("Received task: " + task.getTitle(), null);
            taskPanel.add(new Label(task.getTitle()));
        }
        RootPanel.get().add(taskPanel);
    }
}
