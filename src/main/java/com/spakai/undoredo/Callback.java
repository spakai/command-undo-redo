package com.spakai.undoredo;

public interface Callback<T> {
    T onSuccess(T response);
}
