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

package com.apress.beginninggwt.gwtasks.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.*;

import java.util.*;

/**
 * The GWTasks application
 *
 * @author Uri Boness
 */
public class GWTasks implements EntryPoint {

	private Widget selectedCategoryRow;
	private Category selectedCategory;
	private VerticalPanel tasksWidget;
	
	public void onModuleLoad() {
		DockPanel mainPanel = new DockPanel();
		mainPanel.setBorderWidth(5);
		mainPanel.setSize("100%", "100%");
		mainPanel.setVerticalAlignment(HasAlignment.ALIGN_MIDDLE);
		mainPanel.setHorizontalAlignment(HasAlignment.ALIGN_CENTER);
		Widget header = createHeaderWidget();
		mainPanel.add(header, DockPanel.NORTH);
		mainPanel.setCellHeight(header, "30px");
		Widget footer = createFooterWidget();
		mainPanel.add(footer, DockPanel.SOUTH);
		mainPanel.setCellHeight(footer, "25px");
		
		HorizontalSplitPanel categoriesAndTasks = new HorizontalSplitPanel();
		categoriesAndTasks.setSplitPosition("150px");
		Widget categories = createCategoriesWidget();
		categoriesAndTasks.setLeftWidget(categories);
		Widget tasks = createTasksWidget();
		categoriesAndTasks.setRightWidget(tasks);
		mainPanel.add(categoriesAndTasks, DockPanel.CENTER);
		RootPanel.get().add(mainPanel);
		
		/** uncomment for the non-split panel layout
		Widget categories = createCategoriesWidget();
		mainPanel.add(categories, DockPanel.WEST);
		mainPanel.setCellWidth(categories, "150px");
		mainPanel.setCellVerticalAlignment(categories, DockPanel.ALIGN_TOP);
		mainPanel.setCellHorizontalAlignment(categories, DockPanel.ALIGN_LEFT);
		Widget tasks = createTasksWidget();
		mainPanel.add(tasks, DockPanel.EAST);
		mainPanel.setCellVerticalAlignment(tasks, DockPanel.ALIGN_TOP);
		mainPanel.setCellHorizontalAlignment(tasks, DockPanel.ALIGN_LEFT);
		RootPanel.get().add(mainPanel);
		**/
	}
	
	/**
	 * Creates the header part of the layout.
	 */
	protected Widget createHeaderWidget() {
		return new Label("GWTasks");
	}
	
	/**
	 * Creates the footer part of the layout.
	 */
	protected Widget createFooterWidget() {
		return new HTML("Copyright &copy; 2008");
	}
	
	/**
	 * Creates the widget that will display the list of categories on the left side of the screen
	 */
	protected Widget createCategoriesWidget() {
		VerticalPanel categoryList = new VerticalPanel();
		categoryList.setWidth("100%");
		List<Category> categories = getAllCategories();
		for (final Category category : categories) {
			Widget categoryRow = createCategoryRow(category);
			categoryList.add(categoryRow);
		}
		return categoryList;
	}
	
	/**
	 * Creates the widget that will display a category in the categories list
	 */
	protected Widget createCategoryRow(final Category category) {
		final Label row = new Label(category.getName());
		row.setWordWrap(false);
		row.setStyleName("categoryRow");
		row.addClickListener(new ClickListener() {
			public void onClick(Widget widget) {
				if (row.equals(selectedCategoryRow)) {
					return; // do nothing as it is already selected
				}
				markSelected(selectedCategoryRow, false);
				markSelected(row, true);
				selectedCategoryRow = row;
				selectedCategory = category;
				updateTasksList();
			}
		});
		return row;
	}
	
	/**
	 * Marks the given category row as un/selected
	 */
	protected void markSelected(Widget categoryRow, boolean selected) {
		if (categoryRow == null) {
			return;
		}
		if (selected) {
			categoryRow.addStyleName("selectedCategory");
			categoryRow.removeStyleName("unselectedCategory");
		} else {
			categoryRow.addStyleName("unselectedCategory");
			categoryRow.removeStyleName("selectedCategory");
		}
	}
	
	/**
	 * Updates the tasks shown on the right side of the screen to fit the currently selected category.
	 */
	public void updateTasksList() {
		List<Task> tasks = getTasksForSelectedCategory();
		tasksWidget.clear();
		for (Task task : tasks) {
			Widget taskRow = createTaskRow(task);
			tasksWidget.add(taskRow);
		}
	}
	
	/**
	 * Creates the widget that will display the list of tasks on the right side of the screen
	 */
	public Widget createTasksWidget() {
		tasksWidget = new VerticalPanel();
		tasksWidget.setWidth("100%");
		return tasksWidget;
	}

	/**
	 * Creates the widget that display a task in the tasks list. Currently this widget only shows
	 * the title of the task and a checkbox next to it (so the user will be able to mark it as un/done).
	 */
	public Widget createTaskRow(Task task) {
		HorizontalPanel row = new HorizontalPanel();
		CheckBox checkbox = new CheckBox();
		row.add(checkbox);
		row.add(new Label(task.getTitle()));
		return row;
	}
	
	/**
	 * Returns a dummy task list for the currently selected category.
	 */
	public List<Task> getTasksForSelectedCategory() {
		List<Task> tasks = new ArrayList<Task>();
		if (selectedCategory == null) {
			return tasks;
		}
		if (selectedCategory.getId() == 1L) {
			tasks.add(new Task(1L, "Bread", "1xwhite and 2xdark"));
			tasks.add(new Task(2L, "vegetables", "Tomatoes, Cucumbers and Lettuce"));
			tasks.add(new Task(3L, "Fruits", "Apples, Pears, Melon and Mango"));
			tasks.add(new Task(4L, "Milk", "2xnormal and 1xsoya"));
			return tasks;
		}
		if (selectedCategory.getId() == 2L) {
			tasks.add(new Task(5L, "Contact project manager", "Discuss the status of the project"));
			tasks.add(new Task(6L, "Email HR manager", "Ask for vacation on December"));
			tasks.add(new Task(7L, "Setup meeting with client", "Discuss the progress of the project"));
			return tasks;
		}
		
		// else id = 3L
		tasks.add(new Task(8L, "Vacuum clean the house", Task.Priority.LOW));
		tasks.add(new Task(9L, "Pick up the kids from school", "At 15:00"));
		tasks.add(new Task(10L, "Arrange babysitter", "For the upcoming weekend"));
		return tasks;
	}

	/**
	 * Returns a dummy list of all available categories.
	 */
	protected List<Category> getAllCategories() {
		List<Category> categories = new ArrayList<Category>();
		categories.add(new Category(1L, "Shopping List", ""));
		categories.add(new Category(2L, "Work", ""));
		categories.add(new Category(3L, "Home", ""));
		return categories;
	}
}
