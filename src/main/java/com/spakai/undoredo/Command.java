package com.spakai.undoredo;

public interface Command <O extends Output> {
    public O execute();
    public O undo();    
}