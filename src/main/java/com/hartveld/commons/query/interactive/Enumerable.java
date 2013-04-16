package com.hartveld.commons.query.interactive;

import com.hartveld.commons.query.DoubleQueryable;
import com.hartveld.commons.query.IntQueryable;
import com.hartveld.commons.query.LongQueryable;
import com.hartveld.commons.query.Queryable;
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

public interface Enumerable<T> extends Queryable<T> {

	@Override
	Enumerable<T> filter(Predicate<? super T> predicate);

	@Override
	<R> Enumerable<R> map(Function<? super T, ? extends R> mapper);
	@Override
	IntEnumerable mapToInt(ToIntFunction<? super T> mapper);
	@Override
	LongEnumerable mapToLong(ToLongFunction<? super T> mapper);
	@Override
	DoubleEnumerable mapToDouble(ToDoubleFunction<? super T> mapper);

	@Override
	<R> Enumerable<R> flatMap(Function<? super T, ? extends Queryable<? extends R>> mapper);
	@Override
	IntEnumerable flatMapToInt(Function<? super T, ? extends IntQueryable> mapper);
	@Override
	LongEnumerable flatMapToLong(Function<? super T, ? extends LongQueryable> mapper);
	@Override
	DoubleEnumerable flatMapToDouble(Function<? super T, ? extends DoubleQueryable> mapper);

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
	boolean anyMatch(Predicate<? super T> predicate);
	@Override
	boolean allMatch(Predicate<? super T> predicate);
	@Override
	boolean noneMatch(Predicate<? super T> predicate);

	@Override
	long count();

	@Override
	Object[] toArray();
	@Override
	<A> A[] toArray(IntFunction<A[]> generator);

	@Override
	T reduce(T identity, BinaryOperator<T> accumulator);
	@Override
	Optional<T> reduce(BinaryOperator<T> accumulator);
	@Override
	<U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner);

	@Override
	<R> R collect(Supplier<R> resultFactory, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner);
	@Override
	<R> R collect(Collector<? super T, R> collector);

	@Override
	Optional<T> max(Comparator<? super T> comparator);
	@Override
	Optional<T> min(Comparator<? super T> comparator);

	@Override
	Optional<T> findFirst();
	@Override
	Optional<T> findAny();

}
