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

import java.util.List;

/**
 * Rule service interface
 * 
 * @author edward.biton@gmail.com
 */
public interface Rule
{
	/**
	 * Interface method to get the rule name
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * Interface method to determine if rule is enabled
	 * 
	 * @return
	 */
	public boolean isEnabled();

	/**
	 * Interface method to enable or disable the rule
	 * 
	 * @return
	 */
	public void setEnabled(boolean enabled);

	/**
	 * Interface method for getting the condition
	 * 
	 * @return
	 */
	public Condition getCondition();

	/**
	 * Interface method for getting the list of actions for this rule
	 * 
	 * @return
	 */
	public List<Action> getActions();

	/**
	 * Interface method for getting the list of sub rules under this particular rule
	 * 
	 * @return
	 */
	public List<Rule> getRules();

	/**
	 * This is the service method executed by the Rules Engine to evaluate and apply a rule
	 * 
	 * @param facts
	 *            is the context data that contains the data for processing
	 */
	public void applyRule(Facts facts);
}
