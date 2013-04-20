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

package com.hartveld.queryable.interactive;

import com.hartveld.queryable.Queryable;
import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;

public interface Enumerable<T> extends Queryable<T> {

	void forEach(Consumer<? super T> consumer);

	@Override
	Enumerable<T> filter(Predicate<? super T> predicate);

	@Override
	<R> Enumerable<R> map(Function<? super T, ? extends R> mapper);

	@Override
	<R> Enumerable<R> flatMap(Function<? super T, ? extends Queryable<? extends R>> mapper);

	@Override
	Enumerable<T> distinct();

	@Override
	Enumerable<T> sorted();
	@Override
	Enumerable<T> sorted(Comparator<? super T> comparator);

	@Override
	Enumerable<T> limit(long maxSize);
	@Override
	Enumerable<T> substream(long startingOffset);
	@Override
	Enumerable<T> substream(long startingOffset, long endingOffset);

	@Override
	Enumerable<T> peek(Consumer<? super T> consumer);

	@Override
	Enumerable<Boolean> anyMatch(Predicate<? super T> predicate);
	@Override
	Enumerable<Boolean> allMatch(Predicate<? super T> predicate);
	@Override
	Enumerable<Boolean> noneMatch(Predicate<? super T> predicate);

	@Override
	Enumerable<Long> count();

	@Override
	Enumerable<T> reduce(T identity, BinaryOperator<T> accumulator);
	@Override
	Enumerable<T> reduce(BinaryOperator<T> accumulator);
	@Override
	<U> Enumerable<U> reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner);

	@Override
	<R> Enumerable<R> collect(Supplier<R> resultFactory, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner);
	@Override
	<R> Enumerable<R> collect(Collector<? super T, R> collector);

	@Override
	Enumerable<T> max(Comparator<? super T> comparator);
	@Override
	Enumerable<T> min(Comparator<? super T> comparator);

	@Override
	Enumerable<T> findFirst();
	@Override
	Enumerable<T> findAny();

	@Override
	Enumerable<T> merge(Queryable<T> other);
	Enumerable<T> merge(Enumerable<T> other);

	@Override
	Enumerable<T> zip(Queryable<T> other);
	Enumerable<T> zip(Enumerable<T> other);

}
