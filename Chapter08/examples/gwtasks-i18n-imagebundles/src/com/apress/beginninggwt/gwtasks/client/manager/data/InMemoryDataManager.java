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

import java.util.*;

/**
 * A default in-memory implementation of the {@link DataManager}.
 *
 * @author Uri Boness
 */
public class InMemoryDataManager implements DataManager {

    private final CategoryNameComparator CATEGORY_NAME_COMPARATOR = new CategoryNameComparator();
    private final TaskComparator TASK_COMPARATOR = new TaskComparator();

    private static long categoryIdCounter = 0;
    private static Map<Long, Category> categoryById = new HashMap<Long, Category>();

    private static long taskIdCounter = 0;
    private static Map<Long, TaskWithCategory> taskWithCategoryById = new HashMap<Long, TaskWithCategory>();
    private static Map<Long, List<Task>> tasksByCategoryId = new HashMap<Long, List<Task>>();

    /**
     * {@inheritDoc}
     */
    public void createCategory(Category category, Long parentCategoryId, AsyncCallback<Category> callback) {
        Long id = ++categoryIdCounter;
        Category createdCategory = new Category(category);
        createdCategory.setId(id);
        categoryById.put(id, createdCategory);
        tasksByCategoryId.put(id, new ArrayList<Task>());
        if (parentCategoryId != null) {
            Category parent = categoryById.get(parentCategoryId);
            if (parent == null) {
                throw new IllegalArgumentException("Parent category #" + parentCategoryId + " does not exist");
            }
            parent.addChildCategory(createdCategory);
        }
        callback.onSuccess(createdCategory);
    }

    /**
     * {@inheritDoc}
     */
    public void updateCategory(Category category, AsyncCallback<Void> callback) {
        if (!categoryById.containsKey(category.getId())) {
            callback.onFailure(new IllegalArgumentException("Category #" + category.getId() + " does not exist"));
        }
        Category existingCategory = categoryById.get(category.getId());
        updateCategory(existingCategory, category);
        callback.onSuccess(null);
    }

    /**
     * {@inheritDoc}
     */
    public void removeCategory(long categoryId, AsyncCallback<Void> callback) {
        Category category = categoryById.get(categoryId);
        if (category != null) {
            removeCategory(category);
        }
        callback.onSuccess(null);
    }

    protected void removeCategory(Category category) {
        for (Category child : category.getChildren()) {
            removeCategory(child);
        }
        categoryById.remove(category.getId());
        tasksByCategoryId.remove(category.getId());
    }

    /**
     * {@inheritDoc}
     */
    public void getCategories(AsyncCallback<List<Category>> callback) {
        List<Category> categories = new ArrayList<Category>(categoryById.values());
        Collections.sort(categories, CATEGORY_NAME_COMPARATOR);
        callback.onSuccess(categories);
    }

    /**
     * {@inheritDoc}
     */
    public void createTask(Task task, long categoryId, AsyncCallback<Task> callback) {
        Category category = categoryById.get(categoryId);
        if (category == null) {
            callback.onFailure(new IllegalArgumentException("Cagtegory #" + categoryId + " does not exist"));
            return;
        }
        Long id = ++taskIdCounter;
        Task createdTask = new Task(task);
        createdTask.setId(id);
        TaskWithCategory twc = new TaskWithCategory(createdTask, category);
        taskWithCategoryById.put(id, twc);
        List<Task> tasks = tasksByCategoryId.get(category.getId());
        tasks.add(createdTask);
        callback.onSuccess(createdTask);
    }

    /**
     * {@inheritDoc}
     */
    public void updateTask(Task task, AsyncCallback<Void> callback) {
        TaskWithCategory twc = taskWithCategoryById.get(task.getId());
        if (twc == null) {
            callback.onFailure(new IllegalArgumentException("Task #" + task.getId() + " does not exsit"));
            return;
        }
        twc.updateTask(task);
        callback.onSuccess(null);
    }

    /**
     * {@inheritDoc}
     */
    public void removeTask(long taskId, AsyncCallback<Void> callback) {
        TaskWithCategory twc = taskWithCategoryById.remove(taskId);
        Category category = twc.getCategory();
        List<Task> tasks = tasksByCategoryId.get(category.getId());
        tasks.remove(twc.getTask());
        callback.onSuccess(null);
    }

    /**
     * {@inheritDoc}
     */
    public void getTasks(long categoryId, AsyncCallback<List<Task>> callback) {
        List<Task> tasks = tasksByCategoryId.get(categoryId);
        if (tasks == null) {
            callback.onFailure(new IllegalArgumentException("Category #" + categoryId + " does not exist"));
            return;
        }
        List<Task> result = new ArrayList<Task>(tasks);
        Collections.sort(result, TASK_COMPARATOR);
        callback.onSuccess(result);
    }

    protected void updateCategory(Category existing, Category category) {
        existing.setName(category.getName());
        existing.setDescription(category.getDescription());
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

}
