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

package com.apress.beginninggwt.gwtasks.client.manager;

import com.apress.beginninggwt.gwtasks.client.manager.security.SecurityManager;
import com.apress.beginninggwt.gwtasks.client.manager.data.DataManager;
import com.apress.beginninggwt.gwtasks.client.manager.ui.UIManager;

/**
 * A registry of all available managers.
 *
 * @author Uri Boness
 */
public interface ManagerRegistry {

    /**
     * Returns the security manager of this application.
     *
     * @return The security manager of this application.
     */
    SecurityManager getSecurityManager();

    /**
     * Returns the data manager of this application.
     *
     * @return The data manager of this application.
     */
    DataManager getDataManager();

    /**
     * Returns the UI manager of this application.
     *
     * @return The UI manager of this application.
     */
    UIManager getUIManager();

}
