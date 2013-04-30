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


import org.junit.Test;

public interface ModifiableListTest extends ModifiableCollectionTest, UnmodifiableListTest {

	<T> ModifiableList<T> createModifiableList();

	default <T> ModifiableList<T> createModifiableListWith(final T ... elements) {
		final ModifiableList<T> list = createModifiableList();

		for (final T element : elements) {
			list.add(element);
		}

		return list;
	}

	@Override
	default <T> ModifiableList<T> createUnmodifiableListWith(final T ... elements) {
		return createModifiableListWith(elements);
	}

	@Override
	default <T> ModifiableList<T> createModifiableCollection() {
		return createModifiableList();
	}

	@Override
	default <T> ModifiableList<T> createUnmodifiableCollectionWith(final T ... elements) {
		return createUnmodifiableListWith(elements);
	}

	@Test
	default void testThatSizeOfModifiableListAfterIndexedRemovalOfOneOfThreeElementsIsTwo() {
		final Object o1 = new Object();
		final Object o2 = new Object();
		final Object o3 = new Object();

		final ModifiableList<Object> list = createModifiableListWith(o1, o2, o3);

		list.remove(2);

		assertThatCollectionHasSize(list, 2);
	}

	@Test
	default void testThatModifiableListDoesNotContainElementAfterIndexedRemoval() {
		final Object o1 = new Object();
		final Object o2 = new Object();
		final Object o3 = new Object();

		final ModifiableList<Object> list = createModifiableListWith(o1, o2, o3);

		list.remove(1);

		assertThatCollectionDoesNotContainElement(list, o2, "o2");
	}

	@Test
	default void testThatModifiableListContainsOtherTwoElementsAfterIndexedRemovalOfThird() {
		final Object o1 = new Object();
		final Object o2 = new Object();
		final Object o3 = new Object();

		final ModifiableList<Object> list = createModifiableListWith(o1, o2, o3);

		list.remove(1);

		assertThatCollectionContainsElement(list, o1, "o1");
		assertThatCollectionContainsElement(list, o3, "o3");
	}

	@Test
	default void testThatIndexOfThirdElementInModifiableListIsDecrementedAfterIndexedRemovalOfSecondElement() {
		final Object o1 = new Object();
		final Object o2 = new Object();
		final Object o3 = new Object();

		final ModifiableList<Object> list = createModifiableListWith(o1, o2, o3);

		list.remove(1);

		assertThatElementAtIndexIsSameInstance(list, 1, o3, "o3");
	}

}
