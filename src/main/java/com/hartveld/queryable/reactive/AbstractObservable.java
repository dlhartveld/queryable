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
import com.hartveld.queryable.interactive.Enumerable;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.commons.lang.NotImplementedException;

abstract class AbstractObservable<T> implements Observable<T> {

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
	public Enumerable<T> asEnumerable() {
		throw new NotImplementedException();
	}

	@Override
	public Observable<T> asObservable() {
		return this;
	}

}
