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

package com.apress.beginninggwt.gwtasks.client.support.widget;

import com.google.gwt.user.client.ui.*;

public class ErrorDialogBox extends DialogBox {
	public ErrorDialogBox(Throwable t) {
		super(false,true);
		setText("An error occurred.");
		VerticalPanel vp = new VerticalPanel();
		vp.add(new Label(t.getMessage()));
		DisclosurePanel panel = new DisclosurePanel("more details...");
		VerticalPanel stackTracePanel = new VerticalPanel();
		for(StackTraceElement ste: t.getStackTrace()) {
			stackTracePanel.add(new Label(ste.toString()));
		}
		panel.setContent(stackTracePanel);
		vp.add(panel);
		vp.add(new Button("close", new ClickListener() {
			public void onClick(Widget sender) {
				ErrorDialogBox.this.hide();
			}
		}));
		setWidget(vp);
		center();
	}
}
