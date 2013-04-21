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

package com.hartveld.queryable.optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import com.hartveld.queryable.interactive.Enumerable;
import com.hartveld.queryable.reactive.Observable;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

public class NoneTest {

	private None<Object> none;

	@Before
	public void setUp() {
		none = None.instance();
	}

	@Test
	public void testThatHasValueReturnsFalse() {
		assertThat("hasValue() incorrectly returns true",
				none.hasValue(), is(false)
		);
	}

	@Test(expected =  NoSuchElementException.class)
	public void testThatGetValueThrowsNoSuchElementException() {
		none.getValue();
	}

	@Test
	public void testThatFlatMapReturnsThisInstance() {
		final Optional<Object> result = none.flatMap(x -> Optional.some(new Object()));

		assertThatInstanceReturnedByMethodIsSameAsOriginalInstance("flatMap", result);
	}

	@Test
	public void testThatMapReturnsThisInstance() {
		final Optional<Object> result = none.map(x -> x);

		assertThatInstanceReturnedByMethodIsSameAsOriginalInstance("map", result);
	}

	@Test
	public void testThatReduceReturnsSomeOfIdentityInstance() {
		final Object id = new Object();

		final Optional<Object> result = none.reduce(id, (x, y) -> new Object());

		assertThat("reduce did not return Some of id",
				result.getValue(), is(sameInstance(id))
		);
	}

	@Test
	public void testThatFilterReturnsThisInstance() {
		final Optional<Object> result = none.filter(x -> true);

		assertThatInstanceReturnedByMethodIsSameAsOriginalInstance("filter", result);
	}

	@Test
	public void testThatPeekReturnsThisInstance() {
		final Optional<Object> result = none.peek(x -> { });

		assertThatInstanceReturnedByMethodIsSameAsOriginalInstance("peek", result);
	}

	@Test
	public void testThatAsEnumerableReturnsEmptyEnumerable() {
		final Enumerable<Object> enumerable = none.asEnumerable();

		for (final Object o : enumerable) {
			fail("Enumerable should have been empty, but it is not");
		}
	}

	@Test
	public void testThatAsObservableReturnsEmptyObservable() {
		final Observable<Object> observable = none.asObservable();

		observable.subscribe(o -> fail("Observable should have been empty, but it is not"));
	}

	private void assertThatInstanceReturnedByMethodIsSameAsOriginalInstance(final String function, final Optional<Object> returned) {
		assertThat(function + " did not return 'this' instance",
				returned, is(sameInstance(none))
		);
	}

}
