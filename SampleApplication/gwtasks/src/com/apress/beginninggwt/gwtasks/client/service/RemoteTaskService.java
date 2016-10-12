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

package com.apress.beginninggwt.gwtasks.client.service;

import com.apress.beginninggwt.gwtasks.client.manager.security.Authentication;
import com.apress.beginninggwt.gwtasks.client.manager.security.AuthenticationException;
import com.apress.beginninggwt.gwtasks.client.model.Account;
import com.apress.beginninggwt.gwtasks.client.model.Category;
import com.apress.beginninggwt.gwtasks.client.model.Task;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import java.util.List;

/**
 * @author Roald Bankras
 */
public interface RemoteTaskService extends RemoteService {

    //============================================== Category Methods ==================================================

    /**
     * Creates a new category in the system and adds it as a child to the category identified by the given cateogry id.
     * If the given category id is <code>null</code> the category will be treated as a top-level category.
     *
     * @param category The category to create.
     * @param parentCategoryId The id of the parent category or <code>null</code> if the created category is a top
     *        level one.
     * @return the newly created category.
     */
    Category createCategory(Category category, Long parentCategoryId);

    /**
     * Updates the given category in the system. The category will be identified by its id.
     *
     * @param category The category to be updated.
     */
    void updateCategory(Category category);

    /**
     * Remvoes the category identified by the given category id from the system.
     *
     * @param categoryId The id of the category to be removed.
     */
    void removeCategory(long categoryId);

    /**
     * Returns all available categories in the system.
     *
     * @return a list of all the available categories.
     */
    List<Category> getCategories();


    //================================================ Task Methods ====================================================

    /**
     * Creates a new task in the system and adds it under the specified category.
     *
     * @param task The task to be created.
     * @param categoryId The id of the category to which the task will be added.
     * @return the newly created task.
     */
    Task createTask(Task task, long categoryId);

    /**
     * Updates the given task in the syste. The task will be identified by its id.
     *
     * @param task The task to update.
     */
    void updateTask(Task task);

    /**
     * Removes the task identified by the given task id from the system.
     *
     * @param taskId The id of the task to be removed.
     */
    void removeTask(long taskId);

    /**
     * Returns all tasks associated with the specified cateogry.
     *
     * @param categoryId The id of the category of the requested tasks.
     * @return a list of all tasks associated with the specified category.
     */
    List<Task> getTasks(long categoryId);

    
    //================================================ Account Methods =================================================

    /**
     * Creates a new account based on the given account.
     *
     * @param account The account to create.
     * @return the newly created account.
     */
    Account createAccount(Account account);

    /**
     * Logs in to the application based on the given authentication. On successful login the authentication will be
     * registered with this manager as the currently logged in user.
     *
     * @param authentication Holds the authentication information for the login.
     * @return the account associated witht he given authentication.
     * @throws AuthenticationException on authentication failure
     */
    Account login(Authentication authentication) throws AuthenticationException;


    public static final class Locator {
        private static RemoteTaskServiceAsync service;

        public static RemoteTaskServiceAsync getInstance() {
            if(service == null) {
                service = GWT.create(RemoteTaskService.class);
                ((ServiceDefTarget)service).setServiceEntryPoint(GWT.getModuleBaseURL() + "taskService");
            }
            return service;
        }
    }
}
