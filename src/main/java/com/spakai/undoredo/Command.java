package com.spakai.undoredo;

public interface Command {
    public ResultInfo execute();
    public ResultInfo undo();     
}