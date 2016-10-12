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

import com.google.gwt.junit.client.GWTTestCase;

/**
 * @author Bram Smeets
 */
public class DefaultUIManagerTests extends GWTTestCase {

    private DefaultUIManager uiManager;

    public String getModuleName() {
        return "com.apress.beginninggwt.gwtasks.DefaultModule";
    }

    protected void gwtSetUp() throws Exception {
        super.gwtSetUp();

        uiManager = new DefaultUIManager(true);
    }

    /*public void tetShowDebugMessage() {
        uiManager.showDebugMessage("testing");
    }

    public void testShowErrorMessage() {
        uiManager.showErrorMessage("testing");
    }

    public void testShowInfoMessage() {
        uiManager.showInfoMessage("testing");
    }

    public void testShowConfirmMessage() {
        uiManager.showConfirmMessage("testing", new AsyncCallback<Boolean>() {
            public void onFailure(Throwable caught) {
                fail("the test should have succeeded");
            }

            public void onSuccess(Boolean result) {
                assertTrue(result);
            }
        });
    }*/

    public void testShowProgressIndicator() {
        uiManager.showProgressIndicator("testing");
    }

}
