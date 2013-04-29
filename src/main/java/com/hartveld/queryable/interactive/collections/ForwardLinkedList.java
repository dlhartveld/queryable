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

package com.hartveld.queryable.interactive.collections;

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

public class ForwardLinkedList<T> implements ModifiableList<T> {

	private T element;
	private ForwardLinkedList<T> next;

	public ForwardLinkedList() { }

	public ForwardLinkedList(final T element) {
		checkNotNull(element, "element");

		this.element = element;
	}

	@Override
	public int getSize() {
		if (next != null) {
			return 1 + next.getSize();
		} else if (element != null) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public T get(final int index) {
		if (index > 0) {
			if (next != null) {
				return next.get(index - 1);
			} else {
				throw new IllegalArgumentException("index >= size");
			}
		} else {
			if (element != null) {
				return element;
			} else {
				throw new IllegalArgumentException("index >= size");
			}
		}
	}

	@Override
	public boolean contains(final T element) {
		checkNotNull(element, "element");

		if (element.equals(this.element)) {
			return true;
		} else if (next != null) {
			return next.contains(element);
		} else {
			return false;
		}
	}

	@Override
	public void add(final T element) {
		checkNotNull(element, "element");

		if (this.element == null) {
			this.element = element;
		} else if (next == null) {
			next = new ForwardLinkedList<>(element);
		} else {
			next.add(element);
		}
	}

	@Override
	public void remove(final int index) {
		throw new NotImplementedException();
	}

	@Override
	public void remove(final T element) {
		checkNotNull(element, "element");

		if (this.element.equals(element)) {
			this.element = next.element;
			next = next.next;
		} else {
			next.remove(element);
		}
	}

	@Override
	public void clear() {
		element = null;

		if (next != null) {
			next.clear();
			next = null;
		}
	}

	@Override
	public Enumerator<T> iterator() {
		throw new NotImplementedException();
	}

	@Override
	public <R> Enumerable<R> flatMap(Function<? super T, ? extends Monad<? extends R>> mapper) {
		throw new NotImplementedException();
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
