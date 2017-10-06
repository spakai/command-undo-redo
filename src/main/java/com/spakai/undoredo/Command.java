package com.spakai.undoredo;

public interface Command<O extends Response> {
    public O execute();
    public boolean undo();     
}