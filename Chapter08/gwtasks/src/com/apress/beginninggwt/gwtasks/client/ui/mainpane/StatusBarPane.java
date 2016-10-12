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

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.*;
import com.apress.beginninggwt.gwtasks.client.ui.Pane;
import com.apress.beginninggwt.gwtasks.client.manager.ManagerRegistry;

import java.util.*;

/**
 * The status bar of the GWTasks application.
 *
 * @author Uri Boness
 */
public class StatusBarPane extends Pane {

	private final Label messageLabel;

    /**
     * Constructs a new StatusBarPane with a given manager registry.
     *
     * @param managerRegistry The manager registry to associate with this pane.
     */
	public StatusBarPane(ManagerRegistry managerRegistry) {
        super(managerRegistry);
		messageLabel = new Label();
        messageLabel.setStyleName("MessageLabel");
		initWidget(messageLabel);
        setStyleName("StatusBarPane");
	}
	
	/**
	 * Sets the message to be displayed in this status bar.
     *
     * @param message The message to be displayed in this status bar.
	 */
	public void setMessage(String message) {
		messageLabel.setText(message);
	}

}
