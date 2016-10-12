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

package com.apress.beginninggwt.chap5.dialogbox.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 *
 * @author Uri Boness
 */
public class DialogBoxSample implements EntryPoint {

	/**
	* This is the entry point method.
	*/
	public void onModuleLoad() {

		Button button = new Button("Show Form", new ClickListener() {
			public void onClick(Widget sender) {
				CategoryFormDialogBox dialog = new CategoryFormDialogBox();
				dialog.center();
				dialog.show();
			}
		});
		
		RootPanel.get().add(button);

	}

}
