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

package com.apress.beginninggwt.gwtasks.client.support.async;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.Window;

/**
 * A simple {@link AsyncCallback} which shows all failure messages using the native alert dislog of the browser.
 *
 * @author Uri Boness
 */
public abstract class Callback<T> implements AsyncCallback<T> {

    /**
     * Shows the message of the given caught exception using a {@link Window#alert(String)}.
     *
     * @param caught The caught exception
     */
    public void onFailure(Throwable caught) {
        Window.alert(caught.getMessage());
    }

}
