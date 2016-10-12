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
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.Window;

/**
 * A simple example of the ToggleButton together with a Hyperlink.
 *
 * @author Uri Boness
 */
public class ButtonExample implements EntryPoint {

	public void onModuleLoad() {
	
		final ToggleButton messageToggleButton = new ToggleButton("UP", "DOWN");
		RootPanel.get().add(messageToggleButton);
		
		// the only thing that changed from the previous example is that a Hyperlink is used instead of a button 
		Hyperlink alertLink = new Hyperlink("Alert", "alert");
		alertLink.addClickListener(new ClickListener() {
			public void onClick(Widget widget) {
				if (messageToggleButton.isDown()) {
					Window.alert("HELLLLP!!!!");
				} else {
					Window.alert("Take it easy and relax");
				}
			}
		});
		
		RootPanel.get().add(alertLink);
	}
}
