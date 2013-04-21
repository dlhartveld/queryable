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

package com.hartveld.queryable.collections;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import com.hartveld.queryable.interactive.Enumerables;
import org.junit.Before;
import org.junit.Test;

public class ArrayListTest {

	private ArrayList<Object> list;

	@Before
	public void setUp() {
		this.list = new ArrayList<>();
	}

	@Test
	public void testThatSizeOfNewArrayListIsZero() {
		assertThat("",
				this.list.getSize(), is(0)
		);
	}

	@Test
	public void testThatFlatMapOnEmptyArrayListReturnsNewList() {
		final ArrayList<Object> result = this.list.flatMap(x -> Enumerables.empty());

		assertThat(
				"flatMap should return new empty instance",
				result, is(not(sameInstance(list)))
		);
	}

	@Test
	public void testThatFlatMapOnEmptyArrayListReturnsEmptyList() {
		final ArrayList<Object> result = this.list.flatMap(x -> Enumerables.empty());

		assertThat("",
				result.getSize(), is(0)
		);
	}

}