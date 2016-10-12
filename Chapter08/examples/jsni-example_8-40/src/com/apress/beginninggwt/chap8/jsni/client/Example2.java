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

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author Uri Boness
 * @author Jelmer Kuperus
 */
public class Example2 {

	public static native Person displayPerson(Person person) /*-{
		$wnd.alert('Name: ' + person.@com.apress.beginninggwt.chap8.jsni.client.Person::name);
	}-*/;

	public static native Person modifyName(Person person, String name) /*-{
		person.@com.apress.beginninggwt.chap8.jsni.client.Person::name = name;
		return person;
	}-*/;
	
	public static native int sum(int val1, int val2) /*-{
		return val1 + val2;
	}-*/;
	
	public static native JavaScriptObject createCar(String name) /*-{
		function Car(name) {
			this.name = name;
		}
		return new Car(name);
	}-*/;
	
	public static native void displayCar(JavaScriptObject car) /*-{
		$wnd.alert(car.name);
	}-*/;
}
