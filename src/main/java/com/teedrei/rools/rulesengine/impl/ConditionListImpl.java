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
package com.teedrei.rools.rulesengine.impl;

import java.util.ArrayList;
import java.util.List;

import com.teedrei.rools.rulesengine.Condition;
import com.teedrei.rools.rulesengine.Facts;

/**
 * This is a simple implementation of condition that can join a list of conditions with a given operand (AND | OR)
 * 
 * @author edward.biton@gmail.com
 */
public class ConditionListImpl implements Condition
{
	public static final String AND = "and";
	public static final String OR = "or";
	/**
	 * Operand to use for joining the conditions. Defaults to AND operand
	 */
	private String operand = AND;
	/**
	 * List of conditions
	 */
	private List<Condition> conditions = new ArrayList<Condition>();

	/*
	 * (non-Javadoc)
	 * @see com.teedrei.rools.rulesengine.Condition#evaluate(com.teedrei.rools.rulesengine.Facts)
	 */
	@Override
	public boolean evaluate(Facts facts)
	{
		if (operand == null)
		{
			return false;
		}
		if (operand.equalsIgnoreCase(AND))
		{
			for (Condition condition : conditions)
			{
				if (condition.evaluate(facts) == false)
				{
					// AND condition: return false if one condition is false
					return false;
				}
			}
			// In an AND condition, all values must return true
			// if no value returned false, then we will reach this code
			// we return true, meaning all conditions were met
			return true;
		}
		else if (operand.equalsIgnoreCase(OR))
		{
			for (Condition condition : conditions)
			{
				if (condition.evaluate(facts) == true)
				{
					// OR condition: return true if one condition is true
					return true;
				}
			}
			// In an OR condition, at least one value must return true
			// if we reach this code, it means all conditions failed so we
			// return false
			return false;
		}
		return false;
	}

	/**
	 * @return the operand
	 */
	public String getOperand()
	{
		return operand;
	}

	/**
	 * @param operand
	 *            the operand to set
	 */
	public void setOperand(String operand)
	{
		// we ensure that the operand should be valid (and | or)
		// we throw a runtime exception so setting a wrong value in the
		// condition will no go undetected
		if (operand == null)
		{
			throw new RuntimeException("The ConditionListImpl requires a valid value of AND or OR");
		}
		// check if the operand is either AND or OR
		if ((operand.equalsIgnoreCase(AND) == true) || (operand.equalsIgnoreCase(OR) == true))
		{
			// the value is either AND or OR so we set the value
			this.operand = operand;
		}
		else
		{
			// the value is not AND and is not OR so we throw a Runtime
			// exception so this configuration
			// will be detected at deploy time
			throw new RuntimeException("The ConditionListImpl requires a valid value of AND or OR. The value being set is " + operand);
		}
	}

	/**
	 * @return the conditions
	 */
	public List<Condition> getConditions()
	{
		return conditions;
	}

	/**
	 * @param conditions
	 *            the conditions to set
	 */
	public void setConditions(List<Condition> conditions)
	{
		this.conditions = conditions;
	}

}
