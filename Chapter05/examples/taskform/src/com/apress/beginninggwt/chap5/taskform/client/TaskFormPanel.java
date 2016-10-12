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

package com.apress.beginninggwt.chap5.taskform.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.Window;

/**
 * @author Uri Boness
 */
public class TaskFormPanel extends VerticalPanel {

	private ListBox priorityField;
	private TextBox titleField;
	private Label titleErrorLabel;
	private TextArea descriptionField;
	private Button submitButton;

	public TaskFormPanel() {
		add(new Label("Title"));
		HorizontalPanel titleRow = new HorizontalPanel();
		titleRow.add(titleField = new TextBox());
		titleRow.add(titleErrorLabel = createErrorLabel());
		add(titleRow);
		
		addGap("10px");
		
		add(new Label("Priority"));
		add(priorityField = new ListBox(false));
		priorityField.setVisibleItemCount(1);
		priorityField.addItem("LOW");
		priorityField.addItem("NORMAL");
		priorityField.addItem("HIGH");
		priorityField.setItemSelected(1, true);
		
		addGap("10px");
		
		add(new Label("Description"));
		add(descriptionField = new TextArea());
		descriptionField.setVisibleLines(10);
		descriptionField.setCharacterWidth(50);
		
		addGap("10px");
		
		add(submitButton = new Button("Add Task"));
		submitButton.addClickListener(new ClickListener() {
			public void onClick(Widget widget) {
				if (validateForm()) {
				TaskInfo task = new TaskInfo();
				task.setTitle(titleField.getText());
				task.setDescription(descriptionField.getText());
				TaskInfo.Priority priority = resolvePriority();
				task.setPriority(priority);
				addTask(task);
				}
			}
		});
	}

	/**
	 * Adds a vertical gap to this vertical panel.
	 */
	protected void addGap(String height) {
		Label row = new Label();
		add(row);
		setCellHeight(row, height);
	}
	
	/**
	 * Resolves and returns the priority as selected by the user in the priority field.
	 */
	protected TaskInfo.Priority resolvePriority() {
		int priorityIndex = priorityField.getSelectedIndex();
		String priorityName = priorityField.getValue(priorityIndex);
		return Enum.valueOf(TaskInfo.Priority.class, priorityName);
	}
	
	/**
	 * Creates and returns a new label to show validation errors.
	 */
	protected Label createErrorLabel() {
		Label errorLabel = new Label();
		errorLabel.addStyleName("errorLabel");
		return errorLabel;
	}
	
	/**
	 * Validates the form, showing any validation errors and returning whether the form is valid or not.
	 */
	protected boolean validateForm() {
		boolean titleIsValid = titleField.getText().length() > 0;
		titleErrorLabel.setText(titleIsValid ? "" : "Required");
		return titleIsValid;
	}

	/**
	 * Called when the form is submitted witha  valid task.
	 */
	protected void addTask(TaskInfo task) {
		Window.alert(task.toString());
	}		
}
