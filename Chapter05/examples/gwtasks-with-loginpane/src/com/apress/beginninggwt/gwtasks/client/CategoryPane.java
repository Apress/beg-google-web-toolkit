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
 * @author Uri Boness
 */
public class CategoryPane extends Composite {

	private final Tree tree;
	private final TaskPane taskPane;
	private final PushButton addButton;
	private final PushButton removeButton;

	public CategoryPane(TaskPane taskPane) {
		this.taskPane = taskPane;
		tree = new Tree();
		tree.addTreeListener(new TreeListener() {
			public void onTreeItemSelected(TreeItem item) {
		        Category category = ((CategoryTreeItem)item).getCategory();
				removeButton.setEnabled(true);
				CategoryPane.this.taskPane.reset(category);
		    }
		    public void onTreeItemStateChanged(TreeItem item) {
		    }
		});
		List<Category> categories = getAllCategories();
		for (final Category category : categories) {
			CategoryTreeItem item = createTreeItem(category);
			tree.addItem(item);
		}

		TitledPanel titledPanel = new TitledPanel("Categories", tree);
		titledPanel.setContentVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		titledPanel.setSize("100%", "100%");
		addButton = titledPanel.addToolButton("+", "Add Category", new ClickListener() {
			public void onClick(Widget sender) {
				CategoryFormDialogBox dialog = 
					new CategoryFormDialogBox(CategoryPane.this);
				dialog.center();
				dialog.show();
			}
		});
		removeButton = titledPanel.addToolButton("-", "Add Category", new ClickListener() {
			public void onClick(Widget sender) {
				CategoryTreeItem item = (CategoryTreeItem) tree.getSelectedItem();
				if (item != null) {
					Category category = item.getCategory();
					item.remove();
					removeButton.setEnabled(false);
					CategoryPane.this.taskPane.reset();
				}
			}
		});
		removeButton.setEnabled(false);
		
		initWidget(titledPanel);
	}
	
	/**
	 * Adds the given category to this pane. The category will be added as a child of the currently
	 * selected category. If no category is currently selected, it will be added as a top level category.
	 *
	 * @param category The category to be added.
	 */
	public void addCategory(Category category) {
		CategoryTreeItem item = (CategoryTreeItem) tree.getSelectedItem();
		if (item == null) {
			tree.addItem(createTreeItem(category));
		} else {
			item.addItem(createTreeItem(category));
		}
	}
	
	//=========================================== Helper Methods =======================================================
	
	protected CategoryTreeItem createTreeItem(Category category) {
		CategoryTreeItem item = new CategoryTreeItem(category);
		for (Category child : category.getChildren()) {
			item.addItem(createTreeItem(child));
		}
		return item;
	}
	
	protected List<Category> getAllCategories() {
		List<Category> categories = new ArrayList<Category>();
		
		Category work = new Category(1L, "Work", "Things at work");
		work.addChildCategory(new Category(2L, "Calls", "Make phone calls"));
		work.addChildCategory(new Category(3L, "Meetings", "People I need to meet with"));
		categories.add(work);
		Category home = new Category(4L, "Home", "Things at home");
		home.addChildCategory(new Category(5L, "Shoppings", "Things I need to buy"));
		home.addChildCategory(new Category(6L, "Bills", "Bills I need to sort"));
		categories.add(home);
		categories.add(new Category(3L, "Others", "Other things I need to do"));
		return categories;
	}
	
	
	//============================================ Inner Classes =======================================================
	
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
