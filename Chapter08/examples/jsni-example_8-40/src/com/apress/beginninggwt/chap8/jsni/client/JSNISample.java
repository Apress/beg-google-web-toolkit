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

package com.apress.beginninggwt.chap8.jsni.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * A simple example of JSNI usage
 *
 * @author Uri Boness
 */
public class JSNISample implements EntryPoint {

	private Person person = new Person("Lian");

	private JavaScriptObject car;

	public void onModuleLoad() {
		
		VerticalPanel panel = new VerticalPanel();
		
		// enabling the user to display the person
		CaptionPanel captionPanel = new CaptionPanel("Displaying The Person");
		Button displayPersonButton = new Button("Display Person");
		displayPersonButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				Example2.displayPerson(person);		
			}	
		});
		captionPanel.setContentWidget(displayPersonButton);
		panel.add(captionPanel);
		addGap(panel, "30px");
		
		// enabling the user to modify the person's name
		captionPanel = new CaptionPanel("Changing The Name Of The Person");
		final TextBox personNameField = new TextBox();
		Button modifyPersonButton = new Button("Change Person's Name");
		modifyPersonButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				person = Example2.modifyName(person, personNameField.getText());
				personNameField.setText("");
			}
		});
		HorizontalPanel row = new HorizontalPanel();		
		row.add(personNameField);
		row.add(modifyPersonButton);
		captionPanel.setContentWidget(row);
		panel.add(captionPanel);
		addGap(panel, "30px");
		
		// enabling the user to sum two number
		captionPanel = new CaptionPanel("Sum Two Numbers");
		final TextBox firstNumberField = new TextBox();
		final TextBox secondNumberField = new TextBox();
		final Label resultLabel = new Label();
		Button sumButton = new Button("Compute");
		sumButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				if (firstNumberField.getText().length() == 0) {
					firstNumberField.setText("0");
				}
				if (secondNumberField.getText().length() == 0) {
					secondNumberField.setText("0");
				}
				int firstNumber = Integer.valueOf(firstNumberField.getText());
				int secondNumber = Integer.valueOf(secondNumberField.getText());
				int sum = Example2.sum(firstNumber, secondNumber);
				resultLabel.setText(String.valueOf(sum));
			}
		});
		row = new HorizontalPanel();
		row.add(firstNumberField);
		addGap(row, "10px");
		row.add(new Label(" + "));
		addGap(row, "10px");
		row.add(secondNumberField);
		row.add(new Label(" = "));
		row.add(resultLabel);
		addGap(row, "10px");
		row.add(sumButton);
		captionPanel.setContentWidget(row);
		panel.add(captionPanel);
		addGap(panel, "30px");
		
		// enabling the user to create a car with a given name
		captionPanel = new CaptionPanel("Creating A Car");
		final TextBox carNameField = new TextBox();
		Button createCarButton = new Button("Create Car");
		createCarButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				car = Example2.createCar(carNameField.getText());
				carNameField.setText("");
			}
		});
		row = new HorizontalPanel();
		row.add(new Label("Enter Car Name: "));
		row.add(carNameField);
		row.add(createCarButton);
		captionPanel.setContentWidget(row);
		panel.add(captionPanel);
		addGap(panel, "30px");

		// enabling the user to display the car (will display null if the car wasn't created)		
		captionPanel = new CaptionPanel("Displaying A Car");
		Button displayCarButton = new Button("Display Car");
		displayCarButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				if (car == null) {
					Window.alert("First create a car, then display it!");
				} else {
					Example2.displayCar(car);
				}
			}
		});
		captionPanel.setContentWidget(displayCarButton);
		panel.add(captionPanel);
		addGap(panel, "30px");
		
		RootPanel.get().add(panel);
		
	}
	
	protected void addGap(VerticalPanel panel, String height) {
		Label label = new Label();
		panel.add(label);
		panel.setCellHeight(label, height);
	}
	
	protected void addGap(HorizontalPanel panel, String width) {
		Label label = new Label();
		panel.add(label);
		panel.setCellWidth(label, width);
	}
	
}
