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

import java.io.Serializable;

/**
 * A Task
 *
 * @author Uri Boness
 */
public class Task implements Serializable {

	private Long id;
	private String title;
	private String description;
	
	/**
	 * Returns the id of this task.
	 *
	 * @return The id of this task.
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Sets the id of this task.
	 *
	 * @param id The id of this task.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Returns the title of this task.
	 *
	 * @return The title of this task.
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Sets the title of this task.
	 *
	 * @param title The title of this task.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Returns the description of this task.
	 *
	 * @return The description of this task.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description of this task.
	 *
	 * @param description The description of this task.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
