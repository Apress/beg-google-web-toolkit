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

import com.google.gwt.user.client.ui.TreeImages;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.core.client.GWT;

/**
 * The TreeImages that will be used by the category {@link com.google.gwt.user.client.ui.Tree tree) in the
 * {@link com.apress.beginninggwt.gwtasks.client.ui.mainpane.categorypane.CategoryPane category pane}.
 *
 * @author Uri Boness
 */
public interface CategoryTreeImages extends TreeImages {

    @Resource("category-opened.gif")
    AbstractImagePrototype treeOpen();

    @Resource("category-closed.gif")
    AbstractImagePrototype treeClosed();

    @Resource("category-leaf.gif")
    AbstractImagePrototype treeLeaf();


    /**
     * The Locator pattern.
     */
    public static class Impl {
        private static CategoryTreeImages instance;

        public static CategoryTreeImages getInstance() {
            if (instance == null) {
                instance = GWT.create(CategoryTreeImages.class);
            }
            return instance;
        }
    }

}
