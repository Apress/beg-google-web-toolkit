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
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * A simple example of showing static text on screen using the Label class.
 *
 * @author Uri Boness
 */
public class StaticTextExample implements EntryPoint {

	public void onModuleLoad() {
		String link = "http://code.google.com/webtoolkit";
		HTML html = new HTML("<a href=\"" + link + "\">GWT Website</a>");
		RootPanel.get().add(html);
	}
	
}
