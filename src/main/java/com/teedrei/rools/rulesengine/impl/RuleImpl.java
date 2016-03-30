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

import org.apache.log4j.Logger;

import com.teedrei.rools.rulesengine.AbstractRule;
import com.teedrei.rools.rulesengine.Action;
import com.teedrei.rools.rulesengine.Condition;
import com.teedrei.rools.rulesengine.Facts;
import com.teedrei.rools.rulesengine.Rule;

/**
 * A rule implementation that can be configured with Spring context injection
 * 
 * @author edward.biton@gmail.com
 */
public class RuleImpl extends AbstractRule
{
	/**
	 * Rule condition
	 */
	private Condition condition;
	/**
	 * Rule actions
	 */
	private List<Action> actions = new ArrayList<Action>();
	/**
	 * Subset Rules
	 */
	private List<Rule> rules = new ArrayList<Rule>();
	/**
	 * Logger
	 */
	private static Logger logger = Logger.getLogger(RuleImpl.class);

	/*
	 * (non-Javadoc)
	 * @see com.teedrei.rools.rulesengine.Rule#getCondition()
	 */
	@Override
	public Condition getCondition()
	{
		return condition;
	}

	/*
	 * (non-Javadoc)
	 * @see com.teedrei.rools.rulesengine.Rule#getActions()
	 */
	@Override
	public List<Action> getActions()
	{
		return actions;
	}

	/*
	 * (non-Javadoc)
	 * @see com.teedrei.rools.rulesengine.Rule#getRules()
	 */
	@Override
	public List<Rule> getRules()
	{
		return rules;
	}

	/*
	 * (non-Javadoc)
	 * @see com.teedrei.rools.rulesengine.Rule#applyRule(com.teedrei.rools.rulesengine.Facts)
	 */
	@Override
	public void applyRule(Facts facts)
	{
		logger.info("RULE: Applying Rule [" + this.getName() + "]");
		// first check if the rule has a condition
		Condition condition = this.getCondition();
		// if a condition is present, evaluate it, if condition is not present, then just execute the actions and sub
		// rules
		if (condition != null)
		{
			logger.info("RULE: [" + this.name + "] have a condition, evaluating the condition");
			try
			{
				boolean result = condition.evaluate(facts);
				if (result == false)
				{
					logger.info("RULE: [" + this.name + "] rule condition is not satisfied.");
					return;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				logger.error("RULE: [" + this.name + "] an exception occurred evaluating the rule condition");
				return;
			}
		}

		// at this point, either there was no condition specified or the condition evaluation is satisfied we start
		// executing the actions
		List<Action> actions = this.getActions();
		if (actions != null)
		{
			// execute each action
			for (Action action : actions)
			{
				try
				{
					logger.info("RULE: Executing [" + this.name + "] action [" + action.getClass().getName() + "]");
					action.execute(facts);
					logger.info("RULE: Executing [" + this.name + "] action [" + action.getClass().getName() + "] COMPLETED");
				}
				catch (Exception e)
				{
					e.printStackTrace();
					logger.error("RULE: Executing [" + this.name + "] action [" + action.getClass().getName() + "] FAILED");
				}
			}
		}
		// we have executed the actions, we will have to check if there are sub rules and run those rules as well
		List<Rule> rules = this.getRules();
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
					e.printStackTrace();
					logger.error("RULE: Applying [" + rule.getName() + "] FAILED");
				}
			}
		}

	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @param condition
	 *            the condition to set
	 */
	public void setCondition(Condition condition)
	{
		this.condition = condition;
	}

	/**
	 * @param actions
	 *            the actions to set
	 */
	public void setActions(List<Action> actions)
	{
		this.actions = actions;
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
