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

public interface ModifiableCollectionTest extends UnmodifiableCollectionTest {

	<T> ModifiableCollection<T> createModifiableCollection();

	default <T> ModifiableCollection<T> createModifiableCollectionWith(final T ... elements) {
		final ModifiableCollection<T> result = createModifiableCollection();

		for (final T element : elements) {
			result.add(element);
		}

		return result;
	}

	@Override
	default <T> ModifiableCollection<T> createUnmodifiableCollectionWith(final T ... elements) {
		return createModifiableCollectionWith(elements);
	}

	@Test
	default void testThatModifiableCollectionSizeAfterClearAfterAddingElementsIsZero() {
		final ModifiableCollection<Object> collection = createModifiableCollection();

		collection.add(new Object());
		collection.add(new Object());
		collection.add(new Object());

		collection.clear();

		assertThat(
				"Cleared collection should have zero size",
				collection.getSize(), is(0l)
		);
	}

	@Test
	default void testThatModifiableCollectionDoesNotContainElementsAfterClearAfterAddingElement() {
		final Object o1 = new Object();
		final Object o2 = new Object();
		final Object o3 = new Object();

		final ModifiableCollection<Object> collection = createModifiableCollectionWith(o1, o2, o3);

		collection.clear();

		assertThatCollectionDoesNotContainElement(collection, o1, "o1");
		assertThatCollectionDoesNotContainElement(collection, o2, "o2");
		assertThatCollectionDoesNotContainElement(collection, o2, "o3");
	}

	@Test
	default void testThatCollectionDoesNotContainSpecificElementAfterRemove() {
		final Object o1 = new Object();
		final Object o2 = new Object();
		final Object o3 = new Object();

		final ModifiableCollection<Object> collection = createModifiableCollectionWith(o1, o2, o3);

		collection.remove(o2);

		assertThatCollectionContainsElement(collection, o1, "o1");
		assertThatCollectionDoesNotContainElement(collection, o2, "o2");
		assertThatCollectionContainsElement(collection, o3, "o3");
	}

	default <T> void assertThatCollectionContainsElement(final ModifiableCollection<T> collection, final T element, final String name) {
		assertThat(
				"Collection should contain element " + name,
				collection.contains(element), is(true)
		);
	}

	default <T> void assertThatCollectionDoesNotContainElement(final ModifiableCollection<T> collection, final T element, final String name) {
		assertThat(
				"Collection should not contain element " + name,
				collection.contains(element), is(false)
		);
	}

}
