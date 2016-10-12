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

import com.apress.beginninggwt.gwtasks.client.manager.ManagerRegistry;
import com.apress.beginninggwt.gwtasks.client.ui.Pane;
import com.apress.beginninggwt.gwtasks.client.ui.image.GWTasksImages;
import com.apress.beginninggwt.gwtasks.client.ui.event.LogoutEvent;
import com.apress.beginninggwt.gwtasks.client.ui.i18n.GWTasksConstants;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

/**
 * A {@link Pane} that shows the header of the main page. This header consists of a title and a logout button/link.
 *
 * @author Uri Boness
 */
public class HeaderPane extends Pane {

	private final Label title;

	/**
	 * Constructs a new HeaderPane that displays the given text.
     *
     * NOTE: This pane was slightly modified and differs from the version shown in the book. The reason for that is that
     *       we wanted to show how one can change the locale from within the application. For this, we added two flags
     *       for the two supported languages (dutch and english) and added them next to the logout button.
     *
     * @param titleText The text this header should show as a title.
     * @param managerRegistry The manager registry to associate with this pane.
	 */
	public HeaderPane(String titleText, ManagerRegistry managerRegistry) {
        super(managerRegistry);

        DockPanel main = new DockPanel();

        title = new Label(titleText);
        title.setStyleName("Title");
        main.add(title, DockPanel.CENTER);
        main.setCellWidth(title, "100%");

        HorizontalPanel buttons = new HorizontalPanel();
        buttons.setSpacing(3);
        
        Anchor dutch = new Anchor(GWTasksImages.Impl.getInstance().dutchFlag().getHTML(), true, GWT.getHostPageBaseURL() + "?locale=nl");
        buttons.add(dutch);

        Anchor english = new Anchor(GWTasksImages.Impl.getInstance().englishFlag().getHTML(), true, GWT.getHostPageBaseURL());
        buttons.add(english);

        PushButton logoutButton = new PushButton(GWTasksConstants.Impl.getInstance().logoutButton());
        logoutButton.setStyleName("LogoutButton");
        logoutButton.addClickListener(new ClickListener() {
            public void onClick(Widget sender) {
                getManagerRegistry().getSecurityManager().logout();
                fireEvent(new LogoutEvent(HeaderPane.this));
            }
        });
        buttons.add(logoutButton);
        
        main.add(buttons, DockPanel.EAST);

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
