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

package com.apress.beginninggwt.gwtasks.client.ui.image;

import com.google.gwt.user.client.ui.ImageBundle;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.core.client.GWT;

/**
 * The main image bundle of the GWTasks application.
 *
 * @author Uri Boness
 */
public interface GWTasksImages extends ImageBundle {

    @Resource("field-error.gif")
    AbstractImagePrototype fieldErrorIcon();

    @Resource("priority-high.png")
    AbstractImagePrototype highPriorityIcon();

    @Resource("priority-normal.png")
    AbstractImagePrototype normalPriorityIcon();

    @Resource("priority-low.png")
    AbstractImagePrototype lowPriorityIcon();

    @Resource("priority-disabled.png")
    AbstractImagePrototype disabledPriorityIcon();

    @Resource("edit.gif")
    AbstractImagePrototype editIcon();
    
    @Resource("english-flag.png")
    AbstractImagePrototype englishFlag();

    @Resource("dutch-flag.png")
    AbstractImagePrototype dutchFlag();

    /**
     * The Locator pattern.
     */
    public static class Impl {
        private static GWTasksImages instance;

        public static GWTasksImages getInstance() {
            if (instance == null) {
                instance = GWT.create(GWTasksImages.class);
            }
            return instance;
        }
    }

}
