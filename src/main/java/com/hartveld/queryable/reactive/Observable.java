package com.hartveld.queryable.reactive;

import com.hartveld.queryable.DoubleQueryable;
import com.hartveld.queryable.IntQueryable;
import com.hartveld.queryable.LongQueryable;
import com.hartveld.queryable.Queryable;
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

public interface Observable<T> extends Queryable<T> {

	@Override
	Observable<T> filter(Predicate<? super T> predicate);

	@Override
	<R> Observable<R> map(Function<? super T, ? extends R> mapper);
	@Override
	IntObservable mapToInt(ToIntFunction<? super T> mapper);
	@Override
	LongObservable mapToLong(ToLongFunction<? super T> mapper);
	@Override
	DoubleObservable mapToDouble(ToDoubleFunction<? super T> mapper);

	@Override
	<R> Observable<R> flatMap(Function<? super T, ? extends Queryable<? extends R>> mapper);
	@Override
	IntObservable flatMapToInt(Function<? super T, ? extends IntQueryable> mapper);
	@Override
	LongObservable flatMapToLong(Function<? super T, ? extends LongQueryable> mapper);
	@Override
	DoubleObservable flatMapToDouble(Function<? super T, ? extends DoubleQueryable> mapper);

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
