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

package com.hartveld.queryable;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collector;

public interface Queryable<T> {

	Queryable<T> filter(Predicate<? super T> predicate);

	<R> Queryable<R> map(Function<? super T, ? extends R> mapper);
	IntQueryable mapToInt(ToIntFunction<? super T> mapper);
	LongQueryable mapToLong(ToLongFunction<? super T> mapper);
	DoubleQueryable mapToDouble(ToDoubleFunction<? super T> mapper);

	<R> Queryable<R> flatMap(Function<? super T, ? extends Queryable<? extends R>> mapper);
	IntQueryable flatMapToInt(Function<? super T, ? extends IntQueryable> mapper);
	LongQueryable flatMapToLong(Function<? super T, ? extends LongQueryable> mapper);
	DoubleQueryable flatMapToDouble(Function<? super T, ? extends DoubleQueryable> mapper);

	Queryable<T> distinct();

	Queryable<T> sorted();
	Queryable<T> sorted(Comparator<? super T> comparator);

	Queryable<T> limit(long maxSize);
	Queryable<T> substream(long startingOffset);
	Queryable<T> substream(long startingOffset, long endingOffset);

	Queryable<T> peek(Consumer<? super T> consumer);

	boolean anyMatch(Predicate<? super T> predicate);
	boolean allMatch(Predicate<? super T> predicate);
	boolean noneMatch(Predicate<? super T> predicate);

	long count();

	Object[] toArray();
	<A> A[] toArray(IntFunction<A[]> generator);

	T reduce(T identity, BinaryOperator<T> accumulator);
	Optional<T> reduce(BinaryOperator<T> accumulator);
	<U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner);

	<R> R collect(Supplier<R> resultFactory, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner);
	<R> R collect(Collector<? super T, R> collector);

	Optional<T> max(Comparator<? super T> comparator);
	Optional<T> min(Comparator<? super T> comparator);

	Optional<T> findFirst();
	Optional<T> findAny();

}
