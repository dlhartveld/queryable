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

package com.hartveld.queryable.reactive;

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

public interface Observable<T> extends Queryable<T> {

	AutoCloseable subscribe(Consumer<? extends T> onNext);
	AutoCloseable subscribe(Consumer<? extends T> onNext, Runnable onCompleted);
	AutoCloseable subscribe(Consumer<? extends T> onNext, Consumer<Exception> onError);
	AutoCloseable subscribe(Consumer<? extends T> onNext, Consumer<Exception> onError, Runnable onCompleted);

	AutoCloseable subscribe(Observer<? extends T> observer);

	// Problematic, because this must be Observable<Observable<T>>, which can only be checked at runtime.
	Observable<T> switchToNext();

	@Override
	Observable<T> filter(Predicate<? super T> predicate);

	@Override
	<R> Observable<R> map(Function<? super T, ? extends R> mapper);

	@Override
	<R> Observable<R> flatMap(Function<? super T, ? extends Queryable<? extends R>> mapper);

	@Override
	Observable<T> distinct();

	@Override
	Observable<T> sorted();
	@Override
	Observable<T> sorted(Comparator<? super T> comparator);

	@Override
	Observable<T> limit(long maxSize);
	@Override
	Observable<T> substream(long startingOffset);
	@Override
	Observable<T> substream(long startingOffset, long endingOffset);

	@Override
	Observable<T> peek(Consumer<? super T> consumer);

	@Override
	Observable<Boolean> anyMatch(Predicate<? super T> predicate);
	@Override
	Observable<Boolean> allMatch(Predicate<? super T> predicate);
	@Override
	Observable<Boolean> noneMatch(Predicate<? super T> predicate);

	@Override
	Observable<Long> count();

	@Override
	Observable<T> reduce(T identity, BinaryOperator<T> accumulator);
	@Override
	Observable<T> reduce(BinaryOperator<T> accumulator);
	@Override
	<U> Observable<U> reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner);

	@Override
	<R> Observable<R> collect(Supplier<R> resultFactory, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner);
	@Override
	<R> Observable<R> collect(Collector<? super T, R> collector);

	@Override
	Observable<T> max(Comparator<? super T> comparator);
	@Override
	Observable<T> min(Comparator<? super T> comparator);

	@Override
	Observable<T> findFirst();
	@Override
	Observable<T> findAny();

	@Override
	Observable<T> merge(Queryable<T> other);
	Observable<T> merge(Observable<T> other);

	@Override
	Observable<T> zip(Queryable<T> other);
	Observable<T> zip(Observable<T> other);

}
