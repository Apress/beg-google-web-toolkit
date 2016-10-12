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

package com.apress.beginninggwt.gwtasks.client.ui.i18n;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.core.client.GWT;

/**
 * @author Uri Boness
 */
public interface GWTasksMessages extends Messages {

    //============================================== Error Messages ====================================================

    @Key("error.minLength")
    String minLengthError(int length);
    

    //============================================== Event Messages ====================================================

    @Key("event.categorySelected.description")
    String categorySelectedEventDescription(String categoryName);

    @Key("event.categoryCreated.description")
    String categoryCreatedEventDescription(String categoryName);

    @Key("event.taskSelected.description")
    String taskSelectedEventDescription(String taskTitle);

    @Key("event.taskCreated.description")
    String taskCreatedEventDescription(String taskTitle);

    @Key("event.login.description")
    String loginEventDescription(String username);


    /**
     * The Locator pattern
     */
    public static class Impl {

        private static GWTasksMessages instance;

        public static GWTasksMessages getInstance() {
            if (instance == null) {
                instance = GWT.create(GWTasksMessages.class);
            }
            return instance;
        }
    }
}
