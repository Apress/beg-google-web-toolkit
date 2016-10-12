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
 
package com.apress.beginninggwt.gwtasks.client.ui.mainpane.categorypane;

import com.apress.beginninggwt.gwtasks.client.manager.data.DataManager;
import com.apress.beginninggwt.gwtasks.client.model.Category;
import com.apress.beginninggwt.gwtasks.client.support.async.Callback;
import com.apress.beginninggwt.gwtasks.client.ui.i18n.GWTasksConstants;
import com.apress.beginninggwt.gwtasks.client.ui.image.GWTasksImages;
import com.google.gwt.user.client.ui.*;

/**
 * @author Uri Boness
 */
public class CategoryFormDialogBox extends DialogBox {

    private final TextBox nameField;
    private final Image nameErrorImage;
    private final TextArea descriptionField;
    private final Button submitButton;
    private final Button cancelButton;

    private final boolean editMode;
    private final Category category;
    private final CategoryPane categoryPane;
    private final DataManager dataManager;

    public CategoryFormDialogBox(CategoryPane categoryPane, DataManager dataManager) {
        this(categoryPane, dataManager, new Category(), false);
    }

    public CategoryFormDialogBox(CategoryPane categoryPane, DataManager dataManager, Category category) {
        this(categoryPane, dataManager, category, true);
    }

    private CategoryFormDialogBox(CategoryPane categoryPane, DataManager dataManager, Category category, boolean editMode) {
        super(false, true);

        GWTasksConstants constants = GWTasksConstants.Impl.getInstance();

        setText(constants.categoryFormTitle());
        this.category = category;
		this.categoryPane = categoryPane;
        this.dataManager = dataManager;
		this.editMode = editMode;

        VerticalPanel main = new VerticalPanel();
        main.add(new Label(constants.categoryFormNameLabel()));
        addGap(main, "3px");

        nameField = new TextBox();
        nameErrorImage = GWTasksImages.Impl.getInstance().fieldErrorIcon().createImage();
        nameErrorImage.setVisible(false);
        HorizontalPanel nameFieldRow = new HorizontalPanel();
        nameFieldRow.add(nameField);
        nameFieldRow.setCellWidth(nameField, "60%");
        nameFieldRow.add(nameErrorImage);
        main.add(nameFieldRow);
        main.setCellWidth(nameFieldRow, "100%");
        addGap(main, "10px");

        main.add(new Label(constants.categoryFormDescriptionLabel()));
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
            category.setName(nameField.getText().trim());
            category.setDescription(descriptionField.getText().trim());
            if (editMode) {
                handleUpdate(category);
            } else {
                handleCreate(category);
            }
        }
    }

    protected void handleCreate(Category category) {
        Category selectedCategory = categoryPane.getSelectedCategory();
        Long parentCategoryId = selectedCategory == null ? null : selectedCategory.getId();
        dataManager.createCategory(category, parentCategoryId, new Callback<Category>() {
            public void onSuccess(Category category) {
                categoryPane.addCategory(category);
                hide();
            }
        });
    }

    protected void handleUpdate(Category category) {
        dataManager.updateCategory(category, new Callback<Void>() {
            public void onSuccess(Void result) {
                categoryPane.reloadCategories();
            }
        });
    }

    protected void handleCancel() {
        hide();
    }

    protected boolean validate() {
        String name = nameField.getText().trim();
        if (name.length() == 0) {
            nameErrorImage.setTitle(GWTasksConstants.Impl.getInstance().requiredError());
            nameErrorImage.setVisible(true);
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
