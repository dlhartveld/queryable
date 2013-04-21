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
import static org.junit.Assert.assertThat;

import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ArrayListEnumeratorTest {

	private ArrayListEnumerator<Object> enumerator;

	@Mock
	private ArrayList<Object> source;

	@Before
	public void setUp() {
		this.enumerator = new ArrayListEnumerator<>(source);
	}

	@Test
	public void testThatHasNextForEmptySourceReturnsFalse() {
		this.enumerator = new ArrayListEnumerator<>(new ArrayList<>());

		assertThat(
				"hasNext() should not return true for an empty ArrayList",
				this.enumerator.hasNext(), is(false)
		);
	}

	@Test
	public void testThatNextForEmptySourceThrowsNoSuchElementException() {
		this.enumerator = new ArrayListEnumerator<>(new ArrayList<>());

		boolean exceptionWasThrown = false;

		try {
			this.enumerator.next();
		} catch (final NoSuchElementException e) {
			exceptionWasThrown = true;
		}

		assertThat(
				"next() should throw exception for empty source ArrayList",
				exceptionWasThrown, is(true)
		);
	}

}
