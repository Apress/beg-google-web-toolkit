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
 * A simple implementation of the {@link ManagerRegistry}.
 *
 * @author Uri Boness
 */
public class SimpleManagerRegistry implements ManagerRegistry {

    private final SecurityManager securityManager;
    private final DataManager dataManager;
    private final UIManager uiManager;

    /**
     * Creates a SimpleManagerRegistry with all required managers.
     *
     * @param securityManager The security manager.
     * @param dataManager The data manager.
     * @param uiManager The UI manager.
     */
    public SimpleManagerRegistry(SecurityManager securityManager, DataManager dataManager, UIManager uiManager) {
        this.securityManager = securityManager;
        this.dataManager = dataManager;
        this.uiManager = uiManager;
    }

    /**
     * {@inheritDoc}
     */
    public SecurityManager getSecurityManager() {
        return securityManager;
    }

    /**
     * {@inheritDoc}
     */
    public DataManager getDataManager() {
        return dataManager;
    }

    /**
     * {@inheritDoc}
     */
    public UIManager getUIManager() {
        return uiManager;
    }

}
