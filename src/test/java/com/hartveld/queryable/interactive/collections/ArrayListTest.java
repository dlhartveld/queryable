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
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import com.hartveld.queryable.interactive.Enumerator;
import org.junit.Before;
import org.junit.Test;

public class ArrayListTest {

	private ArrayList<Object> list;

	@Before
	public void setUp() {
		this.list = new ArrayList<>();
	}

	@Test
	public void testThatArrayListWithThreeAddedObjectsHasSizeThree() {
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
	};

	@Test
	public void testThatArrayListEnumeratorReturnsJustAddedObjectsInOrder() {
		final Object [] o = new Object[3];

		o[0] = new Object();
		o[1] = new Object();
		o[2] = new Object();

		this.list.add(o[0]);
		this.list.add(o[1]);
		this.list.add(o[2]);

		final Enumerator<Object> enumerator = this.list.iterator();
		for (int i = 0; i < 3; i++) {
			assertThat(
					"Enumerator should have next value ready",
					enumerator.hasNext(), is(true)
			);

			final Object current = enumerator.next();
			assertThat(current, is(sameInstance(o[i])));
		}

		assertThat(
				"Enumerator should not have more values",
				enumerator.hasNext(), is(false)
		);
	}

	@Test
	public void testThatArrayListSizeAfterClearAfterAddingElementsIsZero() {
		list.add(new Object());
		list.add(new Object());
		list.add(new Object());

		list.clear();

		assertThat(
				"Cleared list should have zero size",
				list.getSize(), is(0l)
		);
	}

	@Test
	public void testThatArrayListDoesNotContainElementAfterClearAfterAddingElement() {
		final Object o = new Object();

		list.add(o);

		list.clear();

		assertThat(
				"Cleared list should not contain value o",
				list.contains(o), is(false)
		);
	}

}
