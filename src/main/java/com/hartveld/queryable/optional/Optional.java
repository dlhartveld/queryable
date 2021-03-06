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

import com.google.common.base.Preconditions;
import com.hartveld.queryable.Monad;
import com.hartveld.queryable.Queryable;
import java.util.NoSuchElementException;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Optional<T> extends Queryable<T> {

	public static <T> Optional<T> none() {
		return None.instance();
	}

	public static <T> Optional<T> some(final T value) {
		Preconditions.checkNotNull(value, "value");

		return new Some<>(value);
	}

	boolean hasValue();

	T getValue() throws NoSuchElementException;

	@Override
	<R> Optional<R> flatMap(Function<? super T, ? extends Monad<? extends R>> mapper);

	@Override
	<R> Optional<R> map(Function<? super T, ? extends R> mapper);

	@Override
	Optional<T> reduce(T identity, BinaryOperator<T> accumulator);

	@Override
	Optional<T> filter(Predicate<? super T> predicate);

	@Override
	Optional<T> peek(Consumer<? super T> consumer);

}
