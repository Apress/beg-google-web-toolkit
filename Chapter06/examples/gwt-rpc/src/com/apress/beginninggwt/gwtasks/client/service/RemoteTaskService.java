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

package com.apress.beginninggwt.gwtasks.client.service;

import com.apress.beginninggwt.gwtasks.client.model.Task;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import java.util.List;

/**
 * @author Roald Bankras
 */
public interface RemoteTaskService extends RemoteService {

    //============================================== Category Methods ==================================================


    /**
     * Returns all tasks associated with the specified cateogry.
     *
     * @return a list of all tasks associated with the specified category.
     */
    List<Task> getTasks();


    public static final class Locator {
        private static RemoteTaskServiceAsync service;

        public static RemoteTaskServiceAsync getInstance() {
            if(service == null) {
                service = GWT.create(RemoteTaskService.class);
                ((ServiceDefTarget)service).setServiceEntryPoint(GWT.getModuleBaseURL() + "taskService");
            }
            return service;
        }
    }
}
