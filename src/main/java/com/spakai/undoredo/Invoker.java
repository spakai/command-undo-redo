package com.spakai.undoredo;

import java.util.Stack;

public class Invoker {
    
    private final Stack<Command> undoStack;
    private final Stack<Command> redoStack;
    
    public Invoker() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }
    
    
    /**
    * Executes a command.
    * If the command is successful , push the command into undoStack
    * so that the action can be undo-ed.
    * If a command failed to execute , add it to the redo stack 
    * so that the action can be redo-ed.
    * Redo stack is cleared every time a command is successfully executed 
    * since there is no reason to redo a successful command 
    *
    * @param  cmd  Command object
    * @return      true if execute was successful, false otherwise
    */
    
    public boolean execute(Command cmd) {
        if (cmd.execute()) {
            undoStack.push(cmd);
            redoStack.clear();
            return true;
        } else {
            redoStack.add(cmd);
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
            return cmd.execute();                
        }
        
        return false;
    }
}
