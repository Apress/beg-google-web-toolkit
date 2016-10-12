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

package com.apress.beginninggwt.chap8.fileupload.client;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.http.client.*;
import com.google.gwt.core.client.GWT;

/**
 * @author Bram Smeets
 */
public class ProgressTimer extends Timer {

    private HTML statusLabel;
    private boolean running = false;

    public ProgressTimer(HTML statusLabel) {
        this.statusLabel = statusLabel;

        // make one call to the server to make sure we have a session
        retrieveCurrentProgressFromServer();
    }

    public void run() {
        if (!running) {
            return;
        }

        retrieveCurrentProgressFromServer();
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    protected void retrieveCurrentProgressFromServer() {
//        System.out.println("calling the server for the status");
        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, "upload");
        builder.setCallback(new RequestCallback() {
            public void onResponseReceived(Request request, Response response) {
                StringBuilder builder = new StringBuilder("reponse received: ");
                builder.append(response.getStatusCode()).append(" - ").append(response.getStatusText()).append("\n\t content: ").append(response.getText());
                System.out.println(builder.toString());

                statusLabel.setText(response.getText());
            }

            public void onError(Request request, Throwable t) {
                GWT.log("Unable to retrieve the current status from the server", t);
            }
        });

        try {
            builder.send();
        } catch (RequestException e) {
            GWT.log("An error occurred while connecting to the file upload monitor", e);
        }
    }

}
