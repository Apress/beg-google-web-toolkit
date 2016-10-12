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

package com.apress.beginninggwt.gwtasks.client.ui.mainpane;

import com.apress.beginninggwt.gwtasks.client.support.widget.Pane;
import com.apress.beginninggwt.gwtasks.client.ui.event.LogoutEvent;
import com.google.gwt.user.client.ui.*;

/**
 * The header panel.
 * 
 * @author Uri Boness
 */
public class HeaderPane extends Pane {

	private final Label title;

	/**
	 * Constructs a new HeaderPane that displays the given text.
     *
     * @param titleText The text this header should show as a title.
	 */
	public HeaderPane(String titleText) {

        DockPanel main = new DockPanel();

        title = new Label(titleText);
        title.setStyleName("Title");
        main.add(title, DockPanel.CENTER);
        main.setCellWidth(title, "100%");

        PushButton logoutButton = new PushButton("Logout");
        logoutButton.setStyleName("LogoutButton");
        logoutButton.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                fireEvent(new LogoutEvent(HeaderPane.this));
            }
        });
        main.add(logoutButton, DockPanel.EAST);

		initWidget(main);
        setStyleName("HeaderPane");
	}
	
	/**
	 * Sets the text that should be displayed in this header pane.
     *
     * @param titleText The text of the title.
	 */
	public void setTitleText(String titleText) {
		title.setText(titleText);
	}
}
