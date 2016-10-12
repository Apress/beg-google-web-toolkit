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

package com.apress.beginninggwt.chap4.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.Window;

/**
 * A simple example of a Button in action.
 *
 * @author Uri Boness
 */
public class ButtonExample implements EntryPoint {

	public void onModuleLoad() {
	
		// creating a new button with "Alert" as its caption
		Button alertButton = new Button("Alert");
	
		// uncomment the following 3 lines to customize the size and behavior of the button.
		//alertButton.setHeight("150px");
		//alertButton.setWidth("200px");
		//alertButton.setEnabled(false);
	
		// registering a click listener. When the button is clicked an alert dialog will be shown.
		alertButton.addClickListener(new ClickListener() {
			public void onClick(Widget widget) {
				Window.alert("This is an alert!!! ");
			}
		});
		
		// adding the button to the body of the hosting html
		RootPanel.get().add(alertButton);
	}
}
