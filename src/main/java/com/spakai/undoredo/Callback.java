package com.spakai.undoredo;

@FunctionalInterface
public interface Callback<T> {
    T onSuccess(T response);
}
