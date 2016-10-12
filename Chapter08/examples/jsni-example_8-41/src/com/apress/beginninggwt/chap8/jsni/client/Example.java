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

import com.google.gwt.user.client.Window;

/**
 * @author Uri Boness
 * @author Jelmer Kuperus
 */
public class Example {

	public static native void sayGoodbye(String name) /*-{
		@com.apress.beginninggwt.chap8.jsni.client.Example::say(Ljava/lang/String;)("Goodbye " + name);
	}-*/;

	public static void say(String text) {
		Window.alert(text);
	}
}
