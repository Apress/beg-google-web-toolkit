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

package com.apress.beginninggwt.chap8.history.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.SourcesTabEvents;
import com.google.gwt.user.client.ui.TabListener;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * A sample application demonstrating GWT history support.
 *
 * @author Uri Boness
 */
public class HistorySample implements EntryPoint {

	public void onModuleLoad() {
    	final TabPanel tabPanel = new DecoratedTabPanel();
		tabPanel.setSize("100%", "100%");
		tabPanel.add(new Label("This is the content of Tab 1"), "Tab 1");
		tabPanel.add(new Label("This is the content of Tab 2"), "Tab 2");
		tabPanel.add(new Label("This is the content of Tab 3"), "Tab 3");
		tabPanel.addTabListener(new TabListener() {
			public boolean onBeforeTabSelected(SourcesTabEvents ste, int index) {
				return true;
			}
			public void onTabSelected(SourcesTabEvents ste, int index) {
				History.newItem(String.valueOf(index));
			}
		});
		
		History.addHistoryListener(new HistoryListener() {
			public void onHistoryChanged(String historyToken) {
				if (historyToken.length() > 0) {
					int tabIndex = Integer.valueOf(historyToken);
					tabPanel.selectTab(tabIndex);
				}
			}
		});
		RootPanel.get().add(tabPanel);
	}

}
