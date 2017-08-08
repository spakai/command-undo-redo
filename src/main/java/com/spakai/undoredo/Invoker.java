package com.spakai.undoredo;

import java.util.Stack;

public class Invoker {
    
    private final Stack<Command> undoStack;
    private final Stack<Command> redoStack;
    
    public Invoker() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }
    
    public void execute(Command cmd) {
        undoStack.push(cmd);
        redoStack.push(cmd);
        cmd.execute();
    }
    
    public void undo() {
        Command cmd = undoStack.pop();
        cmd.undo();
    }
    
    public void redo() {
        Command cmd = redoStack.pop();
        cmd.execute();
        
    }
}
