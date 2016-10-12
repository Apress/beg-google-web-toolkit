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

package com.apress.beginninggwt.chap4.client;

/**
 * @author Uri Boness
 */
public class AccountInfo {

	private String fullName;
	private String email;
	private String username;
	private String password;

	/**
	 * Default empty constructor.
	 */
	public AccountInfo() {
		this(null, null);
	}
	
	/**
	 * Constructs an AccountInfo with a given username and password. The given username wll also be treated as the 
	 * full name of the account.
	 *
	 * @param username The username of the account
	 * @param password The password of the account
	 */
	public AccountInfo(String username, String password) {
		this(username, username, password);
	}
	
	/**
	 * Constructs an AccountInfo with a given full name, username and password. 
	 *
	 * @param fullName The full name of the account
	 * @param username The username of the account
	 * @param password The password of the account
	 */
	public AccountInfo(String fullName, String username, String password) {
		this(fullName, null, username, password);
	}
	
	/**
	 * Constructs an AccountInfo with all account information. 
	 *
	 * @param fullName The full name of the account
	 * @param email The email of the account.
	 * @param username The username of the account
	 * @param password The password of the account
	 */
	public AccountInfo(String fullName, String email, String username, String password) {
		this.fullName = fullName;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Returns the full name of this account
	 *
	 * @return The full name of this account.
	 */
	public String getFullName() {
		return fullName;
	}
	
	/**
	 * Sets the full name of this account
	 *
	 * @param fullName The full name of this account.
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	/**
	 * Returns the email of this account (may be <code>null</code>)
	 *
	 * @return The email of this account, or <code>null</code> when absent.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email of this account.
	 *
	 * @param email The email of this account.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Returns the username of this account.
	 *
	 * @return The username of this account.
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Sets the username of this account.
	 *
	 * @param username The username of this account.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Returns the password of this account.
	 *
	 * @return The password of this account.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password of this account.
	 *
	 * @param password The username of this account.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * {@inheritDocs}
	 */
	public String toString() {
		return new StringBuilder("AccountInfo[")
			.append("fullName:").append(fullName).append(",")
			.append("email:").append(email).append(",")
			.append("username:").append(username).append(",")
			.append("password:").append(password)
			.append("]")
			.toString();
	}
}
