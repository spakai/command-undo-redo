package com.spakai.undoredo;

import java.util.Stack;

public class Invoker {
    
    private final Stack<Command> undoStack;
    private final Stack<Command> redoStack;
    
    public Invoker() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }
    
    public boolean execute(Command cmd) {
        if (cmd.execute()) {
            undoStack.push(cmd);
            redoStack.clear();
            
            return true;
        }
        
        return false;
    }
    
    public boolean undo() {
        if (!undoStack.isEmpty()) {
            Command cmd = undoStack.pop();
            cmd.undo();
            redoStack.push(cmd);
            
            return true;
        }
        
        return false;
    }
    
    public boolean redo() {
        if (!redoStack.empty()) {
            Command cmd = redoStack.pop();
            cmd.execute();
            undoStack.push(cmd);
            
            return true;
        }
        
        return false;
    }
}
