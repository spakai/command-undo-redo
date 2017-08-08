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
        redoStack.add(cmd);
        cmd.undo();
    }
    
    public void redo() {
        Command cmd = redoStack.pop();
        undoStack.push(cmd);
        cmd.execute();
        
    }
}
