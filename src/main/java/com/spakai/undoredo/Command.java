package com.spakai.undoredo;

public interface Command <T1 extends Response, T2 extends Response> {
    public T1 execute();
    public T2 undo();     
}
