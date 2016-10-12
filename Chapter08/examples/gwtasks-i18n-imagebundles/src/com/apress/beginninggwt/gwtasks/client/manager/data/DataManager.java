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
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

/**
 * Manages all data aspects of the application.
 *
 * @author Uri Boness
 */
public interface DataManager {


    //============================================== Category Methods ==================================================

    /**
     * Creates a new category in the system and adds it as a child to the category identified by the given cateogry id.
     * If the given category id is <code>null</code> the category will be treated as a top-level category.
     *
     * @param category The category to create.
     * @param parentCategoryId The id of the parent category or <code>null</code> if the created category is a top
     *        level one.
     * @param callback Called with the newly created category.
     */
    void createCategory(Category category, Long parentCategoryId, AsyncCallback<Category> callback);

    /**
     * Updates the given category in the system. The category will be identified by its id.
     *
     * @param category The category to be updated.
     * @param callback Called as a feedback for the success/failure of the update.
     */
    void updateCategory(Category category, AsyncCallback<Void> callback);

    /**
     * Remvoes the category identified by the given category id from the system.
     *
     * @param categoryId The id of the category to be removed.
     * @param callback Called as a feedback for the success/failure of the removal.
     */
    void removeCategory(long categoryId, AsyncCallback<Void> callback);

    /**
     * Returns all available categories in the system.
     *
     * @param callback Called with a list of all the available categories.
     */
    void getCategories(AsyncCallback<List<Category>> callback);


    //================================================ Task Methods ====================================================

    /**
     * Creates a new task in the system and adds it under the specified category.
     *
     * @param task The task to be created.
     * @param categoryId The id of the category to which the task will be added.
     * @param callback Called with the newly created task.
     */
    void createTask(Task task, long categoryId, AsyncCallback<Task> callback);

    /**
     * Updates the given task in the syste. The task will be identified by its id.
     *
     * @param task The task to update.
     * @param callback Called as a feedback for the success/failure of the update.
     */
    void updateTask(Task task, AsyncCallback<Void> callback);

    /**
     * Removes the task identified by the given task id from the system.
     *
     * @param taskId The id of the task to be removed.
     * @param callback Called as a feedback for the success/failure of the removal.
     */
    void removeTask(long taskId, AsyncCallback<Void> callback);

    /**
     * Returns all tasks associated with the specified cateogry.
     *
     * @param categoryId The id of the category of the requested tasks.
     * @param callback Called with a list of all tasks associated with the specified category.
     */
    void getTasks(long categoryId, AsyncCallback<List<Task>> callback);

}
