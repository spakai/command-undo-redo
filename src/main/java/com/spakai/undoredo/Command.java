package com.spakai.undoredo;

public interface Command {
    public boolean execute();
    public boolean undo();    
}