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

package com.hartveld.queryable.reactive.subjects;

import static org.mockito.Mockito.verify;

import com.hartveld.queryable.reactive.Observer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReplaySubjectTest {

	private ReplaySubject<Object> subject;

	@Mock
	private Observer<Object> observer;

	@Before
	public void setUp() {
		this.subject = new ReplaySubject<>();
	}

	@Test
	public void testThatTwoValuesAndCompletedAreReceveivedOnSubscription() throws Exception {
		final Object o1 = new Object();
		final Object o2 = new Object();

		this.subject.onNext(o1);
		this.subject.onNext(o2);
		this.subject.onCompleted();

		this.subject.subscribe(observer).close();

		verify(observer).onNext(o1);
		verify(observer).onNext(o2);
		verify(observer).onCompleted();
	}

}
