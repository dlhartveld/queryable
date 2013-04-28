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

import static com.google.common.base.Preconditions.checkNotNull;

import com.hartveld.queryable.reactive.Observer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReplaySubject<T> extends AbstractSubject<T> implements Subject<T> {

	private static final Logger LOG = LoggerFactory.getLogger(ReplaySubject.class);

	private final java.util.List<T> values = new java.util.ArrayList<>();

	private Exception exception = null;
	private boolean completed = false;

	@Override
	protected void onSubscribe(final Observer<? super T> observer) {
		LOG.trace("onSubscribe(): {}", observer);

		checkNotNull(observer, "observer");

		LOG.trace("Playing back values ...");
		values.stream().forEach((value) -> observer.onNext(value));

		if (completed) {
			LOG.trace("Playing back completion ...");
			observer.onCompleted();
		} else if (exception != null) {
			LOG.trace("Playing back error ...");
			observer.onError(exception);
		}
	}

	@Override
	public void onNext(final T value) {
		LOG.trace("onNext(): {}", value);

		checkNotNull(value, "value");

		if (isStopped()) {
			return;
		}

		values.add(value);

		super.onNext(value);
	}

	@Override
	public void onError(final Exception exception) {
		LOG.trace("onError(): {}" , exception);

		if (isStopped()) {
			return;
		}

		this.exception = exception;

		super.onError(exception);
	}

	@Override
	public void onCompleted() {
		LOG.trace("onCompleted()");

		if (isStopped()) {
			return;
		}

		this.completed = true;

		super.onCompleted();
	}

	private boolean isStopped() {
		return completed || exception != null;
	}

}
