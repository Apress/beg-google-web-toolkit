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

package com.apress.beginninggwt.chap4.client;

/**
 * @author Uri Boness
 */
public class TaskInfo {

	public enum Priority { LOW, NORMAL, HIGH }
	
	// a required short descriptive title
	private String title;
	
	// option (may be null) extra text describing the task in details
	private String description;
	
	// defines the priority of the task
	private Priority priority = Priority.NORMAL;
	
	/**
	 * A empty default constructor.
	 */
	public TaskInfo() {
		this(null);
	}
	
	/**
	 * Constructs a new TaskInfo with a given task title.  By default the task will have an empty description
	 * and a <code>NORMAL</code> priority.
	 *
	 * @param title The title of the task.
	 */
	public TaskInfo(String title) {
		this(title, "");
	}
	
	/**
	 * Constructs a new TaskInfo with given title and priority. By default the task will have an empty description.
	 *
	 * @param title The title of the task.
	 * @param priority The priority of the task.
	 */
	public TaskInfo(String title, Priority priority) {
		this(title, "", priority);
	}
	
	/**
	 * Constructs a new TaskInfo with given title and description.
	 *
	 * @param title The title of the task
	 * @param description The description of the task.
	 */
	public TaskInfo(String title, String description) {
		this(title, description, Priority.NORMAL);
	}
	
	/**
	 * Constructs a new TaskInfo with all task information.
	 *
	 * @param title The title of the task.
	 * @param description The description of the task.
	 * @param priority The priority of the task.
	 */
	public TaskInfo(String title, String description, Priority priority) {
		this.title = title;
		this.description = description;
		this.priority = priority;
	}
	
	/**
	 * Returns the title of the task.
	 *
	 * @return The title of the task.
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Sets the title of the task.
	 *
	 * @param title The title of the task.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Returns the description of the task.
	 *
	 * @return The description of the task.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description of the task.
	 *
	 * @param description The description of the task.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Returns the priority of the task.
	 *
	 * @return The priority of the task.
	 */
	public Priority getPriority() {
		return priority;
	}
	
	/**
	 * Sets the priority of the task.
	 *
	 * @param priority The priority of the task.
	 */
	public void setPriority(Priority priority) {
		this.priority = priority;;
	}
	
	/**
	 * {@inheritDocs}
	 */
	public String toString() {
		return new StringBuilder("TaskInfo[")
			.append("title:").append(title).append(", ")
			.append("description:").append(description).append(", ")
			.append("priority:").append(priority.name())
			.append("]")
			.toString();
	}		
}
