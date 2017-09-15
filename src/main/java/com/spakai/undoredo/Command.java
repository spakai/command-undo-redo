package com.spakai.undoredo;

public interface Command <T1,T2> {
    public T1 execute();
    public T2 undo();     
}
