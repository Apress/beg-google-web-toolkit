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
 * The GWTasks application.
 *
 * @author Uri Boness
 */
public class GWTasks implements EntryPoint {

	public void onModuleLoad() {
		DockPanel mainPanel = new DockPanel();
		mainPanel.setBorderWidth(5);    	
		mainPanel.setSize("100%", "100%");
		
    	HeaderPane headerPane = new HeaderPane("GWTasks");
    	mainPanel.add(headerPane, DockPanel.NORTH);
    	mainPanel.setCellHeight(headerPane, "30px");
		mainPanel.setCellHorizontalAlignment(headerPane, DockPanel.ALIGN_CENTER);
		mainPanel.setCellVerticalAlignment(headerPane, DockPanel.ALIGN_MIDDLE);
    	
    	StatusBarPane statusBarPane = new StatusBarPane();
		mainPanel.add(statusBarPane, DockPanel.SOUTH); 
		mainPanel.setCellHeight(statusBarPane, "25px");
		mainPanel.setCellHorizontalAlignment(statusBarPane, DockPanel.ALIGN_CENTER);
		mainPanel.setCellVerticalAlignment(statusBarPane, DockPanel.ALIGN_MIDDLE);
    		
		HorizontalSplitPanel categoriesAndTasks = new HorizontalSplitPanel();
		categoriesAndTasks.setSplitPosition("150px");
		TaskPane taskPane = new TaskPane();
		categoriesAndTasks.setRightWidget(taskPane);
		CategoryPane categoryPane = new CategoryPane(taskPane);
		categoriesAndTasks.setLeftWidget(categoryPane);
		mainPanel.add(categoriesAndTasks, DockPanel.CENTER);
			
		RootPanel.get().add(mainPanel);
	}		

}
