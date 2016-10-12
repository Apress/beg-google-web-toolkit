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

package com.apress.beginninggwt.gwtasks.server.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.apress.beginninggwt.gwtasks.client.manager.security.Authentication;
import com.apress.beginninggwt.gwtasks.client.manager.security.AuthenticationException;
import com.apress.beginninggwt.gwtasks.client.model.Account;
import com.apress.beginninggwt.gwtasks.client.model.Category;
import com.apress.beginninggwt.gwtasks.client.model.Task;
import com.apress.beginninggwt.gwtasks.client.service.RemoteTaskService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.core.client.GWT;

/**
 * @author Roald Bankras
 */
public class RemoteTaskServiceImpl extends RemoteServiceServlet implements RemoteTaskService {

    private final CategoryNameComparator CATEGORY_NAME_COMPARATOR = new CategoryNameComparator();
    private final TaskComparator TASK_COMPARATOR = new TaskComparator();

    private static long categoryIdCounter = 0;
    private static Map<Long, CategoryWithParent> categoryById = new HashMap<Long, CategoryWithParent>();

    private static long taskIdCounter = 0;
    private static Map<Long, TaskWithCategory> taskWithCategoryById = new HashMap<Long, TaskWithCategory>();
    private static Map<Long, List<Task>> tasksByCategoryId = new HashMap<Long, List<Task>>();

    private final static Map<String, Account> accountByUsername = new HashMap<String, Account>();
    private static long accountIdCounter = 0;

    /**
     * {@inheritDoc}
     */
    public Category createCategory(Category category, Long parentCategoryId) {
        Long id = ++categoryIdCounter;
        Category createdCategory = new Category(category);
        createdCategory.setId(id);
        Category parent = resolveParent(parentCategoryId);
        if (parent != null) {
            parent.addChildCategory(createdCategory);
        }
        CategoryWithParent cwp = new CategoryWithParent(createdCategory, parent);
        categoryById.put(id, cwp);
        tasksByCategoryId.put(id, new ArrayList<Task>());
        return createdCategory;
    }

    protected Category resolveParent(Long parentCtegoryId) {
        if (parentCtegoryId == null) {
            return null;
        }
        return categoryById.get(parentCtegoryId).getCategory();
    }

    /**
     * {@inheritDoc}
     */
    public void updateCategory(Category category) {
        if (!categoryById.containsKey(category.getId())) {
            throw new IllegalArgumentException("Category #" + category.getId() + " does not exist");
        }
        Category existingCategory = categoryById.get(category.getId()).getCategory();
        updateCategory(existingCategory, category);
    }

    /**
     * {@inheritDoc}
     */
    public void removeCategory(long categoryId) {
        CategoryWithParent cwp = categoryById.get(categoryId);
        if (cwp != null) {
            removeCategory(cwp);
        }
    }

    protected void removeCategory(CategoryWithParent cwp) {
        for (Category child : cwp.getCategory().getChildren()) {
            CategoryWithParent childCwp = categoryById.get(child.getId());
            removeCategory(childCwp);
        }
        categoryById.remove(cwp.getCategory().getId());
        tasksByCategoryId.remove(cwp.getCategory().getId());
    }

