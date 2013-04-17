package com.hartveld.queryable.reactive;

public interface Observer<T> {

	void onNext(T value);
	void onError(Exception exception);
	void onCompleted();

}
