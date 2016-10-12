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

import com.apress.beginninggwt.gwtasks.client.manager.Server;
import com.apress.beginninggwt.gwtasks.client.model.Category;
import com.apress.beginninggwt.gwtasks.client.model.Task;
import com.google.gwt.json.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Roald Bankras
 */
public class JSONDataManager implements DataManager {

    public void getCategories(final AsyncCallback<List<Category>> callback) {
        Server.post("categories", "", new AsyncCallback<String>() {
            public void onFailure(Throwable throwable) {
                callback.onSuccess(new ArrayList<Category>());
            }

            public void onSuccess(String response) {
                List<Category> categories = new ArrayList<Category>();
                JSONValue responseValue = JSONParser.parse(response);
                JSONArray responseArray = responseValue.isArray();
                if (responseArray != null) {
                    for (int i = 0; i < responseArray.size(); i++) {
                        JSONObject responseCategory = responseArray.get(i).isObject();
                        Category category = parseCategory(responseCategory);
                        categories.add(category);
                    }
                }
                callback.onSuccess(categories);
            }
        });
    }

    public void createCategory(final Category category, Long parentCategoryId, final AsyncCallback<Category> callback) {
        JSONObject jsonCategory = new JSONObject();
        jsonCategory.put("name", new JSONString(category.getName()));
        jsonCategory.put("description", new JSONString(category.getDescription()));
        jsonCategory.put("parentid", new JSONNumber(parentCategoryId));
        Server.post("createcategory", jsonCategory.toString(), new AsyncCallback<String>() {
            public void onFailure(Throwable throwable) {
                callback.onFailure(throwable);
            }

            public void onSuccess(String response) {
                JSONValue jsonValue = JSONParser.parse(response);
                JSONObject jsonCategory = jsonValue.isObject();
                if(jsonCategory != null) {
                    Category category = parseCategory(jsonCategory);
                    callback.onSuccess(category);
                }
                callback.onFailure(new IllegalArgumentException("Unable to create category " + category.getName()));
            }
        });
    }

    public void updateCategory(Category category, final AsyncCallback<Void> callback) {
        JSONObject jsonCategory = new JSONObject();
        jsonCategory.put("name", new JSONString(category.getName()));
        jsonCategory.put("description", new JSONString(category.getDescription()));
        Server.post("updatecategory", jsonCategory.toString(), new AsyncCallback<String>() {
            public void onFailure(Throwable throwable) {
                callback.onFailure(throwable);
            }

            public void onSuccess(String response) {
            }
        });
    }

    public void removeCategory(long categoryId, final AsyncCallback<Void> callback) {
        JSONNumber jsonCategoryId = new JSONNumber(categoryId);
        Server.post("removecategory", jsonCategoryId.toString(), new AsyncCallback<String>() {
            public void onFailure(Throwable throwable) {
                callback.onFailure(throwable);
            }

            public void onSuccess(String response) {
            }
        });
    }

    private Category parseCategory(JSONObject cat) {
        Category category = new Category();
        category.setId(((Double) cat.get("id").isNumber().doubleValue()).longValue());
        category.setName(cat.get("name").isString().stringValue());
        category.setDescription(cat.get("description").isString().stringValue());
        JSONArray subCategories = cat.get("subCategories").isArray();
        if (subCategories != null) {
            for (int i = 0; i < subCategories.size(); i++) {
                Category subCategory = parseCategory(subCategories.get(i).isObject());
                category.addChildCategory(subCategory);
            }
        }
        return category;
    }

    public void createTask(final Task task, long categoryId, final AsyncCallback<Task> callback) {
        JSONObject jsonTask = new JSONObject();
        jsonTask.put("name", new JSONString(task.getTitle()));
        jsonTask.put("description", new JSONString(task.getDescription()));
        jsonTask.put("priority", new JSONString(task.getPriority().name()));
        jsonTask.put("categoryid", new JSONNumber(categoryId));
        Server.post("createtask", jsonTask.toString(), new AsyncCallback<String>() {
            public void onFailure(Throwable throwable) {
                callback.onFailure(throwable);
            }

            public void onSuccess(String response) {
                JSONValue jsonValue = JSONParser.parse(response);
                JSONObject jsonTask = jsonValue.isObject();
                if(jsonTask != null) {
                    Task task = parseTask(jsonTask);
                    callback.onSuccess(task);
                }
                callback.onFailure(new IllegalArgumentException("Unable to create task " + task.getTitle()));
            }
        });
    }

    public void updateTask(Task task, final AsyncCallback<Void> callback) {
        JSONObject jsonTask = new JSONObject();
        jsonTask.put("title", new JSONString(task.getTitle()));
        jsonTask.put("description", new JSONString(task.getDescription()));
        jsonTask.put("priority", new JSONString(task.getPriority().name()));
        jsonTask.put("closed", JSONBoolean.getInstance(task.isClosed()));
        Server.post("updatetask", jsonTask.toString(), new AsyncCallback<String>() {
            public void onFailure(Throwable throwable) {
                callback.onFailure(throwable);
            }

            public void onSuccess(String response) {
            }
        });
    }

    public void removeTask(long taskId, final AsyncCallback<Void> callback) {
        JSONNumber jsonTaskId = new JSONNumber(taskId);
        Server.post("removetask", jsonTaskId.toString(), new AsyncCallback<String>() {
            public void onFailure(Throwable throwable) {
                callback.onFailure(throwable);
            }

            public void onSuccess(String response) {
            }
        });
    }

    public void getTasks(long categoryId, final AsyncCallback<List<Task>> callback) {
        JSONNumber jsonCategoryId = new JSONNumber(categoryId);
        Server.post("tasks", jsonCategoryId.toString(), new AsyncCallback<String>() {
            public void onFailure(Throwable throwable) {
                callback.onSuccess(new ArrayList<Task>());
            }

            public void onSuccess(String response) {
                List<Task> tasks = new ArrayList<Task>();
                JSONValue responseValue = JSONParser.parse(response);
                JSONArray responseArray = responseValue.isArray();
                if (responseArray != null) {
                    for (int i = 0; i < responseArray.size(); i++) {
                        JSONObject responseTask = responseArray.get(i).isObject();
                        Task task = parseTask(responseTask);
                        tasks.add(task);
                    }
                }
                callback.onSuccess(tasks);
            }
        });
    }

    private Task parseTask(JSONObject jsonTask) {
        Task task = new Task();
        task.setId(((Double) jsonTask.get("id").isNumber().doubleValue()).longValue());
        task.setTitle(jsonTask.get("title").isString().stringValue());
        task.setDescription(jsonTask.get("description").isString().stringValue());
        String priorityName = jsonTask.get("priority").isString().stringValue();
        if(Task.Priority.HIGH.name().equals(priorityName)) {
            task.setPriority(Task.Priority.HIGH);
        } else if(Task.Priority.LOW.name().equals(priorityName)) {
            task.setPriority(Task.Priority.LOW);
        } else {
            task.setPriority(Task.Priority.NORMAL);
        }
        task.setClosed(jsonTask.get("closed").isBoolean().booleanValue());
        return task;
    }
}
