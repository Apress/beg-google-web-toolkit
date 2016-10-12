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

package com.apress.beginninggwt.gwtasks.server.service;

import junit.framework.TestCase;
import com.apress.beginninggwt.gwtasks.client.model.Category;

import java.util.List;

/**
 * @author Bram Smeets
 */
public class RemoteTaskServiceImplTests extends TestCase {

    private RemoteTaskServiceImpl taskService;

    protected void setUp() throws Exception {
        taskService = new RemoteTaskServiceImpl();
    }

    public void testCreateCategory() {
        Category createdCategory = taskService.createCategory(new Category(), null);

        assertNotNull(createdCategory);
        assertNotNull(createdCategory.getId());

        List<Category> categories = taskService.getCategories();
        assertTrue(categories.contains(createdCategory));
    }

    public void testResolveParent() {
        assertNull(taskService.resolveParent(null));

        // create a category and check whether we can resolve it
        Category createdCategory = taskService.createCategory(new Category(), null);

        Category parent = taskService.resolveParent(createdCategory.getId());
        assertEquals(parent, createdCategory);
    }

    public void testUpdateCategory() {
        Category category = new Category(120L, "CategoryName");

        try {
            taskService.updateCategory(category);
            fail("an illegal argument exception was expected");
        } catch(IllegalArgumentException e) {
            // do nothing, was expected
        }

        // create a category and check whether we can update it
        Category createdCategory = taskService.createCategory(new Category(), null);
        taskService.updateCategory(createdCategory);
    }

}
