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

import com.apress.beginninggwt.gwtasks.client.manager.data.DataManager;
import com.apress.beginninggwt.gwtasks.client.model.Task;
import com.apress.beginninggwt.gwtasks.client.support.async.Callback;
import com.apress.beginninggwt.gwtasks.client.ui.i18n.GWTasksConstants;
import com.apress.beginninggwt.gwtasks.client.ui.image.GWTasksImages;
import com.google.gwt.user.client.ui.*;

/**
 * @author Uri Boness
 */
public class TaskFormDialogBox extends DialogBox {

	private final TextBox titleField;
    private final Image titleErrorImage;
	private final ListBox priorityField;
    private final TextArea descriptionField;
    private final Button submitButton;
    private final Button cancelButton;

    private final Task task;
    private final TaskPane taskPane;
    private final DataManager dataManager;
    private final boolean editMode;

    public TaskFormDialogBox(TaskPane taskPane, DataManager dataManager) {
        this(taskPane, new Task(), dataManager, false);
    }

    public TaskFormDialogBox(TaskPane taskPane, Task task, DataManager dataManager) {
        this(taskPane, task, dataManager, true);
    }

    private TaskFormDialogBox(TaskPane taskPane, Task task, DataManager dataManager, boolean editMode) {
        super(false, true);
        this.task = task;
		this.taskPane = taskPane;
		this.dataManager = dataManager;
        this.editMode = editMode;

        GWTasksConstants constants = GWTasksConstants.Impl.getInstance();

        setText(constants.taskFormTitle());

        VerticalPanel main = new VerticalPanel();
        main.add(new Label(constants.taskFormTitleLabel()));
        addGap(main, "3px");

        titleField = new TextBox();
        titleErrorImage = GWTasksImages.Impl.getInstance().fieldErrorIcon().createImage();
        titleErrorImage.setVisible(false);
        HorizontalPanel titleFieldRow = new HorizontalPanel();
        titleFieldRow.add(titleField);
        titleFieldRow.setCellWidth(titleField, "60%");
        titleFieldRow.add(titleErrorImage);
        main.add(titleFieldRow);
        main.setCellWidth(titleFieldRow, "100%");
        addGap(main, "10px");

		main.add(new Label(constants.taskFormPriorityLabel()));
		addGap(main, "3px");
		
		priorityField = new ListBox(false);
        priorityField.setVisibleItemCount(1);
        priorityField.addItem("LOW");
        priorityField.setItemText(0, constants.taskFormLowPriority());
        priorityField.addItem("NORMAL");
        priorityField.setItemText(1, constants.taskFormNormalPriority());
        priorityField.addItem("HIGH");
        priorityField.setItemText(2, constants.taskFormHighPriority());
        priorityField.setItemSelected(1, true);
		main.add(priorityField);
		addGap(main, "10px");
		
        main.add(new Label(constants.taskFormDescriptionLabel()));
        addGap(main, "3px");

        descriptionField = new TextArea();
        descriptionField.setSize("300px", "250px");
        main.add(descriptionField);
        addGap(main, "10px");

        HorizontalPanel buttons = new HorizontalPanel();
        submitButton = new Button(editMode ? constants.editButton() : constants.addButton());
        submitButton.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                handleSubmit();
            }
        });
        cancelButton = new Button(constants.cancelButton(), new ClickListener() {
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
            if (editMode) {
                handleUpdate(task);
            } else {
                handleCreate(task);
            }
        }
    }

    protected void handleCreate(Task task) {
        dataManager.createTask(task, taskPane.getCurrentCategory().getId(), new Callback<Task>() {
            public void onSuccess(Task task) {
                taskPane.addTask(task);
                hide();
            }
        });
    }

    protected void handleUpdate(Task task) {
        dataManager.updateTask(task, new Callback<Void>() {
                public void onSuccess(Void result) {
                    taskPane.reloadTasks();
                    hide();
                }
            });
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
            titleErrorImage.setTitle(GWTasksConstants.Impl.getInstance().requiredError());
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
