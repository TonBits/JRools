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

import java.util.List;

import org.apache.log4j.Logger;

import com.teedrei.rools.rulesengine.Facts;
import com.teedrei.rools.rulesengine.Rule;
import com.teedrei.rools.rulesengine.RulesEngineService;

/**
 * Rules Engine Service Implementation
 * 
 * @author edward.biton@gmail.com
 */
public class RulesEngineServiceImpl implements RulesEngineService
{
	/**
	 * A list of rules
	 */
	private List<Rule> rules;
	/**
	 * logger
	 */
	private static final Logger logger = Logger.getLogger(RulesEngineServiceImpl.class);

	/**
	 * Constructor
	 */
	public RulesEngineServiceImpl()
	{

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.teedrei.simplerulesengine.rulesengine.RulesEngineService#applyRules(com.teedrei.simplerulesengine.rulesengine
	 * .rules.Facts)
	 */
	@Override
	public void applyRules(Facts facts)
	{
		// for each of the top level rules, run the rules
		if (rules != null)
		{
			for (Rule rule : rules)
			{
				try
				{
					rule.applyRule(facts);
				}
				catch (Exception e)
				{
					logger.error("RulesEngineService: [" + rule.getName() + "] rule runtime error");
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @return the rules
	 */
	public List<Rule> getRules()
	{
		return rules;
	}

	/**
	 * @param rules
	 *            the rules to set
	 */
	public void setRules(List<Rule> rules)
	{
		this.rules = rules;
	}
}
