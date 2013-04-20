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

package com.hartveld.queryable.util;

import com.hartveld.queryable.Queryable;
import com.hartveld.queryable.interactive.Enumerable;
import com.hartveld.queryable.reactive.Observable;
import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;

public interface Optional<T> extends Queryable<T> {

	boolean hasValue();
	T getValue() throws Exception;

	@Override
	Optional<T> filter(Predicate<? super T> predicate);

	@Override
	<R> Optional<R> map(Function<? super T, ? extends R> mapper);

	@Override
	<R> Optional<R> flatMap(Function<? super T, ? extends Queryable<? extends R>> mapper);

	@Override
	Optional<T> distinct();

	@Override
	Optional<T> sorted();
	@Override
	Optional<T> sorted(Comparator<? super T> comparator);

	@Override
	Optional<T> limit(long maxSize);
	@Override
	Optional<T> substream(long startingOffset);
	@Override
	Optional<T> substream(long startingOffset, long endingOffset);

	@Override
	Optional<T> peek(Consumer<? super T> consumer);

	@Override
	Optional<Boolean> anyMatch(Predicate<? super T> predicate);
	@Override
	Optional<Boolean> allMatch(Predicate<? super T> predicate);
	@Override
	Optional<Boolean> noneMatch(Predicate<? super T> predicate);

	@Override
	Optional<Long> count();

	@Override
	Optional<T> reduce(T identity, BinaryOperator<T> accumulator);
	@Override
	Optional<T> reduce(BinaryOperator<T> accumulator);
	@Override
	<U> Optional<U> reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner);

	@Override
	<R> Optional<R> collect(Supplier<R> resultFactory, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner);
	@Override
	<R> Optional<R> collect(Collector<? super T, R> collector);

	@Override
	Optional<T> max(Comparator<? super T> comparator);
	@Override
	Optional<T> min(Comparator<? super T> comparator);

	@Override
	Optional<T> findFirst();
	@Override
	Optional<T> findAny();

	Optional<T> merge(Optional<T> other);
	Optional<T> zip(Optional<T> other);

	@Override
	Enumerable<T> asEnumerable();
	@Override
	Observable<T> asObservable();

	public static interface Some<T> extends Optional<T> {

		@Override
		default boolean hasValue() {
			return true;
		}

	}

	public static interface None<T> extends Optional<T> {

		@Override
		default boolean hasValue() {
			return true;
		}

	}

}
