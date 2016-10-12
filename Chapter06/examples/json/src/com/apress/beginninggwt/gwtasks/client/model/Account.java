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

package com.apress.beginninggwt.gwtasks.client.model;

import java.io.Serializable;

/**
 * An account representing the registered users of the application.
 *
 * @author Uri Boness
 */
public class Account implements Serializable {

    private Long id;
    private String fullName;
	private String email;
	private String username;
	private String password;

	/**
	 * Default empty constructor.
	 */
	public Account() {
	    this(null, null, null, null);
    }

	public Account(Long id, Account account) {
        this(id, account.getFullName(), account.getEmail(), account.getUsername(), account.getPassword());
    }
	
	/**
	 * Constructs a new Account with a full name, email, username, and password.
	 *
	 * @param fullName The full name of the account.
	 * @param email The email of the account.
	 * @param username The username of the account.
	 * @param password The password of the account.
	 */
    public Account(String fullName, String email, String username, String password) {
        this(null, fullName, email, username, password);
    }

	/**
	 * Constructs a new Account with all needed information.
	 * 
	 * @param id The id of the account.
	 * @param fullName The full name of the account.
	 * @param email The email of the account.
	 * @param username The username of the account.
	 * @param password The password of the account.
	 */
    public Account(Long id, String fullName, String email, String username, String password) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.password = password;
    }


    //============================================== Setter/Getter =====================================================

	/**
	 * Returns the id of this account.
	 *
	 * @return The id of this account.
	 */
    public Long getId() {
        return id;
    }

	/**
	 * Sets the id of this account.
	 *
	 * @param id The id of this account.
	 */
    public void setId(Long id) {
        this.id = id;
    }

	/**
	 * Returns the full name of this account.
	 *
	 * @return The full name of this account.
	 */
    public String getFullName() {
		return fullName;
	}

	/**
	 * Sets the full name of this account.
	 *
	 * @param fullName The full name of this account.
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Returns the email of this account.
	 *
	 * @return The email of this account.
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
	 * @param password The password of this account.
	 */
	public void setPassword(String password) {
		this.password = password;
	}


    //============================================== Object Methods ====================================================

    public String toString() {
        return new StringBuffer("Account[")
                .append("id='").append(getId()).append("', ")
                .append("fullName='").append(getFullName()).append("', ")
                .append("email='").append(getEmail()).append("', ")
                .append("username='").append(getUsername()).append("'")
                .append("]")
                .toString();
	}

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (!email.equals(account.email)) return false;
        if (!fullName.equals(account.fullName)) return false;
        if (id != null ? !id.equals(account.id) : account.id != null) return false;
        if (!password.equals(account.password)) return false;
        if (!username.equals(account.username)) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (id != null ? id.hashCode() : 0);
        result = 31 * result + fullName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
