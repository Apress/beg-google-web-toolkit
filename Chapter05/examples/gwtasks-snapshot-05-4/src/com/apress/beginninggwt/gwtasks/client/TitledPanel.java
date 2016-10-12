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

package com.apress.beginninggwt.gwtasks.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.*;

/**
 * @author Uri Boness
 */
public class TitledPanel extends Composite {

	private final static int TITLE_ROW = 0;
	private final static int CONTENT_ROW = 1;

    private final static String STYLE_NAME = "TitledPanel";
	private final static String LABEL_STYLE_NAME = "TitleText";
	private final static String CONTENT_STYLE_NAME = "Content";

	private Label titleLabel;
	private Grid grid;

	public TitledPanel() {
		this("");
	}
	
	public TitledPanel(String titleText) {
		this(titleText, null);
	}

	public TitledPanel(String titleText, Widget content) {
		titleLabel = new Label(titleText);
        titleLabel.setStyleName(LABEL_STYLE_NAME);

        grid = new Grid(2, 1);
        grid.setBorderWidth(0);
        grid.setCellPadding(0);
        grid.setCellSpacing(0);
        grid.setWidget(TITLE_ROW, 0, titleLabel);
        grid.getCellFormatter().setWidth(TITLE_ROW, 0, "100%");
        if (content != null) {
        	grid.setWidget(CONTENT_ROW, 0, content);
        }
        grid.getCellFormatter().setWidth(CONTENT_ROW, 0, "100%");
        grid.getCellFormatter().setHeight(CONTENT_ROW, 0, "100%");
        grid.getCellFormatter().setStyleName(CONTENT_ROW, 0, CONTENT_STYLE_NAME);
        
        initWidget(grid);
        setStyleName(STYLE_NAME);
	}
	
	public void setTitleText(String text) {
		titleLabel.setText(text);
	}
	
	public void setContent(Widget content) {
		grid.setWidget(CONTENT_ROW, 0, content);
	}
	
	public void setContentVerticalAlignment(HasVerticalAlignment.VerticalAlignmentConstant alignment) {
		grid.getCellFormatter().setVerticalAlignment(CONTENT_ROW, 0, alignment);
	}

}

