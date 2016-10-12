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

package com.apress.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.Window;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTasks implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    final TextBox username = new TextBox();
	final PasswordTextBox password = new PasswordTextBox();
	final Button button = new Button("Logon");
	button.addClickListener(new ClickListener() {
	  public void onClick(Widget sender) {
	    if("secret".equals(password.getText())) {
		  Window.alert("Welcome " + username.getText());
		} else {
		  Window.alert("Invalid authentication");
		}
	  }
	});
	final VerticalPanel panel = new VerticalPanel();
	panel.add(new Label("username"));
	panel.add(username);
	panel.add(new Label("password"));
	panel.add(password);
	panel.add(button);
	RootPanel.get("slot1").add(panel);
  }
}
