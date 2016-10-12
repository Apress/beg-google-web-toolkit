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

package com.apress.beginninggwt.gwtasks.client.ui.mainpane.taskpane;

import com.google.gwt.user.client.ui.*;
import com.apress.beginninggwt.gwtasks.client.model.Task;

/**
 * @author Uri Boness
 */
public class TaskFormDialogBox extends DialogBox {

	private final static String ERROR_IMAGE_URL = "image/field-error.gif";

	private final TextBox titleField;
    private final Image titleErrorImage;
	private final ListBox priorityField;
    private final TextArea descriptionField;
    private final Button submitButton;
    private final Button cancelButton;

    private final Task task;
    private final TaskPane taskPane;

    public TaskFormDialogBox(TaskPane taskPane) {
        this(taskPane, new Task(), false);
    }

    public TaskFormDialogBox(TaskPane taskPane, Task task) {
        this(taskPane, task, true);
    }

    private TaskFormDialogBox(TaskPane taskPane, Task task, boolean editMode) {
        super(false, true);
        setText("Task Form");
        this.task = task;
		this.taskPane = taskPane;
		
        VerticalPanel main = new VerticalPanel();
        main.add(new Label("Title"));
        addGap(main, "3px");

        titleField = new TextBox();
        titleErrorImage = new Image(ERROR_IMAGE_URL);
        titleErrorImage.setVisible(false);
        HorizontalPanel titleFieldRow = new HorizontalPanel();
        titleFieldRow.add(titleField);
        titleFieldRow.setCellWidth(titleField, "60%");
        titleFieldRow.add(titleErrorImage);
        main.add(titleFieldRow);
        main.setCellWidth(titleFieldRow, "100%");
        addGap(main, "10px");

		main.add(new Label("Priority"));
		addGap(main, "3px");
		
		priorityField = new ListBox(false);
        priorityField.setVisibleItemCount(1);
        priorityField.addItem("LOW");
        priorityField.setItemText(0, "Low");
        priorityField.addItem("NORMAL");
        priorityField.setItemText(1, "Normal");
        priorityField.addItem("HIGH");
        priorityField.setItemText(2, "High");
        priorityField.setItemSelected(1, true);
		main.add(priorityField);
		addGap(main, "10px");
		
        main.add(new Label("Description"));
        addGap(main, "3px");

        descriptionField = new TextArea();
        descriptionField.setSize("300px", "250px");
        main.add(descriptionField);
        addGap(main, "10px");

        HorizontalPanel buttons = new HorizontalPanel();
        submitButton = new Button(editMode ? "Edit" : "Add");
        submitButton.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                handleSubmit();
            }
        });
        cancelButton = new Button("Cancel", new ClickListener() {
            public void onClick(Widget sender) {
                handleCancel();
            }
        });
        buttons.add(submitButton);
        addGap(buttons, "5px");
        buttons.add(cancelButton);
        main.add(buttons);
        addGap(main, "10px");
        main.setCellHorizontalAlignment(buttons, VerticalPanel.ALIGN_CENTER);
        main.setCellVerticalAlignment(buttons, VerticalPanel.ALIGN_MIDDLE);

        SimplePanel content = new SimplePanel();
        content.setWidget(main);
        content.setStyleName("DialogContent");
        setWidget(content);
    }
    

    //============================================== Helper Methods ====================================================

    protected void handleSubmit() {
        if (validate()) {
            task.setTitle(titleField.getText().trim());
            task.setPriority(resolveSelectedPriority());
            task.setDescription(descriptionField.getText().trim());
            taskPane.addTask(task);
            hide();
        }
    }

    protected void handleCancel() {
        hide();
    }
    
    protected Task.Priority resolveSelectedPriority() {
    	int index = priorityField.getSelectedIndex();
        String value = priorityField.getValue(index);
        return Task.Priority.valueOf(value);
    }

    protected boolean validate() {
        String title = titleField.getText().trim();
        if (title.length() == 0) {
            titleErrorImage.setTitle("Required");
            titleErrorImage.setVisible(true);
            return false;
        }
        return true;
    }

    protected void addGap(HorizontalPanel panel, String width) {
        Label label = new Label();
        panel.add(label);
        panel.setCellWidth(label, width);
    }

    protected void addGap(VerticalPanel panel, String height) {
        Label label = new Label();
        panel.add(label);
        panel.setCellHeight(label, height);
    }

}
