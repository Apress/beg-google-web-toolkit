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

import com.apress.beginninggwt.gwtasks.client.manager.ManagerRegistry;
import com.apress.beginninggwt.gwtasks.client.model.Category;
import com.apress.beginninggwt.gwtasks.client.model.Task;
import com.apress.beginninggwt.gwtasks.client.support.async.Callback;
import com.apress.beginninggwt.gwtasks.client.support.widget.TitledPanel;
import com.apress.beginninggwt.gwtasks.client.ui.Pane;
import com.apress.beginninggwt.gwtasks.client.ui.image.GWTasksImages;
import com.apress.beginninggwt.gwtasks.client.ui.i18n.GWTasksConstants;
import com.apress.beginninggwt.gwtasks.client.ui.event.TaskSelectionEvent;
import com.google.gwt.user.client.ui.*;

import java.util.List;

/**
 * A {@link Pane} showing a list of all the tasks for a chosen category. It allows adding/removing tasks as well
 * as un/selecting them.
 *
 * @author Uri Boness
 */
public class TaskPane extends Pane {

    private final FlexTable taskTable;

    private PushButton addButton;

    private PushButton removeButton;

    private int selectedRow = -1;

    private Task selectedTask;

    private Category category;

    /**
     * Constructs a new TaskPane with a given manager registry.
     *
     * @param managerRegistry The manager registry to associate with this pane.
     */
    public TaskPane(ManagerRegistry managerRegistry) {
        super(managerRegistry);

        GWTasksConstants constants = GWTasksConstants.Impl.getInstance();

        taskTable = new FlexTable();
        taskTable.getColumnFormatter().setWidth(0, "20px");
        taskTable.getColumnFormatter().setWidth(1, "20px");
        taskTable.getColumnFormatter().setWidth(2, "100%");

        Label checkHeaderLabel = new Label();
        checkHeaderLabel.setWidth("20px");
        checkHeaderLabel.setHorizontalAlignment(Label.ALIGN_CENTER);
        taskTable.setWidget(0, 0, checkHeaderLabel);

        Label priorityHeaderLabel = new Label("!");
        priorityHeaderLabel.setWidth("20px");
        priorityHeaderLabel.setHorizontalAlignment(Label.ALIGN_CENTER);
        taskTable.setWidget(0, 1, priorityHeaderLabel);

        Label titleHeaderLabel = new Label(constants.taskPaneTitleTableHeader());
        titleHeaderLabel.setWidth("100%");
        taskTable.setWidget(0, 2, titleHeaderLabel);

        taskTable.getRowFormatter().setStyleName(0, "TableHeader");

        TitledPanel titledPanel = new TitledPanel(constants.taskPaneTitle(), taskTable);
        titledPanel.setContentVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
        titledPanel.setSize("100%", "100%");

        addButton = titledPanel.addToolButton("+", constants.taskPaneAddButton(), new ClickListener() {
            public void onClick(Widget sender) {
                TaskFormDialogBox dialog = new TaskFormDialogBox(TaskPane.this, getManagerRegistry().getDataManager());
                dialog.center();
                dialog.show();
            }
        });
        addButton.setEnabled(false);

        removeButton = titledPanel.addToolButton("-", constants.taskPaneRemoveButton(), new ClickListener() {
            public void onClick(Widget sender) {
                if (selectedRow == -1) {
                    return;
                }
                getManagerRegistry().getDataManager().removeTask(selectedTask.getId(), new Callback<Void>() {
                    public void onSuccess(Void result) {
                        selectedRow = -1;
                        selectedTask = null;
                        removeButton.setEnabled(false);
                        reset(category);
                    }
                });
            }
        });
        removeButton.setEnabled(false);

        SimplePanel mainPanel = new SimplePanel();
        mainPanel.setWidget(titledPanel);
        initWidget(mainPanel);
        setStyleName("TaskPane");
    }

    /**
     * Reloads the the current tasks based on the currely associated category. If the associted category is
     * <code>null</code>, this call has the same effect as calling <code>reset(getCurrentCategory())</code>.
     */
    public void reloadTasks() {
        reset(category);
    }

    /**
     * Resets this pane, that is, removing its current category association and the displyed tasks.
     */
    public void reset() {
        reset(null);
    }

