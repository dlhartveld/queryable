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

import static com.google.common.base.Preconditions.checkNotNull;
import static com.hartveld.queryable.optional.Optional.none;
import static com.hartveld.queryable.optional.Optional.some;

import com.hartveld.queryable.Monad;
import com.hartveld.queryable.interactive.Enumerables;
import com.hartveld.queryable.interactive.Enumerator;
import com.hartveld.queryable.reactive.Observable;
import com.hartveld.queryable.reactive.Observables;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

class Some<T> implements Optional<T> {

	private final T value;

	Some(final T value) {
		checkNotNull(value, "value");

		this.value = value;
	}

	@Override
	public boolean hasValue() {
		return true;
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public <R> Optional<R> flatMap(final Function<? super T, ? extends Monad<? extends R>> mapper) {
		checkNotNull(mapper, "mapper");

		final Monad<? extends R> q = mapper.apply(this.value);

		if (q instanceof Optional) {
			return (Optional<R>) q;
		} else {
			throw new IllegalArgumentException("Mapper does not return Optional<R>");
		}
	}

	@Override
	public <R> Optional<R> map(final Function<? super T, ? extends R> mapper) {
		checkNotNull(mapper, "mapper");

		return some(mapper.apply(this.value));
	}

	@Override
	public Optional<T> reduce(final T identity, final BinaryOperator<T> accumulator) {
		checkNotNull(identity, "identity");
		checkNotNull(accumulator, "accumulator");

		final T result = accumulator.apply(identity, this.value);

		return some(result);
	}

	@Override
	public Optional<T> filter(final Predicate<? super T> predicate) {
		checkNotNull(predicate, "predicate");

		if (predicate.test(this.value)) {
			return this;
		} else {
			return none();
		}
	}

	@Override
	public Optional<T> peek(final Consumer<? super T> consumer) {
		checkNotNull(consumer, "consumer");

		consumer.accept(this.value);

		return this;
	}

	@Override
	public Enumerator<T> asEnumerator() {
		return Enumerables.single(this.value).iterator();
	}

	@Override
	public Observable<T> asObservable() {
		return Observables.single(value);
	}

}
