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
 
package com.apress.beginninggwt.gwtasks.client.ui.mainpane;

import com.google.gwt.user.client.ui.*;
import com.apress.beginninggwt.gwtasks.client.ui.mainpane.categorypane.CategoryPane;
import com.apress.beginninggwt.gwtasks.client.ui.mainpane.taskpane.TaskPane;
import com.apress.beginninggwt.gwtasks.client.ui.event.CategorySelectionEvent;
import com.apress.beginninggwt.gwtasks.client.ui.Pane;
import com.apress.beginninggwt.gwtasks.client.ui.i18n.GWTasksConstants;
import com.apress.beginninggwt.gwtasks.client.support.event.ApplicationEventListener;
import com.apress.beginninggwt.gwtasks.client.support.event.ApplicationEvent;
import com.apress.beginninggwt.gwtasks.client.model.Category;
import com.apress.beginninggwt.gwtasks.client.manager.ManagerRegistry;

/**
 * A {@link Pane} that shows the main page of the application. This page consists of four parts:
 * <ul>
 *  <li>{@link CategoryPane} - shows the category tree.</li>
 *  <li>{@link TaskPane} - shows the tasks for the selected category</li>
 *  <li>{@link HeaderPane} - The header of the page</li>
 *  <li>{@link StatusBarPane} - Serves as the status bar of the page.</li>
 * </ul>
 *
 * @author Uri Boness
 */
public class MainPane extends Pane {

    private HeaderPane headerPane;
    private StatusBarPane statusBarPane;
    private TaskPane taskPane;
    private CategoryPane categoryPane;

	/**
	 * Constructs a new MainPane with a given manager registry.
     *
     * @param managerRegistry The manager registry to associate with this pane.
	 */
	public MainPane(ManagerRegistry managerRegistry) {
        super(managerRegistry);

		DockPanel mainPanel = new DockPanel();
		mainPanel.setBorderWidth(5);    	
		mainPanel.setSize("100%", "100%");
		
    	headerPane = new HeaderPane(GWTasksConstants.Impl.getInstance().mainPaneHeaderTitle(), managerRegistry);
    	headerPane.addListener(new HeaderPaneListener());
        mainPanel.add(headerPane, DockPanel.NORTH);
    	mainPanel.setCellHeight(headerPane, "30px");
		mainPanel.setCellHorizontalAlignment(headerPane, DockPanel.ALIGN_CENTER);
		mainPanel.setCellVerticalAlignment(headerPane, DockPanel.ALIGN_MIDDLE);
    	
    	statusBarPane = new StatusBarPane(managerRegistry);
		mainPanel.add(statusBarPane, DockPanel.SOUTH); 
		mainPanel.setCellHeight(statusBarPane, "25px");
		mainPanel.setCellHorizontalAlignment(statusBarPane, DockPanel.ALIGN_CENTER);
		mainPanel.setCellVerticalAlignment(statusBarPane, DockPanel.ALIGN_MIDDLE);
    		
		HorizontalSplitPanel categoriesAndTasks = new HorizontalSplitPanel();
		categoriesAndTasks.setSplitPosition("150px");
		taskPane = new TaskPane(managerRegistry);
        taskPane.addListener(new TaskPaneListener());
		categoriesAndTasks.setRightWidget(taskPane);
		categoryPane = new CategoryPane(managerRegistry);
        categoryPane.addListener(new CategoryPaneListener());
		categoriesAndTasks.setLeftWidget(categoryPane);
		mainPanel.add(categoriesAndTasks, DockPanel.CENTER);
			
		initWidget(mainPanel);
	}
    
    public void reset() {
        taskPane.reset();
        categoryPane.reset();
    }


    //============================================= Inner Classes ======================================================

    protected class CategoryPaneListener implements ApplicationEventListener {
        public void handle(ApplicationEvent event) {
            if (event instanceof CategorySelectionEvent) {
                Category category = ((CategorySelectionEvent)event).getCategory();
                taskPane.reset(category);
            }
            statusBarPane.setMessage(event.getDescription());
        }
    }

    protected class TaskPaneListener implements ApplicationEventListener {
        public void handle(ApplicationEvent event) {
            statusBarPane.setMessage(event.getDescription());
        }
    }

    protected class HeaderPaneListener implements ApplicationEventListener {
        public void handle(ApplicationEvent event) {
            fireEvent(event); // bubble it up
        }
    }

}