    /**
     * Resets this pane to be associated with the given category and displays all its tasks. If the given category
     * is <code>null</code> all currently displayed tasks will be removed.
     *
     * @param category The newly associated category.
     */
    public void reset(Category category) {
        while (taskTable.getRowCount() > 1) {
            taskTable.removeRow(taskTable.getRowCount() - 1);
        }
        this.category = category;
        if (category == null) {
            return;
        }
        getManagerRegistry().getDataManager().getTasks(category.getId(), new Callback<List<Task>>() {
            public void onSuccess(List<Task> tasks) {
                for (Task task : tasks) {
                    addTask(task);
                }
                addButton.setEnabled(true);
            }
        });
    }

    /**
     * Returns the currently associated category, that is, the category whose tasks should be displayed.
     *
     * @return The currenly category associated with this pane.
     */
    public Category getCurrentCategory() {
        return category;
    }

    //=========================================== Helper Methods =======================================================

    /**
     * Adds a task to the tasks list.
     *
     * NOTE: This method implementation is slightly different than the one used int the book. In this implementation,
     *       when a task is clicked, its associated image is replaced with a "disabled" image. We though this may serve
     *       as a good use case of how to use the different methods of the AbstractImagePrototype. More specifically the
     *       {@link AbstractImagePrototype#applyTo(com.google.gwt.user.client.ui.Image)} method.
     *
     * @param task The task to be added.
     */
    protected void addTask(final Task task) {
        final int row = taskTable.getRowCount();

        final Image priorityImage = resolvePriorityImage(task.getPriority());
        priorityImage.setTitle(task.getPriority().name());

        final CheckBox checkBox = new CheckBox();
        checkBox.setChecked(task.isClosed());
        checkBox.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                handleTaskClose(task, checkBox, priorityImage, row);
            }
        });
        if (checkBox.isChecked()) {
            taskTable.getRowFormatter().addStyleName(row, "TaskRow-disabled");
            GWTasksImages.Impl.getInstance().disabledPriorityIcon().applyTo(priorityImage);
        }

        Label titleLabel = new Label(task.getTitle());
        titleLabel.setStyleName("TaskRow");
        titleLabel.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                handleTaskRowClicked(row, task);
            }
        });

        taskTable.setWidget(row, 0, checkBox);
        taskTable.setWidget(row, 1, priorityImage);
        taskTable.setWidget(row, 2, titleLabel);
    }

    protected void handleTaskClose(final Task task, final CheckBox checkBox, final Image priorityImage, final int row) {
        task.setClosed(checkBox.isChecked());
        getManagerRegistry().getDataManager().updateTask(task, new Callback<Void>() {
            public void onSuccess(Void result) {
                if (checkBox.isChecked()) {
                    taskTable.getRowFormatter().addStyleName(row, "TaskRow-disabled");
                    GWTasksImages.Impl.getInstance().disabledPriorityIcon().applyTo(priorityImage);
                } else {
                    taskTable.getRowFormatter().removeStyleName(row, "TaskRow-disabled");
                    resolvePriorityImagePrototype(task.getPriority()).applyTo(priorityImage);
                }
            }

            public void onFailure(Throwable caught) {
                checkBox.setChecked(!checkBox.isChecked());
            }
        });
    }

    protected void handleTaskRowClicked(int row, Task task) {
        HTMLTable.RowFormatter rowFormatter = taskTable.getRowFormatter();
        if (selectedRow == row) {
            selectedRow = -1;
            selectedTask = null;
            rowFormatter.removeStyleName(row, "TaskRow-selected");
            removeButton.setEnabled(false);
            fireEvent(new TaskSelectionEvent(this, null));
        } else {
            if (selectedRow != -1) {
                rowFormatter.removeStyleName(selectedRow, "TaskRow-selected");
                removeButton.setEnabled(false);
                fireEvent(new TaskSelectionEvent(this, null));
            }
            selectedRow = row;
            selectedTask = task;
            taskTable.getRowFormatter().addStyleName(row, "TaskRow-selected");
            removeButton.setEnabled(true);
            fireEvent(new TaskSelectionEvent(this, task));
        }
    }

    protected Image resolvePriorityImage(Task.Priority priority) {
        return resolvePriorityImagePrototype(priority).createImage();
    }

    protected AbstractImagePrototype resolvePriorityImagePrototype(Task.Priority priority) {
        switch (priority) {
            case HIGH:
                return GWTasksImages.Impl.getInstance().highPriorityIcon();
            case NORMAL:
                return GWTasksImages.Impl.getInstance().normalPriorityIcon();
            default:
                return GWTasksImages.Impl.getInstance().lowPriorityIcon();
        }
    }

}
