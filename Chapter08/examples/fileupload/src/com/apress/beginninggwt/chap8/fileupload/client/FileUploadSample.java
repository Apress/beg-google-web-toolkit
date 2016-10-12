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

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.Timer;

/**
 * @author Bram Smeets
 */
public class FileUploadSample implements EntryPoint {

    private ProgressTimer timer;

    private HTML statusLabel = new HTML("&nbsp;");

    public void onModuleLoad() {
        // create a form panel and set its properties correctly for file uploading
        final FormPanel form = new FormPanel();
        form.setEncoding(FormPanel.ENCODING_MULTIPART);
        form.setMethod(FormPanel.METHOD_GET);
        form.setAction("upload");

        // create a panel to hold the upload widget and submit button
        VerticalPanel panel = new VerticalPanel();
        form.setWidget(panel);

        FileUpload upload = new FileUpload();
        panel.add(upload);

        panel.add(new HTML("&nbsp;"));

        // Add a 'submit' button.
        panel.add(new Button("Submit", new ClickListener() {
            public void onClick(Widget sender) {
                form.submit();

                // start the timer that monitors the progress
//                new Timer() {
//                    public void run() {
//                        System.out.println("scheduling timer!");
//                        timer.setRunning(true);
//                    }
//                }.schedule(2000);
            }
        }));

        // Add an event handler to the form.
        form.addFormHandler(new FormHandler() {
            public void onSubmit(FormSubmitEvent event) {
                // do nothing
            }

            public void onSubmitComplete(FormSubmitCompleteEvent event) {
//                timer.setRunning(false);

                statusLabel.setHTML("Uploading finished!");
            }
        });

        RootPanel.get("content").add(form);

        RootPanel.get("status").add(statusLabel);
//        timer = new ProgressTimer(statusLabel);
//        timer.scheduleRepeating(1000);
    }

}