    /**
     * {@inheritDoc}
     */
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<Category>();
        for (CategoryWithParent cwp : categoryById.values()) {
            if (cwp.getParent() == null) {
                categories.add(cwp.getCategory());
            }
        }
        Collections.sort(categories, CATEGORY_NAME_COMPARATOR);
        return categories;
    }

    /**
     * {@inheritDoc}
     */
    public Task createTask(Task task, long categoryId) {
        CategoryWithParent cwp = categoryById.get(categoryId);
        if (cwp == null) {
            throw new IllegalArgumentException("Cagtegory #" + categoryId + " does not exist");
        }
        Long id = ++taskIdCounter;
        Task createdTask = new Task(task);
        createdTask.setId(id);
        TaskWithCategory twc = new TaskWithCategory(createdTask, cwp.getCategory());
        taskWithCategoryById.put(id, twc);
        List<Task> tasks = tasksByCategoryId.get(cwp.getCategory().getId());
        tasks.add(createdTask);
        return createdTask;
    }

    /**
     * {@inheritDoc}
     */
    public void updateTask(Task task) {
        TaskWithCategory twc = taskWithCategoryById.get(task.getId());
        if (twc == null) {
            throw new IllegalArgumentException("Task #" + task.getId() + " does not exsit");
        }
        twc.updateTask(task);
    }

    /**
     * {@inheritDoc}
     */
    public void removeTask(long taskId) {
        TaskWithCategory twc = taskWithCategoryById.remove(taskId);
        Category category = twc.getCategory();
        List<Task> tasks = tasksByCategoryId.get(category.getId());
        tasks.remove(twc.getTask());
    }

    /**
     * {@inheritDoc}
     */
    public List<Task> getTasks(long categoryId) {
        List<Task> tasks = tasksByCategoryId.get(categoryId);
        if (tasks == null) {
            throw new IllegalArgumentException("Category #" + categoryId + " does not exist");
        }
        List<Task> result = new ArrayList<Task>(tasks);
        Collections.sort(result, TASK_COMPARATOR);
        return result;
    }

    protected void updateCategory(Category existing, Category category) {
        existing.setName(category.getName());
        existing.setDescription(category.getDescription());
    }

    public Account createAccount(Account account) {
        Account newAccount = new Account(account);
        newAccount.setId(++accountIdCounter);
        accountByUsername.put(newAccount.getUsername(), newAccount);
        return newAccount;
    }

    public Account login(Authentication authentication) throws AuthenticationException {
        if (authentication == Authentication.ANONYMOUS) {
            throw new IllegalArgumentException("Cannot login anonymous authentication");
        }
        Account account = accountByUsername.get(authentication.getUsername());
        if (account == null || !authentication.getPassword().equals(account.getPassword())) {
            throw new AuthenticationException("Could not authenticate '" + authentication.getUsername() + "'");
        } else {
            return account;
        }
    }
//============================================== Inner Classes =====================================================

    /**
     * Compares categories by their names.
     */
    protected final static class CategoryNameComparator implements Comparator<Category> {
        public int compare(Category c1, Category c2) {
            return c1.getName().compareTo(c2.getName());
        }
    }

    /**
     * Compares tasks by their priorities and titles.
     */
    protected final static class TaskComparator implements Comparator<Task> {
        public int compare(Task t1, Task t2) {
            int result = t1.getPriority().compareTo(t2.getPriority());
            if (result == 0) {
                result = t1.getTitle().compareTo(t2.getTitle());
            }
            return result;
        }
    }

    /**
     * Holds a category with its associated task.
     */
    protected final static class TaskWithCategory {
        private Task task;
        private Category category;

        public TaskWithCategory(Task task, Category category) {
            this.task = task;
            this.category = category;
        }

        public Task getTask() {
            return task;
        }

        public void setTask(Task task) {
            this.task = task;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public void updateTask(Task task) {
            this.task.setTitle(task.getTitle());
            this.task.setDescription(task.getDescription());
            this.task.setPriority(task.getPriority());
            this.task.setClosed(task.isClosed());
        }

        public int hashCode() {
            return 31 * task.hashCode() + category.hashCode();
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TaskWithCategory)) return false;

            TaskWithCategory that = (TaskWithCategory) o;

            if (!category.equals(that.category)) return false;
            if (!task.equals(that.task)) return false;

            return true;
        }
    }

    protected class CategoryWithParent {

        private Category category;
        private Category parent;

        public CategoryWithParent(Category category, Category parent) {
            this.category = category;
            this.parent = parent;
        }

        public Category getCategory() {
            return category;
        }

        public Category getParent() {
            return parent;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CategoryWithParent)) return false;

            CategoryWithParent that = (CategoryWithParent) o;

            if (category != null ? !category.equals(that.category) : that.category != null) return false;
            if (parent != null ? !parent.equals(that.parent) : that.parent != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = category != null ? category.hashCode() : 0;
            result = 31 * result + (parent != null ? parent.hashCode() : 0);
            return result;
        }
    }
}
