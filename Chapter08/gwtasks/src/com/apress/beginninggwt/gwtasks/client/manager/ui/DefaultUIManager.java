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
import com.google.gwt.user.client.Window;
import com.apress.beginninggwt.gwtasks.client.support.widget.GoogleLikeProgressIndicator;
import com.apress.beginninggwt.gwtasks.client.support.widget.ProgressIndicator;

/**
 * A default implemenation of {@link UIManager}.
 *
 * @author Uri BOness
 */
public class DefaultUIManager implements UIManager {

    private final boolean debugMode;

    /**
     * Constructs a new DefaultUIManager.
     *
     * @param debugMode Determines whether debug messages should be shown or not.
     */
    public DefaultUIManager(boolean debugMode) {
        this.debugMode = debugMode;
    }

    /**
     * {@inheritDoc}
     */
    public void showDebugMessage(String message) {
        if (debugMode) {
            Window.alert(message);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void showErrorMessage(String message) {
        Window.alert(message);
    }

    /**
     * {@inheritDoc}
     */
    public void showInfoMessage(String message) {
        Window.alert(message);
    }

    /**
     * {@inheritDoc}
     */
    public void showConfirmMessage(String message, AsyncCallback<Boolean> callback) {
        boolean result = Window.confirm(message);
        callback.onSuccess(result);
    }

    /**
     * {@inheritDoc}
     */
    public ProgressIndicator showProgressIndicator(String message) {
        return new GoogleLikeProgressIndicator(message);
    }

}
