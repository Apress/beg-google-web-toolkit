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
 
package com.apress.beginninggwt.gwtasks.client.manager.ui;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.apress.beginninggwt.gwtasks.client.support.widget.ProgressIndicator;

/**
 * Manages all common & global aspects of the UI.
 *
 * @author Uri Boness
 */
public interface UIManager {

    /**
     * Shows a debug message.
     *
     * @param message The debug message to show.
     */
    void showDebugMessage(String message);

    /**
     * Shows an error message.
     *
     * @param message The error message to show.
     */
    void showErrorMessage(String message);

    /**
     * Shows an info. message.
     *
     * @param message The info. message to show.
     */
    void showInfoMessage(String message);

    /**
     * Show a confirmation dialog. The given callback is called based on the user action.
     *
     * @param message The message to show on the confirmation dialog.
     * @param callback Called with <code>true</code> if the user confirmed, <code>false</code> otherwise.
     */
    void showConfirmMessage(String message, AsyncCallback<Boolean> callback);

    /**
     * Creates and shows a progress indicator with the given message.
     *
     * @param message The message showed on the progress indicator.
     * @return The created progress indicator.
     */
    ProgressIndicator showProgressIndicator(String message);
}
