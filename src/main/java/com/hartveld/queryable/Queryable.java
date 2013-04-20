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

public interface Queryable<T> {

	Queryable<T> filter(Predicate<? super T> predicate);

	<R> Queryable<R> map(Function<? super T, ? extends R> mapper);

	<R> Queryable<R> flatMap(Function<? super T, ? extends Queryable<? extends R>> mapper);

	Queryable<T> distinct();

	Queryable<T> sorted();
	Queryable<T> sorted(Comparator<? super T> comparator);

	Queryable<T> limit(long maxSize);
	Queryable<T> substream(long startingOffset);
	Queryable<T> substream(long startingOffset, long endingOffset);

	Queryable<T> peek(Consumer<? super T> consumer);

	Queryable<Boolean> anyMatch(Predicate<? super T> predicate);
	Queryable<Boolean> allMatch(Predicate<? super T> predicate);
	Queryable<Boolean> noneMatch(Predicate<? super T> predicate);

	Queryable<Long> count();

	Queryable<T> reduce(T identity, BinaryOperator<T> accumulator);
	Queryable<T> reduce(BinaryOperator<T> accumulator);
	<U> Queryable<U> reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner);

	<R> Queryable<R> collect(Supplier<R> resultFactory, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner);
	<R> Queryable<R> collect(Collector<? super T, R> collector);

	Queryable<T> max(Comparator<? super T> comparator);
	Queryable<T> min(Comparator<? super T> comparator);

	Queryable<T> findFirst();
	Queryable<T> findAny();

	Queryable<T> merge(Queryable<T> other);
	Queryable<T> zip(Queryable<T> other);

	Enumerable<T> asEnumerable();
	Observable<T> asObservable();

}
