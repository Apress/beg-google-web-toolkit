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

package com.apress.beginninggwt.chap5.dialogbox.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.*;

/**
 * @author Uri Boness
 */
public class CategoryFormDialogBox extends DialogBox {

	private final static String ERROR_IMAGE_URL = "image/field-error.gif";

    private TextBox nameField;
    private Image nameErrorImage;

    private TextArea descriptionField;

    private Button submitButton;
    private Button cancelButton;

    private final Category category;

	/**
	 * Constructs a new CategoryFormDialogBox to create a new Category.
	 */
    public CategoryFormDialogBox() {
        this(new Category(), false);
    }

	/**
	 * Constructs a new CategoryFormDialogBox to edit the given Category.
	 *
	 * @param category The category to edit.
	 */
    public CategoryFormDialogBox(Category category) {
        this(category, true);
    }

    private CategoryFormDialogBox(Category category, boolean editMode) {
        super(false, true);
        setText("Category Form");
        this.category = category;

        VerticalPanel main = new VerticalPanel();
        main.add(new Label("Name"));
        addGap(main, "3px");
        nameField = new TextBox();
        nameErrorImage = new Image(ERROR_IMAGE_URL);
        nameErrorImage.setVisible(false);
        HorizontalPanel nameFieldRow = new HorizontalPanel();
        nameFieldRow.add(nameField);
        nameFieldRow.setCellWidth(nameField, "60%");
        nameFieldRow.add(nameErrorImage);
        main.add(nameFieldRow);
        main.setCellWidth(nameFieldRow, "100%");
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

	/**
	 * Called when the user clicks on the Submit button.
	 */
    protected void handleSubmit() {
        if (validate()) {
            category.setName(nameField.getText().trim());
            category.setDescription(descriptionField.getText().trim());
            Window.alert("Category: " + category);
            hide();
        }
    }

	/**
	 * Called when the user clicks on the Cancel button.
	 */
    protected void handleCancel() {
        hide();
    }

	/**
	 * Validating the form, showing any encountered validation error and returning whether the form is valid or not.
	 */
    protected boolean validate() {
        String name = nameField.getText().trim();
        if (name.length() == 0) {
            nameErrorImage.setTitle("Required");
            nameErrorImage.setVisible(true);
            return false;
        }
        return true;
    }

	/**
	 * Adds a gap to the given horizontal panel.
	 * 
	 * @param panel The horizontal panel to which the gap will be added.
	 * @param width The width of the gap.
	 */
    protected void addGap(HorizontalPanel panel, String width) {
        Label label = new Label();
        panel.add(label);
        panel.setCellWidth(label, width);
    }

	/**
	 * Adds a gap to the given vertical panel.
	 * 
	 * @param panel The vertical panel to which the gap will be added.
	 * @param height The height of the gap.
	 */
    protected void addGap(VerticalPanel panel, String height) {
        Label label = new Label();
        panel.add(label);
        panel.setCellHeight(label, height);
    }

}
