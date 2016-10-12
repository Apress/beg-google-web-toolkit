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

package com.apress.beginninggwt.gwtasks.client.manager.data;

import com.apress.beginninggwt.gwtasks.client.model.Category;
import com.apress.beginninggwt.gwtasks.client.model.Task;
import com.apress.beginninggwt.gwtasks.client.service.RemoteTaskService;
import com.apress.beginninggwt.gwtasks.client.service.RemoteTaskServiceAsync;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.core.client.GWT;

import java.util.List;

/**
 * @author Roald Bankras
 */
public class RemoteDataManager implements DataManager {

    public void createCategory(Category category, Long parentCategoryId, AsyncCallback<Category> callback) {
        RemoteTaskService.Locator.getInstance().createCategory(category, parentCategoryId, callback);
    }

    public void updateCategory(Category category, AsyncCallback<Void> callback) {
        RemoteTaskService.Locator.getInstance().updateCategory(category, callback);
    }

    public void removeCategory(long categoryId, AsyncCallback<Void> callback) {
        RemoteTaskService.Locator.getInstance().removeCategory(categoryId, callback);
    }

    public void getCategories(AsyncCallback<List<Category>> callback) {
        RemoteTaskService.Locator.getInstance().getCategories(callback);
    }

    public void createTask(Task task, long categoryId, AsyncCallback<Task> callback) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void updateTask(Task task, AsyncCallback<Void> callback) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void removeTask(long taskId, AsyncCallback<Void> callback) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void getTasks(long categoryId, AsyncCallback<List<Task>> callback) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
