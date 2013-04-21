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

package com.hartveld.queryable.collections;

import static com.google.common.base.Preconditions.checkNotNull;

import com.hartveld.queryable.Monad;
import com.hartveld.queryable.interactive.Enumerable;
import com.hartveld.queryable.interactive.Enumerator;
import com.hartveld.queryable.reactive.Observable;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.commons.lang.NotImplementedException;

public class ArrayList<T> implements Collection<T> {

	@Override
	public Enumerator<T> iterator() {
		return new ArrayListEnumerator<>(this);
	}

	@Override
	public long getSize() {
		throw new NotImplementedException();
	}

	@Override
	public void add(T value) {
		throw new NotImplementedException();
	}

	@Override
	public void remove(T value) {
		throw new NotImplementedException();
	}

	@Override
	public void clear() {
		throw new NotImplementedException();
	}

	@Override
	public <R> Enumerable<R> flatMap(final Function<? super T, ? extends Monad<? extends R>> mapper) {
		checkNotNull(mapper, "mapper");

		return new ArrayList<>();
	}

	@Override
	public <R> Enumerable<R> map(Function<? super T, ? extends R> mapper) {
		throw new NotImplementedException();
	}

	@Override
	public Enumerable<T> reduce(T identity, BinaryOperator<T> accumulator) {
		throw new NotImplementedException();
	}

	@Override
	public Enumerable<T> filter(Predicate<? super T> predicate) {
		throw new NotImplementedException();
	}

	@Override
	public Enumerable<T> peek(Consumer<? super T> consumer) {
		throw new NotImplementedException();
	}

	@Override
	public Enumerable<T> asEnumerable() {
		return this;
	}

	@Override
	public Observable<T> asObservable() {
		throw new NotImplementedException();
	}

}
