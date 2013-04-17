package com.hartveld.queryable.util;

import com.hartveld.queryable.Queryable;

public interface Optional<T> extends Queryable<T> {

	boolean hasValue();
	T getValue() throws Exception;

	public static interface Some<T> extends Optional<T> { }
	public static interface None<T> extends Optional<T> { }

}
