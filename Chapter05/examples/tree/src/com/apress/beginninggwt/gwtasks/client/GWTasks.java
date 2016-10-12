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

	private Category selectedCategory;
	private FlexTable tasksWidget;
	private Task selectedTask;
	private int selectedTaskRow = -1;
	
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
		mainPanel.setCellHorizontalAlignment(categoriesAndTasks, DockPanel.ALIGN_LEFT);
		RootPanel.get().add(mainPanel);
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
		return new Label();
	}
	
	/**
	 * Creates the widget that will display the list of categories on the left side of the screen
	 */
	protected Widget createCategoriesWidget() {
		Tree categoryTree = new Tree();
		categoryTree.addTreeListener(new TreeListener() {
			public void onTreeItemSelected(TreeItem item) {
				selectedCategory = ((CategoryTreeItem)item).getCategory();
				updateTasksList();
			}
			public void onTreeItemStateChanged(TreeItem item) {
			}
		});
		List<Category> categories = getAllCategories();
		for (final Category category : categories) {
			CategoryTreeItem item = createTreeItem(category);
			categoryTree.addItem(item);
		}
		return categoryTree;
	}
	
	/**
	 * Creates a CategoryTreeItem for the given category.
	 */
	protected CategoryTreeItem createTreeItem(Category category) {
		CategoryTreeItem item = new CategoryTreeItem(category);
		for (Category child : category.getChildren()) {
			item.addItem(createTreeItem(child));
		}
		return item;
	}
	
	/**
	 * Updates the tasks shown on the right side of the screen to fit the currently selected category.
	 */
	public void updateTasksList() {
		List<Task> tasks = getTasksForSelectedCategory();
		while (tasksWidget.getRowCount() > 1) {
			tasksWidget.removeRow(tasksWidget.getRowCount()-1);
		}
		for (Task task : tasks) {
			addTask(task);
		}
	}
	
	/**
	 * Adds the given task to the task list
	 */
	public void addTask(final Task task) {
		final int row = tasksWidget.getRowCount();
		tasksWidget.setWidget(row, 0, new CheckBox());
		String priorityName = task.getPriority().name();
		Label priorityLabel = new Label(priorityName.substring(0, 1));
		Label titleLabel = new Label(task.getTitle());
		titleLabel.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				handleTaskRowClicked(row, task);
			}
		});
		priorityLabel.setStyleName("PriorityLabel-" + priorityName.toLowerCase());
		tasksWidget.setWidget(row, 1, priorityLabel);
		tasksWidget.setWidget(row, 2, titleLabel);
	}
	
	/**
	 * Called when a task is clicked.
	 *
	 * @param row The index of the clicked task in the task list.
	 * @param task The clicked task.
	 */
	protected void handleTaskRowClicked(int row, Task task) {
		if (selectedTaskRow == row) {
			selectedTaskRow = -1;
			selectedTask = null;
			tasksWidget .getRowFormatter().removeStyleName(row, "TaskRow-selected");
		} else {
			if (selectedTaskRow != -1) {
				tasksWidget .getRowFormatter()
				.removeStyleName(selectedTaskRow, "TaskRow-selected");
			}
			selectedTaskRow = row;
			selectedTask = task;
			tasksWidget .getRowFormatter().addStyleName(row, "TaskRow-selected");
		}
	}
	
	/**
	 * Creates the widget that will display the list of tasks on the right side of the screen
	 */
	public Widget createTasksWidget() {
		tasksWidget = new FlexTable();
		tasksWidget.getColumnFormatter().setWidth(0, "20px");
		tasksWidget.getColumnFormatter().setWidth(1, "20px");
		tasksWidget.getColumnFormatter().setWidth(2, "100%");
		Label checkHeaderLabel = new Label();
		checkHeaderLabel.setWidth("20px");
		checkHeaderLabel.setHorizontalAlignment(Label.ALIGN_CENTER);
		tasksWidget.setWidget(0, 0, checkHeaderLabel);
		Label priorityHeaderLabel = new Label("!");
		priorityHeaderLabel.setWidth("20px");
		priorityHeaderLabel.setHorizontalAlignment(Label.ALIGN_CENTER);
		tasksWidget.setWidget(0, 1, priorityHeaderLabel);
		Label titleHeaderLabel = new Label("Title");
		titleHeaderLabel.setWidth("100%");
		tasksWidget.setWidget(0, 2, titleHeaderLabel);
		tasksWidget.getRowFormatter().setStyleName(0, "TableHeader");
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
		Category work = new Category(1L, "Work", "Things at work");
		work.addChildCategory(new Category(2L, "Calls", "Make phone calls"));
		work.addChildCategory(new Category(3L, "Meetings", "Meetings to attend"));
		categories.add(work);
		Category home = new Category(4L, "Home", "Things at home");
		home.addChildCategory(new Category(5L, "Shoppings", "Things I need to buy"));
		home.addChildCategory(new Category(6L, "Bills", "Bills I need to sort"));
		categories.add(home);
		categories.add(new Category(3L, "Others", "Other things I need to do"));
		return categories;
	}
	
	
	protected class CategoryTreeItem extends TreeItem {
		
		public CategoryTreeItem(Category category) {
			super(category.getName());
			setTitle(category.getDescription());
			setUserObject(category);
		}
		
		public Category getCategory() {
			return (Category) getUserObject();
		}
	}
	
}
