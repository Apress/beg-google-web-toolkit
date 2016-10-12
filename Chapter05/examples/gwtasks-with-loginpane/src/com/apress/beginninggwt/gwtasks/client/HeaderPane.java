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
 * The header panel.
 *
 * @author Uri Boness
 */
public class HeaderPane extends Composite {

	private final Label title;

	/**
	 * Constructs a new HeaderPane that displays the given text.
	 */
	public HeaderPane(String titleText) {
		title = new Label(titleText);
		initWidget(title);
	}
	
	/**
	 * Sets the text that should be displayed in this header pane.
	 */
	public void setTitleText(String titleText) {
		title.setText(titleText);
	}
}
