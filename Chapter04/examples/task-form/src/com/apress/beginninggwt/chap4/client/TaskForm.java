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

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.Window;

/**
 * The TaskForm application.
 *
 * @author Uri Boness
 */
public class TaskForm implements EntryPoint {

	private TextBox titleField;
	private Label titleErrorLabel;
	private TextArea descriptionField;
	private Label descriptionErrorLabel;
	private ListBox priorityField;

	public void onModuleLoad() {
		titleField= new TextBox();
		titleErrorLabel = createErrorLabel();
		RootPanel.get("titleLabel").add(new Label("Title"));
		RootPanel.get("titleErrorLabel").add(titleErrorLabel);
		RootPanel.get("titleField").add(titleField);

		priorityField = new ListBox(false);
		priorityField.setVisibleItemCount(1);
		priorityField.addItem("LOW");
		priorityField.addItem("NORMAL");
		priorityField.addItem("HIGH");
		priorityField.setItemSelected(1, true);
		RootPanel.get("priorityLabel").add(new Label("Priority"));
		RootPanel.get("priorityField").add(priorityField);
		
		descriptionField = new TextArea();
		descriptionField = new TextArea();
		descriptionField.setVisibleLines(10);
		descriptionField.setCharacterWidth(50);
		descriptionErrorLabel = createErrorLabel();
		descriptionErrorLabel = createErrorLabel();
		RootPanel.get("descriptionLabel").add(new Label("Description"));
		RootPanel.get("descriptionField").add(descriptionField);
		RootPanel.get("descriptionErrorLabel").add(descriptionErrorLabel);
		
		Button submitButton = new Button("Add Task");
		RootPanel.get("submitButton").add(submitButton);
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
	
	protected TaskInfo.Priority resolvePriority() {
		int priorityIndex = priorityField.getSelectedIndex();
		String priorityName = priorityField.getValue(priorityIndex);
		return Enum.valueOf(TaskInfo.Priority.class, priorityName);
	}
	
	protected Label createErrorLabel() {
		Label errorLabel = new Label();
		errorLabel.addStyleName("errorLabel");
		return errorLabel;
	}
	
	protected boolean validateForm() {
		boolean titleIsValid = titleField.getText().length() > 0;
		titleErrorLabel.setText(titleIsValid ? "" : "Required");
		return titleIsValid;
	}

	protected void addTask(TaskInfo task) {
		Window.alert(task.toString());
	}
}
