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
import static com.hartveld.queryable.reactive.Observers.observerWith;

import com.hartveld.queryable.Monad;
import com.hartveld.queryable.interactive.Enumerator;
import com.hartveld.queryable.reactive.Observable;
import com.hartveld.queryable.reactive.Observer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class AbstractSubject<T> implements Subject<T> {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractSubject.class);

	private final java.util.List<Subscription> subscriptions = new java.util.LinkedList<>();

	private boolean completed = false;

	@Override
	public AutoCloseable subscribe(final Consumer<? super T> onNext, final Consumer<Exception> onError, final Runnable onCompleted) {
		return subscribe(observerWith(onNext, onError, onCompleted));
	}

	@Override
	public AutoCloseable subscribe(final Observer<? super T> observer) {
		LOG.trace("subscribe(): {}", observer);

		checkNotNull(observer, "observer");

		final Subscription subscription = new Subscription(observer);

		onSubscribe(observer);

		synchronized(subscriptions) {
			subscriptions.add(subscription);
		}

		return subscription;
	}

	protected void onSubscribe(final Observer<? super T> observer) { }

	@Override
	public <R> Observable<R> flatMap(final Function<? super T, ? extends Monad<? extends R>> mapper) {
		throw new NotImplementedException();
	}

	@Override
	public <R> Observable<R> map(final Function<? super T, ? extends R> mapper) {
		throw new NotImplementedException();
	}

	@Override
	public Observable<T> reduce(final T identity, final BinaryOperator<T> accumulator) {
		throw new NotImplementedException();
	}

	@Override
	public Observable<T> filter(final Predicate<? super T> predicate) {
		throw new NotImplementedException();
	}

	@Override
	public Observable<T> peek(final Consumer<? super T> consumer) {
		throw new NotImplementedException();
	}

	@Override
	public Enumerator<T> asEnumerator() {
		throw new NotImplementedException();
	}

	@Override
	public Observable<T> asObservable() {
		return this;
	}

	@Override
	public void onNext(final T value) {
		LOG.trace("onNext(): {}", value);

		checkNotNull(value, "value");

		if (completed) {
			return;
		}

		synchronized(subscriptions) {
			subscriptions.removeIf(s -> notifyObserverOrRemove(s, value));
		}
	}

	@Override
	public void onError(final Exception exception) {
		throw new NotImplementedException();
	}

	@Override
	public void onCompleted() {
		LOG.trace("onCompleted()");

		if (completed) {
			return;
		}

		completed = true;

		synchronized(subscriptions) {
			subscriptions.forEach(s -> notifyObserverOfCompletion(s.observer));
			subscriptions.clear();
		}
	}

	private boolean notifyObserverOrRemove(final Subscription subscription, final T value) {
		LOG.trace("notifyObserverOrRemove: {} - {}", subscription, value);

		try {
			subscription.observer.onNext(value);
			return false;
		} catch (final Exception e) {
			LOG.warn("Caught an exception while notifying observer: {}", e.getMessage(), e);
			LOG.warn("Dropping observer: {}", subscription.observer);

			return true;
		}
	}

	private void notifyObserverOfCompletion(final Observer<? super T> observer) {
		LOG.trace("notifyObserverOfCompletion(): {}", observer);

		try {
			observer.onCompleted();
		} catch (final Exception e) {
			LOG.warn("While notifying observer of completion: {}", observer);
			LOG.warn("Caught an exception: {}", e.getMessage(), e);
		}
	}

	private class Subscription implements AutoCloseable {

		public final Observer<? super T> observer;

		private Subscription(final Observer<? super T> observer) {
			this.observer = observer;
		}

		@Override
		public void close() throws Exception {
			synchronized(subscriptions) {
				subscriptions.remove(this);
			}
		}

		@Override
		public String toString() {
			final ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
			builder.append("observer", observer);
			return builder.toString();
		}

	}

}
