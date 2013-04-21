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

import com.hartveld.queryable.Monad;
import com.hartveld.queryable.Queryable;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Observable<T> extends Queryable<T> {

	default AutoCloseable subscribe(Consumer<? extends T> onNext) {
		return subscribe(onNext, ex ->  { }, () -> { });
	}

	default AutoCloseable subscribe(Consumer<? extends T> onNext, Runnable onCompleted) {
		return subscribe(onNext, ex -> { }, onCompleted);
	}

	default AutoCloseable subscribe(Consumer<? extends T> onNext, Consumer<Exception> onError) {
		return subscribe(onNext, onError, () -> { });
	}

	AutoCloseable subscribe(Consumer<? extends T> onNext, Consumer<Exception> onError, Runnable onCompleted);

	default AutoCloseable subscribe(final Observer<? super T> observer) {
		return subscribe(observer::onNext, observer::onError, observer::onCompleted);
	}

	@Override
	<R> Observable<R> flatMap(Function<? super T, ? extends Monad<? extends R>> mapper);

	@Override
	<R> Observable<R> map(Function<? super T, ? extends R> mapper);

	@Override
	Observable<T> reduce(T identity, BinaryOperator<T> accumulator);

	@Override
	Observable<T> filter(Predicate<? super T> predicate);

	@Override
	Observable<T> peek(Consumer<? super T> consumer);

}
