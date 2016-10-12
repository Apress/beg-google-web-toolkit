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

package com.apress.beginninggwt.chap5.popup.client;

import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.*;

/**
 * A progress indicator that shows a message on the top-center of the screen.
 *
 * @author Uri Boness
 */
public class MessageProgressIndicator extends PopupPanel {

	private Label messageLabel;
	
	/**
	 * Constructs and shows a new MessageProgressIndicator with "Loading..." as a default message.
	 */
	public MessageProgressIndicator() {
		this("Loading...");
	}
	
	/**
	 * Constructs and shows a new MessageProgressIndicator with the given message.
	 */
	public MessageProgressIndicator(String message) {
		super(false, true);
		messageLabel = new Label(message);
		messageLabel.setStyleName("Label");
		setPopupPositionAndShow(new PositionCallback() {
            public void setPosition(int offsetWidth, int offsetHeight) {
                int x = Window.getClientWidth()/2 - offsetWidth/2;
                setPopupPosition(x, 0);
            }
        });
        setWidget(messageLabel);
        setStyleName("MessageProgressIndicator");
	}
	
	/**
	 * Sets the message to be displayed by this progress indicator.
	 */
	public void setMessage(String message) {
		messageLabel.setText(message);
	}

}
