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

package com.apress.beginninggwt.chap5.loginform.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

/**
 * Displays a login form
 *
 * @author Uri Boness
 */
public class LoginForm implements EntryPoint {

	public void onModuleLoad() {
	
		Grid grid = new Grid(3, 2);
		grid.setBorderWidth(1);
		grid.setWidget(0, 0, new Label("Username:"));
		grid.setWidget(0, 1, new TextBox());
		grid.setWidget(1, 0, new Label("Password:"));
		grid.setWidget(1, 1, new TextBox());
		grid.setWidget(2, 1, new Button("Login"));
		grid.getCellFormatter()
			.setHorizontalAlignment(2, 1, HasHorizontalAlignment.ALIGN_RIGHT);
        
        RootPanel.get().add(grid);
	}
}
