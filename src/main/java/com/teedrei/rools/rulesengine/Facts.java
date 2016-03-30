/*
 * Copyright (c) 2016 www.teedrei.com
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software
 * is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.teedrei.rools.rulesengine;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Facts are execution context data when the rules are being evaluated and actions are being performed. The facts can
 * contain any type of information. The execution pipeline can add and modify data in the Facts object.
 * 
 * @author edward.biton@gmail.com
 */
public class Facts implements Serializable
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8187215662686941535L;

	/**
	 * A generic hash map that can store key value pairs
	 */
	private Map<String, Object> data = new HashMap<String, Object>();

	/**
	 * Constructor
	 */
	public Facts()
	{
	}

	/**
	 * Add a generic key value pair to the facts object. This will override if the key already exists
	 * 
	 * @param key
	 *            key for the data
	 * @param value
	 *            data value
	 */
	public void set(String key, Object value)
	{
		data.put(key, value);
	}

	/**
	 * Get a value from the given the key
	 * 
	 * @param key
	 *            key of the data
	 */
	public Object get(String key)
	{
		return data.get(key);
	}

	/**
	 * Remove the data identified by the key
	 * 
	 * @param key
	 *            key of the data to be remove
	 */
	public void remove(String key)
	{
		data.remove(key);
	}
}
