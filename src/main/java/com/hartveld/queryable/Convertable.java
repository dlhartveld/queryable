package com.hartveld.queryable;

import com.hartveld.queryable.interactive.Enumerable;
import com.hartveld.queryable.reactive.Observable;

public interface Convertable<T> {

	Enumerable<T> asEnumerable();
	Observable<T> asObservable();

}
