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

import org.junit.Test;

public interface UnmodifiableListTest extends UnmodifiableCollectionTest {

	abstract <T> UnmodifiableList<T> createUnmodifiableListWith(T ... elements);

	@Override
	default <T> UnmodifiableCollection<T> createUnmodifiableCollectionWith(final T ... elements) {
		return createUnmodifiableListWith(elements);
	}

	@Test
	default void testThatGetReturnsCorrectElements() {
		final Object o1 = new Object();
		final Object o2 = new Object();
		final Object o3 = new Object();

		final UnmodifiableList<Object> list = createUnmodifiableListWith(o1, o2, o3);

		assertThatElementAtIndexIsSameInstance(list, 0, o1, "o1");
		assertThatElementAtIndexIsSameInstance(list, 1, o2, "o2");
		assertThatElementAtIndexIsSameInstance(list, 2, o3, "o3");
	}

	default <T> void assertThatElementAtIndexIsSameInstance(final UnmodifiableList<T> list, final int index, final T element, final String name) {
		assertThat(
				"List element " + index + " is not the same instance as " + name,
				list.get(index), is(sameInstance(element))
		);
	}

}
