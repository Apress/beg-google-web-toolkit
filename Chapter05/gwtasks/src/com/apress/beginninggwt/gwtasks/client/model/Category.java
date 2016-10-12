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

package com.apress.beginninggwt.gwtasks.client.model;

import java.util.List;
import java.util.ArrayList;

/**
 * A category of tasks. A Category may contain sub-categories.
 *
 * @author Uri Boness
 */
public class Category {

	private Long id;
	private String name;
	private String description;
	private List<Category> children;

	/**
	 * Default empty constructor.
	 */
	public Category() {
		this(null, null, null);
	}
	
	/**
	 * Constructs  a new Cateogry with given id and name. The description of this task will be empty by default.
	 *
	 * @param id The id of the category.
	 * @param name The name of the cateogry.
	 */
	public Category(Long id, String name) {
		this(id, name, "");
	}
	
	/**
	 * Constructs  a new Cateogry with given id, name and description.
	 *
	 * @param id The id of the category.
	 * @param name The name of the cateogry.
	 * @param description The description of the category.
	 */
	public Category(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
		children = new ArrayList<Category>();
	}

    /**
     * Copy constructor.
     *
     * @param category The category based on which this category will be created and initialized.
     */
    public Category(Category category) {
        this(category.getId(), category.getName(), category.getDescription());
        for (Category child : category.getChildren()) {
            addChildCategory(new Category(child));
        }
    }
	
	/**
	 * Returns the id of this category.
	 *
	 * @return The id of this category.
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Sets the id of this category.
	 *
	 * @param id The id of this category.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Returns the name of this category.
	 *
	 * @return The name of this category.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of this category.
	 *
	 * @param name The name of this category.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the description of this category.
	 *
	 * @return The description of this category.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description of this category.
	 *
	 * @param description The description of this category.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Returns the child categories (sub-categories) of this category.
	 *
	 * @return The child categories (sub-categories) of this category.
	 */
	public List<Category> getChildren() {
		return children;
	}
	
	/**
	 * Adds the given category as a child of this category.
	 *
	 * @param category The new child category.
	 */
	public void addChildCategory(Category category) {
		children.add(category);
	}
	
	public String toString() {
		return "category#" + id;
	}
}
