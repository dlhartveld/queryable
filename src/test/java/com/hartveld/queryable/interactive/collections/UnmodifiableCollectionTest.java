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

import org.junit.Test;

public interface UnmodifiableCollectionTest {

	<T> UnmodifiableCollection<T> createUnmodifiableCollectionWith(T ... elements);

	@Test
	default void testThatUnmodifiableCollectionWithThreeObjectsHasSizeThree() {
		final UnmodifiableCollection<Object> collection = createUnmodifiableCollectionWith(new Object(), new Object(), new Object());

		assertThat(
				"Collection should have size 3",
				collection.getSize(), is(3)
		);
	}

	@Test
	default void testThatUnmodifiableCollectionContainsSpecificElement() {
		final Object o1 = new Object();
		final Object o2 = new Object();
		final Object o3 = new Object();

		final UnmodifiableCollection<Object> collection = createUnmodifiableCollectionWith(o1, o2, o3);

		assertThat(
				"Collection should contain object o2",
				collection.contains(o2), is(true)
		);
	}

}
