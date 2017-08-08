package com.spakai.undoredo;

public interface Command {
    public void execute();

    public void undo();    
}