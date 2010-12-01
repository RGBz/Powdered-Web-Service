package org.squeakytinkerings.patterns;

public interface ResponseCommand<T> {

	T execute() throws Exception;
}
