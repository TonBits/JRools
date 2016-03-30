/*
 * Copyright (c) 2016 www.teedrei.com.  All rights reserved.
 *
 * This program contains proprietary and confidential information and trade
 * secrets of teedrei. This program may not be duplicated, disclosed or
 * provided to any third parties without the prior written consent of
 * teedrei. Disassembling or decompiling of the software and/or reverse
 * engineering of the object code are prohibited.
 */
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

/**
 * Condition interface to determine if a rule actions must be executed
 * 
 * @author edward.biton@gmail.com
 */
public interface Condition
{
	/**
	 * This method is called to evaluate the facts. If it returns true, then the actions associated with the rule will
	 * be executed.
	 * 
	 * @param facts
	 *            is the contextual data when a condition is evaluated
	 * @return
	 */
	public boolean evaluate(Facts facts);
}
