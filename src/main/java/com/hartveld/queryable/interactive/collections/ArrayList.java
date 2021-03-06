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

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.System.arraycopy;

import com.hartveld.queryable.Monad;
import com.hartveld.queryable.interactive.Enumerator;
import com.hartveld.queryable.reactive.Observable;
import java.util.NoSuchElementException;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrayList<T> implements ModifiableList<T> {

	private static final Logger LOG = LoggerFactory.getLogger(ArrayList.class);

	private static final int DEFAULT_SIZE = 10;

	public static <T> ArrayList<T> of(final T... elements) {
		checkNotNull(elements, "elements");

		return new ArrayList<>(elements);
	}

	private T[] elements;
	private int size;

	public ArrayList(final T... values) {
		checkNotNull(values, "values");

		this.elements = (T[]) new Object[values.length < DEFAULT_SIZE ? DEFAULT_SIZE : values.length];
		this.size = values.length;

		if (values.length != 0) {
			arraycopy(values, 0, this.elements, 0, values.length);
		}
	}

	@Override
	public Enumerator<T> iterator() {
		return new ArrayListEnumerator();
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public T get(final int index) {
		LOG.trace("get(): {}", index);
		checkArgument(index < size, "index >= size");

		return elements[index];
	}

	@Override
	public boolean contains(final T element) {
		LOG.trace("contains(): {}", element);

		checkNotNull(element, "value");

		for (int i = 0; i < size; i++) {
			if (elements[i].equals(element)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void add(final T element) {
		LOG.trace("add(): {}", element);

		checkNotNull(element, "element");

		if (size > elements.length) {
			throw new IllegalStateException("BUG: size > elements.length");
		} else if (size == elements.length) {
			resize();
		}

		elements[size] = element;
		size++;
	}

	@Override
	public void remove(final T element) {
		LOG.trace("remove(): {}", element);

		checkNotNull(element, "element");

		for (int i = 0; i < size; i++) {
			if (elements[i].equals(element)) {
				remove(i);
				break;
			}
		}
	}

	@Override
	public void remove(final int index) {
		LOG.trace("remove(): {}", index);

		checkArgument(index >= 0, "index < 0");
		checkArgument(index < size, "index >= size");

		elements[index] = null;
		moveAllElementsOneForwardAfter(index);
		size--;
	}

	@Override
	public void clear() {
		LOG.trace("clear()");
		size = 0;
	}

	private void resize() {
		LOG.trace("resize()");

		final T[] origin = elements;

		elements = (T[]) new Object[origin.length * 2];

		arraycopy(origin, 0, elements, 0, origin.length);
	}

	private void moveAllElementsOneForwardAfter(final int index) {
		LOG.trace("moveAllElementsOneForwardAfter(): {}", index);

		for (int i = index + 1; i < size; i++) {
			elements[i - 1] = elements[i];
		}
	}

	private class ArrayListEnumerator implements Enumerator<T> {

		private int index;

		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public T next() throws NoSuchElementException {
			if (hasNext()) {
				final T next = elements[index];

				index++;

				return next;
			} else {
				throw new NoSuchElementException("Source ArrayList has no more elements");
			}
		}

		@Override
		public <R> Enumerator<R> flatMap(Function<? super T, ? extends Monad<? extends R>> mapper) {
			throw new NotImplementedException();
		}

		@Override
		public <R> Enumerator<R> map(Function<? super T, ? extends R> mapper) {
			throw new NotImplementedException();
		}

		@Override
		public Enumerator<T> reduce(T identity, BinaryOperator<T> accumulator) {
			throw new NotImplementedException();
		}

		@Override
		public Enumerator<T> filter(Predicate<? super T> predicate) {
			throw new NotImplementedException();
		}

		@Override
		public Enumerator<T> peek(Consumer<? super T> consumer) {
			throw new NotImplementedException();
		}

		@Override
		public Enumerator<T> asEnumerator() {
			return this;
		}

		@Override
		public Observable<T> asObservable() {
			throw new NotImplementedException();
		}

		@Override
		public Enumerator<T> iterator() {
			return this;
		}

	}

}
