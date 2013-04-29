/*
 * Copyright (c) 2013 David Hartveld
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.hartveld.queryable.interactive.collections;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ArrayListUnmodifiableCollectionTest {

	private ArrayList<Object> list;

	@Before
	public void setUp() {
		list = new ArrayList<>();
	}

	@Test
	public void testThatArrayListWithThreeObjectsHasSizeThree() {
		this.list.add(new Object());
		this.list.add(new Object());
		this.list.add(new Object());

		assertThat(
				"ArrayList should have size 3",
				this.list.getSize(), is(3l)
		);
	}

	@Test
	public void testThatArrayListContainsJustAddedElement() {
		final Object o = new Object();

		list.add(o);

		assertThat(
				"List should contain added object o",
				list.contains(o), is(true)
		);
	}

}
