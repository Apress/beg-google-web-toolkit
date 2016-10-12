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

import java.util.Iterator;
import java.util.List;

import com.apress.beginninggwt.gwtasks.client.manager.ManagerRegistry;
import com.apress.beginninggwt.gwtasks.client.manager.data.DataManager;
import com.apress.beginninggwt.gwtasks.client.model.Category;
import com.apress.beginninggwt.gwtasks.client.support.async.Callback;
import com.apress.beginninggwt.gwtasks.client.support.widget.TitledPanel;
import com.apress.beginninggwt.gwtasks.client.ui.Pane;
import com.apress.beginninggwt.gwtasks.client.ui.event.CategoryCreatedEvent;
import com.apress.beginninggwt.gwtasks.client.ui.event.CategorySelectionEvent;
import com.apress.beginninggwt.gwtasks.client.ui.i18n.GWTasksConstants;
import com.apress.beginninggwt.gwtasks.client.ui.image.CategoryTreeImages;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.TreeListener;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.core.client.GWT;

/**
 * A {@link Pane} showing a tree of categories and enables to add new ones or remove existing ones.
 *
 * @author Uri Boness
 */
public class CategoryPane extends Pane {

    private final Tree tree;

    private final PushButton addButton;

    private final PushButton removeButton;

    private HistoryListener historyListener;

    /**
     * Constructs a new CategoryPane with a given manager registry.
     *
     * @param managerRegistry The manager registry to associate this pane with.
     */
    public CategoryPane(ManagerRegistry managerRegistry) {
        super(managerRegistry);

        GWTasksConstants constants = GWTasksConstants.Impl.getInstance();

        tree = new Tree(CategoryTreeImages.Impl.getInstance());
        tree.addTreeListener(new TreeListener() {
            public void onTreeItemSelected(TreeItem item) {
                Category category = ((CategoryTreeItem) item).getCategory();
                String token = String.valueOf(category.getId());
                History.newItem(token);
                fireEvent(new CategorySelectionEvent(CategoryPane.this, category));
                removeButton.setEnabled(true);
            }

            public void onTreeItemStateChanged(TreeItem item) {
            }
        });

        TitledPanel titledPanel = new TitledPanel(constants.categoryPaneTitle(), tree);
        titledPanel.setContentVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
        titledPanel.setSize("100%", "100%");
        addButton = titledPanel.addToolButton("+", constants.categoryPaneAddButton(), new ClickListener() {
            public void onClick(Widget sender) {
                CategoryFormDialogBox dialog =
                        new CategoryFormDialogBox(CategoryPane.this, getManagerRegistry().getDataManager());
                dialog.center();
                dialog.show();
            }
        });
        removeButton = titledPanel.addToolButton("-", constants.categoryPaneRemoveButton(), new ClickListener() {
            public void onClick(Widget sender) {
                final CategoryTreeItem item = (CategoryTreeItem) tree.getSelectedItem();
                if (item == null) {
                    return;
                }
                Long categoryId = item.getCategory().getId();
                getManagerRegistry().getDataManager().removeCategory(categoryId, new Callback<Void>() {
                    public void onSuccess(Void result) {
                        item.remove();
                        removeButton.setEnabled(false);
                        fireEvent(new CategorySelectionEvent(CategoryPane.this, null));
                    }
                });
            }
        });
        removeButton.setEnabled(false);

        historyListener = new HistoryListener() {
            public void onHistoryChanged(String historyToken) {
                setStateFromHistoryToken(historyToken);
            }
        };
        SimplePanel main = new SimplePanel();
        main.setWidget(titledPanel);
        initWidget(main);
        setStyleName("CategoryPane");
    }

    public void reset() {
        tree.clear();
        History.removeHistoryListener(historyListener);
        if (getManagerRegistry().getSecurityManager().isLoggedIn()) {
            DataManager dataManager = getManagerRegistry().getDataManager();
            dataManager.getCategories(new Callback<List<Category>>() {
                public void onSuccess(List<Category> categories) {
                    for (final Category category : categories) {
                        CategoryTreeItem item = createTreeItem(category);
                        tree.addItem(item);
                    }
                    setStateFromHistoryToken(History.getToken());
                    History.addHistoryListener(historyListener);
                }
            });
        }
    }

    protected void setStateFromHistoryToken(String historyToken) {
        if (historyToken.length() > 0) {
            long categoryId = Long.valueOf(historyToken);
            if (getSelectedCategory() != null && getSelectedCategory().getId() != categoryId) {
                setSelectedCategory(categoryId);
            }
        } else {
            clearSelection();
        }
    }

    /**
     * Adds the given category to this pane. The category will be added as a child of the currently
     * selected category. If no category is currently selected, it will be added as a top level category.
     *
     * @param category The category to be added.
     */
    public void addCategory(Category category) {
        final CategoryTreeItem item = (CategoryTreeItem) tree.getSelectedItem();
        CategoryTreeItem newItem = createTreeItem(category);
        if (item == null) {
            tree.addItem(newItem);
        } else {
            item.addItem(newItem);
        }
        fireEvent(new CategoryCreatedEvent(CategoryPane.this, category));
    }

    //=========================================== Helper Methods =======================================================

    protected CategoryTreeItem createTreeItem(Category category) {
        CategoryTreeItem item = new CategoryTreeItem(category);
        for (Category child : category.getChildren()) {
            item.addItem(createTreeItem(child));
        }
        return item;
    }

    protected void reloadCategories() {
        getManagerRegistry().getDataManager().getCategories(new Callback<List<Category>>() {
            public void onSuccess(List<Category> categories) {
                for (final Category category : categories) {
                    CategoryTreeItem item = createTreeItem(category);
                    tree.addItem(item);
                }
            }
        });
    }

    protected Category getSelectedCategory() {
        CategoryTreeItem item = (CategoryTreeItem) tree.getSelectedItem();
        return item != null ? item.getCategory() : null;
    }

    protected void setSelectedCategory(long categoryId) {
        for (Iterator<TreeItem> iter = tree.treeItemIterator(); iter.hasNext();) {
            CategoryTreeItem item = (CategoryTreeItem) iter.next();
            if (item.getCategory().getId() == categoryId) {
                tree.setSelectedItem(item);
            }
        }
    }

    protected void clearSelection() {
        tree.setSelectedItem(null);
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
