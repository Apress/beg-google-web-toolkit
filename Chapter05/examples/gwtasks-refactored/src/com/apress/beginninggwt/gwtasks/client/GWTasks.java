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
import com.apress.beginninggwt.gwtasks.client.ui.loginpane.LoginPane;
import com.apress.beginninggwt.gwtasks.client.ui.mainpane.MainPane;
import com.apress.beginninggwt.gwtasks.client.ui.event.LoginEvent;
import com.apress.beginninggwt.gwtasks.client.ui.event.LogoutEvent;
import com.apress.beginninggwt.gwtasks.client.support.event.ApplicationEventListener;
import com.apress.beginninggwt.gwtasks.client.support.event.ApplicationEvent;

/**
 * The GWTasks application.
 *
 * @author Uri Boness
 */
public class GWTasks implements EntryPoint {

	private SimplePanel main;
	private LoginPane loginPane;
	private MainPane mainPane;

	public void onModuleLoad() {
		mainPane = new MainPane();
        mainPane.addListener(new MainPaneListener());
		mainPane.setSize("100%", "100%");

		loginPane = new LoginPane();
        loginPane.addListener(new LoginPaneListener());
		loginPane.setSize("100%", "100%");

        main = new SimplePanel();
		main.setSize("100%", "100%");
		main.setWidget(loginPane);

        RootPanel.get().add(main);
	}

    /**
     * Shows the login pane.
     */
	protected void showLoginPane() {
		main.setWidget(loginPane);
	}

    /**
     * Shows the main pane.
     */
	protected void showMainPane() {
		main.setWidget(mainPane);
	}


    //============================================= Inner Classes ======================================================

    protected class LoginPaneListener implements ApplicationEventListener {
        public void handle(ApplicationEvent event) {
            if (event instanceof LoginEvent) {
                showMainPane();
            }
        }
    }

    protected class MainPaneListener implements ApplicationEventListener {
        public void handle(ApplicationEvent event) {
            if (event instanceof LogoutEvent) {
                showLoginPane();
            }
        }
    }
}
