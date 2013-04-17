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
